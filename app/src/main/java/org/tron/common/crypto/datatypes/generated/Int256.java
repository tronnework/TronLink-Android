package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int256 extends Int {
    public static final Int256 DEFAULT = new Int256(BigInteger.ZERO);

    public Int256(BigInteger bigInteger) {
        super(256, bigInteger);
    }

    public Int256(long j) {
        this(BigInteger.valueOf(j));
    }
}
