package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint136 extends Uint {
    public static final Uint136 DEFAULT = new Uint136(BigInteger.ZERO);

    public Uint136(BigInteger bigInteger) {
        super(136, bigInteger);
    }

    public Uint136(long j) {
        this(BigInteger.valueOf(j));
    }
}
