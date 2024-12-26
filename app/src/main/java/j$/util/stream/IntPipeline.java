package j$.util.stream;

import j$.util.IntSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalInt;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.DoublePipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.MatchOps;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.StreamSpliterators$DelegatingSpliterator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
abstract class IntPipeline extends AbstractPipeline implements IntStream {

    public static class Head extends IntPipeline {
        public Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        @Override
        public void forEach(IntConsumer intConsumer) {
            if (isParallel()) {
                super.forEach(intConsumer);
            } else {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            }
        }

        @Override
        public void forEachOrdered(IntConsumer intConsumer) {
            if (isParallel()) {
                super.forEachOrdered(intConsumer);
            } else {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            }
        }

        @Override
        public Iterator<Integer> iterator() {
            return super.iterator();
        }

        @Override
        Spliterator lazySpliterator(Supplier supplier) {
            return super.lazySpliterator(supplier);
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
        public IntStream parallel() {
            return (IntStream) super.parallel();
        }

        @Override
        public IntStream sequential() {
            return (IntStream) super.sequential();
        }

        @Override
        public Spliterator spliterator() {
            return super.spliterator();
        }

        @Override
        public BaseStream unordered() {
            return super.unordered();
        }
    }

    static abstract class StatefulOp extends IntPipeline {
        public StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        @Override
        public Iterator<Integer> iterator() {
            return super.iterator();
        }

        @Override
        Spliterator lazySpliterator(Supplier supplier) {
            return super.lazySpliterator(supplier);
        }

        @Override
        final boolean opIsStateful() {
            return true;
        }

        @Override
        public IntStream parallel() {
            return (IntStream) super.parallel();
        }

        @Override
        public IntStream sequential() {
            return (IntStream) super.sequential();
        }

        @Override
        public Spliterator spliterator() {
            return super.spliterator();
        }

        @Override
        public BaseStream unordered() {
            return super.unordered();
        }
    }

    public static abstract class StatelessOp extends IntPipeline {
        public StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        @Override
        public Iterator<Integer> iterator() {
            return super.iterator();
        }

        @Override
        Spliterator lazySpliterator(Supplier supplier) {
            return super.lazySpliterator(supplier);
        }

        @Override
        final boolean opIsStateful() {
            return false;
        }

        @Override
        public IntStream parallel() {
            return (IntStream) super.parallel();
        }

        @Override
        public IntStream sequential() {
            return (IntStream) super.sequential();
        }

        @Override
        public Spliterator spliterator() {
            return super.spliterator();
        }

        @Override
        public BaseStream unordered() {
            return super.unordered();
        }
    }

    IntPipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    IntPipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    public static Spliterator.OfInt adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Spliterator<Integer> s)");
        }
        


return null;

//throw new UnsupportedOperationException(
IntStream.adapt(Spliterator<Integer> s)");
    }

    private static IntConsumer adapt(Sink sink) {
        if (sink instanceof IntConsumer) {
            return (IntConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Sink<Integer> s)");
        }
        Objects.requireNonNull(sink);
        return new IntPipeline$ExternalSyntheticLambda1(sink);
    }

    public static long[] lambda$average$1() {
        return new long[2];
    }

    public static void lambda$average$2(long[] jArr, int i) {
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + i;
    }

    public static void lambda$average$3(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    public static Integer[] lambda$toArray$5(int i) {
        return new Integer[i];
    }

    private Stream mapToObj(final IntFunction intFunction, int i) {
        return new ReferencePipeline.StatelessOp(this, StreamShape.INT_VALUE, i) {
            @Override
            public Sink opWrapSink(int i2, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i3) {
                        this.downstream.accept((Sink) intFunction.apply(i3));
                    }
                };
            }
        };
    }

    @Override
    public final boolean allMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override
    public final boolean anyMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override
    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp(this, StreamShape.INT_VALUE, 0) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        this.downstream.accept(i2);
                    }
                };
            }
        };
    }

    @Override
    public final LongStream asLongStream() {
        return new LongPipeline.StatelessOp(this, StreamShape.INT_VALUE, 0) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        this.downstream.accept(i2);
                    }
                };
            }
        };
    }

    @Override
    public final OptionalDouble average() {
        long[] jArr = (long[]) collect(new Supplier() {
            @Override
            public final Object get() {
                return IntPipeline.lambda$average$1();
            }
        }, new ObjIntConsumer() {
            @Override
            public final void accept(Object obj, int i) {
                IntPipeline.lambda$average$2((long[]) obj, i);
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                IntPipeline.lambda$average$3((long[]) obj, (long[]) obj2);
            }

            public BiConsumer andThen(BiConsumer biConsumer) {
                return Objects.requireNonNull(biConsumer);
            }
        });
        long j = jArr[0];
        return j > 0 ? OptionalDouble.of(jArr[1] / j) : OptionalDouble.empty();
    }

    @Override
    public final Stream boxed() {
        return mapToObj(new IntFunction() {
            @Override
            public final Object apply(int i) {
                return Integer.valueOf(i);
            }
        }, 0);
    }

    @Override
    public final Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, final BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        return evaluate(ReduceOps.makeInt(supplier, objIntConsumer, new BinaryOperator() {
            public BiFunction andThen(Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj, Object obj2) {
                return BiConsumer.this.accept(obj, obj2);
            }
        }));
    }

    @Override
    public final long count() {
        return ((Long) evaluate(ReduceOps.makeIntCounting())).longValue();
    }

    @Override
    public final IntStream distinct() {
        return boxed().distinct().mapToInt(new ToIntFunction() {
            @Override
            public final int applyAsInt(Object obj) {
                int intValue;
                intValue = ((Integer) obj).intValue();
                return intValue;
            }
        });
    }

    @Override
    final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collectInt(pipelineHelper, spliterator, z);
    }

    @Override
    public final IntStream filter(final IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        if (intPredicate.test(i2)) {
                            this.downstream.accept(i2);
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
    public final OptionalInt findAny() {
        return (OptionalInt) evaluate(FindOps.makeInt(false));
    }

    @Override
    public final OptionalInt findFirst() {
        return (OptionalInt) evaluate(FindOps.makeInt(true));
    }

    @Override
    public final IntStream flatMap(final IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    boolean cancellationRequestedCalled;
                    IntConsumer downstreamAsInt;

                    {
                        Sink sink2 = this.downstream;
                        Objects.requireNonNull(sink2);
                        this.downstreamAsInt = new IntPipeline$ExternalSyntheticLambda1(sink2);
                    }

                    @Override
                    public void accept(int i2) {
                        IntStream intStream = (IntStream) intFunction.apply(i2);
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
    public void forEach(IntConsumer intConsumer) {
        evaluate(ForEachOps.makeInt(intConsumer, false));
    }

    @Override
    public void forEachOrdered(IntConsumer intConsumer) {
        evaluate(ForEachOps.makeInt(intConsumer, true));
    }

    @Override
    final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        Spliterator.OfInt adapt = adapt(spliterator);
        IntConsumer adapt2 = adapt(sink);
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested) {
                break;
            }
        } while (adapt.tryAdvance(adapt2));
        return cancellationRequested;
    }

    @Override
    public final StreamShape getOutputShape() {
        return StreamShape.INT_VALUE;
    }

    @Override
    public final Iterator<Integer> iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    final Spliterator.OfInt lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator.OfInt(supplier);
    }

    @Override
    public final IntStream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeInt(this, 0L, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.intBuilder(j);
    }

    @Override
    public final IntStream map(final IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        this.downstream.accept(intUnaryOperator.applyAsInt(i2));
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        Objects.requireNonNull(intToDoubleFunction);
        return new DoublePipeline.StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, intToDoubleFunction) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        getClass();
                        throw null;
                    }
                };
            }
        };
    }

    @Override
    public final LongStream mapToLong(IntToLongFunction intToLongFunction) {
        Objects.requireNonNull(intToLongFunction);
        return new LongPipeline.StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, intToLongFunction) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        getClass();
                        throw null;
                    }
                };
            }
        };
    }

    @Override
    public final Stream mapToObj(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return mapToObj(intFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    @Override
    public final OptionalInt max() {
        return reduce(new IntBinaryOperator() {
            @Override
            public final int applyAsInt(int i, int i2) {
                return Math.max(i, i2);
            }
        });
    }

    @Override
    public final OptionalInt min() {
        return reduce(new IntBinaryOperator() {
            @Override
            public final int applyAsInt(int i, int i2) {
                return Math.min(i, i2);
            }
        });
    }

    @Override
    public final boolean noneMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override
    public final IntStream peek(final IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new StatelessOp(this, StreamShape.INT_VALUE, 0) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedInt(sink) {
                    @Override
                    public void accept(int i2) {
                        intConsumer.accept(i2);
                        this.downstream.accept(i2);
                    }
                };
            }
        };
    }

    @Override
    public final int reduce(int i, IntBinaryOperator intBinaryOperator) {
        return ((Integer) evaluate(ReduceOps.makeInt(i, intBinaryOperator))).intValue();
    }

    @Override
    public final OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        return (OptionalInt) evaluate(ReduceOps.makeInt(intBinaryOperator));
    }

    @Override
    public final IntStream skip(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            return i == 0 ? this : SliceOps.makeInt(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override
    public final IntStream sorted() {
        return SortedOps.makeInt(this);
    }

    @Override
    public final Spliterator.OfInt spliterator() {
        return adapt(super.spliterator());
    }

    @Override
    public final int sum() {
        return reduce(0, new IntBinaryOperator() {
            @Override
            public final int applyAsInt(int i, int i2) {
                return i + i2;
            }
        });
    }

    @Override
    public final IntSummaryStatistics summaryStatistics() {
        return (IntSummaryStatistics) collect(new Supplier() {
            @Override
            public final Object get() {
                return new IntSummaryStatistics();
            }
        }, new ObjIntConsumer() {
            @Override
            public final void accept(Object obj, int i) {
                ((IntSummaryStatistics) obj).accept(i);
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                ((IntSummaryStatistics) obj).combine((IntSummaryStatistics) obj2);
            }

            public BiConsumer andThen(BiConsumer biConsumer) {
                return Objects.requireNonNull(biConsumer);
            }
        });
    }

    @Override
    public final int[] toArray() {
        return (int[]) Nodes.flattenInt((Node.OfInt) evaluateToArrayNode(new IntFunction() {
            @Override
            public final Object apply(int i) {
                return IntPipeline.lambda$toArray$5(i);
            }
        })).asPrimitiveArray();
    }

    @Override
    public IntStream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_ORDERED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    @Override
    final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$IntWrappingSpliterator(pipelineHelper, supplier, z);
    }
}
