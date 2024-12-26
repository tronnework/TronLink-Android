package com.tron.wallet.business.walletmanager.selectwallet.finance;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.finance.bean.FinanceDataSummaryOutput;
import com.tron.wallet.business.walletmanager.selectwallet.bean.FinanceSelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceContract;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectWalletFinancePresenter extends SelectWalletFinanceContract.Presenter {
    private String keyword;
    private PublishSubject<String> publishSubject;
    private Wallet selectedWallet;
    private WalletSortType walletSortType = WalletSortType.SORT_BY_TYPE;

    public void lambda$initPublisher$1(Throwable th) throws Exception {
        this.publishSubject = null;
    }

    @Override
    public void setSelectedWallet(Wallet wallet) {
        this.selectedWallet = wallet;
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
    public FinanceDataSummaryOutput.DataSummary getDataSummary() {
        return ((SelectWalletFinanceContract.Model) this.mModel).getFinanceDataSummary();
    }

    @Override
    public void onStart() {
        initPublisher();
        if (SpAPI.THIS.isCold()) {
            this.walletSortType = WalletSortType.SORT_BY_NONE;
        } else {
            this.walletSortType = WalletSortType.getTypeByValue(SpAPI.THIS.getWalletSortType());
        }
    }

    public void lambda$initPublisher$2(SelectWalletBean selectWalletBean) {
        selectWalletBean.setSelected(selectWalletBean.getWallet().getWalletName().equals(this.selectedWallet.getWalletName()) && selectWalletBean.getWallet().getAddress().equals(this.selectedWallet.getAddress()));
    }

    private void initPublisher() {
        PublishSubject<String> create = PublishSubject.create();
        this.publishSubject = create;
        addDisposable(create.switchMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$initPublisher$0;
                lambda$initPublisher$0 = lambda$initPublisher$0((String) obj);
                return lambda$initPublisher$0;
            }
        }).doOnError(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initPublisher$1((Throwable) obj);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initPublisher$3((List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initPublisher$4((Throwable) obj);
            }
        }));
    }

    public ObservableSource lambda$initPublisher$0(String str) throws Exception {
        return ((SelectWalletFinanceContract.Model) this.mModel).getSearchObservable(str, this.walletSortType);
    }

    public void lambda$initPublisher$3(List list) throws Exception {
        if (this.mView != 0) {
            if (this.selectedWallet != null && list != null) {
                Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$initPublisher$2((FinanceSelectWalletBean) obj);
                    }

                    public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }
            ((SelectWalletFinanceContract.IView) this.mView).onSearchComplete(list, this.keyword);
        }
    }

    public void lambda$initPublisher$4(Throwable th) throws Exception {
        LogUtils.e(th);
        if (this.mView != 0) {
            ((SelectWalletFinanceContract.IView) this.mView).onSearchComplete(new ArrayList(), this.keyword);
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
