package com.tron.wallet.business.stakev2.managementv2.detail.bean;
public class StakeResourceDetailForMeBean {
    private String accountAddress;
    private int accountType;
    private long blockNum;
    private String fromAccount;
    private String fromBalance;
    private String fromResourceBalance;
    private int resource;

    public String getAccountAddress() {
        return this.accountAddress;
    }

    public int getAccountType() {
        return this.accountType;
    }

    public long getBlockNum() {
        return this.blockNum;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public String getFromBalance() {
        return this.fromBalance;
    }

    public String getFromResourceBalance() {
        return this.fromResourceBalance;
    }

    public int getResource() {
        return this.resource;
    }

    public void setAccountAddress(String str) {
        this.accountAddress = str;
    }

    public void setAccountType(int i) {
        this.accountType = i;
    }

    public void setBlockNum(long j) {
        this.blockNum = j;
    }

    public void setFromAccount(String str) {
        this.fromAccount = str;
    }

    public void setFromBalance(String str) {
        this.fromBalance = str;
    }

    public void setFromResourceBalance(String str) {
        this.fromResourceBalance = str;
    }

    public void setResource(int i) {
        this.resource = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StakeResourceDetailForMeBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeResourceDetailForMeBean) {
            StakeResourceDetailForMeBean stakeResourceDetailForMeBean = (StakeResourceDetailForMeBean) obj;
            if (stakeResourceDetailForMeBean.canEqual(this) && getResource() == stakeResourceDetailForMeBean.getResource() && getAccountType() == stakeResourceDetailForMeBean.getAccountType() && getBlockNum() == stakeResourceDetailForMeBean.getBlockNum()) {
                String accountAddress = getAccountAddress();
                String accountAddress2 = stakeResourceDetailForMeBean.getAccountAddress();
                if (accountAddress != null ? accountAddress.equals(accountAddress2) : accountAddress2 == null) {
                    String fromAccount = getFromAccount();
                    String fromAccount2 = stakeResourceDetailForMeBean.getFromAccount();
                    if (fromAccount != null ? fromAccount.equals(fromAccount2) : fromAccount2 == null) {
                        String fromBalance = getFromBalance();
                        String fromBalance2 = stakeResourceDetailForMeBean.getFromBalance();
                        if (fromBalance != null ? fromBalance.equals(fromBalance2) : fromBalance2 == null) {
                            String fromResourceBalance = getFromResourceBalance();
                            String fromResourceBalance2 = stakeResourceDetailForMeBean.getFromResourceBalance();
                            return fromResourceBalance != null ? fromResourceBalance.equals(fromResourceBalance2) : fromResourceBalance2 == null;
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
        int resource = ((getResource() + 59) * 59) + getAccountType();
        long blockNum = getBlockNum();
        String accountAddress = getAccountAddress();
        int hashCode = (((resource * 59) + ((int) (blockNum ^ (blockNum >>> 32)))) * 59) + (accountAddress == null ? 43 : accountAddress.hashCode());
        String fromAccount = getFromAccount();
        int hashCode2 = (hashCode * 59) + (fromAccount == null ? 43 : fromAccount.hashCode());
        String fromBalance = getFromBalance();
        int hashCode3 = (hashCode2 * 59) + (fromBalance == null ? 43 : fromBalance.hashCode());
        String fromResourceBalance = getFromResourceBalance();
        return (hashCode3 * 59) + (fromResourceBalance != null ? fromResourceBalance.hashCode() : 43);
    }

    public String toString() {
        return "StakeResourceDetailForMeBean(accountAddress=" + getAccountAddress() + ", resource=" + getResource() + ", accountType=" + getAccountType() + ", blockNum=" + getBlockNum() + ", fromAccount=" + getFromAccount() + ", fromBalance=" + getFromBalance() + ", fromResourceBalance=" + getFromResourceBalance() + ")";
    }
}
