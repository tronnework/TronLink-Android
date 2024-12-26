package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint120 extends Uint {
    public static final Uint120 DEFAULT = new Uint120(BigInteger.ZERO);

    public Uint120(BigInteger bigInteger) {
        super(120, bigInteger);
    }

    public Uint120(long j) {
        this(BigInteger.valueOf(j));
    }
}
