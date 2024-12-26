package org.tron.common.utils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.crypto.datatypes.Bool;
import org.tron.common.crypto.datatypes.Int;
import org.tron.common.crypto.datatypes.Uint;
import org.tron.common.crypto.datatypes.Utf8String;
import org.tron.config.Parameter;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.AddressUtil;
public class TriggerLoad {
    public static final int DATAWORD_UNIT_SIZE = 32;

    public enum Type {
        UNKNOWN,
        INT_NUMBER,
        BOOL,
        FLOAT_NUMBER,
        FIXED_BYTES,
        ADDRESS,
        STRING,
        BYTES
    }

    private static void getTriggerData(String str) {
    }

    public static Map<String, String> parseTriggerData(byte[] bArr, SmartContractOuterClass.SmartContract.ABI.Entry entry) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (ArrayUtils.isEmpty(bArr)) {
            return linkedHashMap;
        }
        List<SmartContractOuterClass.SmartContract.ABI.Entry.Param> inputsList = entry.getInputsList();
        Integer num = 0;
        try {
            int i = 0;
            for (Integer num2 = 0; num2.intValue() < inputsList.size(); num2 = Integer.valueOf(num2.intValue() + 1)) {
                SmartContractOuterClass.SmartContract.ABI.Entry.Param param = inputsList.get(num2.intValue());
                if (!param.getIndexed()) {
                    if (num.intValue() == 0) {
                        num = num2;
                    }
                    int i2 = i + 1;
                    String parseDataBytes = parseDataBytes(bArr, param.getType(), i);
                    if (!AddressUtil.isEmpty(param.getName())) {
                        linkedHashMap.put("" + num2, parseDataBytes);
                    }
                    i = i2;
                }
            }
            if (inputsList.size() == 0) {
                linkedHashMap.put("0", Hex.toHexString(bArr));
            }
        } catch (UnsupportedOperationException unused) {
            linkedHashMap.clear();
            linkedHashMap.put(num.toString(), Hex.toHexString(bArr));
        }
        return linkedHashMap;
    }

    public static Map<String, String> parseTriggerDataByFun(byte[] bArr, String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            if (!ArrayUtils.isEmpty(bArr) && !AddressUtil.isEmpty(str) && str.contains("(") && str.contains(")")) {
                String substring = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                if (!AddressUtil.isEmpty(substring) && !substring.contains("(") && !substring.contains(")")) {
                    List asList = Arrays.asList(substring.split(","));
                    int i = 0;
                    Integer num = 0;
                    if (asList != null && asList.size() == 1 && ((String) asList.get(0)).contains(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
                        return parseDataByArray(bArr, substring);
                    }
                    try {
                        Integer num2 = 0;
                        while (num2.intValue() < asList.size()) {
                            if (num.intValue() == 0) {
                                num = num2;
                            }
                            int i2 = i + 1;
                            String parseDataBytes = parseDataBytes(bArr, (String) asList.get(num2.intValue()), i);
                            linkedHashMap.put("" + num2, parseDataBytes);
                            num2 = Integer.valueOf(num2.intValue() + 1);
                            i = i2;
                        }
                        if (asList.size() == 0) {
                            linkedHashMap.put("0", Hex.toHexString(bArr));
                        }
                    } catch (Exception unused) {
                        linkedHashMap.clear();
                        linkedHashMap.put(num.toString(), Hex.toHexString(bArr));
                    }
                }
            }
            return linkedHashMap;
        } catch (Exception e) {
            LogUtils.e(e);
            return linkedHashMap;
        }
    }

    private static Map<String, String> parseDataByArray(byte[] bArr, String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            if (str.contains(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
                String substring = str.substring(0, str.indexOf("["));
                int ceil = (int) Math.ceil(bArr.length / 32.0d);
                for (int i = 0; i < ceil; i++) {
                    String parseDataBytes = parseDataBytes(bArr, substring, i);
                    linkedHashMap.put("" + i, parseDataBytes);
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return linkedHashMap;
    }

    private static String parseDataBytes(byte[] bArr, String str, int i) {
        byte[] subBytes;
        Type basicType;
        try {
            subBytes = subBytes(bArr, i * 32, 32);
            basicType = basicType(str);
        } catch (ArithmeticException | OutputLengthException unused) {
        }
        if (basicType == Type.INT_NUMBER) {
            return new BigInteger(1, subBytes).toString();
        }
        if (basicType == Type.BOOL) {
            return String.valueOf(!isZero(subBytes));
        }
        if (basicType == Type.FIXED_BYTES) {
            return Hex.toHexString(subBytes);
        }
        if (basicType == Type.ADDRESS) {
            return AddressUtil.encode58Check(convertToTronAddress(org.bouncycastle.util.Arrays.copyOfRange(subBytes, 12, subBytes.length)));
        }
        if (basicType == Type.STRING || basicType == Type.BYTES) {
            int intValue = intValueExact(subBytes).intValue();
            int intValue2 = intValueExact(subBytes(bArr, intValue, 32)).intValue();
            if (intValue2 != 0 && intValue2 <= 1048576) {
                byte[] subBytes2 = subBytes(bArr, intValue + 32, intValue2);
                return basicType == Type.STRING ? new String(subBytes2) : Hex.toHexString(subBytes2);
            }
            return "";
        }
        


return null;

//throw new UnsupportedOperationException(
unsupported type:" + str);
    }

    private static Type basicType(String str) {
        if (!Pattern.matches("^.*\\[\\d*\\]$", str)) {
            if (str.startsWith(Int.TYPE_NAME) || str.startsWith(Uint.TYPE_NAME)) {
                return Type.INT_NUMBER;
            }
            if (str.equals(Bool.TYPE_NAME)) {
                return Type.BOOL;
            }
            if (str.equals("address")) {
                return Type.ADDRESS;
            }
            if (Pattern.matches("^bytes\\d+$", str)) {
                return Type.FIXED_BYTES;
            }
            if (str.equals(Utf8String.TYPE_NAME)) {
                return Type.STRING;
            }
            if (str.equals("bytes")) {
                return Type.BYTES;
            }
        }
        return Type.UNKNOWN;
    }

    private static Integer intValueExact(byte[] bArr) {
        return Integer.valueOf(new BigInteger(bArr).intValue());
    }

    private static byte[] subBytes(byte[] bArr, int i, int i2) {
        if (ArrayUtils.isEmpty(bArr) || i >= bArr.length || i2 < 0) {
            throw new OutputLengthException("data start:" + i + ", length:" + i2);
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, Math.min(i2, bArr.length - i));
        return bArr2;
    }

    private static boolean isZero(byte[] bArr) {
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static byte[] convertToTronAddress(byte[] bArr) {
        if (bArr.length == 20) {
            byte[] bArr2 = new byte[21];
            System.arraycopy(new byte[]{Parameter.CommonConstant.ADD_PRE_FIX_BYTE}, 0, bArr2, 0, 1);
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            return bArr2;
        }
        return bArr;
    }

    public static void main(String[] strArr) {
        new TriggerLoad();
        getTriggerData("");
    }
}
