package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint112 extends Uint {
    public static final Uint112 DEFAULT = new Uint112(BigInteger.ZERO);

    public Uint112(BigInteger bigInteger) {
        super(112, bigInteger);
    }

    public Uint112(long j) {
        this(BigInteger.valueOf(j));
    }
}
