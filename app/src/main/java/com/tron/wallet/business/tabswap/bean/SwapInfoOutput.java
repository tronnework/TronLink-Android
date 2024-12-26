package com.tron.wallet.business.tabswap.bean;

import java.util.List;
public class SwapInfoOutput {
    private int code;
    private List<InfoData> data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public List<InfoData> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<InfoData> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SwapInfoOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SwapInfoOutput) {
            SwapInfoOutput swapInfoOutput = (SwapInfoOutput) obj;
            if (swapInfoOutput.canEqual(this) && getCode() == swapInfoOutput.getCode()) {
                String message = getMessage();
                String message2 = swapInfoOutput.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    List<InfoData> data = getData();
                    List<InfoData> data2 = swapInfoOutput.getData();
                    return data != null ? data.equals(data2) : data2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String message = getMessage();
        int code = ((getCode() + 59) * 59) + (message == null ? 43 : message.hashCode());
        List<InfoData> data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "SwapInfoOutput(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }

    public SwapInfoOutput() {
    }

    public SwapInfoOutput(int i, String str, List<InfoData> list) {
        this.code = i;
        this.message = str;
        this.data = list;
    }

    public static class InfoData {
        private String amountIn;
        private String amountOut;
        private String fee;
        private String impact;
        private String inUsd;
        private String outUsd;
        private List<String> poolFees;
        private List<String> poolVersions;
        private List<String> stepAmountsOut;
        private List<String> symbols;
        private List<String> tokens;

        public String getAmountIn() {
            return this.amountIn;
        }

        public String getAmountOut() {
            return this.amountOut;
        }

        public String getFee() {
            return this.fee;
        }

        public String getImpact() {
            return this.impact;
        }

        public String getInUsd() {
            return this.inUsd;
        }

        public String getOutUsd() {
            return this.outUsd;
        }

        public List<String> getPoolFees() {
            return this.poolFees;
        }

        public List<String> getPoolVersions() {
            return this.poolVersions;
        }

        public List<String> getStepAmountsOut() {
            return this.stepAmountsOut;
        }

        public List<String> getSymbols() {
            return this.symbols;
        }

        public List<String> getTokens() {
            return this.tokens;
        }

        public void setAmountIn(String str) {
            this.amountIn = str;
        }

        public void setAmountOut(String str) {
            this.amountOut = str;
        }

        public void setFee(String str) {
            this.fee = str;
        }

        public void setImpact(String str) {
            this.impact = str;
        }

        public void setInUsd(String str) {
            this.inUsd = str;
        }

        public void setOutUsd(String str) {
            this.outUsd = str;
        }

        public void setPoolFees(List<String> list) {
            this.poolFees = list;
        }

        public void setPoolVersions(List<String> list) {
            this.poolVersions = list;
        }

        public void setStepAmountsOut(List<String> list) {
            this.stepAmountsOut = list;
        }

        public void setSymbols(List<String> list) {
            this.symbols = list;
        }

        public void setTokens(List<String> list) {
            this.tokens = list;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof InfoData;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof InfoData) {
                InfoData infoData = (InfoData) obj;
                if (infoData.canEqual(this)) {
                    String amountIn = getAmountIn();
                    String amountIn2 = infoData.getAmountIn();
                    if (amountIn != null ? amountIn.equals(amountIn2) : amountIn2 == null) {
                        String amountOut = getAmountOut();
                        String amountOut2 = infoData.getAmountOut();
                        if (amountOut != null ? amountOut.equals(amountOut2) : amountOut2 == null) {
                            String inUsd = getInUsd();
                            String inUsd2 = infoData.getInUsd();
                            if (inUsd != null ? inUsd.equals(inUsd2) : inUsd2 == null) {
                                String outUsd = getOutUsd();
                                String outUsd2 = infoData.getOutUsd();
                                if (outUsd != null ? outUsd.equals(outUsd2) : outUsd2 == null) {
                                    String impact = getImpact();
                                    String impact2 = infoData.getImpact();
                                    if (impact != null ? impact.equals(impact2) : impact2 == null) {
                                        String fee = getFee();
                                        String fee2 = infoData.getFee();
                                        if (fee != null ? fee.equals(fee2) : fee2 == null) {
                                            List<String> tokens = getTokens();
                                            List<String> tokens2 = infoData.getTokens();
                                            if (tokens != null ? tokens.equals(tokens2) : tokens2 == null) {
                                                List<String> symbols = getSymbols();
                                                List<String> symbols2 = infoData.getSymbols();
                                                if (symbols != null ? symbols.equals(symbols2) : symbols2 == null) {
                                                    List<String> poolFees = getPoolFees();
                                                    List<String> poolFees2 = infoData.getPoolFees();
                                                    if (poolFees != null ? poolFees.equals(poolFees2) : poolFees2 == null) {
                                                        List<String> poolVersions = getPoolVersions();
                                                        List<String> poolVersions2 = infoData.getPoolVersions();
                                                        if (poolVersions != null ? poolVersions.equals(poolVersions2) : poolVersions2 == null) {
                                                            List<String> stepAmountsOut = getStepAmountsOut();
                                                            List<String> stepAmountsOut2 = infoData.getStepAmountsOut();
                                                            return stepAmountsOut != null ? stepAmountsOut.equals(stepAmountsOut2) : stepAmountsOut2 == null;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
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
            String amountIn = getAmountIn();
            int hashCode = amountIn == null ? 43 : amountIn.hashCode();
            String amountOut = getAmountOut();
            int hashCode2 = ((hashCode + 59) * 59) + (amountOut == null ? 43 : amountOut.hashCode());
            String inUsd = getInUsd();
            int hashCode3 = (hashCode2 * 59) + (inUsd == null ? 43 : inUsd.hashCode());
            String outUsd = getOutUsd();
            int hashCode4 = (hashCode3 * 59) + (outUsd == null ? 43 : outUsd.hashCode());
            String impact = getImpact();
            int hashCode5 = (hashCode4 * 59) + (impact == null ? 43 : impact.hashCode());
            String fee = getFee();
            int hashCode6 = (hashCode5 * 59) + (fee == null ? 43 : fee.hashCode());
            List<String> tokens = getTokens();
            int hashCode7 = (hashCode6 * 59) + (tokens == null ? 43 : tokens.hashCode());
            List<String> symbols = getSymbols();
            int hashCode8 = (hashCode7 * 59) + (symbols == null ? 43 : symbols.hashCode());
            List<String> poolFees = getPoolFees();
            int hashCode9 = (hashCode8 * 59) + (poolFees == null ? 43 : poolFees.hashCode());
            List<String> poolVersions = getPoolVersions();
            int hashCode10 = (hashCode9 * 59) + (poolVersions == null ? 43 : poolVersions.hashCode());
            List<String> stepAmountsOut = getStepAmountsOut();
            return (hashCode10 * 59) + (stepAmountsOut != null ? stepAmountsOut.hashCode() : 43);
        }

        public String toString() {
            return "SwapInfoOutput.InfoData(amountIn=" + getAmountIn() + ", amountOut=" + getAmountOut() + ", inUsd=" + getInUsd() + ", outUsd=" + getOutUsd() + ", impact=" + getImpact() + ", fee=" + getFee() + ", tokens=" + getTokens() + ", symbols=" + getSymbols() + ", poolFees=" + getPoolFees() + ", poolVersions=" + getPoolVersions() + ", stepAmountsOut=" + getStepAmountsOut() + ")";
        }

        public InfoData() {
        }

        public InfoData(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
            this.amountIn = str;
            this.amountOut = str2;
            this.inUsd = str3;
            this.outUsd = str4;
            this.impact = str5;
            this.fee = str6;
            this.tokens = list;
            this.symbols = list2;
            this.poolFees = list3;
            this.poolVersions = list4;
            this.stepAmountsOut = list5;
        }
    }
}
