package org.tron.common.crypto.datatypes.primitive;

import java.lang.Comparable;
import java.lang.Number;
import org.tron.common.crypto.datatypes.NumericType;
public abstract class Number<T extends java.lang.Number & Comparable<T>> extends PrimitiveType<T> {
    @Override
    public abstract NumericType toSolidityType();

    public Number(T t) {
        super(t);
    }
}
