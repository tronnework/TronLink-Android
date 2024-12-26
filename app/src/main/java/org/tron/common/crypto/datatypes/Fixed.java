package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
public class Fixed extends FixedPointType {
    public static final Fixed DEFAULT = new Fixed(BigInteger.ZERO);
    public static final String TYPE_NAME = "fixed";

    protected Fixed(int i, int i2, BigInteger bigInteger) {
        super(TYPE_NAME, i, i2, bigInteger);
    }

    public Fixed(BigInteger bigInteger) {
        this(128, 128, bigInteger);
    }

    public Fixed(BigInteger bigInteger, BigInteger bigInteger2) {
        this(convert(bigInteger, bigInteger2));
    }

    protected Fixed(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
        this(convert(i, i2, bigInteger, bigInteger2));
    }
}
