package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.BinaryLog;
import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
public abstract class AbstractServerImplBuilder<T extends ServerBuilder<T>> extends ServerBuilder<T> {
    protected abstract ServerBuilder<?> delegate();

    protected final T thisT() {
        return this;
    }

    public static ServerBuilder<?> forPort(int i) {
        


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
    public T executor(@Nullable Executor executor) {
        delegate().executor(executor);
        return thisT();
    }

    @Override
    public T addService(ServerServiceDefinition serverServiceDefinition) {
        delegate().addService(serverServiceDefinition);
        return thisT();
    }

    @Override
    public T addService(BindableService bindableService) {
        delegate().addService(bindableService);
        return thisT();
    }

    @Override
    public T intercept(ServerInterceptor serverInterceptor) {
        delegate().intercept(serverInterceptor);
        return thisT();
    }

    @Override
    public T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        delegate().addTransportFilter(serverTransportFilter);
        return thisT();
    }

    @Override
    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        delegate().addStreamTracerFactory(factory);
        return thisT();
    }

    @Override
    public T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        delegate().fallbackHandlerRegistry(handlerRegistry);
        return thisT();
    }

    @Override
    public T useTransportSecurity(File file, File file2) {
        delegate().useTransportSecurity(file, file2);
        return thisT();
    }

    @Override
    public T useTransportSecurity(InputStream inputStream, InputStream inputStream2) {
        delegate().useTransportSecurity(inputStream, inputStream2);
        return thisT();
    }

    @Override
    public T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry) {
        delegate().decompressorRegistry(decompressorRegistry);
        return thisT();
    }

    @Override
    public T compressorRegistry(@Nullable CompressorRegistry compressorRegistry) {
        delegate().compressorRegistry(compressorRegistry);
        return thisT();
    }

    @Override
    public T handshakeTimeout(long j, TimeUnit timeUnit) {
        delegate().handshakeTimeout(j, timeUnit);
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
    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return thisT();
    }

    @Override
    public Server build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
