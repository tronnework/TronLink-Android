package com.tron.wallet.business.tabmy.joincommunity;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.business.tabmy.joincommunity.JoinCommunityContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class JoinCommunityModel implements JoinCommunityContract.Model {
    @Override
    public Observable<CommunityOutput> getCommunityContent() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCommunityContent().compose(RxSchedulers.io_main());
    }
}
