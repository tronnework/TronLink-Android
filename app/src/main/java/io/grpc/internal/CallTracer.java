package io.grpc.internal;

import io.grpc.InternalChannelz;
public final class CallTracer {
    static final Factory DEFAULT_FACTORY = new Factory() {
        @Override
        public CallTracer create() {
            return new CallTracer(TimeProvider.SYSTEM_TIME_PROVIDER);
        }
    };
    private volatile long lastCallStartedNanos;
    private final TimeProvider timeProvider;
    private final LongCounter callsStarted = LongCounterFactory.create();
    private final LongCounter callsSucceeded = LongCounterFactory.create();
    private final LongCounter callsFailed = LongCounterFactory.create();

    public interface Factory {
        CallTracer create();
    }

    public static Factory getDefaultFactory() {
        return DEFAULT_FACTORY;
    }

    public CallTracer(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public void reportCallStarted() {
        this.callsStarted.add(1L);
        this.lastCallStartedNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportCallEnded(boolean z) {
        if (z) {
            this.callsSucceeded.add(1L);
        } else {
            this.callsFailed.add(1L);
        }
    }

    public void updateBuilder(InternalChannelz.ChannelStats.Builder builder) {
        builder.setCallsStarted(this.callsStarted.value()).setCallsSucceeded(this.callsSucceeded.value()).setCallsFailed(this.callsFailed.value()).setLastCallStartedNanos(this.lastCallStartedNanos);
    }

    public void updateBuilder(InternalChannelz.ServerStats.Builder builder) {
        builder.setCallsStarted(this.callsStarted.value()).setCallsSucceeded(this.callsSucceeded.value()).setCallsFailed(this.callsFailed.value()).setLastCallStartedNanos(this.lastCallStartedNanos);
    }
}
