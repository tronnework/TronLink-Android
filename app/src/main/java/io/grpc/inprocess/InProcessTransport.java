package io.grpc.inprocess;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.InsightBuilder;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
public final class InProcessTransport implements ServerTransport, ConnectionClientTransport {
    private static final Logger log = Logger.getLogger(InProcessTransport.class.getName());
    private final Attributes attributes;
    private final String authority;
    private final int clientMaxInboundMetadataSize;
    private ManagedClientTransport.Listener clientTransportListener;
    private final InUseStateAggregator<InProcessStream> inUseState;
    private final boolean includeCauseWithStatus;
    private final InternalLogId logId;
    private final String name;
    private final Optional<ServerListener> optionalServerListener;
    private int serverMaxInboundMetadataSize;
    private ScheduledExecutorService serverScheduler;
    private ObjectPool<ScheduledExecutorService> serverSchedulerPool;
    private Attributes serverStreamAttributes;
    private List<ServerStreamTracer.Factory> serverStreamTracerFactories;
    private ServerTransportListener serverTransportListener;
    private boolean shutdown;
    private Status shutdownStatus;
    private final Set<InProcessStream> streams;
    private boolean terminated;
    private final String userAgent;

    static Logger Fun$2100() {
        return log;
    }

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.serverScheduler;
    }

    private InProcessTransport(String str, int i, String str2, String str3, Attributes attributes, Optional<ServerListener> optional, boolean z) {
        this.streams = Collections.newSetFromMap(new IdentityHashMap());
        this.inUseState = new InUseStateAggregator<InProcessStream>() {
            @Override
            protected void handleInUse() {
                clientTransportListener.transportInUse(true);
            }

            @Override
            protected void handleNotInUse() {
                clientTransportListener.transportInUse(false);
            }
        };
        this.name = str;
        this.clientMaxInboundMetadataSize = i;
        this.authority = str2;
        this.userAgent = GrpcUtil.getGrpcUserAgent("inprocess", str3);
        Preconditions.checkNotNull(attributes, "eagAttrs");
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, attributes).set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, new InProcessSocketAddress(str)).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, new InProcessSocketAddress(str)).build();
        this.optionalServerListener = optional;
        this.logId = InternalLogId.allocate(getClass(), str);
        this.includeCauseWithStatus = z;
    }

    public InProcessTransport(String str, int i, String str2, String str3, Attributes attributes, boolean z) {
        this(str, i, str2, str3, attributes, Optional.absent(), z);
    }

    public InProcessTransport(String str, int i, String str2, String str3, Attributes attributes, ObjectPool<ScheduledExecutorService> objectPool, List<ServerStreamTracer.Factory> list, ServerListener serverListener) {
        this(str, i, str2, str3, attributes, Optional.of(serverListener), false);
        this.serverMaxInboundMetadataSize = i;
        this.serverSchedulerPool = objectPool;
        this.serverStreamTracerFactories = list;
    }

    @Override
    @CheckReturnValue
    public synchronized Runnable start(ManagedClientTransport.Listener listener) {
        this.clientTransportListener = listener;
        if (this.optionalServerListener.isPresent()) {
            this.serverScheduler = this.serverSchedulerPool.getObject();
            this.serverTransportListener = this.optionalServerListener.get().transportCreated(this);
        } else {
            InProcessServer findServer = InProcessServer.findServer(this.name);
            if (findServer != null) {
                this.serverMaxInboundMetadataSize = findServer.getMaxInboundMetadataSize();
                ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool = findServer.getScheduledExecutorServicePool();
                this.serverSchedulerPool = scheduledExecutorServicePool;
                this.serverScheduler = scheduledExecutorServicePool.getObject();
                this.serverStreamTracerFactories = findServer.getStreamTracerFactories();
                this.serverTransportListener = findServer.register(this);
            }
        }
        if (this.serverTransportListener == null) {
            Status status = Status.UNAVAILABLE;
            final Status withDescription = status.withDescription("Could not find server: " + this.name);
            this.shutdownStatus = withDescription;
            return new Runnable() {
                @Override
                public void run() {
                    synchronized (InProcessTransport.this) {
                        notifyShutdown(withDescription);
                        notifyTerminated();
                    }
                }
            };
        }
        return new Runnable() {
            @Override
            public void run() {
                synchronized (InProcessTransport.this) {
                    Attributes build = Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, new InProcessSocketAddress(name)).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, new InProcessSocketAddress(name)).build();
                    InProcessTransport inProcessTransport = InProcessTransport.this;
                    inProcessTransport.serverStreamAttributes = inProcessTransport.serverTransportListener.transportReady(build);
                    clientTransportListener.transportReady();
                }
            }
        };
    }

    @Override
    public synchronized ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        int metadataSize;
        if (this.shutdownStatus != null) {
            return failedClientStream(StatsTraceContext.newClientContext(callOptions, this.attributes, metadata), this.shutdownStatus);
        }
        metadata.put(GrpcUtil.USER_AGENT_KEY, this.userAgent);
        if (this.serverMaxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = metadataSize(metadata)) > this.serverMaxInboundMetadataSize) {
            return failedClientStream(StatsTraceContext.newClientContext(callOptions, this.attributes, metadata), Status.RESOURCE_EXHAUSTED.withDescription(String.format("Request metadata larger than %d: %d", Integer.valueOf(this.serverMaxInboundMetadataSize), Integer.valueOf(metadataSize))));
        }
        return new InProcessStream(methodDescriptor, metadata, callOptions, this.authority).clientStream;
    }

    private ClientStream failedClientStream(final StatsTraceContext statsTraceContext, final Status status) {
        return new NoopClientStream() {
            @Override
            public void start(ClientStreamListener clientStreamListener) {
                statsTraceContext.clientOutboundHeaders();
                statsTraceContext.streamClosed(status);
                clientStreamListener.closed(status, new Metadata());
            }
        };
    }

    @Override
    public synchronized void ping(final ClientTransport.PingCallback pingCallback, Executor executor) {
        if (this.terminated) {
            final Status status = this.shutdownStatus;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    pingCallback.onFailure(status.asRuntimeException());
                }
            });
        } else {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    pingCallback.onSuccess(0L);
                }
            });
        }
    }

    @Override
    public synchronized void shutdown(Status status) {
        if (this.shutdown) {
            return;
        }
        this.shutdownStatus = status;
        notifyShutdown(status);
        if (this.streams.isEmpty()) {
            notifyTerminated();
        }
    }

    @Override
    public synchronized void shutdown() {
        shutdown(Status.UNAVAILABLE.withDescription("InProcessTransport shutdown by the server-side"));
    }

    @Override
    public void shutdownNow(Status status) {
        Preconditions.checkNotNull(status, "reason");
        synchronized (this) {
            shutdown(status);
            if (this.terminated) {
                return;
            }
            for (InProcessStream inProcessStream : new ArrayList(this.streams)) {
                inProcessStream.clientStream.cancel(status);
            }
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).toString();
    }

    @Override
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        create.set(null);
        return create;
    }

    public synchronized void notifyShutdown(Status status) {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        this.clientTransportListener.transportShutdown(status);
    }

    public synchronized void notifyTerminated() {
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        ScheduledExecutorService scheduledExecutorService = this.serverScheduler;
        if (scheduledExecutorService != null) {
            this.serverScheduler = this.serverSchedulerPool.returnObject(scheduledExecutorService);
        }
        this.clientTransportListener.transportTerminated();
        ServerTransportListener serverTransportListener = this.serverTransportListener;
        if (serverTransportListener != null) {
            serverTransportListener.transportTerminated();
        }
    }

    public static int metadataSize(Metadata metadata) {
        byte[][] serialize = InternalMetadata.serialize(metadata);
        if (serialize == null) {
            return 0;
        }
        long j = 0;
        for (int i = 0; i < serialize.length; i += 2) {
            j += serialize[i].length + 32 + serialize[i + 1].length;
        }
        return (int) Math.min(j, 2147483647L);
    }

    public class InProcessStream {
        private volatile String authority;
        private final CallOptions callOptions;
        private final InProcessClientStream clientStream;
        private final Metadata headers;
        private final MethodDescriptor<?, ?> method;
        private final InProcessServerStream serverStream;

        private InProcessStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, String str) {
            this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, FirebaseAnalytics.Param.METHOD);
            this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
            this.authority = str;
            this.clientStream = new InProcessClientStream(callOptions, metadata);
            this.serverStream = new InProcessServerStream(methodDescriptor, metadata);
        }

        public void streamClosed() {
            synchronized (InProcessTransport.this) {
                boolean remove = streams.remove(this);
                if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                    inUseState.updateObjectInUse(this, false);
                }
                if (streams.isEmpty() && remove && shutdown) {
                    notifyTerminated();
                }
            }
        }

        public class InProcessServerStream implements ServerStream {
            private Status clientNotifyStatus;
            private Metadata clientNotifyTrailers;
            private ArrayDeque<StreamListener.MessageProducer> clientReceiveQueue = new ArrayDeque<>();
            private int clientRequested;
            private ClientStreamListener clientStreamListener;
            private boolean closed;
            private int outboundSeqNo;
            final StatsTraceContext statsTraceCtx;

            @Override
            public void flush() {
            }

            @Override
            public void optimizeForDirectExecutor() {
            }

            @Override
            public void setCompressor(Compressor compressor) {
            }

            @Override
            public void setDecompressor(Decompressor decompressor) {
            }

            @Override
            public void setMessageCompression(boolean z) {
            }

            @Override
            public StatsTraceContext statsTraceContext() {
                return this.statsTraceCtx;
            }

            @Override
            public int streamId() {
                return -1;
            }

            InProcessServerStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata) {
                this.statsTraceCtx = StatsTraceContext.newServerContext(serverStreamTracerFactories, methodDescriptor.getFullMethodName(), metadata);
            }

            public synchronized void setListener(ClientStreamListener clientStreamListener) {
                this.clientStreamListener = clientStreamListener;
            }

            @Override
            public void setListener(ServerStreamListener serverStreamListener) {
                InProcessStream.this.clientStream.setListener(serverStreamListener);
            }

            @Override
            public void request(int i) {
                if (InProcessStream.this.clientStream.serverRequested(i)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.clientStreamListener.onReady();
                        }
                    }
                }
            }

            public synchronized boolean clientRequested(int i) {
                boolean z = false;
                if (this.closed) {
                    return false;
                }
                int i2 = this.clientRequested;
                boolean z2 = i2 > 0;
                this.clientRequested = i2 + i;
                while (this.clientRequested > 0 && !this.clientReceiveQueue.isEmpty()) {
                    this.clientRequested--;
                    this.clientStreamListener.messagesAvailable(this.clientReceiveQueue.poll());
                }
                if (this.closed) {
                    return false;
                }
                if (this.clientReceiveQueue.isEmpty() && this.clientNotifyStatus != null) {
                    this.closed = true;
                    InProcessStream.this.clientStream.statsTraceCtx.clientInboundTrailers(this.clientNotifyTrailers);
                    InProcessStream.this.clientStream.statsTraceCtx.streamClosed(this.clientNotifyStatus);
                    this.clientStreamListener.closed(this.clientNotifyStatus, this.clientNotifyTrailers);
                }
                boolean z3 = this.clientRequested > 0;
                if (!z2 && z3) {
                    z = true;
                }
                return z;
            }

            public void clientCancelled(Status status) {
                internalCancel(status);
            }

            @Override
            public synchronized void writeMessage(InputStream inputStream) {
                if (this.closed) {
                    return;
                }
                this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                InProcessStream.this.clientStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                InProcessStream.this.clientStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                this.outboundSeqNo++;
                SingleMessageProducer singleMessageProducer = new SingleMessageProducer(inputStream);
                int i = this.clientRequested;
                if (i > 0) {
                    this.clientRequested = i - 1;
                    this.clientStreamListener.messagesAvailable(singleMessageProducer);
                } else {
                    this.clientReceiveQueue.add(singleMessageProducer);
                }
            }

            @Override
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.clientRequested > 0;
            }

            @Override
            public void writeHeaders(Metadata metadata) {
                int metadataSize;
                if (clientMaxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = InProcessTransport.metadataSize(metadata)) > clientMaxInboundMetadataSize) {
                    Status withDescription = Status.CANCELLED.withDescription("Client cancelled the RPC");
                    InProcessStream.this.clientStream.serverClosed(withDescription, withDescription);
                    notifyClientClose(Status.RESOURCE_EXHAUSTED.withDescription(String.format("Response header metadata larger than %d: %d", Integer.valueOf(clientMaxInboundMetadataSize), Integer.valueOf(metadataSize))), new Metadata());
                    return;
                }
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    InProcessStream.this.clientStream.statsTraceCtx.clientInboundHeaders();
                    this.clientStreamListener.headersRead(metadata);
                }
            }

            @Override
            public void close(Status status, Metadata metadata) {
                InProcessStream.this.clientStream.serverClosed(Status.OK, status);
                if (clientMaxInboundMetadataSize != Integer.MAX_VALUE) {
                    int metadataSize = InProcessTransport.metadataSize(metadata) + (status.getDescription() == null ? 0 : status.getDescription().length());
                    if (metadataSize > clientMaxInboundMetadataSize) {
                        status = Status.RESOURCE_EXHAUSTED.withDescription(String.format("Response header metadata larger than %d: %d", Integer.valueOf(clientMaxInboundMetadataSize), Integer.valueOf(metadataSize)));
                        metadata = new Metadata();
                    }
                }
                notifyClientClose(status, metadata);
            }

            private void notifyClientClose(Status status, Metadata metadata) {
                Status cleanStatus = InProcessTransport.cleanStatus(status, includeCauseWithStatus);
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    if (this.clientReceiveQueue.isEmpty()) {
                        this.closed = true;
                        InProcessStream.this.clientStream.statsTraceCtx.clientInboundTrailers(metadata);
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(cleanStatus);
                        this.clientStreamListener.closed(cleanStatus, metadata);
                    } else {
                        this.clientNotifyStatus = cleanStatus;
                        this.clientNotifyTrailers = metadata;
                    }
                    InProcessStream.this.streamClosed();
                }
            }

            @Override
            public void cancel(Status status) {
                if (internalCancel(Status.CANCELLED.withDescription("server cancelled stream"))) {
                    InProcessStream.this.clientStream.serverClosed(status, status);
                    InProcessStream.this.streamClosed();
                }
            }

            private synchronized boolean internalCancel(Status status) {
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while (true) {
                    StreamListener.MessageProducer poll = this.clientReceiveQueue.poll();
                    if (poll == null) {
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(status);
                        this.clientStreamListener.closed(status, new Metadata());
                        return true;
                    }
                    while (true) {
                        InputStream next = poll.next();
                        if (next != null) {
                            next.close();
                        }
                    }
                }
            }

            @Override
            public Attributes getAttributes() {
                return serverStreamAttributes;
            }

            @Override
            public String getAuthority() {
                return InProcessStream.this.authority;
            }
        }

        public class InProcessClientStream implements ClientStream {
            final CallOptions callOptions;
            private boolean closed;
            private int outboundSeqNo;
            private boolean serverNotifyHalfClose;
            private ArrayDeque<StreamListener.MessageProducer> serverReceiveQueue = new ArrayDeque<>();
            private int serverRequested;
            private ServerStreamListener serverStreamListener;
            final StatsTraceContext statsTraceCtx;

            @Override
            public void appendTimeoutInsight(InsightBuilder insightBuilder) {
            }

            @Override
            public void flush() {
            }

            @Override
            public void optimizeForDirectExecutor() {
            }

            @Override
            public void setCompressor(Compressor compressor) {
            }

            @Override
            public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            }

            @Override
            public void setFullStreamDecompression(boolean z) {
            }

            @Override
            public void setMaxInboundMessageSize(int i) {
            }

            @Override
            public void setMaxOutboundMessageSize(int i) {
            }

            @Override
            public void setMessageCompression(boolean z) {
            }

            InProcessClientStream(CallOptions callOptions, Metadata metadata) {
                this.callOptions = callOptions;
                this.statsTraceCtx = StatsTraceContext.newClientContext(callOptions, attributes, metadata);
            }

            public synchronized void setListener(ServerStreamListener serverStreamListener) {
                this.serverStreamListener = serverStreamListener;
            }

            @Override
            public void request(int i) {
                if (InProcessStream.this.serverStream.clientRequested(i)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.serverStreamListener.onReady();
                        }
                    }
                }
            }

            public synchronized boolean serverRequested(int i) {
                boolean z = false;
                if (this.closed) {
                    return false;
                }
                int i2 = this.serverRequested;
                boolean z2 = i2 > 0;
                this.serverRequested = i2 + i;
                while (this.serverRequested > 0 && !this.serverReceiveQueue.isEmpty()) {
                    this.serverRequested--;
                    this.serverStreamListener.messagesAvailable(this.serverReceiveQueue.poll());
                }
                if (this.serverReceiveQueue.isEmpty() && this.serverNotifyHalfClose) {
                    this.serverNotifyHalfClose = false;
                    this.serverStreamListener.halfClosed();
                }
                boolean z3 = this.serverRequested > 0;
                if (!z2 && z3) {
                    z = true;
                }
                return z;
            }

            public void serverClosed(Status status, Status status2) {
                internalCancel(status, status2);
            }

            @Override
            public synchronized void writeMessage(InputStream inputStream) {
                if (this.closed) {
                    return;
                }
                this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                InProcessStream.this.serverStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                InProcessStream.this.serverStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                this.outboundSeqNo++;
                SingleMessageProducer singleMessageProducer = new SingleMessageProducer(inputStream);
                int i = this.serverRequested;
                if (i > 0) {
                    this.serverRequested = i - 1;
                    this.serverStreamListener.messagesAvailable(singleMessageProducer);
                } else {
                    this.serverReceiveQueue.add(singleMessageProducer);
                }
            }

            @Override
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.serverRequested > 0;
            }

            @Override
            public void cancel(Status status) {
                Status cleanStatus = InProcessTransport.cleanStatus(status, includeCauseWithStatus);
                if (internalCancel(cleanStatus, cleanStatus)) {
                    InProcessStream.this.serverStream.clientCancelled(status);
                    InProcessStream.this.streamClosed();
                }
            }

            private synchronized boolean internalCancel(Status status, Status status2) {
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while (true) {
                    StreamListener.MessageProducer poll = this.serverReceiveQueue.poll();
                    if (poll == null) {
                        InProcessStream.this.serverStream.statsTraceCtx.streamClosed(status2);
                        this.serverStreamListener.closed(status);
                        return true;
                    }
                    while (true) {
                        InputStream next = poll.next();
                        if (next != null) {
                            next.close();
                        }
                    }
                }
            }

            @Override
            public synchronized void halfClose() {
                if (this.closed) {
                    return;
                }
                if (this.serverReceiveQueue.isEmpty()) {
                    this.serverStreamListener.halfClosed();
                } else {
                    this.serverNotifyHalfClose = true;
                }
            }

            @Override
            public void setAuthority(String str) {
                InProcessStream.this.authority = str;
            }

            @Override
            public void start(ClientStreamListener clientStreamListener) {
                InProcessStream.this.serverStream.setListener(clientStreamListener);
                synchronized (InProcessTransport.this) {
                    this.statsTraceCtx.clientOutboundHeaders();
                    streams.add(InProcessStream.this);
                    if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                        inUseState.updateObjectInUse(InProcessStream.this, true);
                    }
                    serverTransportListener.streamCreated(InProcessStream.this.serverStream, InProcessStream.this.method.getFullMethodName(), InProcessStream.this.headers);
                }
            }

            @Override
            public Attributes getAttributes() {
                return attributes;
            }

            @Override
            public void setDeadline(Deadline deadline) {
                InProcessStream.this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
                InProcessStream.this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
            }
        }
    }

    public static Status cleanStatus(Status status, boolean z) {
        if (status == null) {
            return null;
        }
        Status withDescription = Status.fromCodeValue(status.getCode().value()).withDescription(status.getDescription());
        return z ? withDescription.withCause(status.getCause()) : withDescription;
    }

    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        @Override
        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }
    }
}
