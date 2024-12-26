package org.tron.common.crypto.datatypes.generated;

import com.facebook.imageutils.JfifUtil;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int216 extends Int {
    public static final Int216 DEFAULT = new Int216(BigInteger.ZERO);

    public Int216(BigInteger bigInteger) {
        super(JfifUtil.MARKER_SOI, bigInteger);
    }

    public Int216(long j) {
        this(BigInteger.valueOf(j));
    }
}
