package com.tron.wallet.business.walletmanager.common;

import com.tron.wallet.business.tabmy.proposals.ChangeAddressActivity;
public class CommonKey {
    public static final String IS_FIRST_IMPORT = "is_first_import";
    public static final String IS_ONLY_LOCAL_HDWALLET = "key_is_only_local_hdwallet";
    public static final String KEY_DATA = "key_data";
    public static final String KEY_DATA_ARRAY = "key_data_array";
    public static final String KEY_MNEMONIC = "key_mnemonic";
    public static final String KEY_SELECTED = "key_checked";
    public static final String KEY_WALLET = "key_wallet";
    public static final String KEY_WALLET_NAME = "key_wallet_name";
    public static final String KEY_WALLET_PASSWORD = "key_wallet_password";
    public static final int REQUEST_CODE_CHANGE_ADDRESS;
    public static final String REQUEST_CODE_SET_CUSTOM_PATH = "key_set_custom_path";
    public static final int RESULT_CODE_CHANGE_ADDRESS;

    static {
        int hashCode = ChangeAddressActivity.class.hashCode() & 255;
        REQUEST_CODE_CHANGE_ADDRESS = hashCode;
        RESULT_CODE_CHANGE_ADDRESS = hashCode << 1;
    }
}
