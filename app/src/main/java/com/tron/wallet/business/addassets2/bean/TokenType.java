package com.tron.wallet.business.addassets2.bean;
public class TokenType {
    public static final int IEO = 3;
    public static final int TOKEN10 = 1;
    public static final int TOKEN20 = 2;
    public static final int TOKEN721 = 5;
    public static final int TRX = 0;
    public static final int TRZ = 4;

    public static class DefiType {
        public static final int DEFAULT = 0;
        public static final int JTOKEN = 1;
        public static final int LPTOKEN = 2;
    }

    public static int toAssetDataType(int i) {
        return i == 5 ? 1 : 0;
    }
}
