package com.tron.wallet.business.stakev2.managementv2;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.stakev2.stake.pop.unfreezing.UnFreezingResourceBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
public class ResourcesBean implements Serializable {
    private static final long TRX_DECIMAL = 1000000;
    private static final long serialVersionUID = 582944013376402909L;
    private long availableVotingTps;
    private long bandwidthDelegatedToOther;
    private long bandwidthDeletedAvailable;
    private long bandwidthDeletedToOthersTrxV2;
    private long bandwidthForOthersV1;
    private long bandwidthFree;
    private long bandwidthFromOtherByStakeV1;
    private long bandwidthFromOtherByStakeV2;
    private long bandwidthFromOtherTotal;
    private long bandwidthFromOthersTrxV1;
    private long bandwidthFromOthersTrxV2;
    private long bandwidthFromSelfByStakeV1;
    private long bandwidthFromSelfByStakeV2;
    private long bandwidthFromSelfTotal;
    private long bandwidthFromSelfTrxV1;
    private long bandwidthFromSelfTrxV2;
    private long bandwidthTotal;
    private long bandwidthUsable;
    private long energyDelegatedToOther;
    private long energyDeletedAvailable;
    private long energyDeletedToOthersTrxV2;
    private long energyForOthersV1;
    private long energyFromOtherByStakeV1;
    private long energyFromOtherByStakeV2;
    private long energyFromOtherTotal;
    private long energyFromOthersTrxV1;
    private long energyFromOthersTrxV2;
    private long energyFromSelfByStakeV1;
    private long energyFromSelfByStakeV2;
    private long energyFromSelfTotal;
    private long energyFromSelfTrxV1;
    private long energyFromSelfTrxV2;
    private long energyTotal;
    private long energyUsable;
    private boolean isValid = true;
    private long stakedSun;
    private long stakedTrx;
    private double trxBalance;
    private ArrayList<UnFreezingResourceBean> unFreezingList;
    private long unFreezingTrx;
    private long votingTps;
    private long withDrawAvailableTrx;

    public long getAvailableVotingTps() {
        return this.availableVotingTps;
    }

    public long getBandwidthDelegatedToOther() {
        return this.bandwidthDelegatedToOther;
    }

    public long getBandwidthDeletedAvailable() {
        return this.bandwidthDeletedAvailable;
    }

    public long getBandwidthForOthersV1() {
        return this.bandwidthForOthersV1;
    }

    public long getBandwidthFree() {
        return this.bandwidthFree;
    }

    public long getBandwidthFromOtherByStakeV1() {
        return this.bandwidthFromOtherByStakeV1;
    }

    public long getBandwidthFromOtherByStakeV2() {
        return this.bandwidthFromOtherByStakeV2;
    }

    public long getBandwidthFromOtherTotal() {
        return this.bandwidthFromOtherTotal;
    }

    public long getBandwidthFromSelfByStakeV1() {
        return this.bandwidthFromSelfByStakeV1;
    }

    public long getBandwidthFromSelfByStakeV2() {
        return this.bandwidthFromSelfByStakeV2;
    }

    public long getBandwidthFromSelfTotal() {
        return this.bandwidthFromSelfTotal;
    }

    public long getBandwidthTotal() {
        return this.bandwidthTotal;
    }

    public long getBandwidthUsable() {
        return this.bandwidthUsable;
    }

    public long getEnergyDelegatedToOther() {
        return this.energyDelegatedToOther;
    }

    public long getEnergyDeletedAvailable() {
        return this.energyDeletedAvailable;
    }

    public long getEnergyForOthersV1() {
        return this.energyForOthersV1;
    }

    public long getEnergyFromOtherByStakeV1() {
        return this.energyFromOtherByStakeV1;
    }

    public long getEnergyFromOtherByStakeV2() {
        return this.energyFromOtherByStakeV2;
    }

    public long getEnergyFromOtherTotal() {
        return this.energyFromOtherTotal;
    }

    public long getEnergyFromSelfByStakeV1() {
        return this.energyFromSelfByStakeV1;
    }

    public long getEnergyFromSelfByStakeV2() {
        return this.energyFromSelfByStakeV2;
    }

    public long getEnergyFromSelfTotal() {
        return this.energyFromSelfTotal;
    }

    public long getEnergyTotal() {
        return this.energyTotal;
    }

    public long getEnergyUsable() {
        return this.energyUsable;
    }

    public long getStakedSun() {
        return this.stakedSun;
    }

    public long getStakedTrx() {
        return this.stakedTrx;
    }

    public double getTrxBalance() {
        return this.trxBalance;
    }

    public ArrayList<UnFreezingResourceBean> getUnFreezingList() {
        return this.unFreezingList;
    }

    public long getVotingTps() {
        return this.votingTps;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setAvailableVotingTps(long j) {
        this.availableVotingTps = j;
    }

    public void setBandwidthDelegatedToOther(long j) {
        this.bandwidthDelegatedToOther = j;
    }

    public void setBandwidthDeletedAvailable(long j) {
        this.bandwidthDeletedAvailable = j;
    }

    public void setBandwidthDeletedToOthersTrxV2(long j) {
        this.bandwidthDeletedToOthersTrxV2 = j;
    }

    public void setBandwidthForOthersV1(long j) {
        this.bandwidthForOthersV1 = j;
    }

    public void setBandwidthFree(long j) {
        this.bandwidthFree = j;
    }

    public void setBandwidthFromOtherByStakeV1(long j) {
        this.bandwidthFromOtherByStakeV1 = j;
    }

    public void setBandwidthFromOtherByStakeV2(long j) {
        this.bandwidthFromOtherByStakeV2 = j;
    }

    public void setBandwidthFromOtherTotal(long j) {
        this.bandwidthFromOtherTotal = j;
    }

    public void setBandwidthFromOthersTrxV1(long j) {
        this.bandwidthFromOthersTrxV1 = j;
    }

    public void setBandwidthFromOthersTrxV2(long j) {
        this.bandwidthFromOthersTrxV2 = j;
    }

    public void setBandwidthFromSelfByStakeV1(long j) {
        this.bandwidthFromSelfByStakeV1 = j;
    }

    public void setBandwidthFromSelfByStakeV2(long j) {
        this.bandwidthFromSelfByStakeV2 = j;
    }

    public void setBandwidthFromSelfTotal(long j) {
        this.bandwidthFromSelfTotal = j;
    }

    public void setBandwidthFromSelfTrxV1(long j) {
        this.bandwidthFromSelfTrxV1 = j;
    }

    public void setBandwidthFromSelfTrxV2(long j) {
        this.bandwidthFromSelfTrxV2 = j;
    }

    public void setBandwidthTotal(long j) {
        this.bandwidthTotal = j;
    }

    public void setBandwidthUsable(long j) {
        this.bandwidthUsable = j;
    }

    public void setEnergyDelegatedToOther(long j) {
        this.energyDelegatedToOther = j;
    }

    public void setEnergyDeletedAvailable(long j) {
        this.energyDeletedAvailable = j;
    }

    public void setEnergyDeletedToOthersTrxV2(long j) {
        this.energyDeletedToOthersTrxV2 = j;
    }

    public void setEnergyForOthersV1(long j) {
        this.energyForOthersV1 = j;
    }

    public void setEnergyFromOtherByStakeV1(long j) {
        this.energyFromOtherByStakeV1 = j;
    }

    public void setEnergyFromOtherByStakeV2(long j) {
        this.energyFromOtherByStakeV2 = j;
    }

    public void setEnergyFromOtherTotal(long j) {
        this.energyFromOtherTotal = j;
    }

    public void setEnergyFromOthersTrxV1(long j) {
        this.energyFromOthersTrxV1 = j;
    }

    public void setEnergyFromOthersTrxV2(long j) {
        this.energyFromOthersTrxV2 = j;
    }

    public void setEnergyFromSelfByStakeV1(long j) {
        this.energyFromSelfByStakeV1 = j;
    }

    public void setEnergyFromSelfByStakeV2(long j) {
        this.energyFromSelfByStakeV2 = j;
    }

    public void setEnergyFromSelfTotal(long j) {
        this.energyFromSelfTotal = j;
    }

    public void setEnergyFromSelfTrxV1(long j) {
        this.energyFromSelfTrxV1 = j;
    }

    public void setEnergyFromSelfTrxV2(long j) {
        this.energyFromSelfTrxV2 = j;
    }

    public void setEnergyTotal(long j) {
        this.energyTotal = j;
    }

    public void setEnergyUsable(long j) {
        this.energyUsable = j;
    }

    public void setStakedSun(long j) {
        this.stakedSun = j;
    }

    public void setStakedTrx(long j) {
        this.stakedTrx = j;
    }

    public void setTrxBalance(double d) {
        this.trxBalance = d;
    }

    public void setUnFreezingList(ArrayList<UnFreezingResourceBean> arrayList) {
        this.unFreezingList = arrayList;
    }

    public void setUnFreezingTrx(long j) {
        this.unFreezingTrx = j;
    }

    public void setValid(boolean z) {
        this.isValid = z;
    }

    public void setVotingTps(long j) {
        this.votingTps = j;
    }

    public void setWithDrawAvailableTrx(long j) {
        this.withDrawAvailableTrx = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ResourcesBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ResourcesBean) {
            ResourcesBean resourcesBean = (ResourcesBean) obj;
            if (resourcesBean.canEqual(this) && isValid() == resourcesBean.isValid() && Double.compare(getTrxBalance(), resourcesBean.getTrxBalance()) == 0 && getStakedTrx() == resourcesBean.getStakedTrx() && getStakedSun() == resourcesBean.getStakedSun() && getBandwidthFromOthersTrxV1() == resourcesBean.getBandwidthFromOthersTrxV1() && getEnergyFromSelfTrxV1() == resourcesBean.getEnergyFromSelfTrxV1() && getBandwidthFromSelfTrxV1() == resourcesBean.getBandwidthFromSelfTrxV1() && getEnergyFromOthersTrxV1() == resourcesBean.getEnergyFromOthersTrxV1() && getBandwidthForOthersV1() == resourcesBean.getBandwidthForOthersV1() && getEnergyForOthersV1() == resourcesBean.getEnergyForOthersV1() && getEnergyFromSelfTrxV2() == resourcesBean.getEnergyFromSelfTrxV2() && getEnergyFromOthersTrxV2() == resourcesBean.getEnergyFromOthersTrxV2() && getEnergyDeletedToOthersTrxV2() == resourcesBean.getEnergyDeletedToOthersTrxV2() && getBandwidthFromSelfTrxV2() == resourcesBean.getBandwidthFromSelfTrxV2() && getBandwidthFromOthersTrxV2() == resourcesBean.getBandwidthFromOthersTrxV2() && getBandwidthDeletedToOthersTrxV2() == resourcesBean.getBandwidthDeletedToOthersTrxV2() && getEnergyTotal() == resourcesBean.getEnergyTotal() && getEnergyUsable() == resourcesBean.getEnergyUsable() && getBandwidthTotal() == resourcesBean.getBandwidthTotal() && getBandwidthUsable() == resourcesBean.getBandwidthUsable() && getBandwidthFree() == resourcesBean.getBandwidthFree() && getVotingTps() == resourcesBean.getVotingTps() && getAvailableVotingTps() == resourcesBean.getAvailableVotingTps() && getUnFreezingTrx() == resourcesBean.getUnFreezingTrx() && getWithDrawAvailableTrx() == resourcesBean.getWithDrawAvailableTrx() && getEnergyFromSelfTotal() == resourcesBean.getEnergyFromSelfTotal() && getEnergyFromSelfByStakeV1() == resourcesBean.getEnergyFromSelfByStakeV1() && getEnergyFromSelfByStakeV2() == resourcesBean.getEnergyFromSelfByStakeV2() && getEnergyDelegatedToOther() == resourcesBean.getEnergyDelegatedToOther() && getEnergyFromOtherTotal() == resourcesBean.getEnergyFromOtherTotal() && getEnergyFromOtherByStakeV1() == resourcesBean.getEnergyFromOtherByStakeV1() && getEnergyFromOtherByStakeV2() == resourcesBean.getEnergyFromOtherByStakeV2() && getEnergyDeletedAvailable() == resourcesBean.getEnergyDeletedAvailable() && getBandwidthFromSelfTotal() == resourcesBean.getBandwidthFromSelfTotal() && getBandwidthFromSelfByStakeV1() == resourcesBean.getBandwidthFromSelfByStakeV1() && getBandwidthFromSelfByStakeV2() == resourcesBean.getBandwidthFromSelfByStakeV2() && getBandwidthDelegatedToOther() == resourcesBean.getBandwidthDelegatedToOther() && getBandwidthFromOtherTotal() == resourcesBean.getBandwidthFromOtherTotal() && getBandwidthFromOtherByStakeV1() == resourcesBean.getBandwidthFromOtherByStakeV1() && getBandwidthFromOtherByStakeV2() == resourcesBean.getBandwidthFromOtherByStakeV2() && getBandwidthDeletedAvailable() == resourcesBean.getBandwidthDeletedAvailable()) {
                ArrayList<UnFreezingResourceBean> unFreezingList = getUnFreezingList();
                ArrayList<UnFreezingResourceBean> unFreezingList2 = resourcesBean.getUnFreezingList();
                return unFreezingList != null ? unFreezingList.equals(unFreezingList2) : unFreezingList2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = isValid() ? 79 : 97;
        long doubleToLongBits = Double.doubleToLongBits(getTrxBalance());
        long stakedTrx = getStakedTrx();
        long stakedSun = getStakedSun();
        long bandwidthFromOthersTrxV1 = getBandwidthFromOthersTrxV1();
        long energyFromSelfTrxV1 = getEnergyFromSelfTrxV1();
        long bandwidthFromSelfTrxV1 = getBandwidthFromSelfTrxV1();
        long energyFromOthersTrxV1 = getEnergyFromOthersTrxV1();
        long bandwidthForOthersV1 = getBandwidthForOthersV1();
        long energyForOthersV1 = getEnergyForOthersV1();
        long energyFromSelfTrxV2 = getEnergyFromSelfTrxV2();
        long energyFromOthersTrxV2 = getEnergyFromOthersTrxV2();
        long energyDeletedToOthersTrxV2 = getEnergyDeletedToOthersTrxV2();
        long bandwidthFromSelfTrxV2 = getBandwidthFromSelfTrxV2();
        long bandwidthFromOthersTrxV2 = getBandwidthFromOthersTrxV2();
        long bandwidthDeletedToOthersTrxV2 = getBandwidthDeletedToOthersTrxV2();
        long energyTotal = getEnergyTotal();
        long energyUsable = getEnergyUsable();
        long bandwidthTotal = getBandwidthTotal();
        long bandwidthUsable = getBandwidthUsable();
        long bandwidthFree = getBandwidthFree();
        long votingTps = getVotingTps();
        long availableVotingTps = getAvailableVotingTps();
        long unFreezingTrx = getUnFreezingTrx();
        long withDrawAvailableTrx = getWithDrawAvailableTrx();
        long energyFromSelfTotal = getEnergyFromSelfTotal();
        long energyFromSelfByStakeV1 = getEnergyFromSelfByStakeV1();
        long energyFromSelfByStakeV2 = getEnergyFromSelfByStakeV2();
        long energyDelegatedToOther = getEnergyDelegatedToOther();
        long energyFromOtherTotal = getEnergyFromOtherTotal();
        long energyFromOtherByStakeV1 = getEnergyFromOtherByStakeV1();
        long energyFromOtherByStakeV2 = getEnergyFromOtherByStakeV2();
        long energyDeletedAvailable = getEnergyDeletedAvailable();
        long bandwidthFromSelfTotal = getBandwidthFromSelfTotal();
        long bandwidthFromSelfByStakeV1 = getBandwidthFromSelfByStakeV1();
        long bandwidthFromSelfByStakeV2 = getBandwidthFromSelfByStakeV2();
        long bandwidthDelegatedToOther = getBandwidthDelegatedToOther();
        long bandwidthFromOtherTotal = getBandwidthFromOtherTotal();
        long bandwidthFromOtherByStakeV1 = getBandwidthFromOtherByStakeV1();
        long bandwidthFromOtherByStakeV2 = getBandwidthFromOtherByStakeV2();
        long bandwidthDeletedAvailable = getBandwidthDeletedAvailable();
        ArrayList<UnFreezingResourceBean> unFreezingList = getUnFreezingList();
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((i + 59) * 59) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 59) + ((int) (stakedTrx ^ (stakedTrx >>> 32)))) * 59) + ((int) (stakedSun ^ (stakedSun >>> 32)))) * 59) + ((int) (bandwidthFromOthersTrxV1 ^ (bandwidthFromOthersTrxV1 >>> 32)))) * 59) + ((int) (energyFromSelfTrxV1 ^ (energyFromSelfTrxV1 >>> 32)))) * 59) + ((int) (bandwidthFromSelfTrxV1 ^ (bandwidthFromSelfTrxV1 >>> 32)))) * 59) + ((int) (energyFromOthersTrxV1 ^ (energyFromOthersTrxV1 >>> 32)))) * 59) + ((int) (bandwidthForOthersV1 ^ (bandwidthForOthersV1 >>> 32)))) * 59) + ((int) (energyForOthersV1 ^ (energyForOthersV1 >>> 32)))) * 59) + ((int) (energyFromSelfTrxV2 ^ (energyFromSelfTrxV2 >>> 32)))) * 59) + ((int) (energyFromOthersTrxV2 ^ (energyFromOthersTrxV2 >>> 32)))) * 59) + ((int) (energyDeletedToOthersTrxV2 ^ (energyDeletedToOthersTrxV2 >>> 32)))) * 59) + ((int) (bandwidthFromSelfTrxV2 ^ (bandwidthFromSelfTrxV2 >>> 32)))) * 59) + ((int) (bandwidthFromOthersTrxV2 ^ (bandwidthFromOthersTrxV2 >>> 32)))) * 59) + ((int) (bandwidthDeletedToOthersTrxV2 ^ (bandwidthDeletedToOthersTrxV2 >>> 32)))) * 59) + ((int) (energyTotal ^ (energyTotal >>> 32)))) * 59) + ((int) (energyUsable ^ (energyUsable >>> 32)))) * 59) + ((int) (bandwidthTotal ^ (bandwidthTotal >>> 32)))) * 59) + ((int) (bandwidthUsable ^ (bandwidthUsable >>> 32)))) * 59) + ((int) (bandwidthFree ^ (bandwidthFree >>> 32)))) * 59) + ((int) (votingTps ^ (votingTps >>> 32)))) * 59) + ((int) (availableVotingTps ^ (availableVotingTps >>> 32)))) * 59) + ((int) (unFreezingTrx ^ (unFreezingTrx >>> 32)))) * 59) + ((int) (withDrawAvailableTrx ^ (withDrawAvailableTrx >>> 32)))) * 59) + ((int) (energyFromSelfTotal ^ (energyFromSelfTotal >>> 32)))) * 59) + ((int) (energyFromSelfByStakeV1 ^ (energyFromSelfByStakeV1 >>> 32)))) * 59) + ((int) (energyFromSelfByStakeV2 ^ (energyFromSelfByStakeV2 >>> 32)))) * 59) + ((int) (energyDelegatedToOther ^ (energyDelegatedToOther >>> 32)))) * 59) + ((int) (energyFromOtherTotal ^ (energyFromOtherTotal >>> 32)))) * 59) + ((int) (energyFromOtherByStakeV1 ^ (energyFromOtherByStakeV1 >>> 32)))) * 59) + ((int) (energyFromOtherByStakeV2 ^ (energyFromOtherByStakeV2 >>> 32)))) * 59) + ((int) (energyDeletedAvailable ^ (energyDeletedAvailable >>> 32)))) * 59) + ((int) (bandwidthFromSelfTotal ^ (bandwidthFromSelfTotal >>> 32)))) * 59) + ((int) (bandwidthFromSelfByStakeV1 ^ (bandwidthFromSelfByStakeV1 >>> 32)))) * 59) + ((int) (bandwidthFromSelfByStakeV2 ^ (bandwidthFromSelfByStakeV2 >>> 32)))) * 59) + ((int) (bandwidthDelegatedToOther ^ (bandwidthDelegatedToOther >>> 32)))) * 59) + ((int) (bandwidthFromOtherTotal ^ (bandwidthFromOtherTotal >>> 32)))) * 59) + ((int) (bandwidthFromOtherByStakeV1 ^ (bandwidthFromOtherByStakeV1 >>> 32)))) * 59) + ((int) (bandwidthFromOtherByStakeV2 ^ (bandwidthFromOtherByStakeV2 >>> 32)))) * 59) + ((int) (bandwidthDeletedAvailable ^ (bandwidthDeletedAvailable >>> 32)))) * 59) + (unFreezingList == null ? 43 : unFreezingList.hashCode());
    }

    public String toString() {
        return "ResourcesBean(isValid=" + isValid() + ", trxBalance=" + getTrxBalance() + ", stakedTrx=" + getStakedTrx() + ", stakedSun=" + getStakedSun() + ", bandwidthFromOthersTrxV1=" + getBandwidthFromOthersTrxV1() + ", energyFromSelfTrxV1=" + getEnergyFromSelfTrxV1() + ", bandwidthFromSelfTrxV1=" + getBandwidthFromSelfTrxV1() + ", energyFromOthersTrxV1=" + getEnergyFromOthersTrxV1() + ", bandwidthForOthersV1=" + getBandwidthForOthersV1() + ", energyForOthersV1=" + getEnergyForOthersV1() + ", energyFromSelfTrxV2=" + getEnergyFromSelfTrxV2() + ", energyFromOthersTrxV2=" + getEnergyFromOthersTrxV2() + ", energyDeletedToOthersTrxV2=" + getEnergyDeletedToOthersTrxV2() + ", bandwidthFromSelfTrxV2=" + getBandwidthFromSelfTrxV2() + ", bandwidthFromOthersTrxV2=" + getBandwidthFromOthersTrxV2() + ", bandwidthDeletedToOthersTrxV2=" + getBandwidthDeletedToOthersTrxV2() + ", energyTotal=" + getEnergyTotal() + ", energyUsable=" + getEnergyUsable() + ", bandwidthTotal=" + getBandwidthTotal() + ", bandwidthUsable=" + getBandwidthUsable() + ", bandwidthFree=" + getBandwidthFree() + ", votingTps=" + getVotingTps() + ", availableVotingTps=" + getAvailableVotingTps() + ", unFreezingTrx=" + getUnFreezingTrx() + ", withDrawAvailableTrx=" + getWithDrawAvailableTrx() + ", energyFromSelfTotal=" + getEnergyFromSelfTotal() + ", energyFromSelfByStakeV1=" + getEnergyFromSelfByStakeV1() + ", energyFromSelfByStakeV2=" + getEnergyFromSelfByStakeV2() + ", energyDelegatedToOther=" + getEnergyDelegatedToOther() + ", energyFromOtherTotal=" + getEnergyFromOtherTotal() + ", energyFromOtherByStakeV1=" + getEnergyFromOtherByStakeV1() + ", energyFromOtherByStakeV2=" + getEnergyFromOtherByStakeV2() + ", energyDeletedAvailable=" + getEnergyDeletedAvailable() + ", bandwidthFromSelfTotal=" + getBandwidthFromSelfTotal() + ", bandwidthFromSelfByStakeV1=" + getBandwidthFromSelfByStakeV1() + ", bandwidthFromSelfByStakeV2=" + getBandwidthFromSelfByStakeV2() + ", bandwidthDelegatedToOther=" + getBandwidthDelegatedToOther() + ", bandwidthFromOtherTotal=" + getBandwidthFromOtherTotal() + ", bandwidthFromOtherByStakeV1=" + getBandwidthFromOtherByStakeV1() + ", bandwidthFromOtherByStakeV2=" + getBandwidthFromOtherByStakeV2() + ", bandwidthDeletedAvailable=" + getBandwidthDeletedAvailable() + ", unFreezingList=" + getUnFreezingList() + ")";
    }

    public static ResourcesBean buildStakedTrx(ResourcesBean resourcesBean, Protocol.Account account) {
        long frozenSun = getFrozenSun(account);
        buildUnFreezeV2(resourcesBean, account);
        long j = frozenSun + resourcesBean.unFreezingTrx + resourcesBean.withDrawAvailableTrx;
        resourcesBean.stakedTrx = j / TRX_DECIMAL;
        resourcesBean.stakedSun = j;
        return resourcesBean;
    }

    public static ResourcesBean buildVotes(ResourcesBean resourcesBean, Protocol.Account account) {
        resourcesBean.votingTps = getFrozenSun(account) / TRX_DECIMAL;
        long j = 0;
        for (Protocol.Vote vote : account.getVotesList()) {
            j += vote.getVoteCount();
        }
        resourcesBean.availableVotingTps = resourcesBean.votingTps - j;
        return resourcesBean;
    }

    public static ResourcesBean build(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        ResourcesBean resourcesBean = new ResourcesBean();
        if (account != null && account.toString().length() != 0) {
            resourcesBean.trxBalance = account.getBalance() / 1000000.0d;
            resourcesBean.energyFromSelfTrxV1 = account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance();
            resourcesBean.energyFromOthersTrxV1 = account.getAccountResource().getAcquiredDelegatedFrozenBalanceForEnergy();
            resourcesBean.bandwidthFromOthersTrxV1 = account.getAcquiredDelegatedFrozenBalanceForBandwidth();
            if (account.getFrozenList() != null && account.getFrozenList().size() != 0) {
                for (Protocol.Account.Frozen frozen : account.getFrozenList()) {
                    resourcesBean.bandwidthFromSelfTrxV1 += frozen.getFrozenBalance();
                }
            }
            if (accountResourceMessage != null) {
                long energyLimit = accountResourceMessage.getEnergyLimit();
                resourcesBean.energyTotal = energyLimit;
                long energyUsed = energyLimit - accountResourceMessage.getEnergyUsed();
                resourcesBean.energyUsable = energyUsed;
                if (energyUsed < 0) {
                    resourcesBean.energyUsable = 0L;
                }
                resourcesBean.bandwidthTotal = accountResourceMessage.getNetLimit() + accountResourceMessage.getFreeNetLimit();
                long freeNetLimit = accountResourceMessage.getFreeNetLimit() - accountResourceMessage.getFreeNetUsed();
                long netLimit = accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed();
                if (freeNetLimit < 0) {
                    freeNetLimit = 0;
                }
                resourcesBean.bandwidthUsable = freeNetLimit + (netLimit >= 0 ? netLimit : 0L);
            }
            buildVotes(resourcesBean, account);
        }
        return resourcesBean;
    }

    public static ResourcesBean buildV2(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        ResourcesBean resourcesBean = new ResourcesBean();
        if ((account == null || account.toString().length() == 0) && accountResourceMessage == null) {
            resourcesBean.isValid = false;
        }
        if (account != null && account.toString().length() != 0) {
            resourcesBean.trxBalance = account.getBalance() / 1000000.0d;
            resourcesBean.energyFromSelfTrxV1 = account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance();
            resourcesBean.energyFromOthersTrxV1 = account.getAccountResource().getAcquiredDelegatedFrozenBalanceForEnergy();
            resourcesBean.bandwidthFromOthersTrxV1 = account.getAcquiredDelegatedFrozenBalanceForBandwidth();
            resourcesBean.bandwidthForOthersV1 = account.getDelegatedFrozenBalanceForBandwidth() / TRX_DECIMAL;
            resourcesBean.energyForOthersV1 = account.getAccountResource().getDelegatedFrozenBalanceForEnergy() / TRX_DECIMAL;
            if (account.getFrozenList() != null && account.getFrozenList().size() != 0) {
                for (Protocol.Account.Frozen frozen : account.getFrozenList()) {
                    resourcesBean.bandwidthFromSelfTrxV1 += frozen.getFrozenBalance();
                }
            }
            if (account.getFrozenV2Count() > 0) {
                for (Protocol.Account.FreezeV2 freezeV2 : account.getFrozenV2List()) {
                    if (freezeV2.getType() == Common.ResourceCode.ENERGY) {
                        resourcesBean.energyFromSelfTrxV2 += freezeV2.getAmount();
                    } else if (freezeV2.getType() == Common.ResourceCode.BANDWIDTH) {
                        resourcesBean.bandwidthFromSelfTrxV2 += freezeV2.getAmount();
                    }
                }
            }
            resourcesBean.energyFromOthersTrxV2 = account.getAccountResource().getAcquiredDelegatedFrozenV2BalanceForEnergy();
            resourcesBean.bandwidthFromOthersTrxV2 = account.getAcquiredDelegatedFrozenV2BalanceForBandwidth();
            resourcesBean.energyDeletedToOthersTrxV2 = account.getAccountResource().getDelegatedFrozenV2BalanceForEnergy();
            resourcesBean.bandwidthDeletedToOthersTrxV2 = account.getDelegatedFrozenV2BalanceForBandwidth();
            if (accountResourceMessage != null) {
                long energyLimit = accountResourceMessage.getEnergyLimit();
                resourcesBean.energyTotal = energyLimit;
                long energyUsed = energyLimit - accountResourceMessage.getEnergyUsed();
                resourcesBean.energyUsable = energyUsed;
                if (energyUsed < 0) {
                    resourcesBean.energyUsable = 0L;
                }
                resourcesBean.bandwidthTotal = accountResourceMessage.getNetLimit() + accountResourceMessage.getFreeNetLimit();
                long freeNetLimit = accountResourceMessage.getFreeNetLimit() - accountResourceMessage.getFreeNetUsed();
                long netLimit = accountResourceMessage.getNetLimit() - accountResourceMessage.getNetUsed();
                if (freeNetLimit < 0) {
                    freeNetLimit = 0;
                }
                if (netLimit < 0) {
                    netLimit = 0;
                }
                resourcesBean.bandwidthFree = accountResourceMessage.getFreeNetLimit();
                resourcesBean.bandwidthUsable = freeNetLimit + netLimit;
            }
            buildVotes(resourcesBean, account);
            resourcesBean.energyFromSelfByStakeV1 = stakedTrxToEnergy(accountResourceMessage, resourcesBean.energyFromSelfTrxV1, true);
            resourcesBean.energyFromSelfByStakeV2 = stakedTrxToEnergy(accountResourceMessage, resourcesBean.energyFromSelfTrxV2, true);
            long stakedTrxToEnergy = stakedTrxToEnergy(accountResourceMessage, resourcesBean.energyDeletedToOthersTrxV2, true);
            resourcesBean.energyDelegatedToOther = stakedTrxToEnergy;
            long j = resourcesBean.energyFromSelfByStakeV2 + stakedTrxToEnergy;
            resourcesBean.energyFromSelfByStakeV2 = j;
            resourcesBean.energyFromSelfTotal = (resourcesBean.energyFromSelfByStakeV1 + j) - stakedTrxToEnergy;
            resourcesBean.energyFromOtherByStakeV1 = stakedTrxToEnergy(accountResourceMessage, resourcesBean.energyFromOthersTrxV1, true);
            long stakedTrxToEnergy2 = stakedTrxToEnergy(accountResourceMessage, resourcesBean.energyFromOthersTrxV2, true);
            resourcesBean.energyFromOtherByStakeV2 = stakedTrxToEnergy2;
            long j2 = resourcesBean.energyFromOtherByStakeV1;
            long j3 = j2 + stakedTrxToEnergy2;
            resourcesBean.energyFromOtherTotal = j3;
            long j4 = resourcesBean.energyTotal;
            long j5 = resourcesBean.energyFromSelfTotal;
            long j6 = (j4 - j5) - j3;
            if (j6 > 0) {
                if (j5 > 0) {
                    long j7 = resourcesBean.energyFromSelfByStakeV1;
                    long j8 = resourcesBean.energyFromSelfByStakeV2;
                    if (j7 >= j8) {
                        resourcesBean.energyFromSelfByStakeV1 = j7 + j6;
                    } else {
                        resourcesBean.energyFromSelfByStakeV2 = j8 + j6;
                    }
                    resourcesBean.energyFromSelfTotal = j5 + j6;
                } else {
                    if (j2 >= stakedTrxToEnergy2) {
                        resourcesBean.energyFromOtherByStakeV1 = j2 + j6;
                    } else {
                        resourcesBean.energyFromOtherByStakeV2 = stakedTrxToEnergy2 + j6;
                    }
                    resourcesBean.energyFromOtherTotal = j3 + j6;
                }
            }
            resourcesBean.bandwidthFromSelfByStakeV1 = stakedTrxToBandwidth(accountResourceMessage, resourcesBean.bandwidthFromSelfTrxV1, true);
            resourcesBean.bandwidthFromSelfByStakeV2 = stakedTrxToBandwidth(accountResourceMessage, resourcesBean.bandwidthFromSelfTrxV2, true);
            long stakedTrxToBandwidth = stakedTrxToBandwidth(accountResourceMessage, resourcesBean.bandwidthDeletedToOthersTrxV2, true);
            resourcesBean.bandwidthDelegatedToOther = stakedTrxToBandwidth;
            long j9 = resourcesBean.bandwidthFromSelfByStakeV2 + stakedTrxToBandwidth;
            resourcesBean.bandwidthFromSelfByStakeV2 = j9;
            resourcesBean.bandwidthFromSelfTotal = (resourcesBean.bandwidthFromSelfByStakeV1 + j9) - stakedTrxToBandwidth;
            resourcesBean.bandwidthFromOtherByStakeV1 = stakedTrxToBandwidth(accountResourceMessage, resourcesBean.bandwidthFromOthersTrxV1, true);
            long stakedTrxToBandwidth2 = stakedTrxToBandwidth(accountResourceMessage, resourcesBean.bandwidthFromOthersTrxV2, true);
            resourcesBean.bandwidthFromOtherByStakeV2 = stakedTrxToBandwidth2;
            long j10 = resourcesBean.bandwidthFromOtherByStakeV1;
            long j11 = j10 + stakedTrxToBandwidth2;
            resourcesBean.bandwidthFromOtherTotal = j11;
            long j12 = resourcesBean.bandwidthTotal;
            long j13 = resourcesBean.bandwidthFromSelfTotal;
            long j14 = ((j12 - j13) - j11) - resourcesBean.bandwidthFree;
            if (j14 > 0) {
                if (j13 > 0) {
                    long j15 = resourcesBean.bandwidthFromSelfByStakeV1;
                    long j16 = resourcesBean.bandwidthFromSelfByStakeV2;
                    if (j15 >= j16) {
                        resourcesBean.bandwidthFromSelfByStakeV1 = j15 + j14;
                    } else {
                        resourcesBean.bandwidthFromSelfByStakeV2 = j16 + j14;
                    }
                    resourcesBean.bandwidthFromSelfTotal = j13 + j14;
                } else {
                    if (j10 >= stakedTrxToBandwidth2) {
                        resourcesBean.bandwidthFromOtherByStakeV1 = j10 + j14;
                    } else {
                        resourcesBean.bandwidthFromOtherByStakeV2 = stakedTrxToBandwidth2 + j14;
                    }
                    resourcesBean.bandwidthFromOtherTotal = j11 + j14;
                }
            }
        }
        return resourcesBean;
    }

    public static long getFrozenSun(Protocol.Account account) {
        long j;
        if (account != null) {
            try {
                if (account.toString().length() != 0) {
                    long frozenBalance = account.getFrozenCount() > 0 ? account.getFrozen(0).getFrozenBalance() : 0L;
                    long frozenBalance2 = account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance();
                    long delegatedFrozenBalanceForBandwidth = account.getDelegatedFrozenBalanceForBandwidth();
                    long delegatedFrozenBalanceForEnergy = account.getAccountResource().getDelegatedFrozenBalanceForEnergy();
                    long delegatedFrozenV2BalanceForBandwidth = account.getDelegatedFrozenV2BalanceForBandwidth();
                    long delegatedFrozenV2BalanceForEnergy = account.getAccountResource().getDelegatedFrozenV2BalanceForEnergy();
                    if (account.getFrozenV2Count() > 0) {
                        long j2 = 0;
                        for (int i = 0; i < account.getFrozenV2Count(); i++) {
                            if (account.getFrozenV2(i).getType() == Common.ResourceCode.BANDWIDTH || account.getFrozenV2(i).getType() == Common.ResourceCode.ENERGY) {
                                j2 += account.getFrozenV2(i).getAmount();
                            }
                        }
                        j = j2;
                    } else {
                        j = 0;
                    }
                    return j + frozenBalance + delegatedFrozenBalanceForBandwidth + delegatedFrozenBalanceForEnergy + frozenBalance2 + delegatedFrozenV2BalanceForBandwidth + delegatedFrozenV2BalanceForEnergy;
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return 0L;
    }

    public static ResourcesBean buildUnFreezeV2(ResourcesBean resourcesBean, Protocol.Account account) {
        resourcesBean.unFreezingList = new ArrayList<>();
        if (account != null && account.toString().length() != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = 0;
            long j2 = 0;
            for (Protocol.Account.UnFreezeV2 unFreezeV2 : account.getUnfrozenV2List()) {
                if (unFreezeV2.getUnfreezeExpireTime() > currentTimeMillis) {
                    j += unFreezeV2.getUnfreezeAmount();
                    UnFreezingResourceBean unFreezingResourceBean = new UnFreezingResourceBean();
                    if (unFreezeV2.getType() == Common.ResourceCode.BANDWIDTH) {
                        unFreezingResourceBean.setType(UnFreezingResourceBean.Type.BANDWIDTH);
                    } else if (unFreezeV2.getType() == Common.ResourceCode.ENERGY) {
                        unFreezingResourceBean.setType(UnFreezingResourceBean.Type.ENERGY);
                    }
                    unFreezingResourceBean.setExpiredTime(unFreezeV2.getUnfreezeExpireTime());
                    unFreezingResourceBean.setTrxCount(unFreezeV2.getUnfreezeAmount() / TRX_DECIMAL);
                    resourcesBean.unFreezingList.add(unFreezingResourceBean);
                } else {
                    j2 += unFreezeV2.getUnfreezeAmount();
                }
            }
            Collections.sort(resourcesBean.unFreezingList);
            resourcesBean.unFreezingTrx = j;
            resourcesBean.withDrawAvailableTrx = j2;
        }
        return resourcesBean;
    }

    public static long stakedTrxToBandwidth(GrpcAPI.AccountResourceMessage accountResourceMessage, long j) {
        return stakedTrxToBandwidth(accountResourceMessage, j, false);
    }

    public static long stakedTrxToBandwidth(GrpcAPI.AccountResourceMessage accountResourceMessage, long j, boolean z) {
        double d = j;
        if (z) {
            d /= 1000000.0d;
        }
        if (accountResourceMessage != null && BigDecimalUtils.MoreThan((Object) Double.valueOf(d), (Object) 0)) {
            BigDecimal bigDecimal = new BigDecimal(accountResourceMessage.getTotalNetWeight());
            BigDecimal bigDecimal2 = new BigDecimal(accountResourceMessage.getTotalNetLimit());
            if (bigDecimal.compareTo(BigDecimal.ZERO) != 0) {
                return BigDecimalUtils.toBigDecimal(Double.valueOf(d)).multiply(bigDecimal2).divide(bigDecimal, 0, 1).longValue();
            }
        }
        return 0L;
    }

    public static long stakedTrxToEnergy(GrpcAPI.AccountResourceMessage accountResourceMessage, long j) {
        return stakedTrxToEnergy(accountResourceMessage, j, false);
    }

    public static long stakedTrxToEnergy(GrpcAPI.AccountResourceMessage accountResourceMessage, long j, boolean z) {
        double d = j;
        if (z) {
            d /= 1000000.0d;
        }
        if (accountResourceMessage != null && BigDecimalUtils.MoreThan((Object) Double.valueOf(d), (Object) 0)) {
            BigDecimal bigDecimal = new BigDecimal(accountResourceMessage.getTotalEnergyWeight());
            BigDecimal bigDecimal2 = new BigDecimal(accountResourceMessage.getTotalEnergyLimit());
            if (bigDecimal.compareTo(BigDecimal.ZERO) != 0) {
                return BigDecimalUtils.toBigDecimal(Double.valueOf(d)).multiply(bigDecimal2).divide(bigDecimal, 0, 1).longValue();
            }
        }
        return 0L;
    }

    public static BigDecimal expectGetBandWidth(GrpcAPI.AccountResourceMessage accountResourceMessage, BigDecimal bigDecimal) {
        if (accountResourceMessage != null && BigDecimalUtils.MoreThan((Object) bigDecimal, (Object) 0)) {
            BigDecimal bigDecimal2 = new BigDecimal(accountResourceMessage.getTotalNetWeight());
            BigDecimal bigDecimal3 = new BigDecimal(accountResourceMessage.getTotalNetLimit());
            if (bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
                bigDecimal2.add(bigDecimal);
                return bigDecimal.multiply(bigDecimal3).divide(bigDecimal2, 0, 1);
            }
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal expectGetEnergy(GrpcAPI.AccountResourceMessage accountResourceMessage, BigDecimal bigDecimal) {
        if (accountResourceMessage != null && BigDecimalUtils.MoreThan((Object) bigDecimal, (Object) 0)) {
            BigDecimal bigDecimal2 = new BigDecimal(accountResourceMessage.getTotalEnergyWeight());
            BigDecimal bigDecimal3 = new BigDecimal(accountResourceMessage.getTotalEnergyLimit());
            if (bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
                return bigDecimal.multiply(bigDecimal3).divide(bigDecimal2.add(bigDecimal), 0, 1);
            }
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal unStakeTrxToBandWidth(GrpcAPI.AccountResourceMessage accountResourceMessage, long j) {
        if (accountResourceMessage != null && BigDecimalUtils.MoreThan((Object) Long.valueOf(j), (Object) 0)) {
            BigDecimal bigDecimal = new BigDecimal(accountResourceMessage.getTotalNetWeight());
            BigDecimal bigDecimal2 = BigDecimalUtils.toBigDecimal(Long.valueOf(j));
            BigDecimal bigDecimal3 = new BigDecimal(accountResourceMessage.getTotalNetLimit());
            if (bigDecimal.compareTo(BigDecimal.ZERO) != 0) {
                return bigDecimal2.multiply(bigDecimal3).divide(bigDecimal.subtract(bigDecimal2), 16, 1);
            }
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal unStakeTrxToEnergy(GrpcAPI.AccountResourceMessage accountResourceMessage, long j) {
        if (accountResourceMessage != null && BigDecimalUtils.MoreThan((Object) Long.valueOf(j), (Object) 0)) {
            BigDecimal bigDecimal = new BigDecimal(accountResourceMessage.getTotalEnergyWeight());
            BigDecimal bigDecimal2 = BigDecimalUtils.toBigDecimal(Long.valueOf(j));
            BigDecimal bigDecimal3 = new BigDecimal(accountResourceMessage.getTotalEnergyLimit());
            if (bigDecimal.compareTo(BigDecimal.ZERO) != 0) {
                return bigDecimal2.multiply(bigDecimal3).divide(bigDecimal.subtract(bigDecimal2), 16, 1);
            }
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }

    public long getBandwidthFromOthersTrxV1() {
        return this.bandwidthFromOthersTrxV1 / TRX_DECIMAL;
    }

    public long getEnergyFromSelfTrxV1() {
        return this.energyFromSelfTrxV1 / TRX_DECIMAL;
    }

    public long getBandwidthFromSelfTrxV1() {
        return this.bandwidthFromSelfTrxV1 / TRX_DECIMAL;
    }

    public long getEnergyFromOthersTrxV1() {
        return this.energyFromOthersTrxV1 / TRX_DECIMAL;
    }

    public long getEnergyFromSelfTrxV2() {
        return this.energyFromSelfTrxV2 / TRX_DECIMAL;
    }

    public long getEnergyFromOthersTrxV2() {
        return this.energyFromOthersTrxV2 / TRX_DECIMAL;
    }

    public long getEnergyDeletedToOthersTrxV2() {
        return this.energyDeletedToOthersTrxV2 / TRX_DECIMAL;
    }

    public long getBandwidthFromSelfTrxV2() {
        return this.bandwidthFromSelfTrxV2 / TRX_DECIMAL;
    }

    public long getBandwidthFromOthersTrxV2() {
        return this.bandwidthFromOthersTrxV2 / TRX_DECIMAL;
    }

    public long getBandwidthDeletedToOthersTrxV2() {
        return this.bandwidthDeletedToOthersTrxV2 / TRX_DECIMAL;
    }

    public long getStakingV2TotalTrx() {
        return (((this.energyFromSelfTrxV2 + this.energyDeletedToOthersTrxV2) + this.bandwidthFromSelfTrxV2) + this.bandwidthDeletedToOthersTrxV2) / TRX_DECIMAL;
    }

    public long getUnFreezingTrx() {
        return this.unFreezingTrx / TRX_DECIMAL;
    }

    public long getWithDrawAvailableTrx() {
        return this.withDrawAvailableTrx / TRX_DECIMAL;
    }
}
