package io.grpc.internal;

import java.util.concurrent.TimeUnit;
public interface TimeProvider {
    public static final TimeProvider SYSTEM_TIME_PROVIDER = new TimeProvider() {
        @Override
        public long currentTimeNanos() {
            return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        }
    };

    long currentTimeNanos();
}
