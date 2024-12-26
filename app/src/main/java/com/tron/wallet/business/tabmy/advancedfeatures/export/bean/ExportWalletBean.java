package com.tron.wallet.business.tabmy.advancedfeatures.export.bean;

import java.util.List;
public class ExportWalletBean {
    public static final String md5Default = "default";
    private String md5 = md5Default;
    private List<WatchWallet> watchWallets;
    private int watchWalletsNum;

    public String getMd5() {
        return this.md5;
    }

    public List<WatchWallet> getWatchWallets() {
        return this.watchWallets;
    }

    public int getWatchWalletsNum() {
        return this.watchWalletsNum;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setWatchWallets(List<WatchWallet> list) {
        this.watchWallets = list;
    }

    public void setWatchWalletsNum(int i) {
        this.watchWalletsNum = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ExportWalletBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ExportWalletBean) {
            ExportWalletBean exportWalletBean = (ExportWalletBean) obj;
            if (exportWalletBean.canEqual(this) && getWatchWalletsNum() == exportWalletBean.getWatchWalletsNum()) {
                List<WatchWallet> watchWallets = getWatchWallets();
                List<WatchWallet> watchWallets2 = exportWalletBean.getWatchWallets();
                if (watchWallets != null ? watchWallets.equals(watchWallets2) : watchWallets2 == null) {
                    String md5 = getMd5();
                    String md52 = exportWalletBean.getMd5();
                    return md5 != null ? md5.equals(md52) : md52 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<WatchWallet> watchWallets = getWatchWallets();
        int watchWalletsNum = ((getWatchWalletsNum() + 59) * 59) + (watchWallets == null ? 43 : watchWallets.hashCode());
        String md5 = getMd5();
        return (watchWalletsNum * 59) + (md5 != null ? md5.hashCode() : 43);
    }

    public String toString() {
        return "ExportWalletBean(watchWallets=" + getWatchWallets() + ", watchWalletsNum=" + getWatchWalletsNum() + ", md5=" + getMd5() + ")";
    }

    public static class WatchWallet {
        private int createType;
        private String mnemonicPath;
        private String walletAddress;
        private String walletName;

        public int getCreateType() {
            return this.createType;
        }

        public String getMnemonicPath() {
            return this.mnemonicPath;
        }

        public String getWalletAddress() {
            return this.walletAddress;
        }

        public String getWalletName() {
            return this.walletName;
        }

        public void setCreateType(int i) {
            this.createType = i;
        }

        public void setMnemonicPath(String str) {
            this.mnemonicPath = str;
        }

        public void setWalletAddress(String str) {
            this.walletAddress = str;
        }

        public void setWalletName(String str) {
            this.walletName = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof WatchWallet;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof WatchWallet) {
                WatchWallet watchWallet = (WatchWallet) obj;
                if (watchWallet.canEqual(this) && getCreateType() == watchWallet.getCreateType()) {
                    String walletName = getWalletName();
                    String walletName2 = watchWallet.getWalletName();
                    if (walletName != null ? walletName.equals(walletName2) : walletName2 == null) {
                        String walletAddress = getWalletAddress();
                        String walletAddress2 = watchWallet.getWalletAddress();
                        if (walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null) {
                            String mnemonicPath = getMnemonicPath();
                            String mnemonicPath2 = watchWallet.getMnemonicPath();
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
            String walletName = getWalletName();
            int createType = ((getCreateType() + 59) * 59) + (walletName == null ? 43 : walletName.hashCode());
            String walletAddress = getWalletAddress();
            int hashCode = (createType * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
            String mnemonicPath = getMnemonicPath();
            return (hashCode * 59) + (mnemonicPath != null ? mnemonicPath.hashCode() : 43);
        }

        public String toString() {
            return "ExportWalletBean.WatchWallet(walletName=" + getWalletName() + ", walletAddress=" + getWalletAddress() + ", createType=" + getCreateType() + ", mnemonicPath=" + getMnemonicPath() + ")";
        }
    }
}
