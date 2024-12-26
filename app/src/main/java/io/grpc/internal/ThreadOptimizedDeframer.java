package io.grpc.internal;
public interface ThreadOptimizedDeframer extends Deframer {
    @Override
    void request(int i);
}
