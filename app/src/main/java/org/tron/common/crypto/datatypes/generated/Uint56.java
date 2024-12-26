package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint56 extends Uint {
    public static final Uint56 DEFAULT = new Uint56(BigInteger.ZERO);

    public Uint56(BigInteger bigInteger) {
        super(56, bigInteger);
    }

    public Uint56(long j) {
        this(BigInteger.valueOf(j));
    }
}
