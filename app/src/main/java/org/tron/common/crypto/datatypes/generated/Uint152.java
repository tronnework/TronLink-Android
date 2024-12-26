package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint152 extends Uint {
    public static final Uint152 DEFAULT = new Uint152(BigInteger.ZERO);

    public Uint152(BigInteger bigInteger) {
        super(152, bigInteger);
    }

    public Uint152(long j) {
        this(BigInteger.valueOf(j));
    }
}
