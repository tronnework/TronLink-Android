package com.tron.wallet.business.confirm.fg.bean;
public class ConstContractEnergyBean {
    public static final int HOT_CONTRACT_CREATOR_ENERGY_FACTOR = 20;
    public static final int NORMAL_CONTRACT_CREATOR_ENERGY_FACTOR = 3;
    private String contactAddress;
    private long creatorEnergyLimit;
    private long creatorEnergyUsable;
    private long energyPenalty;
    private long energyUsed;
    private long userPercent = 100;
    private boolean isHotContract = true;

    public String getContactAddress() {
        return this.contactAddress;
    }

    public long getCreatorEnergyLimit() {
        return this.creatorEnergyLimit;
    }

    public long getCreatorEnergyUsable() {
        return this.creatorEnergyUsable;
    }

    public long getEnergyPenalty() {
        return this.energyPenalty;
    }

    public long getEnergyUsed() {
        return this.energyUsed;
    }

    public long getUserPercent() {
        return this.userPercent;
    }

    public boolean isHotContract() {
        if (this.energyPenalty > 0) {
            return true;
        }
        return this.isHotContract;
    }

    public void setContactAddress(String str) {
        this.contactAddress = str;
    }

    public void setCreatorEnergyLimit(long j) {
        this.creatorEnergyLimit = j;
    }

    public void setCreatorEnergyUsable(long j) {
        this.creatorEnergyUsable = j;
    }

    public void setEnergyPenalty(long j) {
        this.energyPenalty = j;
    }

    public void setEnergyUsed(long j) {
        this.energyUsed = j;
    }

    public void setHotContract(boolean z) {
        this.isHotContract = z;
    }

    public void setUserPercent(long j) {
        this.userPercent = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ConstContractEnergyBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ConstContractEnergyBean) {
            ConstContractEnergyBean constContractEnergyBean = (ConstContractEnergyBean) obj;
            if (constContractEnergyBean.canEqual(this) && getEnergyUsed() == constContractEnergyBean.getEnergyUsed() && getEnergyPenalty() == constContractEnergyBean.getEnergyPenalty() && getUserPercent() == constContractEnergyBean.getUserPercent() && getCreatorEnergyLimit() == constContractEnergyBean.getCreatorEnergyLimit() && getCreatorEnergyUsable() == constContractEnergyBean.getCreatorEnergyUsable() && isHotContract() == constContractEnergyBean.isHotContract()) {
                String contactAddress = getContactAddress();
                String contactAddress2 = constContractEnergyBean.getContactAddress();
                return contactAddress != null ? contactAddress.equals(contactAddress2) : contactAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long energyUsed = getEnergyUsed();
        long energyPenalty = getEnergyPenalty();
        long userPercent = getUserPercent();
        long creatorEnergyLimit = getCreatorEnergyLimit();
        long creatorEnergyUsable = getCreatorEnergyUsable();
        int i = ((((((((((((int) (energyUsed ^ (energyUsed >>> 32))) + 59) * 59) + ((int) (energyPenalty ^ (energyPenalty >>> 32)))) * 59) + ((int) (userPercent ^ (userPercent >>> 32)))) * 59) + ((int) (creatorEnergyLimit ^ (creatorEnergyLimit >>> 32)))) * 59) + ((int) ((creatorEnergyUsable >>> 32) ^ creatorEnergyUsable))) * 59) + (isHotContract() ? 79 : 97);
        String contactAddress = getContactAddress();
        return (i * 59) + (contactAddress == null ? 43 : contactAddress.hashCode());
    }

    public String toString() {
        return "ConstContractEnergyBean(contactAddress=" + getContactAddress() + ", energyUsed=" + getEnergyUsed() + ", energyPenalty=" + getEnergyPenalty() + ", userPercent=" + getUserPercent() + ", creatorEnergyLimit=" + getCreatorEnergyLimit() + ", creatorEnergyUsable=" + getCreatorEnergyUsable() + ", isHotContract=" + isHotContract() + ")";
    }
}
