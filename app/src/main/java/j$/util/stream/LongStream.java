package j$.util.stream;

import j$.util.LongSummaryStatistics;
import j$.util.LongSummaryStatisticsConversions;
import j$.util.OptionalConversions;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.PrimitiveIterator;
import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
public interface LongStream extends BaseStream<Long, LongStream> {

    public final class VivifiedWrapper implements LongStream {
        public final java.util.stream.LongStream wrappedValue;

        private VivifiedWrapper(java.util.stream.LongStream longStream) {
            this.wrappedValue = longStream;
        }

        public static LongStream convert(java.util.stream.LongStream longStream) {
            if (longStream == null) {
                return null;
            }
            return longStream instanceof Wrapper ? LongStream.this : new VivifiedWrapper(longStream);
        }

        @Override
        public boolean allMatch(LongPredicate longPredicate) {
            return this.wrappedValue.allMatch(longPredicate);
        }

        @Override
        public boolean anyMatch(LongPredicate longPredicate) {
            return this.wrappedValue.anyMatch(longPredicate);
        }

        @Override
        public DoubleStream asDoubleStream() {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.asDoubleStream());
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
        public Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
            return this.wrappedValue.collect(supplier, objLongConsumer, biConsumer);
        }

        @Override
        public long count() {
            return this.wrappedValue.count();
        }

        @Override
        public LongStream distinct() {
            return convert(this.wrappedValue.distinct());
        }

        public boolean equals(Object obj) {
            java.util.stream.LongStream longStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return longStream.equals(obj);
        }

        @Override
        public LongStream filter(LongPredicate longPredicate) {
            return convert(this.wrappedValue.filter(longPredicate));
        }

        @Override
        public OptionalLong findAny() {
            return OptionalConversions.convert(this.wrappedValue.findAny());
        }

        @Override
        public OptionalLong findFirst() {
            return OptionalConversions.convert(this.wrappedValue.findFirst());
        }

        @Override
        public LongStream flatMap(LongFunction longFunction) {
            return convert(this.wrappedValue.flatMap(FlatMapApiFlips.flipFunctionReturningStream(longFunction)));
        }

        @Override
        public void forEach(LongConsumer longConsumer) {
            this.wrappedValue.forEach(longConsumer);
        }

        @Override
        public void forEachOrdered(LongConsumer longConsumer) {
            this.wrappedValue.forEachOrdered(longConsumer);
        }

        public int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override
        public boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        @Override
        public Iterator<Long> iterator() {
            return PrimitiveIterator.OfLong.VivifiedWrapper.convert(this.wrappedValue.iterator());
        }

        @Override
        public Iterator<Long> iterator() {
            return this.wrappedValue.iterator();
        }

        @Override
        public LongStream limit(long j) {
            return convert(this.wrappedValue.limit(j));
        }

        @Override
        public LongStream map(LongUnaryOperator longUnaryOperator) {
            return convert(this.wrappedValue.map(longUnaryOperator));
        }

        @Override
        public DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
            return DoubleStream.VivifiedWrapper.convert(this.wrappedValue.mapToDouble(longToDoubleFunction));
        }

        @Override
        public IntStream mapToInt(LongToIntFunction longToIntFunction) {
            return IntStream.VivifiedWrapper.convert(this.wrappedValue.mapToInt(longToIntFunction));
        }

        @Override
        public Stream mapToObj(LongFunction longFunction) {
            return Stream.VivifiedWrapper.convert(this.wrappedValue.mapToObj(longFunction));
        }

        @Override
        public OptionalLong max() {
            return OptionalConversions.convert(this.wrappedValue.max());
        }

        @Override
        public OptionalLong min() {
            return OptionalConversions.convert(this.wrappedValue.min());
        }

        @Override
        public boolean noneMatch(LongPredicate longPredicate) {
            return this.wrappedValue.noneMatch(longPredicate);
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
        public LongStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override
        public LongStream peek(LongConsumer longConsumer) {
            return convert(this.wrappedValue.peek(longConsumer));
        }

        @Override
        public long reduce(long j, LongBinaryOperator longBinaryOperator) {
            return this.wrappedValue.reduce(j, longBinaryOperator);
        }

        @Override
        public OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
            return OptionalConversions.convert(this.wrappedValue.reduce(longBinaryOperator));
        }

        @Override
        public BaseStream sequential() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.sequential());
        }

        @Override
        public LongStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override
        public LongStream skip(long j) {
            return convert(this.wrappedValue.skip(j));
        }

        @Override
        public LongStream sorted() {
            return convert(this.wrappedValue.sorted());
        }

        @Override
        public Spliterator.OfLong spliterator() {
            return Spliterator.OfLong.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override
        public Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override
        public long sum() {
            return this.wrappedValue.sum();
        }

        @Override
        public LongSummaryStatistics summaryStatistics() {
            return LongSummaryStatisticsConversions.convert(this.wrappedValue.summaryStatistics());
        }

        @Override
        public long[] toArray() {
            return this.wrappedValue.toArray();
        }

        @Override
        public BaseStream unordered() {
            return BaseStream.VivifiedWrapper.convert(this.wrappedValue.unordered());
        }
    }

    public final class Wrapper implements java.util.stream.LongStream {
        private Wrapper() {
            LongStream.this = r1;
        }

        public static java.util.stream.LongStream convert(LongStream longStream) {
            if (longStream == null) {
                return null;
            }
            return longStream instanceof VivifiedWrapper ? ((VivifiedWrapper) longStream).wrappedValue : new Wrapper();
        }

        @Override
        public boolean allMatch(LongPredicate longPredicate) {
            return allMatch(longPredicate);
        }

        @Override
        public boolean anyMatch(LongPredicate longPredicate) {
            return anyMatch(longPredicate);
        }

        @Override
        public java.util.stream.DoubleStream asDoubleStream() {
            return DoubleStream.Wrapper.convert(asDoubleStream());
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
        public Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
            return collect(supplier, objLongConsumer, biConsumer);
        }

        @Override
        public long count() {
            return count();
        }

        @Override
        public java.util.stream.LongStream distinct() {
            return convert(distinct());
        }

        public boolean equals(Object obj) {
            LongStream longStream = LongStream.this;
            if (obj instanceof Wrapper) {
                obj = LongStream.this;
            }
            return longStream.equals(obj);
        }

        @Override
        public java.util.stream.LongStream filter(LongPredicate longPredicate) {
            return convert(filter(longPredicate));
        }

        @Override
        public java.util.OptionalLong findAny() {
            return OptionalConversions.convert(findAny());
        }

        @Override
        public java.util.OptionalLong findFirst() {
            return OptionalConversions.convert(findFirst());
        }

        @Override
        public java.util.stream.LongStream flatMap(LongFunction longFunction) {
            return convert(flatMap(FlatMapApiFlips.flipFunctionReturningStream(longFunction)));
        }

        @Override
        public void forEach(LongConsumer longConsumer) {
            forEach(longConsumer);
        }

        @Override
        public void forEachOrdered(LongConsumer longConsumer) {
            forEachOrdered(longConsumer);
        }

        public int hashCode() {
            return hashCode();
        }

        @Override
        public boolean isParallel() {
            return isParallel();
        }

        @Override
        public Iterator<Long> iterator() {
            return iterator();
        }

        @Override
        public Iterator<Long> iterator() {
            return PrimitiveIterator.OfLong.Wrapper.convert(iterator());
        }

        @Override
        public java.util.stream.LongStream limit(long j) {
            return convert(limit(j));
        }

        @Override
        public java.util.stream.LongStream map(LongUnaryOperator longUnaryOperator) {
            return convert(map(longUnaryOperator));
        }

        @Override
        public java.util.stream.DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
            return DoubleStream.Wrapper.convert(mapToDouble(longToDoubleFunction));
        }

        @Override
        public java.util.stream.IntStream mapToInt(LongToIntFunction longToIntFunction) {
            return IntStream.Wrapper.convert(mapToInt(longToIntFunction));
        }

        @Override
        public java.util.stream.Stream mapToObj(LongFunction longFunction) {
            return Stream.Wrapper.convert(mapToObj(longFunction));
        }

        @Override
        public java.util.OptionalLong max() {
            return OptionalConversions.convert(max());
        }

        @Override
        public java.util.OptionalLong min() {
            return OptionalConversions.convert(min());
        }

        @Override
        public boolean noneMatch(LongPredicate longPredicate) {
            return noneMatch(longPredicate);
        }

        @Override
        public java.util.stream.LongStream onClose(Runnable runnable) {
            return BaseStream.Wrapper.convert(onClose(runnable));
        }

        @Override
        public java.util.stream.LongStream parallel() {
            return BaseStream.Wrapper.convert(parallel());
        }

        @Override
        public java.util.stream.LongStream parallel() {
            return convert(parallel());
        }

        @Override
        public java.util.stream.LongStream peek(LongConsumer longConsumer) {
            return convert(peek(longConsumer));
        }

        @Override
        public long reduce(long j, LongBinaryOperator longBinaryOperator) {
            return reduce(j, longBinaryOperator);
        }

        @Override
        public java.util.OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
            return OptionalConversions.convert(reduce(longBinaryOperator));
        }

        @Override
        public java.util.stream.LongStream sequential() {
            return BaseStream.Wrapper.convert(sequential());
        }

        @Override
        public java.util.stream.LongStream sequential() {
            return convert(sequential());
        }

        @Override
        public java.util.stream.LongStream skip(long j) {
            return convert(skip(j));
        }

        @Override
        public java.util.stream.LongStream sorted() {
            return convert(sorted());
        }

        @Override
        public java.util.Spliterator<Long> spliterator() {
            return Spliterator.OfLong.Wrapper.convert(spliterator());
        }

        @Override
        public java.util.Spliterator<Long> spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override
        public long sum() {
            return sum();
        }

        @Override
        public java.util.LongSummaryStatistics summaryStatistics() {
            return LongSummaryStatisticsConversions.convert(summaryStatistics());
        }

        @Override
        public long[] toArray() {
            return toArray();
        }

        @Override
        public java.util.stream.LongStream unordered() {
            return BaseStream.Wrapper.convert(unordered());
        }
    }

    boolean allMatch(LongPredicate longPredicate);

    boolean anyMatch(LongPredicate longPredicate);

    DoubleStream asDoubleStream();

    OptionalDouble average();

    Stream boxed();

    Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer);

    long count();

    LongStream distinct();

    LongStream filter(LongPredicate longPredicate);

    OptionalLong findAny();

    OptionalLong findFirst();

    LongStream flatMap(LongFunction longFunction);

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    @Override
    Iterator<Long> iterator();

    LongStream limit(long j);

    LongStream map(LongUnaryOperator longUnaryOperator);

    DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction);

    IntStream mapToInt(LongToIntFunction longToIntFunction);

    Stream mapToObj(LongFunction longFunction);

    OptionalLong max();

    OptionalLong min();

    boolean noneMatch(LongPredicate longPredicate);

    @Override
    LongStream parallel();

    LongStream peek(LongConsumer longConsumer);

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    OptionalLong reduce(LongBinaryOperator longBinaryOperator);

    @Override
    LongStream sequential();

    LongStream skip(long j);

    LongStream sorted();

    @Override
    Spliterator.OfLong spliterator();

    long sum();

    LongSummaryStatistics summaryStatistics();

    long[] toArray();
}
