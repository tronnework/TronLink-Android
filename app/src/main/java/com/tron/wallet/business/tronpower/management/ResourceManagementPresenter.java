package com.tron.wallet.business.tronpower.management;

import android.app.Activity;
import android.util.Pair;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tronpower.management.ResourceManagementContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.util.ProfitCalculator;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
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
public class ResourceManagementPresenter extends ResourceManagementContract.Presenter {
    private Protocol.Account account;
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
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
    protected void onStart() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.wallet = selectedWallet;
        if (selectedWallet == null) {
            ((ResourceManagementContract.View) this.mView).exit();
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
        if (((ResourceManagementActivity) ((ResourceManagementContract.View) this.mView).getIContext()).isFront()) {
            return;
        }
        ((ResourceManagementContract.View) this.mView).exit();
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        ((ResourceManagementContract.View) this.mView).exit();
    }

    public void lambda$onStart$3(Object obj) throws Exception {
        refreshAccountResources();
    }

    @Override
    public void start() {
        Activity activity = (Activity) ((ResourceManagementContract.View) this.mView).getIContext();
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (this.wallet != null) {
            this.account = WalletUtils.getAccount(((ResourceManagementContract.View) this.mView).getIContext(), this.wallet.getWalletName());
            this.accountResourceMessage = WalletUtils.getAccountRes(((ResourceManagementContract.View) this.mView).getIContext(), this.wallet.getWalletName());
        }
        ((ResourceManagementContract.View) this.mView).updateUI(ResourcesBean.build(this.account, this.accountResourceMessage));
        checkResourcesIntroFlag();
        getMaxVoteApr();
        refreshAccountResources();
    }

    @Override
    public void refreshAccountResources() {
        Observable.just(this.wallet.getAddress()).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$refreshAccountResources$4;
                lambda$refreshAccountResources$4 = lambda$refreshAccountResources$4((String) obj);
                return lambda$refreshAccountResources$4;
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
                ((ResourceManagementContract.View) mView).updateUI(ResourcesBean.build(account, accountResourceMessage));
            }

            @Override
            public void onFailure(String str, String str2) {
                refreshedResult = false;
                if (mView != 0) {
                    ((ResourceManagementContract.View) mView).toast(((ResourceManagementContract.View) mView).getIContext().getResources().getString(R.string.get_account_error));
                    ((ResourceManagementContract.View) mView).updateUI(null);
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "refresh"));
    }

    public ObservableSource lambda$refreshAccountResources$4(String str) throws Exception {
        byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(this.wallet.getAddress());
        return Observable.just(Pair.create(TronAPI.queryAccount(decodeFromBase58Check, false), TronAPI.getAccountRes(decodeFromBase58Check)));
    }

    private void getMaxVoteApr() {
        ((ResourceManagementContract.Model) this.mModel).getWitnessList().subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (mView == 0 || witnessOutput == null || witnessOutput.getData() == null || witnessOutput.getData().size() <= 0) {
                    return;
                }
                ((ResourceManagementContract.View) mView).updateVoteApr((Math.floor(ProfitCalculator.getWitnessProfit(witnessOutput.getTotalVotes(), witnessOutput.getData().get(0)) * 100.0d) / 100.0d) + "\t%");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getMaxVoteApr"));
    }

    private void checkResourcesIntroFlag() {
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(SpAPI.THIS.getResourcesIntroShowFlag());
                return valueOf;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, Boolean bool) {
                if (mView == 0 || bool.booleanValue()) {
                    return;
                }
                ((ResourceManagementContract.View) mView).showResourceIntro();
                SpAPI.THIS.setResourcesIntroShowFlag(true);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "checkResourcesIntroFlag"));
    }
}
