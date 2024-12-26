package com.tron.wallet.business.finance.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
public class FinanceTotalAssetsBean {
    private int code;
    private FinanceTotalAssetsDataBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public FinanceTotalAssetsDataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(FinanceTotalAssetsDataBean financeTotalAssetsDataBean) {
        this.data = financeTotalAssetsDataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceTotalAssetsBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceTotalAssetsBean) {
            FinanceTotalAssetsBean financeTotalAssetsBean = (FinanceTotalAssetsBean) obj;
            if (financeTotalAssetsBean.canEqual(this) && getCode() == financeTotalAssetsBean.getCode()) {
                String message = getMessage();
                String message2 = financeTotalAssetsBean.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    FinanceTotalAssetsDataBean data = getData();
                    FinanceTotalAssetsDataBean data2 = financeTotalAssetsBean.getData();
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
        FinanceTotalAssetsDataBean data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "FinanceTotalAssetsBean(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }

    public static class FinanceTotalAssetsDataBean {
        @JsonProperty("financialAssetsCny")
        private String financialAssetsCny;
        @JsonProperty("financialAssetsUsd")
        private String financialAssetsUsd;
        @JsonProperty("financialIncomeCny")
        private String financialIncomeCny;
        @JsonProperty("financialIncomeUsd")
        private String financialIncomeUsd;
        @JsonProperty("financialPercent")
        private String financialPercent;
        @JsonProperty("totalAssetsCny")
        private String totalAssetsCny;
        @JsonProperty("totalAssetsUsd")
        private String totalAssetsUsd;

        public String getFinancialAssetsCny() {
            return this.financialAssetsCny;
        }

        public String getFinancialAssetsUsd() {
            return this.financialAssetsUsd;
        }

        public String getFinancialIncomeCny() {
            return this.financialIncomeCny;
        }

        public String getFinancialIncomeUsd() {
            return this.financialIncomeUsd;
        }

        public String getFinancialPercent() {
            return this.financialPercent;
        }

        public String getTotalAssetsCny() {
            return this.totalAssetsCny;
        }

        public String getTotalAssetsUsd() {
            return this.totalAssetsUsd;
        }

        @JsonProperty("financialAssetsCny")
        public void setFinancialAssetsCny(String str) {
            this.financialAssetsCny = str;
        }

        @JsonProperty("financialAssetsUsd")
        public void setFinancialAssetsUsd(String str) {
            this.financialAssetsUsd = str;
        }

        @JsonProperty("financialIncomeCny")
        public void setFinancialIncomeCny(String str) {
            this.financialIncomeCny = str;
        }

        @JsonProperty("financialIncomeUsd")
        public void setFinancialIncomeUsd(String str) {
            this.financialIncomeUsd = str;
        }

        @JsonProperty("financialPercent")
        public void setFinancialPercent(String str) {
            this.financialPercent = str;
        }

        @JsonProperty("totalAssetsCny")
        public void setTotalAssetsCny(String str) {
            this.totalAssetsCny = str;
        }

        @JsonProperty("totalAssetsUsd")
        public void setTotalAssetsUsd(String str) {
            this.totalAssetsUsd = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof FinanceTotalAssetsDataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FinanceTotalAssetsDataBean) {
                FinanceTotalAssetsDataBean financeTotalAssetsDataBean = (FinanceTotalAssetsDataBean) obj;
                if (financeTotalAssetsDataBean.canEqual(this)) {
                    String financialPercent = getFinancialPercent();
                    String financialPercent2 = financeTotalAssetsDataBean.getFinancialPercent();
                    if (financialPercent != null ? financialPercent.equals(financialPercent2) : financialPercent2 == null) {
                        String financialAssetsCny = getFinancialAssetsCny();
                        String financialAssetsCny2 = financeTotalAssetsDataBean.getFinancialAssetsCny();
                        if (financialAssetsCny != null ? financialAssetsCny.equals(financialAssetsCny2) : financialAssetsCny2 == null) {
                            String financialAssetsUsd = getFinancialAssetsUsd();
                            String financialAssetsUsd2 = financeTotalAssetsDataBean.getFinancialAssetsUsd();
                            if (financialAssetsUsd != null ? financialAssetsUsd.equals(financialAssetsUsd2) : financialAssetsUsd2 == null) {
                                String financialIncomeCny = getFinancialIncomeCny();
                                String financialIncomeCny2 = financeTotalAssetsDataBean.getFinancialIncomeCny();
                                if (financialIncomeCny != null ? financialIncomeCny.equals(financialIncomeCny2) : financialIncomeCny2 == null) {
                                    String financialIncomeUsd = getFinancialIncomeUsd();
                                    String financialIncomeUsd2 = financeTotalAssetsDataBean.getFinancialIncomeUsd();
                                    if (financialIncomeUsd != null ? financialIncomeUsd.equals(financialIncomeUsd2) : financialIncomeUsd2 == null) {
                                        String totalAssetsCny = getTotalAssetsCny();
                                        String totalAssetsCny2 = financeTotalAssetsDataBean.getTotalAssetsCny();
                                        if (totalAssetsCny != null ? totalAssetsCny.equals(totalAssetsCny2) : totalAssetsCny2 == null) {
                                            String totalAssetsUsd = getTotalAssetsUsd();
                                            String totalAssetsUsd2 = financeTotalAssetsDataBean.getTotalAssetsUsd();
                                            return totalAssetsUsd != null ? totalAssetsUsd.equals(totalAssetsUsd2) : totalAssetsUsd2 == null;
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
            String financialPercent = getFinancialPercent();
            int hashCode = financialPercent == null ? 43 : financialPercent.hashCode();
            String financialAssetsCny = getFinancialAssetsCny();
            int hashCode2 = ((hashCode + 59) * 59) + (financialAssetsCny == null ? 43 : financialAssetsCny.hashCode());
            String financialAssetsUsd = getFinancialAssetsUsd();
            int hashCode3 = (hashCode2 * 59) + (financialAssetsUsd == null ? 43 : financialAssetsUsd.hashCode());
            String financialIncomeCny = getFinancialIncomeCny();
            int hashCode4 = (hashCode3 * 59) + (financialIncomeCny == null ? 43 : financialIncomeCny.hashCode());
            String financialIncomeUsd = getFinancialIncomeUsd();
            int hashCode5 = (hashCode4 * 59) + (financialIncomeUsd == null ? 43 : financialIncomeUsd.hashCode());
            String totalAssetsCny = getTotalAssetsCny();
            int hashCode6 = (hashCode5 * 59) + (totalAssetsCny == null ? 43 : totalAssetsCny.hashCode());
            String totalAssetsUsd = getTotalAssetsUsd();
            return (hashCode6 * 59) + (totalAssetsUsd != null ? totalAssetsUsd.hashCode() : 43);
        }

        public String toString() {
            return "FinanceTotalAssetsBean.FinanceTotalAssetsDataBean(financialPercent=" + getFinancialPercent() + ", financialAssetsCny=" + getFinancialAssetsCny() + ", financialAssetsUsd=" + getFinancialAssetsUsd() + ", financialIncomeCny=" + getFinancialIncomeCny() + ", financialIncomeUsd=" + getFinancialIncomeUsd() + ", totalAssetsCny=" + getTotalAssetsCny() + ", totalAssetsUsd=" + getTotalAssetsUsd() + ")";
        }
    }
}
