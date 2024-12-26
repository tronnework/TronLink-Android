package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.NumericType;
import org.tron.common.crypto.datatypes.generated.Int64;
public final class Long extends Number<java.lang.Long> {
    public Long(long j) {
        super(java.lang.Long.valueOf(j));
    }

    @Override
    public NumericType toSolidityType() {
        return new Int64(((java.lang.Long) getValue()).longValue());
    }
}
