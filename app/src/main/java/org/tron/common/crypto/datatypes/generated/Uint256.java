package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint256 extends Uint {
    public static final Uint256 DEFAULT = new Uint256(BigInteger.ZERO);

    public Uint256(BigInteger bigInteger) {
        super(256, bigInteger);
    }

    public Uint256(long j) {
        this(BigInteger.valueOf(j));
    }
}
