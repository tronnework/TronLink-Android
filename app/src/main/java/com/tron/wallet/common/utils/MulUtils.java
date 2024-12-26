package com.tron.wallet.common.utils;

import java.math.BigDecimal;
public class MulUtils {
    public static long mul(double d, double d2) {
        return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).longValue();
    }
}
