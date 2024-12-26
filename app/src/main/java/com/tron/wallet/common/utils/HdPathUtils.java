package com.tron.wallet.common.utils;

import com.tron.wallet.db.wallet.WalletUtils;
import org.tron.walletserver.Wallet;
public class HdPathUtils {
    public static final String HD_PATH_DEFAULT = "m/44'/195'/%1$d'/0/0";

    public static String getHdPath(String str) {
        if (WalletUtils.getWalletNames().size() > 0) {
            for (String str2 : WalletUtils.getWalletNames()) {
                Wallet wallet = WalletUtils.getWallet(str2);
                if (wallet != null && wallet.getAddress() != null) {
                    str.equals(wallet.getSeedHash());
                }
            }
        }
        return String.format(HD_PATH_DEFAULT, 0);
    }
}
