package com.tron.wallet.common.components.countdownview;

import android.content.Context;
final class Utils {
    public static String formatZero(int i) {
        return "0";
    }

    Utils() {
    }

    public static int dp2px(Context context, float f) {
        if (f <= 0.0f) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float sp2px(Context context, float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        return f * context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static String formatNum(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    public static String formatMillisecond(int i) {
        if (i > 99) {
            return String.valueOf(i / 10);
        }
        if (i <= 9) {
            return "0" + i;
        }
        return String.valueOf(i);
    }
}
