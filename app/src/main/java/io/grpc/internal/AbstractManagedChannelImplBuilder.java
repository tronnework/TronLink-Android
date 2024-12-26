package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.BinaryLog;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.ProxyDetector;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
public abstract class AbstractManagedChannelImplBuilder<T extends ManagedChannelBuilder<T>> extends ManagedChannelBuilder<T> {
    protected abstract ManagedChannelBuilder<?> delegate();

    protected final T thisT() {
        return this;
    }

    @Override
    public ManagedChannelBuilder defaultServiceConfig(@Nullable Map map) {
        return defaultServiceConfig((Map<String, ?>) map);
    }

    @Override
    public ManagedChannelBuilder intercept(List list) {
        return intercept((List<ClientInterceptor>) list);
    }

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        


return null;

//throw new UnsupportedOperationException(
Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        


return null;

//throw new UnsupportedOperationException(
Subclass failed to hide static factory");
    }

    @Override
    public T directExecutor() {
        delegate().directExecutor();
        return thisT();
    }

    @Override
    public T executor(Executor executor) {
        delegate().executor(executor);
        return thisT();
    }

    @Override
    public T offloadExecutor(Executor executor) {
        delegate().offloadExecutor(executor);
        return thisT();
    }

    @Override
    public T intercept(List<ClientInterceptor> list) {
        delegate().intercept(list);
        return thisT();
    }

    @Override
    public T intercept(ClientInterceptor... clientInterceptorArr) {
        delegate().intercept(clientInterceptorArr);
        return thisT();
    }

    @Override
    public T userAgent(String str) {
        delegate().userAgent(str);
        return thisT();
    }

    @Override
    public T overrideAuthority(String str) {
        delegate().overrideAuthority(str);
        return thisT();
    }

    @Override
    public T usePlaintext() {
        delegate().usePlaintext();
        return thisT();
    }

    @Override
    public T useTransportSecurity() {
        delegate().useTransportSecurity();
        return thisT();
    }

    @Override
    @Deprecated
    public T nameResolverFactory(NameResolver.Factory factory) {
        delegate().nameResolverFactory(factory);
        return thisT();
    }

    @Override
    public T defaultLoadBalancingPolicy(String str) {
        delegate().defaultLoadBalancingPolicy(str);
        return thisT();
    }

    @Override
    public T enableFullStreamDecompression() {
        delegate().enableFullStreamDecompression();
        return thisT();
    }

    @Override
    public T decompressorRegistry(DecompressorRegistry decompressorRegistry) {
        delegate().decompressorRegistry(decompressorRegistry);
        return thisT();
    }

    @Override
    public T compressorRegistry(CompressorRegistry compressorRegistry) {
        delegate().compressorRegistry(compressorRegistry);
        return thisT();
    }

    @Override
    public T idleTimeout(long j, TimeUnit timeUnit) {
        delegate().idleTimeout(j, timeUnit);
        return thisT();
    }

    @Override
    public T maxInboundMessageSize(int i) {
        delegate().maxInboundMessageSize(i);
        return thisT();
    }

    @Override
    public T maxInboundMetadataSize(int i) {
        delegate().maxInboundMetadataSize(i);
        return thisT();
    }

    @Override
    public T keepAliveTime(long j, TimeUnit timeUnit) {
        delegate().keepAliveTime(j, timeUnit);
        return thisT();
    }

    @Override
    public T keepAliveTimeout(long j, TimeUnit timeUnit) {
        delegate().keepAliveTimeout(j, timeUnit);
        return thisT();
    }

    @Override
    public T keepAliveWithoutCalls(boolean z) {
        delegate().keepAliveWithoutCalls(z);
        return thisT();
    }

    @Override
    public T maxRetryAttempts(int i) {
        delegate().maxRetryAttempts(i);
        return thisT();
    }

    @Override
    public T maxHedgedAttempts(int i) {
        delegate().maxHedgedAttempts(i);
        return thisT();
    }

    @Override
    public T retryBufferSize(long j) {
        delegate().retryBufferSize(j);
        return thisT();
    }

    @Override
    public T perRpcBufferLimit(long j) {
        delegate().perRpcBufferLimit(j);
        return thisT();
    }

    @Override
    public T disableRetry() {
        delegate().disableRetry();
        return thisT();
    }

    @Override
    public T enableRetry() {
        delegate().enableRetry();
        return thisT();
    }

    @Override
    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return thisT();
    }

    @Override
    public T maxTraceEvents(int i) {
        delegate().maxTraceEvents(i);
        return thisT();
    }

    @Override
    public T proxyDetector(ProxyDetector proxyDetector) {
        delegate().proxyDetector(proxyDetector);
        return thisT();
    }

    @Override
    public T defaultServiceConfig(@Nullable Map<String, ?> map) {
        delegate().defaultServiceConfig(map);
        return thisT();
    }

    @Override
    public T disableServiceConfigLookUp() {
        delegate().disableServiceConfigLookUp();
        return thisT();
    }

    @Override
    public ManagedChannel build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
