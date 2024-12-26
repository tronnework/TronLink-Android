package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
public class SentryUtil {
    public static final String dsn = "http://3d932e47fdfb4b0d99199df9556c6914@47.242.46.199:9000/3";

    public static void captureException(Throwable th) {
        LogUtils.e(th);
    }

    public static void captureMessage(String str) {
        LogUtils.d(str);
    }
}
