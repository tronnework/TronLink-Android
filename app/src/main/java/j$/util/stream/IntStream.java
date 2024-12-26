package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.IntSummaryStatisticsConversions;
import j$.util.OptionalConversions;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
public interface IntStream extends BaseStream<Integer, IntStream> {

    public final class VivifiedWrapper implements IntStream {
        public final java.util.stream.IntStream wrappedValue;

        private VivifiedWrapper(java.util.stream.IntStream intStream) {
            this.wrappedValue = intStream;
        }

        public static IntStream convert(java.util.stream.IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof Wrapper ? IntStream.this : new VivifiedWrapper(intStream);
        }

        @Override
        public boolean allMatch(IntPredicate intPredicate) {
            return this.wrappedValue.allMatch(intPredicate);
        }

        @Override
        public boolean anyMatch(IntPredicate intPredicate) {
            return this.wrappedValue.anyMatch(intPredicate);
        }

        @Override
        public DoubleStream asDoubleStream() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.asDoubleStream());
        }

        @Override
        public LongStream asLongStream() {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.asLongStream());
        }

        @Override
        public OptionalDouble average() {
            return OptionalConversions.convert(this.wrappedValue.average());
        }

        @Override
        public Stream boxed() {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.boxed());
        }

        @Override
        public void close() {
            this.wrappedValue.close();
        }

        @Override
        public Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objIntConsumer, biConsumer);
        }

        @Override
        public long count() {
            return this.wrappedValue.count();
        }

        @Override
        public IntStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public boolean equals(Object obj) {
            java.util.stream.IntStream intStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return intStream.equals(obj);
        }

        @Override
        public IntStream filter(IntPredicate intPredicate) {
            return convert(this.wrappedValue.filter(intPredicate));
        }

        @Override
        public OptionalInt findAny() {
            return OptionalConversions.convert(this.wrappedValue.findAny());
        }

        @Override
        public OptionalInt findFirst() {
            return OptionalConversions.convert(this.wrappedValue.findFirst());
        }

        @Override
        public IntStream flatMap(IntFunction intFunction) {
            return convert(this.wrappedValue.flatMap(FlatMapApiFlips.flipFunctionReturningStream(intFunction)));
        }

        @Override
        public void forEach(IntConsumer intConsumer) {
            this.wrappedValue.forEach(intConsumer);
        }

        @Override
        public void forEachOrdered(IntConsumer intConsumer) {
            this.wrappedValue.forEachOrdered(intConsumer);
        }

        public int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override
        public boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        @Override
        public Iterator<Integer> iterator() {
            return PrimitiveIterator.OfInt.VivifiedWrapper.convert(this.wrappedValue.iterator());
        }

        @Override
        public Iterator<Integer> iterator() {
            return this.wrappedValue.iterator();
        }

        @Override
        public IntStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        @Override
        public IntStream map(IntUnaryOperator intUnaryOperator) {
            return convert(this.wrappedValue.map(intUnaryOperator));
        }

        @Override
        public DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(intToDoubleFunction));
        }

        @Override
        public LongStream mapToLong(IntToLongFunction intToLongFunction) {
            return LongStream.VivifiedWrapper.convert(this.wrappedValue.mapToLong(intToLongFunction));
        }

        @Override
        public Stream mapToObj(IntFunction intFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(intFunction));
        }

        @Override
        public OptionalInt max() {
            return OptionalConversions.convert(this.wrappedValue.max());
        }

        @Override
        public OptionalInt min() {
            return OptionalConversions.convert(this.wrappedValue.min());
        }

        @Override
        public boolean noneMatch(IntPredicate intPredicate) {
            return this.wrappedValue.noneMatch(intPredicate);
        }

        @Override
        public BaseStream onClose(Runnable runnable) {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.onClose(runnable));
        }

        @Override
        public BaseStream parallel() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.parallel());
        }

        @Override
        public IntStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override
        public IntStream peek(IntConsumer intConsumer) {
            return convert(this.wrappedValue.peek(intConsumer));
        }

        @Override
        public int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return this.wrappedValue.reduce(i, intBinaryOperator);
        }

        @Override
        public OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return OptionalConversions.convert(this.wrappedValue.reduce(intBinaryOperator));
        }

        @Override
        public BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        @Override
        public IntStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override
        public IntStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        @Override
        public IntStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        @Override
        public Spliterator.OfInt spliterator() {
            return Spliterator.OfInt.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override
        public Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override
        public int sum() {
            return this.wrappedValue.sum();
        }

        @Override
        public IntSummaryStatistics summaryStatistics() {
            return IntSummaryStatisticsConversions.convert(this.wrappedValue.summaryStatistics());
        }

        @Override
        public int[] toArray() {
            return this.wrappedValue.toArray();
        }

        @Override
        public BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final class Wrapper implements java.util.stream.IntStream {
        private Wrapper() {
            IntStream.this = r1;
        }

        public static java.util.stream.IntStream convert(IntStream intStream) {
            if (intStream == null) {
                return null;
            }
            return intStream instanceof VivifiedWrapper ? ((VivifiedWrapper) intStream).wrappedValue : new Wrapper();
        }

        @Override
        public boolean allMatch(IntPredicate intPredicate) {
            return allMatch(intPredicate);
        }

        @Override
        public boolean anyMatch(IntPredicate intPredicate) {
            return anyMatch(intPredicate);
        }

        @Override
        public java.util.stream.DoubleStream asDoubleStream() {
            return DoubleStream.Wrapper.convert(asDoubleStream());
        }

        @Override
        public java.util.stream.LongStream asLongStream() {
            return LongStream.Wrapper.convert(asLongStream());
        }

        @Override
        public java.util.OptionalDouble average() {
            return OptionalConversions.convert(average());
        }

        @Override
        public java.util.stream.Stream boxed() {
            return Stream.Wrapper.convert(boxed());
        }

        @Override
        public void close() {
            close();
        }

        @Override
        public Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
            return collect(supplier, objIntConsumer, biConsumer);
        }

        @Override
        public long count() {
            return count();
        }

        @Override
        public java.util.stream.IntStream distinct() {
            return convert(distinct());
        }

        public boolean equals(Object obj) {
            IntStream intStream = IntStream.this;
            if (obj instanceof Wrapper) {
                obj = IntStream.this;
            }
            return intStream.equals(obj);
        }

        @Override
        public java.util.stream.IntStream filter(IntPredicate intPredicate) {
            return convert(filter(intPredicate));
        }

        @Override
        public java.util.OptionalInt findAny() {
            return OptionalConversions.convert(findAny());
        }

        @Override
        public java.util.OptionalInt findFirst() {
            return OptionalConversions.convert(findFirst());
        }

        @Override
        public java.util.stream.IntStream flatMap(IntFunction intFunction) {
            return convert(flatMap(FlatMapApiFlips.flipFunctionReturningStream(intFunction)));
        }

        @Override
        public void forEach(IntConsumer intConsumer) {
            forEach(intConsumer);
        }

        @Override
        public void forEachOrdered(IntConsumer intConsumer) {
            forEachOrdered(intConsumer);
        }

        public int hashCode() {
            return hashCode();
        }

        @Override
        public boolean isParallel() {
            return isParallel();
        }

        @Override
        public Iterator<Integer> iterator() {
            return iterator();
        }

        @Override
        public Iterator<Integer> iterator() {
            return PrimitiveIterator.OfInt.Wrapper.convert(iterator());
        }

        @Override
        public java.util.stream.IntStream limit(long j) {
            return convert(limit(j));
        }

        @Override
        public java.util.stream.IntStream map(IntUnaryOperator intUnaryOperator) {
            return convert(map(intUnaryOperator));
        }

        @Override
        public java.util.stream.DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
            return DoubleStream.Wrapper.convert(mapToDouble(intToDoubleFunction));
        }

        @Override
        public java.util.stream.LongStream mapToLong(IntToLongFunction intToLongFunction) {
            return LongStream.Wrapper.convert(mapToLong(intToLongFunction));
        }

        @Override
        public java.util.stream.Stream mapToObj(IntFunction intFunction) {
            return Stream.Wrapper.convert(mapToObj(intFunction));
        }

        @Override
        public java.util.OptionalInt max() {
            return OptionalConversions.convert(max());
        }

        @Override
        public java.util.OptionalInt min() {
            return OptionalConversions.convert(min());
        }

        @Override
        public boolean noneMatch(IntPredicate intPredicate) {
            return noneMatch(intPredicate);
        }

        @Override
        public java.util.stream.IntStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(onClose(runnable));
        }

        @Override
        public java.util.stream.IntStream parallel() {
            return BaseStream.Wrapper.convert(parallel());
        }

        @Override
        public java.util.stream.IntStream parallel() {
            return convert(parallel());
        }

        @Override
        public java.util.stream.IntStream peek(IntConsumer intConsumer) {
            return convert(peek(intConsumer));
        }

        @Override
        public int reduce(int i, IntBinaryOperator intBinaryOperator) {
            return reduce(i, intBinaryOperator);
        }

        @Override
        public java.util.OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
            return OptionalConversions.convert(reduce(intBinaryOperator));
        }

        @Override
        public java.util.stream.IntStream sequential() {
            return BaseStream.Wrapper.convert(sequential());
        }

        @Override
        public java.util.stream.IntStream sequential() {
            return convert(sequential());
        }

        @Override
        public java.util.stream.IntStream skip(long j) {
            return convert(skip(j));
        }

        @Override
        public java.util.stream.IntStream sorted() {
            return convert(sorted());
        }

        @Override
        public java.util.Spliterator<Integer> spliterator() {
            return Spliterator.OfInt.Wrapper.convert(spliterator());
        }

        @Override
        public java.util.Spliterator<Integer> spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override
        public int sum() {
            return sum();
        }

        @Override
        public java.util.IntSummaryStatistics summaryStatistics() {
            return IntSummaryStatisticsConversions.convert(summaryStatistics());
        }

        @Override
        public int[] toArray() {
            return toArray();
        }

        @Override
        public java.util.stream.IntStream unordered() {
            return BaseStream.Wrapper.convert(unordered());
        }
    }

    boolean allMatch(IntPredicate intPredicate);

    boolean anyMatch(IntPredicate intPredicate);

    DoubleStream asDoubleStream();

    LongStream asLongStream();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer);

    long count();

    IntStream distinct();

    IntStream filter(IntPredicate intPredicate);

    OptionalInt findAny();

    OptionalInt findFirst();

    IntStream flatMap(IntFunction intFunction);

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    @Override
    Iterator<Integer> iterator();

    IntStream limit(long j);

    IntStream map(IntUnaryOperator intUnaryOperator);

    DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction);

    LongStream mapToLong(IntToLongFunction intToLongFunction);

    Stream mapToObj(IntFunction intFunction);

    OptionalInt max();

    OptionalInt min();

    boolean noneMatch(IntPredicate intPredicate);

    @Override
    IntStream parallel();

    IntStream peek(IntConsumer intConsumer);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    OptionalInt reduce(IntBinaryOperator intBinaryOperator);

    @Override
    IntStream sequential();

    IntStream skip(long j);

    IntStream sorted();

    @Override
    Spliterator.OfInt spliterator();

    int sum();

    IntSummaryStatistics summaryStatistics();

    int[] toArray();
}
