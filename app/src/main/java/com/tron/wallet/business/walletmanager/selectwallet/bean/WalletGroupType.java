package com.tron.wallet.business.walletmanager.selectwallet.bean;

import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.LedgerWallet;
import org.tron.walletserver.Wallet;
public enum WalletGroupType {
    RECENTLY(0),
    HEAT(1),
    COLD_HARD(2),
    WATCH_COLD(3),
    WATCH(4),
    SHIELD(5),
    ALL(6);
    
    private int value;

    public int getValue() {
        return this.value;
    }

    WalletGroupType(int i) {
        this.value = i;
    }

    public static WalletGroupType getGroupType(Wallet wallet) {
        if (SpAPI.THIS.isCold()) {
            return COLD_HARD;
        }
        if (wallet != null) {
            if (!wallet.isSamsungWallet() && !LedgerWallet.isLedger(wallet)) {
                return wallet.isWatchCold() ? WATCH_COLD : wallet.isWatchOnly() ? WATCH : HEAT;
            }
            return COLD_HARD;
        }
        return HEAT;
    }
}
