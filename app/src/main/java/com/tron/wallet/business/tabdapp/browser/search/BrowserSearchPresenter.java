package com.tron.wallet.business.tabdapp.browser.search;

import android.net.Uri;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserSearchHistoryManager;
import com.tron.wallet.business.tabdapp.browser.controller.DAppVisitHistoryController;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchContract;
import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import com.tron.wallet.business.tabdapp.dappsearch.DappSearchPresenter;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.tron.protos.Protocol;
public class BrowserSearchPresenter extends BrowserSearchContract.Presenter {
    public static final String TAG = "BrowserSearchPresenter";
    public static final int TYPE_TRONSCAN_SEARCH_ACCOUNT = 0;
    public static final int TYPE_TRONSCAN_SEARCH_CONTRACT = 1;
    public static final int TYPE_TRONSCAN_SEARCH_HEX = 2;
    private String lastKeyWord;
    private String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=";
    private volatile String searchKeyWord = "";
    ArrayList<String> allDAPPUrlList = new ArrayList<>();

    public interface SearchTipsPopCall {
        void callBack();
    }

    public static void lambda$loadNewPageAction$1(Boolean bool) throws Exception {
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void getSearchDapp(final String str) {
        this.lastKeyWord = str;
        Observable.zip(((BrowserSearchContract.Model) this.mModel).getSearchDapp(str), ((BrowserSearchContract.Model) this.mModel).getBrowserHistory(str), ((BrowserSearchContract.Model) this.mModel).BrowserBook(str), new Function3() {
            @Override
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return BrowserSearchPresenter.lambda$getSearchDapp$0(str, (SearchDappResultBean) obj, (List) obj2, (List) obj3);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<SearchDappResultBean.SearchDappBean>>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, List<SearchDappResultBean.SearchDappBean> list) {
                if (list == null || list == null || list.size() <= 0) {
                    return;
                }
                Collections.sort(list, new SearchComparator());
                ((BrowserSearchContract.View) mView).updateSearchedResult(list);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getSearchDapp"));
    }

    public static ArrayList lambda$getSearchDapp$0(String str, SearchDappResultBean searchDappResultBean, List list, List list2) throws Exception {
        HashSet hashSet = new HashSet();
        List<SearchDappResultBean.SearchDappBean> data = searchDappResultBean.getData();
        if (data != null && data.size() > 0) {
            for (SearchDappResultBean.SearchDappBean searchDappBean : searchDappResultBean.getData()) {
                searchDappBean.setKeyword(str.toLowerCase());
                searchDappBean.setFromHttp(true);
                searchDappBean.calculatePriory();
            }
            hashSet.addAll(data);
        }
        hashSet.addAll(list2);
        hashSet.addAll(list);
        return new ArrayList(hashSet);
    }

    @Override
    public void getSearchHistory() {
        ((BrowserSearchContract.Model) this.mModel).getSearchHistory().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<BrowserSearchBean>>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, List<BrowserSearchBean> list) {
                ((BrowserSearchContract.View) mView).updateSearchHistory(list);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getSearchHisTory"));
    }

    @Override
    public void getInterviewHistory() {
        ((BrowserSearchContract.Model) this.mModel).getInterviewHistory().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<DAppVisitHistoryBean>>() {
            @Override
            public void onResponse(String str, List<DAppVisitHistoryBean> list) {
                ((BrowserSearchContract.View) mView).updateInterviewHistoryView(list);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((BrowserSearchContract.View) mView).updateInterviewHistoryView(null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getInterviewHistory"));
    }

    @Override
    public void loadNewPage(String str, String str2, String str3) {
        loadNewPage(str, str2, str3, false);
    }

    @Override
    public void loadNewPage(final String str, final String str2, final String str3, boolean z) {
        if (checkUrlIsInDappList(str)) {
            if (!SpAPI.THIS.hasShowThirdAddressPopWindow(str)) {
                ((BrowserSearchContract.View) this.mView).showThirdAddressPopWindow(str, new SearchTipsPopCall() {
                    @Override
                    public void callBack() {
                        loadNewPageAction(str, str2, str3);
                    }
                });
                return;
            } else {
                loadNewPageAction(str, str2, str3);
                return;
            }
        }
        ((BrowserSearchContract.View) this.mView).showUnknownAddressPopWindow(str, new SearchTipsPopCall() {
            @Override
            public void callBack() {
                loadNewPageAction(str, str2, str3);
            }
        });
    }

    @Override
    public void loadNewPageWithOutUnknownPop(final String str, final String str2, final String str3, boolean z) {
        if (checkUrlIsInDappList(str)) {
            if (!SpAPI.THIS.hasShowThirdAddressPopWindow(str)) {
                ((BrowserSearchContract.View) this.mView).showThirdAddressPopWindow(str, new SearchTipsPopCall() {
                    @Override
                    public void callBack() {
                        loadNewPageAction(str, str2, str3);
                    }
                });
                return;
            } else {
                loadNewPageAction(str, str2, str3);
                return;
            }
        }
        loadNewPageAction(str, str2, str3);
    }

    @Override
    public void loadNewPageAction(String str, String str2, String str3) {
        DAppVisitHistoryController.getInstance().insertVisitHistory(str, str2, str3).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                BrowserSearchPresenter.lambda$loadNewPageAction$1((Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e(((Throwable) obj).toString());
            }
        });
        checkAuthAndEnter(str, str2, str3);
    }

    @Override
    public void doGoogleSearch(String str) {
        BrowserSearchHistoryManager.getInstance().addNewBean(str);
        String str2 = this.GOOGLE_SEARCH_URL + str;
        checkAuthAndEnter(str2, str, null);
        SpAPI.THIS.setDappName(Uri.parse(DappSearchPresenter.getFixedUrl(str2)).getHost());
    }

    @Override
    public void doTronScanSearch(final String str) {
        if (!SpAPI.THIS.hasShowThirdAddressPopWindow(str)) {
            doTronscanSearchAction(str);
        } else {
            ((BrowserSearchContract.View) this.mView).showThirdAddressPopWindow(str, new SearchTipsPopCall() {
                @Override
                public void callBack() {
                    doTronscanSearchAction(str);
                }
            });
        }
    }

    @Override
    protected void doTronscanSearchAction(final String str) {
        BrowserSearchHistoryManager.getInstance().addNewBean(str);
        if (!StringTronUtil.is64HexString(str)) {
            ((BrowserSearchContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$doTronscanSearchAction$3(str);
                }
            });
        } else {
            checkAuthAndEnter(IRequest.getTronscanSearchUrl(str, 2), str, null);
        }
    }

    public void lambda$doTronscanSearchAction$3(String str) {
        try {
            try {
                if (TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(str), false).getType().equals(Protocol.AccountType.Contract)) {
                    checkAuthAndEnter(IRequest.getTronscanSearchUrl(str, 1), str, null);
                } else {
                    checkAuthAndEnter(IRequest.getTronscanSearchUrl(str, 0), str, null);
                }
            } catch (Exception e) {
                checkAuthAndEnter(IRequest.getTronscanSearchUrl(str, 0), str, null);
                LogUtils.e(e);
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    private void checkAuthAndEnter(final String str, final String str2, final String str3) {
        if (this.mView != 0) {
            ((BrowserSearchContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$checkAuthAndEnter$4(str2, str, str3);
                }
            });
        }
    }

    public void lambda$checkAuthAndEnter$4(String str, String str2, String str3) {
        DappBean dappBean = new DappBean();
        dappBean.setName(str);
        dappBean.setHomeUrl(str2);
        dappBean.setImageUrl(str3);
        BrowserTabManager.getInstance().startURL(dappBean, false, true);
        ((BrowserSearchContract.View) this.mView).exit();
    }

    @Override
    public void clearSearchHistory() {
        ((BrowserSearchContract.Model) this.mModel).clearHistory().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, Boolean bool) {
                if (bool.booleanValue()) {
                    ((BrowserSearchContract.View) mView).updateSearchHistory(null);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "clearSearchHistory"));
    }

    @Override
    public void clearInterviewHistory() {
        ((BrowserSearchContract.Model) this.mModel).clearInterviewHistory().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, Boolean bool) {
                if (bool.booleanValue()) {
                    ((BrowserSearchContract.View) mView).updateInterviewHistoryView(null);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "clearInterviewHistory"));
    }

    @Override
    public void deleteInterviewHistoryItem(final DAppVisitHistoryBean dAppVisitHistoryBean, final int i) {
        ((BrowserSearchContract.Model) this.mModel).deleteInterviewHistoryItem(dAppVisitHistoryBean, i).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, Boolean bool) {
                if (bool.booleanValue()) {
                    ((BrowserSearchContract.View) mView).updateInterviewHistoryView(dAppVisitHistoryBean, i);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "deleteInterviewHistoryItem"));
    }

    public void loadAllDappUrl() {
        ((BrowserSearchContract.Model) this.mModel).getAllDappList().flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return BrowserSearchPresenter.lambda$loadAllDappUrl$5((DAppListOutput) obj);
            }
        }).subscribe(new IObserver(new ICallback<List<DappBean>>() {
            @Override
            public void onResponse(String str, List<DappBean> list) {
                allDAPPUrlList.clear();
                if (list == null || list.size() <= 0) {
                    return;
                }
                for (DappBean dappBean : list) {
                    allDAPPUrlList.add(dappBean.getHomeUrl());
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                allDAPPUrlList.clear();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "requestBanners"));
    }

    public static ObservableSource lambda$loadAllDappUrl$5(DAppListOutput dAppListOutput) throws Exception {
        if (dAppListOutput.message == null || dAppListOutput.data == null) {
            return Observable.just(new ArrayList());
        }
        return Observable.just(dAppListOutput.data);
    }

    public boolean checkUrlIsInDappList(String str) {
        String host = Uri.parse(DappSearchPresenter.getFixedUrl(str)).getHost();
        ArrayList<String> arrayList = this.allDAPPUrlList;
        if (arrayList != null && arrayList.size() != 0) {
            for (int i = 0; i < this.allDAPPUrlList.size(); i++) {
                if (this.allDAPPUrlList.get(i).contains(host)) {
                    return true;
                }
            }
        }
        return false;
    }
}
