package com.tron.wallet.business.walletmanager.selectwallet.bean;

import android.text.SpannableString;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectWalletBean {
    private long createTime;
    private WalletGroupType groupType;
    private int hdIndex;
    private HdTronRelationshipBean hdTronRelationshipBean;
    private boolean isHdGroup;
    private Priority priority;
    private List<SelectWalletBean> relationWallets;
    private SpannableString searchedString;
    private boolean selected;
    private Wallet wallet;
    private double balance = -1.0d;
    private boolean updating = true;
    private boolean updateResult = true;
    private SearchedTarget searchedTarget = SearchedTarget.NONE;
    private int accountType = 0;

    public enum SearchedTarget {
        NONE,
        NAME,
        Address
    }

    public int getAccountType() {
        return this.accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public WalletGroupType getGroupType() {
        return this.groupType;
    }

    public int getHdIndex() {
        return this.hdIndex;
    }

    public HdTronRelationshipBean getHdTronRelationshipBean() {
        return this.hdTronRelationshipBean;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public List<SelectWalletBean> getRelationWallets() {
        return this.relationWallets;
    }

    public SpannableString getSearchedString() {
        return this.searchedString;
    }

    public SearchedTarget getSearchedTarget() {
        return this.searchedTarget;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public boolean isHdGroup() {
        return this.isHdGroup;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public boolean isUpdateResult() {
        return this.updateResult;
    }

    public boolean isUpdating() {
        return this.updating;
    }

    public void setAccountType(int i) {
        this.accountType = i;
    }

    public void setBalance(double d) {
        this.balance = d;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setGroupType(WalletGroupType walletGroupType) {
        this.groupType = walletGroupType;
    }

    public void setHdGroup(boolean z) {
        this.isHdGroup = z;
    }

    public void setHdIndex(int i) {
        this.hdIndex = i;
    }

    public void setHdTronRelationshipBean(HdTronRelationshipBean hdTronRelationshipBean) {
        this.hdTronRelationshipBean = hdTronRelationshipBean;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setRelationWallets(List<SelectWalletBean> list) {
        this.relationWallets = list;
    }

    public void setSearchedString(SpannableString spannableString) {
        this.searchedString = spannableString;
    }

    public void setSearchedTarget(SearchedTarget searchedTarget) {
        this.searchedTarget = searchedTarget;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setUpdateResult(boolean z) {
        this.updateResult = z;
    }

    public void setUpdating(boolean z) {
        this.updating = z;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SelectWalletBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SelectWalletBean) {
            SelectWalletBean selectWalletBean = (SelectWalletBean) obj;
            if (selectWalletBean.canEqual(this) && isSelected() == selectWalletBean.isSelected() && Double.compare(getBalance(), selectWalletBean.getBalance()) == 0 && isUpdating() == selectWalletBean.isUpdating() && isUpdateResult() == selectWalletBean.isUpdateResult() && getAccountType() == selectWalletBean.getAccountType() && getCreateTime() == selectWalletBean.getCreateTime() && isHdGroup() == selectWalletBean.isHdGroup() && getHdIndex() == selectWalletBean.getHdIndex()) {
                WalletGroupType groupType = getGroupType();
                WalletGroupType groupType2 = selectWalletBean.getGroupType();
                if (groupType != null ? groupType.equals(groupType2) : groupType2 == null) {
                    Wallet wallet = getWallet();
                    Wallet wallet2 = selectWalletBean.getWallet();
                    if (wallet != null ? wallet.equals(wallet2) : wallet2 == null) {
                        Priority priority = getPriority();
                        Priority priority2 = selectWalletBean.getPriority();
                        if (priority != null ? priority.equals(priority2) : priority2 == null) {
                            SpannableString searchedString = getSearchedString();
                            SpannableString searchedString2 = selectWalletBean.getSearchedString();
                            if (searchedString != null ? searchedString.equals(searchedString2) : searchedString2 == null) {
                                SearchedTarget searchedTarget = getSearchedTarget();
                                SearchedTarget searchedTarget2 = selectWalletBean.getSearchedTarget();
                                if (searchedTarget != null ? searchedTarget.equals(searchedTarget2) : searchedTarget2 == null) {
                                    HdTronRelationshipBean hdTronRelationshipBean = getHdTronRelationshipBean();
                                    HdTronRelationshipBean hdTronRelationshipBean2 = selectWalletBean.getHdTronRelationshipBean();
                                    if (hdTronRelationshipBean != null ? hdTronRelationshipBean.equals(hdTronRelationshipBean2) : hdTronRelationshipBean2 == null) {
                                        List<SelectWalletBean> relationWallets = getRelationWallets();
                                        List<SelectWalletBean> relationWallets2 = selectWalletBean.getRelationWallets();
                                        return relationWallets != null ? relationWallets.equals(relationWallets2) : relationWallets2 == null;
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
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = isSelected() ? 79 : 97;
        long doubleToLongBits = Double.doubleToLongBits(getBalance());
        int accountType = ((((((((i + 59) * 59) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 59) + (isUpdating() ? 79 : 97)) * 59) + (isUpdateResult() ? 79 : 97)) * 59) + getAccountType();
        long createTime = getCreateTime();
        int hdIndex = (((((accountType * 59) + ((int) (createTime ^ (createTime >>> 32)))) * 59) + (isHdGroup() ? 79 : 97)) * 59) + getHdIndex();
        WalletGroupType groupType = getGroupType();
        int hashCode = (hdIndex * 59) + (groupType == null ? 43 : groupType.hashCode());
        Wallet wallet = getWallet();
        int hashCode2 = (hashCode * 59) + (wallet == null ? 43 : wallet.hashCode());
        Priority priority = getPriority();
        int hashCode3 = (hashCode2 * 59) + (priority == null ? 43 : priority.hashCode());
        SpannableString searchedString = getSearchedString();
        int hashCode4 = (hashCode3 * 59) + (searchedString == null ? 43 : searchedString.hashCode());
        SearchedTarget searchedTarget = getSearchedTarget();
        int hashCode5 = (hashCode4 * 59) + (searchedTarget == null ? 43 : searchedTarget.hashCode());
        HdTronRelationshipBean hdTronRelationshipBean = getHdTronRelationshipBean();
        int hashCode6 = (hashCode5 * 59) + (hdTronRelationshipBean == null ? 43 : hdTronRelationshipBean.hashCode());
        List<SelectWalletBean> relationWallets = getRelationWallets();
        return (hashCode6 * 59) + (relationWallets != null ? relationWallets.hashCode() : 43);
    }

    public String toString() {
        return "SelectWalletBean(groupType=" + getGroupType() + ", wallet=" + getWallet() + ", selected=" + isSelected() + ", balance=" + getBalance() + ", updating=" + isUpdating() + ", updateResult=" + isUpdateResult() + ", priority=" + getPriority() + ", searchedString=" + ((Object) getSearchedString()) + ", searchedTarget=" + getSearchedTarget() + ", accountType=" + getAccountType() + ", createTime=" + getCreateTime() + ", isHdGroup=" + isHdGroup() + ", hdIndex=" + getHdIndex() + ", hdTronRelationshipBean=" + getHdTronRelationshipBean() + ", relationWallets=" + getRelationWallets() + ")";
    }

    public void setRelationWalletBean(SelectWalletBean selectWalletBean) {
        if (this.relationWallets == null) {
            this.relationWallets = new ArrayList();
        }
        this.relationWallets.add(selectWalletBean);
    }

    public enum Priority {
        NONE(0),
        LOW(1),
        MIDDLE(2),
        HIGH(3),
        ADDRESS_MATCH_PART_AND_WATCH(4),
        NAME_MATCH_PART_AND_WATCH(5),
        ADDRESS_MATCH_PART_AND_NOT_WATCH(6),
        NAME_MATCH_PART_AND_NOT_WATCH(7),
        NAME_MATCH_ALL(8),
        ADDRESS_MATCH_ALL(9);
        
        private int value;

        public int getValue() {
            return this.value;
        }

        Priority(int i) {
            this.value = i;
        }
    }
}
