package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.NumericType;
import org.tron.common.crypto.datatypes.generated.Int16;
public final class Short extends Number<java.lang.Short> {
    public Short(short s) {
        super(java.lang.Short.valueOf(s));
    }

    @Override
    public NumericType toSolidityType() {
        return new Int16(((java.lang.Short) getValue()).shortValue());
    }
}
