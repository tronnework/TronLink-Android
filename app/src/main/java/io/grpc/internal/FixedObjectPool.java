package io.grpc.internal;

import com.google.common.base.Preconditions;
public final class FixedObjectPool<T> implements ObjectPool<T> {
    private final T object;

    @Override
    public T getObject() {
        return this.object;
    }

    @Override
    public T returnObject(Object obj) {
        return null;
    }

    public FixedObjectPool(T t) {
        this.object = (T) Preconditions.checkNotNull(t, "object");
    }
}
