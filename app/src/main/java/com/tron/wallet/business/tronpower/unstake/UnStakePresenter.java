package com.tron.wallet.business.tronpower.unstake;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.tronpower.unstake.UnStakeContract;
import com.tron.wallet.business.tronpower.unstake.UnStakePresenter;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceBean;
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
import j$.util.Collection;
import j$.util.Objects;
import j$.util.concurrent.atomic.DesugarAtomicInteger;
import j$.util.function.Predicate$-CC;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.PermissionException;
import org.tron.walletserver.Wallet;
public class UnStakePresenter extends UnStakeContract.Presenter {
    private static final int REQUEST_SIZE = 10;
    private String controllerAddress;
    private Set<UnStakeResourceBean> selectedResources;
    private long totalSelectedTrx;
    private final int defaultCounter = 2;
    private final int defaultIndex = 0;
    private final AtomicInteger startIndex = new AtomicInteger(0);
    private final AtomicReference<Protocol.Account> accountRef = new AtomicReference<>();
    private boolean isConfirming = false;
    private final AtomicInteger responseCounter = new AtomicInteger(2);

    public static int lambda$countDown$5(int i) {
        return i - 1;
    }

    public int lambda$reset$0(int i) {
        return 0;
    }

    public int lambda$reset$1(int i) {
        return 2;
    }

    @Override
    public void setControllerAddress(String str) {
        this.controllerAddress = str;
    }

    @Override
    protected void onStart() {
        this.selectedResources = new HashSet();
    }

    @Override
    public void requestStakeResource(Protocol.Account account) {
        ((UnStakeContract.Model) this.mModel).requestStakeResourceForSelf(account).subscribe(new IObserver(getRequestResourcesCallback(true), "requestStakeResource_self"));
        requestMore();
    }

    @Override
    public void requestMore() {
        ((UnStakeContract.Model) this.mModel).requestStakeResourceForOthers(this.controllerAddress, this.startIndex.get(), 10).subscribe(new IObserver(getRequestResourcesCallback(false), "requestStakeResource_other"));
    }

    @Override
    public void reset() {
        DesugarAtomicInteger.updateAndGet(this.startIndex, new IntUnaryOperator() {
            public IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }

            @Override
            public final int applyAsInt(int i) {
                int lambda$reset$0;
                lambda$reset$0 = lambda$reset$0(i);
                return lambda$reset$0;
            }

            public IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }
        });
        DesugarAtomicInteger.updateAndGet(this.responseCounter, new IntUnaryOperator() {
            public IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }

            @Override
            public final int applyAsInt(int i) {
                int lambda$reset$1;
                lambda$reset$1 = lambda$reset$1(i);
                return lambda$reset$1;
            }

            public IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }
        });
        Set<UnStakeResourceBean> set = this.selectedResources;
        if (set != null) {
            set.clear();
        }
        this.totalSelectedTrx = 0L;
        ((UnStakeContract.View) this.mView).updateSelectedResources(0, 0L);
    }

    @Override
    public void onItemSelectStateChanged(final UnStakeResourceBean unStakeResourceBean) {
        if (unStakeResourceBean.getState() == 131072) {
            this.selectedResources.add(unStakeResourceBean);
            this.totalSelectedTrx += unStakeResourceBean.getTrxCount();
        } else if (unStakeResourceBean.getState() == 65536 && Collection.-EL.removeIf(this.selectedResources, new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((UnStakeResourceBean) obj).equals(UnStakeResourceBean.this);
                return equals;
            }
        })) {
            this.totalSelectedTrx -= unStakeResourceBean.getTrxCount();
        }
        ((UnStakeContract.View) this.mView).updateSelectedResources(this.selectedResources.size(), this.totalSelectedTrx);
    }

    @Override
    public Observable<Protocol.Account> ensureAccount(final boolean z, final Protocol.Account account, final String str) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$ensureAccount$3(account, z, str, observableEmitter);
            }
        });
    }

    public void lambda$ensureAccount$3(Protocol.Account account, boolean z, String str, ObservableEmitter observableEmitter) throws Exception {
        if (AccountUtils.isNullAccount(account) || z) {
            account = TronAPI.queryAccount(StringTronUtil.decode58Check(str), false);
            LogUtils.w(AnalyticsHelper.UnStakeV2.UN_STAKE, "request account from grpc");
        }
        this.accountRef.set(account);
        observableEmitter.onNext(account);
        observableEmitter.onComplete();
    }

    @Override
    public boolean checkOwnerPermission(Protocol.Account account) {
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
    public void enterConfirm(final boolean z) {
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
                lambda$enterConfirm$4(z, isSamsungWallet, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<BaseConfirmTransParamBuilder>() {
            @Override
            public void onResponse(String str, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
                ConfirmTransactionNewActivity.startActivity(((UnStakeContract.View) mView).getIContext(), baseConfirmTransParamBuilder, isSamsungWallet);
                isConfirming = false;
            }

            @Override
            public void onFailure(String str, String str2) {
                ((UnStakeContract.View) mView).dismissLoading();
                ToastUtil.getInstance().showToast(((UnStakeContract.View) mView).getIContext(), R.string.unfreeze_fail);
                isConfirming = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "confirmUnStake"));
    }

    public void lambda$enterConfirm$4(boolean z, boolean z2, ObservableEmitter observableEmitter) throws Exception {
        boolean z3;
        GrpcAPI.AccountResourceMessage accountRes = TronAPI.getAccountRes(StringTronUtil.decode58Check(this.controllerAddress));
        boolean z4 = false;
        if (z) {
            z3 = false;
        } else {
            z3 = (z2 || WalletUtils.checkHaveOwnerPermissions(this.controllerAddress, this.accountRef.get().getOwnerPermission())) ? true : true;
        }
        BaseConfirmTransParamBuilder buildTransactionParam = ((UnStakeContract.Model) this.mModel).buildTransactionParam(this.accountRef.get(), this.controllerAddress, z3, accountRes, this.selectedResources);
        if (buildTransactionParam == null) {
            observableEmitter.onError(new Exception());
        } else {
            observableEmitter.onNext(buildTransactionParam);
        }
        observableEmitter.onComplete();
    }

    private boolean checkSupportive(Wallet wallet, boolean z) {
        if (wallet.isSamsungWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ((UnStakeContract.View) this.mView).toast(((UnStakeContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return false;
        } else if (wallet.isLedgerHDWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ((UnStakeContract.View) this.mView).toast(((UnStakeContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return false;
        } else if (LedgerWallet.isLedger(wallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ((UnStakeContract.View) this.mView).toast(((UnStakeContract.View) this.mView).getIContext().getString(R.string.ledger_not_support_on_dappchain));
            return false;
        } else if (wallet.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            ((UnStakeContract.View) this.mView).toast(((UnStakeContract.View) this.mView).getIContext().getString(R.string.no_support));
            return false;
        } else if (TextUtils.isEmpty(this.controllerAddress) || SamsungMultisignUtils.isSamsungMultiOwner(this.controllerAddress, this.accountRef.get().getOwnerPermission()) || (wallet.isSamsungWallet() && z)) {
            ((UnStakeContract.View) this.mView).toast(((UnStakeContract.View) this.mView).getIContext().getString(R.string.no_samsung_to_shield));
            return false;
        } else {
            return true;
        }
    }

    public class fun2 implements ICallback<List<UnStakeResourceBean>> {
        final boolean val$forSelf;

        fun2(boolean z) {
            this.val$forSelf = z;
        }

        @Override
        public void onResponse(String str, final List<UnStakeResourceBean> list) {
            ((UnStakeContract.View) mView).updateResourceResult(this.val$forSelf, countDown() == 0, selectedResources.size(), list);
            if (this.val$forSelf) {
                return;
            }
            DesugarAtomicInteger.getAndUpdate(startIndex, new IntUnaryOperator() {
                public IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                    return Objects.requireNonNull(intUnaryOperator);
                }

                @Override
                public final int applyAsInt(int i) {
                    return UnStakePresenter.2.lambda$onResponse$0(list, i);
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
            ((UnStakeContract.View) mView).updateResourceResult(this.val$forSelf, countDown() == 0, selectedResources.size(), Collections.emptyList());
            ((UnStakeContract.View) mView).toast(((UnStakeContract.View) mView).getIContext().getString(R.string.time_out));
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            addDisposable(disposable);
        }
    }

    private ICallback<List<UnStakeResourceBean>> getRequestResourcesCallback(boolean z) {
        return new fun2(z);
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
                return UnStakePresenter.lambda$countDown$5(i);
            }

            public IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                return Objects.requireNonNull(intUnaryOperator);
            }
        });
    }
}
