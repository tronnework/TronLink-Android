package org.tron.common.crypto;

import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.function.Predicate;
public final class TypeDecoderExternalSyntheticLambda8 implements Predicate {
    public final Class f$0;

    public Predicate and(Predicate predicate) {
        return Objects.requireNonNull(predicate);
    }

    public Predicate negate() {
        return Predicate$-CC.$default$negate(this);
    }

    public Predicate or(Predicate predicate) {
        return Objects.requireNonNull(predicate);
    }

    @Override
    public final boolean test(Object obj) {
        return this.f$0.isAssignableFrom((Class) obj);
    }
}
