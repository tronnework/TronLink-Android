package com.tron.wallet.business.walletmanager.adapter;

import org.tron.walletserver.Wallet;
public class ChooseAddressActionProvider {
    public static LoadAddressAction<? extends Wallet> provide(int i) {
        if (1 == i) {
            return new LedgerLoadAddressAction();
        }
        return new MnemonicLoadAddressAction();
    }
}
