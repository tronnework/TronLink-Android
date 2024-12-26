package com.tron.wallet.common.utils;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Ascii;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.db.SpAPI;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.crypto.Hash;
import org.tron.common.crypto.SymmEncoder;
import org.tron.common.utils.Base58;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.ByteUtil;
import org.tron.config.Parameter;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.Wallet;
public class StringTronUtil {
    public static final String EMPTY_STRING = "";

    public enum TronAddress {
        TRON,
        NULL
    }

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
            for (int i2 = 0; i2 < "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\\n|\\\r|\\\t".length(); i2++) {
                if (str.charAt(i) == "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\\n|\\\r|\\\t".charAt(i2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validataLegalString2(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!isRightChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isContainSpace(String str) {
        return (isEmpty(str) || str.indexOf(" ") == -1) ? false : true;
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
        return !EmojiUtils.emojiFilter(c) || isEmpty(String.valueOf(c));
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

    public static String plainScientificNotation(Number number) {
        try {
            return new DecimalFormat("#,###.#####################").format(number);
        } catch (Exception unused) {
            return number.toString();
        }
    }

    public static String plainScientificNotationNoDot(Number number) {
        try {
            return new DecimalFormat("###.#####################").format(number);
        } catch (Exception unused) {
            return number.toString();
        }
    }

    public static String divideDecimal(Object obj, String str) {
        try {
            if (canParseInt(str)) {
                return BigDecimalUtils.div_(obj, Double.valueOf(Math.pow(10.0d, Integer.parseInt(str)))).toPlainString();
            }
            return obj.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return obj.toString();
        }
    }

    public static String plainScientificNotation(Number number, int i) {
        String str = "#,###.";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + "#";
        }
        try {
            DecimalFormat decimalFormat = new DecimalFormat(str);
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(number);
        } catch (Exception unused) {
            return number.toString();
        }
    }

    public static String formatNumber0(double d) {
        try {
            return new DecimalFormat("#,###").format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber0(int i) {
        try {
            return new DecimalFormat("#,###").format(i);
        } catch (Exception unused) {
            return "";
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

    public static String formatNumber1(double d) {
        try {
            return new DecimalFormat("0.0").format(d);
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

    public static String formatNumber3Truncate(Number number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.###");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(number);
    }

    public static String formatNumber2Truncate(Number number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(number);
    }

    public static String formatPrice3(Number number) {
        Object[] objArr = new Object[2];
        objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
        objArr[1] = formatNumber3Truncate(number);
        return String.format("%s%s", objArr);
    }

    public static String formatPrice6(Number number) {
        Object[] objArr = new Object[2];
        objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
        objArr[1] = formatNumber6Truncate(number);
        return String.format("%s%s", objArr);
    }

    public static String formatPriceLessThan(Number number) {
        Object[] objArr = new Object[2];
        objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
        objArr[1] = formatNumber3Truncate(number);
        return String.format("<%s%s", objArr);
    }

    public static String formatPrice0(Number number) {
        Object[] objArr = new Object[2];
        objArr[0] = SpAPI.THIS.isUsdPrice() ? "$" : Html.fromHtml("&yen;");
        objArr[1] = formatNumber0Truncate(BigDecimalUtils.toBigDecimal(number));
        return String.format("%s%s", objArr);
    }

    public static String formatNumber4(double d) {
        try {
            return new DecimalFormat("0.0000").format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber6TruncatePlainScientific(double d) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,###.######");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber6(double d) {
        try {
            return new DecimalFormat("0.000000").format(d);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber6Truncate(Number number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.######");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(number);
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
                    if (!isNullOrEmpty(str) && str.indexOf(" unlimited") <= -1) {
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

    public static int getStringCount(String str) {
        int i = 0;
        try {
            String trim = str.trim();
            int i2 = 0;
            while (i < trim.length()) {
                try {
                    char charAt = trim.charAt(i);
                    i2 = (charAt < ' ' || charAt > 'z') ? i2 + 2 : i2 + 1;
                    i++;
                } catch (Exception e) {
                    e = e;
                    i = i2;
                    SentryUtil.captureException(e);
                    LogUtils.e(e);
                    return i;
                }
            }
            return i2;
        } catch (Exception e2) {
            e = e2;
        }
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

    public static boolean isOkPasswordTwo(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$").matcher(str).matches();
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

    public static String getTextTrimEnd(EditText editText) throws InvalidNameException {
        return editText != null ? trimEnd(editText.getText().toString().toCharArray()) : "";
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

    public static byte[] getPasswordHash(String str) {
        if (isPasswordValid(str)) {
            return Arrays.copyOfRange(Hash.sha256(Hash.sha256(str.getBytes())), 0, 16);
        }
        return null;
    }

    public static byte[] getEncKey(String str) {
        if (isOkPasswordTwo(str)) {
            return Arrays.copyOfRange(Hash.sha256(str.getBytes()), 0, 16);
        }
        return null;
    }

    public static boolean checkPassword(String str, String str2) {
        byte[] passwordHash = getPasswordHash(str2);
        if (passwordHash == null) {
            return false;
        }
        return ByteArray.toHexString(passwordHash).equals(SpAPI.THIS.getPassWord(str));
    }

    public static boolean checkPassword(Wallet wallet, String str) {
        byte[] passwordHash = getPasswordHash(str);
        if (passwordHash == null) {
            return false;
        }
        return ByteArray.toHexString(passwordHash).equals(wallet.getEncryptedPassword());
    }

    public static boolean isNameValid(String str) {
        return !str.isEmpty();
    }

    public static boolean isPasswordValid(String str) {
        return (StringUtils.isEmpty(str) || str.length() < 6 || str.contains("\\s")) ? false : true;
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
        byte[] hash = org.tron.common.utils.Sha256Hash.hash(org.tron.common.utils.Sha256Hash.hash(bArr));
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
            LogUtils.e(e);
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

    public static boolean isPrivateKeyValid(String str) {
        if (!isEmpty(str) && str.length() <= 64) {
            try {
                new BigInteger(str, 16);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static String decryptPrivateKey(byte[] bArr, String str) {
        byte[] AES128EcbDec = SymmEncoder.AES128EcbDec(bArr, getEncKey(str));
        return AES128EcbDec != null ? Hex.toHexString(AES128EcbDec) : "";
    }

    public static double bigDecimal2(double d) {
        return new BigDecimal(d).setScale(2, 4).doubleValue();
    }

    public static String zeros(int i) {
        return repeat('0', i);
    }

    public static String repeat(char c, int i) {
        return new String(new char[i]).replace("\u0000", String.valueOf(c));
    }

    public static String trimEnd(char[] cArr) throws InvalidNameException {
        int length = cArr.length;
        while (length > 0 && cArr[length - 1] <= ' ') {
            length--;
        }
        String replaceAll = (length < cArr.length ? new String(cArr).substring(0, length) : new String(cArr)).replaceAll("\n|\r", "").replaceAll("/", "");
        if (isEmpty(replaceAll)) {
            throw new InvalidNameException("contains:/");
        }
        return replaceAll;
    }

    public static void sort(byte[] bArr) {
        int length = bArr.length / 2;
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - i) - 1];
            bArr[(bArr.length - i) - 1] = b;
        }
    }

    public static String formatNumber6Truncate(BigDecimal bigDecimal) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.######");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(bigDecimal);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatBigDecimal3Truncate(BigDecimal bigDecimal) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.###");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(bigDecimal);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatBigDecimal0(BigDecimal bigDecimal) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("###0");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(bigDecimal);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber0Truncate(BigDecimal bigDecimal) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(bigDecimal);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumber6TruncateNoDots(BigDecimal bigDecimal) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("###0.######");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(bigDecimal);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String formatNumberTruncateNoDots(Object obj, int i) {
        return formatNumberTruncateNoDots(BigDecimalUtils.toBigDecimal(obj), i);
    }

    public static String formatNumberTruncateNoDots(BigDecimal bigDecimal, int i) {
        if (i <= 0) {
            LogUtils.w("Fraction must be positive.");
            return bigDecimal.toPlainString();
        }
        String str = "#0.";
        for (int i2 = 0; i2 < i; i2++) {
            try {
                str = str + "#";
            } catch (Exception e) {
                LogUtils.e(e);
                return bigDecimal.toPlainString();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat(str);
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(bigDecimal);
    }

    public static TronAddress isAddressValid2(String str) {
        if (isEmpty(str)) {
            return TronAddress.NULL;
        }
        return isAddressValid(str) ? TronAddress.TRON : TronAddress.NULL;
    }

    public static boolean isHexString(String str) {
        return str.matches("^[A-Fa-f0-9]+$");
    }

    public static boolean is64HexString(String str) {
        return str.matches("^[0-9a-fA-F]{64}");
    }

    public static byte[] longTo32Bytes(long j) {
        return ByteUtil.merge(new byte[24], ByteArray.fromLong(j));
    }

    public static String removeDot(String str) {
        int indexOf = str.indexOf(ThreadPoolManager.DOT);
        return indexOf < 0 ? str : str.substring(0, indexOf);
    }

    public static String subLeadingZeros(String str) {
        try {
            String replaceFirst = str.replaceFirst("^0+(?!$)", "");
            if (replaceFirst.startsWith(ThreadPoolManager.DOT)) {
                return "0" + replaceFirst;
            }
            return replaceFirst;
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static boolean fuzzyContains(String str, String str2) {
        if (str == null || str2 == null || str.length() < str2.length()) {
            return false;
        }
        int i = 0;
        for (int length = str2.length(); length <= str.length(); length++) {
            if (str.substring(i, length).equalsIgnoreCase(str2)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static String replaceBySymbolChars(String str, int i, int i2, char c, int i3) {
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < i3; i4++) {
            sb.append(c);
        }
        if (TextUtils.isEmpty(str)) {
            return sb.toString();
        }
        if (i < 0 || i >= i2 || i2 >= str.length()) {
            return str;
        }
        sb.insert(0, str, 0, i);
        sb.append((CharSequence) str, i2, str.length());
        return sb.toString();
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        int length;
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || (length = charSequence.length()) != charSequence2.length()) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
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

    public static String indentAddress(String str) {
        return indentAddress(str, 3);
    }

    public static String indentAddress(String str, int i) {
        int length = str.length();
        if (length - 1 >= i && i * 2 < length + 2) {
            String substring = str.substring(0, i);
            String substring2 = str.substring(length - i, length);
            return substring + "..." + substring2;
        }
        return str;
    }
}
