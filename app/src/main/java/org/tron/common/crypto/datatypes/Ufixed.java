package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
public class Ufixed extends FixedPointType {
    public static final Ufixed DEFAULT = new Ufixed(BigInteger.ZERO);
    public static final String TYPE_NAME = "ufixed";

    protected Ufixed(int i, int i2, BigInteger bigInteger) {
        super(TYPE_NAME, i, i2, bigInteger);
    }

    public Ufixed(BigInteger bigInteger) {
        this(128, 128, bigInteger);
    }

    public Ufixed(BigInteger bigInteger, BigInteger bigInteger2) {
        this(convert(bigInteger, bigInteger2));
    }

    protected Ufixed(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2) {
        this(convert(i, i2, bigInteger, bigInteger2));
    }

    @Override
    public boolean valid(int i, int i2, BigInteger bigInteger) {
        return super.valid(i, i2, bigInteger) && bigInteger.signum() != -1;
    }
}
