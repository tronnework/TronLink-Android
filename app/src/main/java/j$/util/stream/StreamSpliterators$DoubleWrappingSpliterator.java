package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Supplier;
public final class StreamSpliterators$DoubleWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfDouble {
    StreamSpliterators$DoubleWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    public StreamSpliterators$DoubleWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    @Override
    public void forEachRemaining(Consumer consumer) {
        Spliterator.OfDouble.-CC.$default$forEachRemaining(this, consumer);
    }

    @Override
    public void forEachRemaining(final DoubleConsumer doubleConsumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(doubleConsumer));
            return;
        }
        Objects.requireNonNull(doubleConsumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(doubleConsumer);
        pipelineHelper.wrapAndCopyInto(new Sink.OfDouble() {
            @Override
            public final void accept(double d) {
                DoubleConsumer.this.accept(d);
            }

            @Override
            public void accept(int i) {
                Sink.-CC.$default$accept((Sink) this, i);
            }

            @Override
            public void accept(long j) {
                Sink.-CC.$default$accept((Sink) this, j);
            }

            @Override
            public void accept(Double d) {
                Sink.OfDouble.-CC.$default$accept((Sink.OfDouble) this, d);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfDouble.-CC.$default$accept(this, obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            public DoubleConsumer andThen(DoubleConsumer doubleConsumer2) {
                return Objects.requireNonNull(doubleConsumer2);
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
        final SpinedBuffer.OfDouble ofDouble = new SpinedBuffer.OfDouble();
        this.buffer = ofDouble;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(ofDouble);
        this.bufferSink = pipelineHelper.wrapSink(new Sink.OfDouble() {
            @Override
            public final void accept(double d) {
                SpinedBuffer.OfDouble.this.accept(d);
            }

            @Override
            public void accept(int i) {
                Sink.-CC.$default$accept((Sink) this, i);
            }

            @Override
            public void accept(long j) {
                Sink.-CC.$default$accept((Sink) this, j);
            }

            @Override
            public void accept(Double d) {
                Sink.OfDouble.-CC.$default$accept((Sink.OfDouble) this, d);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfDouble.-CC.$default$accept(this, obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                return Objects.requireNonNull(doubleConsumer);
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
                return lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$DoubleWrappingSpliterator();
            }
        };
    }

    public boolean lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$DoubleWrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    @Override
    public boolean tryAdvance(Consumer consumer) {
        return Spliterator.OfDouble.-CC.$default$tryAdvance(this, consumer);
    }

    @Override
    public boolean tryAdvance(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            doubleConsumer.accept(((SpinedBuffer.OfDouble) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    @Override
    public Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) super.trySplit();
    }

    @Override
    StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$DoubleWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
