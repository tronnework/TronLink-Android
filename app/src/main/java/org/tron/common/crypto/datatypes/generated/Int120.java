package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int120 extends Int {
    public static final Int120 DEFAULT = new Int120(BigInteger.ZERO);

    public Int120(BigInteger bigInteger) {
        super(120, bigInteger);
    }

    public Int120(long j) {
        this(BigInteger.valueOf(j));
    }
}
