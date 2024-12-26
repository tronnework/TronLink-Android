package org.tron.common.utils;
public class Assertions {
    public static void verifyPrecondition(boolean z, String str) {
        if (!z) {
            throw new RuntimeException(str);
        }
    }
}
