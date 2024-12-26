package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int56 extends Int {
    public static final Int56 DEFAULT = new Int56(BigInteger.ZERO);

    public Int56(BigInteger bigInteger) {
        super(56, bigInteger);
    }

    public Int56(long j) {
        this(BigInteger.valueOf(j));
    }
}
