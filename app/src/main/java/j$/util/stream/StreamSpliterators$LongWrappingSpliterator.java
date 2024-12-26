package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;
public final class StreamSpliterators$LongWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfLong {
    StreamSpliterators$LongWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    public StreamSpliterators$LongWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    @Override
    public void forEachRemaining(Consumer consumer) {
        Spliterator.OfLong.-CC.$default$forEachRemaining(this, consumer);
    }

    @Override
    public void forEachRemaining(final LongConsumer longConsumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(longConsumer));
            return;
        }
        Objects.requireNonNull(longConsumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(longConsumer);
        pipelineHelper.wrapAndCopyInto(new Sink.OfLong() {
            @Override
            public void accept(double d) {
                Sink.-CC.$default$accept(this, d);
            }

            @Override
            public void accept(int i) {
                Sink.-CC.$default$accept((Sink) this, i);
            }

            @Override
            public final void accept(long j) {
                LongConsumer.this.accept(j);
            }

            @Override
            public void accept(Long l) {
                Sink.OfLong.-CC.$default$accept((Sink.OfLong) this, l);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfLong.-CC.$default$accept(this, obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            public LongConsumer andThen(LongConsumer longConsumer2) {
                return Objects.requireNonNull(longConsumer2);
            }

            @Override
            public void begin(long j) {
                Sink.-CC.$default$begin(this, j);
            }

            @Override
            public boolean cancellationRequested() {
                return Sink.-CC.$default$cancellationRequested(this);
            }

            @Override
            public void end() {
                Sink.-CC.$default$end(this);
            }
        }, this.spliterator);
        this.finished = true;
    }

    @Override
    void initPartialTraversalState() {
        final SpinedBuffer.OfLong ofLong = new SpinedBuffer.OfLong();
        this.buffer = ofLong;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(ofLong);
        this.bufferSink = pipelineHelper.wrapSink(new Sink.OfLong() {
            @Override
            public void accept(double d) {
                Sink.-CC.$default$accept(this, d);
            }

            @Override
            public void accept(int i) {
                Sink.-CC.$default$accept((Sink) this, i);
            }

            @Override
            public final void accept(long j) {
                SpinedBuffer.OfLong.this.accept(j);
            }

            @Override
            public void accept(Long l) {
                Sink.OfLong.-CC.$default$accept((Sink.OfLong) this, l);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfLong.-CC.$default$accept(this, obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            public LongConsumer andThen(LongConsumer longConsumer) {
                return Objects.requireNonNull(longConsumer);
            }

            @Override
            public void begin(long j) {
                Sink.-CC.$default$begin(this, j);
            }

            @Override
            public boolean cancellationRequested() {
                return Sink.-CC.$default$cancellationRequested(this);
            }

            @Override
            public void end() {
                Sink.-CC.$default$end(this);
            }
        });
        this.pusher = new BooleanSupplier() {
            @Override
            public final boolean getAsBoolean() {
                return lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$LongWrappingSpliterator();
            }
        };
    }

    public boolean lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$LongWrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    @Override
    public boolean tryAdvance(Consumer consumer) {
        return Spliterator.OfLong.-CC.$default$tryAdvance(this, consumer);
    }

    @Override
    public boolean tryAdvance(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            longConsumer.accept(((SpinedBuffer.OfLong) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    @Override
    public Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) super.trySplit();
    }

    @Override
    StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$LongWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
