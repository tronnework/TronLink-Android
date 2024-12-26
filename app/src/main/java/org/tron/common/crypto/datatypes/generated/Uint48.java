package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint48 extends Uint {
    public static final Uint48 DEFAULT = new Uint48(BigInteger.ZERO);

    public Uint48(BigInteger bigInteger) {
        super(48, bigInteger);
    }

    public Uint48(long j) {
        this(BigInteger.valueOf(j));
    }
}
