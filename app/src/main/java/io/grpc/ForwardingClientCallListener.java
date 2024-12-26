package io.grpc;

import io.grpc.ClientCall;
public abstract class ForwardingClientCallListener<RespT> extends PartialForwardingClientCallListener<RespT> {
    @Override
    protected abstract ClientCall.Listener<RespT> delegate();

    @Override
    public void onClose(Status status, Metadata metadata) {
        super.onClose(status, metadata);
    }

    @Override
    public void onHeaders(Metadata metadata) {
        super.onHeaders(metadata);
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
    public void onMessage(RespT respt) {
        delegate().onMessage(respt);
    }

    public static abstract class SimpleForwardingClientCallListener<RespT> extends ForwardingClientCallListener<RespT> {
        private final ClientCall.Listener<RespT> delegate;

        @Override
        protected ClientCall.Listener<RespT> delegate() {
            return this.delegate;
        }

        @Override
        public void onClose(Status status, Metadata metadata) {
            super.onClose(status, metadata);
        }

        @Override
        public void onHeaders(Metadata metadata) {
            super.onHeaders(metadata);
        }

        @Override
        public void onReady() {
            super.onReady();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public SimpleForwardingClientCallListener(ClientCall.Listener<RespT> listener) {
            this.delegate = listener;
        }
    }
}
