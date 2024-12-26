package com.tron.wallet.common.utils;

import android.content.Context;
import android.text.TextUtils;
public class ProxyCheckUtil {
    public static boolean isWifiProxy(Context context) {
        String property = System.getProperty("http.proxyHost");
        String property2 = System.getProperty("http.proxyPort");
        if (property2 == null) {
            property2 = "-1";
        }
        return TextUtils.isEmpty(property) || Integer.parseInt(property2) == -1;
    }
}
