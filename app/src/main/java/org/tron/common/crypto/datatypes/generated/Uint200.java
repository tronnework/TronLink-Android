package org.tron.common.crypto.datatypes.generated;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint200 extends Uint {
    public static final Uint200 DEFAULT = new Uint200(BigInteger.ZERO);

    public Uint200(BigInteger bigInteger) {
        super(200, bigInteger);
    }

    public Uint200(long j) {
        this(BigInteger.valueOf(j));
    }
}
