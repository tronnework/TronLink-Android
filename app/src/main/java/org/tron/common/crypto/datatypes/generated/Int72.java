package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int72 extends Int {
    public static final Int72 DEFAULT = new Int72(BigInteger.ZERO);

    public Int72(BigInteger bigInteger) {
        super(72, bigInteger);
    }

    public Int72(long j) {
        this(BigInteger.valueOf(j));
    }
}
