package org.tron.common.crypto.datatypes.generated;

import com.facebook.imageutils.JfifUtil;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int208 extends Int {
    public static final Int208 DEFAULT = new Int208(BigInteger.ZERO);

    public Int208(BigInteger bigInteger) {
        super(JfifUtil.MARKER_RST0, bigInteger);
    }

    public Int208(long j) {
        this(BigInteger.valueOf(j));
    }
}
