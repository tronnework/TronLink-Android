package com.tron.wallet.business.upgraded.bean;

import java.io.Serializable;
public class UpgradeHdListBean implements Serializable {
    private double balance;
    private String mnemonicPath;
    private long txNum;
    private String walletAddress;
    private String walletName;

    public double getBalance() {
        return this.balance;
    }

    public String getMnemonicPath() {
        return this.mnemonicPath;
    }

    public long getTxNum() {
        return this.txNum;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public void setBalance(double d) {
        this.balance = d;
    }

    public void setMnemonicPath(String str) {
        this.mnemonicPath = str;
    }

    public void setTxNum(long j) {
        this.txNum = j;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof UpgradeHdListBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UpgradeHdListBean) {
            UpgradeHdListBean upgradeHdListBean = (UpgradeHdListBean) obj;
            if (upgradeHdListBean.canEqual(this) && Double.compare(getBalance(), upgradeHdListBean.getBalance()) == 0 && getTxNum() == upgradeHdListBean.getTxNum()) {
                String walletName = getWalletName();
                String walletName2 = upgradeHdListBean.getWalletName();
                if (walletName != null ? walletName.equals(walletName2) : walletName2 == null) {
                    String walletAddress = getWalletAddress();
                    String walletAddress2 = upgradeHdListBean.getWalletAddress();
                    if (walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null) {
                        String mnemonicPath = getMnemonicPath();
                        String mnemonicPath2 = upgradeHdListBean.getMnemonicPath();
                        return mnemonicPath != null ? mnemonicPath.equals(mnemonicPath2) : mnemonicPath2 == null;
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
        long doubleToLongBits = Double.doubleToLongBits(getBalance());
        long txNum = getTxNum();
        String walletName = getWalletName();
        int hashCode = ((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 59) * 59) + ((int) ((txNum >>> 32) ^ txNum))) * 59) + (walletName == null ? 43 : walletName.hashCode());
        String walletAddress = getWalletAddress();
        int hashCode2 = (hashCode * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
        String mnemonicPath = getMnemonicPath();
        return (hashCode2 * 59) + (mnemonicPath != null ? mnemonicPath.hashCode() : 43);
    }

    public String toString() {
        return "UpgradeHdListBean(walletName=" + getWalletName() + ", walletAddress=" + getWalletAddress() + ", mnemonicPath=" + getMnemonicPath() + ", balance=" + getBalance() + ", txNum=" + getTxNum() + ")";
    }
}
