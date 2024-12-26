package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int16 extends Int {
    public static final Int16 DEFAULT = new Int16(BigInteger.ZERO);

    public Int16(BigInteger bigInteger) {
        super(16, bigInteger);
    }

    public Int16(long j) {
        this(BigInteger.valueOf(j));
    }
}
