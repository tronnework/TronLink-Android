package j$.util.stream;

import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
public interface Sink extends Consumer {

    public abstract class -CC {
        public static void $default$accept(Sink sink, double d) {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$accept(Sink sink, int i) {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$accept(Sink sink, long j) {
            throw new IllegalStateException("called wrong accept method");
        }

        public static void $default$begin(Sink sink, long j) {
        }

        public static boolean $default$cancellationRequested(Sink sink) {
            return false;
        }

        public static void $default$end(Sink sink) {
        }
    }

    public static abstract class ChainedDouble implements OfDouble {
        protected final Sink downstream;

        public ChainedDouble(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override
        public void accept(int i) {
            -CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            -CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Double d) {
            OfDouble.-CC.$default$accept((OfDouble) this, d);
        }

        @Override
        public void accept(Object obj) {
            OfDouble.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override
        public void end() {
            this.downstream.end();
        }
    }

    public static abstract class ChainedInt implements OfInt {
        protected final Sink downstream;

        public ChainedInt(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override
        public void accept(double d) {
            -CC.$default$accept(this, d);
        }

        @Override
        public void accept(long j) {
            -CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Integer num) {
            OfInt.-CC.$default$accept((OfInt) this, num);
        }

        @Override
        public void accept(Object obj) {
            OfInt.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override
        public void end() {
            this.downstream.end();
        }
    }

    public static abstract class ChainedLong implements OfLong {
        protected final Sink downstream;

        public ChainedLong(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override
        public void accept(double d) {
            -CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            -CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(Long l) {
            OfLong.-CC.$default$accept((OfLong) this, l);
        }

        @Override
        public void accept(Object obj) {
            OfLong.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override
        public void end() {
            this.downstream.end();
        }
    }

    public static abstract class ChainedReference implements Sink {
        protected final Sink downstream;

        public ChainedReference(Sink sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override
        public void accept(double d) {
            -CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            -CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            -CC.$default$accept((Sink) this, j);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override
        public void end() {
            this.downstream.end();
        }
    }

    public interface OfDouble extends Sink, DoubleConsumer {

        public abstract class -CC {
            public static void $default$accept(OfDouble ofDouble, Double d) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofDouble.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
                }
                ofDouble.accept(d.doubleValue());
            }

            public static void $default$accept(OfDouble ofDouble, Object obj) {
                ofDouble.accept((Double) obj);
            }
        }

        @Override
        void accept(double d);

        void accept(Double d);
    }

    public interface OfInt extends Sink, IntConsumer {

        public abstract class -CC {
            public static void $default$accept(OfInt ofInt, Integer num) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofInt.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
                }
                ofInt.accept(num.intValue());
            }

            public static void $default$accept(OfInt ofInt, Object obj) {
                ofInt.accept((Integer) obj);
            }
        }

        @Override
        void accept(int i);

        void accept(Integer num);
    }

    public interface OfLong extends Sink, LongConsumer {

        public abstract class -CC {
            public static void $default$accept(OfLong ofLong, Long l) {
                if (Tripwire.ENABLED) {
                    Tripwire.trip(ofLong.getClass(), "{0} calling Sink.OfLong.accept(Long)");
                }
                ofLong.accept(l.longValue());
            }

            public static void $default$accept(OfLong ofLong, Object obj) {
                ofLong.accept((Long) obj);
            }
        }

        @Override
        void accept(long j);

        void accept(Long l);
    }

    void accept(double d);

    void accept(int i);

    void accept(long j);

    void begin(long j);

    boolean cancellationRequested();

    void end();
}
