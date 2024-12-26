package com.tron.wallet.business.tabmy.dealsign.signfailure;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.dealsign.signfailure.SignFailureContract;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class SignFailureModel implements SignFailureContract.Model {
    @Override
    public Observable<DealSignOutput> getSignFailureTransfer(String str, int i, int i2, int i3, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getSignFailureTransfer(str, i, i2, i3, str2).compose(RxSchedulers.io_main());
    }
}
