package io.grpc;
public abstract class ForwardingServerCall<ReqT, RespT> extends PartialForwardingServerCall<ReqT, RespT> {
    @Override
    protected abstract ServerCall<ReqT, RespT> delegate();

    @Override
    public void close(Status status, Metadata metadata) {
        super.close(status, metadata);
    }

    @Override
    public Attributes getAttributes() {
        return super.getAttributes();
    }

    @Override
    public String getAuthority() {
        return super.getAuthority();
    }

    @Override
    public boolean isCancelled() {
        return super.isCancelled();
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
    public void sendHeaders(Metadata metadata) {
        super.sendHeaders(metadata);
    }

    @Override
    public void setCompression(String str) {
        super.setCompression(str);
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
    public void sendMessage(RespT respt) {
        delegate().sendMessage(respt);
    }

    public static abstract class SimpleForwardingServerCall<ReqT, RespT> extends ForwardingServerCall<ReqT, RespT> {
        private final ServerCall<ReqT, RespT> delegate;

        @Override
        protected ServerCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        @Override
        public void close(Status status, Metadata metadata) {
            super.close(status, metadata);
        }

        @Override
        public Attributes getAttributes() {
            return super.getAttributes();
        }

        @Override
        public String getAuthority() {
            return super.getAuthority();
        }

        @Override
        public boolean isCancelled() {
            return super.isCancelled();
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
        public void sendHeaders(Metadata metadata) {
            super.sendHeaders(metadata);
        }

        @Override
        public void setCompression(String str) {
            super.setCompression(str);
        }

        @Override
        public void setMessageCompression(boolean z) {
            super.setMessageCompression(z);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public SimpleForwardingServerCall(ServerCall<ReqT, RespT> serverCall) {
            this.delegate = serverCall;
        }

        @Override
        public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
            return this.delegate.getMethodDescriptor();
        }
    }
}
