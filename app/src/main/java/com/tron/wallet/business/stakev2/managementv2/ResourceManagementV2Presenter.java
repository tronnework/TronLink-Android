package com.tron.wallet.business.stakev2.managementv2;

import android.util.Pair;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Contract;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class ResourceManagementV2Presenter extends ResourceManagementV2Contract.Presenter {
    private Protocol.Account account;
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
    private String controllerAddress;
    private boolean multiSign;
    private boolean refreshedResult = true;
    private Wallet wallet;

    @Override
    public Protocol.Account getAccount() {
        return this.account;
    }

    @Override
    public GrpcAPI.AccountResourceMessage getAccountResourceMessage() {
        return this.accountResourceMessage;
    }

    @Override
    public void setConfig(boolean z, String str) {
        this.multiSign = z;
        this.controllerAddress = str;
    }

    @Override
    protected void onStart() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.wallet = selectedWallet;
        if (selectedWallet == null) {
            ((ResourceManagementV2Contract.View) this.mView).exit();
            return;
        }
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$3(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (!TronConfig.hasNet || this.refreshedResult) {
            return;
        }
        refreshAccountResources();
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        ResourceManagementV2Activity resourceManagementV2Activity = (ResourceManagementV2Activity) ((ResourceManagementV2Contract.View) this.mView).getIContext();
        TronApplication tronApplication = (TronApplication) AppContextUtil.getmApplication();
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        ((ResourceManagementV2Contract.View) this.mView).exit();
    }

    public void lambda$onStart$3(Object obj) throws Exception {
        refreshAccountResources();
    }

    @Override
    public void start() {
        if (this.wallet.getAddress().equals(this.controllerAddress)) {
            this.account = WalletUtils.getAccount(((ResourceManagementV2Contract.View) this.mView).getIContext(), this.wallet.getWalletName());
            this.accountResourceMessage = WalletUtils.getAccountRes(((ResourceManagementV2Contract.View) this.mView).getIContext(), this.wallet.getWalletName());
            Protocol.Account account = this.account;
            if (account != null && account.toString().length() > 0) {
                ((ResourceManagementV2Contract.View) this.mView).updateUI(ResourcesBean.buildV2(this.account, this.accountResourceMessage));
            }
        }
        refreshAccountResources();
    }

    @Override
    public void refreshAccountResources() {
        Observable.just(this.multiSign ? this.controllerAddress : this.wallet.getAddress()).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$refreshAccountResources$4;
                lambda$refreshAccountResources$4 = lambda$refreshAccountResources$4((String) obj);
                return lambda$refreshAccountResources$4;
            }
        }).doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$refreshAccountResources$5((Pair) obj);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Pair<Protocol.Account, GrpcAPI.AccountResourceMessage>>() {
            @Override
            public void onResponse(String str, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
                if (mView == 0 || pair == null) {
                    return;
                }
                refreshedResult = true;
                account = (Protocol.Account) pair.first;
                accountResourceMessage = (GrpcAPI.AccountResourceMessage) pair.second;
                ((ResourceManagementV2Contract.View) mView).updateUI(ResourcesBean.buildV2(account, accountResourceMessage));
            }

            @Override
            public void onFailure(String str, String str2) {
                refreshedResult = false;
                if (mView != 0) {
                    ((ResourceManagementV2Contract.View) mView).toast(((ResourceManagementV2Contract.View) mView).getIContext().getResources().getString(R.string.get_account_error));
                    ((ResourceManagementV2Contract.View) mView).updateUI(null);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "refresh"));
    }

    public ObservableSource lambda$refreshAccountResources$4(String str) throws Exception {
        byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(str);
        this.account = TronAPI.queryAccount(decodeFromBase58Check, false);
        GrpcAPI.AccountResourceMessage accountRes = TronAPI.getAccountRes(decodeFromBase58Check);
        this.accountResourceMessage = accountRes;
        return Observable.just(Pair.create(this.account, accountRes));
    }

    public void lambda$refreshAccountResources$5(Pair pair) throws Exception {
        getCanDelegatedResourceMaxSize();
    }

    private void getCanDelegatedResourceMaxSize() {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Long lambda$getCanDelegatedResourceMaxSize$6;
                lambda$getCanDelegatedResourceMaxSize$6 = lambda$getCanDelegatedResourceMaxSize$6();
                return lambda$getCanDelegatedResourceMaxSize$6;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Long>() {
            @Override
            public void onResponse(String str, Long l) {
                if (mView != 0) {
                    ((ResourceManagementV2Contract.View) mView).updateUI(1, ResourcesBean.stakedTrxToEnergy(accountResourceMessage, l.longValue() / 1000000), l.longValue() / 1000000);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (mView != 0) {
                    ((ResourceManagementV2Contract.View) mView).toast(((ResourceManagementV2Contract.View) mView).getIContext().getResources().getString(R.string.get_account_error));
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "query can delegated"));
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Long lambda$getCanDelegatedResourceMaxSize$7;
                lambda$getCanDelegatedResourceMaxSize$7 = lambda$getCanDelegatedResourceMaxSize$7();
                return lambda$getCanDelegatedResourceMaxSize$7;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Long>() {
            @Override
            public void onResponse(String str, Long l) {
                if (mView != 0) {
                    ((ResourceManagementV2Contract.View) mView).updateUI(0, ResourcesBean.stakedTrxToBandwidth(accountResourceMessage, l.longValue() / 1000000), l.longValue() / 1000000);
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (mView != 0) {
                    ((ResourceManagementV2Contract.View) mView).toast(((ResourceManagementV2Contract.View) mView).getIContext().getResources().getString(R.string.get_account_error));
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "query can delegated"));
    }

    public Long lambda$getCanDelegatedResourceMaxSize$6() throws Exception {
        return Long.valueOf(TronAPI.getCanDelegatedMaxSize(1, this.multiSign ? this.controllerAddress : this.wallet.getAddress()).getMaxSize());
    }

    public Long lambda$getCanDelegatedResourceMaxSize$7() throws Exception {
        return Long.valueOf(TronAPI.getCanDelegatedMaxSize(0, this.multiSign ? this.controllerAddress : this.wallet.getAddress()).getMaxSize());
    }
}
