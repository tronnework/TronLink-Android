package org.tron.api;

import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
public final class NetworkGrpc {
    public static final String SERVICE_NAME = "protocol.Network";
    private static volatile ServiceDescriptor serviceDescriptor;

    private NetworkGrpc() {
    }

    public static NetworkStub newStub(Channel channel) {
        return new NetworkStub(channel);
    }

    public static NetworkBlockingStub newBlockingStub(Channel channel) {
        return new NetworkBlockingStub(channel);
    }

    public static NetworkFutureStub newFutureStub(Channel channel) {
        return new NetworkFutureStub(channel);
    }

    public static abstract class NetworkImplBase implements BindableService {
        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(NetworkGrpc.getServiceDescriptor()).build();
        }
    }

    public static final class NetworkStub extends AbstractStub<NetworkStub> {
        private NetworkStub(Channel channel) {
            super(channel);
        }

        private NetworkStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public NetworkStub build(Channel channel, CallOptions callOptions) {
            return new NetworkStub(channel, callOptions);
        }
    }

    public static final class NetworkBlockingStub extends AbstractStub<NetworkBlockingStub> {
        private NetworkBlockingStub(Channel channel) {
            super(channel);
        }

        private NetworkBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public NetworkBlockingStub build(Channel channel, CallOptions callOptions) {
            return new NetworkBlockingStub(channel, callOptions);
        }
    }

    public static final class NetworkFutureStub extends AbstractStub<NetworkFutureStub> {
        private NetworkFutureStub(Channel channel) {
            super(channel);
        }

        private NetworkFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        public NetworkFutureStub build(Channel channel, CallOptions callOptions) {
            return new NetworkFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final NetworkImplBase serviceImpl;

        MethodHandlers(NetworkImplBase networkImplBase, int i) {
            this.serviceImpl = networkImplBase;
            this.methodId = i;
        }

        @Override
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }

        @Override
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class NetworkBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        NetworkBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return GrpcAPI.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Network");
        }
    }

    public static final class NetworkFileDescriptorSupplier extends NetworkBaseDescriptorSupplier {
        NetworkFileDescriptorSupplier() {
        }
    }

    private static final class NetworkMethodDescriptorSupplier extends NetworkBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        NetworkMethodDescriptorSupplier(String str) {
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
            synchronized (NetworkGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new NetworkFileDescriptorSupplier()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
