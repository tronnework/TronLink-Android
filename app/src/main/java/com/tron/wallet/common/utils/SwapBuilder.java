package com.tron.wallet.common.utils;
public abstract class SwapBuilder {
    public String inputAmount;
    public String inputReserve;
    public String outputReserve;
    public String slippage = "1.0";

    public SwapBuilder addInputAmount(String str) {
        this.inputAmount = str;
        return this;
    }

    public SwapBuilder addInputReserve(String str) {
        this.inputReserve = str;
        return this;
    }

    public SwapBuilder addOutputReserve(String str) {
        this.outputReserve = str;
        return this;
    }

    public SwapBuilder addSlippage(String str) {
        this.slippage = str;
        return this;
    }

    public String calcTrxTokenInputFromOutput() {
        return "";
    }

    public abstract String calcTrxTokenInputFromOutput(int i);

    public String calcTrxTokenOutputFromInput() {
        return "";
    }

    public abstract String calcTrxTokenOutputFromInput(int i);
}
