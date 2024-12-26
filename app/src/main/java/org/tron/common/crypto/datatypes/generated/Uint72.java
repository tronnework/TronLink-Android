package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint72 extends Uint {
    public static final Uint72 DEFAULT = new Uint72(BigInteger.ZERO);

    public Uint72(BigInteger bigInteger) {
        super(72, bigInteger);
    }

    public Uint72(long j) {
        this(BigInteger.valueOf(j));
    }
}
