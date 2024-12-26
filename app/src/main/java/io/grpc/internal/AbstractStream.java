package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.tron.wallet.business.pull.PullConstants;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframer;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.InputStream;
public abstract class AbstractStream implements Stream {
    protected abstract Framer framer();

    protected abstract TransportState transportState();

    @Override
    public void optimizeForDirectExecutor() {
        transportState().optimizeForDirectExecutor();
    }

    @Override
    public final void setMessageCompression(boolean z) {
        framer().setMessageCompression(z);
    }

    @Override
    public final void request(int i) {
        transportState().requestMessagesFromDeframer(i);
    }

    @Override
    public final void writeMessage(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, PullConstants.RESULT_MESSAGE);
        try {
            if (!framer().isClosed()) {
                framer().writePayload(inputStream);
            }
        } finally {
            GrpcUtil.closeQuietly(inputStream);
        }
    }

    @Override
    public final void flush() {
        if (framer().isClosed()) {
            return;
        }
        framer().flush();
    }

    public final void endOfMessages() {
        framer().close();
    }

    @Override
    public final void setCompressor(Compressor compressor) {
        framer().setCompressor((Compressor) Preconditions.checkNotNull(compressor, "compressor"));
    }

    @Override
    public boolean isReady() {
        if (framer().isClosed()) {
            return false;
        }
        return transportState().isReady();
    }

    public final void onSendingBytes(int i) {
        transportState().onSendingBytes(i);
    }

    public static abstract class TransportState implements ApplicationThreadDeframer.TransportExecutor, MessageDeframer.Listener {
        public static final int DEFAULT_ONREADY_THRESHOLD = 32768;
        private boolean allocated;
        private boolean deallocated;
        private Deframer deframer;
        private int numSentBytesQueued;
        private final Object onReadyLock = new Object();
        private final MessageDeframer rawDeframer;
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;

        public final StatsTraceContext getStatsTraceContext() {
            return this.statsTraceCtx;
        }

        public TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        protected abstract StreamListener listener();

        public TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
            this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
            MessageDeframer messageDeframer = new MessageDeframer(this, Codec.Identity.NONE, i, statsTraceContext, transportTracer);
            this.rawDeframer = messageDeframer;
            this.deframer = messageDeframer;
        }

        final void optimizeForDirectExecutor() {
            this.rawDeframer.setListener(this);
            this.deframer = this.rawDeframer;
        }

        public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
            this.rawDeframer.setFullStreamDecompressor(gzipInflatingBuffer);
            this.deframer = new ApplicationThreadDeframer(this, this, this.rawDeframer);
        }

        public final void setMaxInboundMessageSize(int i) {
            this.deframer.setMaxInboundMessageSize(i);
        }

        @Override
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            listener().messagesAvailable(messageProducer);
        }

        public final void closeDeframer(boolean z) {
            if (z) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        public final void deframe(ReadableBuffer readableBuffer) {
            try {
                this.deframer.deframe(readableBuffer);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        public void requestMessagesFromDeframer(final int i) {
            if (this.deframer instanceof ThreadOptimizedDeframer) {
                PerfMark.startTask("AbstractStream.request");
                try {
                    this.deframer.request(i);
                    return;
                } finally {
                    PerfMark.stopTask("AbstractStream.request");
                }
            }
            final Link linkOut = PerfMark.linkOut();
            runOnTransportThread(new Runnable() {
                @Override
                public void run() {
                    PerfMark.startTask("AbstractStream.request");
                    PerfMark.linkIn(linkOut);
                    try {
                        TransportState.this.deframer.request(i);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            });
        }

        public final void requestMessagesFromDeframerForTesting(int i) {
            requestMessagesFromDeframer(i);
        }

        public final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        public boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
            return z;
        }

        public void onStreamAllocated() {
            Preconditions.checkState(listener() != null);
            synchronized (this.onReadyLock) {
                Preconditions.checkState(!this.allocated, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        public final void onStreamDeallocated() {
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
        }

        public void onSendingBytes(int i) {
            synchronized (this.onReadyLock) {
                this.numSentBytesQueued += i;
            }
        }

        public final void onSentBytes(int i) {
            boolean z;
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                int i2 = this.numSentBytesQueued;
                z = true;
                boolean z2 = i2 < 32768;
                int i3 = i2 - i;
                this.numSentBytesQueued = i3;
                boolean z3 = i3 < 32768;
                if (z2 || !z3) {
                    z = false;
                }
            }
            if (z) {
                notifyIfReady();
            }
        }

        private void notifyIfReady() {
            boolean isReady;
            synchronized (this.onReadyLock) {
                isReady = isReady();
            }
            if (isReady) {
                listener().onReady();
            }
        }
    }
}
