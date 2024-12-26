package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.SliceOps;
import j$.util.stream.StreamSpliterators$SliceSpliterator;
import j$.util.stream.StreamSpliterators$UnorderedSliceSpliterator;
import java.util.function.IntFunction;
abstract class SliceOps {

    public class fun2 extends IntPipeline.StatefulOp {
        final long val$limit;
        final long val$skip;

        fun2(AbstractPipeline abstractPipeline, StreamShape streamShape, int i, long j, long j2) {
            super(abstractPipeline, streamShape, i);
            this.val$skip = j;
            this.val$limit = j2;
        }

        public static Integer[] lambda$opEvaluateParallelLazy$0(int i) {
            return new Integer[i];
        }

        @Override
        Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                return Nodes.collectInt(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, this.val$skip, this.val$limit), true);
            }
            return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Nodes.collectInt(this, unorderedSkipLimitSpliterator((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), this.val$skip, this.val$limit, exactOutputSizeIfKnown), true) : (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, this.val$skip, this.val$limit).invoke();
        }

        @Override
        Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
            long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                long j = this.val$skip;
                return new StreamSpliterators$SliceSpliterator.OfInt((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, this.val$limit));
            }
            return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? unorderedSkipLimitSpliterator((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), this.val$skip, this.val$limit, exactOutputSizeIfKnown) : ((Node) new SliceTask(this, pipelineHelper, spliterator, new IntFunction() {
                @Override
                public final Object apply(int i) {
                    return SliceOps.2.lambda$opEvaluateParallelLazy$0(i);
                }
            }, this.val$skip, this.val$limit).invoke()).spliterator();
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            return new Sink.ChainedInt(sink) {
                long m;
                long n;

                {
                    this.n = fun2.this.val$skip;
                    long j = fun2.this.val$limit;
                    this.m = j < 0 ? Long.MAX_VALUE : j;
                }

                @Override
                public void accept(int i2) {
                    long j = this.n;
                    if (j != 0) {
                        this.n = j - 1;
                        return;
                    }
                    long j2 = this.m;
                    if (j2 > 0) {
                        this.m = j2 - 1;
                        this.downstream.accept(i2);
                    }
                }

                @Override
                public void begin(long j) {
                    this.downstream.begin(SliceOps.calcSize(j, fun2.this.val$skip, this.m));
                }

                @Override
                public boolean cancellationRequested() {
                    return this.m == 0 || this.downstream.cancellationRequested();
                }
            };
        }

        Spliterator.OfInt unorderedSkipLimitSpliterator(Spliterator.OfInt ofInt, long j, long j2, long j3) {
            long j4;
            long j5;
            if (j <= j3) {
                long j6 = j3 - j;
                j5 = j2 >= 0 ? Math.min(j2, j6) : j6;
                j4 = 0;
            } else {
                j4 = j;
                j5 = j2;
            }
            return new StreamSpliterators$UnorderedSliceSpliterator.OfInt(ofInt, j4, j5);
        }
    }

    public class fun3 extends LongPipeline.StatefulOp {
        final long val$limit;
        final long val$skip;

        fun3(AbstractPipeline abstractPipeline, StreamShape streamShape, int i, long j, long j2) {
            super(abstractPipeline, streamShape, i);
            this.val$skip = j;
            this.val$limit = j2;
        }

        public static Long[] lambda$opEvaluateParallelLazy$0(int i) {
            return new Long[i];
        }

        @Override
        Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                return Nodes.collectLong(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, this.val$skip, this.val$limit), true);
            }
            return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Nodes.collectLong(this, unorderedSkipLimitSpliterator((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), this.val$skip, this.val$limit, exactOutputSizeIfKnown), true) : (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, this.val$skip, this.val$limit).invoke();
        }

        @Override
        Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
            long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                long j = this.val$skip;
                return new StreamSpliterators$SliceSpliterator.OfLong((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, this.val$limit));
            }
            return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? unorderedSkipLimitSpliterator((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), this.val$skip, this.val$limit, exactOutputSizeIfKnown) : ((Node) new SliceTask(this, pipelineHelper, spliterator, new IntFunction() {
                @Override
                public final Object apply(int i) {
                    return SliceOps.3.lambda$opEvaluateParallelLazy$0(i);
                }
            }, this.val$skip, this.val$limit).invoke()).spliterator();
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            return new Sink.ChainedLong(sink) {
                long m;
                long n;

                {
                    this.n = fun3.this.val$skip;
                    long j = fun3.this.val$limit;
                    this.m = j < 0 ? Long.MAX_VALUE : j;
                }

                @Override
                public void accept(long j) {
                    long j2 = this.n;
                    if (j2 != 0) {
                        this.n = j2 - 1;
                        return;
                    }
                    long j3 = this.m;
                    if (j3 > 0) {
                        this.m = j3 - 1;
                        this.downstream.accept(j);
                    }
                }

                @Override
                public void begin(long j) {
                    this.downstream.begin(SliceOps.calcSize(j, fun3.this.val$skip, this.m));
                }

                @Override
                public boolean cancellationRequested() {
                    return this.m == 0 || this.downstream.cancellationRequested();
                }
            };
        }

        Spliterator.OfLong unorderedSkipLimitSpliterator(Spliterator.OfLong ofLong, long j, long j2, long j3) {
            long j4;
            long j5;
            if (j <= j3) {
                long j6 = j3 - j;
                j5 = j2 >= 0 ? Math.min(j2, j6) : j6;
                j4 = 0;
            } else {
                j4 = j;
                j5 = j2;
            }
            return new StreamSpliterators$UnorderedSliceSpliterator.OfLong(ofLong, j4, j5);
        }
    }

    public class fun4 extends DoublePipeline.StatefulOp {
        final long val$limit;
        final long val$skip;

        fun4(AbstractPipeline abstractPipeline, StreamShape streamShape, int i, long j, long j2) {
            super(abstractPipeline, streamShape, i);
            this.val$skip = j;
            this.val$limit = j2;
        }

        public static Double[] lambda$opEvaluateParallelLazy$0(int i) {
            return new Double[i];
        }

        @Override
        Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                return Nodes.collectDouble(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, this.val$skip, this.val$limit), true);
            }
            return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Nodes.collectDouble(this, unorderedSkipLimitSpliterator((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), this.val$skip, this.val$limit, exactOutputSizeIfKnown), true) : (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, this.val$skip, this.val$limit).invoke();
        }

        @Override
        Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
            long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
            if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                long j = this.val$skip;
                return new StreamSpliterators$SliceSpliterator.OfDouble((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, this.val$limit));
            }
            return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? unorderedSkipLimitSpliterator((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), this.val$skip, this.val$limit, exactOutputSizeIfKnown) : ((Node) new SliceTask(this, pipelineHelper, spliterator, new IntFunction() {
                @Override
                public final Object apply(int i) {
                    return SliceOps.4.lambda$opEvaluateParallelLazy$0(i);
                }
            }, this.val$skip, this.val$limit).invoke()).spliterator();
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            return new Sink.ChainedDouble(sink) {
                long m;
                long n;

                {
                    this.n = fun4.this.val$skip;
                    long j = fun4.this.val$limit;
                    this.m = j < 0 ? Long.MAX_VALUE : j;
                }

                @Override
                public void accept(double d) {
                    long j = this.n;
                    if (j != 0) {
                        this.n = j - 1;
                        return;
                    }
                    long j2 = this.m;
                    if (j2 > 0) {
                        this.m = j2 - 1;
                        this.downstream.accept(d);
                    }
                }

                @Override
                public void begin(long j) {
                    this.downstream.begin(SliceOps.calcSize(j, fun4.this.val$skip, this.m));
                }

                @Override
                public boolean cancellationRequested() {
                    return this.m == 0 || this.downstream.cancellationRequested();
                }
            };
        }

        Spliterator.OfDouble unorderedSkipLimitSpliterator(Spliterator.OfDouble ofDouble, long j, long j2, long j3) {
            long j4;
            long j5;
            if (j <= j3) {
                long j6 = j3 - j;
                j5 = j2 >= 0 ? Math.min(j2, j6) : j6;
                j4 = 0;
            } else {
                j4 = j;
                j5 = j2;
            }
            return new StreamSpliterators$UnorderedSliceSpliterator.OfDouble(ofDouble, j4, j5);
        }
    }

    public static class fun5 {
        static final int[] $SwitchMap$java$util$stream$StreamShape;

        static {
            int[] iArr = new int[StreamShape.values().length];
            $SwitchMap$java$util$stream$StreamShape = iArr;
            try {
                iArr[StreamShape.REFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.INT_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.LONG_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static final class SliceTask extends AbstractShortCircuitTask {
        private volatile boolean completed;
        private final IntFunction generator;
        private final AbstractPipeline op;
        private final long targetOffset;
        private final long targetSize;
        private long thisNodeSize;

        SliceTask(AbstractPipeline abstractPipeline, PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction, long j, long j2) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.targetOffset = j;
            this.targetSize = j2;
        }

        SliceTask(SliceTask sliceTask, Spliterator spliterator) {
            super(sliceTask, spliterator);
            this.op = sliceTask.op;
            this.generator = sliceTask.generator;
            this.targetOffset = sliceTask.targetOffset;
            this.targetSize = sliceTask.targetSize;
        }

        private long completedSize(long j) {
            if (this.completed) {
                return this.thisNodeSize;
            }
            SliceTask sliceTask = (SliceTask) this.leftChild;
            SliceTask sliceTask2 = (SliceTask) this.rightChild;
            if (sliceTask == null || sliceTask2 == null) {
                return this.thisNodeSize;
            }
            long completedSize = sliceTask.completedSize(j);
            return completedSize >= j ? completedSize : completedSize + sliceTask2.completedSize(j);
        }

        private Node doTruncate(Node node) {
            return node.truncate(this.targetOffset, this.targetSize >= 0 ? Math.min(node.count(), this.targetOffset + this.targetSize) : this.thisNodeSize, this.generator);
        }

        private boolean isLeftCompleted(long j) {
            SliceTask sliceTask;
            long completedSize = this.completed ? this.thisNodeSize : completedSize(j);
            if (completedSize >= j) {
                return true;
            }
            SliceTask sliceTask2 = this;
            for (SliceTask sliceTask3 = (SliceTask) getParent(); sliceTask3 != null; sliceTask3 = (SliceTask) sliceTask3.getParent()) {
                if (sliceTask2 == sliceTask3.rightChild && (sliceTask = (SliceTask) sliceTask3.leftChild) != null) {
                    completedSize += sliceTask.completedSize(j);
                    if (completedSize >= j) {
                        return true;
                    }
                }
                sliceTask2 = sliceTask3;
            }
            return completedSize >= j;
        }

        @Override
        public void cancel() {
            super.cancel();
            if (this.completed) {
                setLocalResult(getEmptyResult());
            }
        }

        @Override
        public final Node doLeaf() {
            if (isRoot()) {
                Node.Builder makeNodeBuilder = this.op.makeNodeBuilder(StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags) ? this.op.exactOutputSizeIfKnown(this.spliterator) : -1L, this.generator);
                Sink opWrapSink = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder);
                PipelineHelper pipelineHelper = this.helper;
                pipelineHelper.copyIntoWithCancel(pipelineHelper.wrapSink(opWrapSink), this.spliterator);
                return makeNodeBuilder.build();
            }
            Node.Builder makeNodeBuilder2 = this.op.makeNodeBuilder(-1L, this.generator);
            if (this.targetOffset == 0) {
                Sink opWrapSink2 = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder2);
                PipelineHelper pipelineHelper2 = this.helper;
                pipelineHelper2.copyIntoWithCancel(pipelineHelper2.wrapSink(opWrapSink2), this.spliterator);
            } else {
                this.helper.wrapAndCopyInto(makeNodeBuilder2, this.spliterator);
            }
            Node build = makeNodeBuilder2.build();
            this.thisNodeSize = build.count();
            this.completed = true;
            this.spliterator = null;
            return build;
        }

        @Override
        public final Node getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        @Override
        public SliceTask makeChild(Spliterator spliterator) {
            return new SliceTask(this, spliterator);
        }

        @Override
        public final void onCompletion(java.util.concurrent.CountedCompleter r8) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: j$.util.stream.SliceOps.SliceTask.onCompletion(java.util.concurrent.CountedCompleter):void");
        }
    }

    public static long calcSize(long j, long j2, long j3) {
        if (j >= 0) {
            return Math.max(-1L, Math.min(j - j2, j3));
        }
        return -1L;
    }

    public static long calcSliceFence(long j, long j2) {
        long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
        if (j3 >= 0) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    private static int flags(long j) {
        return (j != -1 ? StreamOpFlag.IS_SHORT_CIRCUIT : 0) | StreamOpFlag.NOT_SIZED;
    }

    public static DoubleStream makeDouble(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            return new fun4(abstractPipeline, StreamShape.DOUBLE_VALUE, flags(j2), j, j2);
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static IntStream makeInt(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            return new fun2(abstractPipeline, StreamShape.INT_VALUE, flags(j2), j, j2);
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static LongStream makeLong(AbstractPipeline abstractPipeline, long j, long j2) {
        if (j >= 0) {
            return new fun3(abstractPipeline, StreamShape.LONG_VALUE, flags(j2), j, j2);
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static Stream makeRef(AbstractPipeline abstractPipeline, final long j, final long j2) {
        if (j >= 0) {
            return new ReferencePipeline.StatefulOp(abstractPipeline, StreamShape.REFERENCE, flags(j2)) {
                @Override
                Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        return Nodes.collect(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j, j2), true, intFunction);
                    }
                    return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? Nodes.collect(this, unorderedSkipLimitSpliterator(pipelineHelper.wrapSpliterator(spliterator), j, j2, exactOutputSizeIfKnown), true, intFunction) : (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j, j2).invoke();
                }

                @Override
                Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                    long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                    if (exactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                        Spliterator wrapSpliterator = pipelineHelper.wrapSpliterator(spliterator);
                        long j3 = j;
                        return new StreamSpliterators$SliceSpliterator.OfRef(wrapSpliterator, j3, SliceOps.calcSliceFence(j3, j2));
                    }
                    return !StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? unorderedSkipLimitSpliterator(pipelineHelper.wrapSpliterator(spliterator), j, j2, exactOutputSizeIfKnown) : ((Node) new SliceTask(this, pipelineHelper, spliterator, Nodes.castingArray(), j, j2).invoke()).spliterator();
                }

                @Override
                public Sink opWrapSink(int i, Sink sink) {
                    return new Sink.ChainedReference(sink) {
                        long m;
                        long n;

                        {
                            this.n = j;
                            long j3 = j2;
                            this.m = j3 < 0 ? Long.MAX_VALUE : j3;
                        }

                        @Override
                        public void accept(Object obj) {
                            long j3 = this.n;
                            if (j3 != 0) {
                                this.n = j3 - 1;
                                return;
                            }
                            long j4 = this.m;
                            if (j4 > 0) {
                                this.m = j4 - 1;
                                this.downstream.accept((Sink) obj);
                            }
                        }

                        @Override
                        public void begin(long j3) {
                            this.downstream.begin(SliceOps.calcSize(j3, j, this.m));
                        }

                        @Override
                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }

                Spliterator unorderedSkipLimitSpliterator(Spliterator spliterator, long j3, long j4, long j5) {
                    long j6;
                    long j7;
                    if (j3 <= j5) {
                        long j8 = j5 - j3;
                        j7 = j4 >= 0 ? Math.min(j4, j8) : j8;
                        j6 = 0;
                    } else {
                        j6 = j3;
                        j7 = j4;
                    }
                    return new StreamSpliterators$UnorderedSliceSpliterator.OfRef(spliterator, j6, j7);
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + j);
    }

    public static Spliterator sliceSpliterator(StreamShape streamShape, Spliterator spliterator, long j, long j2) {
        long calcSliceFence = calcSliceFence(j, j2);
        int i = fun5.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return new StreamSpliterators$SliceSpliterator.OfDouble((Spliterator.OfDouble) spliterator, j, calcSliceFence);
                    }
                    throw new IllegalStateException("Unknown shape " + streamShape);
                }
                return new StreamSpliterators$SliceSpliterator.OfLong((Spliterator.OfLong) spliterator, j, calcSliceFence);
            }
            return new StreamSpliterators$SliceSpliterator.OfInt((Spliterator.OfInt) spliterator, j, calcSliceFence);
        }
        return new StreamSpliterators$SliceSpliterator.OfRef(spliterator, j, calcSliceFence);
    }
}
