package org.tron.common.crypto.datatypes.generated;

import com.facebook.imageutils.JfifUtil;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint208 extends Uint {
    public static final Uint208 DEFAULT = new Uint208(BigInteger.ZERO);

    public Uint208(BigInteger bigInteger) {
        super(JfifUtil.MARKER_RST0, bigInteger);
    }

    public Uint208(long j) {
        this(BigInteger.valueOf(j));
    }
}
