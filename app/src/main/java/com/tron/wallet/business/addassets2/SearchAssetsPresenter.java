package com.tron.wallet.business.addassets2;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.SearchAssetsContract;
import com.tron.wallet.business.addassets2.SearchAssetsPresenter;
import com.tron.wallet.business.addassets2.adapter.BeanExtraData;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.asset.RecommendAssetsHomeOutput;
import com.tron.wallet.business.nft.NftTokenListActivity;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class SearchAssetsPresenter extends SearchAssetsContract.Presenter {
    private static final int PAGE_SIZE = 20;
    public static final String TAG = "SearchAssetsPresenter";
    private String address;
    private String lastKeyWord;
    private PublishSubject<String> mPublishSubject;
    private AtomicInteger pageIndex = new AtomicInteger(1);
    private volatile String searchKeyWord = "";
    private volatile boolean flagHasCache = false;
    private int filterType = 0;

    public String getSearchKeyWord() {
        return this.searchKeyWord;
    }

    @Override
    public void setFilterType(int i) {
        this.filterType = i;
    }

    @Override
    protected void onStart() {
        this.address = WalletUtils.getSelectedWallet().getAddress();
        this.mRxManager.on(RB.RB_PRICE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        initSubject();
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((SearchAssetsContract.View) this.mView).updateAssetsPrice();
    }

    public void updateAssetsView(final List<TokenBean> list, final boolean z) {
        this.mRxManager.add(Observable.just(list).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AssetsManager.getInstance().refineFollowAssetsState((List) obj);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$updateAssetsView$2(z, list, (List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SearchAssetsPresenter.lambda$updateAssetsView$3((Throwable) obj);
            }
        }));
    }

    public void lambda$updateAssetsView$2(boolean z, List list, List list2) throws Exception {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            TokenBean tokenBean = (TokenBean) it.next();
            if (tokenBean.getIsInAssets()) {
                tokenBean.isSelected = true;
            } else {
                tokenBean.isSelected = false;
            }
        }
        if (z) {
            ((SearchAssetsContract.View) this.mView).showHotAssets(list);
            return;
        }
        ((SearchAssetsContract.View) this.mView).updateSearchedAssets(list2);
        if (list.size() < 20) {
            ((SearchAssetsContract.View) this.mView).loadMoreComplete(new ArrayList());
            ((SearchAssetsContract.View) this.mView).loadMoreDone();
        }
    }

    public static void lambda$updateAssetsView$3(Throwable th) throws Exception {
        LogUtils.e(th);
        SentryUtil.captureException(th);
    }

    public void initSubject() {
        PublishSubject<String> create = PublishSubject.create();
        this.mPublishSubject = create;
        create.filter(new Predicate() {
            @Override
            public final boolean test(Object obj) {
                return SearchAssetsPresenter.lambda$initSubject$4((String) obj);
            }
        }).switchMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$initSubject$5;
                lambda$initSubject$5 = lambda$initSubject$5((String) obj);
                return lambda$initSubject$5;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<AssetsDataOutput>() {
            @Override
            public void onResponse(String str, AssetsDataOutput assetsDataOutput) {
                List<TokenBean> tokens = assetsDataOutput.getData() != null ? assetsDataOutput.getData().getTokens() : new ArrayList<>();
                if (mView == 0 || lastKeyWord == null) {
                    return;
                }
                if (tokens != null && !tokens.isEmpty()) {
                    updateAssetsView(tokens, false);
                } else if (pageIndex.get() == 1) {
                    ((SearchAssetsContract.View) mView).showNoDatasPage();
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (mView != 0 && lastKeyWord != null) {
                    ((SearchAssetsContract.View) mView).showNoNetView();
                }
                initSubject();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, ""));
    }

    public static boolean lambda$initSubject$4(String str) throws Exception {
        return str.length() > 0;
    }

    public ObservableSource lambda$initSubject$5(String str) throws Exception {
        this.searchKeyWord = str;
        this.pageIndex.set(1);
        return requestSearchAssets(str, this.pageIndex.get(), 20, this.filterType);
    }

    @Override
    public void getHotAssets() {
        ((SearchAssetsContract.Model) this.mModel).getHotAssets().subscribe(new IObserver(new ICallback<AssetsData>() {
            @Override
            public void onResponse(String str, AssetsData assetsData) {
                if (assetsData != null && assetsData.getCount() > 0) {
                    LogUtils.d(SearchAssetsPresenter.TAG, "getHotAssets:" + assetsData.getCount());
                    updateAssetsView(assetsData.getTokens(), true);
                    flagHasCache = true;
                    return;
                }
                onFailure(str, "No cached hot assets .");
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(SearchAssetsPresenter.TAG, "getHotAssets:" + str2);
                flagHasCache = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getHotAssets"));
        ((SearchAssetsContract.Model) this.mModel).requestHotAssets().subscribe(new IObserver(new fun3(), "requestHotAssets"));
    }

    public class fun3 implements ICallback<RecommendAssetsHomeOutput> {
        fun3() {
        }

        @Override
        public void onResponse(String str, RecommendAssetsHomeOutput recommendAssetsHomeOutput) {
            if (recommendAssetsHomeOutput == null || recommendAssetsHomeOutput.code != 0 || recommendAssetsHomeOutput.data == null) {
                return;
            }
            List<TokenBean> list = recommendAssetsHomeOutput.data;
            AssetsData assetsData = new AssetsData();
            if (list != null && list.size() > 0) {
                updateAssetsView(list, true);
                for (TokenBean tokenBean : list) {
                    tokenBean.setAddress(address);
                    tokenBean.setUsageType(2);
                }
                assetsData.setCount(list.size());
                assetsData.setTokens(list);
            }
            ((SearchAssetsContract.Model) mModel).saveHotAssets(assetsData).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    LogUtils.d(SearchAssetsPresenter.TAG, "save hot tokens:" + ((Boolean) obj));
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SearchAssetsPresenter.3.lambda$onResponse$1((Throwable) obj);
                }
            });
        }

        public static void lambda$onResponse$1(Throwable th) throws Exception {
            LogUtils.d(SearchAssetsPresenter.TAG, "save hot tokens:" + th);
            SentryUtil.captureException(th);
        }

        @Override
        public void onFailure(String str, String str2) {
            if (flagHasCache) {
                return;
            }
            ((SearchAssetsContract.View) mView).showNoNetView();
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            mRxManager.add(disposable);
        }
    }

    @Override
    public void searchAssets(String str, int i, int i2) {
        this.lastKeyWord = str;
        if (str != null) {
            this.mPublishSubject.onNext(str);
        }
    }

    @Override
    public void followAssets(TokenBean tokenBean, int i, boolean z) {
        if (tokenBean.isSelected || this.address == null) {
            return;
        }
        tokenBean.isSelected = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(tokenBean);
        ((SearchAssetsContract.Model) this.mModel).followAssets(arrayList, null).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onResponse(String str, FollowAssetsOutput followAssetsOutput) {
                StringBuilder sb = new StringBuilder("unFollowAssets result:");
                sb.append(followAssetsOutput != null ? Boolean.valueOf(followAssetsOutput.isData()) : "null");
                LogUtils.d(SearchAssetsPresenter.TAG, sb.toString());
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(SearchAssetsPresenter.TAG, "unFollowAssets result:" + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "ignoreAllNewAssets"));
        ((SearchAssetsContract.View) this.mView).showAssetsAddedView();
        ((SearchAssetsContract.View) this.mView).updateAssetsFollowState(tokenBean, i, z);
    }

    @Override
    public void showAssetsDetail(TokenBean tokenBean) {
        if (tokenBean.getType() == 5) {
            NftTokenListActivity.start(((SearchAssetsContract.View) this.mView).getIContext(), this.address, tokenBean);
        } else {
            TokenDetailActivity.start(((SearchAssetsContract.View) this.mView).getIContext(), tokenBean, SpAPI.THIS.getPrice());
        }
    }

    @Override
    public void reloadSearch() {
        if (TextUtils.isEmpty(this.searchKeyWord)) {
            return;
        }
        searchAssets(this.searchKeyWord, 1, 20);
    }

    public void updateMoreAssetsView(final List<TokenBean> list) {
        this.mRxManager.add(Observable.just(list).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AssetsManager.getInstance().refineFollowAssetsState((List) obj);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$updateMoreAssetsView$7(list, (List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SearchAssetsPresenter.lambda$updateMoreAssetsView$8((Throwable) obj);
            }
        }));
    }

    public void lambda$updateMoreAssetsView$7(List list, List list2) throws Exception {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            TokenBean tokenBean = (TokenBean) it.next();
            if (tokenBean.getIsInAssets()) {
                tokenBean.isSelected = true;
            } else {
                tokenBean.isSelected = false;
            }
        }
        ((SearchAssetsContract.View) this.mView).loadMoreComplete(list);
        if (list.size() < 20) {
            ((SearchAssetsContract.View) this.mView).loadMoreDone();
        }
    }

    public static void lambda$updateMoreAssetsView$8(Throwable th) throws Exception {
        LogUtils.e(th);
        SentryUtil.captureException(th);
    }

    public void loadMoreSearchResult(String str) {
        if (TextUtils.isEmpty(this.searchKeyWord) || !TextUtils.equals(this.searchKeyWord, str)) {
            return;
        }
        requestSearchAssets(this.searchKeyWord, this.pageIndex.incrementAndGet(), 20, 0).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<AssetsDataOutput>() {
            @Override
            public void onResponse(String str2, AssetsDataOutput assetsDataOutput) {
                if (assetsDataOutput == null || assetsDataOutput.getData() == null || assetsDataOutput.getData().getTokens() == null || assetsDataOutput.getData().getTokens().isEmpty()) {
                    ((SearchAssetsContract.View) mView).loadMoreDone();
                } else {
                    updateMoreAssetsView(assetsDataOutput.getData().getTokens());
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((SearchAssetsContract.View) mView).loadMoreFail();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, ""));
    }

    private Observable<AssetsDataOutput> requestSearchAssets(final String str, int i, int i2, int i3) {
        return ((SearchAssetsContract.Model) this.mModel).requestSearchAssets(str, i, i2, i3).subscribeOn(Schedulers.io()).doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestSearchAssets$9(str, (AssetsDataOutput) obj);
            }
        });
    }

    public void lambda$requestSearchAssets$9(String str, AssetsDataOutput assetsDataOutput) throws Exception {
        if (assetsDataOutput == null || !str.equals(this.lastKeyWord) || assetsDataOutput.getData() == null || assetsDataOutput.getData().getTokens() == null) {
            return;
        }
        String lowerCase = str.toLowerCase();
        for (TokenBean tokenBean : assetsDataOutput.getData().getTokens()) {
            BeanExtraData beanExtraData = new BeanExtraData();
            beanExtraData.setKeyword(lowerCase);
            if (tokenBean.getShortName() != null && tokenBean.getShortName().toLowerCase().contains(lowerCase)) {
                beanExtraData.setType(BeanExtraData.Type.SYMBOL);
                tokenBean.setExtraData(beanExtraData);
            } else if (tokenBean.getName() != null && tokenBean.getName().toLowerCase().contains(lowerCase)) {
                beanExtraData.setType(BeanExtraData.Type.NAME);
                tokenBean.setExtraData(beanExtraData);
            } else if (tokenBean.getId() != null && tokenBean.getId().equalsIgnoreCase(lowerCase)) {
                beanExtraData.setType(BeanExtraData.Type.ID);
                tokenBean.setExtraData(beanExtraData);
            } else {
                beanExtraData.setType(BeanExtraData.Type.ADDRESS);
                tokenBean.setExtraData(beanExtraData);
            }
        }
    }
}
