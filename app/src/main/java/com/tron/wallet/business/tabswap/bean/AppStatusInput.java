package com.tron.wallet.business.tabswap.bean;

import java.util.List;
public class AppStatusInput {
    private boolean fina;
    private boolean mainland;
    private List<String> walletAddress;

    public List<String> getWalletAddress() {
        return this.walletAddress;
    }

    public boolean isFina() {
        return this.fina;
    }

    public boolean isMainland() {
        return this.mainland;
    }

    public void setFina(boolean z) {
        this.fina = z;
    }

    public void setMainland(boolean z) {
        this.mainland = z;
    }

    public void setWalletAddress(List<String> list) {
        this.walletAddress = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AppStatusInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AppStatusInput) {
            AppStatusInput appStatusInput = (AppStatusInput) obj;
            if (appStatusInput.canEqual(this) && isMainland() == appStatusInput.isMainland() && isFina() == appStatusInput.isFina()) {
                List<String> walletAddress = getWalletAddress();
                List<String> walletAddress2 = appStatusInput.getWalletAddress();
                return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = (((isMainland() ? 79 : 97) + 59) * 59) + (isFina() ? 79 : 97);
        List<String> walletAddress = getWalletAddress();
        return (i * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
    }

    public String toString() {
        return "AppStatusInput(mainland=" + isMainland() + ", fina=" + isFina() + ", walletAddress=" + getWalletAddress() + ")";
    }
}
