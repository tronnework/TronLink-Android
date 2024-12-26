package org.tron.common.utils;

import com.google.common.primitives.Ints;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
public class ByteArray {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    public static String toHexString(byte[] bArr) {
        return bArr == null ? "" : Hex.toHexString(bArr);
    }

    public static byte[] fromHexString(String str) {
        if (str == null) {
            return EMPTY_BYTE_ARRAY;
        }
        if (str.startsWith("0x")) {
            str = str.substring(2);
        }
        if (str.length() % 2 == 1) {
            str = "0" + str;
        }
        return Hex.decode(str);
    }

    public static long toLong(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 0L;
        }
        return new BigInteger(1, bArr).longValue();
    }

    public static byte[] fromString(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }

    public static byte[] fromInt(int i) {
        return Ints.toByteArray(i);
    }

    public static String toStr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr);
    }

    public static byte[] fromLong(long j) {
        return ByteBuffer.allocate(8).putLong(j).array();
    }

    public static byte[] subArray(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }

    public static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean matrixContains(List<byte[]> list, byte[] bArr) {
        for (byte[] bArr2 : list) {
            if (Arrays.equals(bArr2, bArr)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] toBytesPadded(BigInteger bigInteger, int i) {
        int length;
        int i2;
        byte[] bArr = new byte[i];
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            i2 = 1;
            length = byteArray.length - 1;
        } else {
            length = byteArray.length;
            i2 = 0;
        }
        if (length > i) {
            throw new RuntimeException("Input is too large to put in byte array of size " + i);
        }
        System.arraycopy(byteArray, i2, bArr, i - length, length);
        return bArr;
    }
}
