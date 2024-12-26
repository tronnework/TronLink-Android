package com.tron.wallet.business.tabdapp.browser.base.history;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import io.reactivex.Observable;
import java.util.List;
public interface BrowserHistoryContract {

    public interface Model extends BaseModel {
        void clearAllHistory();

        Observable<List<BrowserHistoryBean>> queryHistory(int i, int i2);

        List<BrowserHistoryBean> queryHistory();

        boolean remveHistory(BrowserHistoryBean browserHistoryBean, int i);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void clearAllHistory();

        public abstract void jumpToViewPage(BrowserHistoryBean browserHistoryBean);

        public abstract boolean remveHistory(BrowserHistoryBean browserHistoryBean, int i);

        public abstract void requestData(boolean z);
    }

    public interface View extends BaseView {
        void updateList(List<BrowserHistoryBean> list, boolean z);
    }
}
