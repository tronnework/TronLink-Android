package com.tron.tron_base.frame.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class StatusBarUtils {
    public static void transparencyBar(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(BrowserConstant.WEBVIEW_LINK_LONGPRESS);
    }

    public static void setStatusBarColor(Activity activity, int i) {
        Window window = activity.getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(activity.getResources().getColor(i));
    }

    public static void setLightStatusBar(Activity activity, boolean z) {
        int lightStatusBarAvailableRomType = RomUtils.getLightStatusBarAvailableRomType();
        if (lightStatusBarAvailableRomType == 1) {
            MIUISetStatusBarLightMode(activity, z);
        } else if (lightStatusBarAvailableRomType == 2) {
            setFlymeLightStatusBar(activity, z);
        } else if (lightStatusBarAvailableRomType != 3) {
        } else {
            setAndroidNativeLightStatusBar(activity, z);
        }
    }

    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                try {
                    if (Build.VERSION.SDK_INT >= 23 && RomUtils.isMiUIV7OrAbove()) {
                        if (z) {
                            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                        } else {
                            activity.getWindow().getDecorView().setSystemUiVisibility(BrowserConstant.WEBVIEW_LINK_LONGPRESS);
                        }
                    }
                } catch (Exception unused) {
                }
                return true;
            } catch (Exception unused2) {
                return false;
            }
        }
        return false;
    }

    private static boolean setFlymeLightStatusBar(Activity activity, boolean z) {
        if (activity != null) {
            try {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                activity.getWindow().setAttributes(attributes);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean z) {
        try {
            View decorView = activity.getWindow().getDecorView();
            if (z) {
                decorView.setSystemUiVisibility(9216);
            } else {
                decorView.setSystemUiVisibility(BrowserConstant.WEBVIEW_LINK_LONGPRESS);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
