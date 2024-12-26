package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.apache.commons.lang3.CharEncoding;
public final class ClientCallImpl<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    private final Executor callExecutor;
    private final boolean callExecutorIsDirect;
    private CallOptions callOptions;
    private boolean cancelCalled;
    private volatile boolean cancelListenersShouldBeRemoved;
    private final CallTracer channelCallsTracer;
    private final ClientStreamProvider clientStreamProvider;
    private final Context context;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private volatile ScheduledFuture<?> deadlineCancellationFuture;
    private boolean fullStreamDecompression;
    private boolean halfCloseCalled;
    private final MethodDescriptor<ReqT, RespT> method;
    private ClientStream stream;
    private final Tag tag;
    private final boolean unaryRequest;
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    private static final byte[] FULL_STREAM_DECOMPRESSION_ENCODINGS = "gzip".getBytes(Charset.forName(CharEncoding.US_ASCII));
    private final ClientCallImpl<ReqT, RespT>.ContextCancellationListener cancellationListener = new ContextCancellationListener();
    private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
    private CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();

    public interface ClientStreamProvider {
        ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context);
    }

    public ClientCallImpl<ReqT, RespT> setCompressorRegistry(CompressorRegistry compressorRegistry) {
        this.compressorRegistry = compressorRegistry;
        return this;
    }

    public ClientCallImpl<ReqT, RespT> setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        this.decompressorRegistry = decompressorRegistry;
        return this;
    }

    public ClientCallImpl<ReqT, RespT> setFullStreamDecompression(boolean z) {
        this.fullStreamDecompression = z;
        return this;
    }

    public ClientCallImpl(MethodDescriptor<ReqT, RespT> methodDescriptor, Executor executor, CallOptions callOptions, ClientStreamProvider clientStreamProvider, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer, @Nullable InternalConfigSelector internalConfigSelector) {
        this.method = methodDescriptor;
        Tag createTag = PerfMark.createTag(methodDescriptor.getFullMethodName(), System.identityHashCode(this));
        this.tag = createTag;
        boolean z = true;
        if (executor == MoreExecutors.directExecutor()) {
            this.callExecutor = new SerializeReentrantCallsDirectExecutor();
            this.callExecutorIsDirect = true;
        } else {
            this.callExecutor = new SerializingExecutor(executor);
            this.callExecutorIsDirect = false;
        }
        this.channelCallsTracer = callTracer;
        this.context = Context.current();
        if (methodDescriptor.getType() != MethodDescriptor.MethodType.UNARY && methodDescriptor.getType() != MethodDescriptor.MethodType.SERVER_STREAMING) {
            z = false;
        }
        this.unaryRequest = z;
        this.callOptions = callOptions;
        this.clientStreamProvider = clientStreamProvider;
        this.deadlineCancellationExecutor = scheduledExecutorService;
        PerfMark.event("ClientCall.<init>", createTag);
    }

    public final class ContextCancellationListener implements Context.CancellationListener {
        private ContextCancellationListener() {
        }

        @Override
        public void cancelled(Context context) {
            stream.cancel(Contexts.statusFromCancelled(context));
        }
    }

    static void prepareHeaders(Metadata metadata, DecompressorRegistry decompressorRegistry, Compressor compressor, boolean z) {
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (compressor != Codec.Identity.NONE) {
            metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, compressor.getMessageEncoding());
        }
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        metadata.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        metadata.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (z) {
            metadata.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
    }

    @Override
    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
        PerfMark.startTask("ClientCall.start", this.tag);
        try {
            startInternal(listener, metadata);
        } finally {
            PerfMark.stopTask("ClientCall.start", this.tag);
        }
    }

    private void startInternal(final ClientCall.Listener<RespT> listener, Metadata metadata) {
        Compressor compressor;
        Preconditions.checkState(this.stream == null, "Already started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkNotNull(listener, "observer");
        Preconditions.checkNotNull(metadata, "headers");
        if (this.context.isCancelled()) {
            this.stream = NoopClientStream.INSTANCE;
            this.callExecutor.execute(new ContextRunnable() {
                {
                    super(context);
                }

                @Override
                public void runInContext() {
                    ClientCallImpl clientCallImpl = ClientCallImpl.this;
                    clientCallImpl.closeObserver(listener, Contexts.statusFromCancelled(clientCallImpl.context), new Metadata());
                }
            });
            return;
        }
        applyMethodConfig();
        final String compressor2 = this.callOptions.getCompressor();
        if (compressor2 != null) {
            compressor = this.compressorRegistry.lookupCompressor(compressor2);
            if (compressor == null) {
                this.stream = NoopClientStream.INSTANCE;
                this.callExecutor.execute(new ContextRunnable() {
                    {
                        super(context);
                    }

                    @Override
                    public void runInContext() {
                        closeObserver(listener, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", compressor2)), new Metadata());
                    }
                });
                return;
            }
        } else {
            compressor = Codec.Identity.NONE;
        }
        prepareHeaders(metadata, this.decompressorRegistry, compressor, this.fullStreamDecompression);
        Deadline effectiveDeadline = effectiveDeadline();
        if (effectiveDeadline == null || !effectiveDeadline.isExpired()) {
            logIfContextNarrowedTimeout(effectiveDeadline, this.context.getDeadline(), this.callOptions.getDeadline());
            this.stream = this.clientStreamProvider.newStream(this.method, this.callOptions, metadata, this.context);
        } else {
            Status status = Status.DEADLINE_EXCEEDED;
            this.stream = new FailingClientStream(status.withDescription("ClientCall started after deadline exceeded: " + effectiveDeadline));
        }
        if (this.callExecutorIsDirect) {
            this.stream.optimizeForDirectExecutor();
        }
        if (this.callOptions.getAuthority() != null) {
            this.stream.setAuthority(this.callOptions.getAuthority());
        }
        if (this.callOptions.getMaxInboundMessageSize() != null) {
            this.stream.setMaxInboundMessageSize(this.callOptions.getMaxInboundMessageSize().intValue());
        }
        if (this.callOptions.getMaxOutboundMessageSize() != null) {
            this.stream.setMaxOutboundMessageSize(this.callOptions.getMaxOutboundMessageSize().intValue());
        }
        if (effectiveDeadline != null) {
            this.stream.setDeadline(effectiveDeadline);
        }
        this.stream.setCompressor(compressor);
        boolean z = this.fullStreamDecompression;
        if (z) {
            this.stream.setFullStreamDecompression(z);
        }
        this.stream.setDecompressorRegistry(this.decompressorRegistry);
        this.channelCallsTracer.reportCallStarted();
        this.stream.start(new ClientStreamListenerImpl(listener));
        this.context.addListener(this.cancellationListener, MoreExecutors.directExecutor());
        if (effectiveDeadline != null && !effectiveDeadline.equals(this.context.getDeadline()) && this.deadlineCancellationExecutor != null) {
            this.deadlineCancellationFuture = startDeadlineTimer(effectiveDeadline);
        }
        if (this.cancelListenersShouldBeRemoved) {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    private void applyMethodConfig() {
        ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) this.callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
        if (methodInfo == null) {
            return;
        }
        if (methodInfo.timeoutNanos != null) {
            Deadline after = Deadline.after(methodInfo.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
            Deadline deadline = this.callOptions.getDeadline();
            if (deadline == null || after.compareTo(deadline) < 0) {
                this.callOptions = this.callOptions.withDeadline(after);
            }
        }
        if (methodInfo.waitForReady != null) {
            this.callOptions = methodInfo.waitForReady.booleanValue() ? this.callOptions.withWaitForReady() : this.callOptions.withoutWaitForReady();
        }
        if (methodInfo.maxInboundMessageSize != null) {
            Integer maxInboundMessageSize = this.callOptions.getMaxInboundMessageSize();
            if (maxInboundMessageSize != null) {
                this.callOptions = this.callOptions.withMaxInboundMessageSize(Math.min(maxInboundMessageSize.intValue(), methodInfo.maxInboundMessageSize.intValue()));
            } else {
                this.callOptions = this.callOptions.withMaxInboundMessageSize(methodInfo.maxInboundMessageSize.intValue());
            }
        }
        if (methodInfo.maxOutboundMessageSize != null) {
            Integer maxOutboundMessageSize = this.callOptions.getMaxOutboundMessageSize();
            if (maxOutboundMessageSize != null) {
                this.callOptions = this.callOptions.withMaxOutboundMessageSize(Math.min(maxOutboundMessageSize.intValue(), methodInfo.maxOutboundMessageSize.intValue()));
            } else {
                this.callOptions = this.callOptions.withMaxOutboundMessageSize(methodInfo.maxOutboundMessageSize.intValue());
            }
        }
    }

    private static void logIfContextNarrowedTimeout(Deadline deadline, @Nullable Deadline deadline2, @Nullable Deadline deadline3) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE) && deadline != null && deadline.equals(deadline2)) {
            StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS)))));
            if (deadline3 == null) {
                sb.append(" Explicit call timeout was not set.");
            } else {
                sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(deadline3.timeRemaining(TimeUnit.NANOSECONDS))));
            }
            logger.fine(sb.toString());
        }
    }

    public void removeContextListenerAndCancelDeadlineFuture() {
        this.context.removeListener(this.cancellationListener);
        ScheduledFuture<?> scheduledFuture = this.deadlineCancellationFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public class DeadlineTimer implements Runnable {
        private final long remainingNanos;

        DeadlineTimer(long j) {
            this.remainingNanos = j;
        }

        @Override
        public void run() {
            InsightBuilder insightBuilder = new InsightBuilder();
            stream.appendTimeoutInsight(insightBuilder);
            long abs = Math.abs(this.remainingNanos) / TimeUnit.SECONDS.toNanos(1L);
            long abs2 = Math.abs(this.remainingNanos) % TimeUnit.SECONDS.toNanos(1L);
            StringBuilder sb = new StringBuilder("deadline exceeded after ");
            if (this.remainingNanos < 0) {
                sb.append('-');
            }
            sb.append(abs);
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
            sb.append("s. ");
            sb.append(insightBuilder);
            stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(sb.toString()));
        }
    }

    private ScheduledFuture<?> startDeadlineTimer(Deadline deadline) {
        long timeRemaining = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        return this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new DeadlineTimer(timeRemaining)), timeRemaining, TimeUnit.NANOSECONDS);
    }

    @Nullable
    public Deadline effectiveDeadline() {
        return min(this.callOptions.getDeadline(), this.context.getDeadline());
    }

    @Nullable
    private static Deadline min(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        return deadline == null ? deadline2 : deadline2 == null ? deadline : deadline.minimum(deadline2);
    }

    @Override
    public void request(int i) {
        PerfMark.startTask("ClientCall.request", this.tag);
        try {
            boolean z = true;
            Preconditions.checkState(this.stream != null, "Not started");
            if (i < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "Number requested must be non-negative");
            this.stream.request(i);
        } finally {
            PerfMark.stopTask("ClientCall.request", this.tag);
        }
    }

    @Override
    public void cancel(@Nullable String str, @Nullable Throwable th) {
        PerfMark.startTask("ClientCall.cancel", this.tag);
        try {
            cancelInternal(str, th);
        } finally {
            PerfMark.stopTask("ClientCall.cancel", this.tag);
        }
    }

    private void cancelInternal(@Nullable String str, @Nullable Throwable th) {
        Status withDescription;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            log.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", th);
        }
        if (this.cancelCalled) {
            return;
        }
        this.cancelCalled = true;
        try {
            if (this.stream != null) {
                Status status = Status.CANCELLED;
                if (str != null) {
                    withDescription = status.withDescription(str);
                } else {
                    withDescription = status.withDescription("Call cancelled without message");
                }
                if (th != null) {
                    withDescription = withDescription.withCause(th);
                }
                this.stream.cancel(withDescription);
            }
        } finally {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    @Override
    public void halfClose() {
        PerfMark.startTask("ClientCall.halfClose", this.tag);
        try {
            halfCloseInternal();
        } finally {
            PerfMark.stopTask("ClientCall.halfClose", this.tag);
        }
    }

    private void halfCloseInternal() {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    @Override
    public void sendMessage(ReqT reqt) {
        PerfMark.startTask("ClientCall.sendMessage", this.tag);
        try {
            sendMessageInternal(reqt);
        } finally {
            PerfMark.stopTask("ClientCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(ReqT reqt) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            ClientStream clientStream = this.stream;
            if (clientStream instanceof RetriableStream) {
                ((RetriableStream) clientStream).sendMessage(reqt);
            } else {
                clientStream.writeMessage(this.method.streamRequest(reqt));
            }
            if (this.unaryRequest) {
                return;
            }
            this.stream.flush();
        } catch (Error e) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e;
        } catch (RuntimeException e2) {
            this.stream.cancel(Status.CANCELLED.withCause(e2).withDescription("Failed to stream message"));
        }
    }

    @Override
    public void setMessageCompression(boolean z) {
        Preconditions.checkState(this.stream != null, "Not started");
        this.stream.setMessageCompression(z);
    }

    @Override
    public boolean isReady() {
        return this.stream.isReady();
    }

    @Override
    public Attributes getAttributes() {
        ClientStream clientStream = this.stream;
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public void closeObserver(ClientCall.Listener<RespT> listener, Status status, Metadata metadata) {
        listener.onClose(status, metadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(FirebaseAnalytics.Param.METHOD, this.method).toString();
    }

    public class ClientStreamListenerImpl implements ClientStreamListener {
        private Status exceptionStatus;
        private final ClientCall.Listener<RespT> observer;

        public ClientStreamListenerImpl(ClientCall.Listener<RespT> listener) {
            this.observer = (ClientCall.Listener) Preconditions.checkNotNull(listener, "observer");
        }

        public void exceptionThrown(Status status) {
            this.exceptionStatus = status;
            stream.cancel(status);
        }

        @Override
        public void headersRead(final Metadata metadata) {
            PerfMark.startTask("ClientStreamListener.headersRead", tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                callExecutor.execute(new ContextRunnable() {
                    {
                        super(context);
                    }

                    @Override
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.headersRead", tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.headersRead", tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            return;
                        }
                        try {
                            ClientStreamListenerImpl.this.observer.onHeaders(metadata);
                        } catch (Throwable th) {
                            ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read headers"));
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.headersRead", tag);
            }
        }

        @Override
        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ClientStreamListener.messagesAvailable", tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                callExecutor.execute(new ContextRunnable() {
                    {
                        super(context);
                    }

                    @Override
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.messagesAvailable", tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.messagesAvailable", tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            GrpcUtil.closeQuietly(messageProducer);
                            return;
                        }
                        while (true) {
                            try {
                                InputStream next = messageProducer.next();
                                if (next == null) {
                                    return;
                                }
                                ClientStreamListenerImpl.this.observer.onMessage(method.parseResponse(next));
                                next.close();
                            } catch (Throwable th) {
                                GrpcUtil.closeQuietly(messageProducer);
                                ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read message."));
                                return;
                            }
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.messagesAvailable", tag);
            }
        }

        @Override
        public void closed(Status status, Metadata metadata) {
            closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
        }

        @Override
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            PerfMark.startTask("ClientStreamListener.closed", tag);
            try {
                closedInternal(status, rpcProgress, metadata);
            } finally {
                PerfMark.stopTask("ClientStreamListener.closed", tag);
            }
        }

        private void closedInternal(final Status status, ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            Deadline effectiveDeadline = effectiveDeadline();
            if (status.getCode() == Status.Code.CANCELLED && effectiveDeadline != null && effectiveDeadline.isExpired()) {
                InsightBuilder insightBuilder = new InsightBuilder();
                stream.appendTimeoutInsight(insightBuilder);
                Status status2 = Status.DEADLINE_EXCEEDED;
                status = status2.augmentDescription("ClientCall was cancelled at or after deadline. " + insightBuilder);
                metadata = new Metadata();
            }
            final Link linkOut = PerfMark.linkOut();
            callExecutor.execute(new ContextRunnable() {
                {
                    super(context);
                }

                @Override
                public void runInContext() {
                    PerfMark.startTask("ClientCall$Listener.onClose", tag);
                    PerfMark.linkIn(linkOut);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ClientCall$Listener.onClose", tag);
                    }
                }

                private void runInternal() {
                    Status status3 = status;
                    Metadata metadata2 = metadata;
                    if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                        status3 = ClientStreamListenerImpl.this.exceptionStatus;
                        metadata2 = new Metadata();
                    }
                    cancelListenersShouldBeRemoved = true;
                    try {
                        closeObserver(ClientStreamListenerImpl.this.observer, status3, metadata2);
                    } finally {
                        removeContextListenerAndCancelDeadlineFuture();
                        channelCallsTracer.reportCallEnded(status3.isOk());
                    }
                }
            });
        }

        @Override
        public void onReady() {
            if (method.getType().clientSendsOneMessage()) {
                return;
            }
            PerfMark.startTask("ClientStreamListener.onReady", tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                callExecutor.execute(new ContextRunnable() {
                    {
                        super(context);
                    }

                    @Override
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.onReady", tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.onReady", tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            return;
                        }
                        try {
                            ClientStreamListenerImpl.this.observer.onReady();
                        } catch (Throwable th) {
                            ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to call onReady."));
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.onReady", tag);
            }
        }
    }
}
