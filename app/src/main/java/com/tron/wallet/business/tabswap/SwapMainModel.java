package com.tron.wallet.business.tabswap;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabswap.SwapMainContract;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public class SwapMainModel implements SwapMainContract.Model {
    @Override
    public Observable<Result2<AppStatusOutput>> getStatus(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).checkAppStatus(requestBody).compose(RxSchedulers.io_main());
    }
}
