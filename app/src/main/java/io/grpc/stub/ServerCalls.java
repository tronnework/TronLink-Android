package io.grpc.stub;

import com.google.common.base.Preconditions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.Status;
public final class ServerCalls {
    static final String MISSING_REQUEST = "Half-closed without a request";
    static final String TOO_MANY_REQUESTS = "Too many requests";

    public interface BidiStreamingMethod<ReqT, RespT> extends StreamingRequestMethod<ReqT, RespT> {
    }

    public interface ClientStreamingMethod<ReqT, RespT> extends StreamingRequestMethod<ReqT, RespT> {
    }

    public interface ServerStreamingMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
    }

    public interface StreamingRequestMethod<ReqT, RespT> {
        StreamObserver<ReqT> invoke(StreamObserver<RespT> streamObserver);
    }

    public interface UnaryMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
    }

    public interface UnaryRequestMethod<ReqT, RespT> {
        void invoke(ReqT reqt, StreamObserver<RespT> streamObserver);
    }

    private ServerCalls() {
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryCall(UnaryMethod<ReqT, RespT> unaryMethod) {
        return asyncUnaryRequestCall(unaryMethod);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncServerStreamingCall(ServerStreamingMethod<ReqT, RespT> serverStreamingMethod) {
        return asyncUnaryRequestCall(serverStreamingMethod);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncClientStreamingCall(ClientStreamingMethod<ReqT, RespT> clientStreamingMethod) {
        return asyncStreamingRequestCall(clientStreamingMethod);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncBidiStreamingCall(BidiStreamingMethod<ReqT, RespT> bidiStreamingMethod) {
        return asyncStreamingRequestCall(bidiStreamingMethod);
    }

    public static final class UnaryServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        private final UnaryRequestMethod<ReqT, RespT> method;

        UnaryServerCallHandler(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod) {
            this.method = unaryRequestMethod;
        }

        @Override
        public ServerCall.Listener<ReqT> startCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            Preconditions.checkArgument(serverCall.getMethodDescriptor().getType().clientSendsOneMessage(), "asyncUnaryRequestCall is only for clientSendsOneMessage methods");
            ServerCallStreamObserverImpl serverCallStreamObserverImpl = new ServerCallStreamObserverImpl(serverCall);
            serverCall.request(2);
            return new UnaryServerCallListener(serverCallStreamObserverImpl, serverCall);
        }

        private final class UnaryServerCallListener extends ServerCall.Listener<ReqT> {
            private final ServerCall<ReqT, RespT> call;
            private boolean canInvoke = true;
            private ReqT request;
            private final ServerCallStreamObserverImpl<ReqT, RespT> responseObserver;

            UnaryServerCallListener(ServerCallStreamObserverImpl<ReqT, RespT> serverCallStreamObserverImpl, ServerCall<ReqT, RespT> serverCall) {
                this.call = serverCall;
                this.responseObserver = serverCallStreamObserverImpl;
            }

            @Override
            public void onMessage(ReqT reqt) {
                if (this.request == null) {
                    this.request = reqt;
                    return;
                }
                this.call.close(Status.INTERNAL.withDescription(ServerCalls.TOO_MANY_REQUESTS), new Metadata());
                this.canInvoke = false;
            }

            @Override
            public void onHalfClose() {
                if (this.canInvoke) {
                    if (this.request != null) {
                        UnaryServerCallHandler.this.method.invoke(this.request, this.responseObserver);
                        this.responseObserver.freeze();
                        if (this.call.isReady()) {
                            onReady();
                            return;
                        }
                        return;
                    }
                    this.call.close(Status.INTERNAL.withDescription(ServerCalls.MISSING_REQUEST), new Metadata());
                }
            }

            @Override
            public void onCancel() {
                this.responseObserver.cancelled = true;
                if (((ServerCallStreamObserverImpl) this.responseObserver).onCancelHandler != null) {
                    ((ServerCallStreamObserverImpl) this.responseObserver).onCancelHandler.run();
                }
            }

            @Override
            public void onReady() {
                if (((ServerCallStreamObserverImpl) this.responseObserver).onReadyHandler != null) {
                    ((ServerCallStreamObserverImpl) this.responseObserver).onReadyHandler.run();
                }
            }
        }
    }

    private static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryRequestCall(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod) {
        return new UnaryServerCallHandler(unaryRequestMethod);
    }

    public static final class StreamingServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        private final StreamingRequestMethod<ReqT, RespT> method;

        StreamingServerCallHandler(StreamingRequestMethod<ReqT, RespT> streamingRequestMethod) {
            this.method = streamingRequestMethod;
        }

        @Override
        public ServerCall.Listener<ReqT> startCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            ServerCallStreamObserverImpl serverCallStreamObserverImpl = new ServerCallStreamObserverImpl(serverCall);
            StreamObserver<ReqT> invoke = this.method.invoke(serverCallStreamObserverImpl);
            serverCallStreamObserverImpl.freeze();
            if (serverCallStreamObserverImpl.autoFlowControlEnabled) {
                serverCall.request(1);
            }
            return new StreamingServerCallListener(invoke, serverCallStreamObserverImpl, serverCall);
        }

        private final class StreamingServerCallListener extends ServerCall.Listener<ReqT> {
            private final ServerCall<ReqT, RespT> call;
            private boolean halfClosed = false;
            private final StreamObserver<ReqT> requestObserver;
            private final ServerCallStreamObserverImpl<ReqT, RespT> responseObserver;

            StreamingServerCallListener(StreamObserver<ReqT> streamObserver, ServerCallStreamObserverImpl<ReqT, RespT> serverCallStreamObserverImpl, ServerCall<ReqT, RespT> serverCall) {
                this.requestObserver = streamObserver;
                this.responseObserver = serverCallStreamObserverImpl;
                this.call = serverCall;
            }

            @Override
            public void onMessage(ReqT reqt) {
                this.requestObserver.onNext(reqt);
                if (((ServerCallStreamObserverImpl) this.responseObserver).autoFlowControlEnabled) {
                    this.call.request(1);
                }
            }

            @Override
            public void onHalfClose() {
                this.halfClosed = true;
                this.requestObserver.onCompleted();
            }

            @Override
            public void onCancel() {
                this.responseObserver.cancelled = true;
                if (((ServerCallStreamObserverImpl) this.responseObserver).onCancelHandler != null) {
                    ((ServerCallStreamObserverImpl) this.responseObserver).onCancelHandler.run();
                }
                if (this.halfClosed) {
                    return;
                }
                this.requestObserver.onError(Status.CANCELLED.withDescription("cancelled before receiving half close").asRuntimeException());
            }

            @Override
            public void onReady() {
                if (((ServerCallStreamObserverImpl) this.responseObserver).onReadyHandler != null) {
                    ((ServerCallStreamObserverImpl) this.responseObserver).onReadyHandler.run();
                }
            }
        }
    }

    private static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncStreamingRequestCall(StreamingRequestMethod<ReqT, RespT> streamingRequestMethod) {
        return new StreamingServerCallHandler(streamingRequestMethod);
    }

    public static final class ServerCallStreamObserverImpl<ReqT, RespT> extends ServerCallStreamObserver<RespT> {
        private boolean autoFlowControlEnabled = true;
        final ServerCall<ReqT, RespT> call;
        volatile boolean cancelled;
        private boolean frozen;
        private Runnable onCancelHandler;
        private Runnable onReadyHandler;
        private boolean sentHeaders;

        public void freeze() {
            this.frozen = true;
        }

        ServerCallStreamObserverImpl(ServerCall<ReqT, RespT> serverCall) {
            this.call = serverCall;
        }

        @Override
        public void setMessageCompression(boolean z) {
            this.call.setMessageCompression(z);
        }

        @Override
        public void setCompression(String str) {
            this.call.setCompression(str);
        }

        @Override
        public void onNext(RespT respt) {
            if (this.cancelled) {
                throw Status.CANCELLED.withDescription("call already cancelled").asRuntimeException();
            }
            if (!this.sentHeaders) {
                this.call.sendHeaders(new Metadata());
                this.sentHeaders = true;
            }
            this.call.sendMessage(respt);
        }

        @Override
        public void onError(Throwable th) {
            Metadata trailersFromThrowable = Status.trailersFromThrowable(th);
            if (trailersFromThrowable == null) {
                trailersFromThrowable = new Metadata();
            }
            this.call.close(Status.fromThrowable(th), trailersFromThrowable);
        }

        @Override
        public void onCompleted() {
            if (this.cancelled) {
                throw Status.CANCELLED.withDescription("call already cancelled").asRuntimeException();
            }
            this.call.close(Status.OK, new Metadata());
        }

        @Override
        public boolean isReady() {
            return this.call.isReady();
        }

        @Override
        public void setOnReadyHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onReadyHandler after initialization");
            this.onReadyHandler = runnable;
        }

        @Override
        public boolean isCancelled() {
            return this.call.isCancelled();
        }

        @Override
        public void setOnCancelHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onCancelHandler after initialization");
            this.onCancelHandler = runnable;
        }

        @Override
        public void disableAutoInboundFlowControl() {
            Preconditions.checkState(!this.frozen, "Cannot disable auto flow control after initialization");
            this.autoFlowControlEnabled = false;
        }

        @Override
        public void request(int i) {
            this.call.request(i);
        }
    }

    public static void asyncUnimplementedUnaryCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        Preconditions.checkNotNull(methodDescriptor, "methodDescriptor");
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        streamObserver.onError(Status.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented", methodDescriptor.getFullMethodName())).asRuntimeException());
    }

    public static <T> StreamObserver<T> asyncUnimplementedStreamingCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        asyncUnimplementedUnaryCall(methodDescriptor, streamObserver);
        return new NoopStreamObserver();
    }

    static class NoopStreamObserver<V> implements StreamObserver<V> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable th) {
        }

        @Override
        public void onNext(V v) {
        }

        NoopStreamObserver() {
        }
    }
}
