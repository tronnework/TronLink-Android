package org.tron.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import org.tron.api.ZksnarkGrpcAPI;
public final class TronZksnarkGrpc {
    private static final int METHODID_CHECK_ZKSNARK_PROOF = 0;
    @Deprecated
    public static final MethodDescriptor<ZksnarkGrpcAPI.ZksnarkRequest, ZksnarkGrpcAPI.ZksnarkResponse> METHOD_CHECK_ZKSNARK_PROOF = getCheckZksnarkProofMethod();
    public static final String SERVICE_NAME = "protocol.TronZksnark";
    private static volatile MethodDescriptor<ZksnarkGrpcAPI.ZksnarkRequest, ZksnarkGrpcAPI.ZksnarkResponse> getCheckZksnarkProofMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private TronZksnarkGrpc() {
    }

    public static MethodDescriptor<ZksnarkGrpcAPI.ZksnarkRequest, ZksnarkGrpcAPI.ZksnarkResponse> getCheckZksnarkProofMethod() {
        MethodDescriptor<ZksnarkGrpcAPI.ZksnarkRequest, ZksnarkGrpcAPI.ZksnarkResponse> methodDescriptor = getCheckZksnarkProofMethod;
        if (methodDescriptor == null) {
            synchronized (TronZksnarkGrpc.class) {
                methodDescriptor = getCheckZksnarkProofMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CheckZksnarkProof")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ZksnarkGrpcAPI.ZksnarkRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ZksnarkGrpcAPI.ZksnarkResponse.getDefaultInstance())).setSchemaDescriptor(new TronZksnarkMethodDescriptorSupplier("CheckZksnarkProof")).build();
                    getCheckZksnarkProofMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static TronZksnarkStub newStub(Channel channel) {
        return new TronZksnarkStub(channel);
    }

    public static TronZksnarkBlockingStub newBlockingStub(Channel channel) {
        return new TronZksnarkBlockingStub(channel);
    }

    public static TronZksnarkFutureStub newFutureStub(Channel channel) {
        return new TronZksnarkFutureStub(channel);
    }

    public static abstract class TronZksnarkImplBase implements BindableService {
        public void checkZksnarkProof(ZksnarkGrpcAPI.ZksnarkRequest zksnarkRequest, StreamObserver<ZksnarkGrpcAPI.ZksnarkResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(TronZksnarkGrpc.getCheckZksnarkProofMethod(), streamObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(TronZksnarkGrpc.getServiceDescriptor()).addMethod(TronZksnarkGrpc.getCheckZksnarkProofMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class TronZksnarkStub extends AbstractStub<TronZksnarkStub> {
        private TronZksnarkStub(Channel channel) {
            super(channel);
        }

        private TronZksnarkStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public TronZksnarkStub build(Channel channel, CallOptions callOptions) {
            return new TronZksnarkStub(channel, callOptions);
        }

        public void checkZksnarkProof(ZksnarkGrpcAPI.ZksnarkRequest zksnarkRequest, StreamObserver<ZksnarkGrpcAPI.ZksnarkResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(TronZksnarkGrpc.getCheckZksnarkProofMethod(), getCallOptions()), zksnarkRequest, streamObserver);
        }
    }

    public static final class TronZksnarkBlockingStub extends AbstractStub<TronZksnarkBlockingStub> {
        private TronZksnarkBlockingStub(Channel channel) {
            super(channel);
        }

        private TronZksnarkBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public TronZksnarkBlockingStub build(Channel channel, CallOptions callOptions) {
            return new TronZksnarkBlockingStub(channel, callOptions);
        }

        public ZksnarkGrpcAPI.ZksnarkResponse checkZksnarkProof(ZksnarkGrpcAPI.ZksnarkRequest zksnarkRequest) {
            return (ZksnarkGrpcAPI.ZksnarkResponse) ClientCalls.blockingUnaryCall(getChannel(), TronZksnarkGrpc.getCheckZksnarkProofMethod(), getCallOptions(), zksnarkRequest);
        }
    }

    public static final class TronZksnarkFutureStub extends AbstractStub<TronZksnarkFutureStub> {
        private TronZksnarkFutureStub(Channel channel) {
            super(channel);
        }

        private TronZksnarkFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public TronZksnarkFutureStub build(Channel channel, CallOptions callOptions) {
            return new TronZksnarkFutureStub(channel, callOptions);
        }

        public ListenableFuture<ZksnarkGrpcAPI.ZksnarkResponse> checkZksnarkProof(ZksnarkGrpcAPI.ZksnarkRequest zksnarkRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(TronZksnarkGrpc.getCheckZksnarkProofMethod(), getCallOptions()), zksnarkRequest);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final TronZksnarkImplBase serviceImpl;

        MethodHandlers(TronZksnarkImplBase tronZksnarkImplBase, int i) {
            this.serviceImpl = tronZksnarkImplBase;
            this.methodId = i;
        }

        @Override
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.checkZksnarkProof((ZksnarkGrpcAPI.ZksnarkRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class TronZksnarkBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        TronZksnarkBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return ZksnarkGrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("TronZksnark");
        }
    }

    public static final class TronZksnarkFileDescriptorSupplier extends TronZksnarkBaseDescriptorSupplier {
        TronZksnarkFileDescriptorSupplier() {
        }
    }

    public static final class TronZksnarkMethodDescriptorSupplier extends TronZksnarkBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        TronZksnarkMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (TronZksnarkGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new TronZksnarkFileDescriptorSupplier()).addMethod(getCheckZksnarkProofMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
