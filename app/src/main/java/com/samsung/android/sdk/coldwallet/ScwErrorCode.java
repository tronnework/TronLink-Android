package com.samsung.android.sdk.coldwallet;
public interface ScwErrorCode {
    public static final int ERROR_CHECK_APP_VERSION_FAILED = -15;
    public static final int ERROR_CHECK_INTEGRITY_FAILED = -9;
    public static final int ERROR_CHECK_INTEGRITY_NOT_AVAILABLE = -10;
    public static final int ERROR_DEVELOPER_MODE_VERIFICATION_FAILED = -24;
    @Deprecated
    public static final int ERROR_EXCEED_NUMBER_OF_DEVICES = -24;
    public static final int ERROR_EXTERNAL_DISPLAY_NOT_ALLOWED = -18;
    public static final int ERROR_ILLEGAL_MSG = -3;
    public static final int ERROR_INIT_TA_FAILED = -17;
    public static final int ERROR_INSUFFICIENT_FUNDS = -30;
    public static final int ERROR_INVALID_HD_PATH = -25;
    public static final int ERROR_INVALID_INPUT_UTXO = -22;
    @Deprecated
    public static final int ERROR_INVALID_SCW_APP_ID = -11;
    public static final int ERROR_INVALID_TRANSACTION_FORMAT = -16;
    public static final int ERROR_LOAD_TA_FAILED = -4;
    public static final int ERROR_MANDATORY_APP_UPDATE_NEEDED = -8;
    public static final int ERROR_NETWORK_FAILED = -28;
    public static final int ERROR_NETWORK_NOT_AVAILABLE = -27;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOT_SUPPORTED_COUNTRY = -13;
    public static final int ERROR_OP_FAIL = -1;
    public static final int ERROR_OP_INTERRUPTED = -2;
    public static final int ERROR_OP_NOT_SUPPORTED = -19;
    public static final int ERROR_OP_TIMEOUT = -5;
    public static final int ERROR_OUT_OF_BOUND_VALUE = -20;
    public static final int ERROR_OUT_OF_OUTPUT_COUNT = -21;
    public static final int ERROR_PACKAGE_SIGNATURE_VERIFICATION_FAILED = -11;
    public static final int ERROR_SERVER_FAILED = -29;
    public static final int ERROR_TNC_NOT_AGREED = -6;
    public static final int ERROR_USER_AUTHENTICATION_FAILED = -7;
    public static final int ERROR_WALLET_NOT_CREATED = -26;
    public static final int ERROR_WALLET_RESET = -12;
}
