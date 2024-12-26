package com.tron.wallet.business.tabmy.proposals.makeproposal;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.proposals.makeproposal.MakeProposalContract;
import com.tron.wallet.common.bean.ChainparametersOutPut;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class MakeProposalModel implements MakeProposalContract.Model {
    @Override
    public Observable<ChainparametersOutPut> getChainparameters() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getChainparameters().compose(RxSchedulers.io_main());
    }
}
