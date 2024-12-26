package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int128 extends Int {
    public static final Int128 DEFAULT = new Int128(BigInteger.ZERO);

    public Int128(BigInteger bigInteger) {
        super(128, bigInteger);
    }

    public Int128(long j) {
        this(BigInteger.valueOf(j));
    }
}
