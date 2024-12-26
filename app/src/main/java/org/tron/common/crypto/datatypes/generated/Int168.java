package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int168 extends Int {
    public static final Int168 DEFAULT = new Int168(BigInteger.ZERO);

    public Int168(BigInteger bigInteger) {
        super(168, bigInteger);
    }

    public Int168(long j) {
        this(BigInteger.valueOf(j));
    }
}
