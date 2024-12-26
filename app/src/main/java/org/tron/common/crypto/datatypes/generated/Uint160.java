package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint160 extends Uint {
    public static final Uint160 DEFAULT = new Uint160(BigInteger.ZERO);

    public Uint160(BigInteger bigInteger) {
        super(160, bigInteger);
    }

    public Uint160(long j) {
        this(BigInteger.valueOf(j));
    }
}
