package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
public final class StreamSpliterators$IntWrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator implements Spliterator.OfInt {
    StreamSpliterators$IntWrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    public StreamSpliterators$IntWrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    @Override
    public void forEachRemaining(Consumer consumer) {
        Spliterator.OfInt.-CC.$default$forEachRemaining(this, consumer);
    }

    @Override
    public void forEachRemaining(final IntConsumer intConsumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(intConsumer));
            return;
        }
        Objects.requireNonNull(intConsumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(intConsumer);
        pipelineHelper.wrapAndCopyInto(new Sink.OfInt() {
            @Override
            public void accept(double d) {
                Sink.-CC.$default$accept(this, d);
            }

            @Override
            public final void accept(int i) {
                IntConsumer.this.accept(i);
            }

            @Override
            public void accept(long j) {
                Sink.-CC.$default$accept((Sink) this, j);
            }

            @Override
            public void accept(Integer num) {
                Sink.OfInt.-CC.$default$accept((Sink.OfInt) this, num);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfInt.-CC.$default$accept(this, obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            public IntConsumer andThen(IntConsumer intConsumer2) {
                return Objects.requireNonNull(intConsumer2);
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
        final SpinedBuffer.OfInt ofInt = new SpinedBuffer.OfInt();
        this.buffer = ofInt;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(ofInt);
        this.bufferSink = pipelineHelper.wrapSink(new Sink.OfInt() {
            @Override
            public void accept(double d) {
                Sink.-CC.$default$accept(this, d);
            }

            @Override
            public final void accept(int i) {
                SpinedBuffer.OfInt.this.accept(i);
            }

            @Override
            public void accept(long j) {
                Sink.-CC.$default$accept((Sink) this, j);
            }

            @Override
            public void accept(Integer num) {
                Sink.OfInt.-CC.$default$accept((Sink.OfInt) this, num);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfInt.-CC.$default$accept(this, obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            public IntConsumer andThen(IntConsumer intConsumer) {
                return Objects.requireNonNull(intConsumer);
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
                return lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$IntWrappingSpliterator();
            }
        };
    }

    public boolean lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$IntWrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    @Override
    public boolean tryAdvance(Consumer consumer) {
        return Spliterator.OfInt.-CC.$default$tryAdvance(this, consumer);
    }

    @Override
    public boolean tryAdvance(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            intConsumer.accept(((SpinedBuffer.OfInt) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    @Override
    public Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) super.trySplit();
    }

    @Override
    StreamSpliterators$AbstractWrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$IntWrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
