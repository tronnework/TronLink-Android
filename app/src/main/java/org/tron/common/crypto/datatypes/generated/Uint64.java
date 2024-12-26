package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint64 extends Uint {
    public static final Uint64 DEFAULT = new Uint64(BigInteger.ZERO);

    public Uint64(BigInteger bigInteger) {
        super(64, bigInteger);
    }

    public Uint64(long j) {
        this(BigInteger.valueOf(j));
    }
}
