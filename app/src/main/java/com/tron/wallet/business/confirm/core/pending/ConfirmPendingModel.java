package com.tron.wallet.business.confirm.core.pending;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class ConfirmPendingModel implements ConfirmPendingContract.Model {
    @Override
    public Observable<TransactionInfoBean> getTransactionInfo(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTrasactionInfo(str).compose(RxSchedulers.io_main());
    }
}
