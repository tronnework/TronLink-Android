package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.NumericType;
import org.tron.common.crypto.datatypes.generated.Int32;
public final class Int extends Number<Integer> {
    public Int(int i) {
        super(Integer.valueOf(i));
    }

    @Override
    public NumericType toSolidityType() {
        return new Int32(((Integer) getValue()).intValue());
    }
}
