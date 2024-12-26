package com.tron.wallet.business.confirm.fg.bean;
public class ResourceConsumedBean {
    private double activeFee;
    private double availableBalance;
    private long bandWidthConsumed;
    private double currentFeeLimit;
    private long deductedBandwidth;
    private long deductedEnergy;
    private long energyConsumed;
    private long energyPenalty;
    private long energyUserConsumed;
    private long energyUserPenalty;
    private double memoFee;
    private double multiFee;
    private double specialContractFee;
    private String specialContractName;
    private long energyUserConsumedPercent = 100;
    private boolean isActive = true;

    public double getActiveFee() {
        return this.activeFee;
    }

    public double getAvailableBalance() {
        return this.availableBalance;
    }

    public long getBandWidthConsumed() {
        return this.bandWidthConsumed;
    }

    public double getCurrentFeeLimit() {
        return this.currentFeeLimit;
    }

    public long getDeductedBandwidth() {
        return this.deductedBandwidth;
    }

    public long getDeductedEnergy() {
        return this.deductedEnergy;
    }

    public long getEnergyConsumed() {
        return this.energyConsumed;
    }

    public long getEnergyCreatorConsumed() {
        return this.energyConsumed - this.energyUserConsumed;
    }

    public long getEnergyPenalty() {
        return this.energyPenalty;
    }

    public long getEnergyUserConsumed() {
        return this.energyUserConsumed;
    }

    public long getEnergyUserConsumedPercent() {
        return this.energyUserConsumedPercent;
    }

    public long getEnergyUserPenalty() {
        return this.energyUserPenalty;
    }

    public double getMemoFee() {
        return this.memoFee;
    }

    public double getMultiFee() {
        return this.multiFee;
    }

    public double getSpecialContractFee() {
        return this.specialContractFee;
    }

    public String getSpecialContractName() {
        return this.specialContractName;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean z) {
        this.isActive = z;
    }

    public void setActiveFee(double d) {
        this.activeFee = d;
    }

    public void setAvailableBalance(double d) {
        this.availableBalance = d;
    }

    public void setBandWidthConsumed(long j) {
        this.bandWidthConsumed = j;
    }

    public void setCurrentFeeLimit(double d) {
        this.currentFeeLimit = d;
    }

    public void setDeductedBandwidth(long j) {
        this.deductedBandwidth = j;
    }

    public void setDeductedEnergy(long j) {
        this.deductedEnergy = j;
    }

    public void setEnergyConsumed(long j) {
        this.energyConsumed = j;
    }

    public void setEnergyPenalty(long j) {
        this.energyPenalty = j;
    }

    public void setEnergyUserConsumed(long j) {
        this.energyUserConsumed = j;
    }

    public void setEnergyUserConsumedPercent(long j) {
        this.energyUserConsumedPercent = j;
    }

    public void setEnergyUserPenalty(long j) {
        this.energyUserPenalty = j;
    }

    public void setMemoFee(double d) {
        this.memoFee = d;
    }

    public void setMultiFee(double d) {
        this.multiFee = d;
    }

    public void setSpecialContractFee(double d) {
        this.specialContractFee = d;
    }

    public void setSpecialContractName(String str) {
        this.specialContractName = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ResourceConsumedBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ResourceConsumedBean) {
            ResourceConsumedBean resourceConsumedBean = (ResourceConsumedBean) obj;
            if (resourceConsumedBean.canEqual(this) && Double.compare(getAvailableBalance(), resourceConsumedBean.getAvailableBalance()) == 0 && getBandWidthConsumed() == resourceConsumedBean.getBandWidthConsumed() && getEnergyUserConsumedPercent() == resourceConsumedBean.getEnergyUserConsumedPercent() && getEnergyConsumed() == resourceConsumedBean.getEnergyConsumed() && getEnergyPenalty() == resourceConsumedBean.getEnergyPenalty() && getEnergyUserConsumed() == resourceConsumedBean.getEnergyUserConsumed() && getEnergyUserPenalty() == resourceConsumedBean.getEnergyUserPenalty() && isActive() == resourceConsumedBean.isActive() && getDeductedBandwidth() == resourceConsumedBean.getDeductedBandwidth() && getDeductedEnergy() == resourceConsumedBean.getDeductedEnergy() && Double.compare(getMultiFee(), resourceConsumedBean.getMultiFee()) == 0 && Double.compare(getActiveFee(), resourceConsumedBean.getActiveFee()) == 0 && Double.compare(getSpecialContractFee(), resourceConsumedBean.getSpecialContractFee()) == 0 && Double.compare(getMemoFee(), resourceConsumedBean.getMemoFee()) == 0 && Double.compare(getCurrentFeeLimit(), resourceConsumedBean.getCurrentFeeLimit()) == 0) {
                String specialContractName = getSpecialContractName();
                String specialContractName2 = resourceConsumedBean.getSpecialContractName();
                return specialContractName != null ? specialContractName.equals(specialContractName2) : specialContractName2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(getAvailableBalance());
        long bandWidthConsumed = getBandWidthConsumed();
        long energyUserConsumedPercent = getEnergyUserConsumedPercent();
        long energyConsumed = getEnergyConsumed();
        long energyPenalty = getEnergyPenalty();
        long energyUserConsumed = getEnergyUserConsumed();
        long energyUserPenalty = getEnergyUserPenalty();
        int i = ((((((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 59) * 59) + ((int) (bandWidthConsumed ^ (bandWidthConsumed >>> 32)))) * 59) + ((int) (energyUserConsumedPercent ^ (energyUserConsumedPercent >>> 32)))) * 59) + ((int) (energyConsumed ^ (energyConsumed >>> 32)))) * 59) + ((int) (energyPenalty ^ (energyPenalty >>> 32)))) * 59) + ((int) (energyUserConsumed ^ (energyUserConsumed >>> 32)))) * 59) + ((int) (energyUserPenalty ^ (energyUserPenalty >>> 32)))) * 59) + (isActive() ? 79 : 97);
        long deductedBandwidth = getDeductedBandwidth();
        long deductedEnergy = getDeductedEnergy();
        long doubleToLongBits2 = Double.doubleToLongBits(getMultiFee());
        long doubleToLongBits3 = Double.doubleToLongBits(getActiveFee());
        long doubleToLongBits4 = Double.doubleToLongBits(getSpecialContractFee());
        long doubleToLongBits5 = Double.doubleToLongBits(getMemoFee());
        long doubleToLongBits6 = Double.doubleToLongBits(getCurrentFeeLimit());
        String specialContractName = getSpecialContractName();
        return (((((((((((((((i * 59) + ((int) (deductedBandwidth ^ (deductedBandwidth >>> 32)))) * 59) + ((int) (deductedEnergy ^ (deductedEnergy >>> 32)))) * 59) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 59) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 59) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 59) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 59) + ((int) ((doubleToLongBits6 >>> 32) ^ doubleToLongBits6))) * 59) + (specialContractName == null ? 43 : specialContractName.hashCode());
    }

    public String toString() {
        return "ResourceConsumedBean(availableBalance=" + getAvailableBalance() + ", bandWidthConsumed=" + getBandWidthConsumed() + ", energyUserConsumedPercent=" + getEnergyUserConsumedPercent() + ", energyConsumed=" + getEnergyConsumed() + ", energyPenalty=" + getEnergyPenalty() + ", energyUserConsumed=" + getEnergyUserConsumed() + ", energyUserPenalty=" + getEnergyUserPenalty() + ", isActive=" + isActive() + ", deductedBandwidth=" + getDeductedBandwidth() + ", deductedEnergy=" + getDeductedEnergy() + ", multiFee=" + getMultiFee() + ", activeFee=" + getActiveFee() + ", specialContractName=" + getSpecialContractName() + ", specialContractFee=" + getSpecialContractFee() + ", memoFee=" + getMemoFee() + ", currentFeeLimit=" + getCurrentFeeLimit() + ")";
    }
}
