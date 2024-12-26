package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientCall;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
public class DelayedClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final boolean $assertionsDisabled = false;
    private final Executor callExecutor;
    private final Context context;
    private DelayedListener<RespT> delayedListener;
    private Status error;
    @Nullable
    private final ScheduledFuture<?> initialDeadlineMonitor;
    private ClientCall.Listener<RespT> listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingRunnables = new ArrayList();
    private ClientCall<ReqT, RespT> realCall;
    private static final Logger logger = Logger.getLogger(DelayedClientCall.class.getName());
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        @Override
        public void cancel(String str, Throwable th) {
        }

        @Override
        public void halfClose() {
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void request(int i) {
        }

        @Override
        public void sendMessage(Object obj) {
        }

        @Override
        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };

    public void callCancelled() {
    }

    final ClientCall<ReqT, RespT> getRealCall() {
        return this.realCall;
    }

    public DelayedClientCall(Executor executor, ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        this.callExecutor = (Executor) Preconditions.checkNotNull(executor, "callExecutor");
        Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.context = Context.current();
        this.initialDeadlineMonitor = scheduleDeadlineIfNeeded(scheduledExecutorService, deadline);
    }

    @Nullable
    private ScheduledFuture<?> scheduleDeadlineIfNeeded(ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        Deadline deadline2 = this.context.getDeadline();
        if (deadline == null && deadline2 == null) {
            return null;
        }
        long min = deadline != null ? Math.min(Long.MAX_VALUE, deadline.timeRemaining(TimeUnit.NANOSECONDS)) : Long.MAX_VALUE;
        if (deadline2 != null && deadline2.timeRemaining(TimeUnit.NANOSECONDS) < min) {
            min = deadline2.timeRemaining(TimeUnit.NANOSECONDS);
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(min)));
                if (deadline == null) {
                    sb.append(" Explicit call timeout was not set.");
                } else {
                    sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(deadline.timeRemaining(TimeUnit.NANOSECONDS))));
                }
                logger2.fine(sb.toString());
            }
        }
        long abs = Math.abs(min) / TimeUnit.SECONDS.toNanos(1L);
        long abs2 = Math.abs(min) % TimeUnit.SECONDS.toNanos(1L);
        final StringBuilder sb2 = new StringBuilder();
        if (min < 0) {
            sb2.append("ClientCall started after deadline exceeded. Deadline exceeded after -");
        } else {
            sb2.append("Deadline exceeded after ");
        }
        sb2.append(abs);
        sb2.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
        sb2.append("s. ");
        return scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                cancel(Status.DEADLINE_EXCEEDED.withDescription(sb2.toString()), true);
            }
        }, min, TimeUnit.NANOSECONDS);
    }

    public final void setCall(ClientCall<ReqT, RespT> clientCall) {
        synchronized (this) {
            if (this.realCall != null) {
                return;
            }
            setRealCall((ClientCall) Preconditions.checkNotNull(clientCall, NotificationCompat.CATEGORY_CALL));
            drainPendingCalls();
        }
    }

    @Override
    public final void start(final ClientCall.Listener<RespT> listener, final Metadata metadata) {
        Status status;
        boolean z;
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            this.listener = (ClientCall.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedListener<RespT> delayedListener = new DelayedListener<>(listener);
                this.delayedListener = delayedListener;
                listener = delayedListener;
            }
        }
        if (status != null) {
            this.callExecutor.execute(new CloseListenerRunnable(listener, status));
        } else if (z) {
            this.realCall.start(listener, metadata);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realCall.start(listener, metadata);
                }
            });
        }
    }

    @Override
    public final void cancel(@Nullable String str, @Nullable Throwable th) {
        Status withDescription;
        Status status = Status.CANCELLED;
        if (str != null) {
            withDescription = status.withDescription(str);
        } else {
            withDescription = status.withDescription("Call cancelled without message");
        }
        if (th != null) {
            withDescription = withDescription.withCause(th);
        }
        cancel(withDescription, false);
    }

    public void cancel(final Status status, boolean z) {
        boolean z2;
        ClientCall.Listener<RespT> listener;
        synchronized (this) {
            if (this.realCall == null) {
                setRealCall(NOOP_CALL);
                listener = this.listener;
                this.error = status;
                z2 = false;
            } else if (z) {
                return;
            } else {
                z2 = true;
                listener = null;
            }
            if (z2) {
                delayOrExecute(new Runnable() {
                    @Override
                    public void run() {
                        realCall.cancel(status.getDescription(), status.getCause());
                    }
                });
            } else {
                if (listener != null) {
                    this.callExecutor.execute(new CloseListenerRunnable(listener, status));
                }
                drainPendingCalls();
            }
            callCancelled();
        }
    }

    private void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    private void drainPendingCalls() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.internal.DelayedClientCall.drainPendingCalls():void");
    }

    private void setRealCall(ClientCall<ReqT, RespT> clientCall) {
        ClientCall<ReqT, RespT> clientCall2 = this.realCall;
        Preconditions.checkState(clientCall2 == null, "realCall already set to %s", clientCall2);
        ScheduledFuture<?> scheduledFuture = this.initialDeadlineMonitor;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.realCall = clientCall;
    }

    @Override
    public final void sendMessage(final ReqT reqt) {
        if (this.passThrough) {
            this.realCall.sendMessage(reqt);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realCall.sendMessage(reqt);
                }
            });
        }
    }

    @Override
    public final void setMessageCompression(final boolean z) {
        if (this.passThrough) {
            this.realCall.setMessageCompression(z);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realCall.setMessageCompression(z);
                }
            });
        }
    }

    @Override
    public final void request(final int i) {
        if (this.passThrough) {
            this.realCall.request(i);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realCall.request(i);
                }
            });
        }
    }

    @Override
    public final void halfClose() {
        delayOrExecute(new Runnable() {
            @Override
            public void run() {
                realCall.halfClose();
            }
        });
    }

    @Override
    public final boolean isReady() {
        if (this.passThrough) {
            return this.realCall.isReady();
        }
        return false;
    }

    @Override
    public final Attributes getAttributes() {
        ClientCall<ReqT, RespT> clientCall;
        synchronized (this) {
            clientCall = this.realCall;
        }
        if (clientCall != null) {
            return clientCall.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("realCall", this.realCall).toString();
    }

    public final class CloseListenerRunnable extends ContextRunnable {
        final ClientCall.Listener<RespT> listener;
        final Status status;

        CloseListenerRunnable(ClientCall.Listener<RespT> listener, Status status) {
            super(context);
            this.listener = listener;
            this.status = status;
        }

        @Override
        public void runInContext() {
            this.listener.onClose(this.status, new Metadata());
        }
    }

    public static final class DelayedListener<RespT> extends ClientCall.Listener<RespT> {
        static final boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        private final ClientCall.Listener<RespT> realListener;

        public DelayedListener(ClientCall.Listener<RespT> listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        @Override
        public void onHeaders(final Metadata metadata) {
            if (this.passThrough) {
                this.realListener.onHeaders(metadata);
            } else {
                delayOrExecute(new Runnable() {
                    @Override
                    public void run() {
                        DelayedListener.this.realListener.onHeaders(metadata);
                    }
                });
            }
        }

        @Override
        public void onMessage(final RespT respt) {
            if (this.passThrough) {
                this.realListener.onMessage(respt);
            } else {
                delayOrExecute(new Runnable() {
                    @Override
                    public void run() {
                        DelayedListener.this.realListener.onMessage(respt);
                    }
                });
            }
        }

        @Override
        public void onClose(final Status status, final Metadata metadata) {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    DelayedListener.this.realListener.onClose(status, metadata);
                }
            });
        }

        @Override
        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() {
                    @Override
                    public void run() {
                        DelayedListener.this.realListener.onReady();
                    }
                });
            }
        }

        void drainPendingCallbacks() {
            List<Runnable> list;
            List arrayList = new ArrayList();
            while (true) {
                synchronized (this) {
                    if (this.pendingCallbacks.isEmpty()) {
                        this.pendingCallbacks = null;
                        this.passThrough = true;
                        return;
                    }
                    list = this.pendingCallbacks;
                    this.pendingCallbacks = arrayList;
                }
                for (Runnable runnable : list) {
                    runnable.run();
                }
                list.clear();
                arrayList = list;
            }
        }
    }
}
