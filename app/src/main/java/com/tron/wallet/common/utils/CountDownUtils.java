package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import j$.util.DesugarTimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class CountDownUtils {
    public static int getVoteCountDownEndTime() {
        return ((int) (Math.floor((float) ((System.currentTimeMillis() - getStartTime()) / 21600000)) + 1.0d)) * 6;
    }

    private static long getStartTime() {
        Calendar calendar = Calendar.getInstance(DesugarTimeZone.getTimeZone("UTC"));
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private static long getSpecialTime(int i) {
        Calendar calendar = Calendar.getInstance(DesugarTimeZone.getTimeZone("UTC"));
        calendar.set(11, i);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int[] getVoteCountTime() {
        try {
            return toHhmmss(getSpecialTime(getVoteCountDownEndTime()) - System.currentTimeMillis());
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static void getTime(long j) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static int[] toHhmmss(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 3600;
        return new int[]{(int) (j2 / 3600), (int) (j3 / 60), (int) (j3 % 60)};
    }
}
