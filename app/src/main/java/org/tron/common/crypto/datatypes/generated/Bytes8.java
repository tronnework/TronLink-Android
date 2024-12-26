package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes8 extends Bytes {
    public static final Bytes8 DEFAULT = new Bytes8(new byte[8]);

    public Bytes8(byte[] bArr) {
        super(8, bArr);
    }
}
