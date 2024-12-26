package org.tron.common.crypto.datatypes.generated;

import com.facebook.imageutils.JfifUtil;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint216 extends Uint {
    public static final Uint216 DEFAULT = new Uint216(BigInteger.ZERO);

    public Uint216(BigInteger bigInteger) {
        super(JfifUtil.MARKER_SOI, bigInteger);
    }

    public Uint216(long j) {
        this(BigInteger.valueOf(j));
    }
}
