package com.tron.wallet.business.walletmanager.selectwallet.bean;
public enum WalletSortType {
    SORT_BY_NONE(0),
    SORT_BY_TYPE(1),
    SORT_BY_VALUE_DEFAULT(2),
    SORT_BY_VALUE_ALL(3);
    
    private int value;

    public static WalletSortType getTypeByValue(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? SORT_BY_TYPE : SORT_BY_VALUE_ALL : SORT_BY_VALUE_DEFAULT : SORT_BY_TYPE : SORT_BY_NONE;
    }

    public int getValue() {
        return this.value;
    }

    WalletSortType(int i) {
        this.value = i;
    }
}
