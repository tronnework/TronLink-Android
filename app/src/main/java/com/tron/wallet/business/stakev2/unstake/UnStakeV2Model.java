package com.tron.wallet.business.stakev2.unstake;

import com.tron.wallet.business.stakev2.QueryAccountModel;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.stakev2.unstake.UnStakeV2Contract;
import io.reactivex.Observable;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.function.Predicate;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class UnStakeV2Model implements UnStakeV2Contract.Model {
    private QueryAccountModel queryAccountModel = new QueryAccountModel();
    private ResourcesBean resourcesBean;

    @Override
    public Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(GrpcAPI.AccountResourceMessage accountResourceMessage, String str) {
        return this.queryAccountModel.getAccountResourceMessage(accountResourceMessage, str);
    }

    @Override
    public Observable<Protocol.Account> getAccount(Protocol.Account account, String str) {
        return this.queryAccountModel.getAccount(account, str);
    }

    @Override
    public UnStakeV2Bean getViewData(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        UnStakeV2Bean unStakeV2Bean = new UnStakeV2Bean();
        if (account == null || "".equals(account.toString())) {
            return unStakeV2Bean;
        }
        if (accountResourceMessage == null || "".equals(accountResourceMessage.toString())) {
            return null;
        }
        this.resourcesBean = ResourcesBean.buildV2(account, accountResourceMessage);
        final long currentTimeMillis = System.currentTimeMillis();
        long count = Collection.-EL.stream(account.getUnfrozenV2List()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return UnStakeV2Model.lambda$getViewData$0(currentTimeMillis, (Protocol.Account.UnFreezeV2) obj);
            }
        }).count();
        long j = count != 0 ? 32 - count : 32L;
        long votingTps = this.resourcesBean.getVotingTps();
        long energyFromSelfTrxV2 = this.resourcesBean.getEnergyFromSelfTrxV2();
        long bandwidthFromSelfTrxV2 = this.resourcesBean.getBandwidthFromSelfTrxV2();
        long bandwidthDeletedToOthersTrxV2 = this.resourcesBean.getBandwidthDeletedToOthersTrxV2();
        long energyDeletedToOthersTrxV2 = this.resourcesBean.getEnergyDeletedToOthersTrxV2();
        long energyTotal = this.resourcesBean.getEnergyTotal();
        long j2 = j;
        long bandwidthTotal = this.resourcesBean.getBandwidthTotal();
        unStakeV2Bean.setVotesSum(votingTps);
        unStakeV2Bean.setAvailableUnFreezeEnergy(energyFromSelfTrxV2);
        unStakeV2Bean.setAvailableUnFreezeBandwidth(bandwidthFromSelfTrxV2);
        unStakeV2Bean.setUnAvailableUnFreezeBandwidth(bandwidthDeletedToOthersTrxV2);
        unStakeV2Bean.setUnAvailableUnFreezeEnergy(energyDeletedToOthersTrxV2);
        unStakeV2Bean.setBandwidthSum(bandwidthTotal);
        unStakeV2Bean.setEnergySum(energyTotal);
        unStakeV2Bean.setMaximum(j2);
        return unStakeV2Bean;
    }

    public static boolean lambda$getViewData$0(long j, Protocol.Account.UnFreezeV2 unFreezeV2) {
        return unFreezeV2.getUnfreezeAmount() > 0 && unFreezeV2.getUnfreezeExpireTime() > j;
    }

    public long getSurplusRes(ResState resState, GrpcAPI.AccountResourceMessage accountResourceMessage, long j, long j2) {
        if (this.resourcesBean == null) {
            return 0L;
        }
        if (resState == ResState.Bandwidth) {
            long longValue = ResourcesBean.unStakeTrxToBandWidth(accountResourceMessage, j2).longValue();
            if (longValue > j) {
                return 0L;
            }
            return j - longValue;
        } else if (resState == ResState.Energy) {
            long longValue2 = ResourcesBean.unStakeTrxToEnergy(accountResourceMessage, j2).longValue();
            if (longValue2 > j) {
                return 0L;
            }
            return j - longValue2;
        } else {
            return 0L;
        }
    }
}
