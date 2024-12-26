package com.tron.wallet.common.components.bannerview.utils;

import android.content.res.Resources;
import android.util.Log;
public class BannerUtils {
    private static boolean debugMode = false;

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean z) {
        debugMode = z;
    }

    public static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static void log(String str, String str2) {
        if (isDebugMode()) {
            Log.e(str, str2);
        }
    }

    public static void log(String str) {
        if (isDebugMode()) {
            Log.e("BannerView", str);
        }
    }

    public static int getRealPosition(boolean z, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (z) {
            i--;
        }
        return (i + i2) % i2;
    }
}
