package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int48 extends Int {
    public static final Int48 DEFAULT = new Int48(BigInteger.ZERO);

    public Int48(BigInteger bigInteger) {
        super(48, bigInteger);
    }

    public Int48(long j) {
        this(BigInteger.valueOf(j));
    }
}
