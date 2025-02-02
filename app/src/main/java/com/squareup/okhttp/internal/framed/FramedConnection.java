package com.squareup.okhttp.internal.framed;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FrameReader;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
public final class FramedConnection implements Closeable {
    static final boolean $assertionsDisabled = false;
    private static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private static final ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp FramedConnection", true));
    long bytesLeftInWriteWindow;
    final boolean client;
    private final Set<Integer> currentPushRequests;
    final FrameWriter frameWriter;
    private final String hostName;
    private long idleStartTimeNs;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextPingId;
    private int nextStreamId;
    Settings okHttpSettings;
    final Settings peerSettings;
    private Map<Integer, Ping> pings;
    final Protocol protocol;
    private final ExecutorService pushExecutor;
    private final PushObserver pushObserver;
    final Reader readerRunnable;
    private boolean receivedInitialPeerSettings;
    private boolean shutdown;
    final Socket socket;
    private final Map<Integer, FramedStream> streams;
    long unacknowledgedBytesRead;
    final Variant variant;

    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() {
            @Override
            public void onStream(FramedStream framedStream) throws IOException {
                framedStream.close(ErrorCode.REFUSED_STREAM);
            }
        };

        public void onSettings(FramedConnection framedConnection) {
        }

        public abstract void onStream(FramedStream framedStream) throws IOException;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    private FramedConnection(Builder builder) throws IOException {
        this.streams = new HashMap();
        this.idleStartTimeNs = System.nanoTime();
        this.unacknowledgedBytesRead = 0L;
        this.okHttpSettings = new Settings();
        Settings settings = new Settings();
        this.peerSettings = settings;
        this.receivedInitialPeerSettings = false;
        this.currentPushRequests = new LinkedHashSet();
        Protocol protocol = builder.protocol;
        this.protocol = protocol;
        this.pushObserver = builder.pushObserver;
        boolean z = builder.client;
        this.client = z;
        this.listener = builder.listener;
        this.nextStreamId = builder.client ? 1 : 2;
        if (builder.client && protocol == Protocol.HTTP_2) {
            this.nextStreamId += 2;
        }
        this.nextPingId = builder.client ? 1 : 2;
        if (builder.client) {
            this.okHttpSettings.set(7, 0, 16777216);
        }
        String str = builder.hostName;
        this.hostName = str;
        if (protocol == Protocol.HTTP_2) {
            this.variant = new Http2();
            this.pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(String.format("OkHttp %s Push Observer", str), true));
            settings.set(7, 0, 65535);
            settings.set(5, 0, 16384);
        } else if (protocol == Protocol.SPDY_3) {
            this.variant = new Spdy3();
            this.pushExecutor = null;
        } else {
            throw new AssertionError(protocol);
        }
        this.bytesLeftInWriteWindow = settings.getInitialWindowSize(65536);
        this.socket = builder.socket;
        this.frameWriter = this.variant.newWriter(builder.sink, z);
        Reader reader = new Reader(this.variant.newReader(builder.source, z));
        this.readerRunnable = reader;
        new Thread(reader).start();
    }

    public synchronized int openStreamCount() {
        return this.streams.size();
    }

    synchronized FramedStream getStream(int i) {
        return this.streams.get(Integer.valueOf(i));
    }

    public synchronized FramedStream removeStream(int i) {
        FramedStream remove;
        remove = this.streams.remove(Integer.valueOf(i));
        if (remove != null && this.streams.isEmpty()) {
            setIdle(true);
        }
        notifyAll();
        return remove;
    }

    private synchronized void setIdle(boolean z) {
        long nanoTime;
        if (z) {
            try {
                nanoTime = System.nanoTime();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            nanoTime = Long.MAX_VALUE;
        }
        this.idleStartTimeNs = nanoTime;
    }

    public synchronized boolean isIdle() {
        return this.idleStartTimeNs != Long.MAX_VALUE;
    }

    public synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
    }

    public synchronized long getIdleStartTimeNs() {
        return this.idleStartTimeNs;
    }

    public FramedStream pushStream(int i, List<Header> list, boolean z) throws IOException {
        if (this.client) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        if (this.protocol != Protocol.HTTP_2) {
            throw new IllegalStateException("protocol != HTTP_2");
        }
        return newStream(i, list, z, false);
    }

    public FramedStream newStream(List<Header> list, boolean z, boolean z2) throws IOException {
        return newStream(0, list, z, z2);
    }

    private FramedStream newStream(int i, List<Header> list, boolean z, boolean z2) throws IOException {
        int i2;
        FramedStream framedStream;
        boolean z3 = !z;
        boolean z4 = !z2;
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new IOException("shutdown");
                }
                i2 = this.nextStreamId;
                this.nextStreamId = i2 + 2;
                framedStream = new FramedStream(i2, this, z3, z4, list);
                if (framedStream.isOpen()) {
                    this.streams.put(Integer.valueOf(i2), framedStream);
                    setIdle(false);
                }
            }
            if (i == 0) {
                this.frameWriter.synStream(z3, z4, i2, i, list);
            } else if (this.client) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.frameWriter.pushPromise(i, i2, list);
            }
        }
        if (!z) {
            this.frameWriter.flush();
        }
        return framedStream;
    }

    public void writeSynReply(int i, boolean z, List<Header> list) throws IOException {
        this.frameWriter.synReply(z, i, list);
    }

    public void writeData(int r9, boolean r10, okio.Buffer r11, long r12) throws java.io.IOException {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.writeData(int, boolean, okio.Buffer, long):void");
    }

    void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void writeSynResetLater(final int i, final ErrorCode errorCode) {
        executor.submit(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostName, Integer.valueOf(i)}) {
            @Override
            public void execute() {
                try {
                    writeSynReset(i, errorCode);
                } catch (IOException unused) {
                }
            }
        });
    }

    public void writeSynReset(int i, ErrorCode errorCode) throws IOException {
        this.frameWriter.rstStream(i, errorCode);
    }

    public void writeWindowUpdateLater(final int i, final long j) {
        executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostName, Integer.valueOf(i)}) {
            @Override
            public void execute() {
                try {
                    frameWriter.windowUpdate(i, j);
                } catch (IOException unused) {
                }
            }
        });
    }

    public Ping ping() throws IOException {
        int i;
        Ping ping = new Ping();
        synchronized (this) {
            if (this.shutdown) {
                throw new IOException("shutdown");
            }
            i = this.nextPingId;
            this.nextPingId = i + 2;
            if (this.pings == null) {
                this.pings = new HashMap();
            }
            this.pings.put(Integer.valueOf(i), ping);
        }
        writePing(false, i, 1330343787, ping);
        return ping;
    }

    public void writePingLater(final boolean z, final int i, final int i2, final Ping ping) {
        executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[]{this.hostName, Integer.valueOf(i), Integer.valueOf(i2)}) {
            @Override
            public void execute() {
                try {
                    writePing(z, i, i2, ping);
                } catch (IOException unused) {
                }
            }
        });
    }

    public void writePing(boolean z, int i, int i2, Ping ping) throws IOException {
        synchronized (this.frameWriter) {
            if (ping != null) {
                ping.send();
            }
            this.frameWriter.ping(z, i, i2);
        }
    }

    public synchronized Ping removePing(int i) {
        Map<Integer, Ping> map;
        map = this.pings;
        return map != null ? map.remove(Integer.valueOf(i)) : null;
    }

    public void flush() throws IOException {
        this.frameWriter.flush();
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (this.shutdown) {
                    return;
                }
                this.shutdown = true;
                this.frameWriter.goAway(this.lastGoodStreamId, errorCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }

    @Override
    public void close() throws IOException {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        int i;
        FramedStream[] framedStreamArr;
        Ping[] pingArr = null;
        try {
            shutdown(errorCode);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        synchronized (this) {
            if (this.streams.isEmpty()) {
                framedStreamArr = null;
            } else {
                framedStreamArr = (FramedStream[]) this.streams.values().toArray(new FramedStream[this.streams.size()]);
                this.streams.clear();
                setIdle(false);
            }
            Map<Integer, Ping> map = this.pings;
            if (map != null) {
                this.pings = null;
                pingArr = (Ping[]) map.values().toArray(new Ping[this.pings.size()]);
            }
        }
        if (framedStreamArr != null) {
            for (FramedStream framedStream : framedStreamArr) {
                try {
                    framedStream.close(errorCode2);
                } catch (IOException e2) {
                    if (e != null) {
                        e = e2;
                    }
                }
            }
        }
        if (pingArr != null) {
            for (Ping ping : pingArr) {
                ping.cancel();
            }
        }
        try {
            this.frameWriter.close();
        } catch (IOException e3) {
            if (e == null) {
                e = e3;
            }
        }
        try {
            this.socket.close();
        } catch (IOException e4) {
            e = e4;
        }
        if (e != null) {
            throw e;
        }
    }

    public void sendConnectionPreface() throws IOException {
        this.frameWriter.connectionPreface();
        this.frameWriter.settings(this.okHttpSettings);
        int initialWindowSize = this.okHttpSettings.getInitialWindowSize(65536);
        if (initialWindowSize != 65536) {
            this.frameWriter.windowUpdate(0, initialWindowSize - 65536);
        }
    }

    public void setSettings(Settings settings) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new IOException("shutdown");
                }
                this.okHttpSettings.merge(settings);
                this.frameWriter.settings(settings);
            }
        }
    }

    public static class Builder {
        private boolean client;
        private String hostName;
        private Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        private Protocol protocol = Protocol.SPDY_3;
        private PushObserver pushObserver = PushObserver.CANCEL;
        private BufferedSink sink;
        private Socket socket;
        private BufferedSource source;

        public Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder protocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver) {
            this.pushObserver = pushObserver;
            return this;
        }

        public Builder socket(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.socket = socket;
            this.hostName = str;
            this.source = bufferedSource;
            this.sink = bufferedSink;
            return this;
        }

        public Builder(boolean z) throws IOException {
            this.client = z;
        }

        public Builder socket(Socket socket) throws IOException {
            return socket(socket, ((InetSocketAddress) socket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket)));
        }

        public FramedConnection build() throws IOException {
            return new FramedConnection(this);
        }
    }

    class Reader extends NamedRunnable implements FrameReader.Handler {
        final FrameReader frameReader;

        @Override
        public void ackSettings() {
        }

        @Override
        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }

        @Override
        public void priority(int i, int i2, int i3, boolean z) {
        }

        private Reader(FrameReader frameReader) {
            super("OkHttp %s", hostName);
            this.frameReader = frameReader;
        }

        @Override
        protected void execute() {
            ErrorCode errorCode;
            FramedConnection framedConnection;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    try {
                        if (!client) {
                            this.frameReader.readConnectionPreface();
                        }
                        while (this.frameReader.nextFrame(this)) {
                        }
                        errorCode2 = ErrorCode.NO_ERROR;
                        errorCode = ErrorCode.CANCEL;
                        framedConnection = FramedConnection.this;
                    } catch (IOException unused) {
                    }
                } catch (IOException unused2) {
                    errorCode2 = ErrorCode.PROTOCOL_ERROR;
                    errorCode = ErrorCode.PROTOCOL_ERROR;
                    framedConnection = FramedConnection.this;
                }
                framedConnection.close(errorCode2, errorCode);
                Util.closeQuietly(this.frameReader);
            } catch (Throwable th) {
                try {
                    close(errorCode2, errorCode3);
                } catch (IOException unused3) {
                }
                Util.closeQuietly(this.frameReader);
                throw th;
            }
        }

        @Override
        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (pushedStream(i)) {
                pushDataLater(i, bufferedSource, i2, z);
                return;
            }
            FramedStream stream = getStream(i);
            if (stream == null) {
                writeSynResetLater(i, ErrorCode.INVALID_STREAM);
                bufferedSource.skip(i2);
                return;
            }
            stream.receiveData(bufferedSource, i2);
            if (z) {
                stream.receiveFin();
            }
        }

        @Override
        public void headers(boolean z, boolean z2, int i, int i2, List<Header> list, HeadersMode headersMode) {
            if (pushedStream(i)) {
                pushHeadersLater(i, list, z2);
                return;
            }
            synchronized (FramedConnection.this) {
                if (shutdown) {
                    return;
                }
                FramedStream stream = getStream(i);
                if (stream == null) {
                    if (!headersMode.failIfStreamAbsent()) {
                        if (i <= lastGoodStreamId) {
                            return;
                        }
                        if (i % 2 == nextStreamId % 2) {
                            return;
                        }
                        final FramedStream framedStream = new FramedStream(i, FramedConnection.this, z, z2, list);
                        lastGoodStreamId = i;
                        streams.put(Integer.valueOf(i), framedStream);
                        FramedConnection.executor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{hostName, Integer.valueOf(i)}) {
                            @Override
                            public void execute() {
                                try {
                                    listener.onStream(framedStream);
                                } catch (IOException e) {
                                    Logger logger = Internal.logger;
                                    Level level = Level.INFO;
                                    logger.log(level, "FramedConnection.Listener failure for " + hostName, (Throwable) e);
                                    try {
                                        framedStream.close(ErrorCode.PROTOCOL_ERROR);
                                    } catch (IOException unused) {
                                    }
                                }
                            }
                        });
                        return;
                    }
                    writeSynResetLater(i, ErrorCode.INVALID_STREAM);
                } else if (headersMode.failIfStreamPresent()) {
                    stream.closeLater(ErrorCode.PROTOCOL_ERROR);
                    removeStream(i);
                } else {
                    stream.receiveHeaders(list, headersMode);
                    if (z2) {
                        stream.receiveFin();
                    }
                }
            }
        }

        @Override
        public void rstStream(int i, ErrorCode errorCode) {
            if (pushedStream(i)) {
                pushResetLater(i, errorCode);
                return;
            }
            FramedStream removeStream = removeStream(i);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }

        @Override
        public void settings(boolean z, Settings settings) {
            FramedStream[] framedStreamArr;
            long j;
            int i;
            synchronized (FramedConnection.this) {
                int initialWindowSize = peerSettings.getInitialWindowSize(65536);
                if (z) {
                    peerSettings.clear();
                }
                peerSettings.merge(settings);
                if (getProtocol() == Protocol.HTTP_2) {
                    ackSettingsLater(settings);
                }
                int initialWindowSize2 = peerSettings.getInitialWindowSize(65536);
                framedStreamArr = null;
                if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                    j = 0;
                } else {
                    j = initialWindowSize2 - initialWindowSize;
                    if (!receivedInitialPeerSettings) {
                        addBytesToWriteWindow(j);
                        receivedInitialPeerSettings = true;
                    }
                    if (!streams.isEmpty()) {
                        framedStreamArr = (FramedStream[]) streams.values().toArray(new FramedStream[streams.size()]);
                    }
                }
                FramedConnection.executor.execute(new NamedRunnable("OkHttp %s settings", hostName) {
                    @Override
                    public void execute() {
                        listener.onSettings(FramedConnection.this);
                    }
                });
            }
            if (framedStreamArr == null || j == 0) {
                return;
            }
            for (FramedStream framedStream : framedStreamArr) {
                synchronized (framedStream) {
                    framedStream.addBytesToWriteWindow(j);
                }
            }
        }

        private void ackSettingsLater(final Settings settings) {
            FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{hostName}) {
                @Override
                public void execute() {
                    try {
                        frameWriter.ackSettings(settings);
                    } catch (IOException unused) {
                    }
                }
            });
        }

        @Override
        public void ping(boolean z, int i, int i2) {
            if (z) {
                Ping removePing = removePing(i);
                if (removePing != null) {
                    removePing.receive();
                    return;
                }
                return;
            }
            writePingLater(true, i, i2, null);
        }

        @Override
        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            FramedStream[] framedStreamArr;
            byteString.size();
            synchronized (FramedConnection.this) {
                framedStreamArr = (FramedStream[]) streams.values().toArray(new FramedStream[streams.size()]);
                shutdown = true;
            }
            for (FramedStream framedStream : framedStreamArr) {
                if (framedStream.getId() > i && framedStream.isLocallyInitiated()) {
                    framedStream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    removeStream(framedStream.getId());
                }
            }
        }

        @Override
        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (FramedConnection.this) {
                    bytesLeftInWriteWindow += j;
                    notifyAll();
                }
                return;
            }
            FramedStream stream = getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                }
            }
        }

        @Override
        public void pushPromise(int i, int i2, List<Header> list) {
            pushRequestLater(i2, list);
        }
    }

    public boolean pushedStream(int i) {
        return this.protocol == Protocol.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    public void pushRequestLater(final int i, final List<Header> list) {
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(i))) {
                writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(i));
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
                @Override
                public void execute() {
                    if (pushObserver.onRequest(i, list)) {
                        try {
                            frameWriter.rstStream(i, ErrorCode.CANCEL);
                            synchronized (FramedConnection.this) {
                                currentPushRequests.remove(Integer.valueOf(i));
                            }
                        } catch (IOException unused) {
                        }
                    }
                }
            });
        }
    }

    public void pushHeadersLater(final int i, final List<Header> list, final boolean z) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            @Override
            public void execute() {
                boolean onHeaders = pushObserver.onHeaders(i, list, z);
                if (onHeaders) {
                    try {
                        frameWriter.rstStream(i, ErrorCode.CANCEL);
                    } catch (IOException unused) {
                        return;
                    }
                }
                if (onHeaders || z) {
                    synchronized (FramedConnection.this) {
                        currentPushRequests.remove(Integer.valueOf(i));
                    }
                }
            }
        });
    }

    public void pushDataLater(final int i, BufferedSource bufferedSource, final int i2, final boolean z) throws IOException {
        final Buffer buffer = new Buffer();
        long j = i2;
        bufferedSource.require(j);
        bufferedSource.read(buffer, j);
        if (buffer.size() != j) {
            throw new IOException(buffer.size() + " != " + i2);
        }
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            @Override
            public void execute() {
                try {
                    boolean onData = pushObserver.onData(i, buffer, i2, z);
                    if (onData) {
                        frameWriter.rstStream(i, ErrorCode.CANCEL);
                    }
                    if (onData || z) {
                        synchronized (FramedConnection.this) {
                            currentPushRequests.remove(Integer.valueOf(i));
                        }
                    }
                } catch (IOException unused) {
                }
            }
        });
    }

    public void pushResetLater(final int i, final ErrorCode errorCode) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            @Override
            public void execute() {
                pushObserver.onReset(i, errorCode);
                synchronized (FramedConnection.this) {
                    currentPushRequests.remove(Integer.valueOf(i));
                }
            }
        });
    }
}
