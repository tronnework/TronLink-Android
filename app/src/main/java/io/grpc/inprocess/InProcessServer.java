package io.grpc.inprocess;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerTransportListener;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
final class InProcessServer implements InternalServer {
    private static final ConcurrentMap<String, InProcessServer> registry = new ConcurrentHashMap();
    private ServerListener listener;
    private final int maxInboundMetadataSize;
    private final String name;
    private ScheduledExecutorService scheduler;
    private final ObjectPool<ScheduledExecutorService> schedulerPool;
    private boolean shutdown;
    private final List<ServerStreamTracer.Factory> streamTracerFactories;

    @Override
    public InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats() {
        return null;
    }

    @Override
    public List<InternalInstrumented<InternalChannelz.SocketStats>> getListenSocketStatsList() {
        return null;
    }

    public int getMaxInboundMetadataSize() {
        return this.maxInboundMetadataSize;
    }

    public ObjectPool<ScheduledExecutorService> getScheduledExecutorServicePool() {
        return this.schedulerPool;
    }

    public List<ServerStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    public static InProcessServer findServer(String str) {
        return registry.get(str);
    }

    public InProcessServer(InProcessServerBuilder inProcessServerBuilder, List<? extends ServerStreamTracer.Factory> list) {
        this.name = inProcessServerBuilder.name;
        this.schedulerPool = inProcessServerBuilder.schedulerPool;
        this.maxInboundMetadataSize = inProcessServerBuilder.maxInboundMetadataSize;
        this.streamTracerFactories = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "streamTracerFactories"));
    }

    @Override
    public void start(ServerListener serverListener) throws IOException {
        this.listener = serverListener;
        this.scheduler = this.schedulerPool.getObject();
        if (registry.putIfAbsent(this.name, this) == null) {
            return;
        }
        throw new IOException("name already registered: " + this.name);
    }

    @Override
    public SocketAddress getListenSocketAddress() {
        return new InProcessSocketAddress(this.name);
    }

    @Override
    public List<? extends SocketAddress> getListenSocketAddresses() {
        return Collections.singletonList(getListenSocketAddress());
    }

    @Override
    public void shutdown() {
        if (!registry.remove(this.name, this)) {
            throw new AssertionError();
        }
        this.scheduler = this.schedulerPool.returnObject(this.scheduler);
        synchronized (this) {
            this.shutdown = true;
            this.listener.serverShutdown();
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).toString();
    }

    public synchronized ServerTransportListener register(InProcessTransport inProcessTransport) {
        if (this.shutdown) {
            return null;
        }
        return this.listener.transportCreated(inProcessTransport);
    }
}
