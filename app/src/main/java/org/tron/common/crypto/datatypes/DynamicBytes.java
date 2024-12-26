package org.tron.common.crypto.datatypes;
public class DynamicBytes extends BytesType {
    public static final DynamicBytes DEFAULT = new DynamicBytes(new byte[0]);
    public static final String TYPE_NAME = "bytes";

    public DynamicBytes(byte[] bArr) {
        super(bArr, "bytes");
    }
}
