package io.grpc.okhttp;

import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.http.StatusLine;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Grpc;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.OkHttpClientStream;
import io.grpc.okhttp.OkHttpFrameLogger;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.HeadersMode;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.perfmark.PerfMark;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.ws.RealWebSocket;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import okio.Timeout;
public class OkHttpClientTransport implements ConnectionClientTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler {
    private final InetSocketAddress address;
    private Attributes attributes;
    private ClientFrameHandler clientFrameHandler;
    SettableFuture<Void> connectedFuture;
    Runnable connectingCallback;
    private final ConnectionSpec connectionSpec;
    private int connectionUnacknowledgedBytesRead;
    private final String defaultAuthority;
    private boolean enableKeepAlive;
    private final Executor executor;
    private ExceptionHandlingFrameWriter frameWriter;
    private boolean goAwaySent;
    private Status goAwayStatus;
    private boolean hasStream;
    private HostnameVerifier hostnameVerifier;
    private final InUseStateAggregator<OkHttpClientStream> inUseState;
    private final int initialWindowSize;
    private KeepAliveManager keepAliveManager;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private ManagedClientTransport.Listener listener;
    private final Object lock;
    private final InternalLogId logId;
    private int maxConcurrentStreams;
    private final int maxInboundMetadataSize;
    private final int maxMessageSize;
    private int nextStreamId;
    private OutboundFlowController outboundFlow;
    private final Deque<OkHttpClientStream> pendingStreams;
    private Http2Ping ping;
    @Nullable
    final HttpConnectProxiedSocketAddress proxiedAddr;
    private final Random random;
    private ScheduledExecutorService scheduler;
    private InternalChannelz.Security securityInfo;
    private final SerializingExecutor serializingExecutor;
    private Socket socket;
    private final SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private boolean stopped;
    private final Supplier<Stopwatch> stopwatchFactory;
    private final Map<Integer, OkHttpClientStream> streams;
    private OkHttpFrameLogger testFrameLogger;
    private FrameReader testFrameReader;
    private FrameWriter testFrameWriter;
    private final Runnable tooManyPingsRunnable;
    private final TransportTracer transportTracer;
    private final boolean useGetForSafeMethods;
    private final String userAgent;
    private static final Map<ErrorCode, Status> ERROR_CODE_TO_STATUS = buildErrorCodeToStatusMap();
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private static final OkHttpClientStream[] EMPTY_STREAM_ARRAY = new OkHttpClientStream[0];

    private boolean isForTest() {
        return this.address == null;
    }

    public void enableKeepAlive(boolean z, long j, long j2, boolean z2) {
        this.enableKeepAlive = z;
        this.keepAliveTimeNanos = j;
        this.keepAliveTimeoutNanos = j2;
        this.keepAliveWithoutCalls = z2;
    }

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    ClientFrameHandler getHandler() {
        return this.clientFrameHandler;
    }

    @Override
    public InternalLogId getLogId() {
        return this.logId;
    }

    SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public boolean isUsingPlaintext() {
        return this.sslSocketFactory == null;
    }

    static int Fun$2312(OkHttpClientTransport okHttpClientTransport, int i) {
        int i2 = okHttpClientTransport.connectionUnacknowledgedBytesRead + i;
        okHttpClientTransport.connectionUnacknowledgedBytesRead = i2;
        return i2;
    }

    @Override
    public ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions) {
        return newStream((MethodDescriptor<?, ?>) methodDescriptor, metadata, callOptions);
    }

    private static Map<ErrorCode, Status> buildErrorCodeToStatusMap() {
        EnumMap enumMap = new EnumMap(ErrorCode.class);
        enumMap.put((EnumMap) ErrorCode.NO_ERROR, (ErrorCode) Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        enumMap.put((EnumMap) ErrorCode.PROTOCOL_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Protocol error"));
        enumMap.put((EnumMap) ErrorCode.INTERNAL_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Internal error"));
        enumMap.put((EnumMap) ErrorCode.FLOW_CONTROL_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Flow control error"));
        enumMap.put((EnumMap) ErrorCode.STREAM_CLOSED, (ErrorCode) Status.INTERNAL.withDescription("Stream closed"));
        enumMap.put((EnumMap) ErrorCode.FRAME_TOO_LARGE, (ErrorCode) Status.INTERNAL.withDescription("Frame too large"));
        enumMap.put((EnumMap) ErrorCode.REFUSED_STREAM, (ErrorCode) Status.UNAVAILABLE.withDescription("Refused stream"));
        enumMap.put((EnumMap) ErrorCode.CANCEL, (ErrorCode) Status.CANCELLED.withDescription("Cancelled"));
        enumMap.put((EnumMap) ErrorCode.COMPRESSION_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Compression error"));
        enumMap.put((EnumMap) ErrorCode.CONNECT_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Connect error"));
        enumMap.put((EnumMap) ErrorCode.ENHANCE_YOUR_CALM, (ErrorCode) Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        enumMap.put((EnumMap) ErrorCode.INADEQUATE_SECURITY, (ErrorCode) Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        return Collections.unmodifiableMap(enumMap);
    }

    public OkHttpClientTransport(InetSocketAddress inetSocketAddress, String str, @Nullable String str2, Attributes attributes, Executor executor, @Nullable SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, ConnectionSpec connectionSpec, int i, int i2, @Nullable HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress, Runnable runnable, int i3, TransportTracer transportTracer, boolean z) {
        this.random = new Random();
        this.lock = new Object();
        this.streams = new HashMap();
        this.maxConcurrentStreams = 0;
        this.pendingStreams = new LinkedList();
        this.inUseState = new InUseStateAggregator<OkHttpClientStream>() {
            @Override
            protected void handleInUse() {
                listener.transportInUse(true);
            }

            @Override
            protected void handleNotInUse() {
                listener.transportInUse(false);
            }
        };
        this.address = (InetSocketAddress) Preconditions.checkNotNull(inetSocketAddress, "address");
        this.defaultAuthority = str;
        this.maxMessageSize = i;
        this.initialWindowSize = i2;
        this.executor = (Executor) Preconditions.checkNotNull(executor, "executor");
        this.serializingExecutor = new SerializingExecutor(executor);
        this.nextStreamId = 3;
        this.socketFactory = socketFactory == null ? SocketFactory.getDefault() : socketFactory;
        this.sslSocketFactory = sSLSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
        this.connectionSpec = (ConnectionSpec) Preconditions.checkNotNull(connectionSpec, "connectionSpec");
        this.stopwatchFactory = GrpcUtil.STOPWATCH_SUPPLIER;
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", str2);
        this.proxiedAddr = httpConnectProxiedSocketAddress;
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(runnable, "tooManyPingsRunnable");
        this.maxInboundMetadataSize = i3;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer);
        this.logId = InternalLogId.allocate(getClass(), inetSocketAddress.toString());
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, attributes).build();
        this.useGetForSafeMethods = z;
        initTransportTracer();
    }

    OkHttpClientTransport(String str, Executor executor, FrameReader frameReader, FrameWriter frameWriter, OkHttpFrameLogger okHttpFrameLogger, int i, Socket socket, Supplier<Stopwatch> supplier, @Nullable Runnable runnable, SettableFuture<Void> settableFuture, int i2, int i3, Runnable runnable2, TransportTracer transportTracer) {
        this.random = new Random();
        this.lock = new Object();
        this.streams = new HashMap();
        this.maxConcurrentStreams = 0;
        this.pendingStreams = new LinkedList();
        this.inUseState = new InUseStateAggregator<OkHttpClientStream>() {
            @Override
            protected void handleInUse() {
                listener.transportInUse(true);
            }

            @Override
            protected void handleNotInUse() {
                listener.transportInUse(false);
            }
        };
        this.useGetForSafeMethods = false;
        this.address = null;
        this.maxMessageSize = i2;
        this.initialWindowSize = i3;
        this.defaultAuthority = "notarealauthority:80";
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", str);
        this.executor = (Executor) Preconditions.checkNotNull(executor, "executor");
        this.serializingExecutor = new SerializingExecutor(executor);
        this.socketFactory = SocketFactory.getDefault();
        this.testFrameReader = (FrameReader) Preconditions.checkNotNull(frameReader, "frameReader");
        this.testFrameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter, "testFrameWriter");
        this.testFrameLogger = (OkHttpFrameLogger) Preconditions.checkNotNull(okHttpFrameLogger, "testFrameLogger");
        this.socket = (Socket) Preconditions.checkNotNull(socket, "socket");
        this.nextStreamId = i;
        this.stopwatchFactory = supplier;
        this.connectionSpec = null;
        this.connectingCallback = runnable;
        this.connectedFuture = (SettableFuture) Preconditions.checkNotNull(settableFuture, "connectedFuture");
        this.proxiedAddr = null;
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(runnable2, "tooManyPingsRunnable");
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.logId = InternalLogId.allocate(getClass(), String.valueOf(socket.getInetAddress()));
        initTransportTracer();
    }

    private void initTransportTracer() {
        synchronized (this.lock) {
            this.transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader() {
                @Override
                public TransportTracer.FlowControlWindows read() {
                    TransportTracer.FlowControlWindows flowControlWindows;
                    synchronized (lock) {
                        flowControlWindows = new TransportTracer.FlowControlWindows(-1L, outboundFlow == null ? -1L : outboundFlow.windowUpdate(null, 0));
                    }
                    return flowControlWindows;
                }
            });
        }
    }

    @Override
    public void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
        long nextLong;
        synchronized (this.lock) {
            boolean z = true;
            Preconditions.checkState(this.frameWriter != null);
            if (this.stopped) {
                Http2Ping.notifyFailed(pingCallback, executor, getPingFailure());
                return;
            }
            Http2Ping http2Ping = this.ping;
            if (http2Ping != null) {
                nextLong = 0;
                z = false;
            } else {
                nextLong = this.random.nextLong();
                Stopwatch stopwatch = this.stopwatchFactory.get();
                stopwatch.start();
                Http2Ping http2Ping2 = new Http2Ping(nextLong, stopwatch);
                this.ping = http2Ping2;
                this.transportTracer.reportKeepAliveSent();
                http2Ping = http2Ping2;
            }
            if (z) {
                this.frameWriter.ping(false, (int) (nextLong >>> 32), (int) nextLong);
            }
            http2Ping.addCallback(pingCallback, executor);
        }
    }

    @Override
    public OkHttpClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        Preconditions.checkNotNull(methodDescriptor, FirebaseAnalytics.Param.METHOD);
        Preconditions.checkNotNull(metadata, "headers");
        StatsTraceContext newClientContext = StatsTraceContext.newClientContext(callOptions, this.attributes, metadata);
        synchronized (this.lock) {
            try {
                try {
                    return new OkHttpClientStream(methodDescriptor, metadata, this.frameWriter, this, this.outboundFlow, this.lock, this.maxMessageSize, this.initialWindowSize, this.defaultAuthority, this.userAgent, newClientContext, this.transportTracer, callOptions, this.useGetForSafeMethods);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public void streamReadyToStart(OkHttpClientStream okHttpClientStream) {
        if (this.goAwayStatus != null) {
            okHttpClientStream.transportState().transportReportStatus(this.goAwayStatus, ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
        } else if (this.streams.size() >= this.maxConcurrentStreams) {
            this.pendingStreams.add(okHttpClientStream);
            setInUse(okHttpClientStream);
        } else {
            startStream(okHttpClientStream);
        }
    }

    private void startStream(OkHttpClientStream okHttpClientStream) {
        Preconditions.checkState(okHttpClientStream.id() == -1, "StreamId already assigned");
        this.streams.put(Integer.valueOf(this.nextStreamId), okHttpClientStream);
        setInUse(okHttpClientStream);
        okHttpClientStream.transportState().start(this.nextStreamId);
        if ((okHttpClientStream.getType() != MethodDescriptor.MethodType.UNARY && okHttpClientStream.getType() != MethodDescriptor.MethodType.SERVER_STREAMING) || okHttpClientStream.useGet()) {
            this.frameWriter.flush();
        }
        int i = this.nextStreamId;
        if (i < 2147483645) {
            this.nextStreamId = i + 2;
            return;
        }
        this.nextStreamId = Integer.MAX_VALUE;
        startGoAway(Integer.MAX_VALUE, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
    }

    public boolean startPendingStreams() {
        boolean z = false;
        while (!this.pendingStreams.isEmpty() && this.streams.size() < this.maxConcurrentStreams) {
            startStream(this.pendingStreams.poll());
            z = true;
        }
        return z;
    }

    public void removePendingStream(OkHttpClientStream okHttpClientStream) {
        this.pendingStreams.remove(okHttpClientStream);
        maybeClearInUse(okHttpClientStream);
    }

    @Override
    public Runnable start(ManagedClientTransport.Listener listener) {
        this.listener = (ManagedClientTransport.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (this.enableKeepAlive) {
            this.scheduler = (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE);
            KeepAliveManager keepAliveManager = new KeepAliveManager(new KeepAliveManager.ClientKeepAlivePinger(this), this.scheduler, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            this.keepAliveManager = keepAliveManager;
            keepAliveManager.onTransportStarted();
        }
        if (isForTest()) {
            synchronized (this.lock) {
                ExceptionHandlingFrameWriter exceptionHandlingFrameWriter = new ExceptionHandlingFrameWriter(this, this.testFrameWriter, this.testFrameLogger);
                this.frameWriter = exceptionHandlingFrameWriter;
                this.outboundFlow = new OutboundFlowController(this, exceptionHandlingFrameWriter);
            }
            this.serializingExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (connectingCallback != null) {
                        connectingCallback.run();
                    }
                    OkHttpClientTransport okHttpClientTransport = OkHttpClientTransport.this;
                    okHttpClientTransport.clientFrameHandler = new ClientFrameHandler(okHttpClientTransport.testFrameReader, testFrameLogger);
                    executor.execute(clientFrameHandler);
                    synchronized (lock) {
                        maxConcurrentStreams = Integer.MAX_VALUE;
                        startPendingStreams();
                    }
                    connectedFuture.set(null);
                }
            });
            return null;
        }
        final AsyncSink sink = AsyncSink.sink(this.serializingExecutor, this);
        final Http2 http2 = new Http2();
        FrameWriter newWriter = http2.newWriter(Okio.buffer(sink), true);
        synchronized (this.lock) {
            ExceptionHandlingFrameWriter exceptionHandlingFrameWriter2 = new ExceptionHandlingFrameWriter(this, newWriter);
            this.frameWriter = exceptionHandlingFrameWriter2;
            this.outboundFlow = new OutboundFlowController(this, exceptionHandlingFrameWriter2);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.serializingExecutor.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClientTransport okHttpClientTransport;
                ClientFrameHandler clientFrameHandler;
                Socket createHttpProxySocket;
                SSLSession sSLSession;
                SSLSocket sSLSocket;
                try {
                    countDownLatch.await();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                BufferedSource buffer = Okio.buffer(new Source() {
                    @Override
                    public void close() {
                    }

                    @Override
                    public long read(Buffer buffer2, long j) {
                        return -1L;
                    }

                    @Override
                    public Timeout timeout() {
                        return Timeout.NONE;
                    }
                });
                try {
                    try {
                        if (proxiedAddr == null) {
                            createHttpProxySocket = socketFactory.createSocket(address.getAddress(), address.getPort());
                        } else if (proxiedAddr.getProxyAddress() instanceof InetSocketAddress) {
                            OkHttpClientTransport okHttpClientTransport2 = OkHttpClientTransport.this;
                            createHttpProxySocket = okHttpClientTransport2.createHttpProxySocket(okHttpClientTransport2.proxiedAddr.getTargetAddress(), (InetSocketAddress) proxiedAddr.getProxyAddress(), proxiedAddr.getUsername(), proxiedAddr.getPassword());
                        } else {
                            Status status = Status.INTERNAL;
                            throw status.withDescription("Unsupported SocketAddress implementation " + proxiedAddr.getProxyAddress().getClass()).asException();
                        }
                        Socket socket = createHttpProxySocket;
                        if (sslSocketFactory != null) {
                            SSLSocket upgrade = OkHttpTlsUpgrader.upgrade(sslSocketFactory, hostnameVerifier, socket, getOverridenHost(), getOverridenPort(), connectionSpec);
                            sSLSession = upgrade.getSession();
                            sSLSocket = upgrade;
                        } else {
                            sSLSession = null;
                            sSLSocket = socket;
                        }
                        sSLSocket.setTcpNoDelay(true);
                        BufferedSource buffer2 = Okio.buffer(Okio.source(sSLSocket));
                        sink.becomeConnected(Okio.sink(sSLSocket), sSLSocket);
                        OkHttpClientTransport okHttpClientTransport3 = OkHttpClientTransport.this;
                        okHttpClientTransport3.attributes = okHttpClientTransport3.attributes.toBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, sSLSocket.getRemoteSocketAddress()).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, sSLSocket.getLocalSocketAddress()).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, sSLSession).set(GrpcAttributes.ATTR_SECURITY_LEVEL, sSLSession == null ? SecurityLevel.NONE : SecurityLevel.PRIVACY_AND_INTEGRITY).build();
                        OkHttpClientTransport okHttpClientTransport4 = OkHttpClientTransport.this;
                        okHttpClientTransport4.clientFrameHandler = new ClientFrameHandler(okHttpClientTransport4, http2.newReader(buffer2, true));
                        synchronized (lock) {
                            socket = (Socket) Preconditions.checkNotNull(sSLSocket, "socket");
                            if (sSLSession != null) {
                                securityInfo = new InternalChannelz.Security(new InternalChannelz.Tls(sSLSession));
                            }
                        }
                    } catch (Throwable th) {
                        OkHttpClientTransport okHttpClientTransport5 = OkHttpClientTransport.this;
                        okHttpClientTransport5.clientFrameHandler = new ClientFrameHandler(okHttpClientTransport5, http2.newReader(buffer, true));
                        throw th;
                    }
                } catch (StatusException e) {
                    startGoAway(0, ErrorCode.INTERNAL_ERROR, e.getStatus());
                    okHttpClientTransport = OkHttpClientTransport.this;
                    clientFrameHandler = new ClientFrameHandler(okHttpClientTransport, http2.newReader(buffer, true));
                    okHttpClientTransport.clientFrameHandler = clientFrameHandler;
                } catch (Exception e2) {
                    onException(e2);
                    okHttpClientTransport = OkHttpClientTransport.this;
                    clientFrameHandler = new ClientFrameHandler(okHttpClientTransport, http2.newReader(buffer, true));
                    okHttpClientTransport.clientFrameHandler = clientFrameHandler;
                }
            }
        });
        try {
            sendConnectionPrefaceAndSettings();
            countDownLatch.countDown();
            this.serializingExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    executor.execute(clientFrameHandler);
                    synchronized (lock) {
                        maxConcurrentStreams = Integer.MAX_VALUE;
                        startPendingStreams();
                    }
                }
            });
            return null;
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    void sendConnectionPrefaceAndSettings() {
        synchronized (this.lock) {
            this.frameWriter.connectionPreface();
            Settings settings = new Settings();
            OkHttpSettingsUtil.set(settings, 7, this.initialWindowSize);
            this.frameWriter.settings(settings);
            int i = this.initialWindowSize;
            if (i > 65535) {
                this.frameWriter.windowUpdate(0, i - 65535);
            }
        }
    }

    public Socket createHttpProxySocket(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, String str, String str2) throws StatusException {
        Socket createSocket;
        try {
            if (inetSocketAddress2.getAddress() != null) {
                createSocket = this.socketFactory.createSocket(inetSocketAddress2.getAddress(), inetSocketAddress2.getPort());
            } else {
                createSocket = this.socketFactory.createSocket(inetSocketAddress2.getHostName(), inetSocketAddress2.getPort());
            }
            createSocket.setTcpNoDelay(true);
            Source source = Okio.source(createSocket);
            BufferedSink buffer = Okio.buffer(Okio.sink(createSocket));
            Request createHttpProxyRequest = createHttpProxyRequest(inetSocketAddress, str, str2);
            HttpUrl httpUrl = createHttpProxyRequest.httpUrl();
            buffer.writeUtf8(String.format("CONNECT %s:%d HTTP/1.1", httpUrl.host(), Integer.valueOf(httpUrl.port()))).writeUtf8("\r\n");
            int size = createHttpProxyRequest.headers().size();
            for (int i = 0; i < size; i++) {
                buffer.writeUtf8(createHttpProxyRequest.headers().name(i)).writeUtf8(": ").writeUtf8(createHttpProxyRequest.headers().value(i)).writeUtf8("\r\n");
            }
            buffer.writeUtf8("\r\n");
            buffer.flush();
            StatusLine parse = StatusLine.parse(readUtf8LineStrictUnbuffered(source));
            while (!readUtf8LineStrictUnbuffered(source).equals("")) {
            }
            if (parse.code < 200 || parse.code >= 300) {
                Buffer buffer2 = new Buffer();
                try {
                    createSocket.shutdownOutput();
                    source.read(buffer2, RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE);
                } catch (IOException e) {
                    buffer2.writeUtf8("Unable to read body: " + e.toString());
                }
                try {
                    createSocket.close();
                } catch (IOException unused) {
                }
                throw Status.UNAVAILABLE.withDescription(String.format("Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s", Integer.valueOf(parse.code), parse.message, buffer2.readUtf8())).asException();
            }
            return createSocket;
        } catch (IOException e2) {
            throw Status.UNAVAILABLE.withDescription("Failed trying to connect with proxy").withCause(e2).asException();
        }
    }

    private Request createHttpProxyRequest(InetSocketAddress inetSocketAddress, String str, String str2) {
        HttpUrl build = new HttpUrl.Builder().scheme(UriUtil.HTTPS_SCHEME).host(inetSocketAddress.getHostName()).port(inetSocketAddress.getPort()).build();
        Request.Builder url = new Request.Builder().url(build);
        Request.Builder header = url.header(HttpHeaders.HOST, build.host() + ":" + build.port()).header(HttpHeaders.USER_AGENT, this.userAgent);
        if (str != null && str2 != null) {
            header.header(HttpHeaders.PROXY_AUTHORIZATION, Credentials.basic(str, str2));
        }
        return header.build();
    }

    private static String readUtf8LineStrictUnbuffered(Source source) throws IOException {
        Buffer buffer = new Buffer();
        while (source.read(buffer, 1L) != -1) {
            if (buffer.getByte(buffer.size() - 1) == 10) {
                return buffer.readUtf8LineStrict();
            }
        }
        throw new EOFException("\\n not found: " + buffer.readByteString().hex());
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).toString();
    }

    String getOverridenHost() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        return authorityToUri.getHost() != null ? authorityToUri.getHost() : this.defaultAuthority;
    }

    int getOverridenPort() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getPort() != -1) {
            return authorityToUri.getPort();
        }
        return this.address.getPort();
    }

    @Override
    public void shutdown(Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus != null) {
                return;
            }
            this.goAwayStatus = status;
            this.listener.transportShutdown(status);
            stopIfNecessary();
        }
    }

    @Override
    public void shutdownNow(Status status) {
        shutdown(status);
        synchronized (this.lock) {
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, OkHttpClientStream> next = it.next();
                it.remove();
                next.getValue().transportState().transportReportStatus(status, false, new Metadata());
                maybeClearInUse(next.getValue());
            }
            for (OkHttpClientStream okHttpClientStream : this.pendingStreams) {
                okHttpClientStream.transportState().transportReportStatus(status, true, new Metadata());
                maybeClearInUse(okHttpClientStream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    public OkHttpClientStream[] getActiveStreams() {
        OkHttpClientStream[] okHttpClientStreamArr;
        synchronized (this.lock) {
            okHttpClientStreamArr = (OkHttpClientStream[]) this.streams.values().toArray(EMPTY_STREAM_ARRAY);
        }
        return okHttpClientStreamArr;
    }

    int getPendingStreamSize() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    @Override
    public void onException(Throwable th) {
        Preconditions.checkNotNull(th, "failureCause");
        startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withCause(th));
    }

    public void onError(ErrorCode errorCode, String str) {
        startGoAway(0, errorCode, toGrpcStatus(errorCode).augmentDescription(str));
    }

    public void startGoAway(int i, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, errorCode, new byte[0]);
            }
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, OkHttpClientStream> next = it.next();
                if (next.getKey().intValue() > i) {
                    it.remove();
                    next.getValue().transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, false, new Metadata());
                    maybeClearInUse(next.getValue());
                }
            }
            for (OkHttpClientStream okHttpClientStream : this.pendingStreams) {
                okHttpClientStream.transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, true, new Metadata());
                maybeClearInUse(okHttpClientStream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    public void finishStream(int i, @Nullable Status status, ClientStreamListener.RpcProgress rpcProgress, boolean z, @Nullable ErrorCode errorCode, @Nullable Metadata metadata) {
        synchronized (this.lock) {
            OkHttpClientStream remove = this.streams.remove(Integer.valueOf(i));
            if (remove != null) {
                if (errorCode != null) {
                    this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                }
                if (status != null) {
                    OkHttpClientStream.TransportState transportState = remove.transportState();
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportState.transportReportStatus(status, rpcProgress, z, metadata);
                }
                if (!startPendingStreams()) {
                    stopIfNecessary();
                    maybeClearInUse(remove);
                }
            }
        }
    }

    private void stopIfNecessary() {
        if (this.goAwayStatus == null || !this.streams.isEmpty() || !this.pendingStreams.isEmpty() || this.stopped) {
            return;
        }
        this.stopped = true;
        KeepAliveManager keepAliveManager = this.keepAliveManager;
        if (keepAliveManager != null) {
            keepAliveManager.onTransportTermination();
            this.scheduler = (ScheduledExecutorService) SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.scheduler);
        }
        Http2Ping http2Ping = this.ping;
        if (http2Ping != null) {
            http2Ping.failed(getPingFailure());
            this.ping = null;
        }
        if (!this.goAwaySent) {
            this.goAwaySent = true;
            this.frameWriter.goAway(0, ErrorCode.NO_ERROR, new byte[0]);
        }
        this.frameWriter.close();
    }

    private void maybeClearInUse(OkHttpClientStream okHttpClientStream) {
        if (this.hasStream && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.hasStream = false;
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportIdle();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(okHttpClientStream, false);
        }
    }

    private void setInUse(OkHttpClientStream okHttpClientStream) {
        if (!this.hasStream) {
            this.hasStream = true;
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportActive();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(okHttpClientStream, true);
        }
    }

    private Throwable getPingFailure() {
        synchronized (this.lock) {
            Status status = this.goAwayStatus;
            if (status != null) {
                return status.asException();
            }
            return Status.UNAVAILABLE.withDescription("Connection closed").asException();
        }
    }

    boolean mayHaveCreatedStream(int i) {
        boolean z;
        synchronized (this.lock) {
            if (i < this.nextStreamId) {
                z = true;
                if ((i & 1) == 1) {
                }
            }
            z = false;
        }
        return z;
    }

    public OkHttpClientStream getStream(int i) {
        OkHttpClientStream okHttpClientStream;
        synchronized (this.lock) {
            okHttpClientStream = this.streams.get(Integer.valueOf(i));
        }
        return okHttpClientStream;
    }

    static Status toGrpcStatus(ErrorCode errorCode) {
        Status status = ERROR_CODE_TO_STATUS.get(errorCode);
        if (status != null) {
            return status;
        }
        Status status2 = Status.UNKNOWN;
        return status2.withDescription("Unknown http2 error code: " + errorCode.httpCode);
    }

    @Override
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        synchronized (this.lock) {
            if (this.socket == null) {
                create.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), null, null, new InternalChannelz.SocketOptions.Builder().build(), null));
            } else {
                create.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), this.socket.getLocalSocketAddress(), this.socket.getRemoteSocketAddress(), Utils.getSocketOptions(this.socket), this.securityInfo));
            }
        }
        return create;
    }

    class ClientFrameHandler implements FrameReader.Handler, Runnable {
        boolean firstSettings;
        FrameReader frameReader;
        private final OkHttpFrameLogger logger;

        @Override
        public void ackSettings() {
        }

        @Override
        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }

        @Override
        public void priority(int i, int i2, int i3, boolean z) {
        }

        ClientFrameHandler(OkHttpClientTransport okHttpClientTransport, FrameReader frameReader) {
            this(frameReader, new OkHttpFrameLogger(Level.FINE, OkHttpClientTransport.class));
        }

        ClientFrameHandler(FrameReader frameReader, OkHttpFrameLogger okHttpFrameLogger) {
            this.firstSettings = true;
            this.frameReader = frameReader;
            this.logger = okHttpFrameLogger;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpClientTransport");
            while (this.frameReader.nextFrame(this)) {
                try {
                    if (keepAliveManager != null) {
                        keepAliveManager.onDataReceived();
                    }
                } catch (Throwable th) {
                    try {
                        startGoAway(0, ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("error in frame handler").withCause(th));
                        try {
                            this.frameReader.close();
                        } catch (IOException e) {
                            e = e;
                            OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", (Throwable) e);
                            listener.transportTerminated();
                            Thread.currentThread().setName(name);
                        }
                    } catch (Throwable th2) {
                        try {
                            this.frameReader.close();
                        } catch (IOException e2) {
                            OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", (Throwable) e2);
                        }
                        listener.transportTerminated();
                        Thread.currentThread().setName(name);
                        throw th2;
                    }
                }
            }
            startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withDescription("End of stream or IOException"));
            try {
                this.frameReader.close();
            } catch (IOException e3) {
                e = e3;
                OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", (Throwable) e);
                listener.transportTerminated();
                Thread.currentThread().setName(name);
            }
            listener.transportTerminated();
            Thread.currentThread().setName(name);
        }

        @Override
        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            this.logger.logData(OkHttpFrameLogger.Direction.INBOUND, i, bufferedSource.getBuffer(), i2, z);
            OkHttpClientStream stream = getStream(i);
            if (stream == null) {
                if (mayHaveCreatedStream(i)) {
                    synchronized (lock) {
                        frameWriter.rstStream(i, ErrorCode.INVALID_STREAM);
                    }
                    bufferedSource.skip(i2);
                } else {
                    onError(ErrorCode.PROTOCOL_ERROR, "Received data for unknown stream: " + i);
                    return;
                }
            } else {
                long j = i2;
                bufferedSource.require(j);
                Buffer buffer = new Buffer();
                buffer.write(bufferedSource.getBuffer(), j);
                PerfMark.event("OkHttpClientTransport$ClientFrameHandler.data", stream.transportState().tag());
                synchronized (lock) {
                    stream.transportState().transportDataReceived(buffer, z);
                }
            }
            OkHttpClientTransport.Fun$2312(OkHttpClientTransport.this, i2);
            if (connectionUnacknowledgedBytesRead >= initialWindowSize * 0.5f) {
                synchronized (lock) {
                    frameWriter.windowUpdate(0, connectionUnacknowledgedBytesRead);
                }
                connectionUnacknowledgedBytesRead = 0;
            }
        }

        @Override
        public void headers(boolean z, boolean z2, int i, int i2, List<Header> list, HeadersMode headersMode) {
            Status status;
            int headerBlockSize;
            this.logger.logHeaders(OkHttpFrameLogger.Direction.INBOUND, i, list, z2);
            boolean z3 = true;
            if (maxInboundMetadataSize == Integer.MAX_VALUE || (headerBlockSize = headerBlockSize(list)) <= maxInboundMetadataSize) {
                status = null;
            } else {
                Status status2 = Status.RESOURCE_EXHAUSTED;
                Object[] objArr = new Object[3];
                objArr[0] = z2 ? "trailer" : "header";
                objArr[1] = Integer.valueOf(maxInboundMetadataSize);
                objArr[2] = Integer.valueOf(headerBlockSize);
                status = status2.withDescription(String.format("Response %s metadata larger than %d: %d", objArr));
            }
            synchronized (lock) {
                OkHttpClientStream okHttpClientStream = (OkHttpClientStream) streams.get(Integer.valueOf(i));
                if (okHttpClientStream == null) {
                    if (mayHaveCreatedStream(i)) {
                        frameWriter.rstStream(i, ErrorCode.INVALID_STREAM);
                    }
                } else if (status == null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.headers", okHttpClientStream.transportState().tag());
                    okHttpClientStream.transportState().transportHeadersReceived(list, z2);
                } else {
                    if (!z2) {
                        frameWriter.rstStream(i, ErrorCode.CANCEL);
                    }
                    okHttpClientStream.transportState().transportReportStatus(status, false, new Metadata());
                }
                z3 = false;
            }
            if (z3) {
                OkHttpClientTransport okHttpClientTransport = OkHttpClientTransport.this;
                ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
                okHttpClientTransport.onError(errorCode, "Received header for unknown stream: " + i);
            }
        }

        private int headerBlockSize(List<Header> list) {
            long j = 0;
            for (int i = 0; i < list.size(); i++) {
                Header header = list.get(i);
                j += header.name.size() + 32 + header.value.size();
            }
            return (int) Math.min(j, 2147483647L);
        }

        @Override
        public void rstStream(int i, ErrorCode errorCode) {
            this.logger.logRstStream(OkHttpFrameLogger.Direction.INBOUND, i, errorCode);
            Status augmentDescription = OkHttpClientTransport.toGrpcStatus(errorCode).augmentDescription("Rst Stream");
            boolean z = augmentDescription.getCode() == Status.Code.CANCELLED || augmentDescription.getCode() == Status.Code.DEADLINE_EXCEEDED;
            synchronized (lock) {
                OkHttpClientStream okHttpClientStream = (OkHttpClientStream) streams.get(Integer.valueOf(i));
                if (okHttpClientStream != null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.rstStream", okHttpClientStream.transportState().tag());
                    finishStream(i, augmentDescription, errorCode == ErrorCode.REFUSED_STREAM ? ClientStreamListener.RpcProgress.REFUSED : ClientStreamListener.RpcProgress.PROCESSED, z, null, null);
                }
            }
        }

        @Override
        public void settings(boolean z, Settings settings) {
            boolean z2;
            this.logger.logSettings(OkHttpFrameLogger.Direction.INBOUND, settings);
            synchronized (lock) {
                if (OkHttpSettingsUtil.isSet(settings, 4)) {
                    maxConcurrentStreams = OkHttpSettingsUtil.get(settings, 4);
                }
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    z2 = outboundFlow.initialOutboundWindowSize(OkHttpSettingsUtil.get(settings, 7));
                } else {
                    z2 = false;
                }
                if (this.firstSettings) {
                    listener.transportReady();
                    this.firstSettings = false;
                }
                frameWriter.ackSettings(settings);
                if (z2) {
                    outboundFlow.writeStreams();
                }
                startPendingStreams();
            }
        }

        @Override
        public void ping(boolean z, int i, int i2) {
            Http2Ping http2Ping;
            long j = (i << 32) | (i2 & 4294967295L);
            this.logger.logPing(OkHttpFrameLogger.Direction.INBOUND, j);
            if (!z) {
                synchronized (lock) {
                    frameWriter.ping(true, i, i2);
                }
                return;
            }
            synchronized (lock) {
                http2Ping = null;
                if (ping != null) {
                    if (ping.payload() == j) {
                        Http2Ping http2Ping2 = ping;
                        ping = null;
                        http2Ping = http2Ping2;
                    } else {
                        OkHttpClientTransport.log.log(Level.WARNING, String.format("Received unexpected ping ack. Expecting %d, got %d", Long.valueOf(ping.payload()), Long.valueOf(j)));
                    }
                } else {
                    OkHttpClientTransport.log.warning("Received unexpected ping ack. No ping outstanding");
                }
            }
            if (http2Ping != null) {
                http2Ping.complete();
            }
        }

        @Override
        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            this.logger.logGoAway(OkHttpFrameLogger.Direction.INBOUND, i, errorCode, byteString);
            if (errorCode == ErrorCode.ENHANCE_YOUR_CALM) {
                String utf8 = byteString.utf8();
                OkHttpClientTransport.log.log(Level.WARNING, String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", this, utf8));
                if ("too_many_pings".equals(utf8)) {
                    tooManyPingsRunnable.run();
                }
            }
            Status augmentDescription = GrpcUtil.Http2Error.statusForCode(errorCode.httpCode).augmentDescription("Received Goaway");
            if (byteString.size() > 0) {
                augmentDescription = augmentDescription.augmentDescription(byteString.utf8());
            }
            startGoAway(i, null, augmentDescription);
        }

        @Override
        public void pushPromise(int i, int i2, List<Header> list) throws IOException {
            this.logger.logPushPromise(OkHttpFrameLogger.Direction.INBOUND, i, i2, list);
            synchronized (lock) {
                frameWriter.rstStream(i, ErrorCode.PROTOCOL_ERROR);
            }
        }

        @Override
        public void windowUpdate(int r8, long r9) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ClientFrameHandler.windowUpdate(int, long):void");
        }
    }
}
