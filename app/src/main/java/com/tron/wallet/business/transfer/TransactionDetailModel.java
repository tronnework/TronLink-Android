package com.tron.wallet.business.transfer;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.transfer.TransactionDetailContract;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
public class TransactionDetailModel implements TransactionDetailContract.Model {
    @Override
    public Observable<TransactionInfoBean> getTransactionInfo(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTrasactionInfo(str).flatMap(new Function<Object, Observable<TransactionInfoBean>>() {
            @Override
            public Observable<TransactionInfoBean> apply(Object obj) throws Exception {
                return Observable.just((TransactionInfoBean) obj);
            }
        }).compose(RxSchedulers.io_main());
    }
}
