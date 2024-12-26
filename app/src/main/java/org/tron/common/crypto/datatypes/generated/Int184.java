package org.tron.common.crypto.datatypes.generated;

import com.alibaba.fastjson.asm.Opcodes;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Int;
public class Int184 extends Int {
    public static final Int184 DEFAULT = new Int184(BigInteger.ZERO);

    public Int184(BigInteger bigInteger) {
        super(Opcodes.INVOKESTATIC, bigInteger);
    }

    public Int184(long j) {
        this(BigInteger.valueOf(j));
    }
}
