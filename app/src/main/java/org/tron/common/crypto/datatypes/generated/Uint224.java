package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint224 extends Uint {
    public static final Uint224 DEFAULT = new Uint224(BigInteger.ZERO);

    public Uint224(BigInteger bigInteger) {
        super(224, bigInteger);
    }

    public Uint224(long j) {
        this(BigInteger.valueOf(j));
    }
}
