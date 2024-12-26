package j$.util.stream;

import j$.util.Objects;
import java.util.function.DoubleConsumer;
public final class DoublePipeline$ExternalSyntheticLambda5 implements DoubleConsumer {
    public final Sink f$0;

    @Override
    public final void accept(double d) {
        this.f$0.accept(d);
    }

    public DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return Objects.requireNonNull(doubleConsumer);
    }
}
