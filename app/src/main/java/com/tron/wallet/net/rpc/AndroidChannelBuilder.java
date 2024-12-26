package com.tron.wallet.net.rpc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.common.base.Preconditions;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.MethodDescriptor;
import io.grpc.internal.GrpcUtil;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
public class AndroidChannelBuilder extends ForwardingChannelBuilder<AndroidChannelBuilder> {
    private static final String LOG_TAG = "AndroidChannelBuilder";
    @Nullable
    private Context context;
    private final ManagedChannelBuilder<?> delegateBuilder;

    public AndroidChannelBuilder context(Context context) {
        this.context = context;
        return this;
    }

    @Override
    protected ManagedChannelBuilder<?> delegate() {
        return this.delegateBuilder;
    }

    public static AndroidChannelBuilder forTarget(String str) {
        return new AndroidChannelBuilder(str);
    }

    public static AndroidChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    @Deprecated
    public static AndroidChannelBuilder fromBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
        return usingBuilder(managedChannelBuilder);
    }

    public static AndroidChannelBuilder usingBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
        return new AndroidChannelBuilder(managedChannelBuilder);
    }

    private AndroidChannelBuilder(String str) {
        this.delegateBuilder = ManagedChannelBuilder.forTarget(str);
    }

    private AndroidChannelBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
        this.delegateBuilder = (ManagedChannelBuilder) Preconditions.checkNotNull(managedChannelBuilder, "delegateBuilder");
    }

    @Override
    public ManagedChannel build() {
        return new AndroidChannel(this.delegateBuilder.build(), this.context);
    }

    public static final class AndroidChannel extends ManagedChannel {
        @Nullable
        private final ConnectivityManager connectivityManager;
        @Nullable
        private final Context context;
        private final ManagedChannel delegate;
        private final Object lock = new Object();
        private RxManager rxManager = new RxManager();
        private Runnable unregisterRunnable;

        AndroidChannel(ManagedChannel managedChannel, @Nullable Context context) {
            this.delegate = managedChannel;
            this.context = context;
            if (context == null) {
                this.connectivityManager = null;
                return;
            }
            this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            try {
                configureNetworkMonitoring();
            } catch (SecurityException e) {
                LogUtils.w(AndroidChannelBuilder.LOG_TAG, "Failed to configure network monitoring. Does app have ACCESS_NETWORK_STATE permission?" + e.toString());
            }
        }

        private void configureNetworkMonitoring() {
            if (Build.VERSION.SDK_INT >= 24 && this.connectivityManager != null) {
                final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
                this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
                this.unregisterRunnable = new Runnable() {
                    @Override
                    public void run() {
                        AndroidChannel.this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
                    }
                };
                return;
            }
            final NetworkReceiver networkReceiver = new NetworkReceiver();
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            if (Build.VERSION.SDK_INT >= 26) {
                this.context.registerReceiver(networkReceiver, intentFilter, 2);
            } else {
                this.context.registerReceiver(networkReceiver, intentFilter);
            }
            this.unregisterRunnable = new Runnable() {
                @Override
                public void run() {
                    AndroidChannel.this.context.unregisterReceiver(networkReceiver);
                }
            };
        }

        private void unregisterNetworkListener() {
            synchronized (this.lock) {
                Runnable runnable = this.unregisterRunnable;
                if (runnable != null) {
                    runnable.run();
                    this.unregisterRunnable = null;
                }
            }
        }

        @Override
        public ManagedChannel shutdown() {
            unregisterNetworkListener();
            return this.delegate.shutdown();
        }

        @Override
        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override
        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override
        public ManagedChannel shutdownNow() {
            unregisterNetworkListener();
            return this.delegate.shutdownNow();
        }

        @Override
        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        @Override
        public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
            return this.delegate.newCall(methodDescriptor, callOptions);
        }

        @Override
        public String authority() {
            return this.delegate.authority();
        }

        @Override
        public ConnectivityState getState(boolean z) {
            return this.delegate.getState(z);
        }

        @Override
        public void notifyWhenStateChanged(ConnectivityState connectivityState, Runnable runnable) {
            this.delegate.notifyWhenStateChanged(connectivityState, runnable);
        }

        @Override
        public void resetConnectBackoff() {
            this.delegate.resetConnectBackoff();
        }

        @Override
        public void enterIdle() {
            this.delegate.enterIdle();
        }

        public class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
            private DefaultNetworkCallback() {
            }

            @Override
            public void onAvailable(Network network) {
                AndroidChannel.this.delegate.enterIdle();
                LogUtils.w(AndroidChannelBuilder.LOG_TAG, "onAvailable:   network: " + network.toString());
                AndroidChannel.this.rxManager.post(Event.NODE_CONNECTED, 0);
            }

            @Override
            public void onBlockedStatusChanged(Network network, boolean z) {
                LogUtils.w(AndroidChannelBuilder.LOG_TAG, "blocked: " + z + "  network: " + network.toString());
                if (z) {
                    AndroidChannel.this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                } else {
                    AndroidChannel.this.rxManager.post(Event.NODE_CONNECTED, 0);
                }
                if (z) {
                    return;
                }
                AndroidChannel.this.delegate.enterIdle();
            }
        }

        public class NetworkReceiver extends BroadcastReceiver {
            private boolean isConnected;

            private NetworkReceiver() {
                this.isConnected = false;
            }

            @Override
            public void onReceive(Context context, Intent intent) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                boolean z = this.isConnected;
                boolean z2 = false;
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    z2 = true;
                }
                this.isConnected = z2;
                LogUtils.w(AndroidChannelBuilder.LOG_TAG, "isConnected: " + this.isConnected + "  wasConnected: " + z);
                if (!this.isConnected || z) {
                    AndroidChannel.this.rxManager.post(Event.NODE_CONNECTED_FAILED, 0);
                } else {
                    AndroidChannel.this.rxManager.post(Event.NODE_CONNECTED, 0);
                }
                if (!this.isConnected || z) {
                    return;
                }
                AndroidChannel.this.delegate.enterIdle();
            }
        }
    }
}
