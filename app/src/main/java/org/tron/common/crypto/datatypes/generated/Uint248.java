package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint248 extends Uint {
    public static final Uint248 DEFAULT = new Uint248(BigInteger.ZERO);

    public Uint248(BigInteger bigInteger) {
        super(248, bigInteger);
    }

    public Uint248(long j) {
        this(BigInteger.valueOf(j));
    }
}
