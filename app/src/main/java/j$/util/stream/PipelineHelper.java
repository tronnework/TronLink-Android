package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.function.IntFunction;
abstract class PipelineHelper {
    public abstract void copyInto(Sink sink, Spliterator spliterator);

    public abstract boolean copyIntoWithCancel(Sink sink, Spliterator spliterator);

    public abstract Node evaluate(Spliterator spliterator, boolean z, IntFunction intFunction);

    public abstract long exactOutputSizeIfKnown(Spliterator spliterator);

    public abstract StreamShape getSourceShape();

    public abstract int getStreamAndOpFlags();

    public abstract Node.Builder makeNodeBuilder(long j, IntFunction intFunction);

    public abstract Sink wrapAndCopyInto(Sink sink, Spliterator spliterator);

    public abstract Sink wrapSink(Sink sink);

    public abstract Spliterator wrapSpliterator(Spliterator spliterator);
}
