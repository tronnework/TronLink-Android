package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int240 extends Int {
    public static final Int240 DEFAULT = new Int240(BigInteger.ZERO);

    public Int240(BigInteger bigInteger) {
        super(240, bigInteger);
    }

    public Int240(long j) {
        this(BigInteger.valueOf(j));
    }
}
