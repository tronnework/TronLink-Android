package org.tron.common.utils;

import java.security.SecureRandom;
import org.tron.common.crypto.LinuxSecureRandom;
public class Utils {
    private static int isAndroid;
    private static SecureRandom random;

    public static SecureRandom getRandom() {
        return random;
    }

    static {
        if (isAndroidRuntime()) {
            new LinuxSecureRandom();
        }
        random = new SecureRandom();
        isAndroid = -1;
    }

    static boolean isAndroidRuntime() {
        if (isAndroid == -1) {
            String property = System.getProperty("java.runtime.name");
            isAndroid = (property == null || !property.equals("Android Runtime")) ? 0 : 1;
        }
        return isAndroid == 1;
    }
}
