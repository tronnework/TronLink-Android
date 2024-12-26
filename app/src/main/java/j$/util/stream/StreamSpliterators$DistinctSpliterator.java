package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;
import java.util.function.Consumer;
public final class StreamSpliterators$DistinctSpliterator implements Spliterator, Consumer {
    private static final Object NULL_VALUE = new Object();
    private final Spliterator s;
    private final ConcurrentHashMap seen;
    private Object tmpSlot;

    public StreamSpliterators$DistinctSpliterator(Spliterator spliterator) {
        this(spliterator, new ConcurrentHashMap());
    }

    private StreamSpliterators$DistinctSpliterator(Spliterator spliterator, ConcurrentHashMap concurrentHashMap) {
        this.s = spliterator;
        this.seen = concurrentHashMap;
    }

    private Object mapNull(Object obj) {
        return obj != null ? obj : NULL_VALUE;
    }

    @Override
    public void accept(Object obj) {
        this.tmpSlot = obj;
    }

    public Consumer andThen(Consumer consumer) {
        return Objects.requireNonNull(consumer);
    }

    @Override
    public int characteristics() {
        return (this.s.characteristics() & (-16469)) | 1;
    }

    @Override
    public long estimateSize() {
        return this.s.estimateSize();
    }

    @Override
    public void forEachRemaining(final Consumer consumer) {
        this.s.forEachRemaining(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$forEachRemaining$0$java-util-stream-StreamSpliterators$DistinctSpliterator(consumer, obj);
            }

            public Consumer andThen(Consumer consumer2) {
                return Objects.requireNonNull(consumer2);
            }
        });
    }

    @Override
    public Comparator getComparator() {
        return this.s.getComparator();
    }

    @Override
    public long getExactSizeIfKnown() {
        return Spliterator.-CC.$default$getExactSizeIfKnown(this);
    }

    @Override
    public boolean hasCharacteristics(int i) {
        return Spliterator.-CC.$default$hasCharacteristics(this, i);
    }

    public void lambda$forEachRemaining$0$java-util-stream-StreamSpliterators$DistinctSpliterator(Consumer consumer, Object obj) {
        if (this.seen.putIfAbsent(mapNull(obj), Boolean.TRUE) == null) {
            consumer.accept(obj);
        }
    }

    @Override
    public boolean tryAdvance(Consumer consumer) {
        while (this.s.tryAdvance(this)) {
            if (this.seen.putIfAbsent(mapNull(this.tmpSlot), Boolean.TRUE) == null) {
                consumer.accept(this.tmpSlot);
                this.tmpSlot = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Spliterator trySplit() {
        Spliterator trySplit = this.s.trySplit();
        if (trySplit != null) {
            return new StreamSpliterators$DistinctSpliterator(trySplit, this.seen);
        }
        return null;
    }
}
