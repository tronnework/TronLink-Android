package com.tron.wallet.business.tokendetail.mvp;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import com.tron.wallet.business.tokendetail.mvp.TokenReportContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public class TokenReportModel implements TokenReportContract.Model {
    @Override
    public Observable<BaseOutput> requestTokenReport(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestTokenReport(requestBody).compose(RxSchedulers.io_main());
    }
}
