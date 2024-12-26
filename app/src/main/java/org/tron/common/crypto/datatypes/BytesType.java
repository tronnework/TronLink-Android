package org.tron.common.crypto.datatypes;

import java.util.Arrays;
public abstract class BytesType implements Type<byte[]> {
    private String type;
    private byte[] value;

    @Override
    public String getTypeAsString() {
        return this.type;
    }

    @Override
    public byte[] getValue() {
        return this.value;
    }

    public BytesType(byte[] bArr, String str) {
        this.value = bArr;
        this.type = str;
    }

    @Override
    public int bytes32PaddedLength() {
        byte[] bArr = this.value;
        if (bArr.length <= 32) {
            return 32;
        }
        return ((bArr.length / 32) + 1) * 32;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BytesType bytesType = (BytesType) obj;
        if (Arrays.equals(this.value, bytesType.value)) {
            return this.type.equals(bytesType.type);
        }
        return false;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.value) * 31) + this.type.hashCode();
    }
}
