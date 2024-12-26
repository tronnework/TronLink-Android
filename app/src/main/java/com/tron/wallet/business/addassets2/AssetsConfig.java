package com.tron.wallet.business.addassets2;

import com.google.gson.Gson;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import java.math.BigDecimal;
import org.tron.walletserver.Wallet;
public class AssetsConfig {
    public static final String ACTION = "actionType";
    public static final int ACTION_CUSTOM_TOKEN_ADDED = 1;
    public static final String DATA_TYPE = "dataType";
    public static final int DATA_TYPE_ASSETS = 0;
    public static final int DATA_TYPE_COLLECTIONS = 1;
    public static final String SORT_TYPE = "sortType";
    public static final String TAG = "AssetsConfig";
    public static final String TOKEN_BEAN = "tokenBean";
    public static final String TOKEN_SYMBOL = "tokenSymbol";
    public static final String TOKEN_TYPE = "tokenType";
    private static double assetThousandthLimit = 1.0d;
    private static double assetValueLimit = 1.0d;
    private static DappJsOutput.DataBean.DappScore dappScore = new DappJsOutput.DataBean.DappScore();
    private static boolean initialized = false;
    private static boolean isMainChain = false;
    private static long nftCountLimit = 1;

    public static boolean canDisplayCollections() {
        return isMainChain;
    }

    public static double getAssetThousandthLimit() {
        return assetThousandthLimit;
    }

    public static double getAssetValueLimit() {
        return assetValueLimit;
    }

    public static DappJsOutput.DataBean.DappScore getDappScore() {
        return dappScore;
    }

    public static long getNftCountLimit() {
        return nftCountLimit;
    }

    public static boolean isIsMainChain() {
        return isMainChain;
    }

    public static void setIsMainChain(boolean z) {
        isMainChain = z;
    }

    public static void initConfig() {
        try {
            String dappJSOutput = SpAPI.THIS.getDappJSOutput();
            LogUtils.d(TAG, dappJSOutput);
            if (dappJSOutput != null && dappJSOutput.length() > 0) {
                initConfig((DappJsOutput) new Gson().fromJson(dappJSOutput,  DappJsOutput.class));
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
        initialized = true;
    }

    public static void initConfig(DappJsOutput dappJsOutput) {
        if (dappJsOutput != null) {
            try {
                if (dappJsOutput.getData() != null && dappJsOutput.getData().getBalanceLimit() != null) {
                    DappJsOutput.DataBean.BalanceLimit balanceLimit = dappJsOutput.getData().getBalanceLimit();
                    if (assetValueLimit != balanceLimit.getAssetValueLimit() || assetThousandthLimit != balanceLimit.getAssetThousandthLimit() || nftCountLimit != balanceLimit.getNftCountLimit()) {
                        assetValueLimit = balanceLimit.getAssetValueLimit();
                        assetThousandthLimit = balanceLimit.getAssetThousandthLimit();
                        nftCountLimit = balanceLimit.getNftCountLimit();
                        if (initialized) {
                            RxBus.getInstance().post(RB.RB_HOMEASSET_DB, true);
                        }
                    }
                    LogUtils.d(TAG, "parsed limit: assetValueLimit: " + assetValueLimit + " assetThousandthLimit: " + assetThousandthLimit + " nftCountLimit: " + nftCountLimit);
                }
            } catch (Throwable th) {
                SentryUtil.captureException(th);
            }
        }
        if (dappJsOutput != null) {
            try {
                if (dappJsOutput.getData() != null && dappJsOutput.getData().getDappScore() != null) {
                    dappScore = dappJsOutput.getData().getDappScore();
                }
            } catch (Throwable th2) {
                SentryUtil.captureException(th2);
                return;
            }
        }
        dappScore = new DappJsOutput.DataBean.DappScore();
    }

    public static boolean isSmallAsset(TokenBean tokenBean) {
        if (tokenBean != null) {
            if (tokenBean.getType() == 5) {
                return BigDecimalUtils.LessThan(Double.valueOf(tokenBean.getBalance()), Long.valueOf(nftCountLimit));
            }
            if (BigDecimalUtils.lessThanOrEqual(Double.valueOf(tokenBean.getPrice()), 0)) {
                return BigDecimalUtils.LessThan(Double.valueOf(BigDecimalUtils.div(tokenBean.getBalanceStr(), tokenBean.getTotalSupply())), Double.valueOf(assetThousandthLimit / 1000.0d));
            }
            if (BigDecimalUtils.LessThan(Double.valueOf(SpAPI.THIS.getUsdPrice() * tokenBean.getTrxCount()), Double.valueOf(assetValueLimit))) {
                return true;
            }
        }
        return false;
    }

    public static BigDecimal getTokenTotalPriceNumber(TokenBean tokenBean, double d) {
        if (tokenBean != null) {
            try {
                boolean isUsdPrice = SpAPI.THIS.isUsdPrice();
                if (!isUsdPrice || StringTronUtil.isEmpty(tokenBean.getUsdPrice())) {
                    if (!isUsdPrice && !StringTronUtil.isEmpty(tokenBean.getCnyPrice())) {
                        return BigDecimalUtils.mul_(tokenBean.getCnyPrice(), tokenBean.getBalanceBigDecimal());
                    }
                    return BigDecimalUtils.mul_(Double.valueOf(tokenBean.getTrxCount()), Double.valueOf(d));
                }
                return BigDecimalUtils.mul_(tokenBean.getUsdPrice(), tokenBean.getBalanceBigDecimal());
            } catch (Throwable th) {
                LogUtils.e(th);
            }
        }
        return BigDecimal.ZERO;
    }

    public static String getTokenTotalPrice(TokenBean tokenBean, double d) {
        return StringTronUtil.formatPrice3(getTokenTotalPriceNumber(tokenBean, d));
    }

    public static <T> int getTokenDefaultLogoIconId(T r4) {
        


return 1;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.addassets2.AssetsConfig.getTokenDefaultLogoIconId(java.lang.Object):int");
    }

    public static boolean isCustomTokenAndNotSupportTransfer(TokenBean tokenBean) {
        return (tokenBean == null || tokenBean.getTokenStatus() == 0 || tokenBean.isTransferStatus()) ? false : true;
    }

    public static boolean isCurrentWalletWatch() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        return (selectedPublicWallet == null || !selectedPublicWallet.isWatchOnly() || LedgerWallet.isLedger(selectedPublicWallet)) ? false : true;
    }
}
