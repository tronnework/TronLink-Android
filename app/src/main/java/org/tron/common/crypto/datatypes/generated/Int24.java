package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int24 extends Int {
    public static final Int24 DEFAULT = new Int24(BigInteger.ZERO);

    public Int24(BigInteger bigInteger) {
        super(24, bigInteger);
    }

    public Int24(long j) {
        this(BigInteger.valueOf(j));
    }
}
