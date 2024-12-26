package io.grpc;

import io.grpc.ClientCall;
import javax.annotation.Nullable;
public abstract class ForwardingClientCall<ReqT, RespT> extends PartialForwardingClientCall<ReqT, RespT> {
    @Override
    protected abstract ClientCall<ReqT, RespT> delegate();

    @Override
    public void cancel(@Nullable String str, @Nullable Throwable th) {
        super.cancel(str, th);
    }

    @Override
    public Attributes getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void halfClose() {
        super.halfClose();
    }

    @Override
    public boolean isReady() {
        return super.isReady();
    }

    @Override
    public void request(int i) {
        super.request(i);
    }

    @Override
    public void setMessageCompression(boolean z) {
        super.setMessageCompression(z);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
        delegate().start(listener, metadata);
    }

    @Override
    public void sendMessage(ReqT reqt) {
        delegate().sendMessage(reqt);
    }

    public static abstract class SimpleForwardingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final ClientCall<ReqT, RespT> delegate;

        @Override
        protected ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        @Override
        public void cancel(@Nullable String str, @Nullable Throwable th) {
            super.cancel(str, th);
        }

        @Override
        public Attributes getAttributes() {
            return super.getAttributes();
        }

        @Override
        public void halfClose() {
            super.halfClose();
        }

        @Override
        public boolean isReady() {
            return super.isReady();
        }

        @Override
        public void request(int i) {
            super.request(i);
        }

        @Override
        public void setMessageCompression(boolean z) {
            super.setMessageCompression(z);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public SimpleForwardingClientCall(ClientCall<ReqT, RespT> clientCall) {
            this.delegate = clientCall;
        }
    }
}
