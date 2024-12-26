package org.tron.walletserver;

import androidx.exifinterface.media.ExifInterface;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.Base58;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.Sha256Hash;
import org.tron.config.Parameter;
public class AddressUtil {
    public static final String EMPTY_STRING = "";

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim()) || str.trim().length() == 0 || "null".equals(str.trim());
    }

    public static boolean isEmpty(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        for (String str : strArr) {
            if (str == null || "".equals(str.trim()) || str.trim().length() == 0 || "null".equals(str.trim())) {
                return true;
            }
        }
        return false;
    }

    public static String splitByIndex(String str, String str2) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(str2);
        return indexOf > -1 ? str.substring(0, indexOf) : str;
    }

    public static boolean isAddressValid(byte[] bArr) {
        return bArr != null && bArr.length != 0 && bArr.length == 21 && bArr[0] == 65;
    }

    public static boolean isAddressValid(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return isAddressValid(decodeFromBase58Check(str));
    }

    public static String encode58Check(byte[] bArr) {
        byte[] hash = Sha256Hash.hash(Sha256Hash.hash(bArr));
        byte[] bArr2 = new byte[bArr.length + 4];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(hash, 0, bArr2, bArr.length, 4);
        return Base58.encode(bArr2);
    }

    public static String replace41Address(String str) {
        if (str.startsWith(ExifInterface.GPS_DIRECTION_TRUE)) {
            return ByteArray.toHexString(decode58Check(str)).replaceFirst(Parameter.CommonConstant.ADD_PRE_FIX_STRING, "");
        }
        if (str.startsWith(Parameter.CommonConstant.ADD_PRE_FIX_STRING)) {
            return str.replaceFirst(Parameter.CommonConstant.ADD_PRE_FIX_STRING, "");
        }
        return str.startsWith("0x") ? str.replaceFirst("0x", "") : str;
    }

    public static byte[] decode58Check(String str) {
        try {
            byte[] decode = Base58.decode(str);
            if (decode.length <= 4) {
                return null;
            }
            int length = decode.length;
            int i = length - 4;
            byte[] bArr = new byte[i];
            System.arraycopy(decode, 0, bArr, 0, i);
            byte[] sha256 = Hash.sha256(Hash.sha256(bArr));
            if (sha256[0] == decode[i] && sha256[1] == decode[length - 3] && sha256[2] == decode[length - 2]) {
                if (sha256[3] == decode[length - 1]) {
                    return bArr;
                }
            }
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decodeFromBase58Check(String str) {
        if (isEmpty(str)) {
            return null;
        }
        byte[] decode58Check = decode58Check(str);
        if (isAddressValid(decode58Check)) {
            return decode58Check;
        }
        return null;
    }

    public static String zeros(int i) {
        return repeat('0', i);
    }

    public static String repeat(char c, int i) {
        return new String(new char[i]).replace("\u0000", String.valueOf(c));
    }

    public static boolean isHexString(String str) {
        return str.matches("^[A-Fa-f0-9]+$");
    }
}
