package com.tron.wallet.common.config;

import com.google.gson.Gson;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.SpAPI;
import io.reactivex.functions.Consumer;
import org.tron.config.Parameter;
public class AccountFeeManager {
    private static final int DEFAULT_FREE_BANDWIDTH = 1500;
    private static final int DEFAULT_FREE_BANDWIDTH_DAPP = 5000;
    private static final int DEFAULT_FREE_BANDWIDTH_SHASTA = 5000;
    private static final double DappChainDefaultFeeBandWidth = 1.0E-5d;
    private static final double DeFaultBaseActiveFee = 0.1d;
    private static final double DeFaultDappBaseActiveFee = 0.1d;
    private static final double DeFaultDappExternActiveFee = 0.0d;
    private static final double DeFaultExternActiveFee = 1.0d;
    private static double DefaultFeeBandWidth = 0.001d;
    private static final double OLD_DefaultFeeBandWidth = 1.4E-4d;
    private static double baseActiveFee = 0.1d;
    private static double externActiviteFee = 1.0d;
    private static double feeBandWidth = 0.001d;
    private static double memoFee = 0.0d;
    private static int triggerType = 1;

    public static int getTriggerType() {
        return triggerType;
    }

    public static void initAccountFee() {
        new RxManager().on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                AccountFeeManager.init();
            }
        });
        init();
    }

    public static void init() {
        try {
            String dappJSOutput = SpAPI.THIS.getDappJSOutput();
            if (dappJSOutput != null && dappJSOutput.length() > 0) {
                initAccountFee((DappJsOutput) new Gson().fromJson(dappJSOutput,  DappJsOutput.class));
            } else {
                initAccountFee(null);
            }
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
    }

    public static void initAccountFee(DappJsOutput dappJsOutput) {
        if (dappJsOutput != null) {
            try {
                if (dappJsOutput.getData() != null && dappJsOutput.getData().getAccountActiveFeeParams() != null) {
                    DappJsOutput.DataBean.AccountActiveFeeParams accountActiveFeeParams = dappJsOutput.getData().getAccountActiveFeeParams();
                    if (accountActiveFeeParams != null) {
                        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                            TronConfig.feeBandWidth = DefaultFeeBandWidth;
                            baseActiveFee = 0.1d;
                            externActiviteFee = DeFaultExternActiveFee;
                            TronConfig.bandwidthForFree = 5000;
                        } else {
                            if (!SpAPI.THIS.getCurIsMainChain()) {
                                baseActiveFee = Double.parseDouble(accountActiveFeeParams.getDappBaseActiveFee());
                                externActiviteFee = Double.parseDouble(accountActiveFeeParams.getDappExtraActiveFee());
                                feeBandWidth = Double.parseDouble(accountActiveFeeParams.getDappFeeBandwidth());
                                TronConfig.bandwidthForFree = 5000;
                                TronConfig.feeEnergy = Double.parseDouble(accountActiveFeeParams.getFeeEnergy());
                            } else {
                                baseActiveFee = Double.parseDouble(accountActiveFeeParams.getBaseActiveFee());
                                externActiviteFee = Double.parseDouble(accountActiveFeeParams.getExtraActiveFee());
                                feeBandWidth = Double.parseDouble(accountActiveFeeParams.getFeeBandwidth());
                                TronConfig.bandwidthForFree = Integer.parseInt(accountActiveFeeParams.getFreeNetLimit());
                                TronConfig.feeEnergy = Double.parseDouble(accountActiveFeeParams.getFeeEnergy());
                            }
                            triggerType = accountActiveFeeParams.getTriggerType();
                            Parameter.NetConstant.triggerType = accountActiveFeeParams.getTriggerType();
                            TronConfig.feeBandWidth = feeBandWidth;
                        }
                        memoFee = Double.parseDouble(accountActiveFeeParams.getMemoFee());
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                SentryUtil.captureException(th);
                return;
            }
        }
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            baseActiveFee = 0.1d;
            externActiviteFee = DeFaultExternActiveFee;
            double d = DefaultFeeBandWidth;
            feeBandWidth = d;
            TronConfig.feeBandWidth = d;
            TronConfig.bandwidthForFree = 5000;
        } else if (!SpAPI.THIS.getCurIsMainChain()) {
            baseActiveFee = 0.1d;
            externActiviteFee = 0.0d;
            feeBandWidth = 1.0E-5d;
            TronConfig.feeBandWidth = 1.0E-5d;
            TronConfig.bandwidthForFree = 5000;
        } else {
            baseActiveFee = 0.1d;
            externActiviteFee = DeFaultExternActiveFee;
            double d2 = DefaultFeeBandWidth;
            feeBandWidth = d2;
            TronConfig.feeBandWidth = d2;
            TronConfig.bandwidthForFree = 1500;
        }
    }

    public static double getBaseActiveFee() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            return 0.1d;
        }
        return baseActiveFee;
    }

    public static double getExternActiviteFee() {
        return IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA ? DeFaultExternActiveFee : externActiviteFee;
    }

    public static double getMemoFee() {
        if (SpAPI.THIS.getCurIsMainChain()) {
            return memoFee;
        }
        return 0.0d;
    }
}
