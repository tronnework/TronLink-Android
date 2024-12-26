package j$.util.stream;

import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
abstract class StreamSpliterators$ArrayBuffer {
    int index;

    static final class OfDouble extends OfPrimitive implements DoubleConsumer {
        final double[] array;

        public OfDouble(int i) {
            this.array = new double[i];
        }

        @Override
        public void accept(double d) {
            double[] dArr = this.array;
            int i = ((OfPrimitive) this).index;
            ((OfPrimitive) this).index = i + 1;
            dArr[i] = d;
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void forEach(DoubleConsumer doubleConsumer, long j) {
            for (int i = 0; i < j; i++) {
                doubleConsumer.accept(this.array[i]);
            }
        }
    }

    static final class OfInt extends OfPrimitive implements IntConsumer {
        final int[] array;

        public OfInt(int i) {
            this.array = new int[i];
        }

        @Override
        public void accept(int i) {
            int[] iArr = this.array;
            int i2 = ((OfPrimitive) this).index;
            ((OfPrimitive) this).index = i2 + 1;
            iArr[i2] = i;
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void forEach(IntConsumer intConsumer, long j) {
            for (int i = 0; i < j; i++) {
                intConsumer.accept(this.array[i]);
            }
        }
    }

    static final class OfLong extends OfPrimitive implements LongConsumer {
        final long[] array;

        public OfLong(int i) {
            this.array = new long[i];
        }

        @Override
        public void accept(long j) {
            long[] jArr = this.array;
            int i = ((OfPrimitive) this).index;
            ((OfPrimitive) this).index = i + 1;
            jArr[i] = j;
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void forEach(LongConsumer longConsumer, long j) {
            for (int i = 0; i < j; i++) {
                longConsumer.accept(this.array[i]);
            }
        }
    }

    static abstract class OfPrimitive extends StreamSpliterators$ArrayBuffer {
        int index;

        OfPrimitive() {
        }

        public abstract void forEach(Object obj, long j);

        @Override
        public void reset() {
            this.index = 0;
        }
    }

    static final class OfRef extends StreamSpliterators$ArrayBuffer implements Consumer {
        final Object[] array;

        public OfRef(int i) {
            this.array = new Object[i];
        }

        @Override
        public void accept(Object obj) {
            Object[] objArr = this.array;
            int i = this.index;
            this.index = i + 1;
            objArr[i] = obj;
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public void forEach(Consumer consumer, long j) {
            for (int i = 0; i < j; i++) {
                consumer.accept(this.array[i]);
            }
        }
    }

    StreamSpliterators$ArrayBuffer() {
    }

    public void reset() {
        this.index = 0;
    }
}
