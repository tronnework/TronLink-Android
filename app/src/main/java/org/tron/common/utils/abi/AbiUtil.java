package org.tron.common.utils.abi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.crypto.Hash;
import org.tron.common.crypto.datatypes.Bool;
import org.tron.common.crypto.datatypes.Utf8String;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.ByteUtil;
import org.tron.common.utils.DecimalUtils;
import org.tron.common.utils.GsonFormatUtils;
import org.tron.common.utils.LogUtils;
import org.tron.walletserver.AddressUtil;
public class AbiUtil {
    private static Pattern paramTypeBytes = Pattern.compile("^bytes([0-9]*)$");
    private static Pattern paramTypeNumber = Pattern.compile("^(u?int)([0-9]*)$");
    private static Pattern paramTypeArray = Pattern.compile("^(.*)\\[([0-9]*)]$");

    public static abstract class Coder {
        boolean dynamic = false;

        abstract byte[] decode();

        abstract byte[] encode(String str);

        Coder() {
        }
    }

    public static String[] getTypes(String str) {
        try {
            String charSequence = str.subSequence(str.indexOf(40) + 1, str.lastIndexOf(41)).toString();
            if (charSequence.contains("tuple")) {
                int indexOf = charSequence.indexOf("tuple");
                String substring = charSequence.substring(indexOf, charSequence.lastIndexOf(")") + 1);
                String[] split = charSequence.substring(0, indexOf).split(",");
                String[] strArr = (String[]) Arrays.copyOf(split, split.length + 1);
                strArr[split.length] = substring;
                return strArr;
            }
            return charSequence.split(",");
        } catch (Exception e) {
            LogUtils.e(e);
            return new String[0];
        }
    }

    public static Coder getParamCoder(String str) {
        char c;
        String str2 = str.contains("tuple") ? "tuple" : str;
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1147692044:
                if (str2.equals("address")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -891985903:
                if (str2.equals(Utf8String.TYPE_NAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3029738:
                if (str2.equals(Bool.TYPE_NAME)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 94224491:
                if (str2.equals("bytes")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 110725064:
                if (str2.equals("tuple")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1314015060:
                if (str2.equals("trcToken")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new CoderAddress();
            case 1:
                return new CoderString();
            case 2:
                return new CoderBool();
            case 3:
                return new CoderDynamicBytes();
            case 4:
                return new CoderTuple(str);
            case 5:
                return new CoderNumber();
            default:
                if (paramTypeBytes.matcher(str2).find()) {
                    return new CoderFixedBytes();
                }
                if (paramTypeNumber.matcher(str2).find()) {
                    return new CoderNumber();
                }
                Matcher matcher = paramTypeArray.matcher(str2);
                if (matcher.find()) {
                    return new CoderArray(matcher.group(1), matcher.group(2).equals("") ? -1 : Integer.valueOf(matcher.group(2)).intValue());
                }
                return null;
        }
    }

    public static class CoderArray extends Coder {
        private String elementType;
        private int length;

        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderArray(String str, int i) {
            this.elementType = str;
            this.length = i;
            if (i == -1) {
                this.dynamic = true;
            }
            this.dynamic = true;
        }

        @Override
        byte[] encode(String str) {
            Coder paramCoder = AbiUtil.getParamCoder(this.elementType);
            try {
                List list = (List) new ObjectMapper().readValue(str, List.class);
                ArrayList arrayList = new ArrayList();
                if (this.length == -1) {
                    for (int i = 0; i < list.size(); i++) {
                        arrayList.add(paramCoder);
                    }
                } else {
                    for (int i2 = 0; i2 < this.length; i2++) {
                        arrayList.add(paramCoder);
                    }
                }
                return this.length == -1 ? ByteUtil.merge(new DataWord(list.size()).getData(), AbiUtil.pack(arrayList, list)) : AbiUtil.pack(arrayList, list);
            } catch (IOException e) {
                LogUtils.e(e);
                return null;
            }
        }
    }

    public static class CoderTuple extends Coder {
        private List<String> elementTypeList;
        private String elementTypes;
        private int length;

        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderTuple(String str) {
            String[] split;
            this.elementTypeList = new ArrayList();
            this.elementTypes = str;
            if (AddressUtil.isEmpty(str)) {
                return;
            }
            String charSequence = str.subSequence(str.indexOf(40) + 1, str.lastIndexOf(41)).toString();
            if (charSequence != null && (split = charSequence.split(",")) != null && split.length != 0) {
                List<String> asList = Arrays.asList(split);
                this.elementTypeList = asList;
                this.length = asList.size();
            }
            this.dynamic = false;
        }

        @Override
        byte[] encode(String str) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.elementTypeList.size(); i++) {
                arrayList.add(AbiUtil.getParamCoder(this.elementTypeList.get(i)));
            }
            try {
                List list = (List) new ObjectMapper().readValue(str, List.class);
                return this.length == -1 ? ByteUtil.merge(new DataWord(list.size()).getData(), AbiUtil.pack(arrayList, list)) : AbiUtil.pack(arrayList, list);
            } catch (IOException e) {
                LogUtils.e(e);
                return null;
            }
        }
    }

    public static class CoderNumber extends Coder {
        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderNumber() {
        }

        @Override
        byte[] encode(String str) {
            String decimalUtils = DecimalUtils.toString(DecimalUtils.toBigDecimal(str));
            if (!AddressUtil.isEmpty(decimalUtils) && decimalUtils.contains(ThreadPoolManager.DOT)) {
                decimalUtils = AddressUtil.splitByIndex(decimalUtils, ThreadPoolManager.DOT);
            }
            BigInteger bigInteger = new BigInteger(decimalUtils);
            byte[] byteArray = bigInteger.abs().toByteArray();
            if (byteArray.length == 33 && byteArray[0] == 0) {
                int length = byteArray.length - 1;
                byte[] bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
                byteArray = bArr;
            }
            DataWord dataWord = new DataWord(byteArray);
            if (bigInteger.compareTo(new BigInteger("0")) == -1) {
                dataWord.negate();
            }
            return dataWord.getData();
        }
    }

    public static class CoderFixedBytes extends Coder {
        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderFixedBytes() {
        }

        @Override
        byte[] encode(String str) {
            if (str.startsWith("0x")) {
                str = str.substring(2);
            }
            if (str.length() % 2 != 0) {
                str = "0" + str;
            }
            byte[] bArr = new byte[32];
            byte[] decode = Hex.decode(str);
            System.arraycopy(decode, 0, bArr, 0, decode.length);
            return bArr;
        }
    }

    public static class CoderDynamicBytes extends Coder {
        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderDynamicBytes() {
            this.dynamic = true;
        }

        @Override
        byte[] encode(String str) {
            return AbiUtil.encodeDynamicBytes(str, true);
        }
    }

    public static class CoderBool extends Coder {
        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderBool() {
        }

        @Override
        byte[] encode(String str) {
            if (str.equals("true") || str.equals("1")) {
                return new DataWord(1).getData();
            }
            return new DataWord(0).getData();
        }
    }

    public static class CoderAddress extends Coder {
        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderAddress() {
        }

        @Override
        byte[] encode(String str) {
            byte[] fromHexString = ByteArray.fromHexString(AddressUtil.replace41Address(str));
            if (fromHexString == null) {
                return null;
            }
            return new DataWord(fromHexString).getData();
        }
    }

    public static class CoderString extends Coder {
        @Override
        byte[] decode() {
            return new byte[0];
        }

        CoderString() {
            this.dynamic = true;
        }

        @Override
        byte[] encode(String str) {
            return AbiUtil.encodeDynamicBytes(str);
        }
    }

    public static byte[] encodeDynamicBytes(String str, boolean z) {
        byte[] bytes;
        if (z) {
            if (str.startsWith("0x")) {
                str = str.substring(2);
            }
            bytes = Hex.decode(str);
        } else {
            bytes = str.getBytes();
        }
        return encodeDynamicBytes(bytes);
    }

    private static byte[] encodeDynamicBytes(byte[] bArr) {
        ArrayList<DataWord> arrayList = new ArrayList();
        arrayList.add(new DataWord(bArr.length));
        int length = bArr.length;
        int i = 0;
        while (true) {
            int i2 = 32;
            if (i >= bArr.length) {
                break;
            }
            byte[] bArr2 = new byte[32];
            int i3 = length - i;
            if (i3 < 32) {
                i2 = i3;
            }
            System.arraycopy(bArr, i, bArr2, 0, i2);
            arrayList.add(new DataWord(bArr2));
            i += 32;
        }
        byte[] bArr3 = new byte[arrayList.size() * 32];
        int i4 = 0;
        for (DataWord dataWord : arrayList) {
            System.arraycopy(dataWord.getData(), 0, bArr3, i4, 32);
            i4 += 32;
        }
        return bArr3;
    }

    public static byte[] encodeDynamicBytes(String str) {
        byte[] bytes = str.getBytes();
        new ArrayList().add(new DataWord(bytes.length));
        return encodeDynamicBytes(bytes);
    }

    public static byte[] pack(List<Coder> list, List<Object> list2) {
        String obj;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Coder coder = list.get(i3);
            Object obj2 = list2.get(i3);
            if (obj2 instanceof List) {
                StringBuilder sb = new StringBuilder();
                for (Object obj3 : (List) obj2) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append("\"");
                    sb.append(obj3);
                    sb.append("\"");
                }
                obj = "[" + sb.toString() + "]";
            } else {
                obj = obj2.toString();
            }
            byte[] encode = coder.encode(obj);
            arrayList.add(encode);
            if (coder.dynamic) {
                i += 32;
                i2 += encode.length;
            } else {
                i += encode.length;
            }
        }
        byte[] bArr = new byte[i2 + i];
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            if (list.get(i5).dynamic) {
                System.arraycopy(new DataWord(i).getData(), 0, bArr, i4, 32);
                i4 += 32;
                System.arraycopy(arrayList.get(i5), 0, bArr, i, ((byte[]) arrayList.get(i5)).length);
                i += ((byte[]) arrayList.get(i5)).length;
            } else {
                System.arraycopy(arrayList.get(i5), 0, bArr, i4, ((byte[]) arrayList.get(i5)).length);
                i4 += ((byte[]) arrayList.get(i5)).length;
            }
        }
        return bArr;
    }

    public static String parseMethod(String str, String str2) {
        return parseMethod(str, str2, false);
    }

    public static String parseMethod(String str, String str2, boolean z) {
        byte[] bArr = new byte[4];
        System.arraycopy(Hash.sha3(str.getBytes()), 0, bArr, 0, 4);
        PrintStream printStream = System.out;
        printStream.println(str + ":" + Hex.toHexString(bArr));
        if (str2.length() == 0) {
            return Hex.toHexString(bArr);
        }
        if (z) {
            return Hex.toHexString(bArr) + str2;
        }
        byte[] encodeInput = encodeInput(str, str2);
        if (ByteUtil.isNullOrZeroArray(encodeInput)) {
            return null;
        }
        return Hex.toHexString(bArr) + Hex.toHexString(encodeInput);
    }

    public static byte[] encodeInput(String str, String str2) {
        List parseInputToList_;
        if (AddressUtil.isEmpty(str, str2)) {
            return null;
        }
        if (str2.contains("[") || str2.contains("]")) {
            parseInputToList_ = parseInputToList_(str2);
        } else {
            parseInputToList_ = parseInputToList(str2);
        }
        ArrayList arrayList = new ArrayList();
        for (String str3 : getTypes(str)) {
            arrayList.add(getParamCoder(str3));
        }
        return pack(arrayList, parseInputToList_);
    }

    public static byte[] encodeInputFromList(String str, List<Object> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : getTypes(str)) {
            arrayList.add(getParamCoder(str2));
        }
        return pack(arrayList, list);
    }

    public static List parseInputToList_(String str) {
        String str2 = "[" + str + "]";
        ArrayList arrayList = new ArrayList();
        try {
            return GsonFormatUtils.gsonToListFixDouble(str2, List.class);
        } catch (Exception e) {
            LogUtils.e(e);
            return arrayList;
        }
    }

    public static List parseInputToList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.contains(",")) {
                for (String str2 : str.split(",")) {
                    arrayList.add(str2);
                }
            } else {
                arrayList.add(str);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return arrayList;
    }

    public static String parseMethod(String str, List<Object> list) {
        String[] strArr = new String[list.size()];
        int i = 0;
        for (Object obj : list) {
            if (obj instanceof List) {
                StringBuilder sb = new StringBuilder();
                for (Object obj2 : (List) obj) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append("\"");
                    sb.append(obj2);
                    sb.append("\"");
                }
                strArr[i] = "[" + sb.toString() + "]";
                i++;
            } else {
                int i2 = i + 1;
                strArr[i] = obj instanceof String ? "\"" + obj + "\"" : "" + obj;
                i = i2;
            }
        }
        return parseMethod(str, StringUtils.join((Object[]) strArr, ','));
    }

    public static long decodeABI(String str) {
        byte[] decode = Hex.decode(str);
        if (decode == null) {
            decode = ByteUtil.EMPTY_BYTE_ARRAY;
        } else if (decode.length != 32 && decode.length <= 32) {
            System.arraycopy(decode, 0, decode, 32 - decode.length, decode.length);
        }
        long j = 0;
        for (byte b : decode) {
            j = (j << 8) + (b & 255);
        }
        return j;
    }
}
