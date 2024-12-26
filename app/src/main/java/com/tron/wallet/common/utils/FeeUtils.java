package com.tron.wallet.common.utils;

import android.content.Context;
import android.util.Pair;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.LogUtils;
import j$.util.Collection;
import j$.util.Objects;
import java.util.List;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class FeeUtils {
    public static Pair<Long, Long> getDeductedBandwidth(Context context, Protocol.Transaction transaction) {
        long bandwidthCost = BandwidthUtils.getBandwidthCost(transaction);
        if (BandwidthUtils.isEnoughBandwidth(context, bandwidthCost)) {
            return new Pair<>(Long.valueOf(bandwidthCost), 0L);
        }
        return new Pair<>(0L, Long.valueOf(bandwidthCost));
    }

    public static Pair<Long, Long> getDeductedBandwidth(Context context, List<Protocol.Transaction> list) {
        final long[] jArr = new long[1];
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                FeeUtils.lambda$getDeductedBandwidth$0(jArr, (Protocol.Transaction) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        if (BandwidthUtils.isEnoughBandwidth(context, jArr[0])) {
            return new Pair<>(Long.valueOf(jArr[0]), 0L);
        }
        return new Pair<>(0L, Long.valueOf(jArr[0]));
    }

    public static void lambda$getDeductedBandwidth$0(long[] jArr, Protocol.Transaction transaction) {
        jArr[0] = jArr[0] + BandwidthUtils.getBandwidthCost(transaction);
    }

    public static Pair<Long, Long> getDeductedBandwidthForMultiSign(Protocol.Transaction transaction, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        long bandwidthCost = BandwidthUtils.getBandwidthCost(transaction);
        long netLimit = accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed();
        long freeNetLimit = accountResourceMessage.getFreeNetLimit() - accountResourceMessage.getFreeNetUsed();
        if (netLimit >= bandwidthCost || freeNetLimit >= bandwidthCost) {
            return new Pair<>(Long.valueOf(bandwidthCost), 0L);
        }
        return new Pair<>(0L, Long.valueOf(bandwidthCost));
    }

    public static Pair<Long, Long> getDeductedBandwidthForMultiSign(GrpcAPI.AccountResourceMessage accountResourceMessage, List<Protocol.Transaction> list) {
        final long[] jArr = new long[1];
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                FeeUtils.lambda$getDeductedBandwidthForMultiSign$1(jArr, (Protocol.Transaction) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        long netLimit = accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed();
        long freeNetLimit = accountResourceMessage.getFreeNetLimit() - accountResourceMessage.getFreeNetUsed();
        long j = jArr[0];
        if (netLimit >= j || freeNetLimit >= j) {
            return new Pair<>(Long.valueOf(jArr[0]), 0L);
        }
        return new Pair<>(0L, Long.valueOf(jArr[0]));
    }

    public static void lambda$getDeductedBandwidthForMultiSign$1(long[] jArr, Protocol.Transaction transaction) {
        jArr[0] = jArr[0] + BandwidthUtils.getBandwidthCost(transaction);
    }

    public static Pair<Long, Long> getDeductedEnergy(long j, long j2) {
        if (j < 0) {
            j = 0;
        }
        if (j2 < 0) {
            j2 = 0;
        }
        if (BigDecimalUtils.lessThanOrEqual(Long.valueOf(j), Long.valueOf(j2))) {
            return new Pair<>(Long.valueOf(j), 0L);
        }
        return new Pair<>(Long.valueOf(j2), Long.valueOf(j - j2));
    }

    public static String formatNumberSize(long j) {
        return NumUtils.amountConversion(j);
    }

    public static double getFeeLimit(List<Protocol.Transaction> list) {
        double pow = Math.pow(10.0d, 6.0d) * 150.0d;
        if (list == null || list.isEmpty()) {
            return pow;
        }
        final double[] dArr = {FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE};
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                FeeUtils.lambda$getFeeLimit$2(dArr, (Protocol.Transaction) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        if (dArr[0] == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            dArr[0] = pow;
        }
        return dArr[0];
    }

    public static void lambda$getFeeLimit$2(double[] dArr, Protocol.Transaction transaction) {
        try {
            dArr[0] = dArr[0] + transaction.getRawData().getFeeLimit();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
