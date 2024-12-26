package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes30 extends Bytes {
    public static final Bytes30 DEFAULT = new Bytes30(new byte[30]);

    public Bytes30(byte[] bArr) {
        super(30, bArr);
    }
}
