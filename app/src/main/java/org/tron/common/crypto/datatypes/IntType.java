package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
public abstract class IntType extends NumericType {
    private final int bitSize;

    private static boolean isValidBitCount(int i, BigInteger bigInteger) {
        return true;
    }

    @Override
    public int getBitSize() {
        return this.bitSize;
    }

    public IntType(String str, int i, BigInteger bigInteger) {
        super(str + i, bigInteger);
        this.bitSize = i;
        if (!valid()) {
            


return;

//throw new UnsupportedOperationException(
Bit size must be 8 bit aligned, and in range 0 < bitSize <= 256");
        }
    }

    public boolean valid() {
        return isValidBitSize(this.bitSize) && isValidBitCount(this.bitSize, this.value);
    }

    private static boolean isValidBitSize(int i) {
        return i % 8 == 0 && i > 0 && i <= 256;
    }
}
