package com.tron.wallet.business.addassets2.selecttoken;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.adapter.BeanExtraData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.selecttoken.SelectTokenContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class SelectTokenPresenter implements SelectTokenContract.Presenter {
    private static final int PAGE_SIZE = 20;
    public static final String TAG = "SelectTokenController";
    private String address;
    private boolean isMultiSign;
    private String lastKeyWord;
    private List<TokenBean> localTokenBeans;
    private PublishSubject<String> mPublishSubject;
    private RxManager mRxManager;
    private SelectTokenContract.View mView;
    private TokenBean selectedTokenBean;
    private AtomicInteger pageIndex = new AtomicInteger(1);
    private volatile String searchKeyWord = "";
    private int filterType = 0;

    @Override
    public String getSearchedKeyword() {
        return this.lastKeyWord;
    }

    @Override
    public void setFilterType(int i) {
        this.filterType = i;
    }

    @Override
    public void start(SelectTokenContract.View view, TokenBean tokenBean, String str, boolean z) {
        this.mRxManager = new RxManager();
        this.mView = view;
        this.selectedTokenBean = tokenBean;
        this.address = str;
        this.isMultiSign = z;
        this.localTokenBeans = new ArrayList();
        initSubject();
        getLocalTokenBeans();
    }

    @Override
    public void destroy() {
        this.mRxManager.clear();
    }

    public void updateAssetsView(final List<TokenBean> list, final boolean z) {
        this.mRxManager.add(Observable.just(list).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$updateAssetsView$0(list, z, (List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SelectTokenPresenter.lambda$updateAssetsView$1((Throwable) obj);
            }
        }));
    }

    public void lambda$updateAssetsView$0(List list, boolean z, List list2) throws Exception {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            TokenBean tokenBean = (TokenBean) it.next();
            if (AssetsManager.sameTokenBean(tokenBean, this.selectedTokenBean)) {
                tokenBean.isSelected = true;
            } else {
                tokenBean.isSelected = false;
            }
        }
        this.mView.updateSearchedAssets(list2);
        if (list.size() < 20 || !z) {
            this.mView.loadMoreComplete(new ArrayList());
            this.mView.loadMoreDone();
        }
    }

    public static void lambda$updateAssetsView$1(Throwable th) throws Exception {
        LogUtils.e(th);
        SentryUtil.captureException(th);
    }

    public void initSubject() {
        PublishSubject<String> create = PublishSubject.create();
        this.mPublishSubject = create;
        create.filter(new Predicate() {
            @Override
            public final boolean test(Object obj) {
                return SelectTokenPresenter.lambda$initSubject$2((String) obj);
            }
        }).switchMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$initSubject$3;
                lambda$initSubject$3 = lambda$initSubject$3((String) obj);
                return lambda$initSubject$3;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<AssetsDataOutput>() {
            @Override
            public void onResponse(String str, AssetsDataOutput assetsDataOutput) {
                List<TokenBean> tokens = assetsDataOutput.getData() != null ? assetsDataOutput.getData().getTokens() : new ArrayList<>();
                if (mView == null || lastKeyWord == null) {
                    return;
                }
                if (assetsDataOutput == null || assetsDataOutput.getData() == null || lastKeyWord.equalsIgnoreCase(assetsDataOutput.getData().getWord())) {
                    updateAssetsView(tokens, true);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (mView != null && lastKeyWord != null) {
                    mView.showNoNetView();
                }
                initSubject();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, ""));
    }

    public static boolean lambda$initSubject$2(String str) throws Exception {
        return str.length() > 0;
    }

    public ObservableSource lambda$initSubject$3(String str) throws Exception {
        this.searchKeyWord = str;
        this.pageIndex.set(1);
        return requestSearchAssets(str, this.pageIndex.get(), 20, this.filterType);
    }

    @Override
    public void searchAssets(String str, int i, int i2) {
        this.lastKeyWord = str;
        if (!StringTronUtil.isEmpty(str)) {
            this.mPublishSubject.onNext(str);
        } else {
            getLocalTokenBeans();
        }
    }

    public void updateMoreAssetsView(final List<TokenBean> list) {
        this.mRxManager.add(Observable.just(list).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$updateMoreAssetsView$4(list, (List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SelectTokenPresenter.lambda$updateMoreAssetsView$5((Throwable) obj);
            }
        }));
    }

    public void lambda$updateMoreAssetsView$4(List list, List list2) throws Exception {
        Iterator it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TokenBean tokenBean = (TokenBean) it.next();
            if (AssetsManager.sameTokenBean(tokenBean, this.selectedTokenBean)) {
                tokenBean.isSelected = true;
                break;
            }
        }
        this.mView.loadMoreComplete(list);
        if (list.size() < 20) {
            this.mView.loadMoreDone();
        }
    }

    public static void lambda$updateMoreAssetsView$5(Throwable th) throws Exception {
        LogUtils.e(th);
        SentryUtil.captureException(th);
    }

    @Override
    public void loadMoreSearchResult(String str) {
        if (TextUtils.isEmpty(this.searchKeyWord) || !TextUtils.equals(this.searchKeyWord, str)) {
            return;
        }
        requestSearchAssets(this.searchKeyWord, this.pageIndex.incrementAndGet(), 20, this.filterType).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<AssetsDataOutput>() {
            @Override
            public void onResponse(String str2, AssetsDataOutput assetsDataOutput) {
                if (assetsDataOutput == null || assetsDataOutput.getData() == null || assetsDataOutput.getData().getTokens() == null || assetsDataOutput.getData().getTokens().isEmpty()) {
                    mView.loadMoreDone();
                } else {
                    updateMoreAssetsView(assetsDataOutput.getData().getTokens());
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                mView.loadMoreFail();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, ""));
    }

    private void getLocalTokenBeans() {
        List<TokenBean> list = this.localTokenBeans;
        if (list != null && list.size() > 0) {
            updateAssetsView((List) Collection.-EL.stream(this.localTokenBeans).filter(new java.util.function.Predicate() {
                public java.util.function.Predicate and(java.util.function.Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public java.util.function.Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public java.util.function.Predicate or(java.util.function.Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    boolean lambda$getLocalTokenBeans$6;
                    lambda$getLocalTokenBeans$6 = lambda$getLocalTokenBeans$6((TokenBean) obj);
                    return lambda$getLocalTokenBeans$6;
                }
            }).collect(Collectors.toList()), false);
            return;
        }
        this.mRxManager.add(LocalTokenBeansHelper.getSortedTokens(this.address, this.isMultiSign).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getLocalTokenBeans$8((List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e((Throwable) obj);
            }
        }));
    }

    public boolean lambda$getLocalTokenBeans$6(TokenBean tokenBean) {
        return this.filterType == 0 || tokenBean.getType() == this.filterType;
    }

    public void lambda$getLocalTokenBeans$8(List list) throws Exception {
        this.localTokenBeans = list;
        updateAssetsView((List) Collection.-EL.stream(list).filter(new java.util.function.Predicate() {
            public java.util.function.Predicate and(java.util.function.Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public java.util.function.Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public java.util.function.Predicate or(java.util.function.Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                boolean lambda$getLocalTokenBeans$7;
                lambda$getLocalTokenBeans$7 = lambda$getLocalTokenBeans$7((TokenBean) obj);
                return lambda$getLocalTokenBeans$7;
            }
        }).collect(Collectors.toList()), false);
    }

    public boolean lambda$getLocalTokenBeans$7(TokenBean tokenBean) {
        return this.filterType == 0 || tokenBean.getType() == this.filterType;
    }

    private Observable<AssetsDataOutput> requestSearchAssets(final String str, int i, int i2, int i3) {
        return AssetsManager.getInstance().requestSearchAssets(this.address, str, i, i2, i3).subscribeOn(Schedulers.io()).doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestSearchAssets$10(str, (AssetsDataOutput) obj);
            }
        });
    }

    public void lambda$requestSearchAssets$10(String str, AssetsDataOutput assetsDataOutput) throws Exception {
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
