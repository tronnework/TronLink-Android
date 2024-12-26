package org.tron.common.utils;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
public class DecimalUtils {
    public static String toString(BigDecimal bigDecimal) {
        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    public static String toString(Object obj) {
        return toBigDecimal(obj).stripTrailingZeros().toPlainString();
    }

    public static BigDecimal toBigDecimal(Object obj) {
        try {
            if (obj instanceof BigDecimal) {
                return (BigDecimal) obj;
            }
            if (obj instanceof String) {
                if (TextUtils.isEmpty((String) obj)) {
                    return new BigDecimal(0);
                }
                return new BigDecimal((String) obj);
            } else if (obj instanceof Double) {
                return BigDecimal.valueOf(((Double) obj).doubleValue());
            } else {
                if (obj instanceof Float) {
                    return new BigDecimal(String.valueOf(obj));
                }
                if (obj instanceof Integer) {
                    return new BigDecimal(((Integer) obj).intValue());
                }
                if (obj instanceof BigInteger) {
                    return new BigDecimal((BigInteger) obj);
                }
                if (obj instanceof Long) {
                    return BigDecimal.valueOf(((Long) obj).longValue());
                }
                return new BigDecimal(0);
            }
        } catch (Throwable unused) {
            return BigDecimal.valueOf(0L);
        }
    }
}
