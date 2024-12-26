package com.tron.wallet.business.tabmy.proposals.proposaldetail;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.proposals.proposaldetail.ProposalsDetailContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class ProposalsDetailModel implements ProposalsDetailContract.Model {
    @Override
    public Observable<WitnessOutput> getWitnessList() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList().compose(RxSchedulers.io_main());
    }
}
