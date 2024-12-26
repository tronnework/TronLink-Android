package com.tron.wallet.business.stakev2.stake;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.stakev2.QueryAccountModel;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.stakev2.stake.StakeHomeContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class StakeHomeModel implements StakeHomeContract.Model {
    private QueryAccountModel queryAccountModel = new QueryAccountModel();
    private ResourcesBean resourcesBean;

    @Override
    public Observable<WitnessOutput> getWitnessList() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessListV2(5, 1, 0, 1).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<WitnessOutput> requestWitnessList(String str, int i, int i2, int i3, int i4) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getWitnessList(i3, i4, 2, i2, str, i);
    }

    @Override
    public Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(GrpcAPI.AccountResourceMessage accountResourceMessage, String str) {
        return this.queryAccountModel.getAccountResourceMessage(accountResourceMessage, str);
    }

    @Override
    public Observable<Protocol.Account> getAccount(Protocol.Account account, String str) {
        return this.queryAccountModel.getAccount(account, str);
    }
}
