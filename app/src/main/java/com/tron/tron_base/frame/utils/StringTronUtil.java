package com.tron.tron_base.frame.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.wallet.common.utils.ChainUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.lang.reflect.Field;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
public class StringTronUtil {
    public static byte[] intToByte(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)};
    }

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

    public static String subZeroAndDot(String str) {
        return str.indexOf(ThreadPoolManager.DOT) > 0 ? str.replaceAll("0+?$", "").replaceAll("[.]$", "") : str;
    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(e);
        } catch (NoSuchAlgorithmException unused) {
        }
        byte[] digest = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            if (Integer.toHexString(digest[i] & 255).length() == 1) {
                stringBuffer.append("0").append(Integer.toHexString(digest[i] & 255));
            } else {
                stringBuffer.append(Integer.toHexString(digest[i] & 255));
            }
        }
        return stringBuffer.toString();
    }

    public static int getCharCount(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i = Character.toString(str.charAt(i2)).matches("^[一-龥]{1}$") ? i + 2 : i + 1;
        }
        return i;
    }

    public static String getSubString(String str, int i) {
        return getSubString(str, i, true);
    }

    public static String getSubString(String str, int i, boolean z) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        int i2 = i + 1;
        if (getCharCount(str) <= i2) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= str.length()) {
                break;
            }
            String ch = Character.toString(str.charAt(i3));
            i4 = ch.matches("^[一-龥]{1}$") ? i4 + 2 : i4 + 1;
            if (i4 <= i2) {
                stringBuffer.append(ch);
                i3++;
            } else if (z) {
                stringBuffer.append("...");
            }
        }
        return stringBuffer.toString();
    }

    public static boolean validateEmail(String str) {
        return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(str).matches();
    }

    public static boolean validateIDcard(String str) {
        return Pattern.compile("^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|(3[0-1]))\\d{3})|([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|(3[0-1]))\\d{3}[0-9Xx])$").matcher(str).matches();
    }

    public static boolean validateLegalString(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int i2 = 0; i2 < 35; i2++) {
                if (str.charAt(i) == "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆".charAt(i2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validataLegalString2(String str) {
        if (!isEmpty(str) && validateLegalString(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (!isRightChar(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean validataLegalString3(String str) {
        if (validateLegalString(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (!isChinese(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean validataLegalString4(String str) {
        if (validateLegalString(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (!isWord(str.charAt(i)) || isChinese(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isRightChar(char c) {
        return isChinese(c) || isWord(c);
    }

    public static boolean isWord(char c) {
        return Pattern.compile("[\\w]").matcher(String.valueOf(c)).matches();
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.GENERAL_PUNCTUATION || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static void setTextViewString(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (isNullOrEmpty(str)) {
            str = "";
        }
        textView.setText(str);
    }

    public static void setTextViewString(TextView textView, String str, String str2, String str3) {
        if (textView == null) {
            return;
        }
        if (!isNullOrEmpty(str)) {
            str2 = str3;
        }
        textView.setText(str2);
    }

    public static void setViewVisible(View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void setViewGone(View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public static void setViewInvisible(View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    public static String getContentByString(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    sb.append(new String(bArr, 0, read));
                } else {
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static String getStringByStream(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine + StringUtils.LF);
                } else {
                    return stringBuffer.toString().replaceAll("\n\n", StringUtils.LF);
                }
            }
        } catch (Exception unused) {
            return null;
        } catch (OutOfMemoryError unused2) {
            System.gc();
            return null;
        }
    }

    public static String splitByIndex(String str, String str2) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(str2);
        return indexOf > -1 ? str.substring(0, indexOf) : str;
    }

    public static String splitNumAndStr(String str, String str2) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        if (matcher.find()) {
            StringBuffer stringBuffer = new StringBuffer();
            String group = matcher.group();
            stringBuffer.append(group);
            stringBuffer.append(str2);
            stringBuffer.append(str.replace(group, ""));
            return stringBuffer.toString();
        }
        return null;
    }

    public static double parseDouble(String str) {
        if (isNullOrEmpty(str) || !str.contains(",")) {
            return canParseDouble(str) ? Double.parseDouble(str) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        String str2 = "";
        for (int i = 0; i < str.split(",").length; i++) {
            str2 = str2 + str.split(",")[i];
        }
        return canParseDouble(str2) ? Double.parseDouble(str2) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static String formatNumber(double d, int i) {
        DecimalFormat decimalFormat;
        try {
            if (i == 0) {
                decimalFormat = new DecimalFormat("###0");
            } else {
                String str = "#,##0.";
                String str2 = "";
                for (int i2 = 0; i2 < i; i2++) {
                    str = str + "0";
                    str2 = str2 + "0";
                }
                decimalFormat = new DecimalFormat(str);
                if (decimalFormat.format(d).split("\\.")[1].equals(str2)) {
                    return decimalFormat.format(d).split("\\.")[0];
                }
            }
            return decimalFormat.format(d);
        } catch (Exception e) {
            LogUtils.e(e);
            return "0";
        }
    }

    public static String formatNumber(double d) {
        try {
            return new DecimalFormat("#,##0.0").format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber2(double d) {
        try {
            return new DecimalFormat("0.00").format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber2(double d, boolean z) {
        if (z) {
            try {
                return new DecimalFormat("#,##0.00").format(d);
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    public static String formatNumber3(double d) {
        return new DecimalFormat("#,##0.000").format(d);
    }

    public static String formatNumber4(double d) {
        try {
            return new DecimalFormat("0.0000").format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber(String str) {
        return formatNumber(Double.parseDouble(str));
    }

    public static String formatNumber(String str, int i) {
        return formatNumber(Double.parseDouble(str), i);
    }

    public static Map<String, String> getMapForEntry(Object obj) {
        Field[] fields;
        HashMap hashMap = new HashMap();
        try {
            for (Field field : obj.getClass().getFields()) {
                String name = field.getName();
                try {
                    String str = (String) field.get(obj);
                    if (!isNullOrEmpty(str) && str.indexOf("no limit") <= -1) {
                        hashMap.put(name, str);
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public static <T> T setMapForEntry(Map<String, String> map, Class<T> cls) {
        try {
            T newInstance = cls.newInstance();
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    newInstance.getClass().getField(entry.getKey()).set(newInstance, entry.getValue());
                }
                return newInstance;
            } catch (Exception unused) {
                return newInstance;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static boolean isPriceZero(String str) {
        return isNullOrEmpty(str) || "0".equals(splitByIndex(str, ThreadPoolManager.DOT));
    }

    public static String getPrice(String str) {
        if (str == null) {
            return "";
        }
        Matcher matcher = Pattern.compile("^\\d+").matcher(str);
        return matcher.find() ? matcher.group() : "";
    }

    public static String getPriceAll(String str) {
        if (str == null) {
            return "";
        }
        if (str.contains(ThreadPoolManager.DOT)) {
            Matcher matcher = Pattern.compile("^\\d+.\\d+").matcher(str);
            if (matcher.find()) {
                return matcher.group();
            }
        } else {
            Matcher matcher2 = Pattern.compile("^\\d+").matcher(str);
            if (matcher2.find()) {
                return matcher2.group();
            }
        }
        return "";
    }

    public static boolean isAllNumber(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                z = false;
            }
        }
        return z;
    }

    public static int bytesToInt(byte[] bArr) {
        return ((bArr[3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | (bArr[0] & 255) | ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[2] << 16) & 16711680);
    }

    public static String getPriceNum(String str) {
        return Pattern.compile("[^0-9.]").matcher(str).replaceAll("").trim();
    }

    public static String substring(String str, int i) throws UnsupportedEncodingException {
        if (str != null) {
            String str2 = "";
            if ("".equals(str)) {
                return str;
            }
            String str3 = new String(str.getBytes("UTF-8"), "UTF-8");
            if (i <= 0 || i >= str.getBytes("UTF-8").length) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                if (str3.length() > i3) {
                    char charAt = str3.charAt(i3);
                    stringBuffer.append(charAt);
                    i2 = isChineseChar(charAt) ? i2 + 2 : i2 + 1;
                    if (i2 == i) {
                        str2 = stringBuffer.toString() + "...";
                    } else if (i2 > i) {
                        if (i2 == 15) {
                            return stringBuffer.toString() + "...";
                        }
                        return str2;
                    }
                }
            }
            return stringBuffer.toString();
        }
        return str;
    }

    public static boolean isChineseChar(char c) {
        try {
            return String.valueOf(c).getBytes("UTF-8").length > 1;
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static int getChineseCount(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (isChineseChar(str.charAt(i2))) {
                i++;
            }
        }
        return i;
    }

    public static String getStringName(String... strArr) {
        String str = "";
        for (int i = 0; i < strArr.length; i++) {
            if (!isNullOrEmpty(strArr[i])) {
                str = str + strArr[i] + " ";
            }
        }
        return str;
    }

    public static boolean validateStr(String str) {
        return Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9一-龥]+$").matcher(str).matches();
    }

    public static boolean canParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NullPointerException | NumberFormatException unused) {
            return false;
        }
    }

    public static boolean canParseDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NullPointerException | NumberFormatException unused) {
            return false;
        }
    }

    public static boolean canParseFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NullPointerException | NumberFormatException unused) {
            return false;
        }
    }

    public static boolean canParseLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NullPointerException | NumberFormatException unused) {
            return false;
        }
    }

    public static boolean validateString(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[一-龿 -\u007f\u2000-\u206f\u3000-〿\uff00-\uffefͰ-Ͽ℀-◿]+$").matcher(str).matches();
    }

    public static boolean isHavePhoneNum(String str, int i) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder("\\d{");
        sb.append(i);
        sb.append("}");
        return Pattern.compile(sb.toString()).matcher(str).find();
    }

    public static boolean isRegisterName(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[\\u2E80-\\u9FFF\\[a-zA-Z]{2,10}$").matcher(str).matches();
    }

    public static boolean isOkPassword(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return Pattern.compile("^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）――+|{}【】‘；：”“'。，、？]){8,16}$").matcher(str).matches();
    }

    public static boolean isLetterNumber(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[a-z0-9A-Z_]*$").matcher(str).matches();
    }

    public static boolean isInteger(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[1-9]\\d{0,5}$").matcher(str).matches();
    }

    public static boolean validatePhoneNumber(String... strArr) {
        if (strArr == null || strArr.length == 0 || strArr.length <= 0) {
            return false;
        }
        return Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$").matcher(strArr[0]).matches();
    }

    public static String getTextZi(String str, int i) {
        if (str != null) {
            if (str.length() == 0) {
                return "";
            }
            if (str.length() >= i) {
                return str.substring(0, i) + "...";
            }
            return str;
        }
        return str;
    }

    public static String getDataTime(long j) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(j));
    }

    public static String getText(EditText editText) {
        return editText != null ? editText.getText().toString().trim() : "";
    }

    public static String getResString(int i) {
        return AppContextUtil.getContext().getResources().getString(i);
    }

    public static String url(Map<String, String> map) {
        int i = 0;
        String str = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null) {
                str = i == 0 ? str + entry.getKey() + "=" + ((Object) entry.getValue()) : str + "&" + entry.getKey() + "=" + ((Object) entry.getValue());
            }
            i++;
        }
        return str;
    }

    public static String getHost(String str) {
        String str2;
        if (!startsWithIgnoreCase(str, ChainUtil.Request_HTTP) && !startsWithIgnoreCase(str, "https://")) {
            str = ChainUtil.Request_HTTP + str;
        }
        try {
            str2 = new URI(str).getHost();
        } catch (Exception unused) {
            str2 = "";
        }
        return (endsWithIgnoreCase(str2, ".html") || endsWithIgnoreCase(str2, ".htm")) ? "" : str2;
    }

    private static boolean startsWithIgnoreCase(String str, String str2) {
        return !isEmpty(str) && str.toLowerCase().startsWith(str2.toLowerCase());
    }

    private static boolean endsWithIgnoreCase(String str, String str2) {
        return !isEmpty(str) && str.toLowerCase().endsWith(str2.toLowerCase());
    }
}
