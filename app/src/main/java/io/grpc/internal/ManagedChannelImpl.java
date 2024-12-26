package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ClientStreamTracer;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.ForwardingClientCall;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.AutoConfiguredLoadBalancerFactory;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.RetriableStream;
import java.lang.Thread;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
public final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<InternalChannelz.ChannelStats> {
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1;
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5;
    @Nullable
    private final String authorityOverride;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final ExecutorHolder balancerRpcExecutorHolder;
    private final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    private final CallTracer.Factory callTracerFactory;
    private final long channelBufferLimit;
    private final RetriableStream.ChannelBufferMeter channelBufferUsed;
    private final CallTracer channelCallTracer;
    private final ChannelLogger channelLogger;
    private final ConnectivityStateManager channelStateManager;
    private final ChannelTracer channelTracer;
    private final InternalChannelz channelz;
    private final CompressorRegistry compressorRegistry;
    private final DecompressorRegistry decompressorRegistry;
    @Nullable
    private final ManagedChannelServiceConfig defaultServiceConfig;
    private final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.Listener delayedTransportListener;
    private final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private boolean fullStreamDecompression;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    private ResolutionState lastResolutionState;
    private ManagedChannelServiceConfig lastServiceConfig;
    @Nullable
    private LbHelperImpl lbHelper;
    private final AutoConfiguredLoadBalancerFactory loadBalancerFactory;
    private final InternalLogId logId;
    private final boolean lookUpServiceConfig;
    private final int maxTraceEvents;
    private NameResolver nameResolver;
    private final NameResolver.Args nameResolverArgs;
    @Nullable
    private BackoffPolicy nameResolverBackoffPolicy;
    private final NameResolver.Factory nameResolverFactory;
    private final NameResolverRegistry nameResolverRegistry;
    private boolean nameResolverStarted;
    private final ExecutorHolder offloadExecutorHolder;
    private final Set<OobChannel> oobChannels;
    private final ClientTransportFactory oobTransportFactory;
    @Nullable
    private final ChannelCredentials originalChannelCreds;
    private final ClientTransportFactory originalTransportFactory;
    private boolean panicMode;
    @Nullable
    private Collection<RealChannel.PendingCall<?, ?>> pendingCalls;
    private final Object pendingCallsInUseObject;
    private final long perRpcBufferLimit;
    private final RealChannel realChannel;
    private final boolean retryEnabled;
    private final RestrictedScheduledExecutor scheduledExecutor;
    @Nullable
    private SynchronizationContext.ScheduledHandle scheduledNameResolverRefresh;
    private boolean serviceConfigUpdated;
    private final AtomicBoolean shutdown;
    private boolean shutdownNowed;
    private final Supplier<Stopwatch> stopwatchSupplier;
    @Nullable
    private volatile LoadBalancer.SubchannelPicker subchannelPicker;
    private final Set<InternalSubchannel> subchannels;
    final SynchronizationContext syncContext;
    private final String target;
    private volatile boolean terminated;
    private final CountDownLatch terminatedLatch;
    private boolean terminating;
    private final TimeProvider timeProvider;
    private final ClientTransportFactory transportFactory;
    private final ClientCallImpl.ClientStreamProvider transportProvider;
    private final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry;
    @Nullable
    private final String userAgent;
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    private static final ManagedChannelServiceConfig EMPTY_SERVICE_CONFIG = ManagedChannelServiceConfig.empty();
    private static final InternalConfigSelector INITIAL_PENDING_SELECTOR = new InternalConfigSelector() {
        @Override
        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            throw new IllegalStateException("Resolution is pending");
        }
    };
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        @Override
        public void cancel(String str, Throwable th) {
        }

        @Override
        public void halfClose() {
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void request(int i) {
        }

        @Override
        public void sendMessage(Object obj) {
        }

        @Override
        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };

    public enum ResolutionState {
        NO_RESOLUTION,
        SUCCESS,
        ERROR
    }

    @Override
    public InternalLogId getLogId() {
        return this.logId;
    }

    boolean isInPanicMode() {
        return this.panicMode;
    }

    @Override
    public boolean isTerminated() {
        return this.terminated;
    }

    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel internalSubchannel : this.subchannels) {
                internalSubchannel.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel oobChannel : this.oobChannels) {
                oobChannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    @Override
    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                channelCallTracer.updateBuilder(builder);
                channelTracer.updateBuilder(builder);
                builder.setTarget(target).setState(channelStateManager.getState());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(subchannels);
                arrayList.addAll(oobChannels);
                builder.setSubchannels(arrayList);
                create.set(builder.build());
            }
        });
        return create;
    }

    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        @Override
        public void run() {
            enterIdleMode();
        }
    }

    public void shutdownNameResolverAndLoadBalancer(boolean z) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (z) {
            Preconditions.checkState(this.nameResolverStarted, "nameResolver is not started");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolverStarted = false;
            if (z) {
                this.nameResolver = getNameResolver(this.target, this.authorityOverride, this.nameResolverFactory, this.nameResolverArgs);
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.shutdown.get() || this.panicMode) {
            return;
        }
        if (this.inUseStateAggregator.isInUse()) {
            cancelIdleTimer(false);
        } else {
            rescheduleIdleTimer();
        }
        if (this.lbHelper != null) {
            return;
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Exiting idle mode");
        LbHelperImpl lbHelperImpl = new LbHelperImpl();
        lbHelperImpl.lb = this.loadBalancerFactory.newLoadBalancer(lbHelperImpl);
        this.lbHelper = lbHelperImpl;
        this.nameResolver.start((NameResolver.Listener2) new NameResolverListener(lbHelperImpl, this.nameResolver));
        this.nameResolverStarted = true;
    }

    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess(null);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.isInUse()) {
            exitIdleMode();
        }
    }

    public void cancelIdleTimer(boolean z) {
        this.idleTimer.cancel(z);
    }

    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j == -1) {
            return;
        }
        this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
    }

    public class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        @Override
        public void run() {
            scheduledNameResolverRefresh = null;
            refreshNameResolution();
        }
    }

    private void cancelNameResolverBackoff() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduledNameResolverRefresh;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.scheduledNameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    public void refreshAndResetNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        cancelNameResolverBackoff();
        refreshNameResolution();
    }

    public void refreshNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.nameResolverStarted) {
            this.nameResolver.refresh();
        }
    }

    private final class ChannelStreamProvider implements ClientCallImpl.ClientStreamProvider {
        private ChannelStreamProvider() {
        }

        public ClientTransport getTransport(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            LoadBalancer.SubchannelPicker subchannelPicker = subchannelPicker;
            if (shutdown.get()) {
                return delayedTransport;
            }
            if (subchannelPicker == null) {
                syncContext.execute(new Runnable() {
                    @Override
                    public void run() {
                        exitIdleMode();
                    }
                });
                return delayedTransport;
            }
            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(subchannelPicker.pickSubchannel(pickSubchannelArgs), pickSubchannelArgs.getCallOptions().isWaitForReady());
            return transportFromPickResult != null ? transportFromPickResult : delayedTransport;
        }

        @Override
        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            if (retryEnabled) {
                RetriableStream.Throttle retryThrottling = lastServiceConfig.getRetryThrottling();
                ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
                return new RetriableStream<ReqT>(methodDescriptor, metadata, callOptions, methodInfo == null ? null : methodInfo.retryPolicy, methodInfo == null ? null : methodInfo.hedgingPolicy, retryThrottling, context) {
                    final CallOptions val$callOptions;
                    final Context val$context;
                    final Metadata val$headers;
                    final HedgingPolicy val$hedgingPolicy;
                    final MethodDescriptor val$method;
                    final RetryPolicy val$retryPolicy;
                    final RetriableStream.Throttle val$throttle;

                    {
                        super(methodDescriptor, metadata, channelBufferUsed, perRpcBufferLimit, channelBufferLimit, getCallExecutor(callOptions), transportFactory.getScheduledExecutorService(), r20, r21, retryThrottling);
                        this.val$method = methodDescriptor;
                        this.val$headers = metadata;
                        this.val$callOptions = callOptions;
                        this.val$retryPolicy = r20;
                        this.val$hedgingPolicy = r21;
                        this.val$throttle = retryThrottling;
                        this.val$context = context;
                    }

                    @Override
                    Status prestart() {
                        return uncommittedRetriableStreamsRegistry.add(this);
                    }

                    @Override
                    void postCommit() {
                        uncommittedRetriableStreamsRegistry.remove(this);
                    }

                    @Override
                    ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata2) {
                        CallOptions withStreamTracerFactory = this.val$callOptions.withStreamTracerFactory(factory);
                        ClientTransport transport = ChannelStreamProvider.this.getTransport(new PickSubchannelArgsImpl(this.val$method, metadata2, withStreamTracerFactory));
                        Context attach = this.val$context.attach();
                        try {
                            return transport.newStream(this.val$method, metadata2, withStreamTracerFactory);
                        } finally {
                            this.val$context.detach(attach);
                        }
                    }
                };
            }
            ClientTransport transport = getTransport(new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions));
            Context attach = context.attach();
            try {
                return transport.newStream(methodDescriptor, metadata, callOptions);
            } finally {
                context.detach(attach);
            }
        }
    }

    public ManagedChannelImpl(ManagedChannelImplBuilder managedChannelImplBuilder, ClientTransportFactory clientTransportFactory, BackoffPolicy.Provider provider, ObjectPool<? extends Executor> objectPool, Supplier<Stopwatch> supplier, List<ClientInterceptor> list, final TimeProvider timeProvider) {
        1 r2;
        SynchronizationContext synchronizationContext = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable th) {
                Logger logger2 = ManagedChannelImpl.logger;
                Level level = Level.SEVERE;
                logger2.log(level, "[" + getLogId() + "] Uncaught exception in the SynchronizationContext. Panic!", th);
                panic(th);
            }
        });
        this.syncContext = synchronizationContext;
        this.channelStateManager = new ConnectivityStateManager();
        this.subchannels = new HashSet(16, 0.75f);
        this.pendingCallsInUseObject = new Object();
        this.oobChannels = new HashSet(1, 0.75f);
        this.uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
        this.shutdown = new AtomicBoolean(false);
        this.terminatedLatch = new CountDownLatch(1);
        this.lastResolutionState = ResolutionState.NO_RESOLUTION;
        this.lastServiceConfig = EMPTY_SERVICE_CONFIG;
        this.serviceConfigUpdated = false;
        this.channelBufferUsed = new RetriableStream.ChannelBufferMeter();
        DelayedTransportListener delayedTransportListener = new DelayedTransportListener();
        this.delayedTransportListener = delayedTransportListener;
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelStreamProvider();
        String str = (String) Preconditions.checkNotNull(managedChannelImplBuilder.target, "target");
        this.target = str;
        InternalLogId allocate = InternalLogId.allocate("Channel", str);
        this.logId = allocate;
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider, "timeProvider");
        ObjectPool<? extends Executor> objectPool2 = (ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder.executorPool, "executorPool");
        this.executorPool = objectPool2;
        Executor executor = (Executor) Preconditions.checkNotNull(objectPool2.getObject(), "executor");
        this.executor = executor;
        this.originalChannelCreds = managedChannelImplBuilder.channelCredentials;
        this.originalTransportFactory = clientTransportFactory;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, managedChannelImplBuilder.callCredentials, executor);
        this.transportFactory = callCredentialsApplyingTransportFactory;
        this.oobTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, null, executor);
        RestrictedScheduledExecutor restrictedScheduledExecutor = new RestrictedScheduledExecutor(callCredentialsApplyingTransportFactory.getScheduledExecutorService());
        this.scheduledExecutor = restrictedScheduledExecutor;
        this.maxTraceEvents = managedChannelImplBuilder.maxTraceEvents;
        int i = managedChannelImplBuilder.maxTraceEvents;
        long currentTimeNanos = timeProvider.currentTimeNanos();
        ChannelTracer channelTracer = new ChannelTracer(allocate, i, currentTimeNanos, "Channel for '" + str + "'");
        this.channelTracer = channelTracer;
        ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer, timeProvider);
        this.channelLogger = channelLoggerImpl;
        ProxyDetector proxyDetector = managedChannelImplBuilder.proxyDetector != null ? managedChannelImplBuilder.proxyDetector : GrpcUtil.DEFAULT_PROXY_DETECTOR;
        boolean z = managedChannelImplBuilder.retryEnabled && !managedChannelImplBuilder.temporarilyDisableRetry;
        this.retryEnabled = z;
        AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = new AutoConfiguredLoadBalancerFactory(managedChannelImplBuilder.defaultLbPolicy);
        this.loadBalancerFactory = autoConfiguredLoadBalancerFactory;
        this.offloadExecutorHolder = new ExecutorHolder((ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder.offloadExecutorPool, "offloadExecutorPool"));
        this.nameResolverRegistry = managedChannelImplBuilder.nameResolverRegistry;
        ScParser scParser = new ScParser(z, managedChannelImplBuilder.maxRetryAttempts, managedChannelImplBuilder.maxHedgedAttempts, autoConfiguredLoadBalancerFactory);
        NameResolver.Args build = NameResolver.Args.newBuilder().setDefaultPort(managedChannelImplBuilder.getDefaultPort()).setProxyDetector(proxyDetector).setSynchronizationContext(synchronizationContext).setScheduledExecutorService(restrictedScheduledExecutor).setServiceConfigParser(scParser).setChannelLogger(channelLoggerImpl).setOffloadExecutor(new Executor() {
            @Override
            public void execute(Runnable runnable) {
                offloadExecutorHolder.getExecutor().execute(runnable);
            }
        }).build();
        this.nameResolverArgs = build;
        String str2 = managedChannelImplBuilder.authorityOverride;
        this.authorityOverride = str2;
        NameResolver.Factory factory = managedChannelImplBuilder.nameResolverFactory;
        this.nameResolverFactory = factory;
        this.nameResolver = getNameResolver(str, str2, factory, build);
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool);
        DelayedClientTransport delayedClientTransport = new DelayedClientTransport(executor, synchronizationContext);
        this.delayedTransport = delayedClientTransport;
        delayedClientTransport.start(delayedTransportListener);
        this.backoffPolicyProvider = provider;
        if (managedChannelImplBuilder.defaultServiceConfig != null) {
            NameResolver.ConfigOrError parseServiceConfig = scParser.parseServiceConfig(managedChannelImplBuilder.defaultServiceConfig);
            Preconditions.checkState(parseServiceConfig.getError() == null, "Default config is invalid: %s", parseServiceConfig.getError());
            ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) parseServiceConfig.getConfig();
            this.defaultServiceConfig = managedChannelServiceConfig;
            this.lastServiceConfig = managedChannelServiceConfig;
            r2 = null;
        } else {
            r2 = null;
            this.defaultServiceConfig = null;
        }
        boolean z2 = managedChannelImplBuilder.lookUpServiceConfig;
        this.lookUpServiceConfig = z2;
        RealChannel realChannel = new RealChannel(this.nameResolver.getServiceAuthority());
        this.realChannel = realChannel;
        this.interceptorChannel = ClientInterceptors.intercept(managedChannelImplBuilder.binlog != null ? managedChannelImplBuilder.binlog.wrapChannel(realChannel) : realChannel, list);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        if (managedChannelImplBuilder.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        } else {
            Preconditions.checkArgument(managedChannelImplBuilder.idleTimeoutMillis >= ManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS, "invalid idleTimeoutMillis %s", managedChannelImplBuilder.idleTimeoutMillis);
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), synchronizationContext, callCredentialsApplyingTransportFactory.getScheduledExecutorService(), supplier.get());
        this.fullStreamDecompression = managedChannelImplBuilder.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder.compressorRegistry, "compressorRegistry");
        this.userAgent = managedChannelImplBuilder.userAgent;
        this.channelBufferLimit = managedChannelImplBuilder.retryBufferSize;
        this.perRpcBufferLimit = managedChannelImplBuilder.perRpcBufferLimit;
        CallTracer.Factory factory2 = new CallTracer.Factory() {
            @Override
            public CallTracer create() {
                return new CallTracer(timeProvider);
            }
        };
        this.callTracerFactory = factory2;
        this.channelCallTracer = factory2.create();
        InternalChannelz internalChannelz = (InternalChannelz) Preconditions.checkNotNull(managedChannelImplBuilder.channelz);
        this.channelz = internalChannelz;
        internalChannelz.addRootChannel(this);
        if (z2) {
            return;
        }
        if (this.defaultServiceConfig != null) {
            channelLoggerImpl.log(ChannelLogger.ChannelLogLevel.INFO, "Service config look-up disabled, using default service config");
        }
        this.serviceConfigUpdated = true;
    }

    private static NameResolver getNameResolver(String str, NameResolver.Factory factory, NameResolver.Args args) {
        URI uri;
        NameResolver newNameResolver;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri == null || (newNameResolver = factory.newNameResolver(uri, args)) == null) {
            String str2 = "";
            if (!URI_PATTERN.matcher(str).matches()) {
                try {
                    NameResolver newNameResolver2 = factory.newNameResolver(new URI(factory.getDefaultScheme(), "", "/" + str, null), args);
                    if (newNameResolver2 != null) {
                        return newNameResolver2;
                    }
                } catch (URISyntaxException e2) {
                    throw new IllegalArgumentException(e2);
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = str;
            if (sb.length() > 0) {
                str2 = " (" + ((Object) sb) + ")";
            }
            objArr[1] = str2;
            throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
        }
        return newNameResolver;
    }

    static NameResolver getNameResolver(String str, @Nullable final String str2, NameResolver.Factory factory, NameResolver.Args args) {
        NameResolver nameResolver = getNameResolver(str, factory, args);
        return str2 == null ? nameResolver : new ForwardingNameResolver(nameResolver) {
            @Override
            public String getServiceAuthority() {
                return str2;
            }
        };
    }

    InternalConfigSelector getConfigSelector() {
        return (InternalConfigSelector) this.realChannel.configSelector.get();
    }

    @Override
    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdown() called");
        if (this.shutdown.compareAndSet(false, true)) {
            this.syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                    channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
                }
            });
            this.realChannel.shutdown();
            this.syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    cancelIdleTimer(true);
                }
            });
            return this;
        }
        return this;
    }

    @Override
    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.realChannel.shutdownNow();
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                if (shutdownNowed) {
                    return;
                }
                shutdownNowed = true;
                maybeShutdownNowSubchannels();
            }
        });
        return this;
    }

    void panic(Throwable th) {
        if (this.panicMode) {
            return;
        }
        this.panicMode = true;
        cancelIdleTimer(true);
        shutdownNameResolverAndLoadBalancer(false);
        updateSubchannelPicker(new LoadBalancer.SubchannelPicker(th) {
            private final LoadBalancer.PickResult panicPickResult;
            final Throwable val$t;

            @Override
            public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                return this.panicPickResult;
            }

            {
                this.val$t = th;
                this.panicPickResult = LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(th));
            }

            public String toString() {
                return MoreObjects.toStringHelper((Class<?>) 1PanicSubchannelPicker.class).add("panicPickResult", this.panicPickResult).toString();
            }
        });
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
        this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
    }

    public void updateSubchannelPicker(LoadBalancer.SubchannelPicker subchannelPicker) {
        this.subchannelPicker = subchannelPicker;
        this.delayedTransport.reprocess(subchannelPicker);
    }

    @Override
    public boolean isShutdown() {
        return this.shutdown.get();
    }

    @Override
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    @Override
    public String authority() {
        return this.interceptorChannel.authority();
    }

    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor = callOptions.getExecutor();
        return executor == null ? this.executor : executor;
    }

    public class RealChannel extends Channel {
        private final String authority;
        private final Channel clientCallImplChannel;
        private final AtomicReference<InternalConfigSelector> configSelector;

        @Override
        public String authority() {
            return this.authority;
        }

        private RealChannel(String str) {
            this.configSelector = new AtomicReference<>(ManagedChannelImpl.INITIAL_PENDING_SELECTOR);
            this.clientCallImplChannel = new Channel() {
                @Override
                public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
                    return new ClientCallImpl(methodDescriptor, getCallExecutor(callOptions), callOptions, transportProvider, terminated ? null : transportFactory.getScheduledExecutorService(), channelCallTracer, null).setFullStreamDecompression(fullStreamDecompression).setDecompressorRegistry(decompressorRegistry).setCompressorRegistry(compressorRegistry);
                }

                @Override
                public String authority() {
                    return RealChannel.this.authority;
                }
            };
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(methodDescriptor, callOptions);
            }
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    exitIdleMode();
                }
            });
            if (this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                if (shutdown.get()) {
                    return new ClientCall<ReqT, RespT>() {
                        @Override
                        public void cancel(@Nullable String str, @Nullable Throwable th) {
                        }

                        @Override
                        public void halfClose() {
                        }

                        @Override
                        public void request(int i) {
                        }

                        @Override
                        public void sendMessage(ReqT reqt) {
                        }

                        @Override
                        public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                            listener.onClose(ManagedChannelImpl.SHUTDOWN_STATUS, new Metadata());
                        }
                    };
                }
                final PendingCall pendingCall = new PendingCall(Context.current(), methodDescriptor, callOptions);
                syncContext.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                            if (pendingCalls == null) {
                                pendingCalls = new LinkedHashSet();
                                inUseStateAggregator.updateObjectInUse(pendingCallsInUseObject, true);
                            }
                            pendingCalls.add(pendingCall);
                            return;
                        }
                        pendingCall.reprocess();
                    }
                });
                return pendingCall;
            }
            return newClientCall(methodDescriptor, callOptions);
        }

        void updateConfigSelector(@Nullable InternalConfigSelector internalConfigSelector) {
            InternalConfigSelector internalConfigSelector2 = this.configSelector.get();
            this.configSelector.set(internalConfigSelector);
            if (internalConfigSelector2 != ManagedChannelImpl.INITIAL_PENDING_SELECTOR || pendingCalls == null) {
                return;
            }
            for (PendingCall pendingCall : pendingCalls) {
                pendingCall.reprocess();
            }
        }

        void onConfigError() {
            if (this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                updateConfigSelector(null);
            }
        }

        void shutdown() {
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    if (pendingCalls == null) {
                        if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                            RealChannel.this.configSelector.set(null);
                        }
                        uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                }
            });
        }

        void shutdownNow() {
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        RealChannel.this.configSelector.set(null);
                    }
                    if (pendingCalls != null) {
                        for (PendingCall pendingCall : pendingCalls) {
                            pendingCall.cancel("Channel is forcefully shutdown", (Throwable) null);
                        }
                    }
                    uncommittedRetriableStreamsRegistry.onShutdownNow(ManagedChannelImpl.SHUTDOWN_NOW_STATUS);
                }
            });
        }

        public final class PendingCall<ReqT, RespT> extends DelayedClientCall<ReqT, RespT> {
            final CallOptions callOptions;
            final Context context;
            final MethodDescriptor<ReqT, RespT> method;

            PendingCall(Context context, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
                super(getCallExecutor(callOptions), scheduledExecutor, callOptions.getDeadline());
                this.context = context;
                this.method = methodDescriptor;
                this.callOptions = callOptions;
            }

            void reprocess() {
                getCallExecutor(this.callOptions).execute(new Runnable() {
                    @Override
                    public void run() {
                        Context attach = PendingCall.this.context.attach();
                        try {
                            ClientCall<ReqT, RespT> newClientCall = RealChannel.this.newClientCall(PendingCall.this.method, PendingCall.this.callOptions);
                            PendingCall.this.context.detach(attach);
                            PendingCall.this.setCall(newClientCall);
                            syncContext.execute(new PendingCallRemoval());
                        } catch (Throwable th) {
                            PendingCall.this.context.detach(attach);
                            throw th;
                        }
                    }
                });
            }

            @Override
            public void callCancelled() {
                super.callCancelled();
                syncContext.execute(new PendingCallRemoval());
            }

            final class PendingCallRemoval implements Runnable {
                PendingCallRemoval() {
                }

                @Override
                public void run() {
                    if (pendingCalls != null) {
                        pendingCalls.remove(PendingCall.this);
                        if (pendingCalls.isEmpty()) {
                            inUseStateAggregator.updateObjectInUse(pendingCallsInUseObject, false);
                            pendingCalls = null;
                            if (shutdown.get()) {
                                uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                            }
                        }
                    }
                }
            }
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newClientCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            InternalConfigSelector internalConfigSelector = this.configSelector.get();
            if (internalConfigSelector == null) {
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            if (internalConfigSelector instanceof ManagedChannelServiceConfig.ServiceConfigConvertedSelector) {
                ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig.ServiceConfigConvertedSelector) internalConfigSelector).config.getMethodConfig(methodDescriptor);
                if (methodConfig != null) {
                    callOptions = callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
                }
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            return new ConfigSelectingClientCall(internalConfigSelector, this.clientCallImplChannel, executor, methodDescriptor, callOptions);
        }
    }

    public static final class ConfigSelectingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final Executor callExecutor;
        private CallOptions callOptions;
        private final Channel channel;
        private final InternalConfigSelector configSelector;
        private final Context context;
        private ClientCall<ReqT, RespT> delegate;
        private final MethodDescriptor<ReqT, RespT> method;

        @Override
        protected ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        ConfigSelectingClientCall(InternalConfigSelector internalConfigSelector, Channel channel, Executor executor, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            this.configSelector = internalConfigSelector;
            this.channel = channel;
            this.method = methodDescriptor;
            executor = callOptions.getExecutor() != null ? callOptions.getExecutor() : executor;
            this.callExecutor = executor;
            this.callOptions = callOptions.withExecutor(executor);
            this.context = Context.current();
        }

        @Override
        public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
            InternalConfigSelector.Result selectConfig = this.configSelector.selectConfig(new PickSubchannelArgsImpl(this.method, metadata, this.callOptions));
            Status status = selectConfig.getStatus();
            if (!status.isOk()) {
                executeCloseObserverInContext(listener, status);
                this.delegate = ManagedChannelImpl.NOOP_CALL;
                return;
            }
            ClientInterceptor interceptor = selectConfig.getInterceptor();
            ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig) selectConfig.getConfig()).getMethodConfig(this.method);
            if (methodConfig != null) {
                this.callOptions = this.callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
            }
            if (interceptor != null) {
                this.delegate = interceptor.interceptCall(this.method, this.callOptions, this.channel);
            } else {
                this.delegate = this.channel.newCall(this.method, this.callOptions);
            }
            this.delegate.start(listener, metadata);
        }

        private void executeCloseObserverInContext(final ClientCall.Listener<RespT> listener, final Status status) {
            this.callExecutor.execute(new ContextRunnable() {
                {
                    super(ConfigSelectingClientCall.this.context);
                }

                @Override
                public void runInContext() {
                    listener.onClose(status, new Metadata());
                }
            });
        }

        @Override
        public void cancel(@Nullable String str, @Nullable Throwable th) {
            ClientCall<ReqT, RespT> clientCall = this.delegate;
            if (clientCall != null) {
                clientCall.cancel(str, th);
            }
        }
    }

    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.offloadExecutorHolder.release();
            this.transportFactory.close();
            this.terminated = true;
            this.terminatedLatch.countDown();
        }
    }

    public void handleInternalSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            refreshAndResetNameResolution();
        }
    }

    @Override
    public ConnectivityState getState(boolean z) {
        ConnectivityState state = this.channelStateManager.getState();
        if (z && state == ConnectivityState.IDLE) {
            this.syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    exitIdleMode();
                    if (subchannelPicker != null) {
                        subchannelPicker.requestConnection();
                    }
                    if (lbHelper != null) {
                        lbHelper.lb.requestConnection();
                    }
                }
            });
        }
        return state;
    }

    @Override
    public void notifyWhenStateChanged(final ConnectivityState connectivityState, final Runnable runnable) {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                channelStateManager.notifyWhenStateChanged(runnable, executor, connectivityState);
            }
        });
    }

    @Override
    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                if (shutdown.get()) {
                    return;
                }
                if (scheduledNameResolverRefresh != null && scheduledNameResolverRefresh.isPending()) {
                    Preconditions.checkState(nameResolverStarted, "name resolver must be started");
                    refreshAndResetNameResolution();
                }
                for (InternalSubchannel internalSubchannel : subchannels) {
                    internalSubchannel.resetConnectBackoff();
                }
                for (OobChannel oobChannel : oobChannels) {
                    oobChannel.resetConnectBackoff();
                }
            }
        });
    }

    @Override
    public void enterIdle() {
        this.syncContext.execute(new Runnable() {
            @Override
            public void run() {
                if (shutdown.get() || lbHelper == null) {
                    return;
                }
                cancelIdleTimer(false);
                enterIdleMode();
            }
        });
    }

    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        Status shutdownStatus;
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        void onShutdown(Status status) {
            synchronized (this.lock) {
                if (this.shutdownStatus != null) {
                    return;
                }
                this.shutdownStatus = status;
                boolean isEmpty = this.uncommittedRetriableStreams.isEmpty();
                if (isEmpty) {
                    delayedTransport.shutdown(status);
                }
            }
        }

        void onShutdownNow(Status status) {
            ArrayList<ClientStream> arrayList;
            onShutdown(status);
            synchronized (this.lock) {
                arrayList = new ArrayList(this.uncommittedRetriableStreams);
            }
            for (ClientStream clientStream : arrayList) {
                clientStream.cancel(status);
            }
            delayedTransport.shutdownNow(status);
        }

        @Nullable
        Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                Status status = this.shutdownStatus;
                if (status != null) {
                    return status;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        void remove(RetriableStream<?> retriableStream) {
            Status status;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    status = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                } else {
                    status = null;
                }
            }
            if (status != null) {
                delayedTransport.shutdown(status);
            }
        }
    }

    public final class LbHelperImpl extends LoadBalancer.Helper {
        boolean ignoreRefreshNsCheck;
        AutoConfiguredLoadBalancerFactory.AutoConfiguredLoadBalancer lb;
        boolean nsRefreshedByLb;

        @Override
        public void ignoreRefreshNameResolutionCheck() {
            this.ignoreRefreshNsCheck = true;
        }

        private LbHelperImpl() {
        }

        @Override
        public AbstractSubchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!terminating, "Channel is being terminated");
            return new SubchannelImpl(createSubchannelArgs, this);
        }

        @Override
        public void updateBalancingState(final ConnectivityState connectivityState, final LoadBalancer.SubchannelPicker subchannelPicker) {
            syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkNotNull(connectivityState, "newState");
            Preconditions.checkNotNull(subchannelPicker, "newPicker");
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl != lbHelper) {
                        return;
                    }
                    updateSubchannelPicker(subchannelPicker);
                    if (connectivityState != ConnectivityState.SHUTDOWN) {
                        channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering {0} state with picker: {1}", connectivityState, subchannelPicker);
                        channelStateManager.gotoState(connectivityState);
                    }
                }
            });
        }

        @Override
        public void refreshNameResolution() {
            syncContext.throwIfNotInThisSynchronizationContext();
            this.nsRefreshedByLb = true;
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    refreshAndResetNameResolution();
                }
            });
        }

        @Override
        public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
            return createOobChannel(Collections.singletonList(equivalentAddressGroup), str);
        }

        @Override
        public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
            Preconditions.checkState(!terminated, "Channel is terminated");
            long currentTimeNanos = timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("OobChannel", (String) null);
            InternalLogId allocate2 = InternalLogId.allocate("Subchannel-OOB", str);
            int i = maxTraceEvents;
            ChannelTracer channelTracer = new ChannelTracer(allocate, i, currentTimeNanos, "OobChannel for " + list);
            final OobChannel oobChannel = new OobChannel(str, balancerRpcExecutorPool, oobTransportFactory.getScheduledExecutorService(), syncContext, callTracerFactory.create(), channelTracer, channelz, timeProvider);
            channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child OobChannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setChannelRef(oobChannel).build());
            int i2 = maxTraceEvents;
            ChannelTracer channelTracer2 = new ChannelTracer(allocate2, i2, currentTimeNanos, "Subchannel for " + list);
            InternalSubchannel internalSubchannel = new InternalSubchannel(list, str, userAgent, backoffPolicyProvider, oobTransportFactory, oobTransportFactory.getScheduledExecutorService(), stopwatchSupplier, syncContext, new InternalSubchannel.Callback() {
                @Override
                void onTerminated(InternalSubchannel internalSubchannel2) {
                    oobChannels.remove(oobChannel);
                    channelz.removeSubchannel(internalSubchannel2);
                    oobChannel.handleSubchannelTerminated();
                    maybeTerminateChannel();
                }

                @Override
                void onStateChange(InternalSubchannel internalSubchannel2, ConnectivityStateInfo connectivityStateInfo) {
                    handleInternalSubchannelState(connectivityStateInfo);
                    oobChannel.handleSubchannelStateChange(connectivityStateInfo);
                }
            }, channelz, callTracerFactory.create(), channelTracer2, allocate2, new ChannelLoggerImpl(channelTracer2, timeProvider));
            channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setSubchannelRef(internalSubchannel).build());
            channelz.addSubchannel(oobChannel);
            channelz.addSubchannel(internalSubchannel);
            oobChannel.setSubchannel(internalSubchannel);
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    if (terminating) {
                        oobChannel.shutdown();
                    }
                    if (terminated) {
                        return;
                    }
                    oobChannels.add(oobChannel);
                }
            });
            return oobChannel;
        }

        @Override
        @Deprecated
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
            return createResolvingOobChannelBuilder(str, new DefaultChannelCreds()).overrideAuthority(getAuthority());
        }

        @Override
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str, ChannelCredentials channelCredentials) {
            Preconditions.checkNotNull(channelCredentials, "channelCreds");
            Preconditions.checkState(!terminated, "Channel is terminated");
            return new ForwardingChannelBuilder<1ResolvingOobChannelBuilder>(channelCredentials, str) {
                final ManagedChannelBuilder<?> delegate;
                final ChannelCredentials val$channelCreds;
                final String val$target;

                @Override
                protected ManagedChannelBuilder<?> delegate() {
                    return this.delegate;
                }

                {
                    CallCredentials callCredentials;
                    final ClientTransportFactory clientTransportFactory;
                    this.val$channelCreds = channelCredentials;
                    this.val$target = str;
                    if (channelCredentials instanceof DefaultChannelCreds) {
                        clientTransportFactory = originalTransportFactory;
                        callCredentials = null;
                    } else {
                        ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials = originalTransportFactory.swapChannelCredentials(channelCredentials);
                        if (swapChannelCredentials == null) {
                            this.delegate = Grpc.newChannelBuilder(str, channelCredentials);
                            return;
                        }
                        ClientTransportFactory clientTransportFactory2 = swapChannelCredentials.transportFactory;
                        callCredentials = swapChannelCredentials.callCredentials;
                        clientTransportFactory = clientTransportFactory2;
                    }
                    this.delegate = new ManagedChannelImplBuilder(str, channelCredentials, callCredentials, new ManagedChannelImplBuilder.ClientTransportFactoryBuilder() {
                        @Override
                        public ClientTransportFactory buildClientTransportFactory() {
                            return clientTransportFactory;
                        }
                    }, new ManagedChannelImplBuilder.FixedPortProvider(nameResolverArgs.getDefaultPort()));
                }
            }.nameResolverFactory(nameResolverFactory).executor(executor).offloadExecutor(offloadExecutorHolder.getExecutor()).maxTraceEvents(maxTraceEvents).proxyDetector(nameResolverArgs.getProxyDetector()).userAgent(userAgent);
        }

        @Override
        public ChannelCredentials getUnsafeChannelCredentials() {
            if (originalChannelCreds != null) {
                return originalChannelCreds;
            }
            return new DefaultChannelCreds();
        }

        @Override
        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            updateOobChannelAddresses(managedChannel, Collections.singletonList(equivalentAddressGroup));
        }

        @Override
        public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
            Preconditions.checkArgument(managedChannel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel) managedChannel).updateAddresses(list);
        }

        @Override
        public String getAuthority() {
            return authority();
        }

        @Override
        public SynchronizationContext getSynchronizationContext() {
            return syncContext;
        }

        @Override
        public ScheduledExecutorService getScheduledExecutorService() {
            return scheduledExecutor;
        }

        @Override
        public ChannelLogger getChannelLogger() {
            return channelLogger;
        }

        @Override
        public NameResolver.Args getNameResolverArgs() {
            return nameResolverArgs;
        }

        @Override
        public NameResolverRegistry getNameResolverRegistry() {
            return nameResolverRegistry;
        }

        final class DefaultChannelCreds extends ChannelCredentials {
            @Override
            public ChannelCredentials withoutBearerTokens() {
                return this;
            }

            DefaultChannelCreds() {
            }
        }
    }

    public final class NameResolverListener extends NameResolver.Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        NameResolverListener(LbHelperImpl lbHelperImpl, NameResolver nameResolver) {
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helperImpl");
            this.resolver = (NameResolver) Preconditions.checkNotNull(nameResolver, "resolver");
        }

        @Override
        public void onResult(final NameResolver.ResolutionResult resolutionResult) {
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    ManagedChannelServiceConfig managedChannelServiceConfig;
                    List<EquivalentAddressGroup> addresses = resolutionResult.getAddresses();
                    channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", addresses, resolutionResult.getAttributes());
                    if (lastResolutionState != ResolutionState.SUCCESS) {
                        channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Address resolved: {0}", addresses);
                        lastResolutionState = ResolutionState.SUCCESS;
                    }
                    nameResolverBackoffPolicy = null;
                    NameResolver.ConfigOrError serviceConfig = resolutionResult.getServiceConfig();
                    InternalConfigSelector internalConfigSelector = (InternalConfigSelector) resolutionResult.getAttributes().get(InternalConfigSelector.KEY);
                    ManagedChannelServiceConfig managedChannelServiceConfig2 = (serviceConfig == null || serviceConfig.getConfig() == null) ? null : (ManagedChannelServiceConfig) serviceConfig.getConfig();
                    Status error = serviceConfig != null ? serviceConfig.getError() : null;
                    if (!lookUpServiceConfig) {
                        if (managedChannelServiceConfig2 != null) {
                            channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Service config from name resolver discarded by channel settings");
                        }
                        managedChannelServiceConfig = defaultServiceConfig == null ? ManagedChannelImpl.EMPTY_SERVICE_CONFIG : defaultServiceConfig;
                        if (internalConfigSelector != null) {
                            channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Config selector from name resolver discarded by channel settings");
                        }
                        realChannel.updateConfigSelector(managedChannelServiceConfig.getDefaultConfigSelector());
                    } else {
                        if (managedChannelServiceConfig2 != null) {
                            if (internalConfigSelector != null) {
                                realChannel.updateConfigSelector(internalConfigSelector);
                                if (managedChannelServiceConfig2.getDefaultConfigSelector() != null) {
                                    channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Method configs in service config will be discarded due to presence ofconfig-selector");
                                }
                            } else {
                                realChannel.updateConfigSelector(managedChannelServiceConfig2.getDefaultConfigSelector());
                            }
                        } else if (defaultServiceConfig != null) {
                            managedChannelServiceConfig2 = defaultServiceConfig;
                            realChannel.updateConfigSelector(managedChannelServiceConfig2.getDefaultConfigSelector());
                            channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Received no service config, using default service config");
                        } else if (error != null) {
                            if (!serviceConfigUpdated) {
                                channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Fallback to error due to invalid first service config without default config");
                                NameResolverListener.this.onError(serviceConfig.getError());
                                return;
                            }
                            managedChannelServiceConfig2 = lastServiceConfig;
                        } else {
                            managedChannelServiceConfig2 = ManagedChannelImpl.EMPTY_SERVICE_CONFIG;
                            realChannel.updateConfigSelector(null);
                        }
                        if (!managedChannelServiceConfig2.equals(lastServiceConfig)) {
                            ChannelLogger channelLogger = channelLogger;
                            ChannelLogger.ChannelLogLevel channelLogLevel = ChannelLogger.ChannelLogLevel.INFO;
                            Object[] objArr = new Object[1];
                            objArr[0] = managedChannelServiceConfig2 == ManagedChannelImpl.EMPTY_SERVICE_CONFIG ? " to empty" : "";
                            channelLogger.log(channelLogLevel, "Service config changed{0}", objArr);
                            lastServiceConfig = managedChannelServiceConfig2;
                        }
                        try {
                            serviceConfigUpdated = true;
                        } catch (RuntimeException e) {
                            Logger logger = ManagedChannelImpl.logger;
                            Level level = Level.WARNING;
                            logger.log(level, "[" + getLogId() + "] Unexpected exception from parsing service config", (Throwable) e);
                        }
                        managedChannelServiceConfig = managedChannelServiceConfig2;
                    }
                    Attributes attributes = resolutionResult.getAttributes();
                    if (NameResolverListener.this.helper == lbHelper) {
                        Attributes.Builder discard = attributes.toBuilder().discard(InternalConfigSelector.KEY);
                        Map<String, ?> healthCheckingConfig = managedChannelServiceConfig.getHealthCheckingConfig();
                        if (healthCheckingConfig != null) {
                            discard.set(LoadBalancer.ATTR_HEALTH_CHECKING_CONFIG, healthCheckingConfig).build();
                        }
                        Status tryHandleResolvedAddresses = NameResolverListener.this.helper.lb.tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(addresses).setAttributes(discard.build()).setLoadBalancingPolicyConfig(managedChannelServiceConfig.getLoadBalancingConfig()).build());
                        if (tryHandleResolvedAddresses.isOk()) {
                            return;
                        }
                        NameResolverListener nameResolverListener = NameResolverListener.this;
                        nameResolverListener.handleErrorInSyncContext(tryHandleResolvedAddresses.augmentDescription(NameResolverListener.this.resolver + " was used"));
                    }
                }
            });
        }

        @Override
        public void onError(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "the error status must not be OK");
            syncContext.execute(new Runnable() {
                @Override
                public void run() {
                    NameResolverListener.this.handleErrorInSyncContext(status);
                }
            });
        }

        public void handleErrorInSyncContext(Status status) {
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{getLogId(), status});
            realChannel.onConfigError();
            if (lastResolutionState != ResolutionState.ERROR) {
                channelLogger.log(ChannelLogger.ChannelLogLevel.WARNING, "Failed to resolve name: {0}", status);
                lastResolutionState = ResolutionState.ERROR;
            }
            if (this.helper != lbHelper) {
                return;
            }
            this.helper.lb.handleNameResolutionError(status);
            scheduleExponentialBackOffInSyncContext();
        }

        private void scheduleExponentialBackOffInSyncContext() {
            if (scheduledNameResolverRefresh == null || !scheduledNameResolverRefresh.isPending()) {
                if (nameResolverBackoffPolicy == null) {
                    ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                    managedChannelImpl.nameResolverBackoffPolicy = managedChannelImpl.backoffPolicyProvider.get();
                }
                long nextBackoffNanos = nameResolverBackoffPolicy.nextBackoffNanos();
                channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(nextBackoffNanos));
                ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                managedChannelImpl2.scheduledNameResolverRefresh = managedChannelImpl2.syncContext.schedule(new DelayedNameResolverRefresh(), nextBackoffNanos, TimeUnit.NANOSECONDS, transportFactory.getScheduledExecutorService());
            }
        }
    }

    public final class SubchannelImpl extends AbstractSubchannel {
        List<EquivalentAddressGroup> addressGroups;
        final LoadBalancer.CreateSubchannelArgs args;
        SynchronizationContext.ScheduledHandle delayedShutdownTask;
        final LbHelperImpl helper;
        boolean shutdown;
        boolean started;
        InternalSubchannel subchannel;
        final InternalLogId subchannelLogId;
        final ChannelLoggerImpl subchannelLogger;
        final ChannelTracer subchannelTracer;

        @Override
        public ChannelLogger getChannelLogger() {
            return this.subchannelLogger;
        }

        SubchannelImpl(LoadBalancer.CreateSubchannelArgs createSubchannelArgs, LbHelperImpl lbHelperImpl) {
            this.addressGroups = createSubchannelArgs.getAddresses();
            if (authorityOverride != null) {
                createSubchannelArgs = createSubchannelArgs.toBuilder().setAddresses(stripOverrideAuthorityAttributes(createSubchannelArgs.getAddresses())).build();
            }
            this.args = (LoadBalancer.CreateSubchannelArgs) Preconditions.checkNotNull(createSubchannelArgs, "args");
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helper");
            InternalLogId allocate = InternalLogId.allocate("Subchannel", authority());
            this.subchannelLogId = allocate;
            int i = maxTraceEvents;
            long currentTimeNanos = timeProvider.currentTimeNanos();
            ChannelTracer channelTracer = new ChannelTracer(allocate, i, currentTimeNanos, "Subchannel for " + createSubchannelArgs.getAddresses());
            this.subchannelTracer = channelTracer;
            this.subchannelLogger = new ChannelLoggerImpl(channelTracer, timeProvider);
        }

        @Override
        public void start(final LoadBalancer.SubchannelStateListener subchannelStateListener) {
            syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!this.started, "already started");
            Preconditions.checkState(!this.shutdown, "already shutdown");
            Preconditions.checkState(!terminating, "Channel is being terminated");
            this.started = true;
            InternalSubchannel internalSubchannel = new InternalSubchannel(this.args.getAddresses(), authority(), userAgent, backoffPolicyProvider, transportFactory, transportFactory.getScheduledExecutorService(), stopwatchSupplier, syncContext, new InternalSubchannel.Callback() {
                @Override
                void onTerminated(InternalSubchannel internalSubchannel2) {
                    subchannels.remove(internalSubchannel2);
                    channelz.removeSubchannel(internalSubchannel2);
                    maybeTerminateChannel();
                }

                @Override
                void onStateChange(InternalSubchannel internalSubchannel2, ConnectivityStateInfo connectivityStateInfo) {
                    Preconditions.checkState(subchannelStateListener != null, "listener is null");
                    subchannelStateListener.onSubchannelState(connectivityStateInfo);
                    if ((connectivityStateInfo.getState() != ConnectivityState.TRANSIENT_FAILURE && connectivityStateInfo.getState() != ConnectivityState.IDLE) || SubchannelImpl.this.helper.ignoreRefreshNsCheck || SubchannelImpl.this.helper.nsRefreshedByLb) {
                        return;
                    }
                    ManagedChannelImpl.logger.log(Level.WARNING, "LoadBalancer should call Helper.refreshNameResolution() to refresh name resolution if subchannel state becomes TRANSIENT_FAILURE or IDLE. This will no longer happen automatically in the future releases");
                    refreshAndResetNameResolution();
                    SubchannelImpl.this.helper.nsRefreshedByLb = true;
                }

                @Override
                void onInUse(InternalSubchannel internalSubchannel2) {
                    inUseStateAggregator.updateObjectInUse(internalSubchannel2, true);
                }

                @Override
                void onNotInUse(InternalSubchannel internalSubchannel2) {
                    inUseStateAggregator.updateObjectInUse(internalSubchannel2, false);
                }
            }, channelz, callTracerFactory.create(), this.subchannelTracer, this.subchannelLogId, this.subchannelLogger);
            channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel started").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(timeProvider.currentTimeNanos()).setSubchannelRef(internalSubchannel).build());
            this.subchannel = internalSubchannel;
            channelz.addSubchannel(internalSubchannel);
            subchannels.add(internalSubchannel);
        }

        @Override
        InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel() {
            Preconditions.checkState(this.started, "not started");
            return this.subchannel;
        }

        @Override
        public void shutdown() {
            SynchronizationContext.ScheduledHandle scheduledHandle;
            syncContext.throwIfNotInThisSynchronizationContext();
            if (this.subchannel == null) {
                this.shutdown = true;
                return;
            }
            if (!this.shutdown) {
                this.shutdown = true;
            } else if (!terminating || (scheduledHandle = this.delayedShutdownTask) == null) {
                return;
            } else {
                scheduledHandle.cancel();
                this.delayedShutdownTask = null;
            }
            if (!terminating) {
                this.delayedShutdownTask = syncContext.schedule(new LogExceptionRunnable(new Runnable() {
                    @Override
                    public void run() {
                        SubchannelImpl.this.subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                    }
                }), 5L, TimeUnit.SECONDS, transportFactory.getScheduledExecutorService());
            } else {
                this.subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
            }
        }

        @Override
        public void requestConnection() {
            syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            this.subchannel.obtainActiveTransport();
        }

        @Override
        public List<EquivalentAddressGroup> getAllAddresses() {
            syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            return this.addressGroups;
        }

        @Override
        public Attributes getAttributes() {
            return this.args.getAttributes();
        }

        public String toString() {
            return this.subchannelLogId.toString();
        }

        @Override
        public Channel asChannel() {
            Preconditions.checkState(this.started, "not started");
            return new SubchannelChannel(this.subchannel, balancerRpcExecutorHolder.getExecutor(), transportFactory.getScheduledExecutorService(), callTracerFactory.create(), new AtomicReference(null));
        }

        @Override
        public Object getInternalSubchannel() {
            Preconditions.checkState(this.started, "Subchannel is not started");
            return this.subchannel;
        }

        @Override
        public void updateAddresses(List<EquivalentAddressGroup> list) {
            syncContext.throwIfNotInThisSynchronizationContext();
            this.addressGroups = list;
            if (authorityOverride != null) {
                list = stripOverrideAuthorityAttributes(list);
            }
            this.subchannel.updateAddresses(list);
        }

        private List<EquivalentAddressGroup> stripOverrideAuthorityAttributes(List<EquivalentAddressGroup> list) {
            ArrayList arrayList = new ArrayList();
            for (EquivalentAddressGroup equivalentAddressGroup : list) {
                arrayList.add(new EquivalentAddressGroup(equivalentAddressGroup.getAddresses(), equivalentAddressGroup.getAttributes().toBuilder().discard(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE).build()));
            }
            return Collections.unmodifiableList(arrayList);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("target", this.target).toString();
    }

    private final class DelayedTransportListener implements ManagedClientTransport.Listener {
        @Override
        public void transportReady() {
        }

        private DelayedTransportListener() {
        }

        @Override
        public void transportShutdown(Status status) {
            Preconditions.checkState(shutdown.get(), "Channel must have been shut down");
        }

        @Override
        public void transportInUse(boolean z) {
            inUseStateAggregator.updateObjectInUse(delayedTransport, z);
        }

        @Override
        public void transportTerminated() {
            Preconditions.checkState(shutdown.get(), "Channel must have been shut down");
            terminating = true;
            shutdownNameResolverAndLoadBalancer(false);
            maybeShutdownNowSubchannels();
            maybeTerminateChannel();
        }
    }

    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        @Override
        protected void handleInUse() {
            exitIdleMode();
        }

        @Override
        protected void handleNotInUse() {
            if (shutdown.get()) {
                return;
            }
            rescheduleIdleTimer();
        }
    }

    public static final class ExecutorHolder {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> objectPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        }

        synchronized Executor getExecutor() {
            if (this.executor == null) {
                this.executor = (Executor) Preconditions.checkNotNull(this.pool.getObject(), "%s.getObject()", this.executor);
            }
            return this.executor;
        }

        synchronized void release() {
            Executor executor = this.executor;
            if (executor != null) {
                this.executor = this.pool.returnObject(executor);
            }
        }
    }

    private static final class RestrictedScheduledExecutor implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private RestrictedScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "delegate");
        }

        @Override
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        @Override
        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        @Override
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        @Override
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        @Override
        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.delegate.invokeAll(collection);
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return (T) this.delegate.invokeAny(collection);
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (T) this.delegate.invokeAny(collection, j, timeUnit);
        }

        @Override
        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override
        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override
        public void shutdown() {
            


return;

//throw new UnsupportedOperationException(
Restricted: shutdown() is not allowed");
        }

        @Override
        public List<Runnable> shutdownNow() {
            


return null;

//throw new UnsupportedOperationException(
Restricted: shutdownNow() is not allowed");
        }

        @Override
        public <T> Future<T> submit(Callable<T> callable) {
            return this.delegate.submit(callable);
        }

        @Override
        public Future<?> submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        @Override
        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.delegate.submit(runnable, t);
        }

        @Override
        public void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }
    }

    static final class ScParser extends NameResolver.ServiceConfigParser {
        private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
        private final int maxHedgedAttemptsLimit;
        private final int maxRetryAttemptsLimit;
        private final boolean retryEnabled;

        ScParser(boolean z, int i, int i2, AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory) {
            this.retryEnabled = z;
            this.maxRetryAttemptsLimit = i;
            this.maxHedgedAttemptsLimit = i2;
            this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoConfiguredLoadBalancerFactory, "autoLoadBalancerFactory");
        }

        @Override
        public NameResolver.ConfigOrError parseServiceConfig(Map<String, ?> map) {
            Object config;
            try {
                NameResolver.ConfigOrError parseLoadBalancerPolicy = this.autoLoadBalancerFactory.parseLoadBalancerPolicy(map);
                if (parseLoadBalancerPolicy == null) {
                    config = null;
                } else if (parseLoadBalancerPolicy.getError() != null) {
                    return NameResolver.ConfigOrError.fromError(parseLoadBalancerPolicy.getError());
                } else {
                    config = parseLoadBalancerPolicy.getConfig();
                }
                return NameResolver.ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(map, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, config));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
            }
        }
    }
}
