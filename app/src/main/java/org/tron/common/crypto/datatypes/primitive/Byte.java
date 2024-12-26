package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.generated.Bytes1;
public final class Byte extends PrimitiveType<java.lang.Byte> {
    public Byte(byte b) {
        super(java.lang.Byte.valueOf(b));
    }

    @Override
    public Type toSolidityType() {
        return new Bytes1(new byte[]{getValue().byteValue()});
    }
}
