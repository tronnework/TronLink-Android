package com.tron.wallet.business.tabdapp.dappsearch;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.bean.DappHotBean;
import com.tron.wallet.business.tabdapp.bean.DappSearchBean;
import io.reactivex.Observable;
public interface DappSearchContract {

    public interface Model extends BaseModel {
        Observable<DappSearchBean> doSearch(String str);

        Observable<DappHotBean> getDappHotList();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addSome();

        abstract void doHotList();

        abstract void doSearch();
    }

    public interface View extends BaseView {
        void doFailure(int i);

        void doSuccess(DappHotBean dappHotBean, int i);

        void doSuccess(DappSearchBean dappSearchBean);

        String getContent();
    }
}
