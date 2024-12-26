package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.StreamSpliterators$ArrayBuffer;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
public abstract class StreamSpliterators$UnorderedSliceSpliterator {
    protected final int chunkSize;
    private final AtomicLong permits;
    protected final Spliterator s;
    private final long skipThreshold;
    protected final boolean unlimited;

    static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble, DoubleConsumer {
        double tmpValue;

        public OfDouble(Spliterator.OfDouble ofDouble, long j, long j2) {
            super(ofDouble, j, j2);
        }

        OfDouble(Spliterator.OfDouble ofDouble, OfDouble ofDouble2) {
            super(ofDouble, ofDouble2);
        }

        @Override
        public void accept(double d) {
            this.tmpValue = d;
        }

        @Override
        public void acceptConsumed(DoubleConsumer doubleConsumer) {
            doubleConsumer.accept(this.tmpValue);
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public StreamSpliterators$ArrayBuffer.OfDouble bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfDouble(i);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.OfDouble.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            super.forEachRemaining((Object) doubleConsumer);
        }

        @Override
        public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble ofDouble) {
            return new OfDouble(ofDouble, this);
        }

        @Override
        public boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfDouble.-CC.$default$tryAdvance(this, consumer);
        }

        @Override
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return super.tryAdvance((Object) doubleConsumer);
        }

        @Override
        public Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) super.trySplit();
        }
    }

    static final class OfInt extends OfPrimitive implements Spliterator.OfInt, IntConsumer {
        int tmpValue;

        public OfInt(Spliterator.OfInt ofInt, long j, long j2) {
            super(ofInt, j, j2);
        }

        OfInt(Spliterator.OfInt ofInt, OfInt ofInt2) {
            super(ofInt, ofInt2);
        }

        @Override
        public void accept(int i) {
            this.tmpValue = i;
        }

        @Override
        public void acceptConsumed(IntConsumer intConsumer) {
            intConsumer.accept(this.tmpValue);
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public StreamSpliterators$ArrayBuffer.OfInt bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfInt(i);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.OfInt.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public void forEachRemaining(IntConsumer intConsumer) {
            super.forEachRemaining((Object) intConsumer);
        }

        @Override
        public Spliterator.OfInt makeSpliterator(Spliterator.OfInt ofInt) {
            return new OfInt(ofInt, this);
        }

        @Override
        public boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfInt.-CC.$default$tryAdvance(this, consumer);
        }

        @Override
        public boolean tryAdvance(IntConsumer intConsumer) {
            return super.tryAdvance((Object) intConsumer);
        }

        @Override
        public Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) super.trySplit();
        }
    }

    static final class OfLong extends OfPrimitive implements Spliterator.OfLong, LongConsumer {
        long tmpValue;

        public OfLong(Spliterator.OfLong ofLong, long j, long j2) {
            super(ofLong, j, j2);
        }

        OfLong(Spliterator.OfLong ofLong, OfLong ofLong2) {
            super(ofLong, ofLong2);
        }

        @Override
        public void accept(long j) {
            this.tmpValue = j;
        }

        @Override
        public void acceptConsumed(LongConsumer longConsumer) {
            longConsumer.accept(this.tmpValue);
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public StreamSpliterators$ArrayBuffer.OfLong bufferCreate(int i) {
            return new StreamSpliterators$ArrayBuffer.OfLong(i);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.OfLong.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public void forEachRemaining(LongConsumer longConsumer) {
            super.forEachRemaining((Object) longConsumer);
        }

        @Override
        public Spliterator.OfLong makeSpliterator(Spliterator.OfLong ofLong) {
            return new OfLong(ofLong, this);
        }

        @Override
        public boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfLong.-CC.$default$tryAdvance(this, consumer);
        }

        @Override
        public boolean tryAdvance(LongConsumer longConsumer) {
            return super.tryAdvance((Object) longConsumer);
        }

        @Override
        public Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) super.trySplit();
        }
    }

    static abstract class OfPrimitive extends StreamSpliterators$UnorderedSliceSpliterator implements Spliterator.OfPrimitive {
        OfPrimitive(Spliterator.OfPrimitive ofPrimitive, long j, long j2) {
            super(ofPrimitive, j, j2);
        }

        OfPrimitive(Spliterator.OfPrimitive ofPrimitive, OfPrimitive ofPrimitive2) {
            super(ofPrimitive, ofPrimitive2);
        }

        protected abstract void acceptConsumed(Object obj);

        protected abstract StreamSpliterators$ArrayBuffer.OfPrimitive bufferCreate(int i);

        @Override
        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
            StreamSpliterators$ArrayBuffer.OfPrimitive ofPrimitive = null;
            while (true) {
                PermitStatus permitStatus = permitStatus();
                if (permitStatus == PermitStatus.NO_MORE) {
                    return;
                }
                if (permitStatus != PermitStatus.MAYBE_MORE) {
                    ((Spliterator.OfPrimitive) this.s).forEachRemaining(obj);
                    return;
                }
                if (ofPrimitive == null) {
                    ofPrimitive = bufferCreate(this.chunkSize);
                } else {
                    ofPrimitive.reset();
                }
                long j = 0;
                while (((Spliterator.OfPrimitive) this.s).tryAdvance(ofPrimitive)) {
                    j++;
                    if (j >= this.chunkSize) {
                        break;
                    }
                }
                if (j == 0) {
                    return;
                }
                ofPrimitive.forEach(obj, acquirePermits(j));
            }
        }

        @Override
        public Comparator getComparator() {
            return Spliterator.-CC.$default$getComparator(this);
        }

        @Override
        public long getExactSizeIfKnown() {
            return Spliterator.-CC.$default$getExactSizeIfKnown(this);
        }

        @Override
        public boolean hasCharacteristics(int i) {
            return Spliterator.-CC.$default$hasCharacteristics(this, i);
        }

        @Override
        public boolean tryAdvance(Object obj) {
            Objects.requireNonNull(obj);
            while (permitStatus() != PermitStatus.NO_MORE && ((Spliterator.OfPrimitive) this.s).tryAdvance(this)) {
                if (acquirePermits(1L) == 1) {
                    acceptConsumed(obj);
                    return true;
                }
            }
            return false;
        }

        @Override
        public Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }
    }

    static final class OfRef extends StreamSpliterators$UnorderedSliceSpliterator implements Spliterator, Consumer {
        Object tmpSlot;

        public OfRef(Spliterator spliterator, long j, long j2) {
            super(spliterator, j, j2);
        }

        OfRef(Spliterator spliterator, OfRef ofRef) {
            super(spliterator, ofRef);
        }

        @Override
        public final void accept(Object obj) {
            this.tmpSlot = obj;
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            StreamSpliterators$ArrayBuffer.OfRef ofRef = null;
            while (true) {
                PermitStatus permitStatus = permitStatus();
                if (permitStatus == PermitStatus.NO_MORE) {
                    return;
                }
                if (permitStatus != PermitStatus.MAYBE_MORE) {
                    this.s.forEachRemaining(consumer);
                    return;
                }
                if (ofRef == null) {
                    ofRef = new StreamSpliterators$ArrayBuffer.OfRef(this.chunkSize);
                } else {
                    ofRef.reset();
                }
                long j = 0;
                while (this.s.tryAdvance(ofRef)) {
                    j++;
                    if (j >= this.chunkSize) {
                        break;
                    }
                }
                if (j == 0) {
                    return;
                }
                ofRef.forEach(consumer, acquirePermits(j));
            }
        }

        @Override
        public Comparator getComparator() {
            return Spliterator.-CC.$default$getComparator(this);
        }

        @Override
        public long getExactSizeIfKnown() {
            return Spliterator.-CC.$default$getExactSizeIfKnown(this);
        }

        @Override
        public boolean hasCharacteristics(int i) {
            return Spliterator.-CC.$default$hasCharacteristics(this, i);
        }

        @Override
        protected Spliterator makeSpliterator(Spliterator spliterator) {
            return new OfRef(spliterator, this);
        }

        @Override
        public boolean tryAdvance(Consumer consumer) {
            Objects.requireNonNull(consumer);
            while (permitStatus() != PermitStatus.NO_MORE && this.s.tryAdvance(this)) {
                if (acquirePermits(1L) == 1) {
                    consumer.accept(this.tmpSlot);
                    this.tmpSlot = null;
                    return true;
                }
            }
            return false;
        }
    }

    public enum PermitStatus {
        NO_MORE,
        MAYBE_MORE,
        UNLIMITED
    }

    StreamSpliterators$UnorderedSliceSpliterator(Spliterator spliterator, long j, long j2) {
        this.s = spliterator;
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        this.unlimited = i < 0;
        this.skipThreshold = i >= 0 ? j2 : 0L;
        this.chunkSize = 128;
        this.permits = new AtomicLong(i >= 0 ? j + j2 : j);
    }

    StreamSpliterators$UnorderedSliceSpliterator(Spliterator spliterator, StreamSpliterators$UnorderedSliceSpliterator streamSpliterators$UnorderedSliceSpliterator) {
        this.s = spliterator;
        this.unlimited = streamSpliterators$UnorderedSliceSpliterator.unlimited;
        this.permits = streamSpliterators$UnorderedSliceSpliterator.permits;
        this.skipThreshold = streamSpliterators$UnorderedSliceSpliterator.skipThreshold;
        this.chunkSize = streamSpliterators$UnorderedSliceSpliterator.chunkSize;
    }

    protected final long acquirePermits(long j) {
        long j2;
        long min;
        do {
            j2 = this.permits.get();
            if (j2 != 0) {
                min = Math.min(j2, j);
                if (min <= 0) {
                    break;
                }
            } else if (this.unlimited) {
                return j;
            } else {
                return 0L;
            }
        } while (!this.permits.compareAndSet(j2, j2 - min));
        if (this.unlimited) {
            return Math.max(j - min, 0L);
        }
        long j3 = this.skipThreshold;
        return j2 > j3 ? Math.max(min - (j2 - j3), 0L) : min;
    }

    public final int characteristics() {
        return this.s.characteristics() & (-16465);
    }

    public final long estimateSize() {
        return this.s.estimateSize();
    }

    protected abstract Spliterator makeSpliterator(Spliterator spliterator);

    protected final PermitStatus permitStatus() {
        return this.permits.get() > 0 ? PermitStatus.MAYBE_MORE : this.unlimited ? PermitStatus.UNLIMITED : PermitStatus.NO_MORE;
    }

    public final Spliterator trySplit() {
        Spliterator trySplit;
        if (this.permits.get() == 0 || (trySplit = this.s.trySplit()) == null) {
            return null;
        }
        return makeSpliterator(trySplit);
    }
}
