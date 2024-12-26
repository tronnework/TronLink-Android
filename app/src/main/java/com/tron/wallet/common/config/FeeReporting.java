package com.tron.wallet.common.config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.utils.BigDecimalUtils;
import j$.util.Collection;
import j$.util.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
public class FeeReporting {
    private static Map<String, Double> feeMap = new HashMap();

    public static void addFee(BaseParam baseParam, final double d) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || baseParam == null || baseParam.getTransactionBean() == null || baseParam.getTransactionBean().getBytes() == null || baseParam.getTransactionBean().getBytes().size() <= 0) {
            return;
        }
        final List<byte[]> bytes = baseParam.getTransactionBean().getBytes();
        Collection.-EL.stream(bytes).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                FeeReporting.lambda$addFee$0(d, bytes, (byte[]) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void lambda$addFee$0(double d, List list, byte[] bArr) {
        try {
            feeMap.put(TransactionUtils.getHash(Protocol.Transaction.parseFrom(bArr)), Double.valueOf(BigDecimalUtils.div_(Double.valueOf(d), Integer.valueOf(list.size()), 6).doubleValue()));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static double getFee(String str) {
        Double d = feeMap.get(str);
        return d == null ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : d.doubleValue();
    }

    public static void removeItem(String str) {
        if (feeMap.containsKey(str)) {
            feeMap.remove(str);
        }
    }
}
