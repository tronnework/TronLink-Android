package com.tron.wallet.db.bean;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import org.tron.walletserver.WalletPath;
public class HdTronRelationshipBean {
    private long createTime;
    private String currentWalletAddress;
    private String mnemonicPath;
    private boolean nonHd;
    private String relationshipHDAddress;
    private String walletName;

    public long getCreateTime() {
        return this.createTime;
    }

    public String getCurrentWalletAddress() {
        return this.currentWalletAddress;
    }

    public String getMnemonicPath() {
        return this.mnemonicPath;
    }

    public boolean getNonHd() {
        return this.nonHd;
    }

    public String getRelationshipHDAddress() {
        return this.relationshipHDAddress;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setCurrentWalletAddress(String str) {
        this.currentWalletAddress = str;
    }

    public void setMnemonicPath(String str) {
        this.mnemonicPath = str;
    }

    public void setNonHd(boolean z) {
        this.nonHd = z;
    }

    public void setRelationshipHDAddress(String str) {
        this.relationshipHDAddress = str;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    public HdTronRelationshipBean(String str, String str2, String str3, String str4, long j, boolean z) {
        this.currentWalletAddress = str;
        this.relationshipHDAddress = str2;
        this.mnemonicPath = str3;
        this.walletName = str4;
        this.createTime = j;
        this.nonHd = z;
    }

    public HdTronRelationshipBean() {
    }

    public WalletPath getWalletPath() {
        if (!StringTronUtil.isEmpty(this.mnemonicPath)) {
            try {
                return (WalletPath) GsonUtils.gsonToBean(this.mnemonicPath, WalletPath.class);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return new WalletPath();
    }
}
