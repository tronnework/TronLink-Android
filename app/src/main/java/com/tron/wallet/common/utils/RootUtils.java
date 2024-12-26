package com.tron.wallet.common.utils;

import android.content.Context;
import android.provider.Settings;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
public class RootUtils {
    public static boolean isRootSystem() {
        return isRootSystem1() || isRootSystem2();
    }

    private static boolean isRootSystem1() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                File file = new File(strArr[i] + "su");
                if (file.exists() && file.canExecute()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static boolean isRootSystem2() {
        List<String> path = getPath();
        for (int i = 0; i < path.size(); i++) {
            try {
                File file = new File(path.get(i), "su");
                PrintStream printStream = System.out;
                printStream.println("f.getAbsolutePath():" + file.getAbsolutePath());
                if (file.exists() && file.canExecute()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static List<String> getPath() {
        return Arrays.asList(System.getenv("PATH").split(":"));
    }

    public static boolean IsAirModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1;
    }
}
