package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Status;
import io.grpc.internal.ClientTransport;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
public class KeepAliveManager {
    private final boolean keepAliveDuringTransportIdle;
    private final KeepAlivePinger keepAlivePinger;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private ScheduledFuture<?> pingFuture;
    private final ScheduledExecutorService scheduler;
    private final Runnable sendPing;
    private final Runnable shutdown;
    private ScheduledFuture<?> shutdownFuture;
    private State state;
    private final Stopwatch stopwatch;
    private static final long MIN_KEEPALIVE_TIME_NANOS = TimeUnit.SECONDS.toNanos(10);
    private static final long MIN_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(10);

    public interface KeepAlivePinger {
        void onPingTimeout();

        void ping();
    }

    public enum State {
        IDLE,
        PING_SCHEDULED,
        PING_DELAYED,
        PING_SENT,
        IDLE_AND_PING_SENT,
        DISCONNECTED
    }

    public KeepAliveManager(KeepAlivePinger keepAlivePinger, ScheduledExecutorService scheduledExecutorService, long j, long j2, boolean z) {
        this(keepAlivePinger, scheduledExecutorService, Stopwatch.createUnstarted(), j, j2, z);
    }

    KeepAliveManager(KeepAlivePinger keepAlivePinger, ScheduledExecutorService scheduledExecutorService, Stopwatch stopwatch, long j, long j2, boolean z) {
        this.state = State.IDLE;
        this.shutdown = new LogExceptionRunnable(new Runnable() {
            @Override
            public void run() {
                boolean z2;
                synchronized (KeepAliveManager.this) {
                    if (state != State.DISCONNECTED) {
                        state = State.DISCONNECTED;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    keepAlivePinger.onPingTimeout();
                }
            }
        });
        this.sendPing = new LogExceptionRunnable(new Runnable() {
            @Override
            public void run() {
                boolean z2;
                synchronized (KeepAliveManager.this) {
                    pingFuture = null;
                    if (state == State.PING_SCHEDULED) {
                        state = State.PING_SENT;
                        KeepAliveManager keepAliveManager = KeepAliveManager.this;
                        keepAliveManager.shutdownFuture = keepAliveManager.scheduler.schedule(shutdown, keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
                        z2 = true;
                    } else {
                        if (state == State.PING_DELAYED) {
                            KeepAliveManager keepAliveManager2 = KeepAliveManager.this;
                            keepAliveManager2.pingFuture = keepAliveManager2.scheduler.schedule(sendPing, keepAliveTimeInNanos - stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
                            state = State.PING_SCHEDULED;
                        }
                        z2 = false;
                    }
                }
                if (z2) {
                    keepAlivePinger.ping();
                }
            }
        });
        this.keepAlivePinger = (KeepAlivePinger) Preconditions.checkNotNull(keepAlivePinger, "keepAlivePinger");
        this.scheduler = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.keepAliveDuringTransportIdle = z;
        stopwatch.reset().start();
    }

    public synchronized void onTransportStarted() {
        if (this.keepAliveDuringTransportIdle) {
            onTransportActive();
        }
    }

    public synchronized void onDataReceived() {
        this.stopwatch.reset().start();
        if (this.state == State.PING_SCHEDULED) {
            this.state = State.PING_DELAYED;
        } else if (this.state == State.PING_SENT || this.state == State.IDLE_AND_PING_SENT) {
            ScheduledFuture<?> scheduledFuture = this.shutdownFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            if (this.state == State.IDLE_AND_PING_SENT) {
                this.state = State.IDLE;
                return;
            }
            this.state = State.PING_SCHEDULED;
            Preconditions.checkState(this.pingFuture == null, "There should be no outstanding pingFuture");
            this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos, TimeUnit.NANOSECONDS);
        }
    }

    public synchronized void onTransportActive() {
        if (this.state == State.IDLE) {
            this.state = State.PING_SCHEDULED;
            if (this.pingFuture == null) {
                this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos - this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
            }
        } else if (this.state == State.IDLE_AND_PING_SENT) {
            this.state = State.PING_SENT;
        }
    }

    public synchronized void onTransportIdle() {
        if (this.keepAliveDuringTransportIdle) {
            return;
        }
        if (this.state == State.PING_SCHEDULED || this.state == State.PING_DELAYED) {
            this.state = State.IDLE;
        }
        if (this.state == State.PING_SENT) {
            this.state = State.IDLE_AND_PING_SENT;
        }
    }

    public synchronized void onTransportTermination() {
        if (this.state != State.DISCONNECTED) {
            this.state = State.DISCONNECTED;
            ScheduledFuture<?> scheduledFuture = this.shutdownFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledFuture<?> scheduledFuture2 = this.pingFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
                this.pingFuture = null;
            }
        }
    }

    public static long clampKeepAliveTimeInNanos(long j) {
        return Math.max(j, MIN_KEEPALIVE_TIME_NANOS);
    }

    public static long clampKeepAliveTimeoutInNanos(long j) {
        return Math.max(j, MIN_KEEPALIVE_TIMEOUT_NANOS);
    }

    public static final class ClientKeepAlivePinger implements KeepAlivePinger {
        private final ConnectionClientTransport transport;

        public ClientKeepAlivePinger(ConnectionClientTransport connectionClientTransport) {
            this.transport = connectionClientTransport;
        }

        @Override
        public void ping() {
            this.transport.ping(new ClientTransport.PingCallback() {
                @Override
                public void onSuccess(long j) {
                }

                @Override
                public void onFailure(Throwable th) {
                    ClientKeepAlivePinger.this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
                }
            }, MoreExecutors.directExecutor());
        }

        @Override
        public void onPingTimeout() {
            this.transport.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
        }
    }
}
