package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes10 extends Bytes {
    public static final Bytes10 DEFAULT = new Bytes10(new byte[10]);

    public Bytes10(byte[] bArr) {
        super(10, bArr);
    }
}
