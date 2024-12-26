package io.grpc;

import javax.annotation.Nullable;
public abstract class ServerStreamTracer extends StreamTracer {

    public static abstract class Factory {
        public abstract ServerStreamTracer newServerStreamTracer(String str, Metadata metadata);
    }

    public static abstract class ServerCallInfo<ReqT, RespT> {
        public abstract Attributes getAttributes();

        @Nullable
        public abstract String getAuthority();

        public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();
    }

    public Context filterContext(Context context) {
        return context;
    }

    @Deprecated
    public void serverCallStarted(ServerCall<?, ?> serverCall) {
    }

    public void serverCallStarted(ServerCallInfo<?, ?> serverCallInfo) {
        serverCallStarted(ReadOnlyServerCall.create(serverCallInfo));
    }

    @Deprecated
    private static final class ReadOnlyServerCall<ReqT, RespT> extends ForwardingServerCall<ReqT, RespT> {
        private final ServerCallInfo<ReqT, RespT> callInfo;

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        public static <ReqT, RespT> ReadOnlyServerCall<ReqT, RespT> create(ServerCallInfo<ReqT, RespT> serverCallInfo) {
            return new ReadOnlyServerCall<>(serverCallInfo);
        }

        private ReadOnlyServerCall(ServerCallInfo<ReqT, RespT> serverCallInfo) {
            this.callInfo = serverCallInfo;
        }

        @Override
        public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
            return this.callInfo.getMethodDescriptor();
        }

        @Override
        public Attributes getAttributes() {
            return this.callInfo.getAttributes();
        }

        @Override
        public String getAuthority() {
            return this.callInfo.getAuthority();
        }

        @Override
        protected ServerCall<ReqT, RespT> delegate() {
            throw new UnsupportedOperationException();
        }
    }
}
