package com.tron.wallet.business.vote.component;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.vote.bean.FastAprBean;
import com.tron.wallet.business.vote.bean.RewardOutput;
import com.tron.wallet.business.vote.bean.VoteHeaderBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.component.Contract;
import com.tron.wallet.business.vote.component.VoteMainPresenter;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class VoteMainPresenter extends Contract.Presenter {
    private ArrayList<WitnessOutput.DataBean> cachedMyVotedWitness;
    private int pageIdx = 1;

    public interface Action0<R> {
        R run() throws Exception;
    }

    @Override
    protected void onStart() {
        this.mRxManager.post(Event.VoteClearPrevious, 0);
        this.mRxManager.on(Event.VoteClearPrevious, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.BackToVoteHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((Contract.View) this.mView).exit();
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if (this.mView instanceof VoteMainActivity) {
            ((VoteMainActivity) this.mView).onPullToRefresh();
            LogUtils.e("VoteMain", "received broadcast, refresh data");
        }
    }

    public VoteHeaderBean lambda$getVotingData$2(boolean z, Protocol.Account account, String str) throws Exception {
        return ((Contract.Model) this.mModel).getVotingData(z, account, str);
    }

    @Override
    public void getVotingData(final boolean z, final Protocol.Account account, final String str) {
        rxWrap(new Action0() {
            @Override
            public final Object run() {
                VoteHeaderBean lambda$getVotingData$2;
                lambda$getVotingData$2 = lambda$getVotingData$2(z, account, str);
                return lambda$getVotingData$2;
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getVotingData$3((VoteHeaderBean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getVotingData$4((String) obj);
            }
        }, "requestVotingData");
    }

    public void lambda$getVotingData$3(VoteHeaderBean voteHeaderBean) throws Exception {
        ((Contract.View) this.mView).onVotingDataSuccess(voteHeaderBean);
    }

    public void lambda$getVotingData$4(String str) throws Exception {
        ((Contract.View) this.mView).onRequestFail(str);
    }

    public Protocol.Account lambda$ensureAccount$5(Protocol.Account account, String str) throws Exception {
        return ((Contract.Model) this.mModel).ensureAccount(((Contract.View) this.mView).getIContext(), account, str);
    }

    @Override
    public void ensureAccount(final Protocol.Account account, final String str) {
        rxWrap(new Action0() {
            @Override
            public final Object run() {
                Protocol.Account lambda$ensureAccount$5;
                lambda$ensureAccount$5 = lambda$ensureAccount$5(account, str);
                return lambda$ensureAccount$5;
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$ensureAccount$6((Protocol.Account) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$ensureAccount$7((String) obj);
            }
        }, "requestAccount");
    }

    public void lambda$ensureAccount$6(Protocol.Account account) throws Exception {
        ((Contract.View) this.mView).onRequestAccountSuccess(account);
    }

    public void lambda$ensureAccount$7(String str) throws Exception {
        ((Contract.View) this.mView).onRequestFail(str);
    }

    @Override
    public void requestWitnessList(boolean z, String str, int i, final int i2) {
        if (z) {
            this.pageIdx = 1;
        }
        rxWrap(((Contract.Model) this.mModel).requestWitnessList(str, i, i2, 30, this.pageIdx), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestWitnessList$8(i2, (WitnessOutput) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestWitnessList$9((String) obj);
            }
        }, "requestWitnessList");
    }

    public void lambda$requestWitnessList$8(int i, WitnessOutput witnessOutput) throws Exception {
        int i2 = this.pageIdx;
        this.pageIdx = i2 + 1;
        boolean z = i2 == 1;
        if (z && i == 5) {
            cacheMyVotedWitness(witnessOutput);
        }
        ((Contract.View) this.mView).onRequestWitnessSuccess(z, witnessOutput);
    }

    public void lambda$requestWitnessList$9(String str) throws Exception {
        ((Contract.View) this.mView).onRequestFail(str);
    }

    private void cacheMyVotedWitness(WitnessOutput witnessOutput) {
        if (witnessOutput == null || witnessOutput.getData() == null) {
            return;
        }
        this.cachedMyVotedWitness = new ArrayList<>();
        Collection.-EL.stream(witnessOutput.getData()).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$cacheMyVotedWitness$10((WitnessOutput.DataBean) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$cacheMyVotedWitness$10(WitnessOutput.DataBean dataBean) {
        try {
            if (BigDecimalUtils.MoreThan((Object) dataBean.getVoted(), (Object) 0)) {
                this.cachedMyVotedWitness.add(dataBean);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public ArrayList<WitnessOutput.DataBean> getCachedMyVotedWitness() {
        ArrayList<WitnessOutput.DataBean> arrayList = this.cachedMyVotedWitness;
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    @Override
    public void requestGetProfit(final String str, final Protocol.Account account, final double d) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            ((Contract.View) this.mView).onRequestProfitComplete(false, R.string.no_vote_reward);
            return;
        }
        final Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null) {
            ((Contract.View) this.mView).onRequestProfitComplete(false, R.string.create_transaction_fail);
            return;
        }
        ((Contract.View) this.mView).showLoadingDialog();
        rxWrap(((Contract.Model) this.mModel).requestCanReward(str), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestGetProfit$11(selectedPublicWallet, account, str, d, (RewardOutput) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestGetProfit$12((String) obj);
            }
        }, "requestCanReward");
    }

    public void lambda$requestGetProfit$11(Wallet wallet, Protocol.Account account, String str, double d, RewardOutput rewardOutput) throws Exception {
        if (rewardOutput.reward) {
            requestProfitActual(wallet, account, str, d);
            return;
        }
        ((Contract.View) this.mView).toast(((Contract.View) this.mView).getIContext().getString(R.string.vote_donot_confirm));
        ((Contract.View) this.mView).onRequestProfitComplete(true, 0);
    }

    public void lambda$requestGetProfit$12(String str) throws Exception {
        ((Contract.View) this.mView).onRequestProfitComplete(false, R.string.network_unusable);
    }

    private void requestProfitActual(final Wallet wallet, final Protocol.Account account, final String str, final double d) {
        final boolean z = wallet.getCreateType() == 7;
        rxWrap(new Action0() {
            @Override
            public final Object run() {
                BaseConfirmTransParamBuilder lambda$requestProfitActual$13;
                lambda$requestProfitActual$13 = lambda$requestProfitActual$13(wallet, str, account, d);
                return lambda$requestProfitActual$13;
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestProfitActual$14(z, (BaseConfirmTransParamBuilder) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestProfitActual$15((String) obj);
            }
        }, "requestGetProfit");
    }

    public BaseConfirmTransParamBuilder lambda$requestProfitActual$13(Wallet wallet, String str, Protocol.Account account, double d) throws Exception {
        return ((Contract.Model) this.mModel).requestGetProfit(wallet.getAddress(), str, ((VoteMainActivity) this.mView).permissionData, account, d);
    }

    public void lambda$requestProfitActual$14(boolean z, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) throws Exception {
        if (baseConfirmTransParamBuilder == null) {
            ((Contract.View) this.mView).onRequestProfitComplete(false, R.string.create_transaction_fail);
            return;
        }
        ConfirmTransactionNewActivity.startActivity(((Contract.View) this.mView).getIContext(), baseConfirmTransParamBuilder, z);
        ((Contract.View) this.mView).onRequestProfitComplete(true, 0);
    }

    public void lambda$requestProfitActual$15(String str) throws Exception {
        ((Contract.View) this.mView).onRequestProfitComplete(false, R.string.create_transaction_fail);
    }

    @Override
    public void requestProfitNumber(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        rxWrap(new Action0() {
            @Override
            public final Object run() {
                Double lambda$requestProfitNumber$16;
                lambda$requestProfitNumber$16 = lambda$requestProfitNumber$16(str);
                return lambda$requestProfitNumber$16;
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestProfitNumber$17((Double) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestProfitNumber$18((String) obj);
            }
        }, "requestProfitNumber");
    }

    public Double lambda$requestProfitNumber$16(String str) throws Exception {
        return Double.valueOf(((Contract.Model) this.mModel).requestProfitNumber(str));
    }

    public void lambda$requestProfitNumber$17(Double d) throws Exception {
        ((Contract.View) this.mView).onRequestProfitNumberComplete(d.doubleValue(), true, null);
    }

    public void lambda$requestProfitNumber$18(String str) throws Exception {
        ((Contract.View) this.mView).onRequestProfitNumberComplete(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, false, str);
    }

    @Override
    public void requestApr(String str) {
        rxWrap((Observable) ((Contract.Model) this.mModel).requestWitnessList(str, 0, 5, 30, 1).zipWith(((Contract.Model) this.mModel).requestWitnessList(str, 0, 6, 3, 1), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                FastAprBean calculateApr;
                calculateApr = VoteAprCalculator.calculateApr(((WitnessOutput) obj).getData(), ((WitnessOutput) obj2).getData());
                return calculateApr;
            }
        }), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestApr$20((FastAprBean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestApr$21((String) obj);
            }
        }, "calculateApr");
    }

    public void lambda$requestApr$20(FastAprBean fastAprBean) throws Exception {
        ((Contract.View) this.mView).onRequestAprComplete(fastAprBean);
    }

    public void lambda$requestApr$21(String str) throws Exception {
        ((Contract.View) this.mView).onRequestFail(str);
    }

    @Override
    public void requestCancelAllVotes(final boolean z, final long j, final String str, final Protocol.Account account) {
        rxWrap(((Contract.Model) this.mModel).requestWitnessList(str, 1, 5, 30, 1).compose(new ObservableTransformer() {
            @Override
            public final ObservableSource apply(Observable observable) {
                ObservableSource lambda$requestCancelAllVotes$22;
                lambda$requestCancelAllVotes$22 = lambda$requestCancelAllVotes$22(observable);
                return lambda$requestCancelAllVotes$22;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$requestCancelAllVotes$25;
                lambda$requestCancelAllVotes$25 = lambda$requestCancelAllVotes$25(account, z, j, str, (WitnessOutput) obj);
                return lambda$requestCancelAllVotes$25;
            }
        }), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestCancelAllVotes$26((BaseConfirmTransParamBuilder) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$requestCancelAllVotes$27((String) obj);
            }
        }, "requestCancelAll");
    }

    public ObservableSource lambda$requestCancelAllVotes$22(Observable observable) {
        ArrayList<WitnessOutput.DataBean> cachedMyVotedWitness = getCachedMyVotedWitness();
        if (cachedMyVotedWitness == null || cachedMyVotedWitness.isEmpty()) {
            return observable;
        }
        WitnessOutput witnessOutput = new WitnessOutput();
        witnessOutput.setData(cachedMyVotedWitness);
        return Observable.just(witnessOutput);
    }

    public ObservableSource lambda$requestCancelAllVotes$25(final Protocol.Account account, final boolean z, final long j, final String str, final WitnessOutput witnessOutput) throws Exception {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$requestCancelAllVotes$24(account, witnessOutput, z, j, str, observableEmitter);
            }
        });
    }

    public void lambda$requestCancelAllVotes$24(Protocol.Account account, WitnessOutput witnessOutput, boolean z, long j, String str, ObservableEmitter observableEmitter) throws Exception {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        String checkCancelVoteError = ((Contract.Model) this.mModel).checkCancelVoteError(account, selectedPublicWallet);
        if (!TextUtils.isEmpty(checkCancelVoteError)) {
            observableEmitter.onError(new Exception(checkCancelVoteError));
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            final HashMap<String, String> hashMap2 = new HashMap<>();
            HashMap<String, String> hashMap3 = new HashMap<>();
            hashMap3.put(((Contract.Model) this.mModel).getMaxVotedWitness(hashMap, hashMap2, witnessOutput).getAddress(), String.valueOf(1));
            final BigDecimal[] bigDecimalArr = {new BigDecimal(0)};
            Collection.-EL.stream(hashMap2.keySet()).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    VoteMainPresenter.lambda$requestCancelAllVotes$23(bigDecimalArr, hashMap2, (String) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            observableEmitter.onNext(((Contract.Model) this.mModel).getCancelVoteParamBuilder(z, bigDecimalArr[0].longValue(), j - 1, ((VoteMainActivity) this.mView).permissionData, str, selectedPublicWallet, account, hashMap, hashMap2, hashMap3, ((Contract.View) this.mView).getStatAction()));
        }
        observableEmitter.onComplete();
    }

    public static void lambda$requestCancelAllVotes$23(BigDecimal[] bigDecimalArr, HashMap hashMap, String str) {
        bigDecimalArr[0] = BigDecimalUtils.sum_(bigDecimalArr[0], hashMap.get(str));
    }

    public void lambda$requestCancelAllVotes$26(BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) throws Exception {
        ((Contract.View) this.mView).onRequestCancelVoteComplete(true, null, baseConfirmTransParamBuilder);
    }

    public void lambda$requestCancelAllVotes$27(String str) throws Exception {
        ((Contract.View) this.mView).onRequestCancelVoteComplete(false, str, null);
    }

    @Override
    Observable<Contract.PermissionForStake> checkStakePermission() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$checkStakePermission$28(observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public void lambda$checkStakePermission$28(ObservableEmitter observableEmitter) throws Exception {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null) {
            observableEmitter.onNext(Contract.PermissionForStake.OTHER_ERROR);
        } else {
            Protocol.Account account = WalletUtils.getAccount(((Contract.View) this.mView).getIContext(), selectedPublicWallet.getWalletName());
            if (AccountUtils.checkAccountIsNotActivated(account)) {
                observableEmitter.onNext(Contract.PermissionForStake.NOT_ACTIVATE);
            } else if (!WalletUtils.checkHaveOwnerPermissions(selectedPublicWallet.getAddress(), account.getOwnerPermission())) {
                observableEmitter.onNext(Contract.PermissionForStake.NO_PERMISSION);
            } else {
                observableEmitter.onNext(Contract.PermissionForStake.SUCCESS);
            }
        }
        observableEmitter.onComplete();
    }

    private <R> void rxWrap(final Action0<R> action0, Consumer<R> consumer, Consumer<String> consumer2, String str) {
        rxWrap(Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                VoteMainPresenter.lambda$rxWrap$29(VoteMainPresenter.Action0.this, observableEmitter);
            }
        }), consumer, consumer2, str);
    }

    public static void lambda$rxWrap$29(Action0 action0, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(action0.run());
        observableEmitter.onComplete();
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
}
