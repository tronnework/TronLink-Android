package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes31 extends Bytes {
    public static final Bytes31 DEFAULT = new Bytes31(new byte[31]);

    public Bytes31(byte[] bArr) {
        super(31, bArr);
    }
}
