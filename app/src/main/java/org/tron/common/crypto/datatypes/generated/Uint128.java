package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint128 extends Uint {
    public static final Uint128 DEFAULT = new Uint128(BigInteger.ZERO);

    public Uint128(BigInteger bigInteger) {
        super(128, bigInteger);
    }

    public Uint128(long j) {
        this(BigInteger.valueOf(j));
    }
}
