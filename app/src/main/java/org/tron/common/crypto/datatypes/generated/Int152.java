package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int152 extends Int {
    public static final Int152 DEFAULT = new Int152(BigInteger.ZERO);

    public Int152(BigInteger bigInteger) {
        super(152, bigInteger);
    }

    public Int152(long j) {
        this(BigInteger.valueOf(j));
    }
}
