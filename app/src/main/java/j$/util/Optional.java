package j$.util;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;
public final class Optional<T> {
    private static final Optional EMPTY = new Optional();
    private final Object value;

    private Optional() {
        this.value = null;
    }

    private Optional(Object obj) {
        this.value = Objects.requireNonNull(obj);
    }

    public static <T> Optional<T> empty() {
        return EMPTY;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional<>(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Optional) {
            return Objects.equals(this.value, ((Optional) obj).value);
        }
        return false;
    }

    public T get() {
        T t = (T) this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public void ifPresent(Consumer<? super T> consumer) {
        Object obj = (Object) this.value;
        if (obj != 0) {
            consumer.accept(obj);
        }
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T orElse(T t) {
        T t2 = (T) this.value;
        return t2 != null ? t2 : t;
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier) {
        T t = (T) this.value;
        if (t != null) {
            return t;
        }
        throw supplier.get();
    }

    public String toString() {
        Object obj = this.value;
        return obj != null ? String.format("Optional[%s]", obj) : "Optional.empty";
    }
}
