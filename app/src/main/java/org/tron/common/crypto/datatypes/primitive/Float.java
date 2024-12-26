package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.NumericType;
public class Float extends Number<java.lang.Float> {
    public Float(float f) {
        super(java.lang.Float.valueOf(f));
    }

    @Override
    public NumericType toSolidityType() {
        


return null;

//throw new UnsupportedOperationException(
Fixed types are not supported");
    }
}
