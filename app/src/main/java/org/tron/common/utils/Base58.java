package org.tron.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import org.apache.commons.lang3.CharEncoding;
public class Base58 {
    public static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    private static final int[] INDEXES = new int[128];

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = INDEXES;
            if (i2 >= iArr.length) {
                break;
            }
            iArr[i2] = -1;
            i2++;
        }
        while (true) {
            char[] cArr = ALPHABET;
            if (i >= cArr.length) {
                return;
            }
            INDEXES[cArr[i]] = i;
            i++;
        }
    }

    public static String encode(byte[] bArr) {
        if (bArr.length == 0) {
            return "";
        }
        byte[] copyOfRange = copyOfRange(bArr, 0, bArr.length);
        int i = 0;
        while (i < copyOfRange.length && copyOfRange[i] == 0) {
            i++;
        }
        int length = copyOfRange.length * 2;
        byte[] bArr2 = new byte[length];
        int i2 = i;
        int i3 = length;
        while (i2 < copyOfRange.length) {
            byte divmod58 = divmod58(copyOfRange, i2);
            if (copyOfRange[i2] == 0) {
                i2++;
            }
            i3--;
            bArr2[i3] = (byte) ALPHABET[divmod58];
        }
        while (i3 < length && bArr2[i3] == ALPHABET[0]) {
            i3++;
        }
        while (true) {
            i--;
            if (i >= 0) {
                i3--;
                bArr2[i3] = (byte) ALPHABET[0];
            } else {
                try {
                    return new String(copyOfRange(bArr2, i3, length), CharEncoding.US_ASCII);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static byte[] decode(String str) throws IllegalArgumentException {
        int i = 0;
        if (str.length() == 0) {
            return new byte[0];
        }
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            int i3 = (charAt < 0 || charAt >= 128) ? -1 : INDEXES[charAt];
            if (i3 < 0) {
                throw new IllegalArgumentException("Illegal character " + charAt + " at " + i2);
            }
            bArr[i2] = (byte) i3;
        }
        while (i < length && bArr[i] == 0) {
            i++;
        }
        int length2 = str.length();
        byte[] bArr2 = new byte[length2];
        int i4 = length2;
        int i5 = i;
        while (i5 < length) {
            byte divmod256 = divmod256(bArr, i5);
            if (bArr[i5] == 0) {
                i5++;
            }
            i4--;
            bArr2[i4] = divmod256;
        }
        while (i4 < length2 && bArr2[i4] == 0) {
            i4++;
        }
        return copyOfRange(bArr2, i4 - i, length2);
    }

    public static BigInteger decodeToBigInteger(String str) throws IllegalArgumentException {
        return new BigInteger(1, decode(str));
    }

    private static byte divmod58(byte[] bArr, int i) {
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (i2 * 256) + (bArr[i] & 255);
            bArr[i] = (byte) (i3 / 58);
            i2 = i3 % 58;
            i++;
        }
        return (byte) i2;
    }

    private static byte divmod256(byte[] bArr, int i) {
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (i2 * 58) + (bArr[i] & 255);
            bArr[i] = (byte) (i3 / 256);
            i2 = i3 % 256;
            i++;
        }
        return (byte) i2;
    }

    private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }
}
