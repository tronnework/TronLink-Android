package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.Supplier;
public class StreamSpliterators$DelegatingSpliterator implements Spliterator {
    private Spliterator s;
    private final Supplier supplier;

    static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
        public OfDouble(Supplier supplier) {
            super(supplier);
        }

        @Override
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            super.forEachRemaining((Object) doubleConsumer);
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
        public OfInt(Supplier supplier) {
            super(supplier);
        }

        @Override
        public void forEachRemaining(IntConsumer intConsumer) {
            super.forEachRemaining((Object) intConsumer);
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
        public OfLong(Supplier supplier) {
            super(supplier);
        }

        @Override
        public void forEachRemaining(LongConsumer longConsumer) {
            super.forEachRemaining((Object) longConsumer);
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

    static class OfPrimitive extends StreamSpliterators$DelegatingSpliterator implements Spliterator.OfPrimitive {
        OfPrimitive(Supplier supplier) {
            super(supplier);
        }

        @Override
        public void forEachRemaining(Object obj) {
            ((Spliterator.OfPrimitive) get()).forEachRemaining(obj);
        }

        @Override
        public boolean tryAdvance(Object obj) {
            return ((Spliterator.OfPrimitive) get()).tryAdvance(obj);
        }

        @Override
        public Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }
    }

    public StreamSpliterators$DelegatingSpliterator(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public int characteristics() {
        return get().characteristics();
    }

    @Override
    public long estimateSize() {
        return get().estimateSize();
    }

    @Override
    public void forEachRemaining(Consumer consumer) {
        get().forEachRemaining(consumer);
    }

    Spliterator get() {
        if (this.s == null) {
            this.s = (Spliterator) this.supplier.get();
        }
        return this.s;
    }

    @Override
    public Comparator getComparator() {
        return get().getComparator();
    }

    @Override
    public long getExactSizeIfKnown() {
        return get().getExactSizeIfKnown();
    }

    @Override
    public boolean hasCharacteristics(int i) {
        return Spliterator.-CC.$default$hasCharacteristics(this, i);
    }

    public String toString() {
        String name = getClass().getName();
        Spliterator spliterator = get();
        return name + "[" + spliterator + "]";
    }

    @Override
    public boolean tryAdvance(Consumer consumer) {
        return get().tryAdvance(consumer);
    }

    @Override
    public Spliterator trySplit() {
        return get().trySplit();
    }
}
