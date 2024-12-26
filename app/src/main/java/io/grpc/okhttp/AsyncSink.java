package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;
public final class AsyncSink implements Sink {
    private final SerializingExecutor serializingExecutor;
    @Nullable
    private Sink sink;
    @Nullable
    private Socket socket;
    private final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    private final Object lock = new Object();
    private final Buffer buffer = new Buffer();
    private boolean writeEnqueued = false;
    private boolean flushEnqueued = false;
    private boolean closed = false;

    private AsyncSink(SerializingExecutor serializingExecutor, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler) {
        this.serializingExecutor = (SerializingExecutor) Preconditions.checkNotNull(serializingExecutor, "executor");
        this.transportExceptionHandler = (ExceptionHandlingFrameWriter.TransportExceptionHandler) Preconditions.checkNotNull(transportExceptionHandler, "exceptionHandler");
    }

    public static AsyncSink sink(SerializingExecutor serializingExecutor, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler) {
        return new AsyncSink(serializingExecutor, transportExceptionHandler);
    }

    public void becomeConnected(Sink sink, Socket socket) {
        Preconditions.checkState(this.sink == null, "AsyncSink's becomeConnected should only be called once.");
        this.sink = (Sink) Preconditions.checkNotNull(sink, "sink");
        this.socket = (Socket) Preconditions.checkNotNull(socket, "socket");
    }

    @Override
    public void write(Buffer buffer, long j) throws IOException {
        Preconditions.checkNotNull(buffer, "source");
        if (this.closed) {
            throw new IOException("closed");
        }
        PerfMark.startTask("AsyncSink.write");
        try {
            synchronized (this.lock) {
                this.buffer.write(buffer, j);
                if (!this.writeEnqueued && !this.flushEnqueued && this.buffer.completeSegmentByteCount() > 0) {
                    this.writeEnqueued = true;
                    this.serializingExecutor.execute(new WriteRunnable() {
                        final Link link = PerfMark.linkOut();

                        @Override
                        public void doRun() throws IOException {
                            PerfMark.startTask("WriteRunnable.runWrite");
                            PerfMark.linkIn(this.link);
                            Buffer buffer2 = new Buffer();
                            try {
                                synchronized (lock) {
                                    buffer2.write(buffer, buffer.completeSegmentByteCount());
                                    writeEnqueued = false;
                                }
                                sink.write(buffer2, buffer2.size());
                            } finally {
                                PerfMark.stopTask("WriteRunnable.runWrite");
                            }
                        }
                    });
                }
            }
        } finally {
            PerfMark.stopTask("AsyncSink.write");
        }
    }

    @Override
    public void flush() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        PerfMark.startTask("AsyncSink.flush");
        try {
            synchronized (this.lock) {
                if (this.flushEnqueued) {
                    return;
                }
                this.flushEnqueued = true;
                this.serializingExecutor.execute(new WriteRunnable() {
                    final Link link = PerfMark.linkOut();

                    @Override
                    public void doRun() throws IOException {
                        PerfMark.startTask("WriteRunnable.runFlush");
                        PerfMark.linkIn(this.link);
                        Buffer buffer = new Buffer();
                        try {
                            synchronized (lock) {
                                buffer.write(buffer, buffer.size());
                                flushEnqueued = false;
                            }
                            sink.write(buffer, buffer.size());
                            sink.flush();
                        } finally {
                            PerfMark.stopTask("WriteRunnable.runFlush");
                        }
                    }
                });
            }
        } finally {
            PerfMark.stopTask("AsyncSink.flush");
        }
    }

    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.serializingExecutor.execute(new Runnable() {
            @Override
            public void run() {
                buffer.close();
                try {
                    if (sink != null) {
                        sink.close();
                    }
                } catch (IOException e) {
                    transportExceptionHandler.onException(e);
                }
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e2) {
                    transportExceptionHandler.onException(e2);
                }
            }
        });
    }

    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        @Override
        public final void run() {
            try {
                if (sink == null) {
                    throw new IOException("Unable to perform write due to unavailable sink.");
                }
                doRun();
            } catch (Exception e) {
                transportExceptionHandler.onException(e);
            }
        }
    }
}
