package com.tron.wallet.business.vote.component;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.vote.bean.SearchWitnessResult;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.VoteSRSearchContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class VoteSRSearchModel implements VoteSRSearchContract.Model {
    private int fixSortType(int i) {
        return i;
    }

    @Override
    public Observable<WitnessOutput> getWitnessList(int i, int i2, int i3, int i4, String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList(i, i2, 2, i4, str, 0).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<SearchWitnessResult> search(String str, int i, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).searchWitness(str, fixSortType(i), str2).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<WitnessOutput> getAllFromTron() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAll(2, 1).compose(RxSchedulers.io_main());
    }
}
