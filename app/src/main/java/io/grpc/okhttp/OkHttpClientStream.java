package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.util.List;
import okio.Buffer;
public class OkHttpClientStream extends AbstractClientStream {
    public static final int ABSENT_ID = -1;
    private static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    private String authority;
    private volatile int id;
    private final MethodDescriptor<?, ?> method;
    private Object outboundFlowState;
    private final Sink sink;
    private final TransportState state;
    private final StatsTraceContext statsTraceCtx;
    private boolean useGet;
    private final String userAgent;

    @Override
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    public Object getOutboundFlowState() {
        return this.outboundFlowState;
    }

    public int id() {
        return this.id;
    }

    public void setOutboundFlowState(Object obj) {
        this.outboundFlowState = obj;
    }

    @Override
    public TransportState transportState() {
        return this.state;
    }

    public boolean useGet() {
        return this.useGet;
    }

    public OkHttpClientStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OkHttpClientTransport okHttpClientTransport, OutboundFlowController outboundFlowController, Object obj, int i, int i2, String str, String str2, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions, boolean z) {
        super(new OkHttpWritableBufferAllocator(), statsTraceContext, transportTracer, metadata, callOptions, z && methodDescriptor.isSafe());
        this.id = -1;
        this.sink = new Sink();
        this.useGet = false;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        this.method = methodDescriptor;
        this.authority = str;
        this.userAgent = str2;
        this.attributes = okHttpClientTransport.getAttributes();
        this.state = new TransportState(i, statsTraceContext, obj, exceptionHandlingFrameWriter, outboundFlowController, okHttpClientTransport, i2, methodDescriptor.getFullMethodName());
    }

    public MethodDescriptor.MethodType getType() {
        return this.method.getType();
    }

    @Override
    public void setAuthority(String str) {
        this.authority = (String) Preconditions.checkNotNull(str, "authority");
    }

    public class Sink implements AbstractClientStream.Sink {
        Sink() {
        }

        @Override
        public void writeHeaders(Metadata metadata, byte[] bArr) {
            PerfMark.startTask("OkHttpClientStream$Sink.writeHeaders");
            String str = "/" + method.getFullMethodName();
            if (bArr != null) {
                useGet = true;
                str = str + "?" + BaseEncoding.base64().encode(bArr);
            }
            try {
                synchronized (state.lock) {
                    state.streamReady(metadata, str);
                }
            } finally {
                PerfMark.stopTask("OkHttpClientStream$Sink.writeHeaders");
            }
        }

        @Override
        public void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
            Buffer buffer;
            PerfMark.startTask("OkHttpClientStream$Sink.writeFrame");
            if (writableBuffer == null) {
                buffer = OkHttpClientStream.EMPTY_BUFFER;
            } else {
                buffer = ((OkHttpWritableBuffer) writableBuffer).buffer();
                int size = (int) buffer.size();
                if (size > 0) {
                    onSendingBytes(size);
                }
            }
            try {
                synchronized (state.lock) {
                    state.sendBuffer(buffer, z, z2);
                    getTransportTracer().reportMessageSent(i);
                }
            } finally {
                PerfMark.stopTask("OkHttpClientStream$Sink.writeFrame");
            }
        }

        @Override
        public void cancel(Status status) {
            PerfMark.startTask("OkHttpClientStream$Sink.cancel");
            try {
                synchronized (state.lock) {
                    state.cancel(status, true, null);
                }
            } finally {
                PerfMark.stopTask("OkHttpClientStream$Sink.cancel");
            }
        }
    }

    public class TransportState extends Http2ClientStreamTransportState {
        private boolean canStart;
        private boolean cancelSent;
        private boolean flushPendingData;
        private final ExceptionHandlingFrameWriter frameWriter;
        private final int initialWindowSize;
        private final Object lock;
        private final OutboundFlowController outboundFlow;
        private Buffer pendingData;
        private boolean pendingDataHasEndOfStream;
        private int processedWindow;
        private List<Header> requestHeaders;
        private final Tag tag;
        private final OkHttpClientTransport transport;
        private int window;

        public Tag tag() {
            return this.tag;
        }

        public TransportState(int i, StatsTraceContext statsTraceContext, Object obj, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OutboundFlowController outboundFlowController, OkHttpClientTransport okHttpClientTransport, int i2, String str) {
            super(i, statsTraceContext, getTransportTracer());
            this.pendingData = new Buffer();
            this.pendingDataHasEndOfStream = false;
            this.flushPendingData = false;
            this.cancelSent = false;
            this.canStart = true;
            this.lock = Preconditions.checkNotNull(obj, "lock");
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.transport = okHttpClientTransport;
            this.window = i2;
            this.processedWindow = i2;
            this.initialWindowSize = i2;
            this.tag = PerfMark.createTag(str);
        }

        public void start(int i) {
            Preconditions.checkState(id == -1, "the stream has been started with id %s", i);
            id = i;
            state.onStreamAllocated();
            if (this.canStart) {
                this.frameWriter.synStream(useGet, false, id, 0, this.requestHeaders);
                statsTraceCtx.clientOutboundHeaders();
                this.requestHeaders = null;
                if (this.pendingData.size() > 0) {
                    this.outboundFlow.data(this.pendingDataHasEndOfStream, id, this.pendingData, this.flushPendingData);
                }
                this.canStart = false;
            }
        }

        @Override
        public void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        @Override
        protected void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            cancel(status, z, metadata);
        }

        @Override
        public void deframeFailed(Throwable th) {
            http2ProcessingFailed(Status.fromThrowable(th), true, new Metadata());
        }

        @Override
        public void bytesRead(int i) {
            int i2 = this.processedWindow - i;
            this.processedWindow = i2;
            int i3 = this.initialWindowSize;
            if (i2 <= i3 * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(id(), i4);
            }
        }

        @Override
        public void deframerClosed(boolean z) {
            onEndOfStream();
            super.deframerClosed(z);
        }

        @Override
        public void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        public void transportHeadersReceived(List<Header> list, boolean z) {
            if (z) {
                transportTrailersReceived(Utils.convertTrailers(list));
            } else {
                transportHeadersReceived(Utils.convertHeaders(list));
            }
        }

        public void transportDataReceived(Buffer buffer, boolean z) {
            int size = this.window - ((int) buffer.size());
            this.window = size;
            if (size < 0) {
                this.frameWriter.rstStream(id(), ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(id(), Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), ClientStreamListener.RpcProgress.PROCESSED, false, null, null);
                return;
            }
            super.transportDataReceived(new OkHttpReadableBuffer(buffer), z);
        }

        private void onEndOfStream() {
            if (!isOutboundClosed()) {
                this.transport.finishStream(id(), null, ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.CANCEL, null);
            } else {
                this.transport.finishStream(id(), null, ClientStreamListener.RpcProgress.PROCESSED, false, null, null);
            }
        }

        public void cancel(Status status, boolean z, Metadata metadata) {
            if (this.cancelSent) {
                return;
            }
            this.cancelSent = true;
            if (this.canStart) {
                this.transport.removePendingStream(OkHttpClientStream.this);
                this.requestHeaders = null;
                this.pendingData.clear();
                this.canStart = false;
                if (metadata == null) {
                    metadata = new Metadata();
                }
                transportReportStatus(status, true, metadata);
                return;
            }
            this.transport.finishStream(id(), status, ClientStreamListener.RpcProgress.PROCESSED, z, ErrorCode.CANCEL, metadata);
        }

        public void sendBuffer(Buffer buffer, boolean z, boolean z2) {
            if (this.cancelSent) {
                return;
            }
            if (this.canStart) {
                this.pendingData.write(buffer, (int) buffer.size());
                this.pendingDataHasEndOfStream |= z;
                this.flushPendingData |= z2;
                return;
            }
            Preconditions.checkState(id() != -1, "streamId should be set");
            this.outboundFlow.data(z, id(), buffer, z2);
        }

        public void streamReady(Metadata metadata, String str) {
            this.requestHeaders = Headers.createRequestHeaders(metadata, str, authority, userAgent, useGet, this.transport.isUsingPlaintext());
            this.transport.streamReadyToStart(OkHttpClientStream.this);
        }
    }
}
