package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes3 extends Bytes {
    public static final Bytes3 DEFAULT = new Bytes3(new byte[3]);

    public Bytes3(byte[] bArr) {
        super(3, bArr);
    }
}
