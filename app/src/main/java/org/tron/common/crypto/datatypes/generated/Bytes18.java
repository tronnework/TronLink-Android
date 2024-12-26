package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes18 extends Bytes {
    public static final Bytes18 DEFAULT = new Bytes18(new byte[18]);

    public Bytes18(byte[] bArr) {
        super(18, bArr);
    }
}
