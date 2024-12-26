package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;

import java.util.List;
public class FinacialParam {
    private boolean showUsers;
    private List<String> walletAddress;

    public List<String> getWalletAddress() {
        return this.walletAddress;
    }

    public boolean isShowUsers() {
        return this.showUsers;
    }

    public void setShowUsers(boolean z) {
        this.showUsers = z;
    }

    public void setWalletAddress(List<String> list) {
        this.walletAddress = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FinacialParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinacialParam) {
            FinacialParam finacialParam = (FinacialParam) obj;
            if (finacialParam.canEqual(this) && isShowUsers() == finacialParam.isShowUsers()) {
                List<String> walletAddress = getWalletAddress();
                List<String> walletAddress2 = finacialParam.getWalletAddress();
                return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = isShowUsers() ? 79 : 97;
        List<String> walletAddress = getWalletAddress();
        return ((i + 59) * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
    }

    public String toString() {
        return "FinacialParam(walletAddress=" + getWalletAddress() + ", showUsers=" + isShowUsers() + ")";
    }
}
