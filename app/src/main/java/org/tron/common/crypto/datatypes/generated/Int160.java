package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int160 extends Int {
    public static final Int160 DEFAULT = new Int160(BigInteger.ZERO);

    public Int160(BigInteger bigInteger) {
        super(160, bigInteger);
    }

    public Int160(long j) {
        this(BigInteger.valueOf(j));
    }
}
