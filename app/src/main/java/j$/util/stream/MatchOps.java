package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.MatchOps;
import j$.util.stream.Sink;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
abstract class MatchOps {

    public class 2MatchSink extends BooleanTerminalSink implements Sink.OfInt {
        final MatchKind val$matchKind;
        final IntPredicate val$predicate;

        2MatchSink(MatchKind matchKind, IntPredicate intPredicate) {
            super(matchKind);
            this.val$matchKind = matchKind;
            this.val$predicate = intPredicate;
        }

        @Override
        public void accept(int i) {
            if (this.stop || this.val$predicate.test(i) != this.val$matchKind.stopOnPredicateMatches) {
                return;
            }
            this.stop = true;
            this.value = this.val$matchKind.shortCircuitResult;
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
    }

    public class 3MatchSink extends BooleanTerminalSink implements Sink.OfLong {
        final MatchKind val$matchKind;

        3MatchSink(MatchKind matchKind, LongPredicate longPredicate) {
            super(matchKind);
            this.val$matchKind = matchKind;
        }

        @Override
        public void accept(long j) {
            if (!this.stop) {
                throw null;
            }
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
    }

    public class 4MatchSink extends BooleanTerminalSink implements Sink.OfDouble {
        final MatchKind val$matchKind;

        4MatchSink(MatchKind matchKind, DoublePredicate doublePredicate) {
            super(matchKind);
            this.val$matchKind = matchKind;
        }

        @Override
        public void accept(double d) {
            if (!this.stop) {
                throw null;
            }
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
    }

    public static abstract class BooleanTerminalSink implements Sink {
        boolean stop;
        boolean value;

        BooleanTerminalSink(MatchKind matchKind) {
            this.value = !matchKind.shortCircuitResult;
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
            Sink.-CC.$default$begin(this, j);
        }

        @Override
        public boolean cancellationRequested() {
            return this.stop;
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        public boolean getAndClearState() {
            return this.value;
        }
    }

    public enum MatchKind {
        ANY(true, true),
        ALL(false, false),
        NONE(true, false);
        
        private final boolean shortCircuitResult;
        private final boolean stopOnPredicateMatches;

        MatchKind(boolean z, boolean z2) {
            this.stopOnPredicateMatches = z;
            this.shortCircuitResult = z2;
        }
    }

    public static final class MatchOp implements TerminalOp {
        private final StreamShape inputShape;
        final MatchKind matchKind;
        final Supplier sinkSupplier;

        MatchOp(StreamShape streamShape, MatchKind matchKind, Supplier supplier) {
            this.inputShape = streamShape;
            this.matchKind = matchKind;
            this.sinkSupplier = supplier;
        }

        @Override
        public Boolean evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return (Boolean) new MatchTask(this, pipelineHelper, spliterator).invoke();
        }

        @Override
        public Boolean evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return Boolean.valueOf(((BooleanTerminalSink) pipelineHelper.wrapAndCopyInto((BooleanTerminalSink) this.sinkSupplier.get(), spliterator)).getAndClearState());
        }

        @Override
        public int getOpFlags() {
            return StreamOpFlag.IS_SHORT_CIRCUIT | StreamOpFlag.NOT_ORDERED;
        }
    }

    public static final class MatchTask extends AbstractShortCircuitTask {
        private final MatchOp op;

        MatchTask(MatchOp matchOp, PipelineHelper pipelineHelper, Spliterator spliterator) {
            super(pipelineHelper, spliterator);
            this.op = matchOp;
        }

        MatchTask(MatchTask matchTask, Spliterator spliterator) {
            super(matchTask, spliterator);
            this.op = matchTask.op;
        }

        @Override
        public Boolean doLeaf() {
            boolean andClearState = ((BooleanTerminalSink) this.helper.wrapAndCopyInto((BooleanTerminalSink) this.op.sinkSupplier.get(), this.spliterator)).getAndClearState();
            if (andClearState == this.op.matchKind.shortCircuitResult) {
                shortCircuit(Boolean.valueOf(andClearState));
                return null;
            }
            return null;
        }

        @Override
        public Boolean getEmptyResult() {
            return Boolean.valueOf(!this.op.matchKind.shortCircuitResult);
        }

        @Override
        public MatchTask makeChild(Spliterator spliterator) {
            return new MatchTask(this, spliterator);
        }
    }

    public static BooleanTerminalSink lambda$makeDouble$3(MatchKind matchKind, DoublePredicate doublePredicate) {
        return new 4MatchSink(matchKind, doublePredicate);
    }

    public static BooleanTerminalSink lambda$makeInt$1(MatchKind matchKind, IntPredicate intPredicate) {
        return new 2MatchSink(matchKind, intPredicate);
    }

    public static BooleanTerminalSink lambda$makeLong$2(MatchKind matchKind, LongPredicate longPredicate) {
        return new 3MatchSink(matchKind, longPredicate);
    }

    public static BooleanTerminalSink lambda$makeRef$0(MatchKind matchKind, Predicate predicate) {
        return new BooleanTerminalSink(predicate) {
            final Predicate val$predicate;

            {
                super(MatchKind.this);
                this.val$predicate = predicate;
            }

            @Override
            public void accept(Object obj) {
                if (this.stop || this.val$predicate.test(obj) != MatchKind.this.stopOnPredicateMatches) {
                    return;
                }
                this.stop = true;
                this.value = MatchKind.this.shortCircuitResult;
            }
        };
    }

    public static TerminalOp makeDouble(final DoublePredicate doublePredicate, final MatchKind matchKind) {
        Objects.requireNonNull(doublePredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.DOUBLE_VALUE, matchKind, new Supplier(doublePredicate) {
            @Override
            public final Object get() {
                return MatchOps.lambda$makeDouble$3(MatchOps.MatchKind.this, null);
            }
        });
    }

    public static TerminalOp makeInt(final IntPredicate intPredicate, final MatchKind matchKind) {
        Objects.requireNonNull(intPredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.INT_VALUE, matchKind, new Supplier() {
            @Override
            public final Object get() {
                return MatchOps.lambda$makeInt$1(MatchOps.MatchKind.this, intPredicate);
            }
        });
    }

    public static TerminalOp makeLong(final LongPredicate longPredicate, final MatchKind matchKind) {
        Objects.requireNonNull(longPredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.LONG_VALUE, matchKind, new Supplier(longPredicate) {
            @Override
            public final Object get() {
                return MatchOps.lambda$makeLong$2(MatchOps.MatchKind.this, null);
            }
        });
    }

    public static TerminalOp makeRef(final Predicate predicate, final MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.REFERENCE, matchKind, new Supplier() {
            @Override
            public final Object get() {
                return MatchOps.lambda$makeRef$0(MatchOps.MatchKind.this, predicate);
            }
        });
    }
}
