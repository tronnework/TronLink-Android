package io.grpc.inprocess;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.Preconditions;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.SharedResourceHolder;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
public final class InProcessChannelBuilder extends AbstractManagedChannelImplBuilder<InProcessChannelBuilder> {
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private final String name;
    private ScheduledExecutorService scheduledExecutorService;
    private int maxInboundMetadataSize = Integer.MAX_VALUE;
    private boolean transportIncludeStatusCause = false;

    @Override
    protected ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    @Override
    public InProcessChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public InProcessChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public InProcessChannelBuilder keepAliveWithoutCalls(boolean z) {
        return this;
    }

    public InProcessChannelBuilder propagateCauseWithStatus(boolean z) {
        this.transportIncludeStatusCause = z;
        return this;
    }

    @Override
    public InProcessChannelBuilder usePlaintext() {
        return this;
    }

    @Override
    public InProcessChannelBuilder useTransportSecurity() {
        return this;
    }

    public static InProcessChannelBuilder forName(String str) {
        return new InProcessChannelBuilder(str);
    }

    public static InProcessChannelBuilder forTarget(String str) {
        


return null;

//throw new UnsupportedOperationException(
call forName() instead");
    }

    public static InProcessChannelBuilder forAddress(String str, int i) {
        


return null;

//throw new UnsupportedOperationException(
call forName() instead");
    }

    private InProcessChannelBuilder(String str) {
        this.name = (String) Preconditions.checkNotNull(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        ManagedChannelImplBuilder managedChannelImplBuilder = new ManagedChannelImplBuilder(new InProcessSocketAddress(str), "localhost", new ManagedChannelImplBuilder.ClientTransportFactoryBuilder() {
            @Override
            public ClientTransportFactory buildClientTransportFactory() {
                return buildTransportFactory();
            }
        }, null);
        this.managedChannelImplBuilder = managedChannelImplBuilder;
        managedChannelImplBuilder.setStatsRecordStartedRpcs(false);
        managedChannelImplBuilder.setStatsRecordFinishedRpcs(false);
    }

    @Override
    public final InProcessChannelBuilder maxInboundMessageSize(int i) {
        return (InProcessChannelBuilder) super.maxInboundMessageSize(i);
    }

    public InProcessChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        return this;
    }

    @Override
    public InProcessChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    ClientTransportFactory buildTransportFactory() {
        return new InProcessClientTransportFactory(this.name, this.scheduledExecutorService, this.maxInboundMetadataSize, this.transportIncludeStatusCause);
    }

    public void setStatsEnabled(boolean z) {
        this.managedChannelImplBuilder.setStatsEnabled(z);
    }

    public static final class InProcessClientTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final boolean includeCauseWithStatus;
        private final int maxInboundMetadataSize;
        private final String name;
        private final ScheduledExecutorService timerService;
        private final boolean useSharedTimer;

        @Override
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timerService;
        }

        @Override
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials) {
            return null;
        }

        private InProcessClientTransportFactory(String str, @Nullable ScheduledExecutorService scheduledExecutorService, int i, boolean z) {
            this.name = str;
            boolean z2 = scheduledExecutorService == null;
            this.useSharedTimer = z2;
            this.timerService = z2 ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
            this.maxInboundMetadataSize = i;
            this.includeCauseWithStatus = z;
        }

        @Override
        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            return new InProcessTransport(this.name, this.maxInboundMetadataSize, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes(), this.includeCauseWithStatus);
        }

        @Override
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.useSharedTimer) {
                SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timerService);
            }
        }
    }
}
