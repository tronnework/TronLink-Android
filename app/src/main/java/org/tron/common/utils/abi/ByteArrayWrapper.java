package org.tron.common.utils.abi;

import java.io.Serializable;
import java.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
public class ByteArrayWrapper implements Comparable<ByteArrayWrapper>, Serializable {
    private final byte[] data;
    private int hashCode;

    public byte[] getData() {
        return this.data;
    }

    public int hashCode() {
        return this.hashCode;
    }

    public ByteArrayWrapper(byte[] bArr) {
        this.hashCode = 0;
        if (bArr == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.data = bArr;
        this.hashCode = Arrays.hashCode(bArr);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        byte[] data = ((ByteArrayWrapper) obj).getData();
        byte[] bArr = this.data;
        return FastByteComparisons.compareTo(bArr, 0, bArr.length, data, 0, data.length) == 0;
    }

    @Override
    public int compareTo(ByteArrayWrapper byteArrayWrapper) {
        byte[] bArr = this.data;
        return FastByteComparisons.compareTo(bArr, 0, bArr.length, byteArrayWrapper.getData(), 0, byteArrayWrapper.getData().length);
    }

    public String toString() {
        return Hex.toHexString(this.data);
    }
}
