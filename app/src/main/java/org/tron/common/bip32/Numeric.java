package org.tron.common.bip32;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import org.tron.walletserver.AddressUtil;
public final class Numeric {
    private static final String HEX_PREFIX = "0x";

    public static byte asByte(int i, int i2) {
        return (byte) ((i << 4) | i2);
    }

    private Numeric() {
    }

    public static String encodeQuantity(BigInteger bigInteger) {
        if (bigInteger.signum() != -1) {
            return HEX_PREFIX + bigInteger.toString(16);
        }
        throw new IllegalArgumentException("Negative values are not supported");
    }

    public static BigInteger decodeQuantity(String str) {
        if (!isValidHexQuantity(str)) {
            throw new IllegalArgumentException("Value must be in format 0x[1-9]+[0-9]* or 0x0");
        }
        try {
            return new BigInteger(str.substring(2), 16);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Negative ", e);
        }
    }

    private static boolean isValidHexQuantity(String str) {
        return str != null && str.length() >= 3 && str.startsWith(HEX_PREFIX);
    }

    public static String cleanHexPrefix(String str) {
        return containsHexPrefix(str) ? str.substring(2) : str;
    }

    public static String prependHexPrefix(String str) {
        if (containsHexPrefix(str)) {
            return str;
        }
        return HEX_PREFIX + str;
    }

    public static boolean containsHexPrefix(String str) {
        return !AddressUtil.isEmpty(str) && str.length() > 1 && str.charAt(0) == '0' && str.charAt(1) == 'x';
    }

    public static BigInteger toBigInt(byte[] bArr, int i, int i2) {
        return toBigInt(Arrays.copyOfRange(bArr, i, i2 + i));
    }

    public static BigInteger toBigInt(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInt(String str) {
        return toBigIntNoPrefix(cleanHexPrefix(str));
    }

    public static BigInteger toBigIntNoPrefix(String str) {
        return new BigInteger(str, 16);
    }

    public static String toHexStringWithPrefix(BigInteger bigInteger) {
        return HEX_PREFIX + bigInteger.toString(16);
    }

    public static String toHexStringNoPrefix(BigInteger bigInteger) {
        return bigInteger.toString(16);
    }

    public static String toHexStringNoPrefix(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length, false);
    }

    public static String toHexStringWithPrefixZeroPadded(BigInteger bigInteger, int i) {
        return toHexStringZeroPadded(bigInteger, i, true);
    }

    public static String toHexStringWithPrefixSafe(BigInteger bigInteger) {
        String hexStringNoPrefix = toHexStringNoPrefix(bigInteger);
        if (hexStringNoPrefix.length() < 2) {
            hexStringNoPrefix = AddressUtil.zeros(1) + hexStringNoPrefix;
        }
        return HEX_PREFIX + hexStringNoPrefix;
    }

    public static String toHexStringNoPrefixZeroPadded(BigInteger bigInteger, int i) {
        return toHexStringZeroPadded(bigInteger, i, false);
    }

    private static String toHexStringZeroPadded(BigInteger bigInteger, int i, boolean z) {
        String hexStringNoPrefix = toHexStringNoPrefix(bigInteger);
        int length = hexStringNoPrefix.length();
        if (length > i) {
            


return null;

//throw new UnsupportedOperationException(
Value " + hexStringNoPrefix + "is larger then length " + i);
        } else if (bigInteger.signum() >= 0) {
            if (length < i) {
                hexStringNoPrefix = AddressUtil.zeros(i - length) + hexStringNoPrefix;
            }
            if (z) {
                return HEX_PREFIX + hexStringNoPrefix;
            }
            return hexStringNoPrefix;
        } else {
            


return null;

//throw new UnsupportedOperationException(
Value cannot be negative");
        }
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

    public static byte[] hexStringToByteArray(String str) {
        byte[] bArr;
        String cleanHexPrefix = cleanHexPrefix(str);
        int length = cleanHexPrefix.length();
        int i = 0;
        if (length == 0) {
            return new byte[0];
        }
        if (length % 2 != 0) {
            bArr = new byte[(length / 2) + 1];
            bArr[0] = (byte) Character.digit(cleanHexPrefix.charAt(0), 16);
            i = 1;
        } else {
            bArr = new byte[length / 2];
        }
        while (i < length) {
            int i2 = i + 1;
            bArr[i2 / 2] = (byte) ((Character.digit(cleanHexPrefix.charAt(i), 16) << 4) + Character.digit(cleanHexPrefix.charAt(i2), 16));
            i += 2;
        }
        return bArr;
    }

    public static String toHexString(byte[] bArr, int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append(HEX_PREFIX);
        }
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append(String.format("%02x", Integer.valueOf(bArr[i3] & 255)));
        }
        return sb.toString();
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length, true);
    }

    public static boolean isIntegerValue(BigDecimal bigDecimal) {
        return bigDecimal.signum() == 0 || bigDecimal.scale() <= 0 || bigDecimal.stripTrailingZeros().scale() <= 0;
    }
}
