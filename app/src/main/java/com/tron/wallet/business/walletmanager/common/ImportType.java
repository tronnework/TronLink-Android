package com.tron.wallet.business.walletmanager.common;
public class ImportType {
    public static final int FROM_LEDGER = 1;
    public static final int FROM_MNEMONIC = 0;
    public static final String KEY_FROM = "from";
    public static final String KEY_LEDGER_NAME = "key_ledger_name";
    public static final String KEY_SHOW_STEP = "key_show_step";

    public static int parseFromTronConfig(int i) {
        return i == 8 ? 1 : 0;
    }
}
