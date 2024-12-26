package com.tron.tron_base.frame.utils;
public class ChannelUtils {
    public static String getGoogleAnalyticsChannel() {
        try {
            String string = AppContextUtil.getContext().getPackageManager().getApplicationInfo(AppContextUtil.getContext().getPackageName(), 128).metaData.getString("CHANNEL");
            return string != null ? string : "";
        } catch (Exception unused) {
            return "";
        }
    }
}
