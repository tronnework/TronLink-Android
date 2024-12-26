package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;

import java.util.List;
public class FinancialListParam {
    private int sort;
    private List<String> walletAddress;

    public int getSort() {
        return this.sort;
    }

    public List<String> getWalletAddress() {
        return this.walletAddress;
    }

    public void setSort(int i) {
        this.sort = i;
    }

    public void setWalletAddress(List<String> list) {
        this.walletAddress = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FinancialListParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinancialListParam) {
            FinancialListParam financialListParam = (FinancialListParam) obj;
            if (financialListParam.canEqual(this) && getSort() == financialListParam.getSort()) {
                List<String> walletAddress = getWalletAddress();
                List<String> walletAddress2 = financialListParam.getWalletAddress();
                return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<String> walletAddress = getWalletAddress();
        return ((getSort() + 59) * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
    }

    public String toString() {
        return "FinancialListParam(sort=" + getSort() + ", walletAddress=" + getWalletAddress() + ")";
    }
}
