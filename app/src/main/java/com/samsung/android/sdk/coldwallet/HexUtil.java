package com.samsung.android.sdk.coldwallet;

import android.text.TextUtils;
class HexUtil {
    HexUtil() {
    }

    public static String toHexString(byte[] bArr, int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("0x");
        }
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append(String.format("%02x", Integer.valueOf(bArr[i3] & 255)));
        }
        return sb.toString();
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length, true);
    }

    public static byte[] toBytes(String str) {
        String cleanHexPrefix = cleanHexPrefix(str);
        int length = cleanHexPrefix.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(cleanHexPrefix.charAt(i), 16) << 4) + Character.digit(cleanHexPrefix.charAt(i + 1), 16));
        }
        return bArr;
    }

    private static String cleanHexPrefix(String str) {
        return containsHexPrefix(str) ? str.substring(2) : str;
    }

    private static boolean containsHexPrefix(String str) {
        return !TextUtils.isEmpty(str) && str.length() > 1 && str.charAt(0) == '0' && str.charAt(1) == 'x';
    }
}
