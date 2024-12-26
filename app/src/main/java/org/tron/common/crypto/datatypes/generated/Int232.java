package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int232 extends Int {
    public static final Int232 DEFAULT = new Int232(BigInteger.ZERO);

    public Int232(BigInteger bigInteger) {
        super(232, bigInteger);
    }

    public Int232(long j) {
        this(BigInteger.valueOf(j));
    }
}
