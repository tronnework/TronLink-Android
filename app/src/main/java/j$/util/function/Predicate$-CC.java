package j$.util.function;

import j$.util.Objects;
import java.util.function.Predicate;
public final class Predicate$-CC {
    public static Predicate $default$negate(final Predicate predicate) {
        return new Predicate() {
            public Predicate and(Predicate predicate2) {
                return Objects.requireNonNull(predicate2);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate2) {
                return Objects.requireNonNull(predicate2);
            }

            @Override
            public final boolean test(Object obj) {
                return Predicate$-CC.$private$lambda$negate$1(Predicate.this, obj);
            }
        };
    }

    public static boolean $private$lambda$and$0(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) && predicate2.test(obj);
    }

    public static boolean $private$lambda$negate$1(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    public static boolean $private$lambda$or$2(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) || predicate2.test(obj);
    }
}
