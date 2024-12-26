package com.tron.wallet.business.tabdapp.browser.search;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserBookMarkManager;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserHistoryManager;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserSearchHistoryManager;
import com.tron.wallet.business.tabdapp.browser.controller.DAppVisitHistoryController;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchContract;
import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
public class BrowserSearchModel implements BrowserSearchContract.Model {
    @Override
    public Observable<SearchDappResultBean> getSearchDapp(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDappSearch(str, AssetsManager.getInstance().getHexAddress());
    }

    @Override
    public Observable<List<BrowserSearchBean>> getSearchHistory() {
        return Observable.unsafeCreate(new ObservableSource<List<BrowserSearchBean>>() {
            @Override
            public void subscribe(Observer<? super List<BrowserSearchBean>> observer) {
                observer.onNext(BrowserSearchHistoryManager.getInstance().queryHistory());
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<List<DAppVisitHistoryBean>> getInterviewHistory() {
        return DAppVisitHistoryController.getInstance().getDAppVisitHistory();
    }

    @Override
    public Observable<Boolean> clearHistory() {
        return Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(Observer<? super Boolean> observer) {
                observer.onNext(Boolean.valueOf(BrowserSearchHistoryManager.getInstance().removeAll()));
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Boolean> clearInterviewHistory() {
        return DAppVisitHistoryController.getInstance().clear();
    }

    @Override
    public Observable<Boolean> deleteInterviewHistoryItem(DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
        return DAppVisitHistoryController.getInstance().deleteVisitHistory(dAppVisitHistoryBean.getUrl());
    }

    @Override
    public Observable<List<SearchDappResultBean.SearchDappBean>> getBrowserHistory(final String str) {
        return Observable.unsafeCreate(new ObservableSource<List<SearchDappResultBean.SearchDappBean>>() {
            @Override
            public void subscribe(Observer<? super List<SearchDappResultBean.SearchDappBean>> observer) {
                ArrayList arrayList = new ArrayList();
                for (BrowserHistoryBean browserHistoryBean : BrowserHistoryManager.getInstance().search(str)) {
                    SearchDappResultBean.SearchDappBean searchDappBean = new SearchDappResultBean.SearchDappBean();
                    searchDappBean.setKeyword(str);
                    searchDappBean.setHomeUrl(browserHistoryBean.getUrl());
                    searchDappBean.setImageUrl(browserHistoryBean.getIconUrl());
                    searchDappBean.setName(browserHistoryBean.getTitle());
                    searchDappBean.calculatePriory();
                    arrayList.add(searchDappBean);
                }
                observer.onNext(arrayList);
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<List<SearchDappResultBean.SearchDappBean>> BrowserBook(final String str) {
        return Observable.unsafeCreate(new ObservableSource<List<SearchDappResultBean.SearchDappBean>>() {
            @Override
            public void subscribe(Observer<? super List<SearchDappResultBean.SearchDappBean>> observer) {
                ArrayList arrayList = new ArrayList();
                for (BrowserBookMarkBean browserBookMarkBean : BrowserBookMarkManager.getInstance().search(str)) {
                    SearchDappResultBean.SearchDappBean searchDappBean = new SearchDappResultBean.SearchDappBean();
                    searchDappBean.setKeyword(str);
                    searchDappBean.setHomeUrl(browserBookMarkBean.getUrl());
                    searchDappBean.setImageUrl(browserBookMarkBean.getIconUrl());
                    searchDappBean.setName(browserBookMarkBean.getTitle());
                    searchDappBean.calculatePriory();
                    arrayList.add(searchDappBean);
                }
                observer.onNext(arrayList);
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DAppListOutput> getAllDappList() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestDAppList(0, selectedPublicWallet != null ? selectedPublicWallet.getAddress() : null).compose(RxSchedulers2.io_main());
    }
}
