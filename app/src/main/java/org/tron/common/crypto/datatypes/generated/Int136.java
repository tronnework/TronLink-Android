package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int136 extends Int {
    public static final Int136 DEFAULT = new Int136(BigInteger.ZERO);

    public Int136(BigInteger bigInteger) {
        super(136, bigInteger);
    }

    public Int136(long j) {
        this(BigInteger.valueOf(j));
    }
}
