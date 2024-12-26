package com.tron.wallet.common.utils;

import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class HtmlUtil {
    public static String findFavicon(String str, String str2) {
        Uri parse;
        if (str == null) {
            return null;
        }
        String findGroup = findGroup(str2, "<link ([^>]*rel=\"shortcut icon\"[^>]*)>");
        if (findGroup == null) {
            findGroup = findGroup(str2, "<link ([^>]*rel=\"icon\"[^>]*)>");
        }
        if (findGroup != null) {
            String findGroup2 = findGroup(findGroup, "href=\"([^\"]+)\"");
            if (findGroup2 == null || findGroup2.startsWith(ChainUtil.Request_HTTP) || findGroup2.startsWith("https://")) {
                return findGroup2;
            }
            String str3 = "";
            if (findGroup2.startsWith("./")) {
                findGroup2 = findGroup2.replaceFirst("./", "");
            }
            if (!findGroup2.startsWith("/")) {
                findGroup2 = "/" + findGroup2;
            }
            if (Uri.parse(str).getPort() > 0) {
                str3 = ":" + parse.getPort();
            }
            return parse.getScheme() + "://" + parse.getHost() + str3 + findGroup2;
        }
        return null;
    }

    private static String findGroup(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile(str2).matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (Throwable th) {
            LogUtils.e(th);
            return null;
        }
    }

    public static String urlTrim(String str) {
        if (com.tron.tron_base.frame.utils.StringTronUtil.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        return trim.endsWith("/") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public static boolean sameUrl(String str, String str2) {
        return urlTrimToPath(str).equals(urlTrimToPath(str2));
    }

    public static String urlTrimToPath(String str) {
        if (com.tron.tron_base.frame.utils.StringTronUtil.isEmpty(str)) {
            return "";
        }
        try {
            if (!str.startsWith(UriUtil.HTTP_SCHEME)) {
                str = ChainUtil.Request_HTTP + str;
            }
            Uri parse = Uri.parse(str);
            return urlTrim(parse.getHost() + parse.getPath());
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }
}
