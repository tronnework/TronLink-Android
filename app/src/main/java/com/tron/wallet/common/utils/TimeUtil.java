package com.tron.wallet.common.utils;

import android.content.Context;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tronlinkpro.wallet.R;
import java.util.concurrent.TimeUnit;
public class TimeUtil {
    public static long defaultThreeDay = 3;
    public static long defaultThreeDayMs = TimeUnit.DAYS.toMillis(defaultThreeDay);
    public static long defaultLockPeriod = 86400;
    public static String dayString = "";
    public static String hourString = "";
    public static String minString = "";

    public static void init(Context context) {
        String str = LanguageUtils.languageZH(context) ? " " : "";
        dayString = str + context.getString(R.string.day);
        hourString = str + context.getString(R.string.hour);
        minString = str + context.getString(R.string.minute);
    }

    public static String formatTime(Context context, long j) {
        String str;
        long days = TimeUnit.MILLISECONDS.toDays(j);
        long hours = TimeUnit.MILLISECONDS.toHours(j) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(j));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j));
        long seconds2 = j - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toSeconds(seconds));
        int i = (days > 0L ? 1 : (days == 0L ? 0 : -1));
        int i2 = (hours > 0L ? 1 : (hours == 0L ? 0 : -1));
        boolean z = i2 > 0;
        int i3 = (minutes > 0L ? 1 : (minutes == 0L ? 0 : -1));
        boolean z2 = i3 > 0;
        String str2 = "";
        if (i > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(days);
            sb.append(dayString);
            if (z) {
                str = " " + hours + hourString;
            } else {
                str = "";
            }
            sb.append(str);
            if (z2) {
                str2 = " " + minutes + minString;
            }
            sb.append(str2);
            return sb.toString();
        } else if (i2 > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(hours);
            sb2.append(hourString);
            if (z2) {
                str2 = " " + minutes + minString;
            }
            sb2.append(str2);
            return sb2.toString();
        } else if (i3 > 0) {
            return " " + minutes + minString;
        } else if (seconds > 0 || seconds2 > 0) {
            return "1" + minString;
        } else {
            return "0" + minString;
        }
    }

    public static long formatSeconds2DayWithDefaultThreeDays(long j) {
        return j < defaultThreeDayMs ? defaultThreeDay : ms2Days(j);
    }

    public static long formatSeconds2Day(long j) {
        long j2 = 86400;
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        long j5 = 3600;
        long j6 = j4 / j5;
        return (j6 > 0 || (j4 - (j5 * j6)) / ((long) 60) > 0) ? j3 + 1 : j3;
    }

    public static String fromMs2Day(Context context, long j) {
        return formatSeconds2Day(j / 1000) + dayString;
    }

    public static String fromDay2Day(Context context, long j) {
        return j + dayString;
    }

    public static long[] time2OptionS(long j) {
        long j2 = 86400000;
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        long j5 = 3600000;
        long j6 = j4 / j5;
        return new long[]{j3, j6, (j4 - (j5 * j6)) / 60000};
    }

    public static long days2Ms(long j) {
        return BigDecimalUtils.mul_((Object) Long.valueOf(j * 86400), (Object) 1000).longValue();
    }

    public static long ms2DayOfMs(long j) {
        long days = TimeUnit.MILLISECONDS.toDays(j);
        if (j > TimeUnit.DAYS.toMillis(days)) {
            return TimeUnit.DAYS.toMillis(days + 1);
        }
        return TimeUnit.DAYS.toMillis(days);
    }

    public static long ms2Days(long j) {
        long days = TimeUnit.MILLISECONDS.toDays(j);
        return j > TimeUnit.DAYS.toMillis(days) ? days + 1 : days;
    }

    public static long formatMsWithDefaultThreeDaysOfMs(long j) {
        long j2 = defaultThreeDayMs;
        return j < j2 ? j2 : ms2DayOfMs(j);
    }
}
