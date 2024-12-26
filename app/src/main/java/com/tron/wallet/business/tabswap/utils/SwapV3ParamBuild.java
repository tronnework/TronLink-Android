package com.tron.wallet.business.tabswap.utils;

import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.GsonUtils;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.tron.common.utils.LogUtils;
class SwapV3ParamBuild {
    private static int checkIndex(int i, int i2) {
        if (i < 0 || i >= i2) {
            return 0;
        }
        return i;
    }

    SwapV3ParamBuild() {
    }

    private static boolean checkEmpty(SwapInfoOutput swapInfoOutput) {
        return swapInfoOutput == null || swapInfoOutput.getData() == null || swapInfoOutput.getData().size() == 0;
    }

    public static List<String> generatePath(SwapInfoOutput swapInfoOutput, int i) {
        LogUtils.i("SwapV3ParamBuild", "SwapInfoOutput-:" + GsonUtils.toGsonString(swapInfoOutput));
        ArrayList arrayList = new ArrayList();
        if (checkEmpty(swapInfoOutput)) {
            return arrayList;
        }
        List<String> tokens = swapInfoOutput.getData().get(checkIndex(i, swapInfoOutput.getData().size())).getTokens();
        testPrint("tokens:", tokens);
        return tokens != null ? tokens : arrayList;
    }

    public static List<String> generatePoolVersion(SwapInfoOutput swapInfoOutput, int i) {
        List<String> poolVersions;
        final ArrayList arrayList = new ArrayList();
        if (checkEmpty(swapInfoOutput)) {
            return arrayList;
        }
        try {
            poolVersions = swapInfoOutput.getData().get(checkIndex(i, swapInfoOutput.getData().size())).getPoolVersions();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (poolVersions != null && poolVersions.size() != 0) {
            arrayList.add(poolVersions.get(0));
            Collection.-EL.stream(poolVersions).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SwapV3ParamBuild.lambda$generatePoolVersion$0(arrayList, (String) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            testPrint("poolVersions:", arrayList);
            return arrayList;
        }
        return arrayList;
    }

    public static void lambda$generatePoolVersion$0(List list, String str) {
        if (str.equals(list.get(list.size() - 1))) {
            return;
        }
        list.add(str);
    }

    public static List<Integer> generateVersionLen(SwapInfoOutput swapInfoOutput, int i) {
        List<String> poolVersions;
        ArrayList arrayList = new ArrayList();
        if (checkEmpty(swapInfoOutput)) {
            return arrayList;
        }
        try {
            poolVersions = swapInfoOutput.getData().get(checkIndex(i, swapInfoOutput.getData().size())).getPoolVersions();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (poolVersions != null && poolVersions.size() != 0) {
            if (poolVersions.size() == 1) {
                arrayList.add(1);
            } else {
                int i2 = 0;
                for (int i3 = 0; i3 < poolVersions.size(); i3++) {
                    String str = poolVersions.get(i3);
                    if (i3 == 0) {
                        i2++;
                    } else {
                        if (str.equals(poolVersions.get(i3 - 1))) {
                            i2++;
                        } else {
                            arrayList.add(Integer.valueOf(i2));
                            i2 = 1;
                        }
                        if (i3 == poolVersions.size() - 1) {
                            arrayList.add(Integer.valueOf(i2));
                        }
                    }
                }
            }
            arrayList.set(0, Integer.valueOf(((Integer) arrayList.get(0)).intValue() + 1));
            testPrint("VersionLen:", arrayList);
            return arrayList;
        }
        return arrayList;
    }

    public static List<Long> generateFees(SwapInfoOutput swapInfoOutput, int i) {
        final ArrayList arrayList = new ArrayList();
        if (checkEmpty(swapInfoOutput)) {
            return arrayList;
        }
        try {
            List<String> poolFees = swapInfoOutput.getData().get(checkIndex(i, swapInfoOutput.getData().size())).getPoolFees();
            if (poolFees != null) {
                Collection.-EL.stream(poolFees).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        arrayList.add(Long.valueOf(Long.parseLong((String) obj)));
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }
            testPrint("poolFees:", arrayList);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return arrayList;
    }

    public static SwapTuple generateTuple(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, String str) {
        if (checkEmpty(swapInfoOutput)) {
            return null;
        }
        int checkIndex = checkIndex(i, swapInfoOutput.getData().size());
        SwapTuple swapTuple = new SwapTuple();
        try {
            SwapInfoOutput.InfoData infoData = swapInfoOutput.getData().get(checkIndex);
            BigDecimal bigDecimal = BigDecimalUtils.toBigDecimal(infoData.getAmountIn());
            BigDecimal bigDecimal2 = BigDecimalUtils.toBigDecimal(infoData.getAmountOut());
            BigDecimal bigDecimal3 = BigDecimalUtils.toBigDecimal("0.995");
            BigDecimal mul_ = BigDecimalUtils.mul_(bigDecimal, Double.valueOf(Math.pow(10.0d, swapTokenBean.getDecimal())));
            BigDecimal scale = bigDecimal2.multiply(BigDecimalUtils.toBigDecimal(Double.valueOf(Math.pow(10.0d, swapTokenBean2.getDecimal())))).multiply(bigDecimal3).setScale(0, 1);
            BigInteger bigInteger = mul_.toBigInteger();
            BigInteger bigInteger2 = scale.toBigInteger();
            swapTuple.setAmountIn(bigInteger);
            swapTuple.setAmountOut(bigInteger2);
            swapTuple.setToAddress(str);
            swapTuple.setDeadline(System.currentTimeMillis() + 60000);
            LogUtils.i("SwapV3ParamBuild", "Tuple:" + swapTuple.toString());
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return swapTuple;
    }

    static class SwapTuple {
        private BigInteger amountIn;
        private BigInteger amountOut;
        private long deadline;
        private String toAddress;

        public BigInteger getAmountIn() {
            return this.amountIn;
        }

        public BigInteger getAmountOut() {
            return this.amountOut;
        }

        public long getDeadline() {
            return this.deadline;
        }

        public String getToAddress() {
            return this.toAddress;
        }

        public void setAmountIn(BigInteger bigInteger) {
            this.amountIn = bigInteger;
        }

        public void setAmountOut(BigInteger bigInteger) {
            this.amountOut = bigInteger;
        }

        public void setDeadline(long j) {
            this.deadline = j;
        }

        public void setToAddress(String str) {
            this.toAddress = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof SwapTuple;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SwapTuple) {
                SwapTuple swapTuple = (SwapTuple) obj;
                if (swapTuple.canEqual(this) && getDeadline() == swapTuple.getDeadline()) {
                    BigInteger amountIn = getAmountIn();
                    BigInteger amountIn2 = swapTuple.getAmountIn();
                    if (amountIn != null ? amountIn.equals(amountIn2) : amountIn2 == null) {
                        BigInteger amountOut = getAmountOut();
                        BigInteger amountOut2 = swapTuple.getAmountOut();
                        if (amountOut != null ? amountOut.equals(amountOut2) : amountOut2 == null) {
                            String toAddress = getToAddress();
                            String toAddress2 = swapTuple.getToAddress();
                            return toAddress != null ? toAddress.equals(toAddress2) : toAddress2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            long deadline = getDeadline();
            BigInteger amountIn = getAmountIn();
            int hashCode = ((((int) (deadline ^ (deadline >>> 32))) + 59) * 59) + (amountIn == null ? 43 : amountIn.hashCode());
            BigInteger amountOut = getAmountOut();
            int hashCode2 = (hashCode * 59) + (amountOut == null ? 43 : amountOut.hashCode());
            String toAddress = getToAddress();
            return (hashCode2 * 59) + (toAddress != null ? toAddress.hashCode() : 43);
        }

        public String toString() {
            return this.amountIn + "," + this.amountOut + "," + this.toAddress + "," + this.deadline;
        }
    }

    private static void testPrint(String str, List list) {
        try {
            String deepToString = Arrays.deepToString(list.toArray());
            LogUtils.i("SwapV3ParamBuild", str + deepToString);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
