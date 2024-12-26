package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint104 extends Uint {
    public static final Uint104 DEFAULT = new Uint104(BigInteger.ZERO);

    public Uint104(BigInteger bigInteger) {
        super(104, bigInteger);
    }

    public Uint104(long j) {
        this(BigInteger.valueOf(j));
    }
}
