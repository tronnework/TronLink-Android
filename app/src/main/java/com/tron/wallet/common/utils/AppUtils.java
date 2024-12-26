package com.tron.wallet.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.File;
import java.util.Random;
public class AppUtils {
    public static String getAppPackageName() {
        return AppContextUtil.getContext().getApplicationInfo().packageName;
    }

    public static String getVersionName() {
        String str;
        Exception e;
        try {
            str = AppContextUtil.getContext().getPackageManager().getPackageInfo(AppContextUtil.getContext().getPackageName(), 0).versionName;
        } catch (Exception e2) {
            str = "1.0";
            e = e2;
        }
        if (str != null) {
            try {
            } catch (Exception e3) {
                e = e3;
                LogUtils.e(e);
                SentryUtil.captureException(e);
                return str;
            }
            if (str.length() > 0) {
                return str;
            }
        }
        return "1.0";
    }

    public static int getVersionCode() {
        try {
            return AppContextUtil.getContext().getPackageManager().getPackageInfo(AppContextUtil.getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            return 0;
        }
    }

    public static void installApk(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        File file = new File(str);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(context, getAppPackageName() + ".fileprovider", file);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    public static Object getBuildConfigValue(Context context, String str) {
        try {
            return Class.forName(context.getPackageName() + ".BuildConfig").getField(str).get(null);
        } catch (ClassNotFoundException e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return null;
        } catch (IllegalAccessException e2) {
            SentryUtil.captureException(e2);
            LogUtils.e(e2);
            return null;
        } catch (NoSuchFieldException e3) {
            SentryUtil.captureException(e3);
            LogUtils.e(e3);
            return null;
        }
    }

    public static String getMetaForKey(String str) {
        try {
            return AppContextUtil.getContext().getPackageManager().getApplicationInfo(AppContextUtil.getContext().getPackageName(), 128).metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static void randomReportSentry(Exception exc) {
        if (new Random(System.currentTimeMillis()).nextInt(100) <= 10) {
            SentryUtil.captureException(exc);
        }
    }
}
