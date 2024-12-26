package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.stream.DistinctOps;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
abstract class DistinctOps {

    public class fun1 extends ReferencePipeline.StatefulOp {
        fun1(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, streamShape, i);
        }

        public static void lambda$opEvaluateParallel$0(AtomicBoolean atomicBoolean, ConcurrentHashMap concurrentHashMap, Object obj) {
            if (obj == null) {
                atomicBoolean.set(true);
            } else {
                concurrentHashMap.putIfAbsent(obj, Boolean.TRUE);
            }
        }

        @Override
        Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.DISTINCT.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return reduce(pipelineHelper, spliterator);
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            ForEachOps.makeRef(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    DistinctOps.1.lambda$opEvaluateParallel$0(atomicBoolean, concurrentHashMap, obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            }, false).evaluateParallel(pipelineHelper, spliterator);
            Collection keySet = concurrentHashMap.keySet();
            if (atomicBoolean.get()) {
                HashSet hashSet = new HashSet(keySet);
                hashSet.add(null);
                keySet = hashSet;
            }
            return Nodes.node(keySet);
        }

        @Override
        Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return StreamOpFlag.DISTINCT.isKnown(pipelineHelper.getStreamAndOpFlags()) ? pipelineHelper.wrapSpliterator(spliterator) : StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? reduce(pipelineHelper, spliterator).spliterator() : new StreamSpliterators$DistinctSpliterator(pipelineHelper.wrapSpliterator(spliterator));
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.DISTINCT.isKnown(i) ? sink : StreamOpFlag.SORTED.isKnown(i) ? new Sink.ChainedReference(sink) {
                Object lastSeen;
                boolean seenNull;

                @Override
                public void accept(Object obj) {
                    if (obj == null) {
                        if (this.seenNull) {
                            return;
                        }
                        this.seenNull = true;
                        Sink sink2 = this.downstream;
                        this.lastSeen = null;
                        sink2.accept((Sink) null);
                        return;
                    }
                    Object obj2 = this.lastSeen;
                    if (obj2 == null || !obj.equals(obj2)) {
                        Sink sink3 = this.downstream;
                        this.lastSeen = obj;
                        sink3.accept((Sink) obj);
                    }
                }

                @Override
                public void begin(long j) {
                    this.seenNull = false;
                    this.lastSeen = null;
                    this.downstream.begin(-1L);
                }

                @Override
                public void end() {
                    this.seenNull = false;
                    this.lastSeen = null;
                    this.downstream.end();
                }
            } : new Sink.ChainedReference(sink) {
                Set seen;

                @Override
                public void accept(Object obj) {
                    if (this.seen.contains(obj)) {
                        return;
                    }
                    this.seen.add(obj);
                    this.downstream.accept((Sink) obj);
                }

                @Override
                public void begin(long j) {
                    this.seen = new HashSet();
                    this.downstream.begin(-1L);
                }

                @Override
                public void end() {
                    this.seen = null;
                    this.downstream.end();
                }
            };
        }

        Node reduce(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return Nodes.node((Collection) ReduceOps.makeRef(new Supplier() {
                @Override
                public final Object get() {
                    return new LinkedHashSet();
                }
            }, new BiConsumer() {
                @Override
                public final void accept(Object obj, Object obj2) {
                    ((LinkedHashSet) obj).add(obj2);
                }

                public BiConsumer andThen(BiConsumer biConsumer) {
                    return Objects.requireNonNull(biConsumer);
                }
            }, new BiConsumer() {
                @Override
                public final void accept(Object obj, Object obj2) {
                    ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
                }

                public BiConsumer andThen(BiConsumer biConsumer) {
                    return Objects.requireNonNull(biConsumer);
                }
            }).evaluateParallel(pipelineHelper, spliterator));
        }
    }

    public static ReferencePipeline makeRef(AbstractPipeline abstractPipeline) {
        return new fun1(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_DISTINCT | StreamOpFlag.NOT_SIZED);
    }
}
