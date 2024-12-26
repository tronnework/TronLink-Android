package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint40 extends Uint {
    public static final Uint40 DEFAULT = new Uint40(BigInteger.ZERO);

    public Uint40(BigInteger bigInteger) {
        super(40, bigInteger);
    }

    public Uint40(long j) {
        this(BigInteger.valueOf(j));
    }
}
