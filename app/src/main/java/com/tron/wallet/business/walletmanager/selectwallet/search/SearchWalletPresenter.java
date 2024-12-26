package com.tron.wallet.business.walletmanager.selectwallet.search;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.business.walletmanager.selectwallet.search.SearchWalletContract;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.tron.walletserver.Wallet;
public class SearchWalletPresenter extends SearchWalletContract.Presenter {
    private String keyword;
    private PublishSubject<String> publishSubject;
    private Wallet selectedWallet;
    private WalletSortType walletSortType = WalletSortType.SORT_BY_TYPE;

    public void lambda$initPublisher$4(Throwable th) throws Exception {
        this.publishSubject = null;
    }

    @Override
    public void search(String str) {
        if (str == null) {
            str = "";
        }
        this.keyword = str;
        if (this.publishSubject == null) {
            initPublisher();
        }
        PublishSubject<String> publishSubject = this.publishSubject;
        if (publishSubject != null) {
            publishSubject.onNext(this.keyword);
        }
    }

    @Override
    public void onStart() {
        this.selectedWallet = WalletUtils.getSelectedPublicWallet();
        initPublisher();
        this.mRxManager.on(Event.DELETE_WALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        if (SpAPI.THIS.isCold()) {
            this.walletSortType = WalletSortType.SORT_BY_NONE;
        } else {
            this.walletSortType = WalletSortType.getTypeByValue(SpAPI.THIS.getWalletSortType());
        }
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        this.selectedWallet = WalletUtils.getSelectedPublicWallet();
        getRecentWallets();
        if (StringTronUtil.isEmpty(this.keyword)) {
            return;
        }
        search(this.keyword);
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        try {
            this.selectedWallet = WalletUtils.getSelectedPublicWallet();
            getRecentWallets();
            if (StringTronUtil.isEmpty(this.keyword)) {
                return;
            }
            search(this.keyword);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$initPublisher$5(SelectWalletBean selectWalletBean) {
        selectWalletBean.setSelected(selectWalletBean.getWallet().getWalletName().equals(this.selectedWallet.getWalletName()) && selectWalletBean.getWallet().getAddress().equals(this.selectedWallet.getAddress()));
    }

    @Override
    public void getRecentWallets() {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                List lambda$getRecentWallets$2;
                lambda$getRecentWallets$2 = lambda$getRecentWallets$2();
                return lambda$getRecentWallets$2;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<SelectWalletBean>>() {
            @Override
            public void onResponse(String str, List<SelectWalletBean> list) {
                if (mView != 0) {
                    ((SearchWalletContract.IView) mView).updateRecentWallets(list);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(str, str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getRecentWallets"));
    }

    public List lambda$getRecentWallets$2() throws Exception {
        return RecentlyWalletController.getRecentlyWalletBeans(this.selectedWallet, TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryAll());
    }

    @Override
    public void getAccountInfoList() {
        ((SearchWalletContract.Model) this.mModel).getAccountInfo().compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<SelectWalletBean>>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, List<SelectWalletBean> list) {
                if (mView != 0) {
                    ((SearchWalletContract.IView) mView).updateAccountInfo(list);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getAccountInfoList"));
    }

    private void initPublisher() {
        PublishSubject<String> create = PublishSubject.create();
        this.publishSubject = create;
        addDisposable(create.switchMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$initPublisher$3;
                lambda$initPublisher$3 = lambda$initPublisher$3((String) obj);
                return lambda$initPublisher$3;
            }
        }).doOnError(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initPublisher$4((Throwable) obj);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initPublisher$6((List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initPublisher$7((Throwable) obj);
            }
        }));
    }

    public ObservableSource lambda$initPublisher$3(String str) throws Exception {
        return ((SearchWalletContract.Model) this.mModel).getSearchObservable(str, this.walletSortType);
    }

    public void lambda$initPublisher$6(List list) throws Exception {
        if (this.mView != 0) {
            if (this.selectedWallet != null && list != null) {
                Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$initPublisher$5((SelectWalletBean) obj);
                    }

                    public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }
            ((SearchWalletContract.IView) this.mView).onSearchComplete(list, this.keyword);
        }
    }

    public void lambda$initPublisher$7(Throwable th) throws Exception {
        LogUtils.e(th);
        if (this.mView != 0) {
            ((SearchWalletContract.IView) this.mView).onSearchComplete(new ArrayList(), this.keyword);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PublishSubject<String> publishSubject = this.publishSubject;
        if (publishSubject != null) {
            publishSubject.onComplete();
        }
    }
}
