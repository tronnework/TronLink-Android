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
public final class WalletExtensionGrpc {
    private static final int METHODID_GET_TRANSACTIONS_FROM_THIS = 0;
    private static final int METHODID_GET_TRANSACTIONS_FROM_THIS2 = 1;
    private static final int METHODID_GET_TRANSACTIONS_TO_THIS = 2;
    private static final int METHODID_GET_TRANSACTIONS_TO_THIS2 = 3;
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> METHOD_GET_TRANSACTIONS_FROM_THIS = getGetTransactionsFromThisMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> METHOD_GET_TRANSACTIONS_FROM_THIS2 = getGetTransactionsFromThis2Method();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> METHOD_GET_TRANSACTIONS_TO_THIS = getGetTransactionsToThisMethod();
    @Deprecated
    public static final MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> METHOD_GET_TRANSACTIONS_TO_THIS2 = getGetTransactionsToThis2Method();
    public static final String SERVICE_NAME = "protocol.WalletExtension";
    private static volatile MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> getGetTransactionsFromThis2Method;
    private static volatile MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> getGetTransactionsFromThisMethod;
    private static volatile MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> getGetTransactionsToThis2Method;
    private static volatile MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> getGetTransactionsToThisMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private WalletExtensionGrpc() {
    }

    public static MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> getGetTransactionsFromThisMethod() {
        MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> methodDescriptor = getGetTransactionsFromThisMethod;
        if (methodDescriptor == null) {
            synchronized (WalletExtensionGrpc.class) {
                methodDescriptor = getGetTransactionsFromThisMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionsFromThis")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.AccountPaginated.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionList.getDefaultInstance())).setSchemaDescriptor(new WalletExtensionMethodDescriptorSupplier("GetTransactionsFromThis")).build();
                    getGetTransactionsFromThisMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> getGetTransactionsFromThis2Method() {
        MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> methodDescriptor = getGetTransactionsFromThis2Method;
        if (methodDescriptor == null) {
            synchronized (WalletExtensionGrpc.class) {
                methodDescriptor = getGetTransactionsFromThis2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionsFromThis2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.AccountPaginated.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionListExtention.getDefaultInstance())).setSchemaDescriptor(new WalletExtensionMethodDescriptorSupplier("GetTransactionsFromThis2")).build();
                    getGetTransactionsFromThis2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> getGetTransactionsToThisMethod() {
        MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionList> methodDescriptor = getGetTransactionsToThisMethod;
        if (methodDescriptor == null) {
            synchronized (WalletExtensionGrpc.class) {
                methodDescriptor = getGetTransactionsToThisMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionsToThis")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.AccountPaginated.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionList.getDefaultInstance())).setSchemaDescriptor(new WalletExtensionMethodDescriptorSupplier("GetTransactionsToThis")).build();
                    getGetTransactionsToThisMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> getGetTransactionsToThis2Method() {
        MethodDescriptor<GrpcAPI.AccountPaginated, GrpcAPI.TransactionListExtention> methodDescriptor = getGetTransactionsToThis2Method;
        if (methodDescriptor == null) {
            synchronized (WalletExtensionGrpc.class) {
                methodDescriptor = getGetTransactionsToThis2Method;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTransactionsToThis2")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GrpcAPI.AccountPaginated.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GrpcAPI.TransactionListExtention.getDefaultInstance())).setSchemaDescriptor(new WalletExtensionMethodDescriptorSupplier("GetTransactionsToThis2")).build();
                    getGetTransactionsToThis2Method = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static WalletExtensionStub newStub(Channel channel) {
        return new WalletExtensionStub(channel);
    }

    public static WalletExtensionBlockingStub newBlockingStub(Channel channel) {
        return new WalletExtensionBlockingStub(channel);
    }

    public static WalletExtensionFutureStub newFutureStub(Channel channel) {
        return new WalletExtensionFutureStub(channel);
    }

    public static abstract class WalletExtensionImplBase implements BindableService {
        public void getTransactionsFromThis(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletExtensionGrpc.getGetTransactionsFromThisMethod(), streamObserver);
        }

        public void getTransactionsFromThis2(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionListExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletExtensionGrpc.getGetTransactionsFromThis2Method(), streamObserver);
        }

        public void getTransactionsToThis(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionList> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletExtensionGrpc.getGetTransactionsToThisMethod(), streamObserver);
        }

        public void getTransactionsToThis2(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionListExtention> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(WalletExtensionGrpc.getGetTransactionsToThis2Method(), streamObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(WalletExtensionGrpc.getServiceDescriptor()).addMethod(WalletExtensionGrpc.getGetTransactionsFromThisMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(WalletExtensionGrpc.getGetTransactionsFromThis2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(WalletExtensionGrpc.getGetTransactionsToThisMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(WalletExtensionGrpc.getGetTransactionsToThis2Method(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).build();
        }
    }

    public static final class WalletExtensionStub extends AbstractStub<WalletExtensionStub> {
        private WalletExtensionStub(Channel channel) {
            super(channel);
        }

        private WalletExtensionStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletExtensionStub build(Channel channel, CallOptions callOptions) {
            return new WalletExtensionStub(channel, callOptions);
        }

        public void getTransactionsFromThis(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsFromThisMethod(), getCallOptions()), accountPaginated, streamObserver);
        }

        public void getTransactionsFromThis2(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionListExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsFromThis2Method(), getCallOptions()), accountPaginated, streamObserver);
        }

        public void getTransactionsToThis(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionList> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsToThisMethod(), getCallOptions()), accountPaginated, streamObserver);
        }

        public void getTransactionsToThis2(GrpcAPI.AccountPaginated accountPaginated, StreamObserver<GrpcAPI.TransactionListExtention> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsToThis2Method(), getCallOptions()), accountPaginated, streamObserver);
        }
    }

    public static final class WalletExtensionBlockingStub extends AbstractStub<WalletExtensionBlockingStub> {
        private WalletExtensionBlockingStub(Channel channel) {
            super(channel);
        }

        private WalletExtensionBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletExtensionBlockingStub build(Channel channel, CallOptions callOptions) {
            return new WalletExtensionBlockingStub(channel, callOptions);
        }

        public GrpcAPI.TransactionList getTransactionsFromThis(GrpcAPI.AccountPaginated accountPaginated) {
            return (GrpcAPI.TransactionList) ClientCalls.blockingUnaryCall(getChannel(), WalletExtensionGrpc.getGetTransactionsFromThisMethod(), getCallOptions(), accountPaginated);
        }

        public GrpcAPI.TransactionListExtention getTransactionsFromThis2(GrpcAPI.AccountPaginated accountPaginated) {
            return (GrpcAPI.TransactionListExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletExtensionGrpc.getGetTransactionsFromThis2Method(), getCallOptions(), accountPaginated);
        }

        public GrpcAPI.TransactionList getTransactionsToThis(GrpcAPI.AccountPaginated accountPaginated) {
            return (GrpcAPI.TransactionList) ClientCalls.blockingUnaryCall(getChannel(), WalletExtensionGrpc.getGetTransactionsToThisMethod(), getCallOptions(), accountPaginated);
        }

        public GrpcAPI.TransactionListExtention getTransactionsToThis2(GrpcAPI.AccountPaginated accountPaginated) {
            return (GrpcAPI.TransactionListExtention) ClientCalls.blockingUnaryCall(getChannel(), WalletExtensionGrpc.getGetTransactionsToThis2Method(), getCallOptions(), accountPaginated);
        }
    }

    public static final class WalletExtensionFutureStub extends AbstractStub<WalletExtensionFutureStub> {
        private WalletExtensionFutureStub(Channel channel) {
            super(channel);
        }

        private WalletExtensionFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public WalletExtensionFutureStub build(Channel channel, CallOptions callOptions) {
            return new WalletExtensionFutureStub(channel, callOptions);
        }

        public ListenableFuture<GrpcAPI.TransactionList> getTransactionsFromThis(GrpcAPI.AccountPaginated accountPaginated) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsFromThisMethod(), getCallOptions()), accountPaginated);
        }

        public ListenableFuture<GrpcAPI.TransactionListExtention> getTransactionsFromThis2(GrpcAPI.AccountPaginated accountPaginated) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsFromThis2Method(), getCallOptions()), accountPaginated);
        }

        public ListenableFuture<GrpcAPI.TransactionList> getTransactionsToThis(GrpcAPI.AccountPaginated accountPaginated) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsToThisMethod(), getCallOptions()), accountPaginated);
        }

        public ListenableFuture<GrpcAPI.TransactionListExtention> getTransactionsToThis2(GrpcAPI.AccountPaginated accountPaginated) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(WalletExtensionGrpc.getGetTransactionsToThis2Method(), getCallOptions()), accountPaginated);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final WalletExtensionImplBase serviceImpl;

        MethodHandlers(WalletExtensionImplBase walletExtensionImplBase, int i) {
            this.serviceImpl = walletExtensionImplBase;
            this.methodId = i;
        }

        @Override
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.getTransactionsFromThis((GrpcAPI.AccountPaginated) req, streamObserver);
            } else if (i == 1) {
                this.serviceImpl.getTransactionsFromThis2((GrpcAPI.AccountPaginated) req, streamObserver);
            } else if (i == 2) {
                this.serviceImpl.getTransactionsToThis((GrpcAPI.AccountPaginated) req, streamObserver);
            } else if (i == 3) {
                this.serviceImpl.getTransactionsToThis2((GrpcAPI.AccountPaginated) req, streamObserver);
            } else {
                throw new AssertionError();
            }
        }

        @Override
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class WalletExtensionBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        WalletExtensionBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return GrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("WalletExtension");
        }
    }

    public static final class WalletExtensionFileDescriptorSupplier extends WalletExtensionBaseDescriptorSupplier {
        WalletExtensionFileDescriptorSupplier() {
        }
    }

    public static final class WalletExtensionMethodDescriptorSupplier extends WalletExtensionBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        WalletExtensionMethodDescriptorSupplier(String str) {
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
            synchronized (WalletExtensionGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new WalletExtensionFileDescriptorSupplier()).addMethod(getGetTransactionsFromThisMethod()).addMethod(getGetTransactionsFromThis2Method()).addMethod(getGetTransactionsToThisMethod()).addMethod(getGetTransactionsToThis2Method()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
