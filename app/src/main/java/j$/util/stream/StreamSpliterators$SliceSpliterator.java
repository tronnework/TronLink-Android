package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.StreamSpliterators$SliceSpliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
public abstract class StreamSpliterators$SliceSpliterator {
    long fence;
    long index;
    Spliterator s;
    final long sliceFence;
    final long sliceOrigin;

    static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
        public OfDouble(Spliterator.OfDouble ofDouble, long j, long j2) {
            super(ofDouble, j, j2);
        }

        OfDouble(Spliterator.OfDouble ofDouble, long j, long j2, long j3, long j4) {
            super(ofDouble, j, j2, j3, j4);
        }

        public static void lambda$emptyConsumer$0(double d) {
        }

        @Override
        public DoubleConsumer emptyConsumer() {
            return new DoubleConsumer() {
                @Override
                public final void accept(double d) {
                    StreamSpliterators$SliceSpliterator.OfDouble.lambda$emptyConsumer$0(d);
                }

                public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                    return Objects.requireNonNull(doubleConsumer);
                }
            };
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
        public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble ofDouble, long j, long j2, long j3, long j4) {
            return new OfDouble(ofDouble, j, j2, j3, j4);
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

    static final class OfInt extends OfPrimitive implements Spliterator.OfInt {
        public OfInt(Spliterator.OfInt ofInt, long j, long j2) {
            super(ofInt, j, j2);
        }

        OfInt(Spliterator.OfInt ofInt, long j, long j2, long j3, long j4) {
            super(ofInt, j, j2, j3, j4);
        }

        public static void lambda$emptyConsumer$0(int i) {
        }

        @Override
        public IntConsumer emptyConsumer() {
            return new IntConsumer() {
                @Override
                public final void accept(int i) {
                    StreamSpliterators$SliceSpliterator.OfInt.lambda$emptyConsumer$0(i);
                }

                public IntConsumer andThen(IntConsumer intConsumer) {
                    return Objects.requireNonNull(intConsumer);
                }
            };
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
        public Spliterator.OfInt makeSpliterator(Spliterator.OfInt ofInt, long j, long j2, long j3, long j4) {
            return new OfInt(ofInt, j, j2, j3, j4);
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

    static final class OfLong extends OfPrimitive implements Spliterator.OfLong {
        public OfLong(Spliterator.OfLong ofLong, long j, long j2) {
            super(ofLong, j, j2);
        }

        OfLong(Spliterator.OfLong ofLong, long j, long j2, long j3, long j4) {
            super(ofLong, j, j2, j3, j4);
        }

        public static void lambda$emptyConsumer$0(long j) {
        }

        @Override
        public LongConsumer emptyConsumer() {
            return new LongConsumer() {
                @Override
                public final void accept(long j) {
                    StreamSpliterators$SliceSpliterator.OfLong.lambda$emptyConsumer$0(j);
                }

                public LongConsumer andThen(LongConsumer longConsumer) {
                    return Objects.requireNonNull(longConsumer);
                }
            };
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
        public Spliterator.OfLong makeSpliterator(Spliterator.OfLong ofLong, long j, long j2, long j3, long j4) {
            return new OfLong(ofLong, j, j2, j3, j4);
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

    static abstract class OfPrimitive extends StreamSpliterators$SliceSpliterator implements Spliterator.OfPrimitive {
        OfPrimitive(Spliterator.OfPrimitive ofPrimitive, long j, long j2) {
            this(ofPrimitive, j, j2, 0L, Math.min(ofPrimitive.estimateSize(), j2));
        }

        private OfPrimitive(Spliterator.OfPrimitive ofPrimitive, long j, long j2, long j3, long j4) {
            super(ofPrimitive, j, j2, j3, j4);
        }

        protected abstract Object emptyConsumer();

        @Override
        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
            long j = this.sliceOrigin;
            long j2 = this.fence;
            if (j >= j2) {
                return;
            }
            long j3 = this.index;
            if (j3 >= j2) {
                return;
            }
            if (j3 >= j && j3 + ((Spliterator.OfPrimitive) this.s).estimateSize() <= this.sliceFence) {
                ((Spliterator.OfPrimitive) this.s).forEachRemaining(obj);
                this.index = this.fence;
                return;
            }
            while (this.sliceOrigin > this.index) {
                ((Spliterator.OfPrimitive) this.s).tryAdvance(emptyConsumer());
                this.index++;
            }
            while (this.index < this.fence) {
                ((Spliterator.OfPrimitive) this.s).tryAdvance(obj);
                this.index++;
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
            long j;
            Objects.requireNonNull(obj);
            if (this.sliceOrigin >= this.fence) {
                return false;
            }
            while (true) {
                long j2 = this.sliceOrigin;
                j = this.index;
                if (j2 <= j) {
                    break;
                }
                ((Spliterator.OfPrimitive) this.s).tryAdvance(emptyConsumer());
                this.index++;
            }
            if (j >= this.fence) {
                return false;
            }
            this.index = j + 1;
            return ((Spliterator.OfPrimitive) this.s).tryAdvance(obj);
        }

        @Override
        public Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }
    }

    static final class OfRef extends StreamSpliterators$SliceSpliterator implements Spliterator {
        public OfRef(Spliterator spliterator, long j, long j2) {
            this(spliterator, j, j2, 0L, Math.min(spliterator.estimateSize(), j2));
        }

        private OfRef(Spliterator spliterator, long j, long j2, long j3, long j4) {
            super(spliterator, j, j2, j3, j4);
        }

        public static void lambda$forEachRemaining$1(Object obj) {
        }

        public static void lambda$tryAdvance$0(Object obj) {
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            long j = this.sliceOrigin;
            long j2 = this.fence;
            if (j >= j2) {
                return;
            }
            long j3 = this.index;
            if (j3 >= j2) {
                return;
            }
            if (j3 >= j && j3 + this.s.estimateSize() <= this.sliceFence) {
                this.s.forEachRemaining(consumer);
                this.index = this.fence;
                return;
            }
            while (this.sliceOrigin > this.index) {
                this.s.tryAdvance(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        StreamSpliterators$SliceSpliterator.OfRef.lambda$forEachRemaining$1(obj);
                    }

                    public Consumer andThen(Consumer consumer2) {
                        return Objects.requireNonNull(consumer2);
                    }
                });
                this.index++;
            }
            while (this.index < this.fence) {
                this.s.tryAdvance(consumer);
                this.index++;
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
        protected Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
            return new OfRef(spliterator, j, j2, j3, j4);
        }

        @Override
        public boolean tryAdvance(Consumer consumer) {
            long j;
            Objects.requireNonNull(consumer);
            if (this.sliceOrigin >= this.fence) {
                return false;
            }
            while (true) {
                long j2 = this.sliceOrigin;
                j = this.index;
                if (j2 <= j) {
                    break;
                }
                this.s.tryAdvance(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        StreamSpliterators$SliceSpliterator.OfRef.lambda$tryAdvance$0(obj);
                    }

                    public Consumer andThen(Consumer consumer2) {
                        return Objects.requireNonNull(consumer2);
                    }
                });
                this.index++;
            }
            if (j >= this.fence) {
                return false;
            }
            this.index = j + 1;
            return this.s.tryAdvance(consumer);
        }
    }

    StreamSpliterators$SliceSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4) {
        this.s = spliterator;
        this.sliceOrigin = j;
        this.sliceFence = j2;
        this.index = j3;
        this.fence = j4;
    }

    public int characteristics() {
        return this.s.characteristics();
    }

    public long estimateSize() {
        long j = this.sliceOrigin;
        long j2 = this.fence;
        if (j < j2) {
            return j2 - Math.max(j, this.index);
        }
        return 0L;
    }

    protected abstract Spliterator makeSpliterator(Spliterator spliterator, long j, long j2, long j3, long j4);

    public Spliterator trySplit() {
        long j = this.sliceOrigin;
        long j2 = this.fence;
        if (j >= j2 || this.index >= j2) {
            return null;
        }
        while (true) {
            Spliterator trySplit = this.s.trySplit();
            if (trySplit == null) {
                return null;
            }
            long estimateSize = this.index + trySplit.estimateSize();
            long min = Math.min(estimateSize, this.sliceFence);
            long j3 = this.sliceOrigin;
            if (j3 >= min) {
                this.index = min;
            } else {
                long j4 = this.sliceFence;
                if (min < j4) {
                    long j5 = this.index;
                    if (j5 < j3 || estimateSize > j4) {
                        this.index = min;
                        return makeSpliterator(trySplit, j3, j4, j5, min);
                    }
                    this.index = min;
                    return trySplit;
                }
                this.s = trySplit;
                this.fence = min;
            }
        }
    }
}
