package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint192 extends Uint {
    public static final Uint192 DEFAULT = new Uint192(BigInteger.ZERO);

    public Uint192(BigInteger bigInteger) {
        super(192, bigInteger);
    }

    public Uint192(long j) {
        this(BigInteger.valueOf(j));
    }
}
