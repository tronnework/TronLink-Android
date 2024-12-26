package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint240 extends Uint {
    public static final Uint240 DEFAULT = new Uint240(BigInteger.ZERO);

    public Uint240(BigInteger bigInteger) {
        super(240, bigInteger);
    }

    public Uint240(long j) {
        this(BigInteger.valueOf(j));
    }
}
