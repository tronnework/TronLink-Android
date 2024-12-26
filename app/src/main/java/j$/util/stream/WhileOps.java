package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import java.util.Comparator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
abstract class WhileOps {
    static final int DROP_FLAGS;
    static final int TAKE_FLAGS;

    public class 1Op extends ReferencePipeline.StatefulOp implements DropWhileOp {
        final Predicate val$predicate;

        public class 1OpSink extends Sink.ChainedReference implements DropWhileSink {
            long dropCount;
            boolean take;
            final boolean val$retainAndCountDroppedElements;
            final Sink val$sink;

            1OpSink(Sink sink, boolean z) {
                super(sink);
                this.val$sink = sink;
                this.val$retainAndCountDroppedElements = z;
            }

            @Override
            public void accept(Object obj) {
                boolean z = true;
                if (!this.take) {
                    boolean z2 = !1Op.this.val$predicate.test(obj);
                    this.take = z2;
                    if (!z2) {
                        z = false;
                    }
                }
                boolean z3 = this.val$retainAndCountDroppedElements;
                if (z3 && !z) {
                    this.dropCount++;
                }
                if (z3 || z) {
                    this.downstream.accept((Sink) obj);
                }
            }

            @Override
            public long getDropCount() {
                return this.dropCount;
            }
        }

        public 1Op(AbstractPipeline abstractPipeline, StreamShape streamShape, int i, Predicate predicate) {
            super(abstractPipeline, streamShape, i);
            this.val$predicate = predicate;
        }

        @Override
        Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            return (Node) new DropWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
        }

        @Override
        Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? opEvaluateParallel(pipelineHelper, spliterator, Nodes.castingArray()).spliterator() : new UnorderedWhileSpliterator.OfRef.Dropping(pipelineHelper.wrapSpliterator(spliterator), false, this.val$predicate);
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            return opWrapSink(sink, false);
        }

        @Override
        public DropWhileSink opWrapSink(Sink sink, boolean z) {
            return new 1OpSink(sink, z);
        }
    }

    public interface DropWhileOp {
        DropWhileSink opWrapSink(Sink sink, boolean z);
    }

    public interface DropWhileSink extends Sink {
        long getDropCount();
    }

    public static final class DropWhileTask extends AbstractTask {
        private final IntFunction generator;
        private long index;
        private final boolean isOrdered;
        private final AbstractPipeline op;
        private long thisNodeSize;

        DropWhileTask(AbstractPipeline abstractPipeline, PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags());
        }

        DropWhileTask(DropWhileTask dropWhileTask, Spliterator spliterator) {
            super(dropWhileTask, spliterator);
            this.op = dropWhileTask.op;
            this.generator = dropWhileTask.generator;
            this.isOrdered = dropWhileTask.isOrdered;
        }

        private Node doTruncate(Node node) {
            return this.isOrdered ? node.truncate(this.index, node.count(), this.generator) : node;
        }

        private Node merge() {
            AbstractTask abstractTask = this.leftChild;
            return ((DropWhileTask) abstractTask).thisNodeSize == 0 ? (Node) ((DropWhileTask) this.rightChild).getLocalResult() : ((DropWhileTask) this.rightChild).thisNodeSize == 0 ? (Node) ((DropWhileTask) abstractTask).getLocalResult() : Nodes.conc(this.op.getOutputShape(), (Node) ((DropWhileTask) this.leftChild).getLocalResult(), (Node) ((DropWhileTask) this.rightChild).getLocalResult());
        }

        @Override
        public final Node doLeaf() {
            boolean z = true;
            boolean z2 = !isRoot();
            Node.Builder makeNodeBuilder = this.helper.makeNodeBuilder((z2 && this.isOrdered && StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags)) ? this.op.exactOutputSizeIfKnown(this.spliterator) : -1L, this.generator);
            DropWhileSink opWrapSink = ((DropWhileOp) this.op).opWrapSink(makeNodeBuilder, (this.isOrdered && z2) ? false : false);
            this.helper.wrapAndCopyInto(opWrapSink, this.spliterator);
            Node build = makeNodeBuilder.build();
            this.thisNodeSize = build.count();
            this.index = opWrapSink.getDropCount();
            return build;
        }

        @Override
        public DropWhileTask makeChild(Spliterator spliterator) {
            return new DropWhileTask(this, spliterator);
        }

        @Override
        public final void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                if (this.isOrdered) {
                    AbstractTask abstractTask = this.leftChild;
                    long j = ((DropWhileTask) abstractTask).index;
                    this.index = j;
                    if (j == ((DropWhileTask) abstractTask).thisNodeSize) {
                        this.index = j + ((DropWhileTask) this.rightChild).index;
                    }
                }
                this.thisNodeSize = ((DropWhileTask) this.leftChild).thisNodeSize + ((DropWhileTask) this.rightChild).thisNodeSize;
                Node merge = merge();
                if (isRoot()) {
                    merge = doTruncate(merge);
                }
                setLocalResult(merge);
            }
            super.onCompletion(countedCompleter);
        }
    }

    public static final class TakeWhileTask extends AbstractShortCircuitTask {
        private volatile boolean completed;
        private final IntFunction generator;
        private final boolean isOrdered;
        private final AbstractPipeline op;
        private boolean shortCircuited;
        private long thisNodeSize;

        TakeWhileTask(AbstractPipeline abstractPipeline, PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags());
        }

        TakeWhileTask(TakeWhileTask takeWhileTask, Spliterator spliterator) {
            super(takeWhileTask, spliterator);
            this.op = takeWhileTask.op;
            this.generator = takeWhileTask.generator;
            this.isOrdered = takeWhileTask.isOrdered;
        }

        @Override
        public void cancel() {
            super.cancel();
            if (this.isOrdered && this.completed) {
                setLocalResult(getEmptyResult());
            }
        }

        @Override
        public final Node doLeaf() {
            Node.Builder makeNodeBuilder = this.helper.makeNodeBuilder(-1L, this.generator);
            Sink opWrapSink = this.op.opWrapSink(this.helper.getStreamAndOpFlags(), makeNodeBuilder);
            PipelineHelper pipelineHelper = this.helper;
            boolean copyIntoWithCancel = pipelineHelper.copyIntoWithCancel(pipelineHelper.wrapSink(opWrapSink), this.spliterator);
            this.shortCircuited = copyIntoWithCancel;
            if (copyIntoWithCancel) {
                cancelLaterNodes();
            }
            Node build = makeNodeBuilder.build();
            this.thisNodeSize = build.count();
            return build;
        }

        @Override
        public final Node getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        @Override
        public TakeWhileTask makeChild(Spliterator spliterator) {
            return new TakeWhileTask(this, spliterator);
        }

        Node merge() {
            AbstractTask abstractTask = this.leftChild;
            return ((TakeWhileTask) abstractTask).thisNodeSize == 0 ? (Node) ((TakeWhileTask) this.rightChild).getLocalResult() : ((TakeWhileTask) this.rightChild).thisNodeSize == 0 ? (Node) ((TakeWhileTask) abstractTask).getLocalResult() : Nodes.conc(this.op.getOutputShape(), (Node) ((TakeWhileTask) this.leftChild).getLocalResult(), (Node) ((TakeWhileTask) this.rightChild).getLocalResult());
        }

        @Override
        public final void onCompletion(CountedCompleter countedCompleter) {
            Node merge;
            if (!isLeaf()) {
                this.shortCircuited = ((TakeWhileTask) this.leftChild).shortCircuited | ((TakeWhileTask) this.rightChild).shortCircuited;
                if (this.isOrdered && this.canceled) {
                    this.thisNodeSize = 0L;
                    merge = getEmptyResult();
                } else {
                    if (this.isOrdered) {
                        AbstractTask abstractTask = this.leftChild;
                        if (((TakeWhileTask) abstractTask).shortCircuited) {
                            this.thisNodeSize = ((TakeWhileTask) abstractTask).thisNodeSize;
                            merge = (Node) ((TakeWhileTask) abstractTask).getLocalResult();
                        }
                    }
                    this.thisNodeSize = ((TakeWhileTask) this.leftChild).thisNodeSize + ((TakeWhileTask) this.rightChild).thisNodeSize;
                    merge = merge();
                }
                setLocalResult(merge);
            }
            this.completed = true;
            super.onCompletion(countedCompleter);
        }
    }

    static abstract class UnorderedWhileSpliterator implements Spliterator {
        final AtomicBoolean cancel;
        int count;
        final boolean noSplitting;
        final Spliterator s;
        boolean takeOrDrop;

        static abstract class OfRef extends UnorderedWhileSpliterator implements Consumer {
            final Predicate p;
            Object t;

            static final class Dropping extends OfRef {
                Dropping(Spliterator spliterator, Dropping dropping) {
                    super(spliterator, dropping);
                }

                Dropping(Spliterator spliterator, boolean z, Predicate predicate) {
                    super(spliterator, z, predicate);
                }

                @Override
                Spliterator makeSpliterator(Spliterator spliterator) {
                    return new Dropping(spliterator, this);
                }

                @Override
                public boolean tryAdvance(java.util.function.Consumer r6) {
                    


return true;

//throw new UnsupportedOperationException(
Method not decompiled: j$.util.stream.WhileOps.UnorderedWhileSpliterator.OfRef.Dropping.tryAdvance(java.util.function.Consumer):boolean");
                }
            }

            static final class Taking extends OfRef {
                Taking(Spliterator spliterator, Taking taking) {
                    super(spliterator, taking);
                }

                Taking(Spliterator spliterator, boolean z, Predicate predicate) {
                    super(spliterator, z, predicate);
                }

                @Override
                Spliterator makeSpliterator(Spliterator spliterator) {
                    return new Taking(spliterator, this);
                }

                @Override
                public boolean tryAdvance(Consumer consumer) {
                    boolean z;
                    if (this.takeOrDrop && checkCancelOnCount() && this.s.tryAdvance(this)) {
                        z = this.p.test(this.t);
                        if (z) {
                            consumer.accept(this.t);
                            return true;
                        }
                    } else {
                        z = true;
                    }
                    this.takeOrDrop = false;
                    if (!z) {
                        this.cancel.set(true);
                    }
                    return false;
                }

                @Override
                public Spliterator trySplit() {
                    if (this.cancel.get()) {
                        return null;
                    }
                    return super.trySplit();
                }
            }

            OfRef(Spliterator spliterator, OfRef ofRef) {
                super(spliterator, ofRef);
                this.p = ofRef.p;
            }

            OfRef(Spliterator spliterator, boolean z, Predicate predicate) {
                super(spliterator, z);
                this.p = predicate;
            }

            @Override
            public void accept(Object obj) {
                this.count = (this.count + 1) & 63;
                this.t = obj;
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        }

        UnorderedWhileSpliterator(Spliterator spliterator, UnorderedWhileSpliterator unorderedWhileSpliterator) {
            this.takeOrDrop = true;
            this.s = spliterator;
            this.noSplitting = unorderedWhileSpliterator.noSplitting;
            this.cancel = unorderedWhileSpliterator.cancel;
        }

        UnorderedWhileSpliterator(Spliterator spliterator, boolean z) {
            this.takeOrDrop = true;
            this.s = spliterator;
            this.noSplitting = z;
            this.cancel = new AtomicBoolean();
        }

        @Override
        public int characteristics() {
            return this.s.characteristics() & (-16449);
        }

        boolean checkCancelOnCount() {
            return (this.count == 0 && this.cancel.get()) ? false : true;
        }

        @Override
        public long estimateSize() {
            return this.s.estimateSize();
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            Spliterator.-CC.$default$forEachRemaining(this, consumer);
        }

        @Override
        public Comparator getComparator() {
            return this.s.getComparator();
        }

        @Override
        public long getExactSizeIfKnown() {
            return -1L;
        }

        @Override
        public boolean hasCharacteristics(int i) {
            return Spliterator.-CC.$default$hasCharacteristics(this, i);
        }

        abstract Spliterator makeSpliterator(Spliterator spliterator);

        @Override
        public Spliterator trySplit() {
            Spliterator trySplit = this.noSplitting ? null : this.s.trySplit();
            if (trySplit != null) {
                return makeSpliterator(trySplit);
            }
            return null;
        }
    }

    static {
        int i = StreamOpFlag.NOT_SIZED;
        TAKE_FLAGS = StreamOpFlag.IS_SHORT_CIRCUIT | i;
        DROP_FLAGS = i;
    }

    public static Stream makeDropWhileRef(AbstractPipeline abstractPipeline, Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new 1Op(abstractPipeline, StreamShape.REFERENCE, DROP_FLAGS, predicate);
    }

    public static Stream makeTakeWhileRef(AbstractPipeline abstractPipeline, final Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new ReferencePipeline.StatefulOp(abstractPipeline, StreamShape.REFERENCE, TAKE_FLAGS) {
            @Override
            Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                return (Node) new TakeWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            @Override
            Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? opEvaluateParallel(pipelineHelper, spliterator, Nodes.castingArray()).spliterator() : new UnorderedWhileSpliterator.OfRef.Taking(pipelineHelper.wrapSpliterator(spliterator), false, predicate);
            }

            @Override
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    boolean take = true;

                    @Override
                    public void accept(Object obj) {
                        if (this.take) {
                            boolean test = predicate.test(obj);
                            this.take = test;
                            if (test) {
                                this.downstream.accept((Sink) obj);
                            }
                        }
                    }

                    @Override
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    @Override
                    public boolean cancellationRequested() {
                        return !this.take || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }
}
