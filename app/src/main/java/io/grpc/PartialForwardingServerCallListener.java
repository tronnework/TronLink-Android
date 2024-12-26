package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ServerCall;
abstract class PartialForwardingServerCallListener<ReqT> extends ServerCall.Listener<ReqT> {
    protected abstract ServerCall.Listener<?> delegate();

    @Override
    public void onHalfClose() {
        delegate().onHalfClose();
    }

    @Override
    public void onCancel() {
        delegate().onCancel();
    }

    @Override
    public void onComplete() {
        delegate().onComplete();
    }

    @Override
    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
