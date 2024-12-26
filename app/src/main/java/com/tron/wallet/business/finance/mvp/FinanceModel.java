package com.tron.wallet.business.finance.mvp;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.finance.bean.FinanceDataBean;
import com.tron.wallet.business.finance.mvp.FinanceContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public class FinanceModel implements FinanceContract.Model {
    @Override
    public Observable<FinanceDataBean> getTotalAssets(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getFinancialTotalAssets(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<FinanceDataBean> getTokenFinancialList(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTokenFinancialList(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<FinanceDataBean> getMyTokenFinancialList(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMyTokenFinancialList(requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<FinanceDataBean> getMyFinancialProjectList(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMyFinancialProjectList(requestBody).compose(RxSchedulers.io_main());
    }
}
