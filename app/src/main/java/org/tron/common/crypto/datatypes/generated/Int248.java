package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int248 extends Int {
    public static final Int248 DEFAULT = new Int248(BigInteger.ZERO);

    public Int248(BigInteger bigInteger) {
        super(248, bigInteger);
    }

    public Int248(long j) {
        this(BigInteger.valueOf(j));
    }
}
