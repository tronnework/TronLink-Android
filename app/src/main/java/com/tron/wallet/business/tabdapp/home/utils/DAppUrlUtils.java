package com.tron.wallet.business.tabdapp.home.utils;

import android.net.Uri;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.DappBlackListController;
public class DAppUrlUtils {
    private static final String PHISHING = "https://www.tronlink.org/phishing.html?href=";

    public static String getHost(String str) {
        if (str == null) {
            return "";
        }
        if (str.endsWith(".pdf")) {
            str = str.replace("file:///android_asset/pdf.html?", "");
        }
        int indexOf = str.indexOf("://");
        if (indexOf >= 0) {
            str = str.substring(indexOf + 3);
        }
        String replace = str.replace("www.", "");
        int indexOf2 = replace.indexOf("/");
        return indexOf2 >= 0 ? replace.substring(0, indexOf2) : replace;
    }

    public static String wrapPhishingUrl(String str) {
        if (DappBlackListController.getInstance().isBlack(StringTronUtil.getHost(str))) {
            return PHISHING + str;
        }
        return str;
    }

    public static boolean isPhishingUrl(String str) {
        return str != null && str.startsWith(PHISHING);
    }

    public static String addUrlSuffix(String str) {
        if (str != null) {
            try {
                if (str.contains(BrowserConstant.SHARE_SUFFIX)) {
                    return str;
                }
            } catch (Throwable th) {
                SentryUtil.captureException(th);
                return str;
            }
        }
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("utm_source", "tronlink");
        return buildUpon.toString();
    }

    public static String addQueryParameter(String str, String str2, String str3) {
        try {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter(str2, str3);
            return buildUpon.toString();
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            return str;
        }
    }

    public static String removeUrlSuffixParameter(String str) {
        if (str != null) {
            try {
                if (str.contains(BrowserConstant.SHARE_SUFFIX)) {
                    int indexOf = str.indexOf(BrowserConstant.SHARE_SUFFIX);
                    int i = indexOf + 19;
                    if (indexOf > 1 && i <= str.length()) {
                        int i2 = indexOf - 1;
                        if (str.charAt(i2) == '?') {
                            if (str.length() > i && str.charAt(i) == '&') {
                                str = str.replaceAll("utm_source=tronlink&", "");
                            } else {
                                str = str.replaceAll("\\?utm_source=tronlink", "");
                            }
                        } else if (str.charAt(i2) == '&') {
                            if (str.length() > i && str.charAt(i) == '&') {
                                str = str.replaceAll("&utm_source=tronlink", "");
                            } else {
                                str = str.replaceAll("&utm_source=tronlink", "");
                            }
                        } else if (str.length() > i && str.charAt(i) == '&') {
                            str = str.replaceAll("utm_source=tronlink&&", "");
                        } else {
                            str = str.replaceAll(BrowserConstant.SHARE_SUFFIX, "");
                        }
                    }
                }
            } catch (Throwable th) {
                SentryUtil.captureException(th);
            }
        }
        return str;
    }
}
