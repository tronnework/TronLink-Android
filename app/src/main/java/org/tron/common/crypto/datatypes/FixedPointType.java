package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
public abstract class FixedPointType extends NumericType {
    static final int DEFAULT_BIT_LENGTH = 128;
    private final int bitSize;

    @Override
    public int getBitSize() {
        return this.bitSize;
    }

    public FixedPointType(String str, int i, int i2, BigInteger bigInteger) {
        super(str + i + "x" + i2, bigInteger);
        this.bitSize = i + i2;
        if (!valid(i, i2, bigInteger)) {
            


return;

//throw new UnsupportedOperationException(
Bitsize must be 8 bit aligned, and in range 0 < bitSize <= 256");
        }
    }

    public boolean valid(int i, int i2, BigInteger bigInteger) {
        return isValidBitSize(i, i2) && isValidBitCount(i, i2, bigInteger);
    }

    private boolean isValidBitSize(int i, int i2) {
        int i3;
        return i % 8 == 0 && i2 % 8 == 0 && (i3 = this.bitSize) > 0 && i3 <= 256;
    }

    private static boolean isValidBitCount(int i, int i2, BigInteger bigInteger) {
        return bigInteger.bitCount() <= i + i2;
    }

    public static BigInteger convert(BigInteger bigInteger, BigInteger bigInteger2) {
        return convert(128, 128, bigInteger, bigInteger2);
    }

    public static BigInteger convert(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.shiftLeft(i2).or(bigInteger2.shiftLeft(i2 - ((bigInteger2.bitLength() + 3) & (-4))));
    }
}
