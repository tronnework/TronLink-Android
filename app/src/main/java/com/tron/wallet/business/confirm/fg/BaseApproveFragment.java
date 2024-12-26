package com.tron.wallet.business.confirm.fg;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.BaseApproveFragment;
import com.tron.wallet.business.confirm.fg.BaseConfirmFragment;
import com.tron.wallet.business.confirm.fg.component.ArgumentKeys;
import com.tron.wallet.business.confirm.fg.component.Utils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
public abstract class BaseApproveFragment<P extends BasePresenter, M extends BaseModel> extends BaseFragment<P, M> {
    protected Protocol.Account account;
    private final List<BaseConfirmFragment.AccountCallback> accountCallbacks = new ArrayList();
    protected GrpcAPI.AccountResourceMessage accountRes;
    protected BaseParam baseParam;

    public void addAccountCallback(BaseConfirmFragment.AccountCallback... accountCallbackArr) {
        if (accountCallbackArr == null) {
            return;
        }
        DesugarArrays.stream(accountCallbackArr).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addAccountCallback$0((BaseConfirmFragment.AccountCallback) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$addAccountCallback$0(BaseConfirmFragment.AccountCallback accountCallback) {
        if (this.accountCallbacks.contains(accountCallback)) {
            return;
        }
        this.accountCallbacks.add(accountCallback);
    }

    @Override
    public void processData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.baseParam = (BaseParam) arguments.getParcelable(ArgumentKeys.KEY_PARAM);
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmTransactionPage.ENTER_CONFIRM_PAGE);
    }

    public void ensureAccount() {
        try {
            Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(this.baseParam.getTransactionBean().getBytes().get(0));
            final String owner = TransactionUtils.getOwner(parseFrom);
            if (TextUtils.isEmpty(owner)) {
                return;
            }
            Observable.create(new ObservableOnSubscribe() {
                @Override
                public final void subscribe(ObservableEmitter observableEmitter) {
                    lambda$ensureAccount$1(owner, observableEmitter);
                }
            }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new fun1(parseFrom), "confirmFragment: ensureAccount"));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$ensureAccount$1(String str, ObservableEmitter observableEmitter) throws Exception {
        String nameByAddress = Utils.getNameByAddress(str, false);
        byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(str);
        try {
            this.account = TronAPI.queryAccount(decodeFromBase58Check, false);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (AccountUtils.isNullAccount(this.account)) {
            this.account = WalletUtils.getAccount(AppContextUtil.getContext(), nameByAddress);
        }
        try {
            this.accountRes = TronAPI.getAccountRes(decodeFromBase58Check);
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
        if (this.accountRes == null) {
            this.accountRes = WalletUtils.getAccountRes(AppContextUtil.getContext(), nameByAddress);
        }
        if (!observableEmitter.isDisposed()) {
            observableEmitter.onNext(new Pair(this.account, this.accountRes));
        }
        observableEmitter.onComplete();
    }

    class fun1 implements ICallback<Pair<Protocol.Account, GrpcAPI.AccountResourceMessage>> {
        final Protocol.Transaction val$finalTransaction;

        fun1(Protocol.Transaction transaction) {
            this.val$finalTransaction = transaction;
        }

        @Override
        public void onResponse(String str, final Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
            Stream stream = Collection.-EL.stream(accountCallbacks);
            final Protocol.Transaction transaction = this.val$finalTransaction;
            stream.forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    BaseApproveFragment.1.lambda$onResponse$0(Protocol.Transaction.this, pair, (BaseConfirmFragment.AccountCallback) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }

        public static void lambda$onResponse$0(Protocol.Transaction transaction, Pair pair, BaseConfirmFragment.AccountCallback accountCallback) {
            if (accountCallback != null) {
                accountCallback.onRefreshAccountComplete(true, transaction, pair);
            }
        }

        @Override
        public void onFailure(String str, String str2) {
            Stream stream = Collection.-EL.stream(accountCallbacks);
            final Protocol.Transaction transaction = this.val$finalTransaction;
            stream.forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    BaseApproveFragment.1.lambda$onFailure$1(Protocol.Transaction.this, (BaseConfirmFragment.AccountCallback) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }

        public static void lambda$onFailure$1(Protocol.Transaction transaction, BaseConfirmFragment.AccountCallback accountCallback) {
            if (accountCallback != null) {
                accountCallback.onRefreshAccountComplete(false, transaction, null);
            }
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            mPresenter.mRxManager.add(disposable);
        }
    }
}
