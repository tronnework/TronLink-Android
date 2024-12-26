package j$.util.stream;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import j$.util.Objects;
import j$.util.Optional;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.OptionalLong;
import j$.util.Spliterator;
import j$.util.stream.Collector;
import j$.util.stream.Sink;
import j$.util.stream.TerminalOp;
import java.util.concurrent.CountedCompleter;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
abstract class ReduceOps {

    public class 10ReducingSink extends Box implements AccumulatingSink, Sink.OfLong {
        final ObjLongConsumer val$accumulator;
        final BinaryOperator val$combiner;
        final Supplier val$supplier;

        10ReducingSink(Supplier supplier, ObjLongConsumer objLongConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = objLongConsumer;
            this.val$combiner = binaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            this.val$accumulator.accept(this.state, j);
        }

        @Override
        public void accept(Long l) {
            Sink.OfLong.-CC.$default$accept((Sink.OfLong) this, l);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfLong.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(10ReducingSink r3) {
            this.state = this.val$combiner.apply(this.state, r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }
    }

    public class 11ReducingSink implements AccumulatingSink, Sink.OfDouble {
        private double state;
        final double val$identity;
        final DoubleBinaryOperator val$operator;

        11ReducingSink(double d, DoubleBinaryOperator doubleBinaryOperator) {
            this.val$identity = d;
            this.val$operator = doubleBinaryOperator;
        }

        @Override
        public void accept(double d) {
            this.state = this.val$operator.applyAsDouble(this.state, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Double d) {
            Sink.OfDouble.-CC.$default$accept((Sink.OfDouble) this, d);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfDouble.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$identity;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(11ReducingSink r3) {
            accept(r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public Double get() {
            return Double.valueOf(this.state);
        }
    }

    public class 12ReducingSink implements AccumulatingSink, Sink.OfDouble {
        private boolean empty;
        private double state;
        final DoubleBinaryOperator val$operator;

        12ReducingSink(DoubleBinaryOperator doubleBinaryOperator) {
            this.val$operator = doubleBinaryOperator;
        }

        @Override
        public void accept(double d) {
            if (this.empty) {
                this.empty = false;
            } else {
                d = this.val$operator.applyAsDouble(this.state, d);
            }
            this.state = d;
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Double d) {
            Sink.OfDouble.-CC.$default$accept((Sink.OfDouble) this, d);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfDouble.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void begin(long j) {
            this.empty = true;
            this.state = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(12ReducingSink r3) {
            if (r3.empty) {
                return;
            }
            accept(r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public OptionalDouble get() {
            return this.empty ? OptionalDouble.empty() : OptionalDouble.of(this.state);
        }
    }

    public class 13ReducingSink extends Box implements AccumulatingSink, Sink.OfDouble {
        final ObjDoubleConsumer val$accumulator;
        final BinaryOperator val$combiner;
        final Supplier val$supplier;

        13ReducingSink(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = objDoubleConsumer;
            this.val$combiner = binaryOperator;
        }

        @Override
        public void accept(double d) {
            this.val$accumulator.accept(this.state, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Double d) {
            Sink.OfDouble.-CC.$default$accept((Sink.OfDouble) this, d);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfDouble.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
            return Objects.requireNonNull(doubleConsumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(13ReducingSink r3) {
            this.state = this.val$combiner.apply(this.state, r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }
    }

    public class 1ReducingSink extends Box implements AccumulatingSink {
        final BinaryOperator val$combiner;
        final BiFunction val$reducer;
        final Object val$seed;

        1ReducingSink(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            this.val$seed = obj;
            this.val$reducer = biFunction;
            this.val$combiner = binaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Object obj) {
            this.state = this.val$reducer.apply(this.state, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$seed;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(1ReducingSink r3) {
            this.state = this.val$combiner.apply(this.state, r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }
    }

    public class 3ReducingSink extends Box implements AccumulatingSink {
        final BiConsumer val$accumulator;
        final BinaryOperator val$combiner;
        final Supplier val$supplier;

        3ReducingSink(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = biConsumer;
            this.val$combiner = binaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Object obj) {
            this.val$accumulator.accept(this.state, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(3ReducingSink r3) {
            this.state = this.val$combiner.apply(this.state, r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }
    }

    public class 4ReducingSink extends Box implements AccumulatingSink {
        final BiConsumer val$accumulator;
        final BiConsumer val$reducer;
        final Supplier val$seedFactory;

        4ReducingSink(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            this.val$seedFactory = supplier;
            this.val$accumulator = biConsumer;
            this.val$reducer = biConsumer2;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Object obj) {
            this.val$accumulator.accept(this.state, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$seedFactory.get();
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(4ReducingSink r3) {
            this.val$reducer.accept(this.state, r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }
    }

    public class 5ReducingSink implements AccumulatingSink, Sink.OfInt {
        private int state;
        final int val$identity;
        final IntBinaryOperator val$operator;

        5ReducingSink(int i, IntBinaryOperator intBinaryOperator) {
            this.val$identity = i;
            this.val$operator = intBinaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            this.state = this.val$operator.applyAsInt(this.state, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Integer num) {
            Sink.OfInt.-CC.$default$accept((Sink.OfInt) this, num);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfInt.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$identity;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(5ReducingSink r1) {
            accept(r1.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public Integer get() {
            return Integer.valueOf(this.state);
        }
    }

    public class 6ReducingSink implements AccumulatingSink, Sink.OfInt {
        private boolean empty;
        private int state;
        final IntBinaryOperator val$operator;

        6ReducingSink(IntBinaryOperator intBinaryOperator) {
            this.val$operator = intBinaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            if (this.empty) {
                this.empty = false;
            } else {
                i = this.val$operator.applyAsInt(this.state, i);
            }
            this.state = i;
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Integer num) {
            Sink.OfInt.-CC.$default$accept((Sink.OfInt) this, num);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfInt.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void begin(long j) {
            this.empty = true;
            this.state = 0;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(6ReducingSink r2) {
            if (r2.empty) {
                return;
            }
            accept(r2.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public OptionalInt get() {
            return this.empty ? OptionalInt.empty() : OptionalInt.of(this.state);
        }
    }

    public class 7ReducingSink extends Box implements AccumulatingSink, Sink.OfInt {
        final ObjIntConsumer val$accumulator;
        final BinaryOperator val$combiner;
        final Supplier val$supplier;

        7ReducingSink(Supplier supplier, ObjIntConsumer objIntConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = objIntConsumer;
            this.val$combiner = binaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            this.val$accumulator.accept(this.state, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        @Override
        public void accept(Integer num) {
            Sink.OfInt.-CC.$default$accept((Sink.OfInt) this, num);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfInt.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public IntConsumer andThen(IntConsumer intConsumer) {
            return Objects.requireNonNull(intConsumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(7ReducingSink r3) {
            this.state = this.val$combiner.apply(this.state, r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }
    }

    public class 8ReducingSink implements AccumulatingSink, Sink.OfLong {
        private long state;
        final long val$identity;
        final LongBinaryOperator val$operator;

        8ReducingSink(long j, LongBinaryOperator longBinaryOperator) {
            this.val$identity = j;
            this.val$operator = longBinaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            this.state = this.val$operator.applyAsLong(this.state, j);
        }

        @Override
        public void accept(Long l) {
            Sink.OfLong.-CC.$default$accept((Sink.OfLong) this, l);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfLong.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void begin(long j) {
            this.state = this.val$identity;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(8ReducingSink r3) {
            accept(r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public Long get() {
            return Long.valueOf(this.state);
        }
    }

    public class 9ReducingSink implements AccumulatingSink, Sink.OfLong {
        private boolean empty;
        private long state;
        final LongBinaryOperator val$operator;

        9ReducingSink(LongBinaryOperator longBinaryOperator) {
            this.val$operator = longBinaryOperator;
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            if (this.empty) {
                this.empty = false;
            } else {
                j = this.val$operator.applyAsLong(this.state, j);
            }
            this.state = j;
        }

        @Override
        public void accept(Long l) {
            Sink.OfLong.-CC.$default$accept((Sink.OfLong) this, l);
        }

        @Override
        public void accept(Object obj) {
            Sink.OfLong.-CC.$default$accept(this, obj);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        public LongConsumer andThen(LongConsumer longConsumer) {
            return Objects.requireNonNull(longConsumer);
        }

        @Override
        public void begin(long j) {
            this.empty = true;
            this.state = 0L;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void combine(9ReducingSink r3) {
            if (r3.empty) {
                return;
            }
            accept(r3.state);
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public OptionalLong get() {
            return this.empty ? OptionalLong.empty() : OptionalLong.of(this.state);
        }
    }

    public interface AccumulatingSink extends TerminalSink {
        void combine(AccumulatingSink accumulatingSink);
    }

    public static abstract class Box {
        Object state;

        Box() {
        }

        public Object get() {
            return this.state;
        }
    }

    static abstract class CountingSink extends Box implements AccumulatingSink {
        long count;

        public static final class OfDouble extends CountingSink implements Sink.OfDouble {
            OfDouble() {
            }

            @Override
            public void accept(double d) {
                this.count++;
            }

            @Override
            public void accept(Double d) {
                Sink.OfDouble.-CC.$default$accept((Sink.OfDouble) this, d);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfDouble.-CC.$default$accept(this, obj);
            }

            public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
                return Objects.requireNonNull(doubleConsumer);
            }

            @Override
            public void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override
            public Object get() {
                return super.get();
            }
        }

        public static final class OfInt extends CountingSink implements Sink.OfInt {
            OfInt() {
            }

            @Override
            public void accept(int i) {
                this.count++;
            }

            @Override
            public void accept(Integer num) {
                Sink.OfInt.-CC.$default$accept((Sink.OfInt) this, num);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfInt.-CC.$default$accept(this, obj);
            }

            public IntConsumer andThen(IntConsumer intConsumer) {
                return Objects.requireNonNull(intConsumer);
            }

            @Override
            public void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override
            public Object get() {
                return super.get();
            }
        }

        public static final class OfLong extends CountingSink implements Sink.OfLong {
            OfLong() {
            }

            @Override
            public void accept(long j) {
                this.count++;
            }

            @Override
            public void accept(Long l) {
                Sink.OfLong.-CC.$default$accept((Sink.OfLong) this, l);
            }

            @Override
            public void accept(Object obj) {
                Sink.OfLong.-CC.$default$accept(this, obj);
            }

            public LongConsumer andThen(LongConsumer longConsumer) {
                return Objects.requireNonNull(longConsumer);
            }

            @Override
            public void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override
            public Object get() {
                return super.get();
            }
        }

        public static final class OfRef extends CountingSink {
            OfRef() {
            }

            @Override
            public void accept(Object obj) {
                this.count++;
            }

            @Override
            public void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override
            public Object get() {
                return super.get();
            }
        }

        CountingSink() {
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            Sink.-CC.$default$accept((Sink) this, i);
        }

        @Override
        public void accept(long j) {
            Sink.-CC.$default$accept((Sink) this, j);
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void begin(long j) {
            this.count = 0L;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        public void combine(CountingSink countingSink) {
            this.count += countingSink.count;
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        @Override
        public Long get() {
            return Long.valueOf(this.count);
        }
    }

    public static abstract class ReduceOp implements TerminalOp {
        private final StreamShape inputShape;

        ReduceOp(StreamShape streamShape) {
            this.inputShape = streamShape;
        }

        @Override
        public Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return ((AccumulatingSink) new ReduceTask(this, pipelineHelper, spliterator).invoke()).get();
        }

        @Override
        public Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return ((AccumulatingSink) pipelineHelper.wrapAndCopyInto(makeSink(), spliterator)).get();
        }

        @Override
        public int getOpFlags() {
            return TerminalOp.-CC.$default$getOpFlags(this);
        }

        public abstract AccumulatingSink makeSink();
    }

    public static final class ReduceTask extends AbstractTask {
        private final ReduceOp op;

        ReduceTask(ReduceOp reduceOp, PipelineHelper pipelineHelper, Spliterator spliterator) {
            super(pipelineHelper, spliterator);
            this.op = reduceOp;
        }

        ReduceTask(ReduceTask reduceTask, Spliterator spliterator) {
            super(reduceTask, spliterator);
            this.op = reduceTask.op;
        }

        @Override
        public AccumulatingSink doLeaf() {
            return (AccumulatingSink) this.helper.wrapAndCopyInto(this.op.makeSink(), this.spliterator);
        }

        @Override
        public ReduceTask makeChild(Spliterator spliterator) {
            return new ReduceTask(this, spliterator);
        }

        @Override
        public void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                AccumulatingSink accumulatingSink = (AccumulatingSink) ((ReduceTask) this.leftChild).getLocalResult();
                accumulatingSink.combine((AccumulatingSink) ((ReduceTask) this.rightChild).getLocalResult());
                setLocalResult(accumulatingSink);
            }
            super.onCompletion(countedCompleter);
        }
    }

    public static TerminalOp makeDouble(final double d, final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            @Override
            public 11ReducingSink makeSink() {
                return new 11ReducingSink(d, doubleBinaryOperator);
            }
        };
    }

    public static TerminalOp makeDouble(final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            @Override
            public 12ReducingSink makeSink() {
                return new 12ReducingSink(doubleBinaryOperator);
            }
        };
    }

    public static TerminalOp makeDouble(final Supplier supplier, final ObjDoubleConsumer objDoubleConsumer, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            @Override
            public 13ReducingSink makeSink() {
                return new 13ReducingSink(supplier, objDoubleConsumer, binaryOperator);
            }
        };
    }

    public static TerminalOp makeDoubleCounting() {
        return new ReduceOp(StreamShape.DOUBLE_VALUE) {
            @Override
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override
            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            @Override
            public CountingSink makeSink() {
                return new CountingSink.OfDouble();
            }
        };
    }

    public static TerminalOp makeInt(final int i, final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new ReduceOp(StreamShape.INT_VALUE) {
            @Override
            public 5ReducingSink makeSink() {
                return new 5ReducingSink(i, intBinaryOperator);
            }
        };
    }

    public static TerminalOp makeInt(final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new ReduceOp(StreamShape.INT_VALUE) {
            @Override
            public 6ReducingSink makeSink() {
                return new 6ReducingSink(intBinaryOperator);
            }
        };
    }

    public static TerminalOp makeInt(final Supplier supplier, final ObjIntConsumer objIntConsumer, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.INT_VALUE) {
            @Override
            public 7ReducingSink makeSink() {
                return new 7ReducingSink(supplier, objIntConsumer, binaryOperator);
            }
        };
    }

    public static TerminalOp makeIntCounting() {
        return new ReduceOp(StreamShape.INT_VALUE) {
            @Override
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override
            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            @Override
            public CountingSink makeSink() {
                return new CountingSink.OfInt();
            }
        };
    }

    public static TerminalOp makeLong(final long j, final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new ReduceOp(StreamShape.LONG_VALUE) {
            @Override
            public 8ReducingSink makeSink() {
                return new 8ReducingSink(j, longBinaryOperator);
            }
        };
    }

    public static TerminalOp makeLong(final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new ReduceOp(StreamShape.LONG_VALUE) {
            @Override
            public 9ReducingSink makeSink() {
                return new 9ReducingSink(longBinaryOperator);
            }
        };
    }

    public static TerminalOp makeLong(final Supplier supplier, final ObjLongConsumer objLongConsumer, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.LONG_VALUE) {
            @Override
            public 10ReducingSink makeSink() {
                return new 10ReducingSink(supplier, objLongConsumer, binaryOperator);
            }
        };
    }

    public static TerminalOp makeLongCounting() {
        return new ReduceOp(StreamShape.LONG_VALUE) {
            @Override
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override
            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            @Override
            public CountingSink makeSink() {
                return new CountingSink.OfLong();
            }
        };
    }

    public static TerminalOp makeRef(final Collector collector) {
        final Supplier supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
        final BiConsumer accumulator = collector.accumulator();
        final BinaryOperator combiner = collector.combiner();
        return new ReduceOp(StreamShape.REFERENCE) {
            @Override
            public int getOpFlags() {
                if (collector.characteristics().contains(Collector.Characteristics.UNORDERED)) {
                    return StreamOpFlag.NOT_ORDERED;
                }
                return 0;
            }

            @Override
            public 3ReducingSink makeSink() {
                return new 3ReducingSink(supplier, accumulator, combiner);
            }
        };
    }

    public static TerminalOp makeRef(final Object obj, final BiFunction biFunction, final BinaryOperator binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.REFERENCE) {
            @Override
            public 1ReducingSink makeSink() {
                return new 1ReducingSink(obj, biFunction, binaryOperator);
            }
        };
    }

    public static TerminalOp makeRef(final BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp(StreamShape.REFERENCE) {
            @Override
            public 2ReducingSink makeSink() {
                final BinaryOperator binaryOperator2 = binaryOperator;
                return new AccumulatingSink() {
                    private boolean empty;
                    private Object state;

                    @Override
                    public void accept(double d) {
                        Sink.-CC.$default$accept(this, d);
                    }

                    @Override
                    public void accept(int i) {
                        Sink.-CC.$default$accept((Sink) this, i);
                    }

                    @Override
                    public void accept(long j) {
                        Sink.-CC.$default$accept((Sink) this, j);
                    }

                    @Override
                    public void accept(Object obj) {
                        if (this.empty) {
                            this.empty = false;
                        } else {
                            obj = BinaryOperator.this.apply(this.state, obj);
                        }
                        this.state = obj;
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }

                    @Override
                    public void begin(long j) {
                        this.empty = true;
                        this.state = null;
                    }

                    @Override
                    public boolean cancellationRequested() {
                        return Sink.-CC.$default$cancellationRequested(this);
                    }

                    @Override
                    public void combine(2ReducingSink r2) {
                        if (r2.empty) {
                            return;
                        }
                        accept(r2.state);
                    }

                    @Override
                    public void end() {
                        Sink.-CC.$default$end(this);
                    }

                    @Override
                    public Optional get() {
                        return this.empty ? Optional.empty() : Optional.of(this.state);
                    }
                };
            }
        };
    }

    public static TerminalOp makeRef(final Supplier supplier, final BiConsumer biConsumer, final BiConsumer biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return new ReduceOp(StreamShape.REFERENCE) {
            @Override
            public 4ReducingSink makeSink() {
                return new 4ReducingSink(supplier, biConsumer, biConsumer2);
            }
        };
    }

    public static TerminalOp makeRefCounting() {
        return new ReduceOp(StreamShape.REFERENCE) {
            @Override
            public Long evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override
            public Long evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Long.valueOf(spliterator.getExactSizeIfKnown()) : (Long) super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }

            @Override
            public CountingSink makeSink() {
                return new CountingSink.OfRef();
            }
        };
    }
}
