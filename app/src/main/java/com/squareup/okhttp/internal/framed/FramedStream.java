package com.squareup.okhttp.internal.framed;

import com.polidea.rxandroidble2.ClientComponent;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
public final class FramedStream {
    static final boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    private final FramedConnection connection;
    private final int id;
    private final List<Header> requestHeaders;
    private List<Header> responseHeaders;
    final FramedDataSink sink;
    private final FramedDataSource source;
    long unacknowledgedBytesRead = 0;
    private final StreamTimeout readTimeout = new StreamTimeout();
    private final StreamTimeout writeTimeout = new StreamTimeout();
    private ErrorCode errorCode = null;

    public FramedConnection getConnection() {
        return this.connection;
    }

    public int getId() {
        return this.id;
    }

    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    public Source getSource() {
        return this.source;
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public FramedStream(int i, FramedConnection framedConnection, boolean z, boolean z2, List<Header> list) {
        if (framedConnection == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.id = i;
        this.connection = framedConnection;
        this.bytesLeftInWriteWindow = framedConnection.peerSettings.getInitialWindowSize(65536);
        FramedDataSource framedDataSource = new FramedDataSource(framedConnection.okHttpSettings.getInitialWindowSize(65536));
        this.source = framedDataSource;
        FramedDataSink framedDataSink = new FramedDataSink();
        this.sink = framedDataSink;
        framedDataSource.finished = z2;
        framedDataSink.finished = z;
        this.requestHeaders = list;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed)) {
            if (this.responseHeaders != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.id & 1) == 1);
    }

    public synchronized List<Header> getResponseHeaders() throws IOException {
        List<Header> list;
        this.readTimeout.enter();
        while (this.responseHeaders == null && this.errorCode == null) {
            waitForIo();
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        list = this.responseHeaders;
        if (list == null) {
            throw new IOException("stream was reset: " + this.errorCode);
        }
        return list;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void reply(List<Header> list, boolean z) throws IOException {
        boolean z2;
        synchronized (this) {
            try {
                if (list == null) {
                    throw new NullPointerException("responseHeaders == null");
                }
                if (this.responseHeaders != null) {
                    throw new IllegalStateException("reply already sent");
                }
                this.responseHeaders = list;
                if (z) {
                    z2 = false;
                } else {
                    z2 = true;
                    this.sink.finished = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.connection.writeSynReply(this.id, z2, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public Sink getSink() {
        synchronized (this) {
            if (this.responseHeaders == null && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    public void receiveHeaders(List<Header> list, HeadersMode headersMode) {
        ErrorCode errorCode;
        boolean z;
        synchronized (this) {
            errorCode = null;
            z = true;
            if (this.responseHeaders == null) {
                if (headersMode.failIfHeadersAbsent()) {
                    errorCode = ErrorCode.PROTOCOL_ERROR;
                } else {
                    this.responseHeaders = list;
                    z = isOpen();
                    notifyAll();
                }
            } else if (headersMode.failIfHeadersPresent()) {
                errorCode = ErrorCode.STREAM_IN_USE;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.responseHeaders);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (errorCode != null) {
            closeLater(errorCode);
        } else if (z) {
        } else {
            this.connection.removeStream(this.id);
        }
    }

    public void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, i);
    }

    public void receiveFin() {
        boolean isOpen;
        synchronized (this) {
            this.source.finished = true;
            isOpen = isOpen();
            notifyAll();
        }
        if (isOpen) {
            return;
        }
        this.connection.removeStream(this.id);
    }

    public synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    public final class FramedDataSource implements Source {
        static final boolean $assertionsDisabled = false;
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;

        private FramedDataSource(long j) {
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
            this.maxByteCount = j;
        }

        @Override
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (FramedStream.this) {
                waitUntilReadable();
                checkNotClosed();
                if (this.readBuffer.size() == 0) {
                    return -1L;
                }
                Buffer buffer2 = this.readBuffer;
                long read = buffer2.read(buffer, Math.min(j, buffer2.size()));
                unacknowledgedBytesRead += read;
                if (unacknowledgedBytesRead >= connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                    connection.writeWindowUpdateLater(id, unacknowledgedBytesRead);
                    unacknowledgedBytesRead = 0L;
                }
                synchronized (connection) {
                    connection.unacknowledgedBytesRead += read;
                    if (connection.unacknowledgedBytesRead >= connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                        connection.writeWindowUpdateLater(0, connection.unacknowledgedBytesRead);
                        connection.unacknowledgedBytesRead = 0L;
                    }
                }
                return read;
            }
        }

        private void waitUntilReadable() throws IOException {
            readTimeout.enter();
            while (this.readBuffer.size() == 0 && !this.finished && !this.closed && errorCode == null) {
                try {
                    waitForIo();
                } finally {
                    readTimeout.exitAndThrowIfTimedOut();
                }
            }
        }

        void receive(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (FramedStream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.size() + j > this.maxByteCount;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    bufferedSource.skip(j);
                    return;
                } else {
                    long read = bufferedSource.read(this.receiveBuffer, j);
                    if (read == -1) {
                        throw new EOFException();
                    }
                    j -= read;
                    synchronized (FramedStream.this) {
                        if (this.readBuffer.size() != 0) {
                            z2 = false;
                        }
                        this.readBuffer.writeAll(this.receiveBuffer);
                        if (z2) {
                            notifyAll();
                        }
                    }
                }
            }
        }

        @Override
        public Timeout timeout() {
            return readTimeout;
        }

        @Override
        public void close() throws IOException {
            synchronized (FramedStream.this) {
                this.closed = true;
                this.readBuffer.clear();
                notifyAll();
            }
            cancelStreamIfNecessary();
        }

        private void checkNotClosed() throws IOException {
            if (!this.closed) {
                if (errorCode == null) {
                    return;
                }
                throw new IOException("stream was reset: " + errorCode);
            }
            throw new IOException("stream closed");
        }
    }

    public void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean isOpen;
        synchronized (this) {
            z = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            isOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else if (isOpen) {
        } else {
            this.connection.removeStream(this.id);
        }
    }

    public final class FramedDataSink implements Sink {
        static final boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer = new Buffer();

        FramedDataSink() {
        }

        @Override
        public void write(Buffer buffer, long j) throws IOException {
            this.sendBuffer.write(buffer, j);
            while (this.sendBuffer.size() >= 16384) {
                emitDataFrame(false);
            }
        }

        private void emitDataFrame(boolean z) throws IOException {
            long min;
            synchronized (FramedStream.this) {
                writeTimeout.enter();
                while (bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && errorCode == null) {
                    waitForIo();
                }
                writeTimeout.exitAndThrowIfTimedOut();
                checkOutNotClosed();
                min = Math.min(bytesLeftInWriteWindow, this.sendBuffer.size());
                bytesLeftInWriteWindow -= min;
            }
            writeTimeout.enter();
            try {
                connection.writeData(id, z && min == this.sendBuffer.size(), this.sendBuffer, min);
            } finally {
                writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        @Override
        public void flush() throws IOException {
            synchronized (FramedStream.this) {
                checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0) {
                emitDataFrame(false);
                connection.flush();
            }
        }

        @Override
        public Timeout timeout() {
            return writeTimeout;
        }

        @Override
        public void close() throws IOException {
            synchronized (FramedStream.this) {
                if (this.closed) {
                    return;
                }
                if (!sink.finished) {
                    if (this.sendBuffer.size() <= 0) {
                        connection.writeData(id, true, null, 0L);
                    } else {
                        while (this.sendBuffer.size() > 0) {
                            emitDataFrame(true);
                        }
                    }
                }
                synchronized (FramedStream.this) {
                    this.closed = true;
                }
                connection.flush();
                cancelStreamIfNecessary();
            }
        }
    }

    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void checkOutNotClosed() throws IOException {
        if (!this.sink.closed) {
            if (this.sink.finished) {
                throw new IOException("stream finished");
            }
            if (this.errorCode == null) {
                return;
            }
            throw new IOException("stream was reset: " + this.errorCode);
        }
        throw new IOException("stream closed");
    }

    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    public class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        @Override
        protected void timedOut() {
            closeLater(ErrorCode.CANCEL);
        }

        @Override
        protected IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException(ClientComponent.NamedSchedulers.TIMEOUT);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }
}
