package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes21 extends Bytes {
    public static final Bytes21 DEFAULT = new Bytes21(new byte[21]);

    public Bytes21(byte[] bArr) {
        super(21, bArr);
    }
}
