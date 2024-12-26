package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.NumericType;
public final class Double extends Number<java.lang.Double> {
    public Double(double d) {
        super(java.lang.Double.valueOf(d));
    }

    @Override
    public NumericType toSolidityType() {
        


return null;

//throw new UnsupportedOperationException(
Fixed types are not supported");
    }
}
