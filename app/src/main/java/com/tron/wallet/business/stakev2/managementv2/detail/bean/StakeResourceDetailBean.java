package com.tron.wallet.business.stakev2.managementv2.detail.bean;
public class StakeResourceDetailBean {
    private String accountAddress;
    private String balance;
    private long expireTime;
    private String receiverAddress;
    private int resource;
    private String resourceBalance;
    private String unusedBalance;
    private String usedBalance;

    public String getAccountAddress() {
        return this.accountAddress;
    }

    public String getBalance() {
        return this.balance;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public int getResource() {
        return this.resource;
    }

    public String getResourceBalance() {
        return this.resourceBalance;
    }

    public String getUnusedBalance() {
        return this.unusedBalance;
    }

    public String getUsedBalance() {
        return this.usedBalance;
    }

    public void setAccountAddress(String str) {
        this.accountAddress = str;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public void setReceiverAddress(String str) {
        this.receiverAddress = str;
    }

    public void setResource(int i) {
        this.resource = i;
    }

    public void setResourceBalance(String str) {
        this.resourceBalance = str;
    }

    public void setUnusedBalance(String str) {
        this.unusedBalance = str;
    }

    public void setUsedBalance(String str) {
        this.usedBalance = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StakeResourceDetailBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeResourceDetailBean) {
            StakeResourceDetailBean stakeResourceDetailBean = (StakeResourceDetailBean) obj;
            if (stakeResourceDetailBean.canEqual(this) && getResource() == stakeResourceDetailBean.getResource() && getExpireTime() == stakeResourceDetailBean.getExpireTime()) {
                String accountAddress = getAccountAddress();
                String accountAddress2 = stakeResourceDetailBean.getAccountAddress();
                if (accountAddress != null ? accountAddress.equals(accountAddress2) : accountAddress2 == null) {
                    String receiverAddress = getReceiverAddress();
                    String receiverAddress2 = stakeResourceDetailBean.getReceiverAddress();
                    if (receiverAddress != null ? receiverAddress.equals(receiverAddress2) : receiverAddress2 == null) {
                        String balance = getBalance();
                        String balance2 = stakeResourceDetailBean.getBalance();
                        if (balance != null ? balance.equals(balance2) : balance2 == null) {
                            String usedBalance = getUsedBalance();
                            String usedBalance2 = stakeResourceDetailBean.getUsedBalance();
                            if (usedBalance != null ? usedBalance.equals(usedBalance2) : usedBalance2 == null) {
                                String unusedBalance = getUnusedBalance();
                                String unusedBalance2 = stakeResourceDetailBean.getUnusedBalance();
                                if (unusedBalance != null ? unusedBalance.equals(unusedBalance2) : unusedBalance2 == null) {
                                    String resourceBalance = getResourceBalance();
                                    String resourceBalance2 = stakeResourceDetailBean.getResourceBalance();
                                    return resourceBalance != null ? resourceBalance.equals(resourceBalance2) : resourceBalance2 == null;
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
        long expireTime = getExpireTime();
        String accountAddress = getAccountAddress();
        int resource = ((((getResource() + 59) * 59) + ((int) (expireTime ^ (expireTime >>> 32)))) * 59) + (accountAddress == null ? 43 : accountAddress.hashCode());
        String receiverAddress = getReceiverAddress();
        int hashCode = (resource * 59) + (receiverAddress == null ? 43 : receiverAddress.hashCode());
        String balance = getBalance();
        int hashCode2 = (hashCode * 59) + (balance == null ? 43 : balance.hashCode());
        String usedBalance = getUsedBalance();
        int hashCode3 = (hashCode2 * 59) + (usedBalance == null ? 43 : usedBalance.hashCode());
        String unusedBalance = getUnusedBalance();
        int hashCode4 = (hashCode3 * 59) + (unusedBalance == null ? 43 : unusedBalance.hashCode());
        String resourceBalance = getResourceBalance();
        return (hashCode4 * 59) + (resourceBalance != null ? resourceBalance.hashCode() : 43);
    }

    public String toString() {
        return "StakeResourceDetailBean(accountAddress=" + getAccountAddress() + ", resource=" + getResource() + ", receiverAddress=" + getReceiverAddress() + ", balance=" + getBalance() + ", expireTime=" + getExpireTime() + ", usedBalance=" + getUsedBalance() + ", unusedBalance=" + getUnusedBalance() + ", resourceBalance=" + getResourceBalance() + ")";
    }
}
