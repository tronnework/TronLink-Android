package com.tron.wallet.common.utils;

import android.content.Context;
public class HookUtils {
    private static boolean findHookAppName(android.content.Context r3) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.HookUtils.findHookAppName(android.content.Context):boolean");
    }

    private static boolean findHookAppFile() {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.HookUtils.findHookAppFile():boolean");
    }

    private static boolean findHookStack() {
        StackTraceElement[] stackTrace;
        try {
            throw new Exception("findhook");
        } catch (Exception e) {
            int i = 0;
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i = i + 1) == 2) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    return true;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isHook(Context context) {
        return findHookAppName(context) || findHookAppFile() || findHookStack();
    }
}
