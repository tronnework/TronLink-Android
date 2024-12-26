package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int144 extends Int {
    public static final Int144 DEFAULT = new Int144(BigInteger.ZERO);

    public Int144(BigInteger bigInteger) {
        super(144, bigInteger);
    }

    public Int144(long j) {
        this(BigInteger.valueOf(j));
    }
}
