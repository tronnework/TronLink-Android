package io.grpc;

import io.grpc.ServerCall;
public abstract class ForwardingServerCallListener<ReqT> extends PartialForwardingServerCallListener<ReqT> {
    @Override
    protected abstract ServerCall.Listener<ReqT> delegate();

    @Override
    public void onCancel() {
        super.onCancel();
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }

    @Override
    public void onHalfClose() {
        super.onHalfClose();
    }

    @Override
    public void onReady() {
        super.onReady();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onMessage(ReqT reqt) {
        delegate().onMessage(reqt);
    }

    public static abstract class SimpleForwardingServerCallListener<ReqT> extends ForwardingServerCallListener<ReqT> {
        private final ServerCall.Listener<ReqT> delegate;

        @Override
        protected ServerCall.Listener<ReqT> delegate() {
            return this.delegate;
        }

        @Override
        public void onCancel() {
            super.onCancel();
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onHalfClose() {
            super.onHalfClose();
        }

        @Override
        public void onReady() {
            super.onReady();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public SimpleForwardingServerCallListener(ServerCall.Listener<ReqT> listener) {
            this.delegate = listener;
        }
    }
}
