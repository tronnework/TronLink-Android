package com.tron.wallet.business.transfer.selecttoken;
public class BandWidthHelper {
    private static final int BANDWIDTH_MAX_ADD_NUM = 100;
    private static final int BANDWIDTH_MULTISIGN_NUM = 337;
    private static final int BANDWIDTH_MULTISIGN_TRC20_NUM = 64;
    private static final int BANDWIDTH_NORMAL_NUM = 265;
    public static String TRX_FEE_ACTIVE = "trx_fee_active";
    public static String TRX_FEE_BANDWIDTH = "trx_fee_bandwidth";
    public static String TRX_FEE_MEMO = "trx_fee_memo";
    public static String TRX_FEE_MULTISIGN = "trx_fee_multisign";
    private static boolean insufficientBandwidth;

    public static boolean isInsufficientBandwidth() {
        return insufficientBandwidth;
    }

    public static double checkBandwidth(org.tron.api.GrpcAPI.AccountResourceMessage r20, com.tron.wallet.business.confirm.parambuilder.bean.TransferParam r21, com.tron.wallet.common.bean.token.TokenBean r22, java.lang.String r23, java.lang.String r24, boolean r25, java.util.Map<java.lang.String, java.lang.String> r26) {
        


return 1;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.transfer.selecttoken.BandWidthHelper.checkBandwidth(org.tron.api.GrpcAPI$AccountResourceMessage, com.tron.wallet.business.confirm.parambuilder.bean.TransferParam, com.tron.wallet.common.bean.token.TokenBean, java.lang.String, java.lang.String, boolean, java.util.Map):double");
    }
}
