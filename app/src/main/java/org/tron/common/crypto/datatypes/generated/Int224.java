package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int224 extends Int {
    public static final Int224 DEFAULT = new Int224(BigInteger.ZERO);

    public Int224(BigInteger bigInteger) {
        super(224, bigInteger);
    }

    public Int224(long j) {
        this(BigInteger.valueOf(j));
    }
}
