package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old.DappConfirmContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public class DappConfirmModel implements DappConfirmContract.Model {
    @Override
    public Observable<Object> addDappRecord(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).addDappRecord(requestBody).compose(RxSchedulers.io_main());
    }
}
