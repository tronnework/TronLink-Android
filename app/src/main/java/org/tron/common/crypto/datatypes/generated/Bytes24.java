package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes24 extends Bytes {
    public static final Bytes24 DEFAULT = new Bytes24(new byte[24]);

    public Bytes24(byte[] bArr) {
        super(24, bArr);
    }
}
