package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import io.grpc.CallOptions;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.MessageFramer;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
public abstract class AbstractClientStream extends AbstractStream implements ClientStream, MessageFramer.Sink {
    private static final Logger log = Logger.getLogger(AbstractClientStream.class.getName());
    private volatile boolean cancelled;
    private final Framer framer;
    private Metadata headers;
    private boolean shouldBeCountedForInUse;
    private final TransportTracer transportTracer;
    private boolean useGet;

    protected interface Sink {
        void cancel(Status status);

        void writeFrame(@Nullable WritableBuffer writableBuffer, boolean z, boolean z2, int i);

        void writeHeaders(Metadata metadata, @Nullable byte[] bArr);
    }

    protected abstract Sink abstractClientStreamSink();

    @Override
    protected final Framer framer() {
        return this.framer;
    }

    public TransportTracer getTransportTracer() {
        return this.transportTracer;
    }

    public final boolean shouldBeCountedForInUse() {
        return this.shouldBeCountedForInUse;
    }

    @Override
    public abstract TransportState transportState();

    public AbstractClientStream(WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext, TransportTracer transportTracer, Metadata metadata, CallOptions callOptions, boolean z) {
        Preconditions.checkNotNull(metadata, "headers");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.shouldBeCountedForInUse = GrpcUtil.shouldBeCountedForInUse(callOptions);
        this.useGet = z;
        if (!z) {
            this.framer = new MessageFramer(this, writableBufferAllocator, statsTraceContext);
            this.headers = metadata;
            return;
        }
        this.framer = new GetFramer(metadata, statsTraceContext);
    }

    @Override
    public void setDeadline(Deadline deadline) {
        this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
        this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
    }

    @Override
    public void setMaxOutboundMessageSize(int i) {
        this.framer.setMaxOutboundMessageSize(i);
    }

    @Override
    public void setMaxInboundMessageSize(int i) {
        transportState().setMaxInboundMessageSize(i);
    }

    @Override
    public final void setFullStreamDecompression(boolean z) {
        transportState().setFullStreamDecompression(z);
    }

    @Override
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        transportState().setDecompressorRegistry(decompressorRegistry);
    }

    @Override
    public final void start(ClientStreamListener clientStreamListener) {
        transportState().setListener(clientStreamListener);
        if (this.useGet) {
            return;
        }
        abstractClientStreamSink().writeHeaders(this.headers, null);
        this.headers = null;
    }

    @Override
    public final void deliverFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
        Preconditions.checkArgument(writableBuffer != null || z, "null frame before EOS");
        abstractClientStreamSink().writeFrame(writableBuffer, z, z2, i);
    }

    @Override
    public final void halfClose() {
        if (transportState().isOutboundClosed()) {
            return;
        }
        transportState().setOutboundClosed();
        endOfMessages();
    }

    @Override
    public final void cancel(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Should not cancel with OK status");
        this.cancelled = true;
        abstractClientStreamSink().cancel(status);
    }

    @Override
    public final boolean isReady() {
        return super.isReady() && !this.cancelled;
    }

    @Override
    public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue("remote_addr", getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR));
    }

    public static abstract class TransportState extends AbstractStream.TransportState {
        private DecompressorRegistry decompressorRegistry;
        private boolean deframerClosed;
        private Runnable deframerClosedTask;
        private boolean fullStreamDecompression;
        private ClientStreamListener listener;
        private boolean listenerClosed;
        private volatile boolean outboundClosed;
        private final StatsTraceContext statsTraceCtx;
        private boolean statusReported;
        private boolean statusReportedIsOk;

        public void setFullStreamDecompression(boolean z) {
            this.fullStreamDecompression = z;
        }

        public final void setOutboundClosed() {
            this.outboundClosed = true;
        }

        public final boolean isOutboundClosed() {
            return this.outboundClosed;
        }

        @Override
        public final ClientStreamListener listener() {
            return this.listener;
        }

        public TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
            super(i, statsTraceContext, transportTracer);
            this.decompressorRegistry = DecompressorRegistry.getDefaultInstance();
            this.deframerClosed = false;
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        }

        public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            Preconditions.checkState(this.listener == null, "Already called start");
            this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(decompressorRegistry, "decompressorRegistry");
        }

        public final void setListener(ClientStreamListener clientStreamListener) {
            Preconditions.checkState(this.listener == null, "Already called setListener");
            this.listener = (ClientStreamListener) Preconditions.checkNotNull(clientStreamListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        }

        @Override
        public void deframerClosed(boolean z) {
            Preconditions.checkState(this.statusReported, "status should have been reported on deframer closed");
            this.deframerClosed = true;
            if (this.statusReportedIsOk && z) {
                transportReportStatus(Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame"), true, new Metadata());
            }
            Runnable runnable = this.deframerClosedTask;
            if (runnable != null) {
                runnable.run();
                this.deframerClosedTask = null;
            }
        }

        public void inboundHeadersReceived(io.grpc.Metadata r6) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.internal.AbstractClientStream.TransportState.inboundHeadersReceived(io.grpc.Metadata):void");
        }

        public void inboundDataReceived(ReadableBuffer readableBuffer) {
            Preconditions.checkNotNull(readableBuffer, "frame");
            boolean z = true;
            try {
                if (this.statusReported) {
                    AbstractClientStream.log.log(Level.INFO, "Received data on closed stream");
                    readableBuffer.close();
                    return;
                }
                try {
                    deframe(readableBuffer);
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        readableBuffer.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public void inboundTrailersReceived(Metadata metadata, Status status) {
            Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            Preconditions.checkNotNull(metadata, GrpcUtil.TE_TRAILERS);
            if (this.statusReported) {
                AbstractClientStream.log.log(Level.INFO, "Received trailers on closed stream:\n {1}\n {2}", new Object[]{status, metadata});
                return;
            }
            this.statsTraceCtx.clientInboundTrailers(metadata);
            transportReportStatus(status, false, metadata);
        }

        public final void transportReportStatus(Status status, boolean z, Metadata metadata) {
            transportReportStatus(status, ClientStreamListener.RpcProgress.PROCESSED, z, metadata);
        }

        public final void transportReportStatus(final Status status, final ClientStreamListener.RpcProgress rpcProgress, boolean z, final Metadata metadata) {
            Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            Preconditions.checkNotNull(metadata, GrpcUtil.TE_TRAILERS);
            if (!this.statusReported || z) {
                this.statusReported = true;
                this.statusReportedIsOk = status.isOk();
                onStreamDeallocated();
                if (this.deframerClosed) {
                    this.deframerClosedTask = null;
                    closeListener(status, rpcProgress, metadata);
                    return;
                }
                this.deframerClosedTask = new Runnable() {
                    @Override
                    public void run() {
                        TransportState.this.closeListener(status, rpcProgress, metadata);
                    }
                };
                closeDeframer(z);
            }
        }

        public void closeListener(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            if (this.listenerClosed) {
                return;
            }
            this.listenerClosed = true;
            this.statsTraceCtx.streamClosed(status);
            listener().closed(status, rpcProgress, metadata);
            if (getTransportTracer() != null) {
                getTransportTracer().reportStreamClosed(status.isOk());
            }
        }
    }

    private class GetFramer implements Framer {
        private boolean closed;
        private Metadata headers;
        private byte[] payload;
        private final StatsTraceContext statsTraceCtx;

        @Override
        public void dispose() {
            this.closed = true;
            this.payload = null;
            this.headers = null;
        }

        @Override
        public void flush() {
        }

        @Override
        public boolean isClosed() {
            return this.closed;
        }

        @Override
        public Framer setCompressor(Compressor compressor) {
            return this;
        }

        @Override
        public void setMaxOutboundMessageSize(int i) {
        }

        @Override
        public Framer setMessageCompression(boolean z) {
            return this;
        }

        public GetFramer(Metadata metadata, StatsTraceContext statsTraceContext) {
            this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        }

        @Override
        public void writePayload(InputStream inputStream) {
            Preconditions.checkState(this.payload == null, "writePayload should not be called multiple times");
            try {
                this.payload = ByteStreams.toByteArray(inputStream);
                this.statsTraceCtx.outboundMessage(0);
                StatsTraceContext statsTraceContext = this.statsTraceCtx;
                byte[] bArr = this.payload;
                statsTraceContext.outboundMessageSent(0, bArr.length, bArr.length);
                this.statsTraceCtx.outboundUncompressedSize(this.payload.length);
                this.statsTraceCtx.outboundWireSize(this.payload.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void close() {
            this.closed = true;
            Preconditions.checkState(this.payload != null, "Lack of request message. GET request is only supported for unary requests");
            abstractClientStreamSink().writeHeaders(this.headers, this.payload);
            this.payload = null;
            this.headers = null;
        }
    }
}
