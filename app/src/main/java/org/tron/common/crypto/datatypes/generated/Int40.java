package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int40 extends Int {
    public static final Int40 DEFAULT = new Int40(BigInteger.ZERO);

    public Int40(BigInteger bigInteger) {
        super(40, bigInteger);
    }

    public Int40(long j) {
        this(BigInteger.valueOf(j));
    }
}
