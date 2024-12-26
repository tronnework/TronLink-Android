package j$.util;

import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
public interface PrimitiveIterator extends java.util.Iterator {

    public interface OfDouble extends PrimitiveIterator {

        public abstract class -CC {
            public static void $default$forEachRemaining(OfDouble ofDouble, Object obj) {
                ofDouble.forEachRemaining((DoubleConsumer) obj);
            }

            public static void $default$forEachRemaining(OfDouble ofDouble, Consumer consumer) {
                if (consumer instanceof DoubleConsumer) {
                    ofDouble.forEachRemaining((DoubleConsumer) consumer);
                    return;
                }
                Objects.requireNonNull(consumer);
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling PrimitiveIterator.OfDouble.forEachRemainingDouble(action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofDouble.forEachRemaining((DoubleConsumer) new PrimitiveIterator$OfDouble$ExternalSyntheticLambda0(consumer));
            }

            public static void $default$forEachRemaining(OfDouble ofDouble, DoubleConsumer doubleConsumer) {
                Objects.requireNonNull(doubleConsumer);
                while (ofDouble.hasNext()) {
                    doubleConsumer.accept(ofDouble.nextDouble());
                }
            }

            public static Double $default$next(OfDouble ofDouble) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling PrimitiveIterator.OfDouble.nextLong()");
                }
                return Double.valueOf(ofDouble.nextDouble());
            }

            public static Object $default$next(OfDouble ofDouble) {
                return ofDouble.next();
            }
        }

        public final class VivifiedWrapper implements OfDouble, Iterator {
            public final PrimitiveIterator.OfDouble wrappedValue;

            private VivifiedWrapper(PrimitiveIterator.OfDouble ofDouble) {
                this.wrappedValue = ofDouble;
            }

            public static OfDouble convert(PrimitiveIterator.OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof Wrapper ? OfDouble.this : new VivifiedWrapper(ofDouble);
            }

            public boolean equals(Object obj) {
                PrimitiveIterator.OfDouble ofDouble = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofDouble.equals(obj);
            }

            @Override
            public void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((PrimitiveIterator.OfDouble) obj);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Double>) consumer);
            }

            @Override
            public void forEachRemaining(DoubleConsumer doubleConsumer) {
                this.wrappedValue.forEachRemaining(doubleConsumer);
            }

            @Override
            public boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override
            public Double next() {
                return this.wrappedValue.next();
            }

            @Override
            public Object next() {
                return this.wrappedValue.next();
            }

            @Override
            public double nextDouble() {
                return this.wrappedValue.nextDouble();
            }

            @Override
            public void remove() {
                this.wrappedValue.remove();
            }
        }

        public final class Wrapper implements PrimitiveIterator.OfDouble {
            private Wrapper() {
                OfDouble.this = r1;
            }

            public static PrimitiveIterator.OfDouble convert(OfDouble ofDouble) {
                if (ofDouble == null) {
                    return null;
                }
                return ofDouble instanceof VivifiedWrapper ? ((VivifiedWrapper) ofDouble).wrappedValue : new Wrapper();
            }

            public boolean equals(Object obj) {
                OfDouble ofDouble = OfDouble.this;
                if (obj instanceof Wrapper) {
                    obj = OfDouble.this;
                }
                return ofDouble.equals(obj);
            }

            @Override
            public void forEachRemaining(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining((Object) doubleConsumer);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                OfDouble.this.forEachRemaining(consumer);
            }

            @Override
            public void forEachRemaining(DoubleConsumer doubleConsumer) {
                OfDouble.this.forEachRemaining(doubleConsumer);
            }

            @Override
            public boolean hasNext() {
                return OfDouble.this.hasNext();
            }

            public int hashCode() {
                return OfDouble.this.hashCode();
            }

            @Override
            public Double next() {
                return OfDouble.this.next();
            }

            @Override
            public Object next() {
                return OfDouble.this.next();
            }

            @Override
            public double nextDouble() {
                return OfDouble.this.nextDouble();
            }

            @Override
            public void remove() {
                OfDouble.this.remove();
            }
        }

        @Override
        void forEachRemaining(Consumer consumer);

        void forEachRemaining(DoubleConsumer doubleConsumer);

        @Override
        Double next();

        double nextDouble();
    }

    public interface OfInt extends PrimitiveIterator {

        public abstract class -CC {
            public static void $default$forEachRemaining(OfInt ofInt, Object obj) {
                ofInt.forEachRemaining((IntConsumer) obj);
            }

            public static void $default$forEachRemaining(OfInt ofInt, Consumer consumer) {
                if (consumer instanceof IntConsumer) {
                    ofInt.forEachRemaining((IntConsumer) consumer);
                    return;
                }
                Objects.requireNonNull(consumer);
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling PrimitiveIterator.OfInt.forEachRemainingInt(action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofInt.forEachRemaining((IntConsumer) new PrimitiveIterator$OfInt$ExternalSyntheticLambda0(consumer));
            }

            public static void $default$forEachRemaining(OfInt ofInt, IntConsumer intConsumer) {
                Objects.requireNonNull(intConsumer);
                while (ofInt.hasNext()) {
                    intConsumer.accept(ofInt.nextInt());
                }
            }

            public static Integer $default$next(OfInt ofInt) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling PrimitiveIterator.OfInt.nextInt()");
                }
                return Integer.valueOf(ofInt.nextInt());
            }

            public static Object $default$next(OfInt ofInt) {
                return ofInt.next();
            }
        }

        public final class VivifiedWrapper implements OfInt, Iterator {
            public final PrimitiveIterator.OfInt wrappedValue;

            private VivifiedWrapper(PrimitiveIterator.OfInt ofInt) {
                this.wrappedValue = ofInt;
            }

            public static OfInt convert(PrimitiveIterator.OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof Wrapper ? OfInt.this : new VivifiedWrapper(ofInt);
            }

            public boolean equals(Object obj) {
                PrimitiveIterator.OfInt ofInt = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofInt.equals(obj);
            }

            @Override
            public void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((PrimitiveIterator.OfInt) obj);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Integer>) consumer);
            }

            @Override
            public void forEachRemaining(IntConsumer intConsumer) {
                this.wrappedValue.forEachRemaining(intConsumer);
            }

            @Override
            public boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override
            public Integer next() {
                return this.wrappedValue.next();
            }

            @Override
            public Object next() {
                return this.wrappedValue.next();
            }

            @Override
            public int nextInt() {
                return this.wrappedValue.nextInt();
            }

            @Override
            public void remove() {
                this.wrappedValue.remove();
            }
        }

        public final class Wrapper implements PrimitiveIterator.OfInt {
            private Wrapper() {
                OfInt.this = r1;
            }

            public static PrimitiveIterator.OfInt convert(OfInt ofInt) {
                if (ofInt == null) {
                    return null;
                }
                return ofInt instanceof VivifiedWrapper ? ((VivifiedWrapper) ofInt).wrappedValue : new Wrapper();
            }

            public boolean equals(Object obj) {
                OfInt ofInt = OfInt.this;
                if (obj instanceof Wrapper) {
                    obj = OfInt.this;
                }
                return ofInt.equals(obj);
            }

            @Override
            public void forEachRemaining(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining((Object) intConsumer);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                OfInt.this.forEachRemaining(consumer);
            }

            @Override
            public void forEachRemaining(IntConsumer intConsumer) {
                OfInt.this.forEachRemaining(intConsumer);
            }

            @Override
            public boolean hasNext() {
                return OfInt.this.hasNext();
            }

            public int hashCode() {
                return OfInt.this.hashCode();
            }

            @Override
            public Integer next() {
                return OfInt.this.next();
            }

            @Override
            public Object next() {
                return OfInt.this.next();
            }

            @Override
            public int nextInt() {
                return OfInt.this.nextInt();
            }

            @Override
            public void remove() {
                OfInt.this.remove();
            }
        }

        @Override
        void forEachRemaining(Consumer consumer);

        void forEachRemaining(IntConsumer intConsumer);

        @Override
        Integer next();

        int nextInt();
    }

    public interface OfLong extends PrimitiveIterator {

        public abstract class -CC {
            public static void $default$forEachRemaining(OfLong ofLong, Object obj) {
                ofLong.forEachRemaining((LongConsumer) obj);
            }

            public static void $default$forEachRemaining(OfLong ofLong, Consumer consumer) {
                if (consumer instanceof LongConsumer) {
                    ofLong.forEachRemaining((LongConsumer) consumer);
                    return;
                }
                Objects.requireNonNull(consumer);
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling PrimitiveIterator.OfLong.forEachRemainingLong(action::accept)");
                }
                Objects.requireNonNull(consumer);
                ofLong.forEachRemaining((LongConsumer) new PrimitiveIterator$OfLong$ExternalSyntheticLambda0(consumer));
            }

            public static void $default$forEachRemaining(OfLong ofLong, LongConsumer longConsumer) {
                Objects.requireNonNull(longConsumer);
                while (ofLong.hasNext()) {
                    longConsumer.accept(ofLong.nextLong());
                }
            }

            public static Long $default$next(OfLong ofLong) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling PrimitiveIterator.OfLong.nextLong()");
                }
                return Long.valueOf(ofLong.nextLong());
            }

            public static Object $default$next(OfLong ofLong) {
                return ofLong.next();
            }
        }

        public final class VivifiedWrapper implements OfLong, Iterator {
            public final PrimitiveIterator.OfLong wrappedValue;

            private VivifiedWrapper(PrimitiveIterator.OfLong ofLong) {
                this.wrappedValue = ofLong;
            }

            public static OfLong convert(PrimitiveIterator.OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof Wrapper ? OfLong.this : new VivifiedWrapper(ofLong);
            }

            public boolean equals(Object obj) {
                PrimitiveIterator.OfLong ofLong = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return ofLong.equals(obj);
            }

            @Override
            public void forEachRemaining(Object obj) {
                this.wrappedValue.forEachRemaining((PrimitiveIterator.OfLong) obj);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                this.wrappedValue.forEachRemaining((Consumer<? super Long>) consumer);
            }

            @Override
            public void forEachRemaining(LongConsumer longConsumer) {
                this.wrappedValue.forEachRemaining(longConsumer);
            }

            @Override
            public boolean hasNext() {
                return this.wrappedValue.hasNext();
            }

            public int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override
            public Long next() {
                return this.wrappedValue.next();
            }

            @Override
            public Object next() {
                return this.wrappedValue.next();
            }

            @Override
            public long nextLong() {
                return this.wrappedValue.nextLong();
            }

            @Override
            public void remove() {
                this.wrappedValue.remove();
            }
        }

        public final class Wrapper implements PrimitiveIterator.OfLong {
            private Wrapper() {
                OfLong.this = r1;
            }

            public static PrimitiveIterator.OfLong convert(OfLong ofLong) {
                if (ofLong == null) {
                    return null;
                }
                return ofLong instanceof VivifiedWrapper ? ((VivifiedWrapper) ofLong).wrappedValue : new Wrapper();
            }

            public boolean equals(Object obj) {
                OfLong ofLong = OfLong.this;
                if (obj instanceof Wrapper) {
                    obj = OfLong.this;
                }
                return ofLong.equals(obj);
            }

            @Override
            public void forEachRemaining(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining((Object) longConsumer);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                OfLong.this.forEachRemaining(consumer);
            }

            @Override
            public void forEachRemaining(LongConsumer longConsumer) {
                OfLong.this.forEachRemaining(longConsumer);
            }

            @Override
            public boolean hasNext() {
                return OfLong.this.hasNext();
            }

            public int hashCode() {
                return OfLong.this.hashCode();
            }

            @Override
            public Long next() {
                return OfLong.this.next();
            }

            @Override
            public Object next() {
                return OfLong.this.next();
            }

            @Override
            public long nextLong() {
                return OfLong.this.nextLong();
            }

            @Override
            public void remove() {
                OfLong.this.remove();
            }
        }

        @Override
        void forEachRemaining(Consumer consumer);

        void forEachRemaining(LongConsumer longConsumer);

        @Override
        Long next();

        long nextLong();
    }

    void forEachRemaining(Object obj);
}
