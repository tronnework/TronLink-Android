package com.tron.wallet.business.tabdapp.browser.base.contract;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import io.reactivex.Observable;
import java.util.List;
public interface BookMarkManagerContract {

    public interface Model extends BaseModel {
        Observable<List<BrowserBookMarkBean>> queryBookMark();

        boolean remveBookMark(BrowserBookMarkBean browserBookMarkBean, int i);

        void saveSortedDatas(List<BrowserBookMarkBean> list);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void jumpToViewPage(BrowserBookMarkBean browserBookMarkBean);

        public abstract boolean remveBookMark(BrowserBookMarkBean browserBookMarkBean, int i);

        public abstract void requestData(boolean z);

        public abstract void saveSortedDatas(List<BrowserBookMarkBean> list);
    }

    public interface View extends BaseView {
        void updateList(List<BrowserBookMarkBean> list);
    }
}
