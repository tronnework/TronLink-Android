package io.grpc.internal;

import javax.annotation.Nullable;
public interface TransportProvider {
    @Nullable
    ClientTransport obtainActiveTransport();
}
