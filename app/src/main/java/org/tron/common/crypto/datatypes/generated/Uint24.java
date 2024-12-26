package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint24 extends Uint {
    public static final Uint24 DEFAULT = new Uint24(BigInteger.ZERO);

    public Uint24(BigInteger bigInteger) {
        super(24, bigInteger);
    }

    public Uint24(long j) {
        this(BigInteger.valueOf(j));
    }
}
