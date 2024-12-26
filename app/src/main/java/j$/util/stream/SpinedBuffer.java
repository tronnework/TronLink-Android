package j$.util.stream;

import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
public class SpinedBuffer extends AbstractSpinedBuffer implements Consumer, Iterable {
    protected Object[] curChunk = new Object[1 << this.initialChunkPower];
    protected Object[][] spine;

    public class 1Splitr implements Spliterator {
        final int lastSpineElementFence;
        final int lastSpineIndex;
        Object[] splChunk;
        int splElementIndex;
        int splSpineIndex;

        1Splitr(int i, int i2, int i3, int i4) {
            this.splSpineIndex = i;
            this.lastSpineIndex = i2;
            this.splElementIndex = i3;
            this.lastSpineElementFence = i4;
            Object[][] objArr = spine;
            this.splChunk = objArr == null ? curChunk : objArr[i];
        }

        @Override
        public int characteristics() {
            return 16464;
        }

        @Override
        public long estimateSize() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i == i2) {
                return this.lastSpineElementFence - this.splElementIndex;
            }
            long[] jArr = priorElementCount;
            return ((jArr[i2] + this.lastSpineElementFence) - jArr[i]) - this.splElementIndex;
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            int i;
            Objects.requireNonNull(consumer);
            int i2 = this.splSpineIndex;
            int i3 = this.lastSpineIndex;
            if (i2 < i3 || (i2 == i3 && this.splElementIndex < this.lastSpineElementFence)) {
                int i4 = this.splElementIndex;
                while (true) {
                    i = this.lastSpineIndex;
                    if (i2 >= i) {
                        break;
                    }
                    Object[] objArr = spine[i2];
                    while (i4 < objArr.length) {
                        consumer.accept(objArr[i4]);
                        i4++;
                    }
                    i2++;
                    i4 = 0;
                }
                Object[] objArr2 = this.splSpineIndex == i ? this.splChunk : spine[i];
                int i5 = this.lastSpineElementFence;
                while (i4 < i5) {
                    consumer.accept(objArr2[i4]);
                    i4++;
                }
                this.splSpineIndex = this.lastSpineIndex;
                this.splElementIndex = this.lastSpineElementFence;
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
        public boolean tryAdvance(Consumer consumer) {
            Objects.requireNonNull(consumer);
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i < i2 || (i == i2 && this.splElementIndex < this.lastSpineElementFence)) {
                Object[] objArr = this.splChunk;
                int i3 = this.splElementIndex;
                this.splElementIndex = i3 + 1;
                consumer.accept(objArr[i3]);
                if (this.splElementIndex == this.splChunk.length) {
                    this.splElementIndex = 0;
                    int i4 = this.splSpineIndex + 1;
                    this.splSpineIndex = i4;
                    Object[][] objArr2 = spine;
                    if (objArr2 != null && i4 <= this.lastSpineIndex) {
                        this.splChunk = objArr2[i4];
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        public Spliterator trySplit() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i < i2) {
                SpinedBuffer spinedBuffer = SpinedBuffer.this;
                1Splitr r6 = new 1Splitr(i, i2 - 1, this.splElementIndex, spinedBuffer.spine[i2 - 1].length);
                int i3 = this.lastSpineIndex;
                this.splSpineIndex = i3;
                this.splElementIndex = 0;
                this.splChunk = spine[i3];
                return r6;
            } else if (i == i2) {
                int i4 = this.lastSpineElementFence;
                int i5 = this.splElementIndex;
                int i6 = (i4 - i5) / 2;
                if (i6 == 0) {
                    return null;
                }
                Spliterator spliterator = DesugarArrays.spliterator(this.splChunk, i5, i5 + i6);
                this.splElementIndex += i6;
                return spliterator;
            } else {
                return null;
            }
        }
    }

    public static class OfDouble extends OfPrimitive implements DoubleConsumer {

        public class 1Splitr extends OfPrimitive.BaseSpliterator implements Spliterator.OfDouble {
            1Splitr(int i, int i2, int i3, int i4) {
                super(i, i2, i3, i4);
            }

            @Override
            public void arrayForOne(double[] dArr, int i, DoubleConsumer doubleConsumer) {
                doubleConsumer.accept(dArr[i]);
            }

            @Override
            public Spliterator.OfDouble arraySpliterator(double[] dArr, int i, int i2) {
                return DesugarArrays.spliterator(dArr, i, i2 + i);
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
            public 1Splitr newSpliterator(int i, int i2, int i3, int i4) {
                return new 1Splitr(i, i2, i3, i4);
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

        public OfDouble() {
        }

        public OfDouble(int i) {
            super(i);
        }

        public void accept(double d) {
            preAccept();
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            ((double[]) this.curChunk)[i] = d;
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void arrayForEach(double[] dArr, int i, int i2, DoubleConsumer doubleConsumer) {
            while (i < i2) {
                doubleConsumer.accept(dArr[i]);
                i++;
            }
        }

        @Override
        public int arrayLength(double[] dArr) {
            return dArr.length;
        }

        @Override
        public void forEach(Consumer consumer) {
            if (consumer instanceof DoubleConsumer) {
                forEach((DoubleConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfDouble.forEach(Consumer)");
            }
            spliterator().forEachRemaining(consumer);
        }

        public double get(long j) {
            int chunkFor = chunkFor(j);
            return (this.spineIndex == 0 && chunkFor == 0) ? ((double[]) this.curChunk)[(int) j] : ((double[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        @Override
        public PrimitiveIterator.OfDouble iterator() {
            return Spliterators.iterator(spliterator());
        }

        @Override
        public double[] newArray(int i) {
            return new double[i];
        }

        @Override
        public double[][] newArrayArray(int i) {
            return new double[i];
        }

        @Override
        public Spliterator.OfDouble spliterator() {
            return new 1Splitr(0, this.spineIndex, 0, this.elementIndex);
        }

        public String toString() {
            double[] dArr = (double[]) asPrimitiveArray();
            return dArr.length < 200 ? String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(dArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(dArr)) : String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(dArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(Arrays.copyOf(dArr, 200)));
        }
    }

    public static class OfInt extends OfPrimitive implements IntConsumer {

        public class 1Splitr extends OfPrimitive.BaseSpliterator implements Spliterator.OfInt {
            1Splitr(int i, int i2, int i3, int i4) {
                super(i, i2, i3, i4);
            }

            @Override
            public void arrayForOne(int[] iArr, int i, IntConsumer intConsumer) {
                intConsumer.accept(iArr[i]);
            }

            @Override
            public Spliterator.OfInt arraySpliterator(int[] iArr, int i, int i2) {
                return DesugarArrays.spliterator(iArr, i, i2 + i);
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
            public 1Splitr newSpliterator(int i, int i2, int i3, int i4) {
                return new 1Splitr(i, i2, i3, i4);
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

        public OfInt() {
        }

        public OfInt(int i) {
            super(i);
        }

        public void accept(int i) {
            preAccept();
            int i2 = this.elementIndex;
            this.elementIndex = i2 + 1;
            ((int[]) this.curChunk)[i2] = i;
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void arrayForEach(int[] iArr, int i, int i2, IntConsumer intConsumer) {
            while (i < i2) {
                intConsumer.accept(iArr[i]);
                i++;
            }
        }

        @Override
        public int arrayLength(int[] iArr) {
            return iArr.length;
        }

        @Override
        public void forEach(Consumer consumer) {
            if (consumer instanceof IntConsumer) {
                forEach((IntConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfInt.forEach(Consumer)");
            }
            spliterator().forEachRemaining(consumer);
        }

        public int get(long j) {
            int chunkFor = chunkFor(j);
            return (this.spineIndex == 0 && chunkFor == 0) ? ((int[]) this.curChunk)[(int) j] : ((int[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        @Override
        public PrimitiveIterator.OfInt iterator() {
            return Spliterators.iterator(spliterator());
        }

        @Override
        public int[] newArray(int i) {
            return new int[i];
        }

        @Override
        public int[][] newArrayArray(int i) {
            return new int[i];
        }

        @Override
        public Spliterator.OfInt spliterator() {
            return new 1Splitr(0, this.spineIndex, 0, this.elementIndex);
        }

        public String toString() {
            int[] iArr = (int[]) asPrimitiveArray();
            return iArr.length < 200 ? String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(iArr)) : String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(Arrays.copyOf(iArr, 200)));
        }
    }

    public static class OfLong extends OfPrimitive implements LongConsumer {

        public class 1Splitr extends OfPrimitive.BaseSpliterator implements Spliterator.OfLong {
            1Splitr(int i, int i2, int i3, int i4) {
                super(i, i2, i3, i4);
            }

            @Override
            public void arrayForOne(long[] jArr, int i, LongConsumer longConsumer) {
                longConsumer.accept(jArr[i]);
            }

            @Override
            public Spliterator.OfLong arraySpliterator(long[] jArr, int i, int i2) {
                return DesugarArrays.spliterator(jArr, i, i2 + i);
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
            public 1Splitr newSpliterator(int i, int i2, int i3, int i4) {
                return new 1Splitr(i, i2, i3, i4);
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

        public OfLong() {
        }

        public OfLong(int i) {
            super(i);
        }

        public void accept(long j) {
            preAccept();
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            ((long[]) this.curChunk)[i] = j;
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void arrayForEach(long[] jArr, int i, int i2, LongConsumer longConsumer) {
            while (i < i2) {
                longConsumer.accept(jArr[i]);
                i++;
            }
        }

        @Override
        public int arrayLength(long[] jArr) {
            return jArr.length;
        }

        @Override
        public void forEach(Consumer consumer) {
            if (consumer instanceof LongConsumer) {
                forEach((LongConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfLong.forEach(Consumer)");
            }
            spliterator().forEachRemaining(consumer);
        }

        public long get(long j) {
            int chunkFor = chunkFor(j);
            return (this.spineIndex == 0 && chunkFor == 0) ? ((long[]) this.curChunk)[(int) j] : ((long[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        @Override
        public PrimitiveIterator.OfLong iterator() {
            return Spliterators.iterator(spliterator());
        }

        @Override
        public long[] newArray(int i) {
            return new long[i];
        }

        @Override
        public long[][] newArrayArray(int i) {
            return new long[i];
        }

        @Override
        public Spliterator.OfLong spliterator() {
            return new 1Splitr(0, this.spineIndex, 0, this.elementIndex);
        }

        public String toString() {
            long[] jArr = (long[]) asPrimitiveArray();
            return jArr.length < 200 ? String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(jArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(jArr)) : String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(jArr.length), Integer.valueOf(this.spineIndex), Arrays.toString(Arrays.copyOf(jArr, 200)));
        }
    }

    public static abstract class OfPrimitive extends AbstractSpinedBuffer implements Iterable {
        Object curChunk;
        Object[] spine;

        abstract class BaseSpliterator implements Spliterator.OfPrimitive {
            final int lastSpineElementFence;
            final int lastSpineIndex;
            Object splChunk;
            int splElementIndex;
            int splSpineIndex;

            BaseSpliterator(int i, int i2, int i3, int i4) {
                this.splSpineIndex = i;
                this.lastSpineIndex = i2;
                this.splElementIndex = i3;
                this.lastSpineElementFence = i4;
                Object[] objArr = OfPrimitive.this.spine;
                this.splChunk = objArr == null ? OfPrimitive.this.curChunk : objArr[i];
            }

            abstract void arrayForOne(Object obj, int i, Object obj2);

            abstract Spliterator.OfPrimitive arraySpliterator(Object obj, int i, int i2);

            @Override
            public int characteristics() {
                return 16464;
            }

            @Override
            public long estimateSize() {
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i == i2) {
                    return this.lastSpineElementFence - this.splElementIndex;
                }
                long[] jArr = OfPrimitive.this.priorElementCount;
                return ((jArr[i2] + this.lastSpineElementFence) - jArr[i]) - this.splElementIndex;
            }

            @Override
            public void forEachRemaining(Object obj) {
                int i;
                Objects.requireNonNull(obj);
                int i2 = this.splSpineIndex;
                int i3 = this.lastSpineIndex;
                if (i2 < i3 || (i2 == i3 && this.splElementIndex < this.lastSpineElementFence)) {
                    int i4 = this.splElementIndex;
                    while (true) {
                        i = this.lastSpineIndex;
                        if (i2 >= i) {
                            break;
                        }
                        OfPrimitive ofPrimitive = OfPrimitive.this;
                        Object obj2 = ofPrimitive.spine[i2];
                        ofPrimitive.arrayForEach(obj2, i4, ofPrimitive.arrayLength(obj2), obj);
                        i2++;
                        i4 = 0;
                    }
                    OfPrimitive.this.arrayForEach(this.splSpineIndex == i ? this.splChunk : OfPrimitive.this.spine[i], i4, this.lastSpineElementFence, obj);
                    this.splSpineIndex = this.lastSpineIndex;
                    this.splElementIndex = this.lastSpineElementFence;
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

            abstract Spliterator.OfPrimitive newSpliterator(int i, int i2, int i3, int i4);

            @Override
            public boolean tryAdvance(Object obj) {
                Objects.requireNonNull(obj);
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i < i2 || (i == i2 && this.splElementIndex < this.lastSpineElementFence)) {
                    Object obj2 = this.splChunk;
                    int i3 = this.splElementIndex;
                    this.splElementIndex = i3 + 1;
                    arrayForOne(obj2, i3, obj);
                    if (this.splElementIndex == OfPrimitive.this.arrayLength(this.splChunk)) {
                        this.splElementIndex = 0;
                        int i4 = this.splSpineIndex + 1;
                        this.splSpineIndex = i4;
                        Object[] objArr = OfPrimitive.this.spine;
                        if (objArr != null && i4 <= this.lastSpineIndex) {
                            this.splChunk = objArr[i4];
                        }
                    }
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator.OfPrimitive trySplit() {
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i < i2) {
                    int i3 = this.splElementIndex;
                    OfPrimitive ofPrimitive = OfPrimitive.this;
                    Spliterator.OfPrimitive newSpliterator = newSpliterator(i, i2 - 1, i3, ofPrimitive.arrayLength(ofPrimitive.spine[i2 - 1]));
                    int i4 = this.lastSpineIndex;
                    this.splSpineIndex = i4;
                    this.splElementIndex = 0;
                    this.splChunk = OfPrimitive.this.spine[i4];
                    return newSpliterator;
                } else if (i == i2) {
                    int i5 = this.lastSpineElementFence;
                    int i6 = this.splElementIndex;
                    int i7 = (i5 - i6) / 2;
                    if (i7 == 0) {
                        return null;
                    }
                    Spliterator.OfPrimitive arraySpliterator = arraySpliterator(this.splChunk, i6, i7);
                    this.splElementIndex += i7;
                    return arraySpliterator;
                } else {
                    return null;
                }
            }
        }

        OfPrimitive() {
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        OfPrimitive(int i) {
            super(i);
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        private void inflateSpine() {
            if (this.spine == null) {
                Object[] newArrayArray = newArrayArray(8);
                this.spine = newArrayArray;
                this.priorElementCount = new long[8];
                newArrayArray[0] = this.curChunk;
            }
        }

        protected abstract void arrayForEach(Object obj, int i, int i2, Object obj2);

        protected abstract int arrayLength(Object obj);

        public Object asPrimitiveArray() {
            long count = count();
            if (count < 2147483639) {
                Object newArray = newArray((int) count);
                copyInto(newArray, 0);
                return newArray;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        protected long capacity() {
            int i = this.spineIndex;
            if (i == 0) {
                return arrayLength(this.curChunk);
            }
            return arrayLength(this.spine[i]) + this.priorElementCount[i];
        }

        protected int chunkFor(long j) {
            if (this.spineIndex == 0) {
                if (j < this.elementIndex) {
                    return 0;
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            } else if (j < count()) {
                for (int i = 0; i <= this.spineIndex; i++) {
                    if (j < this.priorElementCount[i] + arrayLength(this.spine[i])) {
                        return i;
                    }
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            } else {
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
        }

        @Override
        public void clear() {
            Object[] objArr = this.spine;
            if (objArr != null) {
                this.curChunk = objArr[0];
                this.spine = null;
                this.priorElementCount = null;
            }
            this.elementIndex = 0;
            this.spineIndex = 0;
        }

        public void copyInto(Object obj, int i) {
            long j = i;
            long count = count() + j;
            if (count > arrayLength(obj) || count < j) {
                throw new IndexOutOfBoundsException("does not fit");
            }
            if (this.spineIndex == 0) {
                System.arraycopy(this.curChunk, 0, obj, i, this.elementIndex);
                return;
            }
            for (int i2 = 0; i2 < this.spineIndex; i2++) {
                Object obj2 = this.spine[i2];
                System.arraycopy(obj2, 0, obj, i, arrayLength(obj2));
                i += arrayLength(this.spine[i2]);
            }
            int i3 = this.elementIndex;
            if (i3 > 0) {
                System.arraycopy(this.curChunk, 0, obj, i, i3);
            }
        }

        public final void ensureCapacity(long j) {
            int i;
            long capacity = capacity();
            if (j <= capacity) {
                return;
            }
            inflateSpine();
            int i2 = this.spineIndex;
            while (true) {
                i2++;
                if (j <= capacity) {
                    return;
                }
                Object[] objArr = this.spine;
                if (i2 >= objArr.length) {
                    int length = objArr.length * 2;
                    this.spine = Arrays.copyOf(objArr, length);
                    this.priorElementCount = Arrays.copyOf(this.priorElementCount, length);
                }
                int chunkSize = chunkSize(i2);
                this.spine[i2] = newArray(chunkSize);
                long[] jArr = this.priorElementCount;
                jArr[i2] = jArr[i2 - 1] + arrayLength(this.spine[i]);
                capacity += chunkSize;
            }
        }

        public void forEach(Object obj) {
            for (int i = 0; i < this.spineIndex; i++) {
                Object obj2 = this.spine[i];
                arrayForEach(obj2, 0, arrayLength(obj2), obj);
            }
            arrayForEach(this.curChunk, 0, this.elementIndex, obj);
        }

        protected void increaseCapacity() {
            ensureCapacity(capacity() + 1);
        }

        public abstract Object newArray(int i);

        protected abstract Object[] newArrayArray(int i);

        protected void preAccept() {
            if (this.elementIndex == arrayLength(this.curChunk)) {
                inflateSpine();
                int i = this.spineIndex;
                int i2 = i + 1;
                Object[] objArr = this.spine;
                if (i2 >= objArr.length || objArr[i + 1] == null) {
                    increaseCapacity();
                }
                this.elementIndex = 0;
                int i3 = this.spineIndex + 1;
                this.spineIndex = i3;
                this.curChunk = this.spine[i3];
            }
        }

        public abstract Spliterator spliterator();

        @Override
        public java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }
    }

    private void inflateSpine() {
        if (this.spine == null) {
            Object[][] objArr = new Object[8];
            this.spine = objArr;
            this.priorElementCount = new long[8];
            objArr[0] = this.curChunk;
        }
    }

    public void accept(Object obj) {
        if (this.elementIndex == this.curChunk.length) {
            inflateSpine();
            int i = this.spineIndex;
            int i2 = i + 1;
            Object[][] objArr = this.spine;
            if (i2 >= objArr.length || objArr[i + 1] == null) {
                increaseCapacity();
            }
            this.elementIndex = 0;
            int i3 = this.spineIndex + 1;
            this.spineIndex = i3;
            this.curChunk = this.spine[i3];
        }
        Object[] objArr2 = this.curChunk;
        int i4 = this.elementIndex;
        this.elementIndex = i4 + 1;
        objArr2[i4] = obj;
    }

    public Consumer andThen(Consumer consumer) {
        return Objects.requireNonNull(consumer);
    }

    public Object[] asArray(IntFunction intFunction) {
        long count = count();
        if (count < 2147483639) {
            Object[] objArr = (Object[]) intFunction.apply((int) count);
            copyInto(objArr, 0);
            return objArr;
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    protected long capacity() {
        int i = this.spineIndex;
        if (i == 0) {
            return this.curChunk.length;
        }
        return this.spine[i].length + this.priorElementCount[i];
    }

    @Override
    public void clear() {
        Object[][] objArr = this.spine;
        if (objArr != null) {
            this.curChunk = objArr[0];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.curChunk;
                if (i >= objArr2.length) {
                    break;
                }
                objArr2[i] = null;
                i++;
            }
            this.spine = null;
            this.priorElementCount = null;
        } else {
            for (int i2 = 0; i2 < this.elementIndex; i2++) {
                this.curChunk[i2] = null;
            }
        }
        this.elementIndex = 0;
        this.spineIndex = 0;
    }

    public void copyInto(Object[] objArr, int i) {
        long j = i;
        long count = count() + j;
        if (count > objArr.length || count < j) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.spineIndex == 0) {
            System.arraycopy(this.curChunk, 0, objArr, i, this.elementIndex);
            return;
        }
        for (int i2 = 0; i2 < this.spineIndex; i2++) {
            Object[] objArr2 = this.spine[i2];
            System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
            i += this.spine[i2].length;
        }
        int i3 = this.elementIndex;
        if (i3 > 0) {
            System.arraycopy(this.curChunk, 0, objArr, i, i3);
        }
    }

    public final void ensureCapacity(long j) {
        Object[][] objArr;
        int i;
        long capacity = capacity();
        if (j <= capacity) {
            return;
        }
        inflateSpine();
        int i2 = this.spineIndex;
        while (true) {
            i2++;
            if (j <= capacity) {
                return;
            }
            Object[][] objArr2 = this.spine;
            if (i2 >= objArr2.length) {
                int length = objArr2.length * 2;
                this.spine = (Object[][]) Arrays.copyOf(objArr2, length);
                this.priorElementCount = Arrays.copyOf(this.priorElementCount, length);
            }
            int chunkSize = chunkSize(i2);
            this.spine[i2] = new Object[chunkSize];
            long[] jArr = this.priorElementCount;
            jArr[i2] = jArr[i2 - 1] + objArr[i].length;
            capacity += chunkSize;
        }
    }

    public void forEach(Consumer consumer) {
        for (int i = 0; i < this.spineIndex; i++) {
            for (Object obj : this.spine[i]) {
                consumer.accept(obj);
            }
        }
        for (int i2 = 0; i2 < this.elementIndex; i2++) {
            consumer.accept(this.curChunk[i2]);
        }
    }

    public Object get(long j) {
        if (this.spineIndex == 0) {
            if (j < this.elementIndex) {
                return this.curChunk[(int) j];
            }
            throw new IndexOutOfBoundsException(Long.toString(j));
        } else if (j < count()) {
            for (int i = 0; i <= this.spineIndex; i++) {
                long j2 = this.priorElementCount[i];
                Object[] objArr = this.spine[i];
                if (j < objArr.length + j2) {
                    return objArr[(int) (j - j2)];
                }
            }
            throw new IndexOutOfBoundsException(Long.toString(j));
        } else {
            throw new IndexOutOfBoundsException(Long.toString(j));
        }
    }

    protected void increaseCapacity() {
        ensureCapacity(capacity() + 1);
    }

    @Override
    public Iterator iterator() {
        return Spliterators.iterator(spliterator());
    }

    public Spliterator spliterator() {
        return new 1Splitr(0, this.spineIndex, 0, this.elementIndex);
    }

    @Override
    public java.util.Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    public String toString() {
        final ArrayList arrayList = new ArrayList();
        Objects.requireNonNull(arrayList);
        forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                arrayList.add(obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        String obj = arrayList.toString();
        return "SpinedBuffer:" + obj;
    }
}
