package com.tron.wallet.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.MobileInfoUtil;
import com.tron.wallet.BuildConfig;
import com.tron.wallet.db.SpAPI;
import org.apache.commons.lang3.StringUtils;
public class ChannelUtils {
    public static final String GOOGLEPLAY = "googleplay";
    public static final String OFFICIAL = "official";
    public static final String TRON_OFFICIAL = "tron_official";

    public static String getChannel(Context context) {
        ApplicationInfo applicationInfo;
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData.getString("CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static void saveConfigInfo(Context context) {
        String channelFile = com.tron.tron_base.frame.utils.FileUtils.getChannelFile(context);
        if (channelFile == null || SpAPI.THIS.isInsertChannelVersionCode(BuildConfig.VERSION_NAME)) {
            return;
        }
        com.tron.tron_base.frame.utils.FileUtils.writeFile(getChannelContent(context), channelFile, true);
        SpAPI.THIS.setIsInsertChannelVersionCode(BuildConfig.VERSION_NAME);
    }

    private static String getChannelContent(Context context) {
        String str;
        try {
            str = MobileInfoUtil.getMacAddress();
        } catch (Exception e) {
            LogUtils.e(e);
            str = "null_Imei_Android";
        }
        return com.tron.tron_base.frame.utils.ChannelUtils.getGoogleAnalyticsChannel() + "\n4.14.3\n2012221301\n" + str + StringUtils.LF;
    }

    public static void toMarket(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + AppUtils.getAppPackageName()));
            intent.setPackage("com.android.vending");
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + AppUtils.getAppPackageName())));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
