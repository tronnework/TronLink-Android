package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int192 extends Int {
    public static final Int192 DEFAULT = new Int192(BigInteger.ZERO);

    public Int192(BigInteger bigInteger) {
        super(192, bigInteger);
    }

    public Int192(long j) {
        this(BigInteger.valueOf(j));
    }
}
