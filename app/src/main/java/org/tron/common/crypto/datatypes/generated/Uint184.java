package org.tron.common.crypto.datatypes.generated;

import com.alibaba.fastjson.asm.Opcodes;
import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Uint;
public class Uint184 extends Uint {
    public static final Uint184 DEFAULT = new Uint184(BigInteger.ZERO);

    public Uint184(BigInteger bigInteger) {
        super(Opcodes.INVOKESTATIC, bigInteger);
    }

    public Uint184(long j) {
        this(BigInteger.valueOf(j));
    }
}
