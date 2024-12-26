package com.tron.wallet.common.utils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
public class DateUtils {
    public static final String DATE_FORMAT_HH_MM_US = "MM/dd/yyyy HH:mm";
    public static final String DATE_FORMAT_HH_MM_ZH = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT_US = "MM/dd/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_ZH = "yyyy/MM/dd HH:mm:ss";
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_TO_STRING_DETAIAL_PATTERN_default_s = "yyyy-MM-dd HH:mm";
    public static String DATE_TO_STRING_DETAIAL_PATTERN_default_s_english = "MM-dd-yyyy HH:mm";
    public static String DATE_TO_STRING_NO_YEAR = "MM/dd HH:mm";
    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";
    public static String DATE_TO_STRING_dot_default_s = "yyyy.MM.dd HH:mm";
    private static SimpleDateFormat simpleDateFormat;

    public static String DateToString(Date date, String str) {
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str);
        simpleDateFormat = simpleDateFormat2;
        return simpleDateFormat2.format(date);
    }

    public static String longToString(long j, String str) throws ParseException {
        return dateToString(longToDate(j, str), str);
    }

    public static Date longToDate(long j, String str) throws ParseException {
        return stringToDate(dateToString(new Date(j), str), str);
    }

    public static long stringToLong(String str, String str2) throws ParseException {
        Date stringToDate = stringToDate(str, str2);
        if (stringToDate == null) {
            return 0L;
        }
        return dateToLong(stringToDate);
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static Date stringToDate(String str, String str2) throws ParseException {
        return new SimpleDateFormat(str2).parse(str);
    }

    public static String dateToString(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String diffLanguageDateToString(Date date, String str) {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return dateToString(date, str);
        }
        return dateToString(date, DATE_TO_STRING_DETAIAL_PATTERN_default_s_english);
    }

    public static String diffLanguageDateToString2(Date date, String str) {
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return dateToString(date, str);
        }
        return dateToString(date, "MM-dd-yyyy HH:mm:ss");
    }

    public static String diffLanguageDate(long j) {
        return new SimpleDateFormat(LanguageUtils.languageZH(AppContextUtil.getContext()) ? DATE_FORMAT_ZH : DATE_FORMAT_US).format(new Date(j));
    }

    public static String formatLanguageDate(long j) {
        Date date = new Date(j);
        LanguageUtils.languageZH(AppContextUtil.getContext());
        return new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN).format(date);
    }

    public static String diffLanguageDateHHMM(long j) {
        return new SimpleDateFormat(LanguageUtils.languageZH(AppContextUtil.getContext()) ? DATE_FORMAT_HH_MM_ZH : DATE_FORMAT_HH_MM_US).format(new Date(j));
    }

    public static String diffLanguageDateWithoutYear(long j) {
        return new SimpleDateFormat(DATE_TO_STRING_NO_YEAR).format(new Date(j));
    }

    public static Date getNowDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat2.parse(simpleDateFormat2.format(date), new ParsePosition(8));
    }

    public static Date getNowDateShort() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat2.parse(simpleDateFormat2.format(date), new ParsePosition(8));
    }

    public static String getStringDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getStringDateShort() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getTimeShort() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static Date strToDateLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0));
    }

    public static String getNowTimeString() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }

    public static String dateToStrLong(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String dateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0));
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getLastDate(long j) {
        return new Date(new Date().getTime() - (j * 122400000));
    }

    public static String getStringToday() {
        return new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
    }

    public static String getHour() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).substring(11, 13);
    }

    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).substring(14, 16);
    }

    public static String getUserDate(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static String getTwoHour(String str, String str2) {
        String[] split = str.split(":");
        String[] split2 = str2.split(":");
        if (Integer.parseInt(split[0]) < Integer.parseInt(split2[0])) {
            return "0";
        }
        double parseDouble = (Double.parseDouble(split[0]) + (Double.parseDouble(split[1]) / 60.0d)) - (Double.parseDouble(split2[0]) + (Double.parseDouble(split2[1]) / 60.0d));
        if (parseDouble > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return parseDouble + "";
        }
        return "0";
    }

    public static String getTwoDay(String str, String str2) {
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long time = (simpleDateFormat2.parse(str).getTime() - simpleDateFormat2.parse(str2).getTime()) / org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;
            return time + "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getPreTime(String str, String str2) {
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat2.parse(str);
            parse.setTime(((parse.getTime() / 1000) + (Integer.parseInt(str2) * 60)) * 1000);
            return simpleDateFormat2.format(parse);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getNextDay(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date strToDate = strToDate(str);
            strToDate.setTime(((strToDate.getTime() / 1000) + (Integer.parseInt(str2) * 86400)) * 1000);
            return simpleDateFormat2.format(strToDate);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isLeapYear(String str) {
        Date strToDate = strToDate(str);
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
        gregorianCalendar.setTime(strToDate);
        int i = gregorianCalendar.get(1);
        if (i % 400 == 0) {
            return true;
        }
        return i % 4 == 0 && i % 100 != 0;
    }

    public static String getEDate(String str) {
        String[] split = new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0)).toString().split(" ");
        return split[2] + split[1].toUpperCase() + split[5].substring(2, 4);
    }

    public static String getEndDateOfMonth(String str) {
        String substring = str.substring(0, 8);
        int parseInt = Integer.parseInt(str.substring(5, 7));
        if (parseInt == 1 || parseInt == 3 || parseInt == 5 || parseInt == 7 || parseInt == 8 || parseInt == 10 || parseInt == 12) {
            return substring + "31";
        } else if (parseInt == 4 || parseInt == 6 || parseInt == 9 || parseInt == 11) {
            return substring + "30";
        } else if (isLeapYear(str)) {
            return substring + "29";
        } else {
            return substring + "28";
        }
    }

    public static String getSeqWeek() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        String num = Integer.toString(calendar.get(3));
        if (num.length() == 1) {
            num = "0" + num;
        }
        return Integer.toString(calendar.get(1)) + num;
    }

    public static long getDays(String str, String str2) {
        Date date;
        if (str == null || str.equals("") || str2 == null || str2.equals("")) {
            return 0L;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        try {
            date = simpleDateFormat2.parse(str);
            try {
                date2 = simpleDateFormat2.parse(str2);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            date = null;
        }
        return (date.getTime() - date2.getTime()) / org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;
    }

    public static String getNo(int i) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(i);
    }

    public static String getRandom(int i) {
        Random random = new Random();
        String str = "";
        if (i == 0) {
            return "";
        }
        for (int i2 = 0; i2 < i; i2++) {
            str = str + random.nextInt(9);
        }
        return str;
    }

    public static boolean isCurrentYear(Long l) {
        return new Date(l.longValue()).getYear() == new Date(System.currentTimeMillis()).getYear();
    }

    public static boolean isIn48Hours(long j) {
        return (((double) (System.currentTimeMillis() - j)) * 1.0d) / 3600000.0d <= 48.0d;
    }
}
