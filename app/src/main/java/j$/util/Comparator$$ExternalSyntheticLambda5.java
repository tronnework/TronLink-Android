package j$.util;

import java.io.Serializable;
import java.util.function.Function;
public final class Comparator$ExternalSyntheticLambda5 implements java.util.Comparator, Serializable {
    public final Function f$0;

    public Comparator$ExternalSyntheticLambda5(Function function) {
        this.f$0 = function;
    }

    @Override
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((Comparable) r0.apply(obj)).compareTo(this.f$0.apply(obj2));
        return compareTo;
    }
}
