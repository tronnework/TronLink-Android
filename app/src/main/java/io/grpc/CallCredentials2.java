package io.grpc;

import io.grpc.CallCredentials;
import java.util.concurrent.Executor;
@Deprecated
public abstract class CallCredentials2 extends CallCredentials {

    public static abstract class MetadataApplier extends CallCredentials.MetadataApplier {
    }

    public abstract void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier);

    @Override
    public final void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, final CallCredentials.MetadataApplier metadataApplier) {
        applyRequestMetadata(requestInfo, executor, new MetadataApplier() {
            @Override
            public void apply(Metadata metadata) {
                metadataApplier.apply(metadata);
            }

            @Override
            public void fail(Status status) {
                metadataApplier.fail(status);
            }
        });
    }
}
