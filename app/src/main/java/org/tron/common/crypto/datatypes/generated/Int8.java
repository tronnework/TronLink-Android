package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int8 extends Int {
    public static final Int8 DEFAULT = new Int8(BigInteger.ZERO);

    public Int8(BigInteger bigInteger) {
        super(8, bigInteger);
    }

    public Int8(long j) {
        this(BigInteger.valueOf(j));
    }
}
