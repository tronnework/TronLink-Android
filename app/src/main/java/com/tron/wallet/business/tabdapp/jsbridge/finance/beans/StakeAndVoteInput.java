package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;
public class StakeAndVoteInput {
    private long freezeBalance;
    private long resource;
    private int resourceCode;
    private int test;

    public long getFreezeBalance() {
        return this.freezeBalance;
    }

    public long getResource() {
        return this.resource;
    }

    public int getResourceCode() {
        return this.resourceCode;
    }

    public int getTest() {
        return this.test;
    }

    public void setFreezeBalance(long j) {
        this.freezeBalance = j;
    }

    public void setResource(long j) {
        this.resource = j;
    }

    public void setResourceCode(int i) {
        this.resourceCode = i;
    }

    public void setTest(int i) {
        this.test = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StakeAndVoteInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeAndVoteInput) {
            StakeAndVoteInput stakeAndVoteInput = (StakeAndVoteInput) obj;
            return stakeAndVoteInput.canEqual(this) && getTest() == stakeAndVoteInput.getTest() && getFreezeBalance() == stakeAndVoteInput.getFreezeBalance() && getResourceCode() == stakeAndVoteInput.getResourceCode() && getResource() == stakeAndVoteInput.getResource();
        }
        return false;
    }

    public int hashCode() {
        long freezeBalance = getFreezeBalance();
        int test = ((((getTest() + 59) * 59) + ((int) (freezeBalance ^ (freezeBalance >>> 32)))) * 59) + getResourceCode();
        long resource = getResource();
        return (test * 59) + ((int) ((resource >>> 32) ^ resource));
    }

    public String toString() {
        return "StakeAndVoteInput(test=" + getTest() + ", freezeBalance=" + getFreezeBalance() + ", resourceCode=" + getResourceCode() + ", resource=" + getResource() + ")";
    }
}
