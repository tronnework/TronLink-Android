package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int112 extends Int {
    public static final Int112 DEFAULT = new Int112(BigInteger.ZERO);

    public Int112(BigInteger bigInteger) {
        super(112, bigInteger);
    }

    public Int112(long j) {
        this(BigInteger.valueOf(j));
    }
}
