package com.tron.wallet.common.utils;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.LogUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
public class BigDecimalUtils {
    public static double round(double d, int i, int i2) {
        return new BigDecimal(d).setScale(i, i2).doubleValue();
    }

    public static double sum(double d, double d2) {
        return sum_(Double.valueOf(d), Double.valueOf(d2)).doubleValue();
    }

    public static BigDecimal sum_(Object obj, Object obj2) {
        return toBigDecimal(obj).add(toBigDecimal(obj2));
    }

    public static double sub(double d, double d2) {
        return sub_(Double.valueOf(d), Double.valueOf(d2)).doubleValue();
    }

    public static BigDecimal sub_(Object obj, Object obj2) {
        return toBigDecimal(obj).subtract(toBigDecimal(obj2));
    }

    public static double mul(double d, double d2) {
        return mul_(Double.valueOf(d), Double.valueOf(d2)).doubleValue();
    }

    public static BigDecimal mul_(Object obj, Object obj2) {
        return toBigDecimal(obj).multiply(toBigDecimal(obj2));
    }

    public static BigDecimal mul_(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.multiply(bigDecimal2);
    }

    public static double div(double d, double d2, int i) {
        return div_(Double.valueOf(d), Double.valueOf(d2), i).doubleValue();
    }

    public static BigDecimal div_(Object obj, Object obj2, int i) {
        return toBigDecimal(obj).divide(toBigDecimal(obj2), i, 1);
    }

    public static BigDecimal div_(BigDecimal bigDecimal, BigDecimal bigDecimal2, int i) {
        return bigDecimal.divide(bigDecimal2, i, 1);
    }

    public static double div(Object obj, Object obj2, int i) {
        try {
            return toBigDecimal(obj).divide(toBigDecimal(obj2), i, 1).doubleValue();
        } catch (Exception e) {
            LogUtils.e(e);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    public static double div(Object obj, Object obj2) {
        try {
            return div(toBigDecimal(obj), toBigDecimal(obj2)).doubleValue();
        } catch (Exception e) {
            LogUtils.e(e);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    public static BigDecimal div_(Object obj, Object obj2) {
        try {
            return div(toBigDecimal(obj), toBigDecimal(obj2));
        } catch (Exception e) {
            LogUtils.e(e);
            return toBigDecimal(0);
        }
    }

    public static BigDecimal div(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        try {
            if (Equal((Object) bigDecimal2, (Object) 0)) {
                return toBigDecimal(0);
            }
            return bigDecimal.divide(bigDecimal2, 32, 1);
        } catch (Exception e) {
            LogUtils.e(e);
            return toBigDecimal(0);
        }
    }

    public static boolean between(Object obj, Object obj2, Object obj3) {
        try {
            BigDecimal bigDecimal = toBigDecimal(obj);
            BigDecimal bigDecimal2 = toBigDecimal(obj2);
            BigDecimal bigDecimal3 = toBigDecimal(obj3);
            if (MoreThan(bigDecimal, bigDecimal2)) {
                return LessThan(bigDecimal, bigDecimal3);
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean MoreThan(Object obj, Object obj2) {
        try {
            return MoreThan(toBigDecimal(obj), toBigDecimal(obj2));
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean MoreThan(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        try {
            return bigDecimal.compareTo(bigDecimal2) == 1;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean LessThan(Object obj, Object obj2) {
        try {
            return LessThan(toBigDecimal(obj), toBigDecimal(obj2));
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean LessThan(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        try {
            return bigDecimal.compareTo(bigDecimal2) == -1;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean Equal(Object obj, Object obj2) {
        try {
            return Equal(toBigDecimal(obj), toBigDecimal(obj2));
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean Equal(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        try {
            return bigDecimal.compareTo(bigDecimal2) == 0;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean moreThanOrEqual(Object obj, Object obj2) {
        return MoreThan(obj, obj2) || Equal(obj, obj2);
    }

    public static boolean lessThanOrEqual(Object obj, Object obj2) {
        return LessThan(obj, obj2) || Equal(obj, obj2);
    }

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

    public static boolean equalsZero(Number number) {
        return Equal(new BigDecimal(String.valueOf(number)), new BigDecimal("0.0"));
    }

    public static boolean equalsZero(Object obj) {
        return Equal(toBigDecimal(obj), new BigDecimal("0.0"));
    }
}
