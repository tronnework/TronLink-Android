package j$.util.function;

import java.util.function.Consumer;
public final class Consumer$-CC {
    public static void $private$lambda$andThen$0(Consumer consumer, Consumer consumer2, Object obj) {
        consumer.accept(obj);
        consumer2.accept(obj);
    }
}
