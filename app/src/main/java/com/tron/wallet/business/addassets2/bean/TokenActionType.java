package com.tron.wallet.business.addassets2.bean;
public class TokenActionType {
    public static final int APPROVE20 = 1;
    public static final int APPROVE721 = 3;
    public static final int APPROVE_PROBABLY = 990;
    public static final int INCREASE_APPROVE = 4;
    public static final int INCREASE_APPROVE_PROBABLY = 993;
    public static final int NONE = -1;
    public static final int TRANSFER20 = 0;
    public static final int TRANSFER721 = 2;
    public static final int TRANSFER_FROM_PROBABLY = 992;
    public static final int TRANSFER_PROBABLY = 991;

    public static boolean identifyNONE(int i) {
        return i == -1 || i == 990 || i == 991 || i == 992;
    }
}
