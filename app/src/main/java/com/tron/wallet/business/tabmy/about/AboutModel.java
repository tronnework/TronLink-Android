package com.tron.wallet.business.tabmy.about;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.about.AboutContract;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class AboutModel implements AboutContract.Model {
    @Override
    public Observable<CommunityOutput> getCommunityContent() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCommunityContent().compose(RxSchedulers.io_main());
    }
}
