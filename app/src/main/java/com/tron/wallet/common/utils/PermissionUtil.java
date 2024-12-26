package com.tron.wallet.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
public class PermissionUtil {
    public static boolean hasPermission(Context context, String str) {
        int checkSelfPermission;
        if (Build.VERSION.SDK_INT >= 23) {
            checkSelfPermission = context.checkSelfPermission(str);
            return checkSelfPermission == 0;
        }
        return true;
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            activity.requestPermissions(strArr, i);
        }
    }

    public static String[] getDeniedPermissions(Context context, String[] strArr) {
        int checkSelfPermission;
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                checkSelfPermission = context.checkSelfPermission(str);
                if (checkSelfPermission != 0) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() > 0) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            return null;
        }
        return null;
    }
}
