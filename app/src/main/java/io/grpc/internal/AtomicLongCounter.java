package io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;
final class AtomicLongCounter implements LongCounter {
    private final AtomicLong counter = new AtomicLong();

    @Override
    public void add(long j) {
        this.counter.getAndAdd(j);
    }

    @Override
    public long value() {
        return this.counter.get();
    }
}
