package io.grpc;

import com.google.common.base.MoreObjects;
public abstract class PartialForwardingServerCall<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    protected abstract ServerCall<?, ?> delegate();

    @Override
    public void request(int i) {
        delegate().request(i);
    }

    @Override
    public void sendHeaders(Metadata metadata) {
        delegate().sendHeaders(metadata);
    }

    @Override
    public boolean isReady() {
        return delegate().isReady();
    }

    @Override
    public void close(Status status, Metadata metadata) {
        delegate().close(status, metadata);
    }

    @Override
    public boolean isCancelled() {
        return delegate().isCancelled();
    }

    @Override
    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    @Override
    public void setCompression(String str) {
        delegate().setCompression(str);
    }

    @Override
    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    @Override
    public String getAuthority() {
        return delegate().getAuthority();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
