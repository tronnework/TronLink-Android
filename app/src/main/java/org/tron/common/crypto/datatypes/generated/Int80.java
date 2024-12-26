package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int80 extends Int {
    public static final Int80 DEFAULT = new Int80(BigInteger.ZERO);

    public Int80(BigInteger bigInteger) {
        super(80, bigInteger);
    }

    public Int80(long j) {
        this(BigInteger.valueOf(j));
    }
}
