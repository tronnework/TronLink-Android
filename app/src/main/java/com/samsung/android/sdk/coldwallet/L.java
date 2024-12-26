package com.samsung.android.sdk.coldwallet;

import android.util.Log;
final class L {
    public static final String TAG = "ScwSDK";

    L() {
    }

    public static void v(String str, String str2) {
        Log.v(TAG, "[" + str + "] " + str2);
    }

    public static void d(String str, String str2) {
        Log.d(TAG, "[" + str + "] " + str2);
    }

    public static void i(String str, String str2) {
        Log.i(TAG, "[" + str + "] " + str2);
    }

    public static void e(String str, String str2) {
        Log.e(TAG, "[" + str + "] " + str2);
    }
}
