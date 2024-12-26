package com.tron.wallet.business.tabmy.allhistory;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabmy.allhistory.TrxAllHistoryContract;
import com.tron.wallet.common.bean.AllTransferOutput;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class TrxAllHistoryModel implements TrxAllHistoryContract.Model {
    @Override
    public Observable<AllTransferOutput> getAllTRXTransfer(String str, String str2, String str3, int i, int i2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAllTRXTransfer(str, str2, str3, i, i2).compose(RxSchedulers.io_main());
    }
}
