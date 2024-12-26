package com.tron.wallet.business.addassets2.bean;
public enum TokenSortType {
    SORT_BY_USER(0),
    SORT_BY_VALUE(1),
    SORT_BY_NAME(2),
    SORT_BY_USER_MANUAL(3);
    
    private int value;

    public static TokenSortType getTypeByValue(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2 && i == 3) {
                    return SORT_BY_USER_MANUAL;
                }
                return SORT_BY_NAME;
            }
            return SORT_BY_VALUE;
        }
        return SORT_BY_USER;
    }

    public int getValue() {
        return this.value;
    }

    TokenSortType(int i) {
        this.value = i;
    }
}
