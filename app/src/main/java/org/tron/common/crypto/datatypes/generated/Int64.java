package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int64 extends Int {
    public static final Int64 DEFAULT = new Int64(BigInteger.ZERO);

    public Int64(BigInteger bigInteger) {
        super(64, bigInteger);
    }

    public Int64(long j) {
        this(BigInteger.valueOf(j));
    }
}
