package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint232 extends Uint {
    public static final Uint232 DEFAULT = new Uint232(BigInteger.ZERO);

    public Uint232(BigInteger bigInteger) {
        super(232, bigInteger);
    }

    public Uint232(long j) {
        this(BigInteger.valueOf(j));
    }
}
