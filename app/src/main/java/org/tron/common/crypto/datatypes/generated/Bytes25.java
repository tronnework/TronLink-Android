package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes25 extends Bytes {
    public static final Bytes25 DEFAULT = new Bytes25(new byte[25]);

    public Bytes25(byte[] bArr) {
        super(25, bArr);
    }
}
