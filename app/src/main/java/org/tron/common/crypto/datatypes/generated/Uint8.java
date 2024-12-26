package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint8 extends Uint {
    public static final Uint8 DEFAULT = new Uint8(BigInteger.ZERO);

    public Uint8(BigInteger bigInteger) {
        super(8, bigInteger);
    }

    public Uint8(long j) {
        this(BigInteger.valueOf(j));
    }
}
