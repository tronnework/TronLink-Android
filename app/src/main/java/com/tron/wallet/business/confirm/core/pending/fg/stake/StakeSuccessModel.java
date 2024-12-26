package com.tron.wallet.business.confirm.core.pending.fg.stake;

import com.tron.wallet.business.confirm.core.pending.fg.stake.StakeSuccessContract;
import com.tron.wallet.business.stakev2.stake.StakeHomeModel;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import io.reactivex.Observable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class StakeSuccessModel implements StakeSuccessContract.Model {
    StakeHomeModel stakeHomeModel = new StakeHomeModel();

    @Override
    public Observable<WitnessOutput> getWitnessList() {
        return this.stakeHomeModel.getWitnessList();
    }

    @Override
    public Observable<WitnessOutput> requestWitnessList(String str, int i, int i2, int i3, int i4) {
        return this.stakeHomeModel.requestWitnessList(str, i, i2, i3, i4);
    }

    @Override
    public Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(GrpcAPI.AccountResourceMessage accountResourceMessage, String str) {
        return this.stakeHomeModel.getAccountResourceMessage(accountResourceMessage, str);
    }

    @Override
    public Observable<Protocol.Account> getAccount(Protocol.Account account, String str) {
        return this.stakeHomeModel.getAccount(account, str);
    }
}
