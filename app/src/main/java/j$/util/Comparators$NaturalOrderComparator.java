package j$.util;

import j$.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
public enum Comparators$NaturalOrderComparator implements java.util.Comparator, Comparator {
    INSTANCE;

    @Override
    public int compare(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    @Override
    public java.util.Comparator reversed() {
        return Comparator.-CC.reverseOrder();
    }

    @Override
    public java.util.Comparator thenComparing(java.util.Comparator comparator) {
        return Comparator.-CC.$default$thenComparing(this, comparator);
    }

    @Override
    public java.util.Comparator thenComparing(Function function) {
        java.util.Comparator thenComparing;
        thenComparing = Comparator.-EL.thenComparing(this, Comparator.-CC.comparing(function));
        return thenComparing;
    }

    @Override
    public java.util.Comparator thenComparing(Function function, java.util.Comparator comparator) {
        java.util.Comparator thenComparing;
        thenComparing = Comparator.-EL.thenComparing(this, Comparator.-CC.comparing(function, comparator));
        return thenComparing;
    }

    @Override
    public java.util.Comparator thenComparingDouble(ToDoubleFunction toDoubleFunction) {
        java.util.Comparator thenComparing;
        thenComparing = Comparator.-EL.thenComparing(this, Comparator.-CC.comparingDouble(toDoubleFunction));
        return thenComparing;
    }

    @Override
    public java.util.Comparator thenComparingInt(ToIntFunction toIntFunction) {
        java.util.Comparator thenComparing;
        thenComparing = Comparator.-EL.thenComparing(this, Comparator.-CC.comparingInt(toIntFunction));
        return thenComparing;
    }

    @Override
    public java.util.Comparator thenComparingLong(ToLongFunction toLongFunction) {
        java.util.Comparator thenComparing;
        thenComparing = Comparator.-EL.thenComparing(this, Comparator.-CC.comparingLong(toLongFunction));
        return thenComparing;
    }
}
