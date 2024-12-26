package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint144 extends Uint {
    public static final Uint144 DEFAULT = new Uint144(BigInteger.ZERO);

    public Uint144(BigInteger bigInteger) {
        super(144, bigInteger);
    }

    public Uint144(long j) {
        this(BigInteger.valueOf(j));
    }
}
