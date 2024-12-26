package io.grpc.inprocess;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.Preconditions;
import io.grpc.Deadline;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public final class InProcessServerBuilder extends AbstractServerImplBuilder<InProcessServerBuilder> {
    final String name;
    private final ServerImplBuilder serverImplBuilder;
    int maxInboundMetadataSize = Integer.MAX_VALUE;
    ObjectPool<ScheduledExecutorService> schedulerPool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);

    @Override
    protected ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    public static InProcessServerBuilder forName(String str) {
        return new InProcessServerBuilder(str);
    }

    public static InProcessServerBuilder forPort(int i) {
        


return null;

//throw new UnsupportedOperationException(
call forName() instead");
    }

    public static String generateName() {
        return UUID.randomUUID().toString();
    }

    private InProcessServerBuilder(String str) {
        this.name = (String) Preconditions.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new ServerImplBuilder.ClientTransportServersBuilder() {
            @Override
            public InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list) {
                return buildTransportServers(list);
            }
        });
        this.serverImplBuilder = serverImplBuilder;
        serverImplBuilder.setStatsRecordStartedRpcs(false);
        serverImplBuilder.setStatsRecordFinishedRpcs(false);
        handshakeTimeout(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public InProcessServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.schedulerPool = new FixedObjectPool(Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public InProcessServerBuilder deadlineTicker(Deadline.Ticker ticker) {
        this.serverImplBuilder.setDeadlineTicker(ticker);
        return this;
    }

    @Override
    public InProcessServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    InProcessServer buildTransportServers(List<? extends ServerStreamTracer.Factory> list) {
        return new InProcessServer(this, list);
    }

    @Override
    public InProcessServerBuilder useTransportSecurity(File file, File file2) {
        


return null;

//throw new UnsupportedOperationException(
TLS not supported in InProcessServer");
    }

    public void setStatsEnabled(boolean z) {
        this.serverImplBuilder.setStatsEnabled(z);
    }
}
