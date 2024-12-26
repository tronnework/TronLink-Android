package com.tron.wallet.business.stakev2.unstake;
public class UnStakeV2Bean {
    private long availableUnFreezeBandwidth;
    private long availableUnFreezeEnergy;
    private long bandwidthSum;
    private long energySum;
    private long maximum = 32;
    private long unAvailableUnFreezeBandwidth;
    private long unAvailableUnFreezeEnergy;
    private long votesSum;

    public long getAvailableUnFreezeBandwidth() {
        return this.availableUnFreezeBandwidth;
    }

    public long getAvailableUnFreezeEnergy() {
        return this.availableUnFreezeEnergy;
    }

    public long getBandwidthSum() {
        return this.bandwidthSum;
    }

    public long getEnergySum() {
        return this.energySum;
    }

    public long getMaximum() {
        return this.maximum;
    }

    public long getUnAvailableUnFreezeBandwidth() {
        return this.unAvailableUnFreezeBandwidth;
    }

    public long getUnAvailableUnFreezeEnergy() {
        return this.unAvailableUnFreezeEnergy;
    }

    public long getVotesSum() {
        return this.votesSum;
    }

    public void setAvailableUnFreezeBandwidth(long j) {
        this.availableUnFreezeBandwidth = j;
    }

    public void setAvailableUnFreezeEnergy(long j) {
        this.availableUnFreezeEnergy = j;
    }

    public void setBandwidthSum(long j) {
        this.bandwidthSum = j;
    }

    public void setEnergySum(long j) {
        this.energySum = j;
    }

    public void setMaximum(long j) {
        this.maximum = j;
    }

    public void setUnAvailableUnFreezeBandwidth(long j) {
        this.unAvailableUnFreezeBandwidth = j;
    }

    public void setUnAvailableUnFreezeEnergy(long j) {
        this.unAvailableUnFreezeEnergy = j;
    }

    public void setVotesSum(long j) {
        this.votesSum = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof UnStakeV2Bean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnStakeV2Bean) {
            UnStakeV2Bean unStakeV2Bean = (UnStakeV2Bean) obj;
            return unStakeV2Bean.canEqual(this) && getAvailableUnFreezeEnergy() == unStakeV2Bean.getAvailableUnFreezeEnergy() && getAvailableUnFreezeBandwidth() == unStakeV2Bean.getAvailableUnFreezeBandwidth() && getVotesSum() == unStakeV2Bean.getVotesSum() && getEnergySum() == unStakeV2Bean.getEnergySum() && getBandwidthSum() == unStakeV2Bean.getBandwidthSum() && getUnAvailableUnFreezeEnergy() == unStakeV2Bean.getUnAvailableUnFreezeEnergy() && getUnAvailableUnFreezeBandwidth() == unStakeV2Bean.getUnAvailableUnFreezeBandwidth() && getMaximum() == unStakeV2Bean.getMaximum();
        }
        return false;
    }

    public int hashCode() {
        long availableUnFreezeEnergy = getAvailableUnFreezeEnergy();
        long availableUnFreezeBandwidth = getAvailableUnFreezeBandwidth();
        long votesSum = getVotesSum();
        long energySum = getEnergySum();
        long bandwidthSum = getBandwidthSum();
        long unAvailableUnFreezeEnergy = getUnAvailableUnFreezeEnergy();
        long unAvailableUnFreezeBandwidth = getUnAvailableUnFreezeBandwidth();
        long maximum = getMaximum();
        return ((((((((((((((((int) (availableUnFreezeEnergy ^ (availableUnFreezeEnergy >>> 32))) + 59) * 59) + ((int) (availableUnFreezeBandwidth ^ (availableUnFreezeBandwidth >>> 32)))) * 59) + ((int) (votesSum ^ (votesSum >>> 32)))) * 59) + ((int) (energySum ^ (energySum >>> 32)))) * 59) + ((int) (bandwidthSum ^ (bandwidthSum >>> 32)))) * 59) + ((int) (unAvailableUnFreezeEnergy ^ (unAvailableUnFreezeEnergy >>> 32)))) * 59) + ((int) (unAvailableUnFreezeBandwidth ^ (unAvailableUnFreezeBandwidth >>> 32)))) * 59) + ((int) ((maximum >>> 32) ^ maximum));
    }

    public String toString() {
        return "UnStakeV2Bean(availableUnFreezeEnergy=" + getAvailableUnFreezeEnergy() + ", availableUnFreezeBandwidth=" + getAvailableUnFreezeBandwidth() + ", votesSum=" + getVotesSum() + ", energySum=" + getEnergySum() + ", bandwidthSum=" + getBandwidthSum() + ", unAvailableUnFreezeEnergy=" + getUnAvailableUnFreezeEnergy() + ", unAvailableUnFreezeBandwidth=" + getUnAvailableUnFreezeBandwidth() + ", maximum=" + getMaximum() + ")";
    }
}
