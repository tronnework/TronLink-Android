package io.grpc.internal;

import com.google.common.base.Stopwatch;
import io.grpc.internal.ClientTransport;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Http2Ping {
    private static final Logger log = Logger.getLogger(Http2Ping.class.getName());
    private Map<ClientTransport.PingCallback, Executor> callbacks = new LinkedHashMap();
    private boolean completed;
    private final long data;
    private Throwable failureCause;
    private long roundTripTimeNanos;
    private final Stopwatch stopwatch;

    public long payload() {
        return this.data;
    }

    public Http2Ping(long j, Stopwatch stopwatch) {
        this.data = j;
        this.stopwatch = stopwatch;
    }

    public void addCallback(ClientTransport.PingCallback pingCallback, Executor executor) {
        Runnable asRunnable;
        synchronized (this) {
            if (!this.completed) {
                this.callbacks.put(pingCallback, executor);
                return;
            }
            Throwable th = this.failureCause;
            if (th != null) {
                asRunnable = asRunnable(pingCallback, th);
            } else {
                asRunnable = asRunnable(pingCallback, this.roundTripTimeNanos);
            }
            doExecute(executor, asRunnable);
        }
    }

    public boolean complete() {
        synchronized (this) {
            if (this.completed) {
                return false;
            }
            this.completed = true;
            long elapsed = this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
            this.roundTripTimeNanos = elapsed;
            Map<ClientTransport.PingCallback, Executor> map = this.callbacks;
            this.callbacks = null;
            for (Map.Entry<ClientTransport.PingCallback, Executor> entry : map.entrySet()) {
                doExecute(entry.getValue(), asRunnable(entry.getKey(), elapsed));
            }
            return true;
        }
    }

    public void failed(Throwable th) {
        synchronized (this) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            this.failureCause = th;
            Map<ClientTransport.PingCallback, Executor> map = this.callbacks;
            this.callbacks = null;
            for (Map.Entry<ClientTransport.PingCallback, Executor> entry : map.entrySet()) {
                notifyFailed(entry.getKey(), entry.getValue(), th);
            }
        }
    }

    public static void notifyFailed(ClientTransport.PingCallback pingCallback, Executor executor, Throwable th) {
        doExecute(executor, asRunnable(pingCallback, th));
    }

    private static void doExecute(Executor executor, Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Throwable th) {
            log.log(Level.SEVERE, "Failed to execute PingCallback", th);
        }
    }

    private static Runnable asRunnable(final ClientTransport.PingCallback pingCallback, final long j) {
        return new Runnable() {
            @Override
            public void run() {
                ClientTransport.PingCallback.this.onSuccess(j);
            }
        };
    }

    private static Runnable asRunnable(final ClientTransport.PingCallback pingCallback, final Throwable th) {
        return new Runnable() {
            @Override
            public void run() {
                ClientTransport.PingCallback.this.onFailure(th);
            }
        };
    }
}
