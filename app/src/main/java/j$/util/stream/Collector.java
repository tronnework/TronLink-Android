package j$.util.stream;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
public interface Collector<T, A, R> {

    public enum Characteristics {
        CONCURRENT,
        UNORDERED,
        IDENTITY_FINISH;

        public abstract class EnumConversion {
            public static Characteristics convert(Collector.Characteristics characteristics) {
                if (characteristics == null) {
                    return null;
                }
                return characteristics == Collector.Characteristics.CONCURRENT ? Characteristics.CONCURRENT : characteristics == Collector.Characteristics.UNORDERED ? Characteristics.UNORDERED : Characteristics.IDENTITY_FINISH;
            }

            public static Collector.Characteristics convert(Characteristics characteristics) {
                if (characteristics == null) {
                    return null;
                }
                return characteristics == Characteristics.CONCURRENT ? Collector.Characteristics.CONCURRENT : characteristics == Characteristics.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH;
            }
        }
    }

    public final class VivifiedWrapper implements Collector {
        public final java.util.stream.Collector wrappedValue;

        private VivifiedWrapper(java.util.stream.Collector collector) {
            this.wrappedValue = collector;
        }

        public static Collector convert(java.util.stream.Collector collector) {
            if (collector == null) {
                return null;
            }
            return collector instanceof Wrapper ? Collector.this : new VivifiedWrapper(collector);
        }

        @Override
        public BiConsumer accumulator() {
            return this.wrappedValue.accumulator();
        }

        @Override
        public Set characteristics() {
            return StreamApiFlips.flipCharacteristicSet(this.wrappedValue.characteristics());
        }

        @Override
        public BinaryOperator combiner() {
            return this.wrappedValue.combiner();
        }

        public boolean equals(Object obj) {
            java.util.stream.Collector collector = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return collector.equals(obj);
        }

        @Override
        public Function finisher() {
            return this.wrappedValue.finisher();
        }

        public int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override
        public Supplier supplier() {
            return this.wrappedValue.supplier();
        }
    }

    public final class Wrapper implements java.util.stream.Collector {
        private Wrapper() {
        }

        public static java.util.stream.Collector convert(Collector collector) {
            if (collector == null) {
                return null;
            }
            return collector instanceof VivifiedWrapper ? ((VivifiedWrapper) collector).wrappedValue : new Wrapper();
        }

        @Override
        public BiConsumer accumulator() {
            return accumulator();
        }

        @Override
        public Set characteristics() {
            return StreamApiFlips.flipCharacteristicSet(characteristics());
        }

        @Override
        public BinaryOperator combiner() {
            return combiner();
        }

        public boolean equals(Object obj) {
            Collector collector = Collector.this;
            if (obj instanceof Wrapper) {
                obj = Collector.this;
            }
            return collector.equals(obj);
        }

        @Override
        public Function finisher() {
            return finisher();
        }

        public int hashCode() {
            return hashCode();
        }

        @Override
        public Supplier supplier() {
            return supplier();
        }
    }

    BiConsumer accumulator();

    Set characteristics();

    BinaryOperator combiner();

    Function finisher();

    Supplier supplier();
}
