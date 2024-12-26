package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.ThreadPoolManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
public class NumUtils {
    private static final String BILLION_UNIT = "B";
    private static final String MILLION_UNIT = "M";
    private static final String THOUSAND_UNIT = "K";
    private static final String TRILLION_UNIT = "T";
    public static NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
    private static final Double THOUSAND = Double.valueOf(1000.0d);
    private static final Double THOUSAND_START = Double.valueOf(9999.0d);
    private static final Double MILLIONS = Double.valueOf(1000000.0d);
    private static final Double MILLIONS_START = Double.valueOf(9999999.0d);
    private static final Double BILLION = Double.valueOf(1.0E9d);
    private static final Double BILLION_START = Double.valueOf(9.999999999E9d);
    private static final Double TRILLION_START = Double.valueOf(9.999999999999E12d);
    private static final Double TRILLION = Double.valueOf(1.0E12d);

    public static String numFormatToK(long j) {
        if (j <= 9999) {
            return numberInstance.format(j);
        }
        if (j <= 9999999) {
            return numberInstance.format(j / 1000) + "K";
        } else if (j <= 9999999999L) {
            return numberInstance.format(j / 1000000) + "M";
        } else {
            return numberInstance.format(j / 1000000000) + BILLION_UNIT;
        }
    }

    public static String amountConversion(double d) {
        return amountConversion(d, false, null);
    }

    public static String amountConversion(double d, boolean z, String str) {
        BigDecimal div;
        String.valueOf(d);
        if (BigDecimalUtils.moreThanOrEqual(Double.valueOf(d), THOUSAND_START) && BigDecimalUtils.lessThanOrEqual(Double.valueOf(d), MILLIONS)) {
            Double d2 = THOUSAND;
            double doubleValue = formatNumber(d / d2.doubleValue()).doubleValue();
            if (doubleValue == d2.doubleValue()) {
                return zeroFill(doubleValue / d2.doubleValue(), z) + "M";
            }
            return zeroFillBigDecimal(BigDecimalUtils.toBigDecimal(Double.valueOf(doubleValue)), z) + "K";
        } else if (d > MILLIONS_START.doubleValue() && d <= BILLION.doubleValue()) {
            Double d3 = MILLIONS;
            double doubleValue2 = formatNumber(d / d3.doubleValue()).doubleValue();
            if (doubleValue2 == d3.doubleValue()) {
                return zeroFill(doubleValue2 / d3.doubleValue(), z) + BILLION_UNIT;
            }
            return zeroFillBigDecimal(BigDecimalUtils.toBigDecimal(Double.valueOf(doubleValue2)), z) + "M";
        } else if (d > BILLION_START.doubleValue() && d <= TRILLION.doubleValue()) {
            double doubleValue3 = formatNumber(d / BILLION.doubleValue()).doubleValue();
            return zeroFillBigDecimal(BigDecimalUtils.toBigDecimal(Double.valueOf(doubleValue3)), z) + BILLION_UNIT;
        } else if (d > TRILLION_START.doubleValue()) {
            if (!StringTronUtil.isEmpty(str)) {
                div = BigDecimalUtils.div(new BigDecimal(str), new BigDecimal(TRILLION.doubleValue()));
            } else {
                div = BigDecimalUtils.div(new BigDecimal(d), new BigDecimal(TRILLION.doubleValue()));
            }
            return zeroFillBigDecimal(div, z) + "T";
        } else {
            return zeroFill(d, false);
        }
    }

    private static String zeroFillBigDecimal(BigDecimal bigDecimal, boolean z) {
        if (z) {
            String[] split = bigDecimal.toPlainString().split("\\.");
            if (split.length > 1) {
                return StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(split[0])) + ThreadPoolManager.DOT + split[1].substring(0, 2);
            }
            return split[0];
        }
        return StringTronUtil.formatNumber0Truncate(BigDecimalUtils.toBigDecimal(bigDecimal.toPlainString().split("\\.")[0]));
    }

    public static Double formatNumber(double d) {
        return Double.valueOf(new BigDecimal(d).setScale(2, RoundingMode.DOWN).doubleValue());
    }

    public static String zeroFill(double d, boolean z) {
        if (z) {
            return StringTronUtil.formatNumber2(d);
        }
        return StringTronUtil.formatNumber0(d);
    }
}
