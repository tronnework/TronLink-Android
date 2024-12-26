package com.tron.wallet.business.create.creatwallet;
public interface AddWalletType {
    public static final int ACTION_CREATE = 10;
    public static final int ACTION_IMPORT = 11;
    public static final String INTENT_KEY_ACTION = "wallet_action";
    public static final String INTENT_KEY_IS_NILE = "isNile";
    public static final String INTENT_KEY_SHIELD = "is_wallet_shield";
    public static final String INTENT_KEY_WALLET_TYPE = "wallet_shield_type";
    public static final String KEY_DATA_ADDRESS = "address";
    public static final String KEY_DATA_AK = "ak";
    public static final String KEY_DATA_NSK = "nsk";
    public static final String KEY_DATA_OVK = "ovk";
    public static final int TYPE_GENERAL = 0;
    public static final int TYPE_SHIELD = 1;
}
