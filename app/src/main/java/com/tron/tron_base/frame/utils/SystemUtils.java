package com.tron.tron_base.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
public class SystemUtils {
    public static long getTotalInternalSpace() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Exception e) {
            LogUtils.e(e);
            return -1L;
        }
    }

    public static long getAvailableInternalMemorySize() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception e) {
            LogUtils.e(e);
            return -1L;
        }
    }

    public static void hideSoftKeyBoard(Activity activity) {
        View peekDecorView;
        if (activity == null || activity.getWindow() == null || (peekDecorView = activity.getWindow().peekDecorView()) == null || peekDecorView.getWindowToken() == null) {
            return;
        }
        try {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
            return 1;
        }
    }
}
