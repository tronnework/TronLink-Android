package org.tron.common.crypto.datatypes.generated;

import com.alibaba.fastjson.asm.Opcodes;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint176 extends Uint {
    public static final Uint176 DEFAULT = new Uint176(BigInteger.ZERO);

    public Uint176(BigInteger bigInteger) {
        super(Opcodes.ARETURN, bigInteger);
    }

    public Uint176(long j) {
        this(BigInteger.valueOf(j));
    }
}
