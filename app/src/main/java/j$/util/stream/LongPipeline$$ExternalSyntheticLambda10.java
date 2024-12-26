package j$.util.stream;

import j$.util.Objects;
import java.util.function.LongConsumer;
public final class LongPipeline$ExternalSyntheticLambda10 implements LongConsumer {
    public final Sink f$0;

    @Override
    public final void accept(long j) {
        this.f$0.accept(j);
    }

    public LongConsumer andThen(LongConsumer longConsumer) {
        return Objects.requireNonNull(longConsumer);
    }
}
