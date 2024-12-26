package io.grpc.internal;

import io.grpc.internal.SharedResourceHolder;
public final class SharedResourcePool<T> implements ObjectPool<T> {
    private final SharedResourceHolder.Resource<T> resource;

    private SharedResourcePool(SharedResourceHolder.Resource<T> resource) {
        this.resource = resource;
    }

    public static <T> SharedResourcePool<T> forResource(SharedResourceHolder.Resource<T> resource) {
        return new SharedResourcePool<>(resource);
    }

    @Override
    public T getObject() {
        return (T) SharedResourceHolder.get(this.resource);
    }

    @Override
    public T returnObject(Object obj) {
        SharedResourceHolder.release(this.resource, obj);
        return null;
    }
}
