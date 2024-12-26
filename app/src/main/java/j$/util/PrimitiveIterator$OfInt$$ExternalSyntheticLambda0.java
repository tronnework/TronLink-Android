package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
public final class PrimitiveIterator$OfInt$ExternalSyntheticLambda0 implements IntConsumer {
    public final Consumer f$0;

    public PrimitiveIterator$OfInt$ExternalSyntheticLambda0(Consumer consumer) {
        this.f$0 = consumer;
    }

    @Override
    public final void accept(int i) {
        this.f$0.accept(Integer.valueOf(i));
    }

    public IntConsumer andThen(IntConsumer intConsumer) {
        return Objects.requireNonNull(intConsumer);
    }
}
