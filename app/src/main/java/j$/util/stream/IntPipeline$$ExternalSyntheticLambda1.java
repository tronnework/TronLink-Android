package j$.util.stream;

import j$.util.Objects;
import java.util.function.IntConsumer;
public final class IntPipeline$ExternalSyntheticLambda1 implements IntConsumer {
    public final Sink f$0;

    @Override
    public final void accept(int i) {
        this.f$0.accept(i);
    }

    public IntConsumer andThen(IntConsumer intConsumer) {
        return Objects.requireNonNull(intConsumer);
    }
}
