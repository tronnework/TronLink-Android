package com.tron.wallet.common.utils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.vote.bean.FastAprBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class VoteAprCalculator {
    public static FastAprBean calculateApr(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        List<WitnessOutput.DataBean> list3 = list2;
        final boolean[] zArr = {false};
        final BigDecimal[] bigDecimalArr = {new BigDecimal(0)};
        final BigDecimal[] bigDecimalArr2 = {new BigDecimal(0)};
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                VoteAprCalculator.lambda$calculateApr$0(bigDecimalArr, bigDecimalArr2, zArr, (WitnessOutput.DataBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        double doubleValue = BigDecimalUtils.div_(bigDecimalArr[0], bigDecimalArr2[0]).doubleValue();
        if (!zArr[0] && !list3.isEmpty()) {
            return new FastAprBean(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, list3.get(0).getAnnualized_income(), false, list3);
        }
        final double[] dArr = {FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE};
        Collection.-EL.stream(list3).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                VoteAprCalculator.lambda$calculateApr$1(dArr, (WitnessOutput.DataBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return new FastAprBean(doubleValue, dArr[0] / 3.0d, true, list3);
    }

    public static void lambda$calculateApr$0(BigDecimal[] bigDecimalArr, BigDecimal[] bigDecimalArr2, boolean[] zArr, WitnessOutput.DataBean dataBean) {
        bigDecimalArr[0] = BigDecimalUtils.sum_(bigDecimalArr[0], BigDecimalUtils.mul_(dataBean.getVoted(), Double.valueOf(dataBean.getAnnualized_income())));
        bigDecimalArr2[0] = BigDecimalUtils.sum_(dataBean.getVoted(), bigDecimalArr2[0]);
        zArr[0] = zArr[0] | BigDecimalUtils.MoreThan((Object) dataBean.getVoted(), (Object) 0);
    }

    public static void lambda$calculateApr$1(double[] dArr, WitnessOutput.DataBean dataBean) {
        try {
            dArr[0] = dArr[0] + dataBean.getAnnualized_income();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static String calculateApr(List<VoteWitnessBean> list, long j) {
        BigDecimal bigDecimal = new BigDecimal(0);
        for (VoteWitnessBean voteWitnessBean : list) {
            if (!voteWitnessBean.isTitle()) {
                bigDecimal = BigDecimalUtils.sum_(bigDecimal, BigDecimalUtils.mul_(voteWitnessBean.getVoteCount(), Double.valueOf(voteWitnessBean.getDataBean().getAnnualized_income())));
            }
        }
        double div = BigDecimalUtils.div(bigDecimal, Long.valueOf(j));
        return "" + div;
    }

    public static String formatAprPercent(double d) {
        return StringTronUtil.formatNumberTruncateNoDots(BigDecimalUtils.toBigDecimal(Double.valueOf(d)), 2);
    }
}
