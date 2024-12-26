package org.tron.common.crypto.datatypes;
public class Bytes extends BytesType {
    public static final String TYPE_NAME = "bytes";

    public Bytes(int i, byte[] bArr) {
        super(bArr, "bytes" + bArr.length);
        if (!isValid(i)) {
            


return;

//throw new UnsupportedOperationException(
Input byte array must be in range 0 < M <= 32 and length must match type");
        }
    }

    private boolean isValid(int i) {
        int length = getValue().length;
        return length > 0 && length <= 32 && length == i;
    }
}
