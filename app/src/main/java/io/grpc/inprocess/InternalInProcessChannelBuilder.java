package io.grpc.inprocess;
public final class InternalInProcessChannelBuilder {
    public static void setStatsEnabled(InProcessChannelBuilder inProcessChannelBuilder, boolean z) {
        inProcessChannelBuilder.setStatsEnabled(z);
    }

    private InternalInProcessChannelBuilder() {
    }
}
