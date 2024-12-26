package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int88 extends Int {
    public static final Int88 DEFAULT = new Int88(BigInteger.ZERO);

    public Int88(BigInteger bigInteger) {
        super(88, bigInteger);
    }

    public Int88(long j) {
        this(BigInteger.valueOf(j));
    }
}
