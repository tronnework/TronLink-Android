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
public final class DatabaseGrpc {
    private static final int METHODID_GET_BLOCK_BY_NUM = 3;
    private static final int METHODID_GET_BLOCK_REFERENCE = 0;
    private static final int METHODID_GET_DYNAMIC_PROPERTIES = 1;
    private static final int METHODID_GET_NOW_BLOCK = 2;
    public static final String SERVICE_NAME = "protocol.Database";
    private static volatile MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> getGetBlockByNumMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockReference> getGetBlockReferenceMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.DynamicProperties> getGetDynamicPropertiesMethod;
    private static volatile MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> getGetNowBlockMethod;
    private static volatile ServiceDescriptor serviceDescriptor;
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockReference> METHOD_GET_BLOCK_REFERENCE = getGetBlockReferenceMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.DynamicProperties> METHOD_GET_DYNAMIC_PROPERTIES = getGetDynamicPropertiesMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> METHOD_GET_NOW_BLOCK = getGetNowBlockMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> METHOD_GET_BLOCK_BY_NUM = getGetBlockByNumMethod();

    private DatabaseGrpc() {
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockReference> getGetBlockReferenceMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, GrpcAPI.BlockReference> methodDescriptor = getGetBlockReferenceMethod;
        if (methodDescriptor == null) {
            synchronized (DatabaseGrpc.class) {
                methodDescriptor = getGetBlockReferenceMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "getBlockReference")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.BlockReference.getDefaultInstance())).setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("getBlockReference")).build();
                    getGetBlockReferenceMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.DynamicProperties> getGetDynamicPropertiesMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.DynamicProperties> methodDescriptor = getGetDynamicPropertiesMethod;
        if (methodDescriptor == null) {
            synchronized (DatabaseGrpc.class) {
                methodDescriptor = getGetDynamicPropertiesMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDynamicProperties")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.DynamicProperties.getDefaultInstance())).setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("GetDynamicProperties")).build();
                    getGetDynamicPropertiesMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> getGetNowBlockMethod() {
        MethodDescriptor<GrpcAPI.EmptyMessage, Protocol.Block> methodDescriptor = getGetNowBlockMethod;
        if (methodDescriptor == null) {
            synchronized (DatabaseGrpc.class) {
                methodDescriptor = getGetNowBlockMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetNowBlock")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.EmptyMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("GetNowBlock")).build();
                    getGetNowBlockMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> getGetBlockByNumMethod() {
        MethodDescriptor<GrpcAPI.NumberMessage, Protocol.Block> methodDescriptor = getGetBlockByNumMethod;
        if (methodDescriptor == null) {
            synchronized (DatabaseGrpc.class) {
                methodDescriptor = getGetBlockByNumMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetBlockByNum")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.NumberMessage.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Protocol.Block.getDefaultInstance())).setSchemaDescriptor(new DatabaseMethodDescriptorSupplier("GetBlockByNum")).build();
                    getGetBlockByNumMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static DatabaseStub newStub(Channel channel) {
        return new DatabaseStub(channel);
    }

    public static DatabaseBlockingStub newBlockingStub(Channel channel) {
        return new DatabaseBlockingStub(channel);
    }

    public static DatabaseFutureStub newFutureStub(Channel channel) {
        return new DatabaseFutureStub(channel);
    }

    public static abstract class DatabaseImplBase implements BindableService {
        public void getBlockReference(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BlockReference> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(DatabaseGrpc.getGetBlockReferenceMethod(), streamObserver);
        }

        public void getDynamicProperties(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.DynamicProperties> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(DatabaseGrpc.getGetDynamicPropertiesMethod(), streamObserver);
        }

        public void getNowBlock(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(DatabaseGrpc.getGetNowBlockMethod(), streamObserver);
        }

        public void getBlockByNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<Protocol.Block> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(DatabaseGrpc.getGetBlockByNumMethod(), streamObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(DatabaseGrpc.getServiceDescriptor()).addMethod(DatabaseGrpc.getGetBlockReferenceMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(DatabaseGrpc.getGetDynamicPropertiesMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(DatabaseGrpc.getGetNowBlockMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(DatabaseGrpc.getGetBlockByNumMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).build();
        }
    }

    public static final class DatabaseStub extends AbstractStub<DatabaseStub> {
        private DatabaseStub(Channel channel) {
            super(channel);
        }

        private DatabaseStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public DatabaseStub build(Channel channel, CallOptions callOptions) {
            return new DatabaseStub(channel, callOptions);
        }

        public void getBlockReference(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<GrpcAPI.BlockReference> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(DatabaseGrpc.getGetBlockReferenceMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getDynamicProperties(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.DynamicProperties> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(DatabaseGrpc.getGetDynamicPropertiesMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getNowBlock(GrpcAPI.EmptyMessage emptyMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(DatabaseGrpc.getGetNowBlockMethod(), getCallOptions()), emptyMessage, streamObserver);
        }

        public void getBlockByNum(GrpcAPI.NumberMessage numberMessage, StreamObserver<Protocol.Block> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(DatabaseGrpc.getGetBlockByNumMethod(), getCallOptions()), numberMessage, streamObserver);
        }
    }

    public static final class DatabaseBlockingStub extends AbstractStub<DatabaseBlockingStub> {
        private DatabaseBlockingStub(Channel channel) {
            super(channel);
        }

        private DatabaseBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public DatabaseBlockingStub build(Channel channel, CallOptions callOptions) {
            return new DatabaseBlockingStub(channel, callOptions);
        }

        public GrpcAPI.BlockReference getBlockReference(GrpcAPI.EmptyMessage emptyMessage) {
            return (GrpcAPI.BlockReference) ClientCalls.blockingUnaryCall(getChannel(), DatabaseGrpc.getGetBlockReferenceMethod(), getCallOptions(), emptyMessage);
        }

        public Protocol.DynamicProperties getDynamicProperties(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.DynamicProperties) ClientCalls.blockingUnaryCall(getChannel(), DatabaseGrpc.getGetDynamicPropertiesMethod(), getCallOptions(), emptyMessage);
        }

        public Protocol.Block getNowBlock(GrpcAPI.EmptyMessage emptyMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), DatabaseGrpc.getGetNowBlockMethod(), getCallOptions(), emptyMessage);
        }

        public Protocol.Block getBlockByNum(GrpcAPI.NumberMessage numberMessage) {
            return (Protocol.Block) ClientCalls.blockingUnaryCall(getChannel(), DatabaseGrpc.getGetBlockByNumMethod(), getCallOptions(), numberMessage);
        }
    }

    public static final class DatabaseFutureStub extends AbstractStub<DatabaseFutureStub> {
        private DatabaseFutureStub(Channel channel) {
            super(channel);
        }

        private DatabaseFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public DatabaseFutureStub build(Channel channel, CallOptions callOptions) {
            return new DatabaseFutureStub(channel, callOptions);
        }

        public ListenableFuture<GrpcAPI.BlockReference> getBlockReference(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(DatabaseGrpc.getGetBlockReferenceMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.DynamicProperties> getDynamicProperties(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(DatabaseGrpc.getGetDynamicPropertiesMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.Block> getNowBlock(GrpcAPI.EmptyMessage emptyMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(DatabaseGrpc.getGetNowBlockMethod(), getCallOptions()), emptyMessage);
        }

        public ListenableFuture<Protocol.Block> getBlockByNum(GrpcAPI.NumberMessage numberMessage) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(DatabaseGrpc.getGetBlockByNumMethod(), getCallOptions()), numberMessage);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final DatabaseImplBase serviceImpl;

        MethodHandlers(DatabaseImplBase databaseImplBase, int i) {
            this.serviceImpl = databaseImplBase;
            this.methodId = i;
        }

        @Override
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.getBlockReference((GrpcAPI.EmptyMessage) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.getDynamicProperties((GrpcAPI.EmptyMessage) req, streamObserver);
            } else if (i == 2) {
                this.serviceImpl.getNowBlock((GrpcAPI.EmptyMessage) req, streamObserver);
            } else if (i == 3) {
                this.serviceImpl.getBlockByNum((GrpcAPI.NumberMessage) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        @Override
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class DatabaseBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        DatabaseBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return GrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Database");
        }
    }

    public static final class DatabaseFileDescriptorSupplier extends DatabaseBaseDescriptorSupplier {
        DatabaseFileDescriptorSupplier() {
        }
    }

    public static final class DatabaseMethodDescriptorSupplier extends DatabaseBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        DatabaseMethodDescriptorSupplier(String str) {
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
            synchronized (DatabaseGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new DatabaseFileDescriptorSupplier()).addMethod(getGetBlockReferenceMethod()).addMethod(getGetDynamicPropertiesMethod()).addMethod(getGetNowBlockMethod()).addMethod(getGetBlockByNumMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
