package j$.util;

import java.io.Serializable;
import java.util.function.ToDoubleFunction;
public final class Comparator$ExternalSyntheticLambda0 implements java.util.Comparator, Serializable {
    public final ToDoubleFunction f$0;

    public Comparator$ExternalSyntheticLambda0(ToDoubleFunction toDoubleFunction) {
        this.f$0 = toDoubleFunction;
    }

    @Override
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Double.compare(r0.applyAsDouble(obj), this.f$0.applyAsDouble(obj2));
        return compare;
    }
}
