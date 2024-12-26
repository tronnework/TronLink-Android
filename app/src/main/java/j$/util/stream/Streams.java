package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;
public abstract class Streams {

    static abstract class ConcatSpliterator implements Spliterator {
        protected final Spliterator aSpliterator;
        protected final Spliterator bSpliterator;
        boolean beforeSplit = true;
        final boolean unsized;

        public static class OfRef extends ConcatSpliterator {
            public OfRef(Spliterator spliterator, Spliterator spliterator2) {
                super(spliterator, spliterator2);
            }
        }

        public ConcatSpliterator(Spliterator spliterator, Spliterator spliterator2) {
            this.aSpliterator = spliterator;
            this.bSpliterator = spliterator2;
            this.unsized = spliterator.estimateSize() + spliterator2.estimateSize() < 0;
        }

        @Override
        public int characteristics() {
            if (this.beforeSplit) {
                return this.aSpliterator.characteristics() & this.bSpliterator.characteristics() & (~((this.unsized ? 16448 : 0) | 5));
            }
            return this.bSpliterator.characteristics();
        }

        @Override
        public long estimateSize() {
            if (this.beforeSplit) {
                long estimateSize = this.aSpliterator.estimateSize() + this.bSpliterator.estimateSize();
                if (estimateSize >= 0) {
                    return estimateSize;
                }
                return Long.MAX_VALUE;
            }
            return this.bSpliterator.estimateSize();
        }

        @Override
        public void forEachRemaining(Consumer consumer) {
            if (this.beforeSplit) {
                this.aSpliterator.forEachRemaining(consumer);
            }
            this.bSpliterator.forEachRemaining(consumer);
        }

        @Override
        public Comparator getComparator() {
            if (this.beforeSplit) {
                throw new IllegalStateException();
            }
            return this.bSpliterator.getComparator();
        }

        @Override
        public long getExactSizeIfKnown() {
            return Spliterator.-CC.$default$getExactSizeIfKnown(this);
        }

        @Override
        public boolean hasCharacteristics(int i) {
            return Spliterator.-CC.$default$hasCharacteristics(this, i);
        }

        @Override
        public boolean tryAdvance(Consumer consumer) {
            if (this.beforeSplit) {
                boolean tryAdvance = this.aSpliterator.tryAdvance(consumer);
                if (tryAdvance) {
                    return tryAdvance;
                }
                this.beforeSplit = false;
            }
            return this.bSpliterator.tryAdvance(consumer);
        }

        @Override
        public Spliterator trySplit() {
            Spliterator trySplit = this.beforeSplit ? this.aSpliterator : this.bSpliterator.trySplit();
            this.beforeSplit = false;
            return trySplit;
        }
    }

    public static Runnable composeWithExceptions(final Runnable runnable, final Runnable runnable2) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                    runnable2.run();
                } catch (Throwable th) {
                    try {
                        runnable2.run();
                    } catch (Throwable th2) {
                        try {
                            th.addSuppressed(th2);
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        };
    }

    public static Runnable composedClose(final BaseStream baseStream, final BaseStream baseStream2) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    BaseStream.this.close();
                    baseStream2.close();
                } catch (Throwable th) {
                    try {
                        baseStream2.close();
                    } catch (Throwable th2) {
                        try {
                            th.addSuppressed(th2);
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        };
    }
}
