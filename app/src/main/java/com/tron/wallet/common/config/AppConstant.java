package com.tron.wallet.common.config;

import com.tron.wallet.common.utils.SdUtils;
public class AppConstant {
    public static final int BROWSER_BOOK_MARK_NUM = 11;
    public static final int DAPP_ROLL_DATA_DISPLAY_MAX_SIZE = 5;
    public static final int LIST_ITEM_COUNT_TO_SHOW_NO_MORE = 5;
    public static String APK_FILE_NAME = System.currentTimeMillis() + "tronlink.apk";
    public static String APK_FILE_PATH = SdUtils.getDownloadPath();
    public static String CHANNEL_VALUE_TEST = "tronTest";
    public static String CHANNEL_KEY = "CHANNEL";
    public static String BUGLY_RELEASE_ID = "7b9957a11f";
    public static String BUGLY_TEST_ID = "ec8cc927c1";
    public static String BUGLY_GLOBAL_RELEASE_ID = "0deb296421";
}
