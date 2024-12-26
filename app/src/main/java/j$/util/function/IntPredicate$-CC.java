package j$.util.function;

import j$.util.Objects;
import java.util.function.IntPredicate;
public final class IntPredicate$-CC {
    public static IntPredicate $default$negate(final IntPredicate intPredicate) {
        return new IntPredicate() {
            public IntPredicate and(IntPredicate intPredicate2) {
                return Objects.requireNonNull(intPredicate2);
            }

            public IntPredicate negate() {
                return IntPredicate$-CC.$default$negate(this);
            }

            public IntPredicate or(IntPredicate intPredicate2) {
                return Objects.requireNonNull(intPredicate2);
            }

            @Override
            public final boolean test(int i) {
                return IntPredicate$-CC.$private$lambda$negate$1(IntPredicate.this, i);
            }
        };
    }

    public static boolean $private$lambda$and$0(IntPredicate intPredicate, IntPredicate intPredicate2, int i) {
        return intPredicate.test(i) && intPredicate2.test(i);
    }

    public static boolean $private$lambda$negate$1(IntPredicate intPredicate, int i) {
        return !intPredicate.test(i);
    }

    public static boolean $private$lambda$or$2(IntPredicate intPredicate, IntPredicate intPredicate2, int i) {
        return intPredicate.test(i) || intPredicate2.test(i);
    }
}
