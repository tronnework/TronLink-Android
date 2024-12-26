package com.tron.wallet.business.tabdapp.browser.search;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchPresenter;
import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public interface BrowserSearchContract {

    public interface Model extends BaseModel {
        Observable<List<SearchDappResultBean.SearchDappBean>> BrowserBook(String str);

        Observable<Boolean> clearHistory();

        Observable<Boolean> clearInterviewHistory();

        Observable<Boolean> deleteInterviewHistoryItem(DAppVisitHistoryBean dAppVisitHistoryBean, int i);

        Observable<DAppListOutput> getAllDappList();

        Observable<List<SearchDappResultBean.SearchDappBean>> getBrowserHistory(String str);

        Observable<List<DAppVisitHistoryBean>> getInterviewHistory();

        Observable<SearchDappResultBean> getSearchDapp(String str);

        Observable<List<BrowserSearchBean>> getSearchHistory();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void clearInterviewHistory();

        public abstract void clearSearchHistory();

        public abstract void deleteInterviewHistoryItem(DAppVisitHistoryBean dAppVisitHistoryBean, int i);

        public abstract void doGoogleSearch(String str);

        public abstract void doTronScanSearch(String str);

        protected abstract void doTronscanSearchAction(String str);

        public abstract void getInterviewHistory();

        public abstract void getSearchDapp(String str);

        public abstract void getSearchHistory();

        public abstract void loadNewPage(String str, String str2, String str3);

        public abstract void loadNewPage(String str, String str2, String str3, boolean z);

        public abstract void loadNewPageAction(String str, String str2, String str3);

        public abstract void loadNewPageWithOutUnknownPop(String str, String str2, String str3, boolean z);
    }

    public interface View extends BaseView {
        void hideInterviewHistoryView();

        void showClearAllVisitHistoryPopWindow();

        void showNoNetView();

        void showThirdAddressPopWindow(String str, BrowserSearchPresenter.SearchTipsPopCall searchTipsPopCall);

        void showUnknownAddressPopWindow(String str, BrowserSearchPresenter.SearchTipsPopCall searchTipsPopCall);

        void updateAssetsFollowState(TokenBean tokenBean, int i, boolean z);

        void updateInterviewHistoryView(DAppVisitHistoryBean dAppVisitHistoryBean, int i);

        void updateInterviewHistoryView(List<DAppVisitHistoryBean> list);

        void updateSearchHistory(List<BrowserSearchBean> list);

        void updateSearchedResult(List<SearchDappResultBean.SearchDappBean> list);
    }
}
