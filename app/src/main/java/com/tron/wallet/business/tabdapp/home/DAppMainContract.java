package com.tron.wallet.business.tabdapp.home;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.home.bean.DAppBannerOutput;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.business.tabdapp.home.bean.DappBannerBean;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.List;
public interface DAppMainContract {

    public interface Model extends BaseModel {
        ObservableSource<List<DappBean>> getBookDapp();

        Observable<List<DappBean>> getMostVisitDApps();

        void insertVisitedDApp(DappBean dappBean);

        Observable<DAppBannerOutput> requestBanners();

        Observable<DAppListOutput> requestDappList(int i, int i2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getBanners();

        abstract void getBookDapp();

        abstract void getDappList(int i);

        abstract void getRecommendDapp();

        public abstract void getRecommendDapp(int i);

        abstract DappBean insertVisitedDapp(DappBean dappBean);

        abstract void refresh();
    }

    public interface View extends BaseView {
        void onBookDappComplete(boolean z, List<DappBean> list, boolean z2);

        void onRecommendDappComplete(boolean z, List<DappBean> list);

        void onRequestBannersComplete(boolean z, List<DappBannerBean> list);

        void onRequestDappListComplete(boolean z, int i, List<DappBean> list);

        void onStartRequestDappList();
    }
}
