package j$.util;

import java.io.Serializable;
import java.util.function.Function;
public final class Comparator$ExternalSyntheticLambda2 implements java.util.Comparator, Serializable {
    public final java.util.Comparator f$0;
    public final Function f$1;

    public Comparator$ExternalSyntheticLambda2(java.util.Comparator comparator, Function function) {
        this.f$0 = comparator;
        this.f$1 = function;
    }

    @Override
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = this.f$0.compare(r1.apply(obj), this.f$1.apply(obj2));
        return compare;
    }
}
