package org.tron.common.utils;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;
public class RomUtils {
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";

    private static boolean isAndroidMOrAbove() {
        return Build.VERSION.SDK_INT >= 23;
    }

    class AvailableRomType {
        public static final int ANDROID_NATIVE = 3;
        public static final int FLYME = 2;
        public static final int MIUI = 1;
        public static final int NA = 4;

        AvailableRomType() {
        }
    }

    public static int getLightStatusBarAvailableRomType() {
        if (isMiUIV7OrAbove()) {
            return 3;
        }
        if (isMiUIV6OrAbove()) {
            return 1;
        }
        if (isFlymeV4OrAbove()) {
            return 2;
        }
        return isAndroidMOrAbove() ? 3 : 4;
    }

    private static boolean isFlymeV4OrAbove() {
        String str = Build.DISPLAY;
        if (!TextUtils.isEmpty(str) && str.contains("Flyme")) {
            for (String str2 : str.split(" ")) {
                if (str2.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isMiUIV6OrAbove() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty(KEY_MIUI_VERSION_CODE, null);
            if (property != null) {
                return Integer.parseInt(property) >= 4;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    static boolean isMiUIV7OrAbove() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty(KEY_MIUI_VERSION_CODE, null);
            if (property != null) {
                return Integer.parseInt(property) >= 5;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            int intValue = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue();
            bufferedReader.close();
            return (intValue / 1024) / 1024;
        } catch (Exception unused) {
            return 6;
        }
    }
}
