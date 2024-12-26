package com.tron.wallet.business.walletmanager.selectwallet;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.selectwallet.SelectWalletContract;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.controller.WalletController;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectWalletPresenter extends SelectWalletContract.Presenter {
    private Wallet selectedWallet;
    private boolean showShieldWallet;
    private List<SelectWalletBean> walletBeans;
    private WalletSortType walletSortType = WalletSortType.SORT_BY_TYPE;

    @Override
    public WalletSortType getWalletSortType() {
        return this.walletSortType;
    }

    @Override
    protected void onStart() {
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
        getWallets();
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        getWallets();
    }

    @Override
    public int getWalletsSize() {
        List<SelectWalletBean> list = this.walletBeans;
        int i = 0;
        if (list != null) {
            for (SelectWalletBean selectWalletBean : list) {
                if (selectWalletBean.getGroupType() != WalletGroupType.RECENTLY) {
                    i = (!selectWalletBean.isHdGroup() || selectWalletBean.getRelationWallets() == null) ? i + 1 : i + selectWalletBean.getRelationWallets().size();
                }
            }
        }
        return i;
    }

    @Override
    public void setShowShieldWallet(boolean z) {
        this.showShieldWallet = z;
        if (z) {
            this.walletSortType = WalletSortType.SORT_BY_NONE;
        }
    }

    @Override
    public void getWallets() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        this.selectedWallet = selectedPublicWallet;
        WalletController.getSortedWalletObservable(selectedPublicWallet, this.walletSortType, true, false).doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getWallets$2((List) obj);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<SelectWalletBean>>() {
            @Override
            public void onResponse(String str, List<SelectWalletBean> list) {
                walletBeans = list;
                if (mView != 0) {
                    ((SelectWalletContract.View) mView).updateData(list);
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
        }, "getWallets"));
    }

    @Override
    public void setWalletSortType(WalletSortType walletSortType) {
        this.walletSortType = walletSortType;
        SpAPI.THIS.setWalletSortType(walletSortType.getValue());
        getWallets();
    }

    public void lambda$getWallets$2(List<SelectWalletBean> list) {
        WalletController.getAccountInfoObservable(list, this.walletSortType).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<List<SelectWalletBean>>() {
            @Override
            public void onResponse(String str, List<SelectWalletBean> list2) {
                if (mView != 0) {
                    ((SelectWalletContract.View) mView).updateData(list2);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (mView != 0) {
                    ((SelectWalletContract.View) mView).refreshData();
                    if (SpAPI.THIS.isCold() || IRequest.isShasta()) {
                        return;
                    }
                    ((SelectWalletContract.View) mView).toast(((SelectWalletContract.View) mView).getIContext().getString(R.string.time_out));
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getAccountInfo"));
    }
}
