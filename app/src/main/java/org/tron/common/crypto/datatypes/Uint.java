package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
public class Uint extends IntType {
    public static final Uint DEFAULT = new Uint(BigInteger.ZERO);
    public static final String TYPE_NAME = "uint";

    public Uint(int i, BigInteger bigInteger) {
        super(TYPE_NAME, i, bigInteger);
    }

    public Uint(BigInteger bigInteger) {
        this(256, bigInteger);
    }

    @Override
    public boolean valid() {
        return super.valid() && this.value.signum() >= 0;
    }
}
