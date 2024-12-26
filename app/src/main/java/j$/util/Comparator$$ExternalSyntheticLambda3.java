package j$.util;

import java.io.Serializable;
import java.util.function.ToIntFunction;
public final class Comparator$ExternalSyntheticLambda3 implements java.util.Comparator, Serializable {
    public final ToIntFunction f$0;

    public Comparator$ExternalSyntheticLambda3(ToIntFunction toIntFunction) {
        this.f$0 = toIntFunction;
    }

    @Override
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(r0.applyAsInt(obj), this.f$0.applyAsInt(obj2));
        return compare;
    }
}
