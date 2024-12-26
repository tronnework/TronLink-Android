package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int96 extends Int {
    public static final Int96 DEFAULT = new Int96(BigInteger.ZERO);

    public Int96(BigInteger bigInteger) {
        super(96, bigInteger);
    }

    public Int96(long j) {
        this(BigInteger.valueOf(j));
    }
}
