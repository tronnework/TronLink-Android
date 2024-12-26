package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.InternalServerInterceptors;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerTransportFilter;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
public final class ServerImpl extends Server implements InternalInstrumented<InternalChannelz.ServerStats> {
    private final BinaryLog binlog;
    private final InternalChannelz channelz;
    private final CompressorRegistry compressorRegistry;
    private final DecompressorRegistry decompressorRegistry;
    private Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private final HandlerRegistry fallbackRegistry;
    private final long handshakeTimeoutMillis;
    private final ServerInterceptor[] interceptors;
    private final HandlerRegistry registry;
    private final Context rootContext;
    private final CallTracer serverCallTracer;
    private boolean serverShutdownCallbackInvoked;
    private boolean shutdown;
    private Status shutdownNowStatus;
    private boolean started;
    private boolean terminated;
    private final Deadline.Ticker ticker;
    private final List<ServerTransportFilter> transportFilters;
    private final InternalServer transportServer;
    private boolean transportServersTerminated;
    private static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    private static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    private final Object lock = new Object();
    private final Set<ServerTransport> transports = new HashSet();
    private final InternalLogId logId = InternalLogId.allocate(HttpHeaders.SERVER, String.valueOf(getListenSocketsIgnoringLifecycle()));

    @Override
    public InternalLogId getLogId() {
        return this.logId;
    }

    public ServerImpl(ServerImplBuilder serverImplBuilder, InternalServer internalServer, Context context) {
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(serverImplBuilder.executorPool, "executorPool");
        this.registry = (HandlerRegistry) Preconditions.checkNotNull(serverImplBuilder.registryBuilder.build(), "registryBuilder");
        this.fallbackRegistry = (HandlerRegistry) Preconditions.checkNotNull(serverImplBuilder.fallbackRegistry, "fallbackRegistry");
        this.transportServer = (InternalServer) Preconditions.checkNotNull(internalServer, "transportServer");
        this.rootContext = ((Context) Preconditions.checkNotNull(context, "rootContext")).fork();
        this.decompressorRegistry = serverImplBuilder.decompressorRegistry;
        this.compressorRegistry = serverImplBuilder.compressorRegistry;
        this.transportFilters = Collections.unmodifiableList(new ArrayList(serverImplBuilder.transportFilters));
        this.interceptors = (ServerInterceptor[]) serverImplBuilder.interceptors.toArray(new ServerInterceptor[serverImplBuilder.interceptors.size()]);
        this.handshakeTimeoutMillis = serverImplBuilder.handshakeTimeoutMillis;
        this.binlog = serverImplBuilder.binlog;
        InternalChannelz internalChannelz = serverImplBuilder.channelz;
        this.channelz = internalChannelz;
        this.serverCallTracer = serverImplBuilder.callTracerFactory.create();
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(serverImplBuilder.ticker, "ticker");
        internalChannelz.addServer(this);
    }

    @Override
    public ServerImpl start() throws IOException {
        synchronized (this.lock) {
            Preconditions.checkState(!this.started, "Already started");
            Preconditions.checkState(!this.shutdown, "Shutting down");
            this.transportServer.start(new ServerListenerImpl());
            this.executor = (Executor) Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
            this.started = true;
        }
        return this;
    }

    @Override
    public int getPort() {
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            for (SocketAddress socketAddress : this.transportServer.getListenSocketAddresses()) {
                if (socketAddress instanceof InetSocketAddress) {
                    return ((InetSocketAddress) socketAddress).getPort();
                }
            }
            return -1;
        }
    }

    @Override
    public List<SocketAddress> getListenSockets() {
        List<SocketAddress> listenSocketsIgnoringLifecycle;
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            listenSocketsIgnoringLifecycle = getListenSocketsIgnoringLifecycle();
        }
        return listenSocketsIgnoringLifecycle;
    }

    private List<SocketAddress> getListenSocketsIgnoringLifecycle() {
        List<SocketAddress> unmodifiableList;
        synchronized (this.lock) {
            unmodifiableList = Collections.unmodifiableList(this.transportServer.getListenSocketAddresses());
        }
        return unmodifiableList;
    }

    @Override
    public List<ServerServiceDefinition> getServices() {
        List<ServerServiceDefinition> services = this.fallbackRegistry.getServices();
        if (services.isEmpty()) {
            return this.registry.getServices();
        }
        List<ServerServiceDefinition> services2 = this.registry.getServices();
        ArrayList arrayList = new ArrayList(services2.size() + services.size());
        arrayList.addAll(services2);
        arrayList.addAll(services);
        return Collections.unmodifiableList(arrayList);
    }

    @Override
    public List<ServerServiceDefinition> getImmutableServices() {
        return this.registry.getServices();
    }

    @Override
    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.unmodifiableList(this.fallbackRegistry.getServices());
    }

    @Override
    public ServerImpl shutdown() {
        synchronized (this.lock) {
            if (this.shutdown) {
                return this;
            }
            this.shutdown = true;
            boolean z = this.started;
            if (!z) {
                this.transportServersTerminated = true;
                checkForTermination();
            }
            if (z) {
                this.transportServer.shutdown();
            }
            return this;
        }
    }

    @Override
    public ServerImpl shutdownNow() {
        shutdown();
        Status withDescription = Status.UNAVAILABLE.withDescription("Server shutdownNow invoked");
        synchronized (this.lock) {
            if (this.shutdownNowStatus != null) {
                return this;
            }
            this.shutdownNowStatus = withDescription;
            ArrayList<ServerTransport> arrayList = new ArrayList(this.transports);
            boolean z = this.serverShutdownCallbackInvoked;
            if (z) {
                for (ServerTransport serverTransport : arrayList) {
                    serverTransport.shutdownNow(withDescription);
                }
            }
            return this;
        }
    }

    @Override
    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    @Override
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        synchronized (this.lock) {
            long nanoTime = System.nanoTime() + timeUnit.toNanos(j);
            while (!this.terminated) {
                long nanoTime2 = nanoTime - System.nanoTime();
                if (nanoTime2 <= 0) {
                    break;
                }
                TimeUnit.NANOSECONDS.timedWait(this.lock, nanoTime2);
            }
            z = this.terminated;
        }
        return z;
    }

    @Override
    public void awaitTermination() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.terminated) {
                this.lock.wait();
            }
        }
    }

    @Override
    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            z = this.terminated;
        }
        return z;
    }

    public void transportClosed(ServerTransport serverTransport) {
        synchronized (this.lock) {
            if (!this.transports.remove(serverTransport)) {
                throw new AssertionError("Transport already removed");
            }
            this.channelz.removeServerSocket(this, serverTransport);
            checkForTermination();
        }
    }

    public void checkForTermination() {
        synchronized (this.lock) {
            if (this.shutdown && this.transports.isEmpty() && this.transportServersTerminated) {
                if (this.terminated) {
                    throw new AssertionError("Server already terminated");
                }
                this.terminated = true;
                this.channelz.removeServer(this);
                Executor executor = this.executor;
                if (executor != null) {
                    this.executor = this.executorPool.returnObject(executor);
                }
                this.lock.notifyAll();
            }
        }
    }

    public final class ServerListenerImpl implements ServerListener {
        private ServerListenerImpl() {
        }

        @Override
        public ServerTransportListener transportCreated(ServerTransport serverTransport) {
            synchronized (lock) {
                transports.add(serverTransport);
            }
            ServerTransportListenerImpl serverTransportListenerImpl = new ServerTransportListenerImpl(serverTransport);
            serverTransportListenerImpl.init();
            return serverTransportListenerImpl;
        }

        @Override
        public void serverShutdown() {
            synchronized (lock) {
                if (serverShutdownCallbackInvoked) {
                    return;
                }
                ArrayList arrayList = new ArrayList(transports);
                Status status = shutdownNowStatus;
                serverShutdownCallbackInvoked = true;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ServerTransport serverTransport = (ServerTransport) it.next();
                    if (status == null) {
                        serverTransport.shutdown();
                    } else {
                        serverTransport.shutdownNow(status);
                    }
                }
                synchronized (lock) {
                    transportServersTerminated = true;
                    checkForTermination();
                }
            }
        }
    }

    public final class ServerTransportListenerImpl implements ServerTransportListener {
        private Attributes attributes;
        private Future<?> handshakeTimeoutFuture;
        private final ServerTransport transport;

        ServerTransportListenerImpl(ServerTransport serverTransport) {
            this.transport = serverTransport;
        }

        public void init() {
            if (handshakeTimeoutMillis != Long.MAX_VALUE) {
                this.handshakeTimeoutFuture = this.transport.getScheduledExecutorService().schedule(new Runnable() {
                    @Override
                    public void run() {
                        ServerTransportListenerImpl.this.transport.shutdownNow(Status.CANCELLED.withDescription("Handshake timeout exceeded"));
                    }
                }, handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
            } else {
                this.handshakeTimeoutFuture = new FutureTask(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, null);
            }
            channelz.addServerSocket(ServerImpl.this, this.transport);
        }

        @Override
        public Attributes transportReady(Attributes attributes) {
            this.handshakeTimeoutFuture.cancel(false);
            this.handshakeTimeoutFuture = null;
            for (ServerTransportFilter serverTransportFilter : transportFilters) {
                attributes = (Attributes) Preconditions.checkNotNull(serverTransportFilter.transportReady(attributes), "Filter %s returned null", serverTransportFilter);
            }
            this.attributes = attributes;
            return attributes;
        }

        @Override
        public void transportTerminated() {
            Future<?> future = this.handshakeTimeoutFuture;
            if (future != null) {
                future.cancel(false);
                this.handshakeTimeoutFuture = null;
            }
            for (ServerTransportFilter serverTransportFilter : transportFilters) {
                serverTransportFilter.transportTerminated(this.attributes);
            }
            transportClosed(this.transport);
        }

        @Override
        public void streamCreated(ServerStream serverStream, String str, Metadata metadata) {
            Tag createTag = PerfMark.createTag(str, serverStream.streamId());
            PerfMark.startTask("ServerTransportListener.streamCreated", createTag);
            try {
                streamCreatedInternal(serverStream, str, metadata, createTag);
            } finally {
                PerfMark.stopTask("ServerTransportListener.streamCreated", createTag);
            }
        }

        private void streamCreatedInternal(ServerStream serverStream, String str, Metadata metadata, Tag tag) {
            Executor serializingExecutor;
            if (executor == MoreExecutors.directExecutor()) {
                serializingExecutor = new SerializeReentrantCallsDirectExecutor();
                serverStream.optimizeForDirectExecutor();
            } else {
                serializingExecutor = new SerializingExecutor(executor);
            }
            Executor executor = serializingExecutor;
            if (metadata.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
                String str2 = (String) metadata.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                Decompressor lookupDecompressor = decompressorRegistry.lookupDecompressor(str2);
                if (lookupDecompressor == null) {
                    serverStream.setListener(ServerImpl.NOOP_LISTENER);
                    serverStream.close(Status.UNIMPLEMENTED.withDescription(String.format("Can't find decompressor for %s", str2)), new Metadata());
                    return;
                }
                serverStream.setDecompressor(lookupDecompressor);
            }
            StatsTraceContext statsTraceContext = (StatsTraceContext) Preconditions.checkNotNull(serverStream.statsTraceContext(), "statsTraceCtx not present from stream");
            Context.CancellableContext createContext = createContext(metadata, statsTraceContext);
            Link linkOut = PerfMark.linkOut();
            JumpToApplicationThreadServerStreamListener jumpToApplicationThreadServerStreamListener = new JumpToApplicationThreadServerStreamListener(executor, executor, serverStream, createContext, tag);
            serverStream.setListener(jumpToApplicationThreadServerStreamListener);
            executor.execute(new ContextRunnable(createContext, tag, linkOut, str, serverStream, metadata, statsTraceContext, jumpToApplicationThreadServerStreamListener) {
                final Context.CancellableContext val$context;
                final Metadata val$headers;
                final JumpToApplicationThreadServerStreamListener val$jumpListener;
                final Link val$link;
                final String val$methodName;
                final StatsTraceContext val$statsTraceCtx;
                final ServerStream val$stream;
                final Tag val$tag;

                {
                    super(createContext);
                    this.val$context = createContext;
                    this.val$tag = tag;
                    this.val$link = linkOut;
                    this.val$methodName = str;
                    this.val$stream = serverStream;
                    this.val$headers = metadata;
                    this.val$statsTraceCtx = statsTraceContext;
                    this.val$jumpListener = jumpToApplicationThreadServerStreamListener;
                }

                @Override
                public void runInContext() {
                    PerfMark.startTask("ServerTransportListener$StreamCreated.startCall", this.val$tag);
                    PerfMark.linkIn(this.val$link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ServerTransportListener$StreamCreated.startCall", this.val$tag);
                    }
                }

                private void runInternal() {
                    ServerStreamListener serverStreamListener = ServerImpl.NOOP_LISTENER;
                    try {
                        ServerMethodDefinition<?, ?> lookupMethod = registry.lookupMethod(this.val$methodName);
                        if (lookupMethod == null) {
                            lookupMethod = fallbackRegistry.lookupMethod(this.val$methodName, this.val$stream.getAuthority());
                        }
                        ServerMethodDefinition<?, ?> serverMethodDefinition = lookupMethod;
                        if (serverMethodDefinition != null) {
                            this.val$jumpListener.setListener(ServerTransportListenerImpl.this.startCall(this.val$stream, this.val$methodName, serverMethodDefinition, this.val$headers, this.val$context, this.val$statsTraceCtx, this.val$tag));
                            this.val$context.addListener(new Context.CancellationListener() {
                                @Override
                                public void cancelled(Context context) {
                                    Status statusFromCancelled = Contexts.statusFromCancelled(context);
                                    if (Status.DEADLINE_EXCEEDED.getCode().equals(statusFromCancelled.getCode())) {
                                        1StreamCreated.this.val$stream.cancel(statusFromCancelled);
                                    }
                                }
                            }, MoreExecutors.directExecutor());
                            return;
                        }
                        Status status = Status.UNIMPLEMENTED;
                        this.val$stream.close(status.withDescription("Method not found: " + this.val$methodName), new Metadata());
                        this.val$context.cancel(null);
                    } catch (Throwable th) {
                        try {
                            this.val$stream.close(Status.fromThrowable(th), new Metadata());
                            this.val$context.cancel(null);
                            throw th;
                        } finally {
                            this.val$jumpListener.setListener(serverStreamListener);
                        }
                    }
                }
            });
        }

        private Context.CancellableContext createContext(Metadata metadata, StatsTraceContext statsTraceContext) {
            Long l = (Long) metadata.get(GrpcUtil.TIMEOUT_KEY);
            Context withValue = statsTraceContext.serverFilterContext(rootContext).withValue(io.grpc.InternalServer.SERVER_CONTEXT_KEY, ServerImpl.this);
            if (l == null) {
                return withValue.withCancellation();
            }
            return withValue.withDeadline(Deadline.after(l.longValue(), TimeUnit.NANOSECONDS, ticker), this.transport.getScheduledExecutorService());
        }

        public <ReqT, RespT> ServerStreamListener startCall(ServerStream serverStream, String str, ServerMethodDefinition<ReqT, RespT> serverMethodDefinition, Metadata metadata, Context.CancellableContext cancellableContext, StatsTraceContext statsTraceContext, Tag tag) {
            statsTraceContext.serverCallStarted(new ServerCallInfoImpl(serverMethodDefinition.getMethodDescriptor(), serverStream.getAttributes(), serverStream.getAuthority()));
            ServerCallHandler<ReqT, RespT> serverCallHandler = serverMethodDefinition.getServerCallHandler();
            for (ServerInterceptor serverInterceptor : interceptors) {
                serverCallHandler = InternalServerInterceptors.interceptCallHandler(serverInterceptor, serverCallHandler);
            }
            ServerMethodDefinition<ReqT, RespT> withServerCallHandler = serverMethodDefinition.withServerCallHandler(serverCallHandler);
            if (binlog != null) {
                withServerCallHandler = (ServerMethodDefinition<ReqT, RespT>) binlog.wrapMethodDefinition(withServerCallHandler);
            }
            return startWrappedCall(str, withServerCallHandler, serverStream, metadata, cancellableContext, tag);
        }

        private <WReqT, WRespT> ServerStreamListener startWrappedCall(String str, ServerMethodDefinition<WReqT, WRespT> serverMethodDefinition, ServerStream serverStream, Metadata metadata, Context.CancellableContext cancellableContext, Tag tag) {
            ServerCallImpl serverCallImpl = new ServerCallImpl(serverStream, serverMethodDefinition.getMethodDescriptor(), metadata, cancellableContext, decompressorRegistry, compressorRegistry, serverCallTracer, tag);
            ServerCall.Listener<WReqT> startCall = serverMethodDefinition.getServerCallHandler().startCall(serverCallImpl, metadata);
            if (startCall == null) {
                throw new NullPointerException("startCall() returned a null listener for method " + str);
            }
            return serverCallImpl.newServerStreamListener(startCall);
        }
    }

    @Override
    public ListenableFuture<InternalChannelz.ServerStats> getStats() {
        InternalChannelz.ServerStats.Builder builder = new InternalChannelz.ServerStats.Builder();
        List<InternalInstrumented<InternalChannelz.SocketStats>> listenSocketStatsList = this.transportServer.getListenSocketStatsList();
        if (listenSocketStatsList != null) {
            builder.addListenSockets(listenSocketStatsList);
        }
        this.serverCallTracer.updateBuilder(builder);
        SettableFuture create = SettableFuture.create();
        create.set(builder.build());
        return create;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("transportServer", this.transportServer).toString();
    }

    private static final class NoopListener implements ServerStreamListener {
        @Override
        public void closed(Status status) {
        }

        @Override
        public void halfClosed() {
        }

        @Override
        public void onReady() {
        }

        private NoopListener() {
        }

        @Override
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            while (true) {
                InputStream next = messageProducer.next();
                if (next == null) {
                    return;
                }
                try {
                    next.close();
                } catch (IOException e) {
                    while (true) {
                        InputStream next2 = messageProducer.next();
                        if (next2 == null) {
                            break;
                        }
                        try {
                            next2.close();
                        } catch (IOException e2) {
                            ServerImpl.log.log(Level.WARNING, "Exception closing stream", (Throwable) e2);
                        }
                    }
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static final class JumpToApplicationThreadServerStreamListener implements ServerStreamListener {
        private final Executor callExecutor;
        private final Executor cancelExecutor;
        private final Context.CancellableContext context;
        private ServerStreamListener listener;
        private final ServerStream stream;
        private final Tag tag;

        public JumpToApplicationThreadServerStreamListener(Executor executor, Executor executor2, ServerStream serverStream, Context.CancellableContext cancellableContext, Tag tag) {
            this.callExecutor = executor;
            this.cancelExecutor = executor2;
            this.stream = serverStream;
            this.context = cancellableContext;
            this.tag = tag;
        }

        public ServerStreamListener getListener() {
            ServerStreamListener serverStreamListener = this.listener;
            if (serverStreamListener != null) {
                return serverStreamListener;
            }
            throw new IllegalStateException("listener unset");
        }

        void setListener(ServerStreamListener serverStreamListener) {
            Preconditions.checkNotNull(serverStreamListener, "listener must not be null");
            Preconditions.checkState(this.listener == null, "Listener already set");
            this.listener = serverStreamListener;
        }

        public void internalClose(Throwable th) {
            this.stream.close(Status.UNKNOWN.withCause(th), new Metadata());
        }

        @Override
        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() {
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).messagesAvailable", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().messagesAvailable(messageProducer);
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", this.tag);
            }
        }

        @Override
        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() {
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).halfClosed", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().halfClosed();
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", this.tag);
            }
        }

        @Override
        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", this.tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", this.tag);
            }
        }

        private void closedInternal(final Status status) {
            if (!status.isOk()) {
                this.cancelExecutor.execute(new ContextCloser(this.context, status.getCause()));
            }
            final Link linkOut = PerfMark.linkOut();
            this.callExecutor.execute(new ContextRunnable() {
                {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override
                public void runInContext() {
                    PerfMark.startTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    PerfMark.linkIn(linkOut);
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().closed(status);
                    } finally {
                        PerfMark.stopTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    }
                }
            });
        }

        @Override
        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() {
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).onReady", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().onReady();
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.onReady", this.tag);
            }
        }
    }

    public static final class ContextCloser implements Runnable {
        private final Throwable cause;
        private final Context.CancellableContext context;

        ContextCloser(Context.CancellableContext cancellableContext, Throwable th) {
            this.context = cancellableContext;
            this.cause = th;
        }

        @Override
        public void run() {
            this.context.cancel(this.cause);
        }
    }
}
