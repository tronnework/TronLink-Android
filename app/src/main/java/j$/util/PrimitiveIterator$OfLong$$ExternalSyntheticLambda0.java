package j$.util;

import java.util.function.Consumer;
import java.util.function.LongConsumer;
public final class PrimitiveIterator$OfLong$ExternalSyntheticLambda0 implements LongConsumer {
    public final Consumer f$0;

    public PrimitiveIterator$OfLong$ExternalSyntheticLambda0(Consumer consumer) {
        this.f$0 = consumer;
    }

    @Override
    public final void accept(long j) {
        this.f$0.accept(Long.valueOf(j));
    }

    public LongConsumer andThen(LongConsumer longConsumer) {
        return Objects.requireNonNull(longConsumer);
    }
}
