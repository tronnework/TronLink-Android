package io.grpc.stub;

import com.google.errorprone.annotations.DoNotMock;
import javax.annotation.Nullable;
@DoNotMock
public abstract class ClientCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);
}
