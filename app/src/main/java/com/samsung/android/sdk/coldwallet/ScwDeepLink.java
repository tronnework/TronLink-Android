package com.samsung.android.sdk.coldwallet;
public interface ScwDeepLink {
    public static final String BACKUP_WALLET = "coldwallet://launch?action=backup_wallet";
    @Deprecated
    public static final String CHANGE_PIN = "coldwallet://launch?action=change_pin";
    @Deprecated
    public static final String DISPLAY_WALLET = "coldwallet://launch?action=display_wallet";
    public static final String GALAXY_STORE = "samsungapps://ProductDetail/com.samsung.android.coldwalletservice";
    public static final String MAIN = "coldwallet://launch?action=main";
    public static final String NOTICE_CONTENT = "coldwallet://launch?action=notice&noticeId=%s";
    @Deprecated
    public static final String RESET = "coldwallet://launch?action=reset";
}
