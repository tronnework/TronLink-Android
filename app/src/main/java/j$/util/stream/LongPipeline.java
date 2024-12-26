package j$.util.stream;

import j$.util.LongSummaryStatistics;
import j$.util.Objects;
import j$.util.OptionalDouble;
import j$.util.OptionalLong;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
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
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;
abstract class LongPipeline extends AbstractPipeline implements LongStream {

    public static class Head extends LongPipeline {
        public Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        @Override
        public void forEach(LongConsumer longConsumer) {
            if (isParallel()) {
                super.forEach(longConsumer);
            } else {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            }
        }

        @Override
        public void forEachOrdered(LongConsumer longConsumer) {
            if (isParallel()) {
                super.forEachOrdered(longConsumer);
            } else {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            }
        }

        @Override
        public Iterator<Long> iterator() {
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
        public LongStream parallel() {
            return (LongStream) super.parallel();
        }

        @Override
        public LongStream sequential() {
            return (LongStream) super.sequential();
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

    static abstract class StatefulOp extends LongPipeline {
        public StatefulOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        @Override
        public Iterator<Long> iterator() {
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
        public LongStream parallel() {
            return (LongStream) super.parallel();
        }

        @Override
        public LongStream sequential() {
            return (LongStream) super.sequential();
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

    public static abstract class StatelessOp extends LongPipeline {
        public StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }

        @Override
        public Iterator<Long> iterator() {
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
        public LongStream parallel() {
            return (LongStream) super.parallel();
        }

        @Override
        public LongStream sequential() {
            return (LongStream) super.sequential();
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

    LongPipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    LongPipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    public static Spliterator.OfLong adapt(Spliterator spliterator) {
        if (spliterator instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) spliterator;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Spliterator<Long> s)");
        }
        


return null;

//throw new UnsupportedOperationException(
LongStream.adapt(Spliterator<Long> s)");
    }

    private static LongConsumer adapt(Sink sink) {
        if (sink instanceof LongConsumer) {
            return (LongConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Sink<Long> s)");
        }
        Objects.requireNonNull(sink);
        return new LongPipeline$ExternalSyntheticLambda10(sink);
    }

    public static long[] lambda$average$1() {
        return new long[2];
    }

    public static void lambda$average$2(long[] jArr, long j) {
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + j;
    }

    public static void lambda$average$3(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    public static Long[] lambda$toArray$5(int i) {
        return new Long[i];
    }

    private Stream mapToObj(final LongFunction longFunction, int i) {
        return new ReferencePipeline.StatelessOp(this, StreamShape.LONG_VALUE, i) {
            @Override
            public Sink opWrapSink(int i2, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        this.downstream.accept((Sink) longFunction.apply(j));
                    }
                };
            }
        };
    }

    @Override
    public final boolean allMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override
    public final boolean anyMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override
    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_DISTINCT) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        this.downstream.accept(j);
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
                return LongPipeline.lambda$average$1();
            }
        }, new ObjLongConsumer() {
            @Override
            public final void accept(Object obj, long j) {
                LongPipeline.lambda$average$2((long[]) obj, j);
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                LongPipeline.lambda$average$3((long[]) obj, (long[]) obj2);
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
        return mapToObj(new LongFunction() {
            @Override
            public final Object apply(long j) {
                return Long.valueOf(j);
            }
        }, 0);
    }

    @Override
    public final Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, final BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        return evaluate(ReduceOps.makeLong(supplier, objLongConsumer, new BinaryOperator() {
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
        return ((Long) evaluate(ReduceOps.makeLongCounting())).longValue();
    }

    @Override
    public final LongStream distinct() {
        return boxed().distinct().mapToLong(new ToLongFunction() {
            @Override
            public final long applyAsLong(Object obj) {
                long longValue;
                longValue = ((Long) obj).longValue();
                return longValue;
            }
        });
    }

    @Override
    final Node evaluateToNode(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Nodes.collectLong(pipelineHelper, spliterator, z);
    }

    @Override
    public final LongStream filter(LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SIZED, longPredicate) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        getClass();
                        throw null;
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
    public final OptionalLong findAny() {
        return (OptionalLong) evaluate(FindOps.makeLong(false));
    }

    @Override
    public final OptionalLong findFirst() {
        return (OptionalLong) evaluate(FindOps.makeLong(true));
    }

    @Override
    public final LongStream flatMap(final LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    boolean cancellationRequestedCalled;
                    LongConsumer downstreamAsLong;

                    {
                        Sink sink2 = this.downstream;
                        Objects.requireNonNull(sink2);
                        this.downstreamAsLong = new LongPipeline$ExternalSyntheticLambda10(sink2);
                    }

                    @Override
                    public void accept(long j) {
                        LongStream longStream = (LongStream) longFunction.apply(j);
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
    public void forEach(LongConsumer longConsumer) {
        evaluate(ForEachOps.makeLong(longConsumer, false));
    }

    @Override
    public void forEachOrdered(LongConsumer longConsumer) {
        evaluate(ForEachOps.makeLong(longConsumer, true));
    }

    @Override
    final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        Spliterator.OfLong adapt = adapt(spliterator);
        LongConsumer adapt2 = adapt(sink);
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
        return StreamShape.LONG_VALUE;
    }

    @Override
    public final Iterator<Long> iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    final Spliterator.OfLong lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator.OfLong(supplier);
    }

    @Override
    public final LongStream limit(long j) {
        if (j >= 0) {
            return SliceOps.makeLong(this, 0L, j);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.longBuilder(j);
    }

    @Override
    public final LongStream map(LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longUnaryOperator);
        return new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longUnaryOperator) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        getClass();
                        throw null;
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction) {
        Objects.requireNonNull(longToDoubleFunction);
        return new DoublePipeline.StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longToDoubleFunction) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        getClass();
                        throw null;
                    }
                };
            }
        };
    }

    @Override
    public final IntStream mapToInt(LongToIntFunction longToIntFunction) {
        Objects.requireNonNull(longToIntFunction);
        return new IntPipeline.StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, longToIntFunction) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        getClass();
                        throw null;
                    }
                };
            }
        };
    }

    @Override
    public final Stream mapToObj(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return mapToObj(longFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    @Override
    public final OptionalLong max() {
        return reduce(new LongBinaryOperator() {
            @Override
            public final long applyAsLong(long j, long j2) {
                return Math.max(j, j2);
            }
        });
    }

    @Override
    public final OptionalLong min() {
        return reduce(new LongBinaryOperator() {
            @Override
            public final long applyAsLong(long j, long j2) {
                return Math.min(j, j2);
            }
        });
    }

    @Override
    public final boolean noneMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override
    public final LongStream peek(final LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new StatelessOp(this, StreamShape.LONG_VALUE, 0) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedLong(sink) {
                    @Override
                    public void accept(long j) {
                        longConsumer.accept(j);
                        this.downstream.accept(j);
                    }
                };
            }
        };
    }

    @Override
    public final long reduce(long j, LongBinaryOperator longBinaryOperator) {
        return ((Long) evaluate(ReduceOps.makeLong(j, longBinaryOperator))).longValue();
    }

    @Override
    public final OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        return (OptionalLong) evaluate(ReduceOps.makeLong(longBinaryOperator));
    }

    @Override
    public final LongStream skip(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            return i == 0 ? this : SliceOps.makeLong(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override
    public final LongStream sorted() {
        return SortedOps.makeLong(this);
    }

    @Override
    public final Spliterator.OfLong spliterator() {
        return adapt(super.spliterator());
    }

    @Override
    public final long sum() {
        return reduce(0L, new LongBinaryOperator() {
            @Override
            public final long applyAsLong(long j, long j2) {
                return j + j2;
            }
        });
    }

    @Override
    public final LongSummaryStatistics summaryStatistics() {
        return (LongSummaryStatistics) collect(new Supplier() {
            @Override
            public final Object get() {
                return new LongSummaryStatistics();
            }
        }, new ObjLongConsumer() {
            @Override
            public final void accept(Object obj, long j) {
                ((LongSummaryStatistics) obj).accept(j);
            }
        }, new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                ((LongSummaryStatistics) obj).combine((LongSummaryStatistics) obj2);
            }

            public BiConsumer andThen(BiConsumer biConsumer) {
                return Objects.requireNonNull(biConsumer);
            }
        });
    }

    @Override
    public final long[] toArray() {
        return (long[]) Nodes.flattenLong((Node.OfLong) evaluateToArrayNode(new IntFunction() {
            @Override
            public final Object apply(int i) {
                return LongPipeline.lambda$toArray$5(i);
            }
        })).asPrimitiveArray();
    }

    @Override
    public LongStream unordered() {
        return !isOrdered() ? this : new StatelessOp(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_ORDERED) {
            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return sink;
            }
        };
    }

    @Override
    final Spliterator wrap(PipelineHelper pipelineHelper, Supplier supplier, boolean z) {
        return new StreamSpliterators$LongWrappingSpliterator(pipelineHelper, supplier, z);
    }
}
