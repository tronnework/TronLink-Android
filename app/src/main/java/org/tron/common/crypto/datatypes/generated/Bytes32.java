package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes32 extends Bytes {
    public static final Bytes32 DEFAULT = new Bytes32(new byte[32]);

    public Bytes32(byte[] bArr) {
        super(32, bArr);
    }
}
