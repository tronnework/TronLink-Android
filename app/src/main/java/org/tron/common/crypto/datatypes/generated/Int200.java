package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int200 extends Int {
    public static final Int200 DEFAULT = new Int200(BigInteger.ZERO);

    public Int200(BigInteger bigInteger) {
        super(200, bigInteger);
    }

    public Int200(long j) {
        this(BigInteger.valueOf(j));
    }
}
