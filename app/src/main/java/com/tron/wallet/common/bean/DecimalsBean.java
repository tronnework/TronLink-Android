package com.tron.wallet.common.bean;
public class DecimalsBean {
    private long decimals;
    private String shieldAddress;
    private String tokenAddress;

    public long getDecimals() {
        return this.decimals;
    }

    public String getShieldAddress() {
        return this.shieldAddress;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public void setDecimals(long j) {
        this.decimals = j;
    }

    public void setShieldAddress(String str) {
        this.shieldAddress = str;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }
}
