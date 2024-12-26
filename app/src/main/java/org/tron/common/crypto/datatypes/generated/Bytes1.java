package org.tron.common.crypto.datatypes.generated;

import org.tron.common.crypto.datatypes.Bytes;
public class Bytes1 extends Bytes {
    public static final Bytes1 DEFAULT = new Bytes1(new byte[1]);

    public Bytes1(byte[] bArr) {
        super(1, bArr);
    }
}
