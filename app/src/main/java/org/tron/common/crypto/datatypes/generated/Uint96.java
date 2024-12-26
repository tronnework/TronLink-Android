package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint96 extends Uint {
    public static final Uint96 DEFAULT = new Uint96(BigInteger.ZERO);

    public Uint96(BigInteger bigInteger) {
        super(96, bigInteger);
    }

    public Uint96(long j) {
        this(BigInteger.valueOf(j));
    }
}
