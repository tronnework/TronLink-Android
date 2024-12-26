package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Context;
import io.grpc.InternalConfigSelector;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientStreamListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
final class SubchannelChannel extends Channel {
    static final Status NOT_READY_ERROR;
    static final Status WAIT_FOR_READY_ERROR;
    private static final FailingClientTransport notReadyTransport;
    private final CallTracer callsTracer;
    private final AtomicReference<InternalConfigSelector> configSelector;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private final Executor executor;
    private final InternalSubchannel subchannel;
    private final ClientCallImpl.ClientStreamProvider transportProvider = new ClientCallImpl.ClientStreamProvider() {
        @Override
        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            ClientTransport transport = subchannel.getTransport();
            if (transport == null) {
                transport = SubchannelChannel.notReadyTransport;
            }
            Context attach = context.attach();
            try {
                return transport.newStream(methodDescriptor, metadata, callOptions);
            } finally {
                context.detach(attach);
            }
        }
    };

    static {
        Status withDescription = Status.UNAVAILABLE.withDescription("Subchannel is NOT READY");
        NOT_READY_ERROR = withDescription;
        WAIT_FOR_READY_ERROR = Status.UNAVAILABLE.withDescription("wait-for-ready RPC is not supported on Subchannel.asChannel()");
        notReadyTransport = new FailingClientTransport(withDescription, ClientStreamListener.RpcProgress.REFUSED);
    }

    public SubchannelChannel(InternalSubchannel internalSubchannel, Executor executor, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer, AtomicReference<InternalConfigSelector> atomicReference) {
        this.subchannel = (InternalSubchannel) Preconditions.checkNotNull(internalSubchannel, "subchannel");
        this.executor = (Executor) Preconditions.checkNotNull(executor, "executor");
        this.deadlineCancellationExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "deadlineCancellationExecutor");
        this.callsTracer = (CallTracer) Preconditions.checkNotNull(callTracer, "callsTracer");
        this.configSelector = (AtomicReference) Preconditions.checkNotNull(atomicReference, "configSelector");
    }

    @Override
    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        final Executor executor = callOptions.getExecutor() == null ? this.executor : callOptions.getExecutor();
        if (callOptions.isWaitForReady()) {
            return new ClientCall<RequestT, ResponseT>() {
                @Override
                public void cancel(String str, Throwable th) {
                }

                @Override
                public void halfClose() {
                }

                @Override
                public void request(int i) {
                }

                @Override
                public void sendMessage(RequestT requestt) {
                }

                @Override
                public void start(final ClientCall.Listener<ResponseT> listener, Metadata metadata) {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            listener.onClose(SubchannelChannel.WAIT_FOR_READY_ERROR, new Metadata());
                        }
                    });
                }
            };
        }
        return new ClientCallImpl(methodDescriptor, executor, callOptions.withOption(GrpcUtil.CALL_OPTIONS_RPC_OWNED_BY_BALANCER, Boolean.TRUE), this.transportProvider, this.deadlineCancellationExecutor, this.callsTracer, this.configSelector.get());
    }

    @Override
    public String authority() {
        return this.subchannel.getAuthority();
    }
}
