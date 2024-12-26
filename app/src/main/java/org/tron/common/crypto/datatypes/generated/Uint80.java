package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint80 extends Uint {
    public static final Uint80 DEFAULT = new Uint80(BigInteger.ZERO);

    public Uint80(BigInteger bigInteger) {
        super(80, bigInteger);
    }

    public Uint80(long j) {
        this(BigInteger.valueOf(j));
    }
}
