package org.tron.common.utils;

import android.util.Log;
public class LogUtils {
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_ERROR = 5;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_NONE = 0;
    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_WARN = 4;
    private static int mDebuggable = 0;
    private static final Object mLogLock = new Object();
    private static String mTag = "Tron";
    private static long mTimestamp;

    public static void init(int i) {
        if (i < 0 || i > 5) {
            return;
        }
        mDebuggable = i;
    }

    public static void v(String str) {
        if (mDebuggable >= 1) {
            String str2 = mTag;
            Log.v(str2, "" + str);
        }
    }

    public static void v(String str, String str2) {
        if (mDebuggable >= 1) {
            Log.v(str, "" + str2);
        }
    }

    public static void d(String str) {
        if (mDebuggable >= 2) {
            String str2 = mTag;
            Log.d(str2, "" + str);
        }
    }

    public static void d(String str, String str2) {
        if (mDebuggable >= 2) {
            Log.d(str, "" + str2);
        }
    }

    public static void i(String str) {
        if (mDebuggable >= 3) {
            String str2 = mTag;
            Log.i(str2, "" + str);
        }
    }

    public static void i(String str, String str2) {
        if (mDebuggable >= 3) {
            Log.i(str, "" + str2);
        }
    }

    public static void w(String str) {
        if (mDebuggable >= 4) {
            String str2 = mTag;
            Log.w(str2, "" + str);
        }
    }

    public static void w(String str, String str2) {
        if (mDebuggable >= 4) {
            Log.w(str, "" + str2);
        }
    }

    public static void w(Throwable th) {
        if (mDebuggable >= 4) {
            Log.w(mTag, "", th);
        }
    }

    public static void w(String str, Throwable th) {
        if (mDebuggable < 4 || str == null) {
            return;
        }
        String str2 = mTag;
        Log.w(str2, "" + str, th);
    }

    public static void e(String str) {
        if (mDebuggable >= 5) {
            String str2 = mTag;
            Log.e(str2, "" + str);
        }
    }

    public static void e(String str, String str2) {
        if (mDebuggable >= 5) {
            Log.e(str, "" + str2);
        }
    }

    public static void e(Throwable th) {
        if (mDebuggable >= 5) {
            Log.e(mTag, "", th);
        }
    }

    public static void e(String str, Throwable th) {
        if (mDebuggable < 5 || str == null) {
            return;
        }
        String str2 = mTag;
        Log.e(str2, "" + str, th);
    }
}
