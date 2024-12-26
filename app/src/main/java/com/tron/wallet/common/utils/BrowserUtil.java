package com.tron.wallet.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tronlinkpro.wallet.R;
import java.net.MalformedURLException;
import java.net.URL;
public class BrowserUtil {
    public static final String DEFAULT_ICON = "/favicon.ico";

    public static void openBrowser(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str) || !str.startsWith(UriUtil.HTTP_SCHEME)) {
                return;
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            LogUtils.e(e);
            ToastUtil.getInstance().show(context, context.getString(R.string.no_browser_available));
        }
    }

    public static String getIcon(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            URL url = new URL(str);
            if (url.getPort() == -1) {
                return url.getProtocol() + "://" + url.getHost() + DEFAULT_ICON;
            }
            return url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + DEFAULT_ICON;
        } catch (MalformedURLException e) {
            LogUtils.e(e);
            return "";
        }
    }
}
