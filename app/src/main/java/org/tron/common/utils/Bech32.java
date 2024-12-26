package org.tron.common.utils;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Locale;
public class Bech32 {
    private static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";
    private static final byte[] CHARSET_REV = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Ascii.SI, -1, 10, 17, Ascii.NAK, Ascii.DC4, Ascii.SUB, Ascii.RS, 7, 5, -1, -1, -1, -1, -1, -1, -1, Ascii.GS, -1, Ascii.CAN, Ascii.CR, Ascii.EM, 9, 8, Ascii.ETB, -1, Ascii.DC2, Ascii.SYN, Ascii.US, Ascii.ESC, 19, -1, 1, 0, 3, 16, Ascii.VT, Ascii.FS, Ascii.FF, Ascii.SO, 6, 4, 2, -1, -1, -1, -1, -1, -1, Ascii.GS, -1, Ascii.CAN, Ascii.CR, Ascii.EM, 9, 8, Ascii.ETB, -1, Ascii.DC2, Ascii.SYN, Ascii.US, Ascii.ESC, 19, -1, 1, 0, 3, 16, Ascii.VT, Ascii.FS, Ascii.FF, Ascii.SO, 6, 4, 2, -1, -1, -1, -1, -1};

    public static class Bech32Data {
        public final byte[] data;
        public final String hrp;

        private Bech32Data(String str, byte[] bArr) {
            this.hrp = str;
            this.data = bArr;
        }
    }

    private static int polymod(byte[] bArr) {
        int i = 1;
        for (byte b : bArr) {
            int i2 = i >>> 25;
            i = ((i & 33554431) << 5) ^ (b & 255);
            if ((i2 & 1) != 0) {
                i ^= 996825010;
            }
            if ((i2 & 2) != 0) {
                i ^= 642813549;
            }
            if ((i2 & 4) != 0) {
                i ^= 513874426;
            }
            if ((i2 & 8) != 0) {
                i ^= 1027748829;
            }
            if ((i2 & 16) != 0) {
                i ^= 705979059;
            }
        }
        return i;
    }

    private static byte[] expandHrp(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length * 2) + 1];
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            bArr[i] = (byte) (((charAt & Ascii.MAX) >>> 5) & 7);
            bArr[i + length + 1] = (byte) (charAt & 31);
        }
        bArr[length] = 0;
        return bArr;
    }

    private static boolean verifyChecksum(String str, byte[] bArr) {
        byte[] expandHrp = expandHrp(str);
        byte[] bArr2 = new byte[expandHrp.length + bArr.length];
        System.arraycopy(expandHrp, 0, bArr2, 0, expandHrp.length);
        System.arraycopy(bArr, 0, bArr2, expandHrp.length, bArr.length);
        return polymod(bArr2) == 1;
    }

    private static byte[] createChecksum(String str, byte[] bArr) {
        byte[] expandHrp = expandHrp(str);
        byte[] bArr2 = new byte[expandHrp.length + bArr.length + 6];
        System.arraycopy(expandHrp, 0, bArr2, 0, expandHrp.length);
        System.arraycopy(bArr, 0, bArr2, expandHrp.length, bArr.length);
        int polymod = polymod(bArr2) ^ 1;
        byte[] bArr3 = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr3[i] = (byte) ((polymod >>> ((5 - i) * 5)) & 31);
        }
        return bArr3;
    }

    public static String encode(Bech32Data bech32Data) {
        return encode(bech32Data.hrp, bech32Data.data);
    }

    public static String encode(String str, byte[] bArr) {
        Preconditions.checkArgument(str.length() >= 1, "Human-readable part is too short");
        Preconditions.checkArgument(str.length() <= 83, "Human-readable part is too long");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        byte[] createChecksum = createChecksum(lowerCase, bArr);
        int length = bArr.length + createChecksum.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(createChecksum, 0, bArr2, bArr.length, createChecksum.length);
        StringBuilder sb = new StringBuilder(lowerCase.length() + 1 + length);
        sb.append(lowerCase);
        sb.append('1');
        for (int i = 0; i < length; i++) {
            sb.append(CHARSET.charAt(bArr2[i]));
        }
        return sb.toString();
    }

    public static Bech32Data decode(String str) throws IllegalArgumentException {
        if (str.length() < 8) {
            throw new IllegalArgumentException("Input too short: " + str.length());
        } else if (str.length() > 90) {
            throw new IllegalArgumentException("Input too long: " + str.length());
        } else {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                String str2 = "InvalidCharacter(" + charAt + ", " + i + ")";
                if (charAt < '!' || charAt > '~') {
                    throw new IllegalArgumentException(str2);
                }
                if (charAt >= 'a' && charAt <= 'z') {
                    if (z) {
                        throw new IllegalArgumentException(str2);
                    }
                    z2 = true;
                }
                if (charAt >= 'A' && charAt <= 'Z') {
                    if (z2) {
                        throw new IllegalArgumentException(str2);
                    }
                    z = true;
                }
            }
            int lastIndexOf = str.lastIndexOf(49);
            if (lastIndexOf < 1) {
                throw new IllegalArgumentException("Missing human-readable part");
            }
            int length = (str.length() - 1) - lastIndexOf;
            if (length < 6) {
                throw new IllegalArgumentException("Data part too short: " + length);
            }
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                char charAt2 = str.charAt(i2 + lastIndexOf + 1);
                byte b = CHARSET_REV[charAt2];
                if (b == -1) {
                    throw new IllegalArgumentException("InvalidCharacter(" + charAt2 + ", " + i2 + lastIndexOf + "1)");
                }
                bArr[i2] = b;
            }
            String lowerCase = str.substring(0, lastIndexOf).toLowerCase(Locale.ROOT);
            if (!verifyChecksum(lowerCase, bArr)) {
                throw new IllegalArgumentException("InvalidChecksum:");
            }
            return new Bech32Data(lowerCase, Arrays.copyOfRange(bArr, 0, length - 6));
        }
    }
}
