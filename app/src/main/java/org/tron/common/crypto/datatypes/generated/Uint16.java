package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint16 extends Uint {
    public static final Uint16 DEFAULT = new Uint16(BigInteger.ZERO);

    public Uint16(BigInteger bigInteger) {
        super(16, bigInteger);
    }

    public Uint16(long j) {
        this(BigInteger.valueOf(j));
    }
}
