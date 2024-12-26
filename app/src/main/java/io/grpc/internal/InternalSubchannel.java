package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ManagedClientTransport;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
public final class InternalSubchannel implements InternalInstrumented<InternalChannelz.ChannelStats>, TransportProvider {
    @Nullable
    private volatile ManagedClientTransport activeTransport;
    private volatile List<EquivalentAddressGroup> addressGroups;
    private final Index addressIndex;
    private final String authority;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final Callback callback;
    private final CallTracer callsTracer;
    private final ChannelLogger channelLogger;
    private final ChannelTracer channelTracer;
    private final InternalChannelz channelz;
    private final Stopwatch connectingTimer;
    private final InternalLogId logId;
    @Nullable
    private ConnectionClientTransport pendingTransport;
    private BackoffPolicy reconnectPolicy;
    @Nullable
    private SynchronizationContext.ScheduledHandle reconnectTask;
    private final ScheduledExecutorService scheduledExecutor;
    @Nullable
    private SynchronizationContext.ScheduledHandle shutdownDueToUpdateTask;
    @Nullable
    private ManagedClientTransport shutdownDueToUpdateTransport;
    private Status shutdownReason;
    private final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    private final String userAgent;
    private final Collection<ConnectionClientTransport> transports = new ArrayList();
    private final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>() {
        @Override
        protected void handleInUse() {
            callback.onInUse(InternalSubchannel.this);
        }

        @Override
        protected void handleNotInUse() {
            callback.onNotInUse(InternalSubchannel.this);
        }
    };
    private volatile ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);

    public static abstract class Callback {
        void onInUse(InternalSubchannel internalSubchannel) {
        }

        void onNotInUse(InternalSubchannel internalSubchannel) {
        }

        void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        void onTerminated(InternalSubchannel internalSubchannel) {
        }
    }

    public List<EquivalentAddressGroup> getAddressGroups() {
        return this.addressGroups;
    }

    public String getAuthority() {
        return this.authority;
    }

    ChannelLogger getChannelLogger() {
        return this.channelLogger;
    }

    @Override
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Nullable
    public ClientTransport getTransport() {
        return this.activeTransport;
    }

    public InternalSubchannel(List<EquivalentAddressGroup> list, String str, String str2, BackoffPolicy.Provider provider, ClientTransportFactory clientTransportFactory, ScheduledExecutorService scheduledExecutorService, Supplier<Stopwatch> supplier, SynchronizationContext synchronizationContext, Callback callback, InternalChannelz internalChannelz, CallTracer callTracer, ChannelTracer channelTracer, InternalLogId internalLogId, ChannelLogger channelLogger) {
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!list.isEmpty(), "addressGroups is empty");
        checkListHasNoNulls(list, "addressGroups contains null entry");
        List<EquivalentAddressGroup> unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.addressGroups = unmodifiableList;
        this.addressIndex = new Index(unmodifiableList);
        this.authority = str;
        this.userAgent = str2;
        this.backoffPolicyProvider = provider;
        this.transportFactory = clientTransportFactory;
        this.scheduledExecutor = scheduledExecutorService;
        this.connectingTimer = supplier.get();
        this.syncContext = synchronizationContext;
        this.callback = callback;
        this.channelz = internalChannelz;
        this.callsTracer = callTracer;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer, "channelTracer");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger, "channelLogger");
    }

    @Override
    public ClientTransport obtainActiveTransport() {
        ManagedClientTransport managedClientTransport = this.activeTransport;
        if (managedClientTransport != null) {
            return managedClientTransport;
        }
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                if (state.getState() == ConnectivityState.IDLE) {
                    channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING as requested");
                    gotoNonErrorState(ConnectivityState.CONNECTING);
                    startNewTransport();
                }
            }
        });
        return null;
    }

    public void startNewTransport() {
        InetSocketAddress inetSocketAddress;
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex.isAtBeginning()) {
            this.connectingTimer.reset().start();
        }
        SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
        if (currentAddress instanceof HttpConnectProxiedSocketAddress) {
            httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) currentAddress;
            inetSocketAddress = httpConnectProxiedSocketAddress.getTargetAddress();
        } else {
            inetSocketAddress = currentAddress;
            httpConnectProxiedSocketAddress = null;
        }
        Attributes currentEagAttributes = this.addressIndex.getCurrentEagAttributes();
        String str = (String) currentEagAttributes.get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE);
        ClientTransportFactory.ClientTransportOptions clientTransportOptions = new ClientTransportFactory.ClientTransportOptions();
        if (str == null) {
            str = this.authority;
        }
        ClientTransportFactory.ClientTransportOptions httpConnectProxiedSocketAddress2 = clientTransportOptions.setAuthority(str).setEagAttributes(currentEagAttributes).setUserAgent(this.userAgent).setHttpConnectProxiedSocketAddress(httpConnectProxiedSocketAddress);
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = getLogId();
        CallTracingTransport callTracingTransport = new CallTracingTransport(this.transportFactory.newClientTransport(inetSocketAddress, httpConnectProxiedSocketAddress2, transportLogger), this.callsTracer);
        transportLogger.logId = callTracingTransport.getLogId();
        this.channelz.addClientSocket(callTracingTransport);
        this.pendingTransport = callTracingTransport;
        this.transports.add(callTracingTransport);
        Runnable start = callTracingTransport.start(new TransportListener(callTracingTransport, inetSocketAddress));
        if (start != null) {
            this.syncContext.executeLater(start);
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Started transport {0}", transportLogger.logId);
    }

    public void scheduleBackoff(Status status) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long nextBackoffNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", printShortStatus(status), Long.valueOf(nextBackoffNanos));
        Preconditions.checkState(this.reconnectTask == null, "previous reconnectTask is not done");
        this.reconnectTask = this.syncContext.schedule(new Runnable() {
            @Override
            public void run() {
                reconnectTask = null;
                channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING after backoff");
                gotoNonErrorState(ConnectivityState.CONNECTING);
                startNewTransport();
            }
        }, nextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutor);
    }

    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                if (state.getState() != ConnectivityState.TRANSIENT_FAILURE) {
                    return;
                }
                cancelReconnectTask();
                channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
                gotoNonErrorState(ConnectivityState.CONNECTING);
                startNewTransport();
            }
        });
    }

    public void gotoNonErrorState(ConnectivityState connectivityState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forNonError(connectivityState));
    }

    private void gotoState(ConnectivityStateInfo connectivityStateInfo) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.state.getState() != connectivityStateInfo.getState()) {
            boolean z = this.state.getState() != ConnectivityState.SHUTDOWN;
            Preconditions.checkState(z, "Cannot transition out of SHUTDOWN to " + connectivityStateInfo);
            this.state = connectivityStateInfo;
            this.callback.onStateChange(this, connectivityStateInfo);
        }
    }

    public void updateAddresses(List<EquivalentAddressGroup> list) {
        Preconditions.checkNotNull(list, "newAddressGroups");
        checkListHasNoNulls(list, "newAddressGroups contains null entry");
        Preconditions.checkArgument(!list.isEmpty(), "newAddressGroups is empty");
        final List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                


return;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.internal.InternalSubchannel.4.run():void");
            }
        });
    }

    public void shutdown(final Status status) {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                if (state.getState() == ConnectivityState.SHUTDOWN) {
                    return;
                }
                shutdownReason = status;
                ManagedClientTransport managedClientTransport = activeTransport;
                ConnectionClientTransport connectionClientTransport = pendingTransport;
                activeTransport = null;
                pendingTransport = null;
                gotoNonErrorState(ConnectivityState.SHUTDOWN);
                addressIndex.reset();
                if (transports.isEmpty()) {
                    handleTermination();
                }
                cancelReconnectTask();
                if (shutdownDueToUpdateTask != null) {
                    shutdownDueToUpdateTask.cancel();
                    shutdownDueToUpdateTransport.shutdown(status);
                    shutdownDueToUpdateTask = null;
                    shutdownDueToUpdateTransport = null;
                }
                if (managedClientTransport != null) {
                    managedClientTransport.shutdown(status);
                }
                if (connectionClientTransport != null) {
                    connectionClientTransport.shutdown(status);
                }
            }
        });
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("addressGroups", this.addressGroups).toString();
    }

    public void handleTermination() {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
                callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    public void handleTransportInUseState(final ConnectionClientTransport connectionClientTransport, final boolean z) {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                inUseStateAggregator.updateObjectInUse(connectionClientTransport, z);
            }
        });
    }

    public void shutdownNow(final Status status) {
        shutdown(status);
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                for (ManagedClientTransport managedClientTransport : new ArrayList(transports)) {
                    managedClientTransport.shutdownNow(status);
                }
            }
        });
    }

    public void cancelReconnectTask() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.reconnectTask;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    @Override
    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                List<EquivalentAddressGroup> groups = addressIndex.getGroups();
                ArrayList arrayList = new ArrayList(transports);
                builder.setTarget(groups.toString()).setState(getState());
                builder.setSockets(arrayList);
                callsTracer.updateBuilder(builder);
                channelTracer.updateBuilder(builder);
                create.set(builder.build());
            }
        });
        return create;
    }

    public ConnectivityState getState() {
        return this.state.getState();
    }

    private static void checkListHasNoNulls(List<?> list, String str) {
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), str);
        }
    }

    public class TransportListener implements ManagedClientTransport.Listener {
        final SocketAddress address;
        boolean shutdownInitiated = false;
        final ConnectionClientTransport transport;

        TransportListener(ConnectionClientTransport connectionClientTransport, SocketAddress socketAddress) {
            this.transport = connectionClientTransport;
            this.address = socketAddress;
        }

        @Override
        public void transportReady() {
            channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "READY");
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    reconnectPolicy = null;
                    if (shutdownReason != null) {
                        Preconditions.checkState(activeTransport == null, "Unexpected non-null activeTransport");
                        TransportListener.this.transport.shutdown(shutdownReason);
                    } else if (pendingTransport == TransportListener.this.transport) {
                        activeTransport = TransportListener.this.transport;
                        pendingTransport = null;
                        gotoNonErrorState(ConnectivityState.READY);
                    }
                }
            });
        }

        @Override
        public void transportInUse(boolean z) {
            handleTransportInUseState(this.transport, z);
        }

        @Override
        public void transportShutdown(final Status status) {
            channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} SHUTDOWN with {1}", this.transport.getLogId(), printShortStatus(status));
            this.shutdownInitiated = true;
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    if (state.getState() == ConnectivityState.SHUTDOWN) {
                        return;
                    }
                    if (activeTransport == TransportListener.this.transport) {
                        activeTransport = null;
                        addressIndex.reset();
                        gotoNonErrorState(ConnectivityState.IDLE);
                    } else if (pendingTransport == TransportListener.this.transport) {
                        Preconditions.checkState(state.getState() == ConnectivityState.CONNECTING, "Expected state is CONNECTING, actual state is %s", state.getState());
                        addressIndex.increment();
                        if (!addressIndex.isValid()) {
                            pendingTransport = null;
                            addressIndex.reset();
                            scheduleBackoff(status);
                            return;
                        }
                        startNewTransport();
                    }
                }
            });
        }

        @Override
        public void transportTerminated() {
            Preconditions.checkState(this.shutdownInitiated, "transportShutdown() must be called before transportTerminated().");
            channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} Terminated", this.transport.getLogId());
            channelz.removeClientSocket(this.transport);
            handleTransportInUseState(this.transport, false);
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    transports.remove(TransportListener.this.transport);
                    if (state.getState() == ConnectivityState.SHUTDOWN && transports.isEmpty()) {
                        handleTermination();
                    }
                }
            });
        }
    }

    public static final class CallTracingTransport extends ForwardingConnectionClientTransport {
        private final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        @Override
        protected ConnectionClientTransport delegate() {
            return this.delegate;
        }

        private CallTracingTransport(ConnectionClientTransport connectionClientTransport, CallTracer callTracer) {
            this.delegate = connectionClientTransport;
            this.callTracer = callTracer;
        }

        @Override
        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
            final ClientStream newStream = super.newStream(methodDescriptor, metadata, callOptions);
            return new ForwardingClientStream() {
                @Override
                protected ClientStream delegate() {
                    return newStream;
                }

                @Override
                public void start(final ClientStreamListener clientStreamListener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener() {
                        @Override
                        protected ClientStreamListener delegate() {
                            return clientStreamListener;
                        }

                        @Override
                        public void closed(Status status, Metadata metadata2) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, metadata2);
                        }

                        @Override
                        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata2) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, metadata2);
                        }
                    });
                }
            };
        }
    }

    public static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public List<EquivalentAddressGroup> getGroups() {
            return this.addressGroups;
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public Index(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public void increment() {
            int i = this.addressIndex + 1;
            this.addressIndex = i;
            if (i >= this.addressGroups.get(this.groupIndex).getAddresses().size()) {
                this.groupIndex++;
                this.addressIndex = 0;
            }
        }

        public SocketAddress getCurrentAddress() {
            return this.addressGroups.get(this.groupIndex).getAddresses().get(this.addressIndex);
        }

        public Attributes getCurrentEagAttributes() {
            return this.addressGroups.get(this.groupIndex).getAttributes();
        }

        public void updateGroups(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
            reset();
        }

        public boolean seekTo(SocketAddress socketAddress) {
            for (int i = 0; i < this.addressGroups.size(); i++) {
                int indexOf = this.addressGroups.get(i).getAddresses().indexOf(socketAddress);
                if (indexOf != -1) {
                    this.groupIndex = i;
                    this.addressIndex = indexOf;
                    return true;
                }
            }
            return false;
        }
    }

    public String printShortStatus(Status status) {
        StringBuilder sb = new StringBuilder();
        sb.append(status.getCode());
        if (status.getDescription() != null) {
            sb.append("(");
            sb.append(status.getDescription());
            sb.append(")");
        }
        return sb.toString();
    }

    public static final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        TransportLogger() {
        }

        @Override
        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str);
        }

        @Override
        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str, objArr);
        }
    }
}
