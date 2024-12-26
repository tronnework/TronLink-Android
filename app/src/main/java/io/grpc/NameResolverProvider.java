package io.grpc;

import io.grpc.NameResolver;
public abstract class NameResolverProvider extends NameResolver.Factory {
    public abstract boolean isAvailable();

    public abstract int priority();
}
