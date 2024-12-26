package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes4 extends Bytes {
    public static final Bytes4 DEFAULT = new Bytes4(new byte[4]);

    public Bytes4(byte[] bArr) {
        super(4, bArr);
    }
}
