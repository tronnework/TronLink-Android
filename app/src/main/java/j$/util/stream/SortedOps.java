package j$.util.stream;

import j$.util.Collection;
import j$.util.Comparator;
import j$.util.List$-CC;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.stream.DoublePipeline;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
abstract class SortedOps {

    private static abstract class AbstractDoubleSortingSink extends Sink.ChainedDouble {
        protected boolean cancellationRequestedCalled;

        AbstractDoubleSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static abstract class AbstractIntSortingSink extends Sink.ChainedInt {
        protected boolean cancellationRequestedCalled;

        AbstractIntSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static abstract class AbstractLongSortingSink extends Sink.ChainedLong {
        protected boolean cancellationRequestedCalled;

        AbstractLongSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static abstract class AbstractRefSortingSink extends Sink.ChainedReference {
        protected boolean cancellationRequestedCalled;
        protected final Comparator comparator;

        AbstractRefSortingSink(Sink sink, Comparator comparator) {
            super(sink);
            this.comparator = comparator;
        }

        @Override
        public final boolean cancellationRequested() {
            this.cancellationRequestedCalled = true;
            return false;
        }
    }

    private static final class DoubleSortingSink extends AbstractDoubleSortingSink {
        private SpinedBuffer.OfDouble b;

        DoubleSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public void accept(double d) {
            this.b.accept(d);
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.b = j > 0 ? new SpinedBuffer.OfDouble((int) j) : new SpinedBuffer.OfDouble();
        }

        @Override
        public void end() {
            double[] dArr = (double[]) this.b.asPrimitiveArray();
            Arrays.sort(dArr);
            this.downstream.begin(dArr.length);
            int i = 0;
            if (this.cancellationRequestedCalled) {
                int length = dArr.length;
                while (i < length) {
                    double d = dArr[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(d);
                    i++;
                }
            } else {
                int length2 = dArr.length;
                while (i < length2) {
                    this.downstream.accept(dArr[i]);
                    i++;
                }
            }
            this.downstream.end();
        }
    }

    private static final class IntSortingSink extends AbstractIntSortingSink {
        private SpinedBuffer.OfInt b;

        IntSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public void accept(int i) {
            this.b.accept(i);
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.b = j > 0 ? new SpinedBuffer.OfInt((int) j) : new SpinedBuffer.OfInt();
        }

        @Override
        public void end() {
            int[] iArr = (int[]) this.b.asPrimitiveArray();
            Arrays.sort(iArr);
            this.downstream.begin(iArr.length);
            int i = 0;
            if (this.cancellationRequestedCalled) {
                int length = iArr.length;
                while (i < length) {
                    int i2 = iArr[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(i2);
                    i++;
                }
            } else {
                int length2 = iArr.length;
                while (i < length2) {
                    this.downstream.accept(iArr[i]);
                    i++;
                }
            }
            this.downstream.end();
        }
    }

    private static final class LongSortingSink extends AbstractLongSortingSink {
        private SpinedBuffer.OfLong b;

        LongSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public void accept(long j) {
            this.b.accept(j);
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.b = j > 0 ? new SpinedBuffer.OfLong((int) j) : new SpinedBuffer.OfLong();
        }

        @Override
        public void end() {
            long[] jArr = (long[]) this.b.asPrimitiveArray();
            Arrays.sort(jArr);
            this.downstream.begin(jArr.length);
            int i = 0;
            if (this.cancellationRequestedCalled) {
                int length = jArr.length;
                while (i < length) {
                    long j = jArr[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(j);
                    i++;
                }
            } else {
                int length2 = jArr.length;
                while (i < length2) {
                    this.downstream.accept(jArr[i]);
                    i++;
                }
            }
            this.downstream.end();
        }
    }

    public static final class OfDouble extends DoublePipeline.StatefulOp {
        OfDouble(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.DOUBLE_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        @Override
        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            double[] dArr = (double[]) ((Node.OfDouble) pipelineHelper.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
            Arrays.sort(dArr);
            return Nodes.node(dArr);
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedDoubleSortingSink(sink) : new DoubleSortingSink(sink);
        }
    }

    public static final class OfInt extends IntPipeline.StatefulOp {
        OfInt(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.INT_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        @Override
        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            int[] iArr = (int[]) ((Node.OfInt) pipelineHelper.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
            Arrays.sort(iArr);
            return Nodes.node(iArr);
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedIntSortingSink(sink) : new IntSortingSink(sink);
        }
    }

    public static final class OfLong extends LongPipeline.StatefulOp {
        OfLong(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.LONG_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        @Override
        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            long[] jArr = (long[]) ((Node.OfLong) pipelineHelper.evaluate(spliterator, true, intFunction)).asPrimitiveArray();
            Arrays.sort(jArr);
            return Nodes.node(jArr);
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return StreamOpFlag.SORTED.isKnown(i) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedLongSortingSink(sink) : new LongSortingSink(sink);
        }
    }

    public static final class OfRef extends ReferencePipeline.StatefulOp {
        private final Comparator comparator;
        private final boolean isNaturalSort;

        OfRef(AbstractPipeline abstractPipeline) {
            super(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
            this.isNaturalSort = true;
            this.comparator = Comparator.-CC.naturalOrder();
        }

        OfRef(AbstractPipeline abstractPipeline, java.util.Comparator comparator) {
            super(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.NOT_SORTED);
            this.isNaturalSort = false;
            this.comparator = (java.util.Comparator) Objects.requireNonNull(comparator);
        }

        @Override
        public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
            if (StreamOpFlag.SORTED.isKnown(pipelineHelper.getStreamAndOpFlags()) && this.isNaturalSort) {
                return pipelineHelper.evaluate(spliterator, false, intFunction);
            }
            Object[] asArray = pipelineHelper.evaluate(spliterator, true, intFunction).asArray(intFunction);
            Arrays.sort(asArray, this.comparator);
            return Nodes.node(asArray);
        }

        @Override
        public Sink opWrapSink(int i, Sink sink) {
            Objects.requireNonNull(sink);
            return (StreamOpFlag.SORTED.isKnown(i) && this.isNaturalSort) ? sink : StreamOpFlag.SIZED.isKnown(i) ? new SizedRefSortingSink(sink, this.comparator) : new RefSortingSink(sink, this.comparator);
        }
    }

    private static final class RefSortingSink extends AbstractRefSortingSink {
        private ArrayList list;

        RefSortingSink(Sink sink, java.util.Comparator comparator) {
            super(sink, comparator);
        }

        @Override
        public void accept(Object obj) {
            this.list.add(obj);
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.list = j >= 0 ? new ArrayList((int) j) : new ArrayList();
        }

        @Override
        public void end() {
            List$-CC.$default$sort(this.list, this.comparator);
            this.downstream.begin(this.list.size());
            if (this.cancellationRequestedCalled) {
                Iterator it = this.list.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept((Sink) next);
                }
            } else {
                ArrayList arrayList = this.list;
                final Sink sink = this.downstream;
                Objects.requireNonNull(sink);
                Collection.-EL.forEach(arrayList, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        Sink.this.accept((Sink) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }
            this.downstream.end();
            this.list = null;
        }
    }

    private static final class SizedDoubleSortingSink extends AbstractDoubleSortingSink {
        private double[] array;
        private int offset;

        SizedDoubleSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public void accept(double d) {
            double[] dArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            dArr[i] = d;
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new double[(int) j];
        }

        @Override
        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin(this.offset);
            if (this.cancellationRequestedCalled) {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    private static final class SizedIntSortingSink extends AbstractIntSortingSink {
        private int[] array;
        private int offset;

        SizedIntSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public void accept(int i) {
            int[] iArr = this.array;
            int i2 = this.offset;
            this.offset = i2 + 1;
            iArr[i2] = i;
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new int[(int) j];
        }

        @Override
        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin(this.offset);
            if (this.cancellationRequestedCalled) {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    private static final class SizedLongSortingSink extends AbstractLongSortingSink {
        private long[] array;
        private int offset;

        SizedLongSortingSink(Sink sink) {
            super(sink);
        }

        @Override
        public void accept(long j) {
            long[] jArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            jArr[i] = j;
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new long[(int) j];
        }

        @Override
        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin(this.offset);
            if (this.cancellationRequestedCalled) {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset) {
                    this.downstream.accept(this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    private static final class SizedRefSortingSink extends AbstractRefSortingSink {
        private Object[] array;
        private int offset;

        SizedRefSortingSink(Sink sink, java.util.Comparator comparator) {
            super(sink, comparator);
        }

        @Override
        public void accept(Object obj) {
            Object[] objArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            objArr[i] = obj;
        }

        @Override
        public void begin(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new Object[(int) j];
        }

        @Override
        public void end() {
            int i = 0;
            Arrays.sort(this.array, 0, this.offset, this.comparator);
            this.downstream.begin(this.offset);
            if (this.cancellationRequestedCalled) {
                while (i < this.offset && !this.downstream.cancellationRequested()) {
                    this.downstream.accept((Sink) this.array[i]);
                    i++;
                }
            } else {
                while (i < this.offset) {
                    this.downstream.accept((Sink) this.array[i]);
                    i++;
                }
            }
            this.downstream.end();
            this.array = null;
        }
    }

    public static DoubleStream makeDouble(AbstractPipeline abstractPipeline) {
        return new OfDouble(abstractPipeline);
    }

    public static IntStream makeInt(AbstractPipeline abstractPipeline) {
        return new OfInt(abstractPipeline);
    }

    public static LongStream makeLong(AbstractPipeline abstractPipeline) {
        return new OfLong(abstractPipeline);
    }

    public static Stream makeRef(AbstractPipeline abstractPipeline) {
        return new OfRef(abstractPipeline);
    }

    public static Stream makeRef(AbstractPipeline abstractPipeline, java.util.Comparator comparator) {
        return new OfRef(abstractPipeline, comparator);
    }
}
