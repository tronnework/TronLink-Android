package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ClientCall;
abstract class PartialForwardingClientCallListener<RespT> extends ClientCall.Listener<RespT> {
    protected abstract ClientCall.Listener<?> delegate();

    @Override
    public void onHeaders(Metadata metadata) {
        delegate().onHeaders(metadata);
    }

    @Override
    public void onClose(Status status, Metadata metadata) {
        delegate().onClose(status, metadata);
    }

    @Override
    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
