package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;
public class FinancialListH5InputParam {
    private int sort;
    private String walletAddress;

    public int getSort() {
        return this.sort;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public void setSort(int i) {
        this.sort = i;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FinancialListH5InputParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinancialListH5InputParam) {
            FinancialListH5InputParam financialListH5InputParam = (FinancialListH5InputParam) obj;
            if (financialListH5InputParam.canEqual(this) && getSort() == financialListH5InputParam.getSort()) {
                String walletAddress = getWalletAddress();
                String walletAddress2 = financialListH5InputParam.getWalletAddress();
                return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String walletAddress = getWalletAddress();
        return ((getSort() + 59) * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
    }

    public String toString() {
        return "FinancialListH5InputParam(sort=" + getSort() + ", walletAddress=" + getWalletAddress() + ")";
    }
}
