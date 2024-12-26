package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint88 extends Uint {
    public static final Uint88 DEFAULT = new Uint88(BigInteger.ZERO);

    public Uint88(BigInteger bigInteger) {
        super(88, bigInteger);
    }

    public Uint88(long j) {
        this(BigInteger.valueOf(j));
    }
}
