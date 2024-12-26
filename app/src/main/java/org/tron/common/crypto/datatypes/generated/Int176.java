package org.tron.common.crypto.datatypes.generated;

import com.alibaba.fastjson.asm.Opcodes;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int176 extends Int {
    public static final Int176 DEFAULT = new Int176(BigInteger.ZERO);

    public Int176(BigInteger bigInteger) {
        super(Opcodes.ARETURN, bigInteger);
    }

    public Int176(long j) {
        this(BigInteger.valueOf(j));
    }
}
