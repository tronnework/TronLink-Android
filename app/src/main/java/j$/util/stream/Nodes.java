package j$.util.stream;

import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Node;
import j$.util.stream.Nodes;
import j$.util.stream.Sink;
import j$.util.stream.SpinedBuffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.concurrent.CountedCompleter;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
public abstract class Nodes {
    private static final Node EMPTY_NODE = new EmptyNode.OfRef();
    private static final Node.OfInt EMPTY_INT_NODE = new EmptyNode.OfInt();
    private static final Node.OfLong EMPTY_LONG_NODE = new EmptyNode.OfLong();
    private static final Node.OfDouble EMPTY_DOUBLE_NODE = new EmptyNode.OfDouble();
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

    public static class fun1 {
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

    public static abstract class AbstractConcNode implements Node {
        protected final Node left;
        protected final Node right;
        private final long size;

        AbstractConcNode(Node node, Node node2) {
            this.left = node;
            this.right = node2;
            this.size = node.count() + node2.count();
        }

        @Override
        public long count() {
            return this.size;
        }

        @Override
        public Node getChild(int i) {
            if (i == 0) {
                return this.left;
            }
            if (i == 1) {
                return this.right;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int getChildCount() {
            return 2;
        }

        public StreamShape getShape() {
            StreamShape streamShape;
            streamShape = StreamShape.REFERENCE;
            return streamShape;
        }
    }

    public static class ArrayNode implements Node {
        final Object[] array;
        int curSize;

        ArrayNode(long j, IntFunction intFunction) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = (Object[]) intFunction.apply((int) j);
            this.curSize = 0;
        }

        ArrayNode(Object[] objArr) {
            this.array = objArr;
            this.curSize = objArr.length;
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            Object[] objArr = this.array;
            if (objArr.length == this.curSize) {
                return objArr;
            }
            throw new IllegalStateException();
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            System.arraycopy(this.array, 0, objArr, i, this.curSize);
        }

        @Override
        public long count() {
            return this.curSize;
        }

        @Override
        public void forEach(Consumer consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        @Override
        public Node getChild(int i) {
            return Node.-CC.$default$getChild(this, i);
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Spliterator spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("ArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.-CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    private static final class CollectionNode implements Node {
        private final Collection c;

        CollectionNode(Collection collection) {
            this.c = collection;
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            Collection collection = this.c;
            return collection.toArray((Object[]) intFunction.apply(collection.size()));
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            for (Object obj : this.c) {
                objArr[i] = obj;
                i++;
            }
        }

        @Override
        public long count() {
            return this.c.size();
        }

        @Override
        public void forEach(Consumer consumer) {
            Collection.-EL.forEach(this.c, consumer);
        }

        @Override
        public Node getChild(int i) {
            return Node.-CC.$default$getChild(this, i);
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Spliterator spliterator() {
            return Collection.-EL.stream(this.c).spliterator();
        }

        public String toString() {
            return String.format("CollectionNode[%d][%s]", Integer.valueOf(this.c.size()), this.c);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.-CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    private static class CollectorTask extends AbstractTask {
        protected final LongFunction builderFactory;
        protected final BinaryOperator concFactory;
        protected final PipelineHelper helper;

        private static final class OfDouble extends CollectorTask {
            OfDouble(PipelineHelper pipelineHelper, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new LongFunction() {
                    @Override
                    public final Object apply(long j) {
                        return Nodes.doubleBuilder(j);
                    }
                }, new BinaryOperator() {
                    public BiFunction andThen(Function function) {
                        return Objects.requireNonNull(function);
                    }

                    @Override
                    public final Object apply(Object obj, Object obj2) {
                        return new Nodes.ConcNode.OfDouble((Node.OfDouble) obj, (Node.OfDouble) obj2);
                    }
                });
            }

            @Override
            protected Object doLeaf() {
                return super.doLeaf();
            }

            @Override
            protected AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        private static final class OfInt extends CollectorTask {
            OfInt(PipelineHelper pipelineHelper, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new LongFunction() {
                    @Override
                    public final Object apply(long j) {
                        return Nodes.intBuilder(j);
                    }
                }, new BinaryOperator() {
                    public BiFunction andThen(Function function) {
                        return Objects.requireNonNull(function);
                    }

                    @Override
                    public final Object apply(Object obj, Object obj2) {
                        return new Nodes.ConcNode.OfInt((Node.OfInt) obj, (Node.OfInt) obj2);
                    }
                });
            }

            @Override
            protected Object doLeaf() {
                return super.doLeaf();
            }

            @Override
            protected AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        private static final class OfLong extends CollectorTask {
            OfLong(PipelineHelper pipelineHelper, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new LongFunction() {
                    @Override
                    public final Object apply(long j) {
                        return Nodes.longBuilder(j);
                    }
                }, new BinaryOperator() {
                    public BiFunction andThen(Function function) {
                        return Objects.requireNonNull(function);
                    }

                    @Override
                    public final Object apply(Object obj, Object obj2) {
                        return new Nodes.ConcNode.OfLong((Node.OfLong) obj, (Node.OfLong) obj2);
                    }
                });
            }

            @Override
            protected Object doLeaf() {
                return super.doLeaf();
            }

            @Override
            protected AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        private static final class OfRef extends CollectorTask {
            OfRef(PipelineHelper pipelineHelper, final IntFunction intFunction, Spliterator spliterator) {
                super(pipelineHelper, spliterator, new LongFunction() {
                    @Override
                    public final Object apply(long j) {
                        Node.Builder builder;
                        builder = Nodes.builder(j, IntFunction.this);
                        return builder;
                    }
                }, new BinaryOperator() {
                    public BiFunction andThen(Function function) {
                        return Objects.requireNonNull(function);
                    }

                    @Override
                    public final Object apply(Object obj, Object obj2) {
                        return new Nodes.ConcNode((Node) obj, (Node) obj2);
                    }
                });
            }

            @Override
            protected Object doLeaf() {
                return super.doLeaf();
            }

            @Override
            protected AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }
        }

        CollectorTask(CollectorTask collectorTask, Spliterator spliterator) {
            super(collectorTask, spliterator);
            this.helper = collectorTask.helper;
            this.builderFactory = collectorTask.builderFactory;
            this.concFactory = collectorTask.concFactory;
        }

        CollectorTask(PipelineHelper pipelineHelper, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator) {
            super(pipelineHelper, spliterator);
            this.helper = pipelineHelper;
            this.builderFactory = longFunction;
            this.concFactory = binaryOperator;
        }

        @Override
        public Node doLeaf() {
            return ((Node.Builder) this.helper.wrapAndCopyInto((Node.Builder) this.builderFactory.apply(this.helper.exactOutputSizeIfKnown(this.spliterator)), this.spliterator)).build();
        }

        @Override
        public CollectorTask makeChild(Spliterator spliterator) {
            return new CollectorTask(this, spliterator);
        }

        @Override
        public void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                setLocalResult((Node) this.concFactory.apply((Node) ((CollectorTask) this.leftChild).getLocalResult(), (Node) ((CollectorTask) this.rightChild).getLocalResult()));
            }
            super.onCompletion(countedCompleter);
        }
    }

    public static final class ConcNode extends AbstractConcNode implements Node {

        public static final class OfDouble extends OfPrimitive implements Node.OfDouble {
            public OfDouble(Node.OfDouble ofDouble, Node.OfDouble ofDouble2) {
                super(ofDouble, ofDouble2);
            }

            @Override
            public void copyInto(Double[] dArr, int i) {
                Node.OfDouble.-CC.$default$copyInto((Node.OfDouble) this, dArr, i);
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                copyInto((Double[]) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                Node.OfDouble.-CC.$default$forEach(this, consumer);
            }

            @Override
            public Object newArray(int i) {
                Object newArray;
                newArray = newArray(i);
                return newArray;
            }

            @Override
            public double[] newArray(int i) {
                return Node.OfDouble.-CC.$default$newArray((Node.OfDouble) this, i);
            }

            @Override
            public Spliterator.OfDouble spliterator() {
                return new InternalNodeSpliterator.OfDouble(this);
            }

            @Override
            public Node.OfDouble truncate(long j, long j2, IntFunction intFunction) {
                return Node.OfDouble.-CC.$default$truncate((Node.OfDouble) this, j, j2, intFunction);
            }

            @Override
            public Node truncate(long j, long j2, IntFunction intFunction) {
                Node truncate;
                truncate = truncate(j, j2, intFunction);
                return truncate;
            }
        }

        public static final class OfInt extends OfPrimitive implements Node.OfInt {
            public OfInt(Node.OfInt ofInt, Node.OfInt ofInt2) {
                super(ofInt, ofInt2);
            }

            @Override
            public void copyInto(Integer[] numArr, int i) {
                Node.OfInt.-CC.$default$copyInto((Node.OfInt) this, numArr, i);
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                copyInto((Integer[]) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                Node.OfInt.-CC.$default$forEach(this, consumer);
            }

            @Override
            public Object newArray(int i) {
                Object newArray;
                newArray = newArray(i);
                return newArray;
            }

            @Override
            public int[] newArray(int i) {
                return Node.OfInt.-CC.$default$newArray((Node.OfInt) this, i);
            }

            @Override
            public Spliterator.OfInt spliterator() {
                return new InternalNodeSpliterator.OfInt(this);
            }

            @Override
            public Node.OfInt truncate(long j, long j2, IntFunction intFunction) {
                return Node.OfInt.-CC.$default$truncate((Node.OfInt) this, j, j2, intFunction);
            }

            @Override
            public Node truncate(long j, long j2, IntFunction intFunction) {
                Node truncate;
                truncate = truncate(j, j2, intFunction);
                return truncate;
            }
        }

        public static final class OfLong extends OfPrimitive implements Node.OfLong {
            public OfLong(Node.OfLong ofLong, Node.OfLong ofLong2) {
                super(ofLong, ofLong2);
            }

            @Override
            public void copyInto(Long[] lArr, int i) {
                Node.OfLong.-CC.$default$copyInto((Node.OfLong) this, lArr, i);
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                copyInto((Long[]) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                Node.OfLong.-CC.$default$forEach(this, consumer);
            }

            @Override
            public Object newArray(int i) {
                Object newArray;
                newArray = newArray(i);
                return newArray;
            }

            @Override
            public long[] newArray(int i) {
                return Node.OfLong.-CC.$default$newArray((Node.OfLong) this, i);
            }

            @Override
            public Spliterator.OfLong spliterator() {
                return new InternalNodeSpliterator.OfLong(this);
            }

            @Override
            public Node.OfLong truncate(long j, long j2, IntFunction intFunction) {
                return Node.OfLong.-CC.$default$truncate((Node.OfLong) this, j, j2, intFunction);
            }

            @Override
            public Node truncate(long j, long j2, IntFunction intFunction) {
                Node truncate;
                truncate = truncate(j, j2, intFunction);
                return truncate;
            }
        }

        private static abstract class OfPrimitive extends AbstractConcNode implements Node.OfPrimitive {
            OfPrimitive(Node.OfPrimitive ofPrimitive, Node.OfPrimitive ofPrimitive2) {
                super(ofPrimitive, ofPrimitive2);
            }

            @Override
            public Object[] asArray(IntFunction intFunction) {
                return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
            }

            @Override
            public Object asPrimitiveArray() {
                long count = count();
                if (count < 2147483639) {
                    Object newArray = newArray((int) count);
                    copyInto(newArray, 0);
                    return newArray;
                }
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }

            @Override
            public void copyInto(Object obj, int i) {
                ((Node.OfPrimitive) this.left).copyInto(obj, i);
                ((Node.OfPrimitive) this.right).copyInto(obj, i + ((int) ((Node.OfPrimitive) this.left).count()));
            }

            @Override
            public void forEach(Object obj) {
                ((Node.OfPrimitive) this.left).forEach(obj);
                ((Node.OfPrimitive) this.right).forEach(obj);
            }

            @Override
            public Node.OfPrimitive getChild(int i) {
                return (Node.OfPrimitive) super.getChild(i);
            }

            public String toString() {
                return count() < 32 ? String.format("%s[%s.%s]", getClass().getName(), this.left, this.right) : String.format("%s[size=%d]", getClass().getName(), Long.valueOf(count()));
            }
        }

        public ConcNode(Node node, Node node2) {
            super(node, node2);
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            long count = count();
            if (count < 2147483639) {
                Object[] objArr = (Object[]) intFunction.apply((int) count);
                copyInto(objArr, 0);
                return objArr;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            Objects.requireNonNull(objArr);
            this.left.copyInto(objArr, i);
            this.right.copyInto(objArr, i + ((int) this.left.count()));
        }

        @Override
        public void forEach(Consumer consumer) {
            this.left.forEach(consumer);
            this.right.forEach(consumer);
        }

        @Override
        public Spliterator spliterator() {
            return new InternalNodeSpliterator.OfRef(this);
        }

        public String toString() {
            return count() < 32 ? String.format("ConcNode[%s.%s]", this.left, this.right) : String.format("ConcNode[size=%d]", Long.valueOf(count()));
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            if (j == 0 && j2 == count()) {
                return this;
            }
            long count = this.left.count();
            return j >= count ? this.right.truncate(j - count, j2 - count, intFunction) : j2 <= count ? this.left.truncate(j, j2, intFunction) : Nodes.conc(getShape(), this.left.truncate(j, count, intFunction), this.right.truncate(0L, j2 - count, intFunction));
        }
    }

    public static class DoubleArrayNode implements Node.OfDouble {
        final double[] array;
        int curSize;

        DoubleArrayNode(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new double[(int) j];
            this.curSize = 0;
        }

        DoubleArrayNode(double[] dArr) {
            this.array = dArr;
            this.curSize = dArr.length;
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
        }

        @Override
        public double[] asPrimitiveArray() {
            double[] dArr = this.array;
            int length = dArr.length;
            int i = this.curSize;
            return length == i ? dArr : Arrays.copyOf(dArr, i);
        }

        @Override
        public void copyInto(double[] dArr, int i) {
            System.arraycopy(this.array, 0, dArr, i, this.curSize);
        }

        @Override
        public void copyInto(Double[] dArr, int i) {
            Node.OfDouble.-CC.$default$copyInto((Node.OfDouble) this, dArr, i);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            copyInto((Double[]) objArr, i);
        }

        @Override
        public long count() {
            return this.curSize;
        }

        @Override
        public void forEach(Consumer consumer) {
            Node.OfDouble.-CC.$default$forEach(this, consumer);
        }

        @Override
        public void forEach(DoubleConsumer doubleConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                doubleConsumer.accept(this.array[i]);
            }
        }

        @Override
        public Node.OfPrimitive getChild(int i) {
            return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
        }

        @Override
        public Node getChild(int i) {
            Node child;
            child = getChild(i);
            return child;
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public double[] newArray(int i) {
            return Node.OfDouble.-CC.$default$newArray((Node.OfDouble) this, i);
        }

        @Override
        public Spliterator.OfDouble spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("DoubleArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }

        @Override
        public Node.OfDouble truncate(long j, long j2, IntFunction intFunction) {
            return Node.OfDouble.-CC.$default$truncate((Node.OfDouble) this, j, j2, intFunction);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            Node truncate;
            truncate = truncate(j, j2, intFunction);
            return truncate;
        }
    }

    public static final class DoubleFixedNodeBuilder extends DoubleArrayNode implements Node.Builder.OfDouble {
        DoubleFixedNodeBuilder(long j) {
            super(j);
        }

        @Override
        public void accept(double d) {
            int i = this.curSize;
            double[] dArr = this.array;
            if (i >= dArr.length) {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
            }
            this.curSize = i + 1;
            dArr[i] = d;
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
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override
        public Node.OfDouble build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override
        public String toString() {
            return String.format("DoubleFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    public static final class DoubleSpinedNodeBuilder extends SpinedBuffer.OfDouble implements Node.OfDouble, Node.Builder.OfDouble {
        private boolean building = false;

        DoubleSpinedNodeBuilder() {
        }

        @Override
        public void accept(double d) {
            super.accept(d);
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

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
        }

        @Override
        public double[] asPrimitiveArray() {
            return (double[]) super.asPrimitiveArray();
        }

        @Override
        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        @Override
        public Node.OfDouble build() {
            return this;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void copyInto(double[] dArr, int i) {
            super.copyInto((Object) dArr, i);
        }

        @Override
        public void copyInto(Double[] dArr, int i) {
            Node.OfDouble.-CC.$default$copyInto((Node.OfDouble) this, dArr, i);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            copyInto((Double[]) objArr, i);
        }

        @Override
        public void end() {
            this.building = false;
        }

        @Override
        public void forEach(DoubleConsumer doubleConsumer) {
            super.forEach((Object) doubleConsumer);
        }

        @Override
        public Node.OfPrimitive getChild(int i) {
            return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
        }

        @Override
        public Node getChild(int i) {
            Node child;
            child = getChild(i);
            return child;
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Spliterator.OfDouble spliterator() {
            return super.spliterator();
        }

        @Override
        public Node.OfDouble truncate(long j, long j2, IntFunction intFunction) {
            return Node.OfDouble.-CC.$default$truncate((Node.OfDouble) this, j, j2, intFunction);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            Node truncate;
            truncate = truncate(j, j2, intFunction);
            return truncate;
        }
    }

    private static abstract class EmptyNode implements Node {

        private static final class OfDouble extends EmptyNode implements Node.OfDouble {
            OfDouble() {
            }

            @Override
            public double[] asPrimitiveArray() {
                return Nodes.EMPTY_DOUBLE_ARRAY;
            }

            @Override
            public void copyInto(Double[] dArr, int i) {
                Node.OfDouble.-CC.$default$copyInto((Node.OfDouble) this, dArr, i);
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                copyInto((Double[]) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                Node.OfDouble.-CC.$default$forEach(this, consumer);
            }

            @Override
            public Node.OfPrimitive getChild(int i) {
                return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
            }

            @Override
            public Node getChild(int i) {
                Node child;
                child = getChild(i);
                return child;
            }

            @Override
            public double[] newArray(int i) {
                return Node.OfDouble.-CC.$default$newArray((Node.OfDouble) this, i);
            }

            @Override
            public Spliterator.OfDouble spliterator() {
                return Spliterators.emptyDoubleSpliterator();
            }

            @Override
            public Node.OfDouble truncate(long j, long j2, IntFunction intFunction) {
                return Node.OfDouble.-CC.$default$truncate((Node.OfDouble) this, j, j2, intFunction);
            }

            @Override
            public Node truncate(long j, long j2, IntFunction intFunction) {
                Node truncate;
                truncate = truncate(j, j2, intFunction);
                return truncate;
            }
        }

        private static final class OfInt extends EmptyNode implements Node.OfInt {
            OfInt() {
            }

            @Override
            public int[] asPrimitiveArray() {
                return Nodes.EMPTY_INT_ARRAY;
            }

            @Override
            public void copyInto(Integer[] numArr, int i) {
                Node.OfInt.-CC.$default$copyInto((Node.OfInt) this, numArr, i);
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                copyInto((Integer[]) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                Node.OfInt.-CC.$default$forEach(this, consumer);
            }

            @Override
            public Node.OfPrimitive getChild(int i) {
                return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
            }

            @Override
            public Node getChild(int i) {
                Node child;
                child = getChild(i);
                return child;
            }

            @Override
            public int[] newArray(int i) {
                return Node.OfInt.-CC.$default$newArray((Node.OfInt) this, i);
            }

            @Override
            public Spliterator.OfInt spliterator() {
                return Spliterators.emptyIntSpliterator();
            }

            @Override
            public Node.OfInt truncate(long j, long j2, IntFunction intFunction) {
                return Node.OfInt.-CC.$default$truncate((Node.OfInt) this, j, j2, intFunction);
            }

            @Override
            public Node truncate(long j, long j2, IntFunction intFunction) {
                Node truncate;
                truncate = truncate(j, j2, intFunction);
                return truncate;
            }
        }

        private static final class OfLong extends EmptyNode implements Node.OfLong {
            OfLong() {
            }

            @Override
            public long[] asPrimitiveArray() {
                return Nodes.EMPTY_LONG_ARRAY;
            }

            @Override
            public void copyInto(Long[] lArr, int i) {
                Node.OfLong.-CC.$default$copyInto((Node.OfLong) this, lArr, i);
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                copyInto((Long[]) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                Node.OfLong.-CC.$default$forEach(this, consumer);
            }

            @Override
            public Node.OfPrimitive getChild(int i) {
                return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
            }

            @Override
            public Node getChild(int i) {
                Node child;
                child = getChild(i);
                return child;
            }

            @Override
            public long[] newArray(int i) {
                return Node.OfLong.-CC.$default$newArray((Node.OfLong) this, i);
            }

            @Override
            public Spliterator.OfLong spliterator() {
                return Spliterators.emptyLongSpliterator();
            }

            @Override
            public Node.OfLong truncate(long j, long j2, IntFunction intFunction) {
                return Node.OfLong.-CC.$default$truncate((Node.OfLong) this, j, j2, intFunction);
            }

            @Override
            public Node truncate(long j, long j2, IntFunction intFunction) {
                Node truncate;
                truncate = truncate(j, j2, intFunction);
                return truncate;
            }
        }

        private static class OfRef extends EmptyNode {
            private OfRef() {
            }

            @Override
            public void copyInto(Object[] objArr, int i) {
                super.copyInto((Object) objArr, i);
            }

            @Override
            public void forEach(Consumer consumer) {
                super.forEach((Object) consumer);
            }

            @Override
            public Spliterator spliterator() {
                return Spliterators.emptySpliterator();
            }
        }

        EmptyNode() {
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return (Object[]) intFunction.apply(0);
        }

        public void copyInto(Object obj, int i) {
        }

        @Override
        public long count() {
            return 0L;
        }

        public void forEach(Object obj) {
        }

        @Override
        public Node getChild(int i) {
            return Node.-CC.$default$getChild(this, i);
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.-CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    public static final class FixedNodeBuilder extends ArrayNode implements Node.Builder {
        FixedNodeBuilder(long j, IntFunction intFunction) {
            super(j, intFunction);
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
            int i = this.curSize;
            Object[] objArr = this.array;
            if (i >= objArr.length) {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
            }
            this.curSize = i + 1;
            objArr[i] = obj;
        }

        public Consumer andThen(Consumer consumer) {
            return Objects.requireNonNull(consumer);
        }

        @Override
        public void begin(long j) {
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override
        public Node build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override
        public String toString() {
            return String.format("FixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    public static class IntArrayNode implements Node.OfInt {
        final int[] array;
        int curSize;

        IntArrayNode(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new int[(int) j];
            this.curSize = 0;
        }

        IntArrayNode(int[] iArr) {
            this.array = iArr;
            this.curSize = iArr.length;
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
        }

        @Override
        public int[] asPrimitiveArray() {
            int[] iArr = this.array;
            int length = iArr.length;
            int i = this.curSize;
            return length == i ? iArr : Arrays.copyOf(iArr, i);
        }

        @Override
        public void copyInto(int[] iArr, int i) {
            System.arraycopy(this.array, 0, iArr, i, this.curSize);
        }

        @Override
        public void copyInto(Integer[] numArr, int i) {
            Node.OfInt.-CC.$default$copyInto((Node.OfInt) this, numArr, i);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            copyInto((Integer[]) objArr, i);
        }

        @Override
        public long count() {
            return this.curSize;
        }

        @Override
        public void forEach(Consumer consumer) {
            Node.OfInt.-CC.$default$forEach(this, consumer);
        }

        @Override
        public void forEach(IntConsumer intConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                intConsumer.accept(this.array[i]);
            }
        }

        @Override
        public Node.OfPrimitive getChild(int i) {
            return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
        }

        @Override
        public Node getChild(int i) {
            Node child;
            child = getChild(i);
            return child;
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public int[] newArray(int i) {
            return Node.OfInt.-CC.$default$newArray((Node.OfInt) this, i);
        }

        @Override
        public Spliterator.OfInt spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("IntArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }

        @Override
        public Node.OfInt truncate(long j, long j2, IntFunction intFunction) {
            return Node.OfInt.-CC.$default$truncate((Node.OfInt) this, j, j2, intFunction);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            Node truncate;
            truncate = truncate(j, j2, intFunction);
            return truncate;
        }
    }

    public static final class IntFixedNodeBuilder extends IntArrayNode implements Node.Builder.OfInt {
        IntFixedNodeBuilder(long j) {
            super(j);
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            int i2 = this.curSize;
            int[] iArr = this.array;
            if (i2 >= iArr.length) {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
            }
            this.curSize = i2 + 1;
            iArr[i2] = i;
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
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override
        public Node.OfInt build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override
        public String toString() {
            return String.format("IntFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    public static final class IntSpinedNodeBuilder extends SpinedBuffer.OfInt implements Node.OfInt, Node.Builder.OfInt {
        private boolean building = false;

        IntSpinedNodeBuilder() {
        }

        @Override
        public void accept(double d) {
            Sink.-CC.$default$accept(this, d);
        }

        @Override
        public void accept(int i) {
            super.accept(i);
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

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
        }

        @Override
        public int[] asPrimitiveArray() {
            return (int[]) super.asPrimitiveArray();
        }

        @Override
        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        @Override
        public Node.OfInt build() {
            return this;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void copyInto(int[] iArr, int i) {
            super.copyInto((Object) iArr, i);
        }

        @Override
        public void copyInto(Integer[] numArr, int i) {
            Node.OfInt.-CC.$default$copyInto((Node.OfInt) this, numArr, i);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            copyInto((Integer[]) objArr, i);
        }

        @Override
        public void end() {
            this.building = false;
        }

        @Override
        public void forEach(IntConsumer intConsumer) {
            super.forEach((Object) intConsumer);
        }

        @Override
        public Node.OfPrimitive getChild(int i) {
            return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
        }

        @Override
        public Node getChild(int i) {
            Node child;
            child = getChild(i);
            return child;
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Spliterator.OfInt spliterator() {
            return super.spliterator();
        }

        @Override
        public Node.OfInt truncate(long j, long j2, IntFunction intFunction) {
            return Node.OfInt.-CC.$default$truncate((Node.OfInt) this, j, j2, intFunction);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            Node truncate;
            truncate = truncate(j, j2, intFunction);
            return truncate;
        }
    }

    public static abstract class InternalNodeSpliterator implements Spliterator {
        int curChildIndex;
        Node curNode;
        Spliterator lastNodeSpliterator;
        Spliterator tryAdvanceSpliterator;
        Deque tryAdvanceStack;

        public static final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
            OfDouble(Node.OfDouble ofDouble) {
                super(ofDouble);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                Spliterator.OfDouble.-CC.$default$forEachRemaining(this, consumer);
            }

            @Override
            public void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining((Object) doubleConsumer);
            }

            @Override
            public boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfDouble.-CC.$default$tryAdvance(this, consumer);
            }

            @Override
            public boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance((Object) doubleConsumer);
            }

            @Override
            public Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }
        }

        public static final class OfInt extends OfPrimitive implements Spliterator.OfInt {
            OfInt(Node.OfInt ofInt) {
                super(ofInt);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                Spliterator.OfInt.-CC.$default$forEachRemaining(this, consumer);
            }

            @Override
            public void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining((Object) intConsumer);
            }

            @Override
            public boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfInt.-CC.$default$tryAdvance(this, consumer);
            }

            @Override
            public boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance((Object) intConsumer);
            }

            @Override
            public Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }
        }

        public static final class OfLong extends OfPrimitive implements Spliterator.OfLong {
            OfLong(Node.OfLong ofLong) {
                super(ofLong);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                Spliterator.OfLong.-CC.$default$forEachRemaining(this, consumer);
            }

            @Override
            public void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining((Object) longConsumer);
            }

            @Override
            public boolean tryAdvance(Consumer consumer) {
                return Spliterator.OfLong.-CC.$default$tryAdvance(this, consumer);
            }

            @Override
            public boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance((Object) longConsumer);
            }

            @Override
            public Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }
        }

        private static abstract class OfPrimitive extends InternalNodeSpliterator implements Spliterator.OfPrimitive {
            OfPrimitive(Node.OfPrimitive ofPrimitive) {
                super(ofPrimitive);
            }

            @Override
            public void forEachRemaining(Object obj) {
                if (this.curNode == null) {
                    return;
                }
                if (this.tryAdvanceSpliterator != null) {
                    do {
                    } while (tryAdvance(obj));
                    return;
                }
                Spliterator spliterator = this.lastNodeSpliterator;
                if (spliterator != null) {
                    ((Spliterator.OfPrimitive) spliterator).forEachRemaining(obj);
                    return;
                }
                Deque initStack = initStack();
                while (true) {
                    Node.OfPrimitive ofPrimitive = (Node.OfPrimitive) findNextLeafNode(initStack);
                    if (ofPrimitive == null) {
                        this.curNode = null;
                        return;
                    }
                    ofPrimitive.forEach(obj);
                }
            }

            @Override
            public boolean tryAdvance(Object obj) {
                Node.OfPrimitive ofPrimitive;
                if (initTryAdvance()) {
                    boolean tryAdvance = ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance(obj);
                    if (!tryAdvance) {
                        if (this.lastNodeSpliterator == null && (ofPrimitive = (Node.OfPrimitive) findNextLeafNode(this.tryAdvanceStack)) != null) {
                            Spliterator.OfPrimitive spliterator = ofPrimitive.spliterator();
                            this.tryAdvanceSpliterator = spliterator;
                            return spliterator.tryAdvance(obj);
                        }
                        this.curNode = null;
                    }
                    return tryAdvance;
                }
                return false;
            }

            @Override
            public Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }
        }

        private static final class OfRef extends InternalNodeSpliterator {
            OfRef(Node node) {
                super(node);
            }

            @Override
            public void forEachRemaining(Consumer consumer) {
                if (this.curNode == null) {
                    return;
                }
                if (this.tryAdvanceSpliterator != null) {
                    do {
                    } while (tryAdvance(consumer));
                    return;
                }
                Spliterator spliterator = this.lastNodeSpliterator;
                if (spliterator != null) {
                    spliterator.forEachRemaining(consumer);
                    return;
                }
                Deque initStack = initStack();
                while (true) {
                    Node findNextLeafNode = findNextLeafNode(initStack);
                    if (findNextLeafNode == null) {
                        this.curNode = null;
                        return;
                    }
                    findNextLeafNode.forEach(consumer);
                }
            }

            @Override
            public boolean tryAdvance(Consumer consumer) {
                Node findNextLeafNode;
                if (initTryAdvance()) {
                    boolean tryAdvance = this.tryAdvanceSpliterator.tryAdvance(consumer);
                    if (!tryAdvance) {
                        if (this.lastNodeSpliterator == null && (findNextLeafNode = findNextLeafNode(this.tryAdvanceStack)) != null) {
                            Spliterator spliterator = findNextLeafNode.spliterator();
                            this.tryAdvanceSpliterator = spliterator;
                            return spliterator.tryAdvance(consumer);
                        }
                        this.curNode = null;
                    }
                    return tryAdvance;
                }
                return false;
            }
        }

        InternalNodeSpliterator(Node node) {
            this.curNode = node;
        }

        @Override
        public final int characteristics() {
            return 64;
        }

        @Override
        public final long estimateSize() {
            long j = 0;
            if (this.curNode == null) {
                return 0L;
            }
            Spliterator spliterator = this.lastNodeSpliterator;
            if (spliterator != null) {
                return spliterator.estimateSize();
            }
            for (int i = this.curChildIndex; i < this.curNode.getChildCount(); i++) {
                j += this.curNode.getChild(i).count();
            }
            return j;
        }

        protected final Node findNextLeafNode(Deque deque) {
            while (true) {
                Node node = (Node) deque.pollFirst();
                if (node == null) {
                    return null;
                }
                if (node.getChildCount() != 0) {
                    for (int childCount = node.getChildCount() - 1; childCount >= 0; childCount--) {
                        deque.addFirst(node.getChild(childCount));
                    }
                } else if (node.count() > 0) {
                    return node;
                }
            }
        }

        @Override
        public Comparator getComparator() {
            return Spliterator.-CC.$default$getComparator(this);
        }

        @Override
        public long getExactSizeIfKnown() {
            return Spliterator.-CC.$default$getExactSizeIfKnown(this);
        }

        @Override
        public boolean hasCharacteristics(int i) {
            return Spliterator.-CC.$default$hasCharacteristics(this, i);
        }

        protected final Deque initStack() {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            int childCount = this.curNode.getChildCount();
            while (true) {
                childCount--;
                if (childCount < this.curChildIndex) {
                    return arrayDeque;
                }
                arrayDeque.addFirst(this.curNode.getChild(childCount));
            }
        }

        protected final boolean initTryAdvance() {
            if (this.curNode == null) {
                return false;
            }
            if (this.tryAdvanceSpliterator == null) {
                Spliterator spliterator = this.lastNodeSpliterator;
                if (spliterator == null) {
                    Deque initStack = initStack();
                    this.tryAdvanceStack = initStack;
                    Node findNextLeafNode = findNextLeafNode(initStack);
                    if (findNextLeafNode == null) {
                        this.curNode = null;
                        return false;
                    }
                    spliterator = findNextLeafNode.spliterator();
                }
                this.tryAdvanceSpliterator = spliterator;
                return true;
            }
            return true;
        }

        @Override
        public final Spliterator trySplit() {
            Node node = this.curNode;
            if (node == null || this.tryAdvanceSpliterator != null) {
                return null;
            }
            Spliterator spliterator = this.lastNodeSpliterator;
            if (spliterator != null) {
                return spliterator.trySplit();
            }
            if (this.curChildIndex < node.getChildCount() - 1) {
                Node node2 = this.curNode;
                int i = this.curChildIndex;
                this.curChildIndex = i + 1;
                return node2.getChild(i).spliterator();
            }
            Node child = this.curNode.getChild(this.curChildIndex);
            this.curNode = child;
            if (child.getChildCount() == 0) {
                Spliterator spliterator2 = this.curNode.spliterator();
                this.lastNodeSpliterator = spliterator2;
                return spliterator2.trySplit();
            }
            Node node3 = this.curNode;
            this.curChildIndex = 1;
            return node3.getChild(0).spliterator();
        }
    }

    public static class LongArrayNode implements Node.OfLong {
        final long[] array;
        int curSize;

        LongArrayNode(long j) {
            if (j >= 2147483639) {
                throw new IllegalArgumentException("Stream size exceeds max array size");
            }
            this.array = new long[(int) j];
            this.curSize = 0;
        }

        LongArrayNode(long[] jArr) {
            this.array = jArr;
            this.curSize = jArr.length;
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
        }

        @Override
        public long[] asPrimitiveArray() {
            long[] jArr = this.array;
            int length = jArr.length;
            int i = this.curSize;
            return length == i ? jArr : Arrays.copyOf(jArr, i);
        }

        @Override
        public void copyInto(long[] jArr, int i) {
            System.arraycopy(this.array, 0, jArr, i, this.curSize);
        }

        @Override
        public void copyInto(Long[] lArr, int i) {
            Node.OfLong.-CC.$default$copyInto((Node.OfLong) this, lArr, i);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            copyInto((Long[]) objArr, i);
        }

        @Override
        public long count() {
            return this.curSize;
        }

        @Override
        public void forEach(Consumer consumer) {
            Node.OfLong.-CC.$default$forEach(this, consumer);
        }

        @Override
        public void forEach(LongConsumer longConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                longConsumer.accept(this.array[i]);
            }
        }

        @Override
        public Node.OfPrimitive getChild(int i) {
            return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
        }

        @Override
        public Node getChild(int i) {
            Node child;
            child = getChild(i);
            return child;
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public long[] newArray(int i) {
            return Node.OfLong.-CC.$default$newArray((Node.OfLong) this, i);
        }

        @Override
        public Spliterator.OfLong spliterator() {
            return DesugarArrays.spliterator(this.array, 0, this.curSize);
        }

        public String toString() {
            return String.format("LongArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }

        @Override
        public Node.OfLong truncate(long j, long j2, IntFunction intFunction) {
            return Node.OfLong.-CC.$default$truncate((Node.OfLong) this, j, j2, intFunction);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            Node truncate;
            truncate = truncate(j, j2, intFunction);
            return truncate;
        }
    }

    public static final class LongFixedNodeBuilder extends LongArrayNode implements Node.Builder.OfLong {
        LongFixedNodeBuilder(long j) {
            super(j);
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
            int i = this.curSize;
            long[] jArr = this.array;
            if (i >= jArr.length) {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
            }
            this.curSize = i + 1;
            jArr[i] = j;
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
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override
        public Node.OfLong build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override
        public String toString() {
            return String.format("LongFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    public static final class LongSpinedNodeBuilder extends SpinedBuffer.OfLong implements Node.OfLong, Node.Builder.OfLong {
        private boolean building = false;

        LongSpinedNodeBuilder() {
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
            super.accept(j);
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

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return Node.OfPrimitive.-CC.$default$asArray(this, intFunction);
        }

        @Override
        public long[] asPrimitiveArray() {
            return (long[]) super.asPrimitiveArray();
        }

        @Override
        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        @Override
        public Node.OfLong build() {
            return this;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void copyInto(long[] jArr, int i) {
            super.copyInto((Object) jArr, i);
        }

        @Override
        public void copyInto(Long[] lArr, int i) {
            Node.OfLong.-CC.$default$copyInto((Node.OfLong) this, lArr, i);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            copyInto((Long[]) objArr, i);
        }

        @Override
        public void end() {
            this.building = false;
        }

        @Override
        public void forEach(LongConsumer longConsumer) {
            super.forEach((Object) longConsumer);
        }

        @Override
        public Node.OfPrimitive getChild(int i) {
            return Node.OfPrimitive.-CC.$default$getChild((Node.OfPrimitive) this, i);
        }

        @Override
        public Node getChild(int i) {
            Node child;
            child = getChild(i);
            return child;
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Spliterator.OfLong spliterator() {
            return super.spliterator();
        }

        @Override
        public Node.OfLong truncate(long j, long j2, IntFunction intFunction) {
            return Node.OfLong.-CC.$default$truncate((Node.OfLong) this, j, j2, intFunction);
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            Node truncate;
            truncate = truncate(j, j2, intFunction);
            return truncate;
        }
    }

    private static abstract class SizedCollectorTask extends CountedCompleter implements Sink {
        protected int fence;
        protected final PipelineHelper helper;
        protected int index;
        protected long length;
        protected long offset;
        protected final Spliterator spliterator;
        protected final long targetSize;

        static final class OfDouble extends SizedCollectorTask implements Sink.OfDouble {
            private final double[] array;

            OfDouble(Spliterator spliterator, PipelineHelper pipelineHelper, double[] dArr) {
                super(spliterator, pipelineHelper, dArr.length);
                this.array = dArr;
            }

            OfDouble(OfDouble ofDouble, Spliterator spliterator, long j, long j2) {
                super(ofDouble, spliterator, j, j2, ofDouble.array.length);
                this.array = ofDouble.array;
            }

            @Override
            public void accept(double d) {
                int i = this.index;
                if (i >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                double[] dArr = this.array;
                this.index = i + 1;
                dArr[i] = d;
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
            public OfDouble makeChild(Spliterator spliterator, long j, long j2) {
                return new OfDouble(this, spliterator, j, j2);
            }
        }

        static final class OfInt extends SizedCollectorTask implements Sink.OfInt {
            private final int[] array;

            OfInt(Spliterator spliterator, PipelineHelper pipelineHelper, int[] iArr) {
                super(spliterator, pipelineHelper, iArr.length);
                this.array = iArr;
            }

            OfInt(OfInt ofInt, Spliterator spliterator, long j, long j2) {
                super(ofInt, spliterator, j, j2, ofInt.array.length);
                this.array = ofInt.array;
            }

            @Override
            public void accept(int i) {
                int i2 = this.index;
                if (i2 >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                int[] iArr = this.array;
                this.index = i2 + 1;
                iArr[i2] = i;
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
            public OfInt makeChild(Spliterator spliterator, long j, long j2) {
                return new OfInt(this, spliterator, j, j2);
            }
        }

        static final class OfLong extends SizedCollectorTask implements Sink.OfLong {
            private final long[] array;

            OfLong(Spliterator spliterator, PipelineHelper pipelineHelper, long[] jArr) {
                super(spliterator, pipelineHelper, jArr.length);
                this.array = jArr;
            }

            OfLong(OfLong ofLong, Spliterator spliterator, long j, long j2) {
                super(ofLong, spliterator, j, j2, ofLong.array.length);
                this.array = ofLong.array;
            }

            @Override
            public void accept(long j) {
                int i = this.index;
                if (i >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                long[] jArr = this.array;
                this.index = i + 1;
                jArr[i] = j;
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
            public OfLong makeChild(Spliterator spliterator, long j, long j2) {
                return new OfLong(this, spliterator, j, j2);
            }
        }

        static final class OfRef extends SizedCollectorTask implements Sink {
            private final Object[] array;

            OfRef(Spliterator spliterator, PipelineHelper pipelineHelper, Object[] objArr) {
                super(spliterator, pipelineHelper, objArr.length);
                this.array = objArr;
            }

            OfRef(OfRef ofRef, Spliterator spliterator, long j, long j2) {
                super(ofRef, spliterator, j, j2, ofRef.array.length);
                this.array = ofRef.array;
            }

            @Override
            public void accept(Object obj) {
                int i = this.index;
                if (i >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                Object[] objArr = this.array;
                this.index = i + 1;
                objArr[i] = obj;
            }

            @Override
            public OfRef makeChild(Spliterator spliterator, long j, long j2) {
                return new OfRef(this, spliterator, j, j2);
            }
        }

        SizedCollectorTask(Spliterator spliterator, PipelineHelper pipelineHelper, int i) {
            this.spliterator = spliterator;
            this.helper = pipelineHelper;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator.estimateSize());
            this.offset = 0L;
            this.length = i;
        }

        SizedCollectorTask(SizedCollectorTask sizedCollectorTask, Spliterator spliterator, long j, long j2, int i) {
            super(sizedCollectorTask);
            this.spliterator = spliterator;
            this.helper = sizedCollectorTask.helper;
            this.targetSize = sizedCollectorTask.targetSize;
            this.offset = j;
            this.length = j2;
            if (j < 0 || j2 < 0 || (j + j2) - 1 >= i) {
                throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)));
            }
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
            long j2 = this.length;
            if (j > j2) {
                throw new IllegalStateException("size passed to Sink.begin exceeds array length");
            }
            int i = (int) this.offset;
            this.index = i;
            this.fence = i + ((int) j2);
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void compute() {
            Spliterator trySplit;
            Spliterator spliterator = this.spliterator;
            SizedCollectorTask sizedCollectorTask = this;
            while (spliterator.estimateSize() > sizedCollectorTask.targetSize && (trySplit = spliterator.trySplit()) != null) {
                sizedCollectorTask.setPendingCount(1);
                long estimateSize = trySplit.estimateSize();
                sizedCollectorTask.makeChild(trySplit, sizedCollectorTask.offset, estimateSize).fork();
                sizedCollectorTask = sizedCollectorTask.makeChild(spliterator, sizedCollectorTask.offset + estimateSize, sizedCollectorTask.length - estimateSize);
            }
            sizedCollectorTask.helper.wrapAndCopyInto(sizedCollectorTask, spliterator);
            sizedCollectorTask.propagateCompletion();
        }

        @Override
        public void end() {
            Sink.-CC.$default$end(this);
        }

        abstract SizedCollectorTask makeChild(Spliterator spliterator, long j, long j2);
    }

    public static final class SpinedNodeBuilder extends SpinedBuffer implements Node, Node.Builder {
        private boolean building = false;

        SpinedNodeBuilder() {
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
            super.accept(obj);
        }

        @Override
        public Object[] asArray(IntFunction intFunction) {
            return super.asArray(intFunction);
        }

        @Override
        public void begin(long j) {
            this.building = true;
            clear();
            ensureCapacity(j);
        }

        @Override
        public Node build() {
            return this;
        }

        @Override
        public boolean cancellationRequested() {
            return Sink.-CC.$default$cancellationRequested(this);
        }

        @Override
        public void copyInto(Object[] objArr, int i) {
            super.copyInto(objArr, i);
        }

        @Override
        public void end() {
            this.building = false;
        }

        @Override
        public void forEach(Consumer consumer) {
            super.forEach(consumer);
        }

        @Override
        public Node getChild(int i) {
            return Node.-CC.$default$getChild(this, i);
        }

        @Override
        public int getChildCount() {
            return Node.-CC.$default$getChildCount(this);
        }

        @Override
        public Spliterator spliterator() {
            return super.spliterator();
        }

        @Override
        public Node truncate(long j, long j2, IntFunction intFunction) {
            return Node.-CC.$default$truncate(this, j, j2, intFunction);
        }
    }

    public static abstract class ToArrayTask extends CountedCompleter {
        protected final Node node;
        protected final int offset;

        public static final class OfDouble extends OfPrimitive {
            private OfDouble(Node.OfDouble ofDouble, double[] dArr, int i) {
                super(ofDouble, dArr, i);
            }
        }

        public static final class OfInt extends OfPrimitive {
            private OfInt(Node.OfInt ofInt, int[] iArr, int i) {
                super(ofInt, iArr, i);
            }
        }

        public static final class OfLong extends OfPrimitive {
            private OfLong(Node.OfLong ofLong, long[] jArr, int i) {
                super(ofLong, jArr, i);
            }
        }

        private static class OfPrimitive extends ToArrayTask {
            private final Object array;

            private OfPrimitive(Node.OfPrimitive ofPrimitive, Object obj, int i) {
                super(ofPrimitive, i);
                this.array = obj;
            }

            private OfPrimitive(OfPrimitive ofPrimitive, Node.OfPrimitive ofPrimitive2, int i) {
                super(ofPrimitive, ofPrimitive2, i);
                this.array = ofPrimitive.array;
            }

            @Override
            void copyNodeToArray() {
                ((Node.OfPrimitive) this.node).copyInto(this.array, this.offset);
            }

            @Override
            public OfPrimitive makeChild(int i, int i2) {
                return new OfPrimitive(this, ((Node.OfPrimitive) this.node).getChild(i), i2);
            }
        }

        public static final class OfRef extends ToArrayTask {
            private final Object[] array;

            private OfRef(Node node, Object[] objArr, int i) {
                super(node, i);
                this.array = objArr;
            }

            private OfRef(OfRef ofRef, Node node, int i) {
                super(ofRef, node, i);
                this.array = ofRef.array;
            }

            @Override
            void copyNodeToArray() {
                this.node.copyInto(this.array, this.offset);
            }

            @Override
            public OfRef makeChild(int i, int i2) {
                return new OfRef(this, this.node.getChild(i), i2);
            }
        }

        ToArrayTask(Node node, int i) {
            this.node = node;
            this.offset = i;
        }

        ToArrayTask(ToArrayTask toArrayTask, Node node, int i) {
            super(toArrayTask);
            this.node = node;
            this.offset = i;
        }

        @Override
        public void compute() {
            ToArrayTask toArrayTask = this;
            while (toArrayTask.node.getChildCount() != 0) {
                toArrayTask.setPendingCount(toArrayTask.node.getChildCount() - 1);
                int i = 0;
                int i2 = 0;
                while (i < toArrayTask.node.getChildCount() - 1) {
                    ToArrayTask makeChild = toArrayTask.makeChild(i, toArrayTask.offset + i2);
                    i2 = (int) (i2 + makeChild.node.count());
                    makeChild.fork();
                    i++;
                }
                toArrayTask = toArrayTask.makeChild(i, toArrayTask.offset + i2);
            }
            toArrayTask.copyNodeToArray();
            toArrayTask.propagateCompletion();
        }

        abstract void copyNodeToArray();

        abstract ToArrayTask makeChild(int i, int i2);
    }

    static Node.Builder builder() {
        return new SpinedNodeBuilder();
    }

    public static Node.Builder builder(long j, IntFunction intFunction) {
        return (j < 0 || j >= 2147483639) ? builder() : new FixedNodeBuilder(j, intFunction);
    }

    public static IntFunction castingArray() {
        return new IntFunction() {
            @Override
            public final Object apply(int i) {
                return Nodes.lambda$castingArray$0(i);
            }
        };
    }

    public static Node collect(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z, IntFunction intFunction) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node node = (Node) new CollectorTask.OfRef(pipelineHelper, intFunction, spliterator).invoke();
            return z ? flatten(node, intFunction) : node;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            Object[] objArr = (Object[]) intFunction.apply((int) exactOutputSizeIfKnown);
            new SizedCollectorTask.OfRef(spliterator, pipelineHelper, objArr).invoke();
            return node(objArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node.OfDouble collectDouble(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfDouble ofDouble = (Node.OfDouble) new CollectorTask.OfDouble(pipelineHelper, spliterator).invoke();
            return z ? flattenDouble(ofDouble) : ofDouble;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            double[] dArr = new double[(int) exactOutputSizeIfKnown];
            new SizedCollectorTask.OfDouble(spliterator, pipelineHelper, dArr).invoke();
            return node(dArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node.OfInt collectInt(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfInt ofInt = (Node.OfInt) new CollectorTask.OfInt(pipelineHelper, spliterator).invoke();
            return z ? flattenInt(ofInt) : ofInt;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            int[] iArr = new int[(int) exactOutputSizeIfKnown];
            new SizedCollectorTask.OfInt(spliterator, pipelineHelper, iArr).invoke();
            return node(iArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node.OfLong collectLong(PipelineHelper pipelineHelper, Spliterator spliterator, boolean z) {
        long exactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (exactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfLong ofLong = (Node.OfLong) new CollectorTask.OfLong(pipelineHelper, spliterator).invoke();
            return z ? flattenLong(ofLong) : ofLong;
        } else if (exactOutputSizeIfKnown < 2147483639) {
            long[] jArr = new long[(int) exactOutputSizeIfKnown];
            new SizedCollectorTask.OfLong(spliterator, pipelineHelper, jArr).invoke();
            return node(jArr);
        } else {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public static Node conc(StreamShape streamShape, Node node, Node node2) {
        int i = fun1.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return new ConcNode.OfDouble((Node.OfDouble) node, (Node.OfDouble) node2);
                    }
                    throw new IllegalStateException("Unknown shape " + streamShape);
                }
                return new ConcNode.OfLong((Node.OfLong) node, (Node.OfLong) node2);
            }
            return new ConcNode.OfInt((Node.OfInt) node, (Node.OfInt) node2);
        }
        return new ConcNode(node, node2);
    }

    static Node.Builder.OfDouble doubleBuilder() {
        return new DoubleSpinedNodeBuilder();
    }

    public static Node.Builder.OfDouble doubleBuilder(long j) {
        return (j < 0 || j >= 2147483639) ? doubleBuilder() : new DoubleFixedNodeBuilder(j);
    }

    public static Node emptyNode(StreamShape streamShape) {
        int i = fun1.$SwitchMap$java$util$stream$StreamShape[streamShape.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return EMPTY_DOUBLE_NODE;
                    }
                    throw new IllegalStateException("Unknown shape " + streamShape);
                }
                return EMPTY_LONG_NODE;
            }
            return EMPTY_INT_NODE;
        }
        return EMPTY_NODE;
    }

    public static Node flatten(Node node, IntFunction intFunction) {
        if (node.getChildCount() > 0) {
            long count = node.count();
            if (count < 2147483639) {
                Object[] objArr = (Object[]) intFunction.apply((int) count);
                new ToArrayTask.OfRef(node, objArr, 0).invoke();
                return node(objArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return node;
    }

    public static Node.OfDouble flattenDouble(Node.OfDouble ofDouble) {
        if (ofDouble.getChildCount() > 0) {
            long count = ofDouble.count();
            if (count < 2147483639) {
                double[] dArr = new double[(int) count];
                new ToArrayTask.OfDouble(ofDouble, dArr, 0).invoke();
                return node(dArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return ofDouble;
    }

    public static Node.OfInt flattenInt(Node.OfInt ofInt) {
        if (ofInt.getChildCount() > 0) {
            long count = ofInt.count();
            if (count < 2147483639) {
                int[] iArr = new int[(int) count];
                new ToArrayTask.OfInt(ofInt, iArr, 0).invoke();
                return node(iArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return ofInt;
    }

    public static Node.OfLong flattenLong(Node.OfLong ofLong) {
        if (ofLong.getChildCount() > 0) {
            long count = ofLong.count();
            if (count < 2147483639) {
                long[] jArr = new long[(int) count];
                new ToArrayTask.OfLong(ofLong, jArr, 0).invoke();
                return node(jArr);
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        return ofLong;
    }

    static Node.Builder.OfInt intBuilder() {
        return new IntSpinedNodeBuilder();
    }

    public static Node.Builder.OfInt intBuilder(long j) {
        return (j < 0 || j >= 2147483639) ? intBuilder() : new IntFixedNodeBuilder(j);
    }

    public static Object[] lambda$castingArray$0(int i) {
        return new Object[i];
    }

    static Node.Builder.OfLong longBuilder() {
        return new LongSpinedNodeBuilder();
    }

    public static Node.Builder.OfLong longBuilder(long j) {
        return (j < 0 || j >= 2147483639) ? longBuilder() : new LongFixedNodeBuilder(j);
    }

    public static Node.OfDouble node(double[] dArr) {
        return new DoubleArrayNode(dArr);
    }

    public static Node.OfInt node(int[] iArr) {
        return new IntArrayNode(iArr);
    }

    public static Node.OfLong node(long[] jArr) {
        return new LongArrayNode(jArr);
    }

    public static Node node(java.util.Collection collection) {
        return new CollectionNode(collection);
    }

    public static Node node(Object[] objArr) {
        return new ArrayNode(objArr);
    }
}
