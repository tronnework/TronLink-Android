package com.tron.wallet.business.upgraded.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class UpgradeHdBean implements Serializable {
    private BigDecimal balance;
    private boolean expand;
    private boolean nonHd;
    private String relationshipZeroAddress;
    private boolean selected;
    private String title;
    private long txTotalNum;
    private Type type;
    private List<UpgradeHdListBean> upgradeHdLists;
    private long zeroAddressCreateTime;

    public enum Type implements Serializable {
        NULL,
        MoreTx,
        MoreAssets,
        MoreTxAndMoreAssets
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public String getRelationshipZeroAddress() {
        return this.relationshipZeroAddress;
    }

    public String getTitle() {
        return this.title;
    }

    public long getTxTotalNum() {
        return this.txTotalNum;
    }

    public Type getType() {
        return this.type;
    }

    public List<UpgradeHdListBean> getUpgradeHdLists() {
        return this.upgradeHdLists;
    }

    public long getZeroAddressCreateTime() {
        return this.zeroAddressCreateTime;
    }

    public boolean isExpand() {
        return this.expand;
    }

    public boolean isNonHd() {
        return this.nonHd;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setBalance(BigDecimal bigDecimal) {
        this.balance = bigDecimal;
    }

    public void setExpand(boolean z) {
        this.expand = z;
    }

    public void setNonHd(boolean z) {
        this.nonHd = z;
    }

    public void setRelationshipZeroAddress(String str) {
        this.relationshipZeroAddress = str;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTxTotalNum(long j) {
        this.txTotalNum = j;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setUpgradeHdLists(List<UpgradeHdListBean> list) {
        this.upgradeHdLists = list;
    }

    public void setZeroAddressCreateTime(long j) {
        this.zeroAddressCreateTime = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof UpgradeHdBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UpgradeHdBean) {
            UpgradeHdBean upgradeHdBean = (UpgradeHdBean) obj;
            if (upgradeHdBean.canEqual(this) && isSelected() == upgradeHdBean.isSelected() && isExpand() == upgradeHdBean.isExpand() && getZeroAddressCreateTime() == upgradeHdBean.getZeroAddressCreateTime() && getTxTotalNum() == upgradeHdBean.getTxTotalNum() && isNonHd() == upgradeHdBean.isNonHd()) {
                String title = getTitle();
                String title2 = upgradeHdBean.getTitle();
                if (title != null ? title.equals(title2) : title2 == null) {
                    Type type = getType();
                    Type type2 = upgradeHdBean.getType();
                    if (type != null ? type.equals(type2) : type2 == null) {
                        BigDecimal balance = getBalance();
                        BigDecimal balance2 = upgradeHdBean.getBalance();
                        if (balance != null ? balance.equals(balance2) : balance2 == null) {
                            String relationshipZeroAddress = getRelationshipZeroAddress();
                            String relationshipZeroAddress2 = upgradeHdBean.getRelationshipZeroAddress();
                            if (relationshipZeroAddress != null ? relationshipZeroAddress.equals(relationshipZeroAddress2) : relationshipZeroAddress2 == null) {
                                List<UpgradeHdListBean> upgradeHdLists = getUpgradeHdLists();
                                List<UpgradeHdListBean> upgradeHdLists2 = upgradeHdBean.getUpgradeHdLists();
                                return upgradeHdLists != null ? upgradeHdLists.equals(upgradeHdLists2) : upgradeHdLists2 == null;
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
        int i = (((isSelected() ? 79 : 97) + 59) * 59) + (isExpand() ? 79 : 97);
        long zeroAddressCreateTime = getZeroAddressCreateTime();
        long txTotalNum = getTxTotalNum();
        int i2 = (((((i * 59) + ((int) (zeroAddressCreateTime ^ (zeroAddressCreateTime >>> 32)))) * 59) + ((int) (txTotalNum ^ (txTotalNum >>> 32)))) * 59) + (isNonHd() ? 79 : 97);
        String title = getTitle();
        int hashCode = (i2 * 59) + (title == null ? 43 : title.hashCode());
        Type type = getType();
        int hashCode2 = (hashCode * 59) + (type == null ? 43 : type.hashCode());
        BigDecimal balance = getBalance();
        int hashCode3 = (hashCode2 * 59) + (balance == null ? 43 : balance.hashCode());
        String relationshipZeroAddress = getRelationshipZeroAddress();
        int hashCode4 = (hashCode3 * 59) + (relationshipZeroAddress == null ? 43 : relationshipZeroAddress.hashCode());
        List<UpgradeHdListBean> upgradeHdLists = getUpgradeHdLists();
        return (hashCode4 * 59) + (upgradeHdLists != null ? upgradeHdLists.hashCode() : 43);
    }

    public String toString() {
        return "UpgradeHdBean(selected=" + isSelected() + ", expand=" + isExpand() + ", title=" + getTitle() + ", type=" + getType() + ", balance=" + getBalance() + ", relationshipZeroAddress=" + getRelationshipZeroAddress() + ", zeroAddressCreateTime=" + getZeroAddressCreateTime() + ", txTotalNum=" + getTxTotalNum() + ", nonHd=" + isNonHd() + ", upgradeHdLists=" + getUpgradeHdLists() + ")";
    }

    public UpgradeHdBean() {
        setType(Type.NULL);
        this.balance = BigDecimal.ZERO;
        this.upgradeHdLists = new ArrayList();
    }
}
