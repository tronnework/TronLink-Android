package j$.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
public final class PrimitiveIterator$OfDouble$ExternalSyntheticLambda0 implements DoubleConsumer {
    public final Consumer f$0;

    public PrimitiveIterator$OfDouble$ExternalSyntheticLambda0(Consumer consumer) {
        this.f$0 = consumer;
    }

    @Override
    public final void accept(double d) {
        this.f$0.accept(Double.valueOf(d));
    }

    public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return Objects.requireNonNull(doubleConsumer);
    }
}
