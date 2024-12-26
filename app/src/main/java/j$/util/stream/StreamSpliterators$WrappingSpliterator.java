package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Sink;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
public final class StreamSpliterators$WrappingSpliterator extends StreamSpliterators$AbstractWrappingSpliterator {
    StreamSpliterators$WrappingSpliterator(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        super(pipelineHelper, spliterator, z);
    }

    public StreamSpliterators$WrappingSpliterator(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        super(pipelineHelper, supplier, z);
    }

    @Override
    public void forEachRemaining(final Consumer consumer) {
        if (this.buffer != null || this.finished) {
            do {
            } while (tryAdvance(consumer));
            return;
        }
        Objects.requireNonNull(consumer);
        init();
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(consumer);
        pipelineHelper.wrapAndCopyInto(new Sink() {
            @Override
            public void accept(double d) {
                Sink.-CC.$default$accept(this, d);
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
            public final void accept(Object obj) {
                Consumer.this.accept(obj);
            }

            public Consumer andThen(Consumer consumer2) {
                return Objects.requireNonNull(consumer2);
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
        final SpinedBuffer spinedBuffer = new SpinedBuffer();
        this.buffer = spinedBuffer;
        PipelineHelper pipelineHelper = this.ph;
        Objects.requireNonNull(spinedBuffer);
        this.bufferSink = pipelineHelper.wrapSink(new Sink() {
            @Override
            public void accept(double d) {
                Sink.-CC.$default$accept(this, d);
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
            public final void accept(Object obj) {
                SpinedBuffer.this.accept(obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
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
                return lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$WrappingSpliterator();
            }
        };
    }

    public boolean lambda$initPartialTraversalState$0$java-util-stream-StreamSpliterators$WrappingSpliterator() {
        return this.spliterator.tryAdvance(this.bufferSink);
    }

    @Override
    public boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        boolean doAdvance = doAdvance();
        if (doAdvance) {
            consumer.accept(((SpinedBuffer) this.buffer).get(this.nextToConsume));
        }
        return doAdvance;
    }

    @Override
    public StreamSpliterators$WrappingSpliterator wrap(Spliterator spliterator) {
        return new StreamSpliterators$WrappingSpliterator(this.ph, spliterator, this.isParallel);
    }
}
