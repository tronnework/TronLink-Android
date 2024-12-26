package com.tron.wallet.business.transfer.deposit;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.bean.DappToMainFeeResponse;
import io.reactivex.Observable;
public interface DepositContract {

    public interface Model extends BaseModel {
        Observable<DappToMainFeeResponse> getDappToMainFee();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getDappToMainFee();
    }

    public interface View extends BaseView {
        void setDappToMainFee(long j);
    }
}
