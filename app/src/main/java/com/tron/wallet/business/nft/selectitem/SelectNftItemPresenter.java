package com.tron.wallet.business.nft.selectitem;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.nft.selectitem.SelectNftItemContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class SelectNftItemPresenter implements SelectNftItemContract.Presenter {
    private static final int PAGE_SIZE = 20;
    public static final String TAG = "SelectTokenController";
    private String address;
    private String lastKeyWord;
    private PublishSubject<String> mPublishSubject;
    private RxManager mRxManager;
    private SelectNftItemContract.View mView;
    private AtomicInteger pageIndex = new AtomicInteger(0);
    private volatile String searchKeyWord = "";
    private NftItemInfo selectedItemInfo;
    private TokenBean selectedTokenBean;

    @Override
    public String getSearchedKeyword() {
        return this.searchKeyWord;
    }

    @Override
    public void start(SelectNftItemContract.View view, TokenBean tokenBean, NftItemInfo nftItemInfo, String str) {
        this.mRxManager = new RxManager();
        this.mView = view;
        this.selectedTokenBean = tokenBean;
        this.selectedItemInfo = nftItemInfo;
        this.address = str;
        initSubject();
        loadMoreSearchResult(null);
    }

    @Override
    public void destroy() {
        this.mRxManager.clear();
    }

    public void initSubject() {
        PublishSubject<String> create = PublishSubject.create();
        this.mPublishSubject = create;
        create.filter(new Predicate() {
            @Override
            public final boolean test(Object obj) {
                return SelectNftItemPresenter.lambda$initSubject$0((String) obj);
            }
        }).switchMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$initSubject$1;
                lambda$initSubject$1 = lambda$initSubject$1((String) obj);
                return lambda$initSubject$1;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<NftInfoOutput>() {
            @Override
            public void onResponse(String str, NftInfoOutput nftInfoOutput) {
                List<NftItemInfo> arrayList;
                if (mView == null || lastKeyWord == null) {
                    return;
                }
                if (nftInfoOutput == null || nftInfoOutput.getData() == null || lastKeyWord.equalsIgnoreCase(nftInfoOutput.getData().getWord())) {
                    if (nftInfoOutput == null || nftInfoOutput.getData() == null || nftInfoOutput.getData().getCollectionInfoList() == null || nftInfoOutput.getData().getCollectionInfoList().isEmpty()) {
                        arrayList = new ArrayList<>();
                    } else {
                        arrayList = nftInfoOutput.getData().getCollectionInfoList();
                    }
                    SelectNftItemPresenter selectNftItemPresenter = SelectNftItemPresenter.this;
                    selectNftItemPresenter.updateMoreAssetsView(arrayList, selectNftItemPresenter.pageIndex.get() == 0, true ^ StringTronUtil.isEmpty(lastKeyWord));
                    pageIndex.getAndIncrement();
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

    public static boolean lambda$initSubject$0(String str) throws Exception {
        return str.length() > 0;
    }

    public ObservableSource lambda$initSubject$1(String str) throws Exception {
        this.searchKeyWord = str;
        this.pageIndex.set(0);
        return requestSearchNftInfo(str, this.pageIndex.get(), 20);
    }

    @Override
    public void searchAssets(String str, int i, int i2) {
        this.lastKeyWord = str;
        if (!StringTronUtil.isEmpty(str)) {
            this.mPublishSubject.onNext(str);
            return;
        }
        this.pageIndex.set(0);
        loadMoreSearchResult(str);
    }

    public void updateMoreAssetsView(List<NftItemInfo> list, boolean z, boolean z2) {
        if (z) {
            this.mView.updateSearchedAssets(list);
        } else {
            this.mView.loadMoreComplete(list);
        }
        if (list.size() < 20 || z2) {
            this.mView.loadMoreComplete(new ArrayList());
            this.mView.loadMoreDone();
        }
    }

    @Override
    public void loadMoreSearchResult(final String str) {
        requestSearchNftInfo(str, this.pageIndex.get(), 20).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<NftInfoOutput>() {
            @Override
            public void onResponse(String str2, NftInfoOutput nftInfoOutput) {
                List<NftItemInfo> arrayList;
                if (nftInfoOutput == null || nftInfoOutput.getData() == null || nftInfoOutput.getData().getCollectionInfoList() == null || nftInfoOutput.getData().getCollectionInfoList().isEmpty()) {
                    arrayList = new ArrayList<>();
                } else {
                    arrayList = nftInfoOutput.getData().getCollectionInfoList();
                }
                SelectNftItemPresenter selectNftItemPresenter = SelectNftItemPresenter.this;
                selectNftItemPresenter.updateMoreAssetsView(arrayList, selectNftItemPresenter.pageIndex.get() == 0, true ^ StringTronUtil.isEmpty(str));
                pageIndex.getAndIncrement();
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

    private Observable<NftInfoOutput> requestSearchNftInfo(String str, int i, int i2) {
        if (!StringTronUtil.isEmpty(str)) {
            return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).queryCollection(this.address, this.selectedTokenBean.getContractAddress(), str).subscribeOn(Schedulers.io());
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollection(this.address, this.selectedTokenBean.getContractAddress(), i, i2).subscribeOn(Schedulers.io());
    }
}
