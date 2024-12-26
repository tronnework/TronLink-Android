package j$.util;

import java.io.Serializable;
import java.util.function.ToLongFunction;
public final class Comparator$ExternalSyntheticLambda4 implements java.util.Comparator, Serializable {
    public final ToLongFunction f$0;

    public Comparator$ExternalSyntheticLambda4(ToLongFunction toLongFunction) {
        this.f$0 = toLongFunction;
    }

    @Override
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Long.compare(r0.applyAsLong(obj), this.f$0.applyAsLong(obj2));
        return compare;
    }
}
