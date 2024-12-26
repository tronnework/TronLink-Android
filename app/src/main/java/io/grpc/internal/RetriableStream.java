package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
public abstract class RetriableStream<ReqT> implements ClientStream {
    private final Executor callExecutor;
    private final long channelBufferLimit;
    private final ChannelBufferMeter channelBufferUsed;
    private final Metadata headers;
    @Nullable
    private final HedgingPolicy hedgingPolicy;
    private final boolean isHedging;
    private ClientStreamListener masterListener;
    private final MethodDescriptor<ReqT, ?> method;
    private long nextBackoffIntervalNanos;
    private final long perRpcBufferLimit;
    private long perRpcBufferUsed;
    @Nullable
    private final RetryPolicy retryPolicy;
    private final ScheduledExecutorService scheduledExecutorService;
    private FutureCanceller scheduledHedging;
    private FutureCanceller scheduledRetry;
    @Nullable
    private final Throttle throttle;
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    private static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    private static Random random = new Random();
    private final Object lock = new Object();
    private final InsightBuilder closedSubstreamsInsight = new InsightBuilder();
    private volatile State state = new State(new ArrayList(8), Collections.emptyList(), null, null, false, false, false, 0);
    private final AtomicBoolean noMoreTransparentRetry = new AtomicBoolean();

    public interface BufferEntry {
        void runWith(Substream substream);
    }

    static void setRandom(Random random2) {
        random = random2;
    }

    abstract ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata);

    abstract void postCommit();

    @CheckReturnValue
    @Nullable
    abstract Status prestart();

    public RetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, Metadata metadata, ChannelBufferMeter channelBufferMeter, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService, @Nullable RetryPolicy retryPolicy, @Nullable HedgingPolicy hedgingPolicy, @Nullable Throttle throttle) {
        this.method = methodDescriptor;
        this.channelBufferUsed = channelBufferMeter;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService;
        this.headers = metadata;
        this.retryPolicy = retryPolicy;
        if (retryPolicy != null) {
            this.nextBackoffIntervalNanos = retryPolicy.initialBackoffNanos;
        }
        this.hedgingPolicy = hedgingPolicy;
        Preconditions.checkArgument(retryPolicy == null || hedgingPolicy == null, "Should not provide both retryPolicy and hedgingPolicy");
        this.isHedging = hedgingPolicy != null;
        this.throttle = throttle;
    }

    @CheckReturnValue
    @Nullable
    public Runnable commit(final Substream substream) {
        final Future<?> future;
        final Future<?> future2;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> collection = this.state.drainedSubstreams;
            this.state = this.state.committed(substream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledRetry = null;
                future = markCancelled;
            } else {
                future = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                Future<?> markCancelled2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
                future2 = markCancelled2;
            } else {
                future2 = null;
            }
            return new Runnable() {
                @Override
                public void run() {
                    for (Substream substream2 : collection) {
                        if (substream2 != substream) {
                            substream2.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    Future future3 = future;
                    if (future3 != null) {
                        future3.cancel(false);
                    }
                    Future future4 = future2;
                    if (future4 != null) {
                        future4.cancel(false);
                    }
                    postCommit();
                }
            };
        }
    }

    public void commitAndRun(Substream substream) {
        Runnable commit = commit(substream);
        if (commit != null) {
            commit.run();
        }
    }

    public Substream createSubstream(int i) {
        Substream substream = new Substream(i);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(substream);
        substream.stream = newSubstream(new ClientStreamTracer.Factory() {
            @Override
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                return bufferSizeTracer;
            }
        }, updateHeaders(this.headers, i));
        return substream;
    }

    final Metadata updateHeaders(Metadata metadata, int i) {
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        return metadata2;
    }

    public void drain(Substream substream) {
        ArrayList<BufferEntry> arrayList = null;
        int i = 0;
        while (true) {
            synchronized (this.lock) {
                State state = this.state;
                if (state.winningSubstream == null || state.winningSubstream == substream) {
                    if (i == state.buffer.size()) {
                        this.state = state.substreamDrained(substream);
                        return;
                    } else if (substream.closed) {
                        return;
                    } else {
                        int min = Math.min(i + 128, state.buffer.size());
                        if (arrayList == null) {
                            arrayList = new ArrayList(state.buffer.subList(i, min));
                        } else {
                            arrayList.clear();
                            arrayList.addAll(state.buffer.subList(i, min));
                        }
                        for (BufferEntry bufferEntry : arrayList) {
                            State state2 = this.state;
                            if (state2.winningSubstream == null || state2.winningSubstream == substream) {
                                if (state2.cancelled) {
                                    Preconditions.checkState(state2.winningSubstream == substream, "substream should be CANCELLED_BECAUSE_COMMITTED already");
                                    return;
                                }
                                bufferEntry.runWith(substream);
                            }
                        }
                        i = min;
                    }
                } else {
                    substream.stream.cancel(CANCELLED_BECAUSE_COMMITTED);
                    return;
                }
            }
        }
    }

    @Override
    public final void start(ClientStreamListener clientStreamListener) {
        FutureCanceller futureCanceller;
        Throttle throttle;
        this.masterListener = clientStreamListener;
        Status prestart = prestart();
        if (prestart != null) {
            cancel(prestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new BufferEntry() {
                @Override
                public void runWith(Substream substream) {
                    substream.stream.start(new Sublistener(substream));
                }
            });
        }
        Substream createSubstream = createSubstream(0);
        if (this.isHedging) {
            synchronized (this.lock) {
                this.state = this.state.addActiveHedge(createSubstream);
                if (hasPotentialHedging(this.state) && ((throttle = this.throttle) == null || throttle.isAboveThreshold())) {
                    futureCanceller = new FutureCanceller(this.lock);
                    this.scheduledHedging = futureCanceller;
                } else {
                    futureCanceller = null;
                }
            }
            if (futureCanceller != null) {
                futureCanceller.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
            }
        }
        drain(createSubstream);
    }

    public void pushbackHedging(@Nullable Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 0) {
            freezeHedging();
            return;
        }
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            if (futureCanceller == null) {
                return;
            }
            Future<?> markCancelled = futureCanceller.markCancelled();
            FutureCanceller futureCanceller2 = new FutureCanceller(this.lock);
            this.scheduledHedging = futureCanceller2;
            if (markCancelled != null) {
                markCancelled.cancel(false);
            }
            futureCanceller2.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller2), num.intValue(), TimeUnit.MILLISECONDS));
        }
    }

    public final class HedgingRunnable implements Runnable {
        final FutureCanceller scheduledHedgingRef;

        HedgingRunnable(FutureCanceller futureCanceller) {
            this.scheduledHedgingRef = futureCanceller;
        }

        @Override
        public void run() {
            callExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    FutureCanceller futureCanceller;
                    boolean z;
                    Substream createSubstream = createSubstream(state.hedgingAttemptCount);
                    synchronized (lock) {
                        futureCanceller = null;
                        if (HedgingRunnable.this.scheduledHedgingRef.isCancelled()) {
                            z = true;
                        } else {
                            state = state.addActiveHedge(createSubstream);
                            if (hasPotentialHedging(state) && (throttle == null || throttle.isAboveThreshold())) {
                                RetriableStream retriableStream = RetriableStream.this;
                                futureCanceller = new FutureCanceller(lock);
                                retriableStream.scheduledHedging = futureCanceller;
                            } else {
                                state = state.freezeHedging();
                                scheduledHedging = null;
                            }
                            z = false;
                        }
                    }
                    if (!z) {
                        if (futureCanceller != null) {
                            futureCanceller.setFuture(scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                        }
                        drain(createSubstream);
                        return;
                    }
                    createSubstream.stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                }
            });
        }
    }

    @Override
    public final void cancel(Status status) {
        Substream substream = new Substream(0);
        substream.stream = new NoopClientStream();
        Runnable commit = commit(substream);
        if (commit != null) {
            this.masterListener.closed(status, new Metadata());
            commit.run();
            return;
        }
        this.state.winningSubstream.stream.cancel(status);
        synchronized (this.lock) {
            this.state = this.state.cancelled();
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        for (Substream substream : collection) {
            bufferEntry.runWith(substream);
        }
    }

    @Override
    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    public final void sendMessage(final ReqT reqt) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.writeMessage(this.method.streamRequest(reqt));
        } else {
            delayOrExecute(new BufferEntry() {
                @Override
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(method.streamRequest(reqt));
                }
            });
        }
    }

    @Override
    public final void request(final int i) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new BufferEntry() {
                @Override
                public void runWith(Substream substream) {
                    substream.stream.request(i);
                }
            });
        }
    }

    @Override
    public final void flush() {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() {
                @Override
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    @Override
    public final boolean isReady() {
        for (Substream substream : this.state.drainedSubstreams) {
            if (substream.stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void optimizeForDirectExecutor() {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.optimizeForDirectExecutor();
            }
        });
    }

    @Override
    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    @Override
    public final void setFullStreamDecompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(z);
            }
        });
    }

    @Override
    public final void setMessageCompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(z);
            }
        });
    }

    @Override
    public final void halfClose() {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    @Override
    public final void setAuthority(final String str) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setAuthority(str);
            }
        });
    }

    @Override
    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    @Override
    public final void setMaxInboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(i);
            }
        });
    }

    @Override
    public final void setMaxOutboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(i);
            }
        });
    }

    @Override
    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() {
            @Override
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }

    @Override
    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    @Override
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        State state;
        synchronized (this.lock) {
            insightBuilder.appendKeyValue("closed", this.closedSubstreamsInsight);
            state = this.state;
        }
        if (state.winningSubstream != null) {
            InsightBuilder insightBuilder2 = new InsightBuilder();
            state.winningSubstream.stream.appendTimeoutInsight(insightBuilder2);
            insightBuilder.appendKeyValue("committed", insightBuilder2);
            return;
        }
        InsightBuilder insightBuilder3 = new InsightBuilder();
        for (Substream substream : state.drainedSubstreams) {
            InsightBuilder insightBuilder4 = new InsightBuilder();
            substream.stream.appendTimeoutInsight(insightBuilder4);
            insightBuilder3.append(insightBuilder4);
        }
        insightBuilder.appendKeyValue("open", insightBuilder3);
    }

    public boolean hasPotentialHedging(State state) {
        return state.winningSubstream == null && state.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state.hedgingFrozen;
    }

    public void freezeHedging() {
        Future<?> future;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            future = null;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
                future = markCancelled;
            }
            this.state = this.state.freezeHedging();
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    private final class Sublistener implements ClientStreamListener {
        final Substream substream;

        Sublistener(Substream substream) {
            this.substream = substream;
        }

        @Override
        public void headersRead(Metadata metadata) {
            commitAndRun(this.substream);
            if (state.winningSubstream == this.substream) {
                masterListener.headersRead(metadata);
                if (throttle != null) {
                    throttle.onSuccess();
                }
            }
        }

        @Override
        public void closed(Status status, Metadata metadata) {
            closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
        }

        @Override
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            FutureCanceller futureCanceller;
            synchronized (lock) {
                RetriableStream retriableStream = RetriableStream.this;
                retriableStream.state = retriableStream.state.substreamClosed(this.substream);
                closedSubstreamsInsight.append(status.getCode());
            }
            if (this.substream.bufferLimitExceeded) {
                commitAndRun(this.substream);
                if (state.winningSubstream == this.substream) {
                    masterListener.closed(status, metadata);
                    return;
                }
                return;
            }
            if (state.winningSubstream == null) {
                boolean z = true;
                if (rpcProgress == ClientStreamListener.RpcProgress.REFUSED && noMoreTransparentRetry.compareAndSet(false, true)) {
                    final Substream createSubstream = createSubstream(this.substream.previousAttemptCount);
                    if (isHedging) {
                        synchronized (lock) {
                            RetriableStream retriableStream2 = RetriableStream.this;
                            retriableStream2.state = retriableStream2.state.replaceActiveHedge(this.substream, createSubstream);
                            RetriableStream retriableStream3 = RetriableStream.this;
                            if (retriableStream3.hasPotentialHedging(retriableStream3.state) || state.activeHedges.size() != 1) {
                                z = false;
                            }
                        }
                        if (z) {
                            commitAndRun(createSubstream);
                        }
                    } else if (retryPolicy == null || retryPolicy.maxAttempts == 1) {
                        commitAndRun(createSubstream);
                    }
                    callExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            drain(createSubstream);
                        }
                    });
                    return;
                } else if (rpcProgress == ClientStreamListener.RpcProgress.DROPPED) {
                    if (isHedging) {
                        freezeHedging();
                    }
                } else {
                    noMoreTransparentRetry.set(true);
                    if (isHedging) {
                        HedgingPlan makeHedgingDecision = makeHedgingDecision(status, metadata);
                        if (makeHedgingDecision.isHedgeable) {
                            pushbackHedging(makeHedgingDecision.hedgingPushbackMillis);
                        }
                        synchronized (lock) {
                            RetriableStream retriableStream4 = RetriableStream.this;
                            retriableStream4.state = retriableStream4.state.removeActiveHedge(this.substream);
                            if (makeHedgingDecision.isHedgeable) {
                                RetriableStream retriableStream5 = RetriableStream.this;
                                if (retriableStream5.hasPotentialHedging(retriableStream5.state) || !state.activeHedges.isEmpty()) {
                                    return;
                                }
                            }
                        }
                    } else {
                        RetryPlan makeRetryDecision = makeRetryDecision(status, metadata);
                        if (makeRetryDecision.shouldRetry) {
                            synchronized (lock) {
                                RetriableStream retriableStream6 = RetriableStream.this;
                                futureCanceller = new FutureCanceller(retriableStream6.lock);
                                retriableStream6.scheduledRetry = futureCanceller;
                            }
                            futureCanceller.setFuture(scheduledExecutorService.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    callExecutor.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            drain(createSubstream(Sublistener.this.substream.previousAttemptCount + 1));
                                        }
                                    });
                                }
                            }, makeRetryDecision.backoffNanos, TimeUnit.NANOSECONDS));
                            return;
                        }
                    }
                }
            }
            commitAndRun(this.substream);
            if (state.winningSubstream == this.substream) {
                masterListener.closed(status, metadata);
            }
        }

        private RetryPlan makeRetryDecision(Status status, Metadata metadata) {
            long j = 0;
            boolean z = false;
            if (retryPolicy != null) {
                boolean contains = retryPolicy.retryableStatusCodes.contains(status.getCode());
                Integer pushbackMills = getPushbackMills(metadata);
                boolean z2 = (throttle == null || (!contains && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : !throttle.onQualifiedFailureThenCheckIsAboveThreshold();
                if (retryPolicy.maxAttempts > this.substream.previousAttemptCount + 1 && !z2) {
                    if (pushbackMills == null) {
                        if (contains) {
                            j = (long) (nextBackoffIntervalNanos * RetriableStream.random.nextDouble());
                            RetriableStream retriableStream = RetriableStream.this;
                            retriableStream.nextBackoffIntervalNanos = Math.min((long) (retriableStream.nextBackoffIntervalNanos * retryPolicy.backoffMultiplier), retryPolicy.maxBackoffNanos);
                            z = true;
                        }
                    } else if (pushbackMills.intValue() >= 0) {
                        j = TimeUnit.MILLISECONDS.toNanos(pushbackMills.intValue());
                        RetriableStream retriableStream2 = RetriableStream.this;
                        retriableStream2.nextBackoffIntervalNanos = retriableStream2.retryPolicy.initialBackoffNanos;
                        z = true;
                    }
                }
                return new RetryPlan(z, j);
            }
            return new RetryPlan(false, 0L);
        }

        private HedgingPlan makeHedgingDecision(Status status, Metadata metadata) {
            Integer pushbackMills = getPushbackMills(metadata);
            boolean z = true;
            boolean z2 = !hedgingPolicy.nonFatalStatusCodes.contains(status.getCode());
            return new HedgingPlan((z2 || ((throttle == null || (z2 && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : throttle.onQualifiedFailureThenCheckIsAboveThreshold() ^ true)) ? false : false, pushbackMills);
        }

        @Nullable
        private Integer getPushbackMills(Metadata metadata) {
            String str = (String) metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (str != null) {
                try {
                    return Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    return -1;
                }
            }
            return null;
        }

        @Override
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            State state = state;
            Preconditions.checkState(state.winningSubstream != null, "Headers should be received prior to messages.");
            if (state.winningSubstream != this.substream) {
                return;
            }
            masterListener.messagesAvailable(messageProducer);
        }

        @Override
        public void onReady() {
            masterListener.onReady();
        }
    }

    public static final class State {
        final Collection<Substream> activeHedges;
        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;
        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> list, Collection<Substream> collection, Collection<Substream> collection2, @Nullable Substream substream, boolean z, boolean z2, boolean z3, int i) {
            this.buffer = list;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.winningSubstream = substream;
            this.activeHedges = collection2;
            this.cancelled = z;
            this.passThrough = z2;
            this.hedgingFrozen = z3;
            this.hedgingAttemptCount = i;
            boolean z4 = false;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState((z2 && substream == null) ? false : true, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(substream)) || (collection.size() == 0 && substream.closed), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((z && substream == null) ? true : true, "cancelled should imply committed");
        }

        @CheckReturnValue
        State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, true, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State substreamDrained(Substream substream) {
            Collection unmodifiableCollection;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                unmodifiableCollection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                unmodifiableCollection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            Collection collection = unmodifiableCollection;
            Substream substream2 = this.winningSubstream;
            boolean z = substream2 != null;
            List<BufferEntry> list = this.buffer;
            if (z) {
                Preconditions.checkState(substream2 == substream, "Another RPC attempt has already committed");
                list = null;
            }
            return new State(list, collection, this.activeHedges, this.winningSubstream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State substreamClosed(Substream substream) {
            substream.closed = true;
            if (this.drainedSubstreams.contains(substream)) {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.remove(substream);
                return new State(this.buffer, Collections.unmodifiableCollection(arrayList), this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
            }
            return this;
        }

        @CheckReturnValue
        State committed(Substream substream) {
            List<BufferEntry> list;
            Collection emptyList;
            boolean z;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            List<BufferEntry> list2 = this.buffer;
            if (this.drainedSubstreams.contains(substream)) {
                emptyList = Collections.singleton(substream);
                list = null;
                z = true;
            } else {
                list = list2;
                emptyList = Collections.emptyList();
                z = false;
            }
            return new State(list, emptyList, this.activeHedges, substream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State freezeHedging() {
            return this.hedgingFrozen ? this : new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State addActiveHedge(Substream substream) {
            Collection unmodifiableCollection;
            Preconditions.checkState(!this.hedgingFrozen, "hedging frozen");
            Preconditions.checkState(this.winningSubstream == null, "already committed");
            if (this.activeHedges == null) {
                unmodifiableCollection = Collections.singleton(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.activeHedges);
                arrayList.add(substream);
                unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            return new State(this.buffer, this.drainedSubstreams, unmodifiableCollection, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount + 1);
        }

        @CheckReturnValue
        State removeActiveHedge(Substream substream) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State replaceActiveHedge(Substream substream, Substream substream2) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            arrayList.add(substream2);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    public static final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttemptCount;
        ClientStream stream;

        Substream(int i) {
            this.previousAttemptCount = i;
        }
    }

    public class BufferSizeTracer extends ClientStreamTracer {
        long bufferNeeded;
        private final Substream substream;

        BufferSizeTracer(Substream substream) {
            this.substream = substream;
        }

        @Override
        public void outboundWireSize(long j) {
            if (state.winningSubstream != null) {
                return;
            }
            synchronized (lock) {
                if (state.winningSubstream == null && !this.substream.closed) {
                    long j2 = this.bufferNeeded + j;
                    this.bufferNeeded = j2;
                    if (j2 <= perRpcBufferUsed) {
                        return;
                    }
                    if (this.bufferNeeded <= perRpcBufferLimit) {
                        long addAndGet = channelBufferUsed.addAndGet(this.bufferNeeded - perRpcBufferUsed);
                        perRpcBufferUsed = this.bufferNeeded;
                        if (addAndGet > channelBufferLimit) {
                            this.substream.bufferLimitExceeded = true;
                        }
                    } else {
                        this.substream.bufferLimitExceeded = true;
                    }
                    Runnable commit = this.substream.bufferLimitExceeded ? commit(this.substream) : null;
                    if (commit != null) {
                        commit.run();
                    }
                }
            }
        }
    }

    public static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        long addAndGet(long j) {
            return this.bufferUsed.addAndGet(j);
        }
    }

    public static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        public Throttle(float f, float f2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (f2 * 1000.0f);
            int i = (int) (f * 1000.0f);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                if (i == 0) {
                    return false;
                }
                i2 = i - 1000;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            return i2 > this.threshold;
        }

        void onSuccess() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                i2 = this.maxTokens;
                if (i == i2) {
                    return;
                }
            } while (!this.tokenCount.compareAndSet(i, Math.min(this.tokenRatio + i, i2)));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Throttle) {
                Throttle throttle = (Throttle) obj;
                return this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    public static final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        RetryPlan(boolean z, long j) {
            this.shouldRetry = z;
            this.backoffNanos = j;
        }
    }

    public static final class HedgingPlan {
        @Nullable
        final Integer hedgingPushbackMillis;
        final boolean isHedgeable;

        public HedgingPlan(boolean z, @Nullable Integer num) {
            this.isHedgeable = z;
            this.hedgingPushbackMillis = num;
        }
    }

    public static final class FutureCanceller {
        boolean cancelled;
        Future<?> future;
        final Object lock;

        boolean isCancelled() {
            return this.cancelled;
        }

        @CheckForNull
        Future<?> markCancelled() {
            this.cancelled = true;
            return this.future;
        }

        FutureCanceller(Object obj) {
            this.lock = obj;
        }

        void setFuture(Future<?> future) {
            synchronized (this.lock) {
                if (!this.cancelled) {
                    this.future = future;
                }
            }
        }
    }
}
