package j$.util.stream;

import j$.util.Objects;
import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.BinaryOperator$-CC;
import j$.util.stream.Collector;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.MatchOps;
import j$.util.stream.Node;
import j$.util.stream.Sink;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
abstract class ReferencePipeline extends AbstractPipeline implements Stream {

    public static class Head extends ReferencePipeline {
        public Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        public Head(Supplier supplier, int i, boolean z) {
            super(supplier, i, z);
        }

        @Override
        public void forEach(Consumer consumer) {
            if (isParallel()) {
                super.forEach(consumer);
            } else {
                sourceStageSpliterator().forEachRemaining(consumer);
            }
        }

        @Override
        public void forEachOrdered(Consumer consumer) {
            if (isParallel()) {
                super.forEachOrdered(consumer);
            } else {
                sourceStageSpliterator().forEachRemaining(consumer);
            }
        }

        @Override
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override
        public final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        @Override
        public BaseStream unordered() {
            return super.unordered();
        }
    }

    static abstract class StatefulOp extends ReferencePipeline {
        public StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        @Override
        final boolean opIsStateful() {
            return true;
        }

        @Override
        public BaseStream unordered() {
            return super.unordered();
        }
    }

    public static abstract class StatelessOp extends ReferencePipeline {
        public StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        @Override
        final boolean opIsStateful() {
            return false;
        }

        @Override
        public BaseStream unordered() {
            return super.unordered();
        }
    }

    ReferencePipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    ReferencePipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    ReferencePipeline(Supplier supplier, int i, boolean z) {
        super(supplier, i, z);
    }

    public static Object[] lambda$toArray$0(int i) {
        return new Object[i];
    }

    @Override
    public final boolean allMatch(Predicate predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override
    public final boolean anyMatch(Predicate predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override
    public final Object collect(Collector collector) {
        final Object evaluate;
        if (isParallel() && collector.characteristics().contains(Collector.Characteristics.CONCURRENT) && (!isOrdered() || collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            evaluate = collector.supplier().get();
            final BiConsumer accumulator = collector.accumulator();
            forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    BiConsumer.this.accept(evaluate, obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } else {
            evaluate = evaluate(ReduceOps.makeRef(collector));
        }
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? evaluate : collector.finisher().apply(evaluate);
    }

    @Override
    public final Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        return evaluate(ReduceOps.makeRef(supplier, biConsumer, biConsumer2));
    }

    @Override
    public final long count() {
        return ((Long) evaluate(ReduceOps.makeRefCounting())).longValue();
    }

    @Override
    public final Stream distinct() {
        return DistinctOps.makeRef(this);
    }

    @Override
    public final Stream dropWhile(Predicate predicate) {
        return WhileOps.makeDropWhileRef(this, predicate);
    }

    @Override
    final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collect(pipelineHelper, spliterator, z, intFunction);
    }

    @Override
    public final Stream filter(final Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    @Override
                    public void accept(Object obj) {
                        if (predicate.test(obj)) {
                            this.downstream.accept((Sink) obj);
                        }
                    }

                    @Override
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }
                };
            }
        };
    }

    @Override
    public final Optional findAny() {
        return (Optional) evaluate(FindOps.makeRef(false));
    }

    @Override
    public final Optional findFirst() {
        return (Optional) evaluate(FindOps.makeRef(true));
    }

    @Override
    public final Stream flatMap(final Function function) {
        Objects.requireNonNull(function);
        return new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;

                    @Override
                    public void accept(Object obj) {
                        Stream stream = (Stream) function.apply(obj);
                        if (stream != null) {
                            try {
                                if (this.cancellationRequestedCalled) {
                                    Spliterator spliterator = ((Stream) stream.sequential()).spliterator();
                                    while (!this.downstream.cancellationRequested() && spliterator.tryAdvance(this.downstream)) {
                                    }
                                } else {
                                    ((Stream) stream.sequential()).forEach(this.downstream);
                                }
                            } catch (Throwable th) {
                                try {
                                    stream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        if (stream != null) {
                            stream.close();
                        }
                    }

                    @Override
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    @Override
                    public boolean cancellationRequested() {
                        this.cancellationRequestedCalled = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream flatMapToDouble(final Function function) {
        Objects.requireNonNull(function);
        return new DoublePipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;
                    DoubleConsumer downstreamAsDouble;

                    {
                        Sink sink2 = this.downstream;
                        Objects.requireNonNull(sink2);
                        this.downstreamAsDouble = new DoublePipeline$ExternalSyntheticLambda5(sink2);
                    }

                    @Override
                    public void accept(Object obj) {
                        DoubleStream doubleStream = (DoubleStream) function.apply(obj);
                        if (doubleStream != null) {
                            try {
                                if (this.cancellationRequestedCalled) {
                                    Spliterator.OfDouble spliterator = doubleStream.sequential().spliterator();
                                    while (!this.downstream.cancellationRequested() && spliterator.tryAdvance(this.downstreamAsDouble)) {
                                    }
                                } else {
                                    doubleStream.sequential().forEach(this.downstreamAsDouble);
                                }
                            } catch (Throwable th) {
                                try {
                                    doubleStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        if (doubleStream != null) {
                            doubleStream.close();
                        }
                    }

                    @Override
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    @Override
                    public boolean cancellationRequested() {
                        this.cancellationRequestedCalled = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public final IntStream flatMapToInt(final Function function) {
        Objects.requireNonNull(function);
        return new IntPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;
                    IntConsumer downstreamAsInt;

                    {
                        Sink sink2 = this.downstream;
                        Objects.requireNonNull(sink2);
                        this.downstreamAsInt = new IntPipeline$ExternalSyntheticLambda1(sink2);
                    }

                    @Override
                    public void accept(Object obj) {
                        IntStream intStream = (IntStream) function.apply(obj);
                        if (intStream != null) {
                            try {
                                if (this.cancellationRequestedCalled) {
                                    Spliterator.OfInt spliterator = intStream.sequential().spliterator();
                                    while (!this.downstream.cancellationRequested() && spliterator.tryAdvance(this.downstreamAsInt)) {
                                    }
                                } else {
                                    intStream.sequential().forEach(this.downstreamAsInt);
                                }
                            } catch (Throwable th) {
                                try {
                                    intStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        if (intStream != null) {
                            intStream.close();
                        }
                    }

                    @Override
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    @Override
                    public boolean cancellationRequested() {
                        this.cancellationRequestedCalled = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public final LongStream flatMapToLong(final Function function) {
        Objects.requireNonNull(function);
        return new LongPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean cancellationRequestedCalled;
                    LongConsumer downstreamAsLong;

                    {
                        Sink sink2 = this.downstream;
                        Objects.requireNonNull(sink2);
                        this.downstreamAsLong = new LongPipeline$ExternalSyntheticLambda10(sink2);
                    }

                    @Override
                    public void accept(Object obj) {
                        LongStream longStream = (LongStream) function.apply(obj);
                        if (longStream != null) {
                            try {
                                if (this.cancellationRequestedCalled) {
                                    Spliterator.OfLong spliterator = longStream.sequential().spliterator();
                                    while (!this.downstream.cancellationRequested() && spliterator.tryAdvance(this.downstreamAsLong)) {
                                    }
                                } else {
                                    longStream.sequential().forEach(this.downstreamAsLong);
                                }
                            } catch (Throwable th) {
                                try {
                                    longStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        if (longStream != null) {
                            longStream.close();
                        }
                    }

                    @Override
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    @Override
                    public boolean cancellationRequested() {
                        this.cancellationRequestedCalled = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public void forEach(Consumer consumer) {
        evaluate(ForEachOps.makeRef(consumer, false));
    }

    @Override
    public void forEachOrdered(Consumer consumer) {
        evaluate(ForEachOps.makeRef(consumer, true));
    }

    @Override
    final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested) {
                break;
            }
        } while (spliterator.tryAdvance(sink));
        return cancellationRequested;
    }

    @Override
    public final StreamShape getOutputShape() {
        return StreamShape.REFERENCE;
    }

    @Override
    public final Iterator iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    final Spliterator lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator(supplier);
    }

    @Override
    public final Stream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeRef(this, 0L, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.builder(j, intFunction);
    }

    @Override
    public final Stream map(final Function function) {
        Objects.requireNonNull(function);
        return new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    @Override
                    public void accept(Object obj) {
                        this.downstream.accept((Sink) function.apply(obj));
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream mapToDouble(final ToDoubleFunction toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new DoublePipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    @Override
                    public void accept(Object obj) {
                        this.downstream.accept(toDoubleFunction.applyAsDouble(obj));
                    }
                };
            }
        };
    }

    @Override
    public final IntStream mapToInt(final ToIntFunction toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new IntPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    @Override
                    public void accept(Object obj) {
                        this.downstream.accept(toIntFunction.applyAsInt(obj));
                    }
                };
            }
        };
    }

    @Override
    public final LongStream mapToLong(final ToLongFunction toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new LongPipeline.StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    @Override
                    public void accept(Object obj) {
                        this.downstream.accept(toLongFunction.applyAsLong(obj));
                    }
                };
            }
        };
    }

    @Override
    public final Optional max(Comparator comparator) {
        return reduce(BinaryOperator$-CC.maxBy(comparator));
    }

    @Override
    public final Optional min(Comparator comparator) {
        return reduce(BinaryOperator$-CC.minBy(comparator));
    }

    @Override
    public final boolean noneMatch(Predicate predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override
    public final Stream peek(final Consumer consumer) {
        Objects.requireNonNull(consumer);
        return new StatelessOp(this, StreamShape.REFERENCE, 0) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    @Override
                    public void accept(Object obj) {
                        consumer.accept(obj);
                        this.downstream.accept((Sink) obj);
                    }
                };
            }
        };
    }

    @Override
    public final Optional reduce(BinaryOperator binaryOperator) {
        return (Optional) evaluate(ReduceOps.makeRef(binaryOperator));
    }

    @Override
    public final Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        return evaluate(ReduceOps.makeRef(obj, biFunction, binaryOperator));
    }

    @Override
    public final Object reduce(Object obj, BinaryOperator binaryOperator) {
        return evaluate(ReduceOps.makeRef(obj, binaryOperator, binaryOperator));
    }

    @Override
    public final Stream skip(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            return i == 0 ? this : SliceOps.makeRef(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override
    public final Stream sorted() {
        return SortedOps.makeRef(this);
    }

    @Override
    public final Stream sorted(Comparator comparator) {
        return SortedOps.makeRef(this, comparator);
    }

    @Override
    public final Stream takeWhile(Predicate predicate) {
        return WhileOps.makeTakeWhileRef(this, predicate);
    }

    @Override
    public final Object[] toArray() {
        return toArray(new IntFunction() {
            @Override
            public final Object apply(int i) {
                return ReferencePipeline.lambda$toArray$0(i);
            }
        });
    }

    @Override
    public final Object[] toArray(IntFunction intFunction) {
        return Nodes.flatten(evaluateToArrayNode(intFunction), intFunction).asArray(intFunction);
    }

    @Override
    public Stream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.REFERENCE, StreamOpFlag.NOT_ORDERED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    @Override
    final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$WrappingSpliterator(pipelineHelper, supplier, z);
    }
}
