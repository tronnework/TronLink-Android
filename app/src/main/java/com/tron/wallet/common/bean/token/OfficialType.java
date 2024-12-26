package com.tron.wallet.common.bean.token;
public class OfficialType {
    public static final int NORMAL = 1;
    public static final int SCAM = -5;
    public static final int UNSAFE = -4;

    public static boolean isScamOrUnSafeToken(TokenBean tokenBean) {
        if (tokenBean == null) {
            return false;
        }
        return tokenBean.isOfficial == -5 || tokenBean.isOfficial == -4;
    }

    public static boolean isAtRiskToken(TokenBean tokenBean) {
        return tokenBean != null && tokenBean.isOfficial == -5;
    }

    public static boolean isUnSafeToken(TokenBean tokenBean) {
        return tokenBean != null && tokenBean.isOfficial == -4;
    }
}
