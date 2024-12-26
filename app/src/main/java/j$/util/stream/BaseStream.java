package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.BaseStream;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.Iterator;
public interface BaseStream<T, S extends BaseStream<T, S>> extends AutoCloseable {

    public final class VivifiedWrapper implements BaseStream {
        public final java.util.stream.BaseStream wrappedValue;

        private VivifiedWrapper(java.util.stream.BaseStream baseStream) {
            this.wrappedValue = baseStream;
        }

        public static BaseStream convert(java.util.stream.BaseStream baseStream) {
            if (baseStream == null) {
                return null;
            }
            return baseStream instanceof Wrapper ? BaseStream.this : baseStream instanceof java.util.stream.DoubleStream ? DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) baseStream) : baseStream instanceof java.util.stream.IntStream ? IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) baseStream) : baseStream instanceof java.util.stream.LongStream ? LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) baseStream) : baseStream instanceof java.util.stream.Stream ? Stream.VivifiedWrapper.convert((java.util.stream.Stream) baseStream) : new VivifiedWrapper(baseStream);
        }

        @Override
        public void close() {
            this.wrappedValue.close();
        }

        public boolean equals(Object obj) {
            java.util.stream.BaseStream baseStream = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return baseStream.equals(obj);
        }

        public int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override
        public boolean isParallel() {
            return this.wrappedValue.isParallel();
        }

        @Override
        public Iterator iterator() {
            return this.wrappedValue.iterator();
        }

        @Override
        public BaseStream onClose(Runnable runnable) {
            return convert(this.wrappedValue.onClose(runnable));
        }

        @Override
        public BaseStream parallel() {
            return convert(this.wrappedValue.parallel());
        }

        @Override
        public BaseStream sequential() {
            return convert(this.wrappedValue.sequential());
        }

        @Override
        public Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override
        public BaseStream unordered() {
            return convert(this.wrappedValue.unordered());
        }
    }

    public final class Wrapper implements java.util.stream.BaseStream {
        private Wrapper() {
            BaseStream.this = r1;
        }

        public static java.util.stream.BaseStream convert(BaseStream baseStream) {
            if (baseStream == null) {
                return null;
            }
            return baseStream instanceof VivifiedWrapper ? ((VivifiedWrapper) baseStream).wrappedValue : baseStream instanceof DoubleStream ? DoubleStream.Wrapper.convert((DoubleStream) baseStream) : baseStream instanceof IntStream ? IntStream.Wrapper.convert((IntStream) baseStream) : baseStream instanceof LongStream ? LongStream.Wrapper.convert((LongStream) baseStream) : baseStream instanceof Stream ? Stream.Wrapper.convert((Stream) baseStream) : new Wrapper();
        }

        @Override
        public void close() {
            close();
        }

        public boolean equals(Object obj) {
            BaseStream baseStream = BaseStream.this;
            if (obj instanceof Wrapper) {
                obj = BaseStream.this;
            }
            return baseStream.equals(obj);
        }

        public int hashCode() {
            return hashCode();
        }

        @Override
        public boolean isParallel() {
            return isParallel();
        }

        @Override
        public Iterator iterator() {
            return iterator();
        }

        @Override
        public java.util.stream.BaseStream onClose(Runnable runnable) {
            return convert(onClose(runnable));
        }

        @Override
        public java.util.stream.BaseStream parallel() {
            return convert(parallel());
        }

        @Override
        public java.util.stream.BaseStream sequential() {
            return convert(sequential());
        }

        @Override
        public java.util.Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override
        public java.util.stream.BaseStream unordered() {
            return convert(unordered());
        }
    }

    @Override
    void close();

    boolean isParallel();

    Iterator<T> iterator();

    BaseStream onClose(Runnable runnable);

    BaseStream parallel();

    BaseStream sequential();

    Spliterator spliterator();

    BaseStream unordered();
}
