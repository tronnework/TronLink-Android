package org.tron.common.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
public class ByteUtil {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final byte[] ZERO_BYTE_ARRAY = {0};

    public static byte[] bigIntegerToBytes(BigInteger bigInteger, int i) {
        if (bigInteger == null) {
            return null;
        }
        byte[] bArr = new byte[i];
        byte[] byteArray = bigInteger.toByteArray();
        int i2 = byteArray.length == i + 1 ? 1 : 0;
        int min = Math.min(byteArray.length, i);
        System.arraycopy(byteArray, i2, bArr, i - min, min);
        return bArr;
    }

    public static byte[] bigIntegerToBytes(BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == 1 || byteArray[0] != 0) {
            return byteArray;
        }
        int length = byteArray.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 1, bArr, 0, length);
        return bArr;
    }

    public static byte[] merge(byte[]... bArr) {
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        byte[] bArr3 = new byte[i];
        int i2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, i2, bArr4.length);
            i2 += bArr4.length;
        }
        return bArr3;
    }

    public static byte[] appendByte(byte[] bArr, byte b) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + 1);
        copyOf[copyOf.length - 1] = b;
        return copyOf;
    }

    public static byte[] appendBytes(byte[] bArr, byte[] bArr2, int i, int i2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + i2);
        System.arraycopy(bArr2, i, copyOf, bArr.length, i2);
        return copyOf;
    }

    public static String nibblesToPrettyString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String oneByteToHexString = oneByteToHexString(b);
            sb.append("\\x");
            sb.append(oneByteToHexString);
        }
        return sb.toString();
    }

    public static String oneByteToHexString(byte b) {
        String num = Integer.toString(b & 255, 16);
        if (num.length() == 1) {
            return "0" + num;
        }
        return num;
    }

    public static String toHexString(byte[] bArr) {
        return bArr == null ? "" : Hex.toHexString(bArr);
    }

    public static int byteArrayToInt(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        return new BigInteger(1, bArr).intValue();
    }

    public static boolean isNullOrZeroArray(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean isSingleZero(byte[] bArr) {
        return bArr.length == 1 && bArr[0] == 0;
    }

    public static byte[] intToBytesNoLeadZeroes(int i) {
        if (i == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        int i2 = 0;
        int i3 = i;
        while (i3 != 0) {
            i3 >>>= 8;
            i2++;
        }
        byte[] bArr = new byte[i2];
        int i4 = i2 - 1;
        while (i != 0) {
            bArr[i4] = (byte) (i & 255);
            i >>>= 8;
            i4--;
        }
        return bArr;
    }

    public static byte[] intToBytes(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }

    public static byte[] stripLeadingZeroes(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int firstNonZeroByte = firstNonZeroByte(bArr);
        if (firstNonZeroByte != -1) {
            if (firstNonZeroByte != 0) {
                byte[] bArr2 = new byte[bArr.length - firstNonZeroByte];
                System.arraycopy(bArr, firstNonZeroByte, bArr2, 0, bArr.length - firstNonZeroByte);
                return bArr2;
            }
            return bArr;
        }
        return ZERO_BYTE_ARRAY;
    }

    public static int firstNonZeroByte(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    public static byte[] copyToArray(BigInteger bigInteger) {
        byte[] array = ByteBuffer.allocate(32).array();
        byte[] bigIntegerToBytes = bigIntegerToBytes(bigInteger);
        if (bigIntegerToBytes != null) {
            System.arraycopy(bigIntegerToBytes, 0, array, array.length - bigIntegerToBytes.length, bigIntegerToBytes.length);
        }
        return array;
    }

    public static List<Boolean> convertBytesVectorToVector(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            for (int i = 0; i < 8; i++) {
                boolean z = true;
                if (((b >> (7 - i)) & 1) != 1) {
                    z = false;
                }
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }
}
