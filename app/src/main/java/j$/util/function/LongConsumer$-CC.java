package j$.util.function;

import java.util.function.LongConsumer;
public abstract class LongConsumer$-CC {
    public static void $private$lambda$andThen$0(LongConsumer longConsumer, LongConsumer longConsumer2, long j) {
        longConsumer.accept(j);
        longConsumer2.accept(j);
    }
}
