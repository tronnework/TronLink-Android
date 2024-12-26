package io.grpc;

import com.google.common.base.MoreObjects;
import javax.annotation.Nullable;
abstract class PartialForwardingClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    protected abstract ClientCall<?, ?> delegate();

    @Override
    public void request(int i) {
        delegate().request(i);
    }

    @Override
    public void cancel(@Nullable String str, @Nullable Throwable th) {
        delegate().cancel(str, th);
    }

    @Override
    public void halfClose() {
        delegate().halfClose();
    }

    @Override
    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    @Override
    public boolean isReady() {
        return delegate().isReady();
    }

    @Override
    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
