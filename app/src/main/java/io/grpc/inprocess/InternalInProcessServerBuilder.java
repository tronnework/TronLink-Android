package io.grpc.inprocess;
public class InternalInProcessServerBuilder {
    public static void setStatsEnabled(InProcessServerBuilder inProcessServerBuilder, boolean z) {
        inProcessServerBuilder.setStatsEnabled(z);
    }

    private InternalInProcessServerBuilder() {
    }
}
