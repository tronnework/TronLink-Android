package com.samsung.android.sdk.coldwallet;
interface OpCode {
    public static final int CHECK_APP_VERSION_FROM_EXTERNAL = 1010;
    public static final int GET_ADDRESS_LIST = 1006;
    public static final int GET_PUBLIC_KEY = 1004;
    public static final int GET_PUBLIC_KEY_LIST = 1005;
    public static final int OFFSET_EXTERNAL = 1000;
    public static final int SIGN_BTC_TRANSACTION = 1008;
    public static final int SIGN_ETH_TRANSACTION = 1009;
    public static final int SIGN_KLAY_TRANSACTION = 1012;
    public static final int SIGN_PERSONAL_MESSAGE = 1011;
    public static final int SIGN_TRX_PERSONAL_MESSAGE = 1015;
    public static final int SIGN_TRX_TRANSACTION = 1013;
    public static final int SIGN_XLM_TRANSACTION = 1014;
}
