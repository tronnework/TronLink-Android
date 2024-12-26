package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
@DoNotMock
public abstract class ServerCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract boolean isCancelled();

    public abstract void setCompression(String str);

    public abstract void setOnCancelHandler(Runnable runnable);
}
