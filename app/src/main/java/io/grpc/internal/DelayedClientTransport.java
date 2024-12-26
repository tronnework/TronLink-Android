package io.grpc.internal;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ManagedClientTransport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
public final class DelayedClientTransport implements ManagedClientTransport {
    private final Executor defaultAppExecutor;
    @Nullable
    private LoadBalancer.SubchannelPicker lastPicker;
    private long lastPickerVersion;
    private ManagedClientTransport.Listener listener;
    private Runnable reportTransportInUse;
    private Runnable reportTransportNotInUse;
    private Runnable reportTransportTerminated;
    private Status shutdownStatus;
    private final SynchronizationContext syncContext;
    private final InternalLogId logId = InternalLogId.allocate(DelayedClientTransport.class, (String) null);
    private final Object lock = new Object();
    @Nonnull
    private Collection<PendingStream> pendingStreams = new LinkedHashSet();

    @Override
    public InternalLogId getLogId() {
        return this.logId;
    }

    public DelayedClientTransport(Executor executor, SynchronizationContext synchronizationContext) {
        this.defaultAppExecutor = executor;
        this.syncContext = synchronizationContext;
    }

    @Override
    public final Runnable start(final ManagedClientTransport.Listener listener) {
        this.listener = listener;
        this.reportTransportInUse = new Runnable() {
            @Override
            public void run() {
                listener.transportInUse(true);
            }
        };
        this.reportTransportNotInUse = new Runnable() {
            @Override
            public void run() {
                listener.transportInUse(false);
            }
        };
        this.reportTransportTerminated = new Runnable() {
            @Override
            public void run() {
                listener.transportTerminated();
            }
        };
        return null;
    }

    @Override
    public final ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        ClientStream failingClientStream;
        try {
            PickSubchannelArgsImpl pickSubchannelArgsImpl = new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions);
            LoadBalancer.SubchannelPicker subchannelPicker = null;
            long j = -1;
            while (true) {
                synchronized (this.lock) {
                    if (this.shutdownStatus == null) {
                        LoadBalancer.SubchannelPicker subchannelPicker2 = this.lastPicker;
                        if (subchannelPicker2 != null) {
                            if (subchannelPicker != null && j == this.lastPickerVersion) {
                                failingClientStream = createPendingStream(pickSubchannelArgsImpl);
                                break;
                            }
                            j = this.lastPickerVersion;
                            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(subchannelPicker2.pickSubchannel(pickSubchannelArgsImpl), callOptions.isWaitForReady());
                            if (transportFromPickResult != null) {
                                failingClientStream = transportFromPickResult.newStream(pickSubchannelArgsImpl.getMethodDescriptor(), pickSubchannelArgsImpl.getHeaders(), pickSubchannelArgsImpl.getCallOptions());
                                break;
                            }
                            subchannelPicker = subchannelPicker2;
                        } else {
                            failingClientStream = createPendingStream(pickSubchannelArgsImpl);
                            break;
                        }
                    } else {
                        failingClientStream = new FailingClientStream(this.shutdownStatus);
                        break;
                    }
                }
            }
            return failingClientStream;
        } finally {
            this.syncContext.drain();
        }
    }

    private PendingStream createPendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
        PendingStream pendingStream = new PendingStream(pickSubchannelArgs);
        this.pendingStreams.add(pendingStream);
        if (getPendingStreamsCount() == 1) {
            this.syncContext.executeLater(this.reportTransportInUse);
        }
        return pendingStream;
    }

    @Override
    public final void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
        


return;

//throw new UnsupportedOperationException(
This method is not expected to be called");
    }

    @Override
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        create.set(null);
        return create;
    }

    @Override
    public final void shutdown(final Status status) {
        Runnable runnable;
        synchronized (this.lock) {
            if (this.shutdownStatus != null) {
                return;
            }
            this.shutdownStatus = status;
            this.syncContext.executeLater(new Runnable() {
                @Override
                public void run() {
                    listener.transportShutdown(status);
                }
            });
            if (!hasPendingStreams() && (runnable = this.reportTransportTerminated) != null) {
                this.syncContext.executeLater(runnable);
                this.reportTransportTerminated = null;
            }
            this.syncContext.drain();
        }
    }

    @Override
    public final void shutdownNow(Status status) {
        Collection<PendingStream> collection;
        Runnable runnable;
        shutdown(status);
        synchronized (this.lock) {
            collection = this.pendingStreams;
            runnable = this.reportTransportTerminated;
            this.reportTransportTerminated = null;
            if (!collection.isEmpty()) {
                this.pendingStreams = Collections.emptyList();
            }
        }
        if (runnable != null) {
            for (PendingStream pendingStream : collection) {
                Runnable stream = pendingStream.setStream(new FailingClientStream(status, ClientStreamListener.RpcProgress.REFUSED));
                if (stream != null) {
                    stream.run();
                }
            }
            this.syncContext.execute(runnable);
        }
    }

    public final boolean hasPendingStreams() {
        boolean z;
        synchronized (this.lock) {
            z = !this.pendingStreams.isEmpty();
        }
        return z;
    }

    final int getPendingStreamsCount() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    public final void reprocess(@Nullable LoadBalancer.SubchannelPicker subchannelPicker) {
        Runnable runnable;
        synchronized (this.lock) {
            this.lastPicker = subchannelPicker;
            this.lastPickerVersion++;
            if (subchannelPicker != null && hasPendingStreams()) {
                ArrayList arrayList = new ArrayList(this.pendingStreams);
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    PendingStream pendingStream = (PendingStream) it.next();
                    LoadBalancer.PickResult pickSubchannel = subchannelPicker.pickSubchannel(pendingStream.args);
                    CallOptions callOptions = pendingStream.args.getCallOptions();
                    ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(pickSubchannel, callOptions.isWaitForReady());
                    if (transportFromPickResult != null) {
                        Executor executor = this.defaultAppExecutor;
                        if (callOptions.getExecutor() != null) {
                            executor = callOptions.getExecutor();
                        }
                        Runnable createRealStream = pendingStream.createRealStream(transportFromPickResult);
                        if (createRealStream != null) {
                            executor.execute(createRealStream);
                        }
                        arrayList2.add(pendingStream);
                    }
                }
                synchronized (this.lock) {
                    if (hasPendingStreams()) {
                        this.pendingStreams.removeAll(arrayList2);
                        if (this.pendingStreams.isEmpty()) {
                            this.pendingStreams = new LinkedHashSet();
                        }
                        if (!hasPendingStreams()) {
                            this.syncContext.executeLater(this.reportTransportNotInUse);
                            if (this.shutdownStatus != null && (runnable = this.reportTransportTerminated) != null) {
                                this.syncContext.executeLater(runnable);
                                this.reportTransportTerminated = null;
                            }
                        }
                        this.syncContext.drain();
                    }
                }
            }
        }
    }

    public class PendingStream extends DelayedStream {
        private final LoadBalancer.PickSubchannelArgs args;
        private final Context context;

        private PendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            this.context = Context.current();
            this.args = pickSubchannelArgs;
        }

        public Runnable createRealStream(ClientTransport clientTransport) {
            Context attach = this.context.attach();
            try {
                ClientStream newStream = clientTransport.newStream(this.args.getMethodDescriptor(), this.args.getHeaders(), this.args.getCallOptions());
                this.context.detach(attach);
                return setStream(newStream);
            } catch (Throwable th) {
                this.context.detach(attach);
                throw th;
            }
        }

        @Override
        public void cancel(Status status) {
            super.cancel(status);
            synchronized (lock) {
                if (reportTransportTerminated != null) {
                    boolean remove = pendingStreams.remove(this);
                    if (!hasPendingStreams() && remove) {
                        syncContext.executeLater(reportTransportNotInUse);
                        if (shutdownStatus != null) {
                            syncContext.executeLater(reportTransportTerminated);
                            reportTransportTerminated = null;
                        }
                    }
                }
            }
            syncContext.drain();
        }

        @Override
        public void appendTimeoutInsight(InsightBuilder insightBuilder) {
            if (this.args.getCallOptions().isWaitForReady()) {
                insightBuilder.append("wait_for_ready");
            }
            super.appendTimeoutInsight(insightBuilder);
        }
    }
}
