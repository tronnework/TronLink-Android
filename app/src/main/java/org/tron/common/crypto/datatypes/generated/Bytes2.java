package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes2 extends Bytes {
    public static final Bytes2 DEFAULT = new Bytes2(new byte[2]);

    public Bytes2(byte[] bArr) {
        super(2, bArr);
    }
}
