package com.tron.wallet.common.utils;

import org.tron.walletserver.Wallet;
public class WalletTypeUtils {
    public static boolean isOnlyWatch(Wallet wallet) {
        return (wallet == null || !wallet.isWatchOnly() || wallet.isWatchCold() || wallet.isSamsungWallet() || wallet.isLedgerHDWallet()) ? false : true;
    }
}
