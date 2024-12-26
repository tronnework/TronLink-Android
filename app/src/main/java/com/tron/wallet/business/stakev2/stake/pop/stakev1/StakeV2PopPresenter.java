package com.tron.wallet.business.stakev2.stake.pop.stakev1;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeV2PopContract;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeV2PopPresenter;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.SamsungMultisignUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import j$.util.Objects;
import j$.util.concurrent.atomic.DesugarAtomicInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntUnaryOperator;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.PermissionException;
import org.tron.walletserver.Wallet;
public class StakeV2PopPresenter extends StakeV2PopContract.Presenter {
    private static final int REQUEST_SIZE = 10;
    private String controllerAddress;
    private Set<StakeDetailResourceBean> selectedResources;
    private long totalSelectedTrx;
    private final int defaultCounter = 2;
    private final int defaultIndex = 0;
    private final AtomicInteger startIndex = new AtomicInteger(0);
    private final AtomicReference<Protocol.Account> accountRef = new AtomicReference<>();
    private boolean isConfirming = false;
    private final AtomicInteger responseCounter = new AtomicInteger(2);

    public static int lambda$countDown$0(int i) {
        return i - 1;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void requestStakeResource(Protocol.Account account) {
        ((StakeV2PopContract.Model) this.mModel).requestStakeResourceForSelf(account).subscribe(new IObserver(getRequestResourcesCallback(true), "requestStakeResource_self"));
        requestMore();
    }

    @Override
    public void requestMore() {
        ((StakeV2PopContract.Model) this.mModel).requestStakeResourceForOthers(this.controllerAddress, this.startIndex.get(), 10).subscribe(new IObserver(getRequestResourcesCallback(false), "requestStakeResource_other"));
    }

    public class fun1 implements ICallback<List<StakeDetailResourceBean>> {
        final boolean val$forSelf;

        fun1(boolean z) {
            this.val$forSelf = z;
        }

        @Override
        public void onResponse(String str, final List<StakeDetailResourceBean> list) {
            ((StakeV2PopContract.IView) mView).updateResourceResult(this.val$forSelf, countDown() == 0, list);
            if (this.val$forSelf) {
                return;
            }
            DesugarAtomicInteger.getAndUpdate(startIndex, new IntUnaryOperator() {
                public IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                    return Objects.requireNonNull(intUnaryOperator);
                }

                @Override
                public final int applyAsInt(int i) {
                    return StakeV2PopPresenter.1.lambda$onResponse$0(list, i);
                }

                public IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                    return Objects.requireNonNull(intUnaryOperator);
                }
            });
        }

        public static int lambda$onResponse$0(List list, int i) {
            return i + (list != null ? list.size() : 0);
        }

        @Override
        public void onFailure(String str, String str2) {
            ((StakeV2PopContract.IView) mView).updateResourceResult(this.val$forSelf, countDown() == 0, Collections.emptyList());
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            addDisposable(disposable);
        }
    }

    private ICallback<List<StakeDetailResourceBean>> getRequestResourcesCallback(boolean z) {
        return new fun1(z);
    }

    public int countDown() {
        if (this.responseCounter.get() == 0) {
            return 0;
        }
        return DesugarAtomicInteger.updateAndGet(this.responseCounter, new IntUnaryOperator() {
            public IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }

            @Override
            public final int applyAsInt(int i) {
                return StakeV2PopPresenter.lambda$countDown$0(i);
            }

            public IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }
        });
    }

    @Override
    public Observable<Protocol.Account> ensureAccount(final boolean z, final Protocol.Account account, final String str) {
        this.controllerAddress = str;
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$ensureAccount$1(account, z, str, observableEmitter);
            }
        });
    }

    public void lambda$ensureAccount$1(Protocol.Account account, boolean z, String str, ObservableEmitter observableEmitter) throws Exception {
        if (AccountUtils.isNullAccount(account) || z) {
            account = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
            LogUtils.w(AnalyticsHelper.UnStakeV2.UN_STAKE, "request account from grpc");
        }
        this.accountRef.set(account);
        observableEmitter.onNext(account);
        observableEmitter.onComplete();
    }

    @Override
    boolean checkOwnerPermission(Protocol.Account account) {
        try {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null) {
                return WalletUtils.checkHaveOwnerPermissions(selectedPublicWallet.getAddress(), account.getOwnerPermission());
            }
            return false;
        } catch (PermissionException e) {
            LogUtils.e(e);
            return false;
        }
    }

    @Override
    public void enterConfirm(final boolean z, final StakeDetailResourceBean stakeDetailResourceBean) {
        if (this.isConfirming) {
            return;
        }
        this.isConfirming = true;
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null || !checkSupportive(selectedPublicWallet, z)) {
            return;
        }
        final boolean isSamsungWallet = selectedPublicWallet.isSamsungWallet();
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$enterConfirm$2(z, isSamsungWallet, stakeDetailResourceBean, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<BaseConfirmTransParamBuilder>() {
            @Override
            public void onResponse(String str, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
                ConfirmTransactionNewActivity.startActivity(((StakeV2PopContract.IView) mView).getIContext(), baseConfirmTransParamBuilder, isSamsungWallet);
                isConfirming = false;
            }

            @Override
            public void onFailure(String str, String str2) {
                ToastUtil.getInstance().showToast(((StakeV2PopContract.IView) mView).getIContext(), R.string.unfreeze_fail);
                isConfirming = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "confirmUnStake"));
    }

    public void lambda$enterConfirm$2(boolean z, boolean z2, StakeDetailResourceBean stakeDetailResourceBean, ObservableEmitter observableEmitter) throws Exception {
        boolean z3;
        GrpcAPI.AccountResourceMessage accountRes = TronAPI.getAccountRes(StringTronUtil.decode58Check(this.controllerAddress));
        boolean z4 = false;
        if (z) {
            z3 = false;
        } else {
            z3 = (z2 || WalletUtils.checkHaveOwnerPermissions(this.controllerAddress, this.accountRef.get().getOwnerPermission())) ? true : true;
        }
        HashSet hashSet = new HashSet();
        this.selectedResources = hashSet;
        hashSet.add(stakeDetailResourceBean);
        BaseConfirmTransParamBuilder buildTransactionParam = ((StakeV2PopContract.Model) this.mModel).buildTransactionParam(this.accountRef.get(), this.controllerAddress, z3, accountRes, this.selectedResources);
        if (buildTransactionParam == null) {
            observableEmitter.onError(new Exception());
        } else {
            observableEmitter.onNext(buildTransactionParam);
        }
        observableEmitter.onComplete();
    }

    private boolean checkSupportive(Wallet wallet, boolean z) {
        if (wallet.isSamsungWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ToastUtil.getInstance().showToast(((StakeV2PopContract.IView) this.mView).getIContext(), ((StakeV2PopContract.IView) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return false;
        } else if (wallet.isLedgerHDWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ToastUtil.getInstance().showToast(((StakeV2PopContract.IView) this.mView).getIContext(), ((StakeV2PopContract.IView) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return false;
        } else if (LedgerWallet.isLedger(wallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ToastUtil.getInstance().showToast(((StakeV2PopContract.IView) this.mView).getIContext(), ((StakeV2PopContract.IView) this.mView).getIContext().getString(R.string.ledger_not_support_on_dappchain));
            return false;
        } else if (wallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ToastUtil.getInstance().showToast(((StakeV2PopContract.IView) this.mView).getIContext(), ((StakeV2PopContract.IView) this.mView).getIContext().getString(R.string.no_support));
            return false;
        } else if (TextUtils.isEmpty(this.controllerAddress) || SamsungMultisignUtils.isSamsungMultiOwner(this.controllerAddress, this.accountRef.get().getOwnerPermission()) || (wallet.isSamsungWallet() && z)) {
            ToastUtil.getInstance().showToast(((StakeV2PopContract.IView) this.mView).getIContext(), ((StakeV2PopContract.IView) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return false;
        } else {
            return true;
        }
    }
}
