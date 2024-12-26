package org.tron.common.bip32;
public class Assertions {
    public static void verifyPrecondition(boolean z, String str) {
        if (!z) {
            throw new RuntimeException(str);
        }
    }
}
