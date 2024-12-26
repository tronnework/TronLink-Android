package org.tron.common.utils;

import java.math.BigInteger;
public class BIUtil {
    public static boolean isLessThan(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) < 0;
    }
}
