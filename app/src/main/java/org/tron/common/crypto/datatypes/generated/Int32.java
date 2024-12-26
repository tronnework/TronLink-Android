package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int32 extends Int {
    public static final Int32 DEFAULT = new Int32(BigInteger.ZERO);

    public Int32(BigInteger bigInteger) {
        super(32, bigInteger);
    }

    public Int32(long j) {
        this(BigInteger.valueOf(j));
    }
}
