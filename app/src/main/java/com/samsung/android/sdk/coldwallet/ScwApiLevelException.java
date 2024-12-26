package com.samsung.android.sdk.coldwallet;
public class ScwApiLevelException extends RuntimeException {
    public ScwApiLevelException() {
    }

    public ScwApiLevelException(String str) {
        super(str);
    }

    public ScwApiLevelException(int i, int i2) {
        super("Keystore is outdated. Need to update keystore to use this API\n- keystoreApiLevel " + i + "\n- requiredApiLevel : " + i2);
    }
}
