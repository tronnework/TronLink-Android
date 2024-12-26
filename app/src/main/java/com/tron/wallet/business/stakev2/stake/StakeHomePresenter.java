package com.tron.wallet.business.stakev2.stake;

import android.text.TextUtils;
import android.util.Pair;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.stakev2.stake.StakeHomeContract;
import com.tron.wallet.business.stakev2.stake.pop.unfreezing.UnFreezingResourceBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.util.ProfitCalculator;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class StakeHomePresenter extends StakeHomeContract.Presenter {
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
    private String controllerAddress;
    private Protocol.Account mAccount;
    private boolean multiSign;
    private boolean refreshedResult = true;
    private ResourcesBean resourcesBean;
    private Wallet wallet;

    public static void lambda$getMaxVoteAprOrCurrentApr$12(String str) throws Exception {
    }

    @Override
    public void setConfig(boolean z, String str) {
        this.multiSign = z;
        this.controllerAddress = str;
    }

    @Override
    protected void onStart() {
        this.mRxManager.post(Event.StakeHomeClearPrevious, 0);
        this.mRxManager.on(Event.StakeHomeClearPrevious, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.VOTE_SUCCESS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
        this.mRxManager.on(Event.VOTE_TO_MULTI_VOTE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$3(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$4(obj);
            }
        });
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.wallet = selectedWallet;
        if (selectedWallet == null) {
            ((StakeHomeContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((StakeHomeContract.View) this.mView).exit();
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        ((StakeHomeContract.View) this.mView).hiddenTheStakeV1Pop();
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        ((StakeHomeContract.View) this.mView).exit();
    }

    public void lambda$onStart$3(Object obj) throws Exception {
        if (this.mView != 0) {
            ((StakeHomeContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$4(Object obj) throws Exception {
        ((StakeHomeContract.View) this.mView).hiddenTheStakeV1Pop();
        if (!((TronApplication) AppContextUtil.getmApplication()).isResourceHomeIn() || this.mView == 0) {
            return;
        }
        ((StakeHomeContract.View) this.mView).exit();
    }

    @Override
    public void start() {
        ((StakeHomeContract.View) this.mView).showLoadingDialog();
        loadData();
        getMaxVoteAprOrCurrentApr();
    }

    public void cancelAllUnsake(final long j, final boolean z, final ResourcesBean resourcesBean) {
        ((StakeHomeContract.View) this.mView).showLoadingDialog();
        ((StakeHomeContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$cancelAllUnsake$7(j, resourcesBean, z);
            }
        });
    }

    public void lambda$cancelAllUnsake$7(long j, ResourcesBean resourcesBean, boolean z) {
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = j;
        for (int i = 0; i < resourcesBean.getUnFreezingList().size(); i++) {
            try {
                UnFreezingResourceBean unFreezingResourceBean = resourcesBean.getUnFreezingList().get(i);
                j3 += unFreezingResourceBean.getTrxCount();
                if (unFreezingResourceBean.getExpiredTime() > System.currentTimeMillis()) {
                    if (unFreezingResourceBean.getType() == UnFreezingResourceBean.Type.ENERGY) {
                        j2 += unFreezingResourceBean.getTrxCount();
                    } else {
                        j4 += unFreezingResourceBean.getTrxCount();
                    }
                } else {
                    j5 += unFreezingResourceBean.getTrxCount();
                }
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
                ((StakeHomeContract.View) this.mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public final void doInUIThread() {
                        lambda$cancelAllUnsake$6();
                    }
                });
                return;
            }
        }
        long longValue = ResourcesBean.expectGetEnergy(this.accountResourceMessage, BigDecimalUtils.toBigDecimal(Long.valueOf(j2))).longValue();
        long longValue2 = ResourcesBean.expectGetBandWidth(this.accountResourceMessage, BigDecimalUtils.toBigDecimal(Long.valueOf(j4))).longValue();
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet != null && selectedPublicWallet.getCreateType() == 7) {
            ((StakeHomeContract.View) this.mView).toast(((StakeHomeContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            ((StakeHomeContract.View) this.mView).dismissLoadingDialog();
            return;
        }
        GrpcAPI.TransactionExtention cancelAllUnfreezeV2 = TronAPI.cancelAllUnfreezeV2(this.controllerAddress);
        ((StakeHomeContract.View) this.mView).dismissLoadingDialog();
        if (cancelAllUnfreezeV2 != null) {
            if (TransactionUtils.checkTransactionEmpty(cancelAllUnfreezeV2)) {
                ((StakeHomeContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            }
            String str = this.controllerAddress;
            String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(this.controllerAddress);
            ConfirmTransactionNewActivity.startActivity(((StakeHomeContract.View) this.mView).getIContext(), ParamBuildUtils.getCancelUnStakeTransactionParamBuilder(!z, j3, j5, longValue, longValue2, !TextUtils.isEmpty(nameByAddress) ? String.format(String.format("%s\n(%s)", nameByAddress, this.controllerAddress), new Object[0]) : str, cancelAllUnfreezeV2.getTransaction()));
            return;
        }
        ((StakeHomeContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$cancelAllUnsake$5();
            }
        });
    }

    public void lambda$cancelAllUnsake$5() {
        ((StakeHomeContract.View) this.mView).dismissLoadingDialog();
        ((StakeHomeContract.View) this.mView).toast(StringTronUtil.getResString(R.string.net_error));
    }

    public void lambda$cancelAllUnsake$6() {
        ((StakeHomeContract.View) this.mView).dismissLoadingDialog();
        ((StakeHomeContract.View) this.mView).toast(StringTronUtil.getResString(R.string.net_error));
    }

    public void withdrawExpireUnfreeze(long j, boolean z) {
        try {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null && selectedPublicWallet.getCreateType() == 7) {
                ((StakeHomeContract.View) this.mView).toast(((StakeHomeContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
                return;
            }
            GrpcAPI.TransactionExtention withdrawExpireUnfreeze = TronAPI.withdrawExpireUnfreeze(this.controllerAddress);
            if (withdrawExpireUnfreeze != null) {
                if (TransactionUtils.checkTransactionEmpty(withdrawExpireUnfreeze)) {
                    ((StakeHomeContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                    return;
                }
                String str = this.controllerAddress;
                String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(this.controllerAddress);
                if (!TextUtils.isEmpty(nameByAddress)) {
                    str = String.format(String.format("%s\n(%s)", nameByAddress, this.controllerAddress), new Object[0]);
                }
                ConfirmTransactionNewActivity.startActivity(((StakeHomeContract.View) this.mView).getIContext(), ParamBuildUtils.getWithDrawDelegatedResourceTransactionParamBuilder(!z, j, str, withdrawExpireUnfreeze.getTransaction()));
                return;
            }
            ((StakeHomeContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$withdrawExpireUnfreeze$8();
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            ((StakeHomeContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$withdrawExpireUnfreeze$9();
                }
            });
        }
    }

    public void lambda$withdrawExpireUnfreeze$8() {
        ((StakeHomeContract.View) this.mView).toast(StringTronUtil.getResString(R.string.net_error));
    }

    public void lambda$withdrawExpireUnfreeze$9() {
        ((StakeHomeContract.View) this.mView).toast(StringTronUtil.getResString(R.string.net_error));
    }

    @Override
    public void getMaxVoteApr() {
        ((StakeHomeContract.Model) this.mModel).getWitnessList().subscribe(new IObserver(new ICallback<WitnessOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, WitnessOutput witnessOutput) {
                if (mView == 0 || witnessOutput == null || witnessOutput.getData() == null || witnessOutput.getData().size() <= 0) {
                    return;
                }
                ((StakeHomeContract.View) mView).updateNoStakeApr((Math.floor(ProfitCalculator.getWitnessProfit(witnessOutput.getTotalVotes(), witnessOutput.getData().get(0)) * 100.0d) / 100.0d) + "\t%");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getMaxVoteApr"));
    }

    @Override
    public void getMaxVoteAprOrCurrentApr() {
        rxWrap(((StakeHomeContract.Model) this.mModel).requestWitnessList(this.controllerAddress, 0, 5, 30, 1).zipWith(((StakeHomeContract.Model) this.mModel).requestWitnessList(this.controllerAddress, 0, 6, 3, 1), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                StakeHomeAprBean lambda$getMaxVoteAprOrCurrentApr$10;
                lambda$getMaxVoteAprOrCurrentApr$10 = lambda$getMaxVoteAprOrCurrentApr$10((WitnessOutput) obj, (WitnessOutput) obj2);
                return lambda$getMaxVoteAprOrCurrentApr$10;
            }
        }), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getMaxVoteAprOrCurrentApr$11((StakeHomeAprBean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                StakeHomePresenter.lambda$getMaxVoteAprOrCurrentApr$12((String) obj);
            }
        }, "calculateApr");
    }

    public void lambda$getMaxVoteAprOrCurrentApr$11(StakeHomeAprBean stakeHomeAprBean) throws Exception {
        ((StakeHomeContract.View) this.mView).updateVoteApr(stakeHomeAprBean);
    }

    @Override
    public void loadData() {
        Observable.zip(((StakeHomeContract.Model) this.mModel).getAccount(null, this.controllerAddress), ((StakeHomeContract.Model) this.mModel).getAccountResourceMessage(null, this.controllerAddress), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                return StakeHomePresenter.lambda$loadData$13((Protocol.Account) obj, (GrpcAPI.AccountResourceMessage) obj2);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<Protocol.Account, GrpcAPI.AccountResourceMessage>>() {
            @Override
            public void onResponse(String str, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
                ((StakeHomeContract.View) mView).dismissLoadingDialog();
                if (pair.first != null && pair.second != null) {
                    mAccount = (Protocol.Account) pair.first;
                    accountResourceMessage = (GrpcAPI.AccountResourceMessage) pair.second;
                    StakeHomePresenter stakeHomePresenter = StakeHomePresenter.this;
                    stakeHomePresenter.resourcesBean = ResourcesBean.buildV2(stakeHomePresenter.mAccount, accountResourceMessage);
                    ResourcesBean.buildStakedTrx(resourcesBean, mAccount);
                    ((StakeHomeContract.View) mView).updateUI(resourcesBean, mAccount, accountResourceMessage);
                    return;
                }
                ((StakeHomeContract.View) mView).showEmptyPage();
            }

            @Override
            public void onFailure(String str, String str2) {
                ((StakeHomeContract.View) mView).dismissLoadingDialog();
                ((StakeHomeContract.View) mView).toast(((StakeHomeContract.View) mView).getIContext().getString(R.string.time_out));
                ((StakeHomeContract.View) mView).showEmptyPage();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "UnStakeV2Presenter_GetRes"));
    }

    public static Pair lambda$loadData$13(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) throws Exception {
        return new Pair(account, accountResourceMessage);
    }

    private <R> void rxWrap(Observable<R> observable, final Consumer<R> consumer, final Consumer<String> consumer2, String str) {
        observable.compose(RxSchedulers2.io_main()).subscribe(new IObserver<R>(new ICallback<R>() {
            @Override
            public void onResponse(String str2, R r) {
                try {
                    consumer.accept(r);
                } catch (Exception e) {
                    LogUtils.e(e);
                    try {
                        consumer2.accept(e.getMessage());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                try {
                    consumer2.accept(str3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, str) {
            @Override
            public void onError(Throwable th) {
                this.iCallback.onFailure(th.getMessage(), th.getLocalizedMessage());
            }
        });
    }

    @Override
    public StakeHomeAprBean lambda$getMaxVoteAprOrCurrentApr$10(WitnessOutput witnessOutput, WitnessOutput witnessOutput2) {
        return new StakeHomeAprBean(witnessOutput, witnessOutput2);
    }
}
