package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.InternalChannelz;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.ProxyDetector;
import io.grpc.internal.ExponentialBackoffPolicy;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Nullable;
public final class ManagedChannelImplBuilder extends ManagedChannelBuilder<ManagedChannelImplBuilder> {
    private static final long DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES = 1048576;
    private static final long DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES = 16777216;
    private static final String DIRECT_ADDRESS_SCHEME = "directaddress";
    static final long IDLE_MODE_MAX_TIMEOUT_DAYS = 30;
    private boolean authorityCheckerDisabled;
    @Nullable
    String authorityOverride;
    @Nullable
    BinaryLog binlog;
    @Nullable
    final CallCredentials callCredentials;
    private final ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider;
    @Nullable
    final ChannelCredentials channelCredentials;
    InternalChannelz channelz;
    private final ClientTransportFactoryBuilder clientTransportFactoryBuilder;
    CompressorRegistry compressorRegistry;
    DecompressorRegistry decompressorRegistry;
    String defaultLbPolicy;
    @Nullable
    Map<String, ?> defaultServiceConfig;
    @Nullable
    private final SocketAddress directServerAddress;
    ObjectPool<? extends Executor> executorPool;
    boolean fullStreamDecompression;
    long idleTimeoutMillis;
    private final List<ClientInterceptor> interceptors;
    boolean lookUpServiceConfig;
    int maxHedgedAttempts;
    int maxRetryAttempts;
    int maxTraceEvents;
    NameResolver.Factory nameResolverFactory;
    final NameResolverRegistry nameResolverRegistry;
    ObjectPool<? extends Executor> offloadExecutorPool;
    long perRpcBufferLimit;
    @Nullable
    ProxyDetector proxyDetector;
    private boolean recordFinishedRpcs;
    private boolean recordRealTimeMetrics;
    private boolean recordStartedRpcs;
    long retryBufferSize;
    boolean retryEnabled;
    private boolean statsEnabled;
    final String target;
    boolean temporarilyDisableRetry;
    private boolean tracingEnabled;
    @Nullable
    String userAgent;
    private static final Logger log = Logger.getLogger(ManagedChannelImplBuilder.class.getName());
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(30);
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();

    public interface ChannelBuilderDefaultPortProvider {
        int getDefaultPort();
    }

    public interface ClientTransportFactoryBuilder {
        ClientTransportFactory buildClientTransportFactory();
    }

    @Override
    public ManagedChannelImplBuilder compressorRegistry(CompressorRegistry compressorRegistry) {
        if (compressorRegistry != null) {
            this.compressorRegistry = compressorRegistry;
        } else {
            this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        }
        return this;
    }

    @Override
    public ManagedChannelImplBuilder decompressorRegistry(DecompressorRegistry decompressorRegistry) {
        if (decompressorRegistry != null) {
            this.decompressorRegistry = decompressorRegistry;
        } else {
            this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        return this;
    }

    public ManagedChannelImplBuilder disableCheckAuthority() {
        this.authorityCheckerDisabled = true;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder disableRetry() {
        this.retryEnabled = false;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder disableServiceConfigLookUp() {
        this.lookUpServiceConfig = false;
        return this;
    }

    public ManagedChannelImplBuilder enableCheckAuthority() {
        this.authorityCheckerDisabled = false;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder enableFullStreamDecompression() {
        this.fullStreamDecompression = true;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder enableRetry() {
        this.retryEnabled = true;
        this.statsEnabled = false;
        this.tracingEnabled = false;
        return this;
    }

    public ObjectPool<? extends Executor> getOffloadExecutorPool() {
        return this.offloadExecutorPool;
    }

    @Override
    public ManagedChannelImplBuilder maxHedgedAttempts(int i) {
        this.maxHedgedAttempts = i;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder maxRetryAttempts(int i) {
        this.maxRetryAttempts = i;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder proxyDetector(@Nullable ProxyDetector proxyDetector) {
        this.proxyDetector = proxyDetector;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder setBinaryLog(BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return this;
    }

    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    @Override
    public ManagedChannelImplBuilder userAgent(@Nullable String str) {
        this.userAgent = str;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder defaultServiceConfig(@Nullable Map map) {
        return defaultServiceConfig((Map<String, ?>) map);
    }

    @Override
    public ManagedChannelImplBuilder intercept(List list) {
        return intercept((List<ClientInterceptor>) list);
    }

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        


return null;

//throw new UnsupportedOperationException(
ClientTransportFactoryBuilder is required, use a constructor");
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        


return null;

//throw new UnsupportedOperationException(
ClientTransportFactoryBuilder is required, use a constructor");
    }

    public static class UnsupportedClientTransportFactoryBuilder implements ClientTransportFactoryBuilder {
        @Override
        public ClientTransportFactory buildClientTransportFactory() {
            throw new UnsupportedOperationException();
        }
    }

    public static final class FixedPortProvider implements ChannelBuilderDefaultPortProvider {
        private final int port;

        @Override
        public int getDefaultPort() {
            return this.port;
        }

        public FixedPortProvider(int i) {
            this.port = i;
        }
    }

    private static final class ManagedChannelDefaultPortProvider implements ChannelBuilderDefaultPortProvider {
        @Override
        public int getDefaultPort() {
            return GrpcUtil.DEFAULT_PORT_SSL;
        }

        private ManagedChannelDefaultPortProvider() {
        }
    }

    public ManagedChannelImplBuilder(String str, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        this(str, null, null, clientTransportFactoryBuilder, channelBuilderDefaultPortProvider);
    }

    public ManagedChannelImplBuilder(String str, @Nullable ChannelCredentials channelCredentials, @Nullable CallCredentials callCredentials, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        ObjectPool<? extends Executor> objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        this.interceptors = new ArrayList();
        NameResolverRegistry defaultRegistry = NameResolverRegistry.getDefaultRegistry();
        this.nameResolverRegistry = defaultRegistry;
        this.nameResolverFactory = defaultRegistry.asFactory();
        this.defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES;
        this.perRpcBufferLimit = DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES;
        this.retryEnabled = false;
        this.channelz = InternalChannelz.instance();
        this.lookUpServiceConfig = true;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRealTimeMetrics = false;
        this.tracingEnabled = true;
        this.target = (String) Preconditions.checkNotNull(str, "target");
        this.channelCredentials = channelCredentials;
        this.callCredentials = callCredentials;
        this.clientTransportFactoryBuilder = (ClientTransportFactoryBuilder) Preconditions.checkNotNull(clientTransportFactoryBuilder, "clientTransportFactoryBuilder");
        this.directServerAddress = null;
        if (channelBuilderDefaultPortProvider != null) {
            this.channelBuilderDefaultPortProvider = channelBuilderDefaultPortProvider;
        } else {
            this.channelBuilderDefaultPortProvider = new ManagedChannelDefaultPortProvider();
        }
    }

    static String makeTargetStringForDirectAddress(SocketAddress socketAddress) {
        try {
            return new URI(DIRECT_ADDRESS_SCHEME, "", "/" + socketAddress, null).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public ManagedChannelImplBuilder(SocketAddress socketAddress, String str, ClientTransportFactoryBuilder clientTransportFactoryBuilder, @Nullable ChannelBuilderDefaultPortProvider channelBuilderDefaultPortProvider) {
        ObjectPool<? extends Executor> objectPool = DEFAULT_EXECUTOR_POOL;
        this.executorPool = objectPool;
        this.offloadExecutorPool = objectPool;
        this.interceptors = new ArrayList();
        NameResolverRegistry defaultRegistry = NameResolverRegistry.getDefaultRegistry();
        this.nameResolverRegistry = defaultRegistry;
        this.nameResolverFactory = defaultRegistry.asFactory();
        this.defaultLbPolicy = GrpcUtil.DEFAULT_LB_POLICY;
        this.decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        this.compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        this.idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
        this.maxRetryAttempts = 5;
        this.maxHedgedAttempts = 5;
        this.retryBufferSize = DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES;
        this.perRpcBufferLimit = DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES;
        this.retryEnabled = false;
        this.channelz = InternalChannelz.instance();
        this.lookUpServiceConfig = true;
        this.statsEnabled = true;
        this.recordStartedRpcs = true;
        this.recordFinishedRpcs = true;
        this.recordRealTimeMetrics = false;
        this.tracingEnabled = true;
        this.target = makeTargetStringForDirectAddress(socketAddress);
        this.channelCredentials = null;
        this.callCredentials = null;
        this.clientTransportFactoryBuilder = (ClientTransportFactoryBuilder) Preconditions.checkNotNull(clientTransportFactoryBuilder, "clientTransportFactoryBuilder");
        this.directServerAddress = socketAddress;
        this.nameResolverFactory = new DirectAddressNameResolverFactory(socketAddress, str);
        if (channelBuilderDefaultPortProvider != null) {
            this.channelBuilderDefaultPortProvider = channelBuilderDefaultPortProvider;
        } else {
            this.channelBuilderDefaultPortProvider = new ManagedChannelDefaultPortProvider();
        }
    }

    @Override
    public ManagedChannelImplBuilder directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    @Override
    public ManagedChannelImplBuilder executor(Executor executor) {
        if (executor != null) {
            this.executorPool = new FixedObjectPool(executor);
        } else {
            this.executorPool = DEFAULT_EXECUTOR_POOL;
        }
        return this;
    }

    @Override
    public ManagedChannelImplBuilder offloadExecutor(Executor executor) {
        if (executor != null) {
            this.offloadExecutorPool = new FixedObjectPool(executor);
        } else {
            this.offloadExecutorPool = DEFAULT_EXECUTOR_POOL;
        }
        return this;
    }

    @Override
    public ManagedChannelImplBuilder intercept(List<ClientInterceptor> list) {
        this.interceptors.addAll(list);
        return this;
    }

    @Override
    public ManagedChannelImplBuilder intercept(ClientInterceptor... clientInterceptorArr) {
        return intercept(Arrays.asList(clientInterceptorArr));
    }

    @Override
    @Deprecated
    public ManagedChannelImplBuilder nameResolverFactory(NameResolver.Factory factory) {
        SocketAddress socketAddress = this.directServerAddress;
        Preconditions.checkState(socketAddress == null, "directServerAddress is set (%s), which forbids the use of NameResolverFactory", socketAddress);
        if (factory != null) {
            this.nameResolverFactory = factory;
        } else {
            this.nameResolverFactory = this.nameResolverRegistry.asFactory();
        }
        return this;
    }

    @Override
    public ManagedChannelImplBuilder defaultLoadBalancingPolicy(String str) {
        SocketAddress socketAddress = this.directServerAddress;
        Preconditions.checkState(socketAddress == null, "directServerAddress is set (%s), which forbids the use of load-balancing policy", socketAddress);
        Preconditions.checkArgument(str != null, "policy cannot be null");
        this.defaultLbPolicy = str;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder overrideAuthority(String str) {
        this.authorityOverride = checkAuthority(str);
        return this;
    }

    @Override
    public ManagedChannelImplBuilder idleTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "idle timeout is %s, but must be positive", j);
        if (timeUnit.toDays(j) >= 30) {
            this.idleTimeoutMillis = -1L;
        } else {
            this.idleTimeoutMillis = Math.max(timeUnit.toMillis(j), IDLE_MODE_MIN_TIMEOUT_MILLIS);
        }
        return this;
    }

    @Override
    public ManagedChannelImplBuilder retryBufferSize(long j) {
        Preconditions.checkArgument(j > 0, "retry buffer size must be positive");
        this.retryBufferSize = j;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder perRpcBufferLimit(long j) {
        Preconditions.checkArgument(j > 0, "per RPC buffer limit must be positive");
        this.perRpcBufferLimit = j;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder maxTraceEvents(int i) {
        Preconditions.checkArgument(i >= 0, "maxTraceEvents must be non-negative");
        this.maxTraceEvents = i;
        return this;
    }

    @Override
    public ManagedChannelImplBuilder defaultServiceConfig(@Nullable Map<String, ?> map) {
        this.defaultServiceConfig = checkMapEntryTypes(map);
        return this;
    }

    @Nullable
    private static Map<String, ?> checkMapEntryTypes(@Nullable Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Preconditions.checkArgument(entry.getKey() instanceof String, "The key of the entry '%s' is not of String type", entry);
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                linkedHashMap.put(str, null);
            } else if (value instanceof Map) {
                linkedHashMap.put(str, checkMapEntryTypes((Map) value));
            } else if (value instanceof List) {
                linkedHashMap.put(str, checkListEntryTypes((List) value));
            } else if (value instanceof String) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Double) {
                linkedHashMap.put(str, value);
            } else if (value instanceof Boolean) {
                linkedHashMap.put(str, value);
            } else {
                throw new IllegalArgumentException("The value of the map entry '" + entry + "' is of type '" + value.getClass() + "', which is not supported");
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static List<?> checkListEntryTypes(List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            if (obj == null) {
                arrayList.add(null);
            } else if (obj instanceof Map) {
                arrayList.add(checkMapEntryTypes((Map) obj));
            } else if (obj instanceof List) {
                arrayList.add(checkListEntryTypes((List) obj));
            } else if (obj instanceof String) {
                arrayList.add(obj);
            } else if (obj instanceof Double) {
                arrayList.add(obj);
            } else if (obj instanceof Boolean) {
                arrayList.add(obj);
            } else {
                throw new IllegalArgumentException("The entry '" + obj + "' is of type '" + obj.getClass() + "', which is not supported");
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    String checkAuthority(String str) {
        return this.authorityCheckerDisabled ? str : GrpcUtil.checkAuthority(str);
    }

    @Override
    public ManagedChannel build() {
        return new ManagedChannelOrphanWrapper(new ManagedChannelImpl(this, this.clientTransportFactoryBuilder.buildClientTransportFactory(), new ExponentialBackoffPolicy.Provider(), SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR), GrpcUtil.STOPWATCH_SUPPLIER, getEffectiveInterceptors(), TimeProvider.SYSTEM_TIME_PROVIDER));
    }

    java.util.List<io.grpc.ClientInterceptor> getEffectiveInterceptors() {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.internal.ManagedChannelImplBuilder.getEffectiveInterceptors():java.util.List");
    }

    public int getDefaultPort() {
        return this.channelBuilderDefaultPortProvider.getDefaultPort();
    }

    private static class DirectAddressNameResolverFactory extends NameResolver.Factory {
        final SocketAddress address;
        final String authority;

        @Override
        public String getDefaultScheme() {
            return ManagedChannelImplBuilder.DIRECT_ADDRESS_SCHEME;
        }

        DirectAddressNameResolverFactory(SocketAddress socketAddress, String str) {
            this.address = socketAddress;
            this.authority = str;
        }

        @Override
        public NameResolver newNameResolver(URI uri, NameResolver.Args args) {
            return new NameResolver() {
                @Override
                public void shutdown() {
                }

                @Override
                public String getServiceAuthority() {
                    return DirectAddressNameResolverFactory.this.authority;
                }

                @Override
                public void start(NameResolver.Listener2 listener2) {
                    listener2.onResult(NameResolver.ResolutionResult.newBuilder().setAddresses(Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverFactory.this.address))).setAttributes(Attributes.EMPTY).build());
                }
            };
        }
    }
}
