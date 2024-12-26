package com.tron.wallet.business.vote.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.wallet.business.vote.bean.WitnessOutput;
public class ProfitCalculator {
    private static double getPartnerProfit(long j, int i) {
        return j == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : (4608000.0d / j) * ((100 - i) / 100.0d) * 365.0d;
    }

    private static double getWitnessProfit(long j, long j2, int i) {
        if (j == 0 || j2 == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        double d = 4608000.0d / j;
        double d2 = (100 - i) / 100.0d;
        return ((d * d2) + ((d2 * 17066.0d) / j2)) * 365.0d;
    }

    public static double getWitnessProfit(long j, WitnessOutput.DataBean dataBean) {
        double partnerProfit;
        if (dataBean == null) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        if (dataBean.getAnnualized_income() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return dataBean.getAnnualized_income();
        }
        if (dataBean.getRealTimeRanking() <= 27) {
            partnerProfit = getWitnessProfit(j, dataBean.getRealTimeVotes(), dataBean.getBrokerage());
        } else if (dataBean.getRealTimeRanking() > 127) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        } else {
            partnerProfit = getPartnerProfit(j, dataBean.getBrokerage());
        }
        return partnerProfit * 100.0d;
    }

    public static double getVariableProfit(long j, long j2, long j3, int i, long j4) {
        if (j4 <= 27) {
            return getWitnessProfit(j + j3, j2 + j3, i);
        }
        return j4 <= 127 ? getPartnerProfit(j + j3, i) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }
}
