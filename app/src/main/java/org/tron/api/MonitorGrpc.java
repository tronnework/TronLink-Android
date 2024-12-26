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
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public final class MonitorGrpc {
    private static final int METHODID_GET_STATS_INFO = 0;
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MetricsInfo> METHOD_GET_STATS_INFO = getGetStatsInfoMethod();
    public static final String SERVICE_NAME = "protocol.Monitor";
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MetricsInfo> getGetStatsInfoMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private MonitorGrpc() {
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MetricsInfo> getGetStatsInfoMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.MetricsInfo> methodDescriptor = getGetStatsInfoMethod;
        if (methodDescriptor == null) {
            synchronized (MonitorGrpc.class) {
                methodDescriptor = getGetStatsInfoMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetStatsInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.MetricsInfo.getDefaultInstance())).setSchemaDescriptor(new MonitorMethodDescriptorSupplier("GetStatsInfo")).build();
                    getGetStatsInfoMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MonitorStub newStub(Channel channel) {
        return new MonitorStub(channel);
    }

    public static MonitorBlockingStub newBlockingStub(Channel channel) {
        return new MonitorBlockingStub(channel);
    }

    public static MonitorFutureStub newFutureStub(Channel channel) {
        return new MonitorFutureStub(channel);
    }

    public static abstract class MonitorImplBase implements BindableService {
        public void getStatsInfo(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.MetricsInfo> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(MonitorGrpc.getGetStatsInfoMethod(), streamObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(MonitorGrpc.getServiceDescriptor()).addMethod(MonitorGrpc.getGetStatsInfoMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class MonitorStub extends AbstractStub<MonitorStub> {
        private MonitorStub(Channel channel) {
            super(channel);
        }

        private MonitorStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public MonitorStub build(Channel channel, CallOptions callOptions) {
            return new MonitorStub(channel, callOptions);
        }

        public void getStatsInfo(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.MetricsInfo> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(MonitorGrpc.getGetStatsInfoMethod(), getCallOptions()), emptyMessage, streamObserver);
        }
    }

    public static final class MonitorBlockingStub extends AbstractStub<MonitorBlockingStub> {
        private MonitorBlockingStub(Channel channel) {
            super(channel);
        }

        private MonitorBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public MonitorBlockingStub build(Channel channel, CallOptions callOptions) {
            return new MonitorBlockingStub(channel, callOptions);
        }

        public Protocol.MetricsInfo getStatsInfo(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.MetricsInfo) ClientCalls.blockingUnaryCall(getChannel(), MonitorGrpc.getGetStatsInfoMethod(), getCallOptions(), emptyMessage);
        }
    }

    public static final class MonitorFutureStub extends AbstractStub<MonitorFutureStub> {
        private MonitorFutureStub(Channel channel) {
            super(channel);
        }

        private MonitorFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public MonitorFutureStub build(Channel channel, CallOptions callOptions) {
            return new MonitorFutureStub(channel, callOptions);
        }

        public ListenableFuture<Protocol.MetricsInfo> getStatsInfo(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(MonitorGrpc.getGetStatsInfoMethod(), getCallOptions()), emptyMessage);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final MonitorImplBase serviceImpl;

        MethodHandlers(MonitorImplBase monitorImplBase, int i) {
            this.serviceImpl = monitorImplBase;
            this.methodId = i;
        }

        @Override
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.getStatsInfo((GrpcAPI.EmptyMessage) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class MonitorBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        MonitorBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return GrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Monitor");
        }
    }

    public static final class MonitorFileDescriptorSupplier extends MonitorBaseDescriptorSupplier {
        MonitorFileDescriptorSupplier() {
        }
    }

    public static final class MonitorMethodDescriptorSupplier extends MonitorBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        MonitorMethodDescriptorSupplier(String str) {
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
            synchronized (MonitorGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new MonitorFileDescriptorSupplier()).addMethod(getGetStatsInfoMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
