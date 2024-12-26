package com.tron.wallet.business.tronpower.management;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tronpower.management.ResourceManagementContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class ResourceManagementModel implements ResourceManagementContract.Model {
    @Override
    public Observable<WitnessOutput> getWitnessList() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessListV2(5, 1, 0, 1).compose(RxSchedulers.io_main());
    }
}
