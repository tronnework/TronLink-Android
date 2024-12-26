package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint168 extends Uint {
    public static final Uint168 DEFAULT = new Uint168(BigInteger.ZERO);

    public Uint168(BigInteger bigInteger) {
        super(168, bigInteger);
    }

    public Uint168(long j) {
        this(BigInteger.valueOf(j));
    }
}
