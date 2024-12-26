package j$.util.function;

import java.util.function.BiConsumer;
public final class BiConsumer$-CC {
    public static void $private$lambda$andThen$0(BiConsumer biConsumer, BiConsumer biConsumer2, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        biConsumer2.accept(obj, obj2);
    }
}
