package j$.util;

import j$.util.Iterator;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
public final class Spliterators {
    private static final Spliterator EMPTY_SPLITERATOR = new EmptySpliterator.OfRef();
    private static final Spliterator.OfInt EMPTY_INT_SPLITERATOR = new EmptySpliterator.OfInt();
    private static final Spliterator.OfLong EMPTY_LONG_SPLITERATOR = new EmptySpliterator.OfLong();
    private static final Spliterator.OfDouble EMPTY_DOUBLE_SPLITERATOR = new EmptySpliterator.OfDouble();

    class 1Adapter implements java.util.Iterator, Consumer {
        Object nextElement;
        final Spliterator val$spliterator;
        boolean valueReady = false;

        1Adapter(Spliterator spliterator) {
            this.val$spliterator = spliterator;
        }

        @Override
        public void accept(Object obj) {
            this.valueReady = true;
            this.nextElement = obj;
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance(this);
            }
            return this.valueReady;
        }

        @Override
        public Object next() {
            if (this.valueReady || hasNext()) {
                this.valueReady = false;
                return this.nextElement;
            }
            throw new NoSuchElementException();
        }
    }

    class 2Adapter implements PrimitiveIterator.OfInt, IntConsumer, Iterator {
        int nextElement;
        final Spliterator.OfInt val$spliterator;
        boolean valueReady = false;

        2Adapter(Spliterator.OfInt ofInt) {
            this.val$spliterator = ofInt;
        }

        @Override
        public void accept(int i) {
            this.valueReady = true;
            this.nextElement = i;
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void forEachRemaining(Object obj) {
            PrimitiveIterator.OfInt.-CC.$default$forEachRemaining(this, obj);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            PrimitiveIterator.OfInt.-CC.$default$forEachRemaining((PrimitiveIterator.OfInt) this, consumer);
        }

        @Override
        public void forEachRemaining(IntConsumer intConsumer) {
            PrimitiveIterator.OfInt.-CC.$default$forEachRemaining((PrimitiveIterator.OfInt) this, intConsumer);
        }

        @Override
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((IntConsumer) this);
            }
            return this.valueReady;
        }

        @Override
        public Integer next() {
            return PrimitiveIterator.OfInt.-CC.$default$next((PrimitiveIterator.OfInt) this);
        }

        @Override
        public Object next() {
            return PrimitiveIterator.OfInt.-CC.$default$next((PrimitiveIterator.OfInt) this);
        }

        @Override
        public int nextInt() {
            if (this.valueReady || hasNext()) {
                this.valueReady = false;
                return this.nextElement;
            }
            throw new NoSuchElementException();
        }
    }

    class 3Adapter implements PrimitiveIterator.OfLong, LongConsumer, Iterator {
        long nextElement;
        final Spliterator.OfLong val$spliterator;
        boolean valueReady = false;

        3Adapter(Spliterator.OfLong ofLong) {
            this.val$spliterator = ofLong;
        }

        @Override
        public void accept(long j) {
            this.valueReady = true;
            this.nextElement = j;
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void forEachRemaining(Object obj) {
            PrimitiveIterator.OfLong.-CC.$default$forEachRemaining(this, obj);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            PrimitiveIterator.OfLong.-CC.$default$forEachRemaining((PrimitiveIterator.OfLong) this, consumer);
        }

        @Override
        public void forEachRemaining(LongConsumer longConsumer) {
            PrimitiveIterator.OfLong.-CC.$default$forEachRemaining((PrimitiveIterator.OfLong) this, longConsumer);
        }

        @Override
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((LongConsumer) this);
            }
            return this.valueReady;
        }

        @Override
        public Long next() {
            return PrimitiveIterator.OfLong.-CC.$default$next((PrimitiveIterator.OfLong) this);
        }

        @Override
        public Object next() {
            return PrimitiveIterator.OfLong.-CC.$default$next((PrimitiveIterator.OfLong) this);
        }

        @Override
        public long nextLong() {
            if (this.valueReady || hasNext()) {
                this.valueReady = false;
                return this.nextElement;
            }
            throw new NoSuchElementException();
        }
    }

    class 4Adapter implements PrimitiveIterator.OfDouble, DoubleConsumer, Iterator {
        double nextElement;
        final Spliterator.OfDouble val$spliterator;
        boolean valueReady = false;

        4Adapter(Spliterator.OfDouble ofDouble) {
            this.val$spliterator = ofDouble;
        }

        @Override
        public void accept(double d) {
            this.valueReady = true;
            this.nextElement = d;
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void forEachRemaining(Object obj) {
            PrimitiveIterator.OfDouble.-CC.$default$forEachRemaining(this, obj);
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            PrimitiveIterator.OfDouble.-CC.$default$forEachRemaining((PrimitiveIterator.OfDouble) this, consumer);
        }

        @Override
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            PrimitiveIterator.OfDouble.-CC.$default$forEachRemaining((PrimitiveIterator.OfDouble) this, doubleConsumer);
        }

        @Override
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((DoubleConsumer) this);
            }
            return this.valueReady;
        }

        @Override
        public Double next() {
            return PrimitiveIterator.OfDouble.-CC.$default$next((PrimitiveIterator.OfDouble) this);
        }

        @Override
        public Object next() {
            return PrimitiveIterator.OfDouble.-CC.$default$next((PrimitiveIterator.OfDouble) this);
        }

        @Override
        public double nextDouble() {
            if (this.valueReady || hasNext()) {
                this.valueReady = false;
                return this.nextElement;
            }
            throw new NoSuchElementException();
        }
    }

    static final class ArraySpliterator implements Spliterator {
        private final Object[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public ArraySpliterator(Object[] objArr, int i, int i2, int i3) {
            this.array = objArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override
        public int characteristics() {
            return this.characteristics;
        }

        @Override
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            int i;
            consumer.getClass();
            Object[] objArr = this.array;
            int length = objArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    consumer.accept(objArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override
        public java.util.Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
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
        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            Object[] objArr = this.array;
            this.index = i + 1;
            consumer.accept(objArr[i]);
            return true;
        }

        @Override
        public Spliterator trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            Object[] objArr = this.array;
            this.index = i2;
            return new ArraySpliterator(objArr, i, i2, this.characteristics);
        }
    }

    static final class DoubleArraySpliterator implements Spliterator.OfDouble {
        private final double[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public DoubleArraySpliterator(double[] dArr, int i, int i2, int i3) {
            this.array = dArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override
        public int characteristics() {
            return this.characteristics;
        }

        @Override
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.OfDouble.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            int i;
            doubleConsumer.getClass();
            double[] dArr = this.array;
            int length = dArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    doubleConsumer.accept(dArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override
        public java.util.Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
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
        public boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfDouble.-CC.$default$tryAdvance(this, consumer);
        }

        @Override
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            doubleConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            double[] dArr = this.array;
            this.index = i + 1;
            doubleConsumer.accept(dArr[i]);
            return true;
        }

        @Override
        public Spliterator.OfDouble trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            double[] dArr = this.array;
            this.index = i2;
            return new DoubleArraySpliterator(dArr, i, i2, this.characteristics);
        }
    }

    private static abstract class EmptySpliterator {

        private static final class OfDouble extends EmptySpliterator implements Spliterator.OfDouble {
            OfDouble() {
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
            public java.util.Comparator getComparator() {
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

            @Override
            public Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }
        }

        private static final class OfInt extends EmptySpliterator implements Spliterator.OfInt {
            OfInt() {
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
            public java.util.Comparator getComparator() {
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

            @Override
            public Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }
        }

        private static final class OfLong extends EmptySpliterator implements Spliterator.OfLong {
            OfLong() {
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
            public java.util.Comparator getComparator() {
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

            @Override
            public Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }
        }

        private static final class OfRef extends EmptySpliterator implements Spliterator {
            OfRef() {
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                super.forEachRemaining((Object) consumer);
            }

            @Override
            public java.util.Comparator getComparator() {
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
            public boolean tryAdvance(Consumer consumer) {
                return super.tryAdvance((Object) consumer);
            }
        }

        EmptySpliterator() {
        }

        public int characteristics() {
            return 16448;
        }

        public long estimateSize() {
            return 0L;
        }

        public void forEachRemaining(Object obj) {
            Objects.requireNonNull(obj);
        }

        public boolean tryAdvance(Object obj) {
            Objects.requireNonNull(obj);
            return false;
        }

        public Spliterator trySplit() {
            return null;
        }
    }

    static final class IntArraySpliterator implements Spliterator.OfInt {
        private final int[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public IntArraySpliterator(int[] iArr, int i, int i2, int i3) {
            this.array = iArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override
        public int characteristics() {
            return this.characteristics;
        }

        @Override
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.OfInt.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public void forEachRemaining(IntConsumer intConsumer) {
            int i;
            intConsumer.getClass();
            int[] iArr = this.array;
            int length = iArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    intConsumer.accept(iArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override
        public java.util.Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
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
        public boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfInt.-CC.$default$tryAdvance(this, consumer);
        }

        @Override
        public boolean tryAdvance(IntConsumer intConsumer) {
            intConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            int[] iArr = this.array;
            this.index = i + 1;
            intConsumer.accept(iArr[i]);
            return true;
        }

        @Override
        public Spliterator.OfInt trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            int[] iArr = this.array;
            this.index = i2;
            return new IntArraySpliterator(iArr, i, i2, this.characteristics);
        }
    }

    static class IteratorSpliterator implements Spliterator {
        private int batch;
        private final int characteristics;
        private final java.util.Collection collection;
        private long est;
        private java.util.Iterator it;

        public IteratorSpliterator(java.util.Collection collection, int i) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (i & 4096) == 0 ? i | 16448 : i;
        }

        public IteratorSpliterator(java.util.Iterator it, int i) {
            this.collection = null;
            this.it = it;
            this.est = Long.MAX_VALUE;
            this.characteristics = i & (-16449);
        }

        @Override
        public int characteristics() {
            return this.characteristics;
        }

        @Override
        public long estimateSize() {
            if (this.it == null) {
                this.it = this.collection.iterator();
                long size = this.collection.size();
                this.est = size;
                return size;
            }
            return this.est;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            consumer.getClass();
            java.util.Iterator it = this.it;
            if (it == null) {
                it = this.collection.iterator();
                this.it = it;
                this.est = this.collection.size();
            }
            Iterator.-EL.forEachRemaining(it, consumer);
        }

        @Override
        public java.util.Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
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
        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            if (this.it == null) {
                this.it = this.collection.iterator();
                this.est = this.collection.size();
            }
            if (this.it.hasNext()) {
                consumer.accept(this.it.next());
                return true;
            }
            return false;
        }

        @Override
        public Spliterator trySplit() {
            long j;
            java.util.Iterator it = this.it;
            if (it == null) {
                it = this.collection.iterator();
                this.it = it;
                j = this.collection.size();
                this.est = j;
            } else {
                j = this.est;
            }
            if (j <= 1 || !it.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = it.next();
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (it.hasNext());
            this.batch = i2;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - i2;
            }
            return new ArraySpliterator(objArr, 0, i2, this.characteristics);
        }
    }

    static final class LongArraySpliterator implements Spliterator.OfLong {
        private final long[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public LongArraySpliterator(long[] jArr, int i, int i2, int i3) {
            this.array = jArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 16448;
        }

        @Override
        public int characteristics() {
            return this.characteristics;
        }

        @Override
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.OfLong.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public void forEachRemaining(LongConsumer longConsumer) {
            int i;
            longConsumer.getClass();
            long[] jArr = this.array;
            int length = jArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    longConsumer.accept(jArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override
        public java.util.Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
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
        public boolean tryAdvance(Consumer consumer) {
            return Spliterator.OfLong.-CC.$default$tryAdvance(this, consumer);
        }

        @Override
        public boolean tryAdvance(LongConsumer longConsumer) {
            longConsumer.getClass();
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            long[] jArr = this.array;
            this.index = i + 1;
            longConsumer.accept(jArr[i]);
            return true;
        }

        @Override
        public Spliterator.OfLong trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            long[] jArr = this.array;
            this.index = i2;
            return new LongArraySpliterator(jArr, i, i2, this.characteristics);
        }
    }

    private static void checkFromToBounds(int i, int i2, int i3) {
        if (i2 <= i3) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException(i2);
            }
            if (i3 > i) {
                throw new ArrayIndexOutOfBoundsException(i3);
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException("origin(" + i2 + ") > fence(" + i3 + ")");
    }

    public static Spliterator.OfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    public static Spliterator.OfInt emptyIntSpliterator() {
        return EMPTY_INT_SPLITERATOR;
    }

    public static Spliterator.OfLong emptyLongSpliterator() {
        return EMPTY_LONG_SPLITERATOR;
    }

    public static Spliterator emptySpliterator() {
        return EMPTY_SPLITERATOR;
    }

    public static PrimitiveIterator.OfDouble iterator(Spliterator.OfDouble ofDouble) {
        Objects.requireNonNull(ofDouble);
        return new 4Adapter(ofDouble);
    }

    public static PrimitiveIterator.OfInt iterator(Spliterator.OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new 2Adapter(ofInt);
    }

    public static PrimitiveIterator.OfLong iterator(Spliterator.OfLong ofLong) {
        Objects.requireNonNull(ofLong);
        return new 3Adapter(ofLong);
    }

    public static java.util.Iterator iterator(Spliterator spliterator) {
        Objects.requireNonNull(spliterator);
        return new 1Adapter(spliterator);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2, int i3) {
        checkFromToBounds(((double[]) Objects.requireNonNull(dArr)).length, i, i2);
        return new DoubleArraySpliterator(dArr, i, i2, i3);
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2, int i3) {
        checkFromToBounds(((int[]) Objects.requireNonNull(iArr)).length, i, i2);
        return new IntArraySpliterator(iArr, i, i2, i3);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2, int i3) {
        checkFromToBounds(((long[]) Objects.requireNonNull(jArr)).length, i, i2);
        return new LongArraySpliterator(jArr, i, i2, i3);
    }

    public static Spliterator spliterator(java.util.Collection collection, int i) {
        return new IteratorSpliterator((java.util.Collection) Objects.requireNonNull(collection), i);
    }

    public static Spliterator spliterator(Object[] objArr, int i, int i2, int i3) {
        checkFromToBounds(((Object[]) Objects.requireNonNull(objArr)).length, i, i2);
        return new ArraySpliterator(objArr, i, i2, i3);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(java.util.Iterator<? extends T> it, int i) {
        return new IteratorSpliterator((java.util.Iterator) Objects.requireNonNull(it), i);
    }
}
