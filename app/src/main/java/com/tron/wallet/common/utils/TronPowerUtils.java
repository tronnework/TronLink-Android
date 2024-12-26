package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class TronPowerUtils {
    public static BigDecimal expectGetPower(long j, long j2, BigDecimal bigDecimal) {
        try {
            BigDecimal bigDecimal2 = new BigDecimal(j);
            BigDecimal bigDecimal3 = new BigDecimal(j2);
            if (bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
                return bigDecimal.multiply(bigDecimal3).divide(bigDecimal2, 4, RoundingMode.HALF_UP);
            }
            return new BigDecimal(0);
        } catch (Exception e) {
            LogUtils.e(e);
            return new BigDecimal(0);
        }
    }
}
