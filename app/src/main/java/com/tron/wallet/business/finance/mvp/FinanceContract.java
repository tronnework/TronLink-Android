package com.tron.wallet.business.finance.mvp;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.finance.bean.FinanceDataBean;
import com.tron.wallet.business.tabswap.mvp.Contract;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public interface FinanceContract {

    public interface FinanceSortIndex {
        public static final int APR = 2;
        public static final int SUGGEST = 1;
        public static final int TVL = 4;
        public static final int VALUE = 3;
    }

    public interface Model extends BaseModel {
        Observable<FinanceDataBean> getMyFinancialProjectList(RequestBody requestBody);

        Observable<FinanceDataBean> getMyTokenFinancialList(RequestBody requestBody);

        Observable<FinanceDataBean> getTokenFinancialList(RequestBody requestBody);

        Observable<FinanceDataBean> getTotalAssets(RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<Contract.Model, Contract.View> {
    }

    public interface View extends BaseView {
    }
}
