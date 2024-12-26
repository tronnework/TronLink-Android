package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int104 extends Int {
    public static final Int104 DEFAULT = new Int104(BigInteger.ZERO);

    public Int104(BigInteger bigInteger) {
        super(104, bigInteger);
    }

    public Int104(long j) {
        this(BigInteger.valueOf(j));
    }
}
