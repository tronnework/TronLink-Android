package com.tron.wallet.business.finance.bean;

import java.util.List;
public class MyFinancialTokenListInput {
    private List<String> walletAddress;

    public List<String> getWalletAddress() {
        return this.walletAddress;
    }

    public void setWalletAddress(List<String> list) {
        this.walletAddress = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MyFinancialTokenListInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MyFinancialTokenListInput) {
            MyFinancialTokenListInput myFinancialTokenListInput = (MyFinancialTokenListInput) obj;
            if (myFinancialTokenListInput.canEqual(this)) {
                List<String> walletAddress = getWalletAddress();
                List<String> walletAddress2 = myFinancialTokenListInput.getWalletAddress();
                return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<String> walletAddress = getWalletAddress();
        return 59 + (walletAddress == null ? 43 : walletAddress.hashCode());
    }

    public String toString() {
        return "MyFinancialTokenListInput(walletAddress=" + getWalletAddress() + ")";
    }
}
