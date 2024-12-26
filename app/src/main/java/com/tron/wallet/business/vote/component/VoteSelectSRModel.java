package com.tron.wallet.business.vote.component;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSelectSRContract;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class VoteSelectSRModel implements VoteSelectSRContract.Model {
    @Override
    public Observable<WitnessOutput> getWitnessList(String str, int i, int i2, int i3, int i4, boolean z) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList(30, i2, 2, i4, str, z ? 1 : 0).compose(RxSchedulers2.io_main());
    }

    @Override
    public Observable<SearchWitnessResult> search(String str, int i, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).searchWitness(str, i, str2).compose(RxSchedulers.io_main());
    }
}
