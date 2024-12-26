package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint32 extends Uint {
    public static final Uint32 DEFAULT = new Uint32(BigInteger.ZERO);

    public Uint32(BigInteger bigInteger) {
        super(32, bigInteger);
    }

    public Uint32(long j) {
        this(BigInteger.valueOf(j));
    }
}
