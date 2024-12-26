package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
public class Int extends IntType {
    public static final Int DEFAULT = new Int(BigInteger.ZERO);
    public static final String TYPE_NAME = "int";

    public Int(BigInteger bigInteger) {
        this(256, bigInteger);
    }

    public Int(int i, BigInteger bigInteger) {
        super(TYPE_NAME, i, bigInteger);
    }
}
