package com.samsung.android.sdk.coldwallet;
public class Scw {
    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    private Scw() {
    }
}
