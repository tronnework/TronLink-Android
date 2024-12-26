package j$.util.function;

import java.util.function.DoubleConsumer;
public abstract class DoubleConsumer$-CC {
    public static void $private$lambda$andThen$0(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2, double d) {
        doubleConsumer.accept(d);
        doubleConsumer2.accept(d);
    }
}
