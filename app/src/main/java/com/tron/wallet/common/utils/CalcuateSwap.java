package com.tron.wallet.common.utils;

import java.math.BigDecimal;
public class CalcuateSwap {
    public static String afterFee = "0.997";
    public static String constantOne = "0";

    public static class UNIBuilder extends SwapBuilder {
        @Override
        public String calcTrxTokenInputFromOutput(int i) {
            return null;
        }

        @Override
        public String calcTrxTokenOutputFromInput(int i) {
            return null;
        }
    }

    public static class JUSTBuilder extends SwapBuilder {
        @Override
        public String calcTrxTokenOutputFromInput(int i) {
            BigDecimal mul_ = BigDecimalUtils.mul_(BigDecimalUtils.mul_(this.inputAmount, CalcuateSwap.afterFee), this.slippage);
            return BigDecimalUtils.toString(BigDecimalUtils.div_(BigDecimalUtils.mul_(mul_, this.outputReserve), BigDecimalUtils.sum_(this.inputReserve, mul_), i));
        }

        @Override
        public String calcTrxTokenInputFromOutput(int i) {
            return BigDecimalUtils.toString(BigDecimalUtils.sum_(BigDecimalUtils.div_(BigDecimalUtils.mul_(this.inputReserve, this.inputAmount), BigDecimalUtils.mul_(BigDecimalUtils.mul_(BigDecimalUtils.sub_(this.outputReserve, this.inputAmount), CalcuateSwap.afterFee), this.slippage), i), CalcuateSwap.constantOne));
        }
    }
}
