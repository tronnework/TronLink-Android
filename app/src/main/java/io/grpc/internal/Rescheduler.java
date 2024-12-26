package io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
public final class Rescheduler {
    private boolean enabled;
    private long runAtNanos;
    private final Runnable runnable;
    private final ScheduledExecutorService scheduler;
    private final Executor serializingExecutor;
    private final Stopwatch stopwatch;
    private ScheduledFuture<?> wakeUp;

    public Rescheduler(Runnable runnable, Executor executor, ScheduledExecutorService scheduledExecutorService, Stopwatch stopwatch) {
        this.runnable = runnable;
        this.serializingExecutor = executor;
        this.scheduler = scheduledExecutorService;
        this.stopwatch = stopwatch;
        stopwatch.start();
    }

    public void reschedule(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        long nanoTime = nanoTime() + nanos;
        this.enabled = true;
        if (nanoTime - this.runAtNanos < 0 || this.wakeUp == null) {
            ScheduledFuture<?> scheduledFuture = this.wakeUp;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.wakeUp = this.scheduler.schedule(new FutureRunnable(), nanos, TimeUnit.NANOSECONDS);
        }
        this.runAtNanos = nanoTime;
    }

    public void cancel(boolean z) {
        ScheduledFuture<?> scheduledFuture;
        this.enabled = false;
        if (!z || (scheduledFuture = this.wakeUp) == null) {
            return;
        }
        scheduledFuture.cancel(false);
        this.wakeUp = null;
    }

    private final class FutureRunnable implements Runnable {
        private FutureRunnable() {
        }

        @Override
        public void run() {
            serializingExecutor.execute(new ChannelFutureRunnable());
        }

        public boolean isEnabled() {
            return enabled;
        }
    }

    private final class ChannelFutureRunnable implements Runnable {
        private ChannelFutureRunnable() {
        }

        @Override
        public void run() {
            if (!enabled) {
                wakeUp = null;
                return;
            }
            long nanoTime = nanoTime();
            if (runAtNanos - nanoTime > 0) {
                Rescheduler rescheduler = Rescheduler.this;
                rescheduler.wakeUp = rescheduler.scheduler.schedule(new FutureRunnable(), runAtNanos - nanoTime, TimeUnit.NANOSECONDS);
                return;
            }
            enabled = false;
            wakeUp = null;
            runnable.run();
        }
    }

    static boolean isEnabled(Runnable runnable) {
        return ((FutureRunnable) runnable).isEnabled();
    }

    public long nanoTime() {
        return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
    }
}
