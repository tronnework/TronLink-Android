package com.tron.wallet.business.tronpower.stake;

import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tronpower.stake.StakeTRXContract;
import com.tron.wallet.common.components.dialog.Common7Dialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.utils.AddressNameUtils;
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
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import kotlin.time.DurationKt;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
import org.tron.walletserver.Wallet;
public class StakeTRXPresenter extends StakeTRXContract.Presenter {
    private Protocol.Account account;
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
    private Common7Dialog dialog;
    private boolean hasNetData = false;
    private String mSelectAddress;
    private Wallet mWallet;
    private FragmentManager manager;
    private NumberFormat numberFormat;

    @Override
    public void getData() {
        if (this.hasNetData) {
            return;
        }
        ((StakeTRXContract.View) this.mView).setButtonEnable(false);
        ((StakeTRXContract.View) this.mView).showLoadingDialog();
        Observable.zip(((StakeTRXContract.Model) this.mModel).getAccount(((StakeTRXContract.View) this.mView).getAccount(), ((StakeTRXContract.View) this.mView).getSelectedAddress()), ((StakeTRXContract.Model) this.mModel).getAccountResourceMessage(), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                return StakeTRXPresenter.lambda$getData$0((Protocol.Account) obj, (GrpcAPI.AccountResourceMessage) obj2);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Pair<Protocol.Account, GrpcAPI.AccountResourceMessage>>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str, Pair<Protocol.Account, GrpcAPI.AccountResourceMessage> pair) {
                ((StakeTRXContract.View) mView).setButtonEnable(false);
                ((StakeTRXContract.View) mView).dismissLoadingDialog();
                if (pair.first == null || pair.second == null) {
                    return;
                }
                ((StakeTRXContract.View) mView).updateUI((Protocol.Account) pair.first, (GrpcAPI.AccountResourceMessage) pair.second);
                hasNetData = true;
            }

            @Override
            public void onFailure(String str, String str2) {
                ((StakeTRXContract.View) mView).toast(((StakeTRXContract.View) mView).getIContext().getString(R.string.time_out));
            }
        }, "StakeTRXPresenter_GetRes"));
    }

    public static Pair lambda$getData$0(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) throws Exception {
        return new Pair(account, accountResourceMessage);
    }

    @Override
    public void init(Protocol.Account account) {
        this.mSelectAddress = ((StakeTRXContract.View) this.mView).getSelectedAddress();
        this.mWallet = ((StakeTRXContract.View) this.mView).getDefaultWallet();
        this.account = account;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
    }

    @Override
    public Observable<Protocol.Account> queryAccount(final String str, final String str2) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$queryAccount$1(str, str2, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public void lambda$queryAccount$1(String str, String str2, ObservableEmitter observableEmitter) throws Exception {
        Protocol.Account account = WalletUtils.getAccount(((StakeTRXContract.View) this.mView).getIContext(), str);
        this.account = account;
        if (isNullAccount(account)) {
            this.account = TronAPI.queryAccount(StringTronUtil.decode58Check(str2), false);
        }
        observableEmitter.onNext(this.account);
        observableEmitter.onComplete();
    }

    public boolean isNullAccount(Protocol.Account account) {
        return account == null || account.toString().length() <= 0;
    }

    @Override
    public boolean hasOwnerPermission(String str, Protocol.Account account) {
        if (!isNullAccount(account) && !TextUtils.isEmpty(str)) {
            try {
                return WalletUtils.checkHaveOwnerPermissions(str, account.getOwnerPermission());
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return false;
    }

    private void toast(String str) {
        ((StakeTRXContract.View) this.mView).toast(str);
    }

    private String getString(int i) {
        return ((StakeTRXContract.View) this.mView).getIContext().getString(i);
    }

    public void stake() {
        String str;
        BigDecimal bigDecimal;
        if (!TronConfig.hasNet) {
            ((StakeTRXContract.View) this.mView).ToastError(R.string.time_out);
        } else if (SamsungMultisignUtils.isSamsungWallet(this.mSelectAddress) && !SpAPI.THIS.getCurIsMainChain()) {
            toast(getString(R.string.no_samsung_to_shield));
        } else if (SamsungMultisignUtils.isSamsungWallet(this.mSelectAddress) && ((StakeTRXContract.View) this.mView).isMultisign()) {
            toast(getString(R.string.no_samsung_to_shield));
        } else {
            Wallet wallet = this.mWallet;
            if (wallet != null && LedgerWallet.isLedger(wallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                toast(getString(R.string.ledger_not_support_on_dappchain));
                return;
            }
            Wallet wallet2 = this.mWallet;
            if (wallet2 != null && wallet2.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                toast(getString(R.string.no_support));
                return;
            }
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (((StakeTRXContract.View) this.mView).isMultisign() && selectedPublicWallet != null && selectedPublicWallet.isSamsungWallet()) {
                toast(getString(R.string.no_samsung_to_shield));
                return;
            }
            TronConfig.currentPwdType = 5;
            try {
                str = NumberFormat.getNumberInstance(Locale.US).parse(((StakeTRXContract.View) this.mView).getEtAmount().getText().toString()).longValue() + "";
            } catch (ParseException e) {
                LogUtils.e(e);
                str = null;
            }
            if (TextUtils.isEmpty(str)) {
                ((StakeTRXContract.View) this.mView).setErrorCountStatus(true, R.string.freeze_empty);
                ((StakeTRXContract.View) this.mView).updateBtStatus(false);
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                bigDecimal = new BigDecimal(str.trim());
                if (str.length() <= 0) {
                    bigDecimal = new BigDecimal(0);
                }
            } else {
                bigDecimal = new BigDecimal(0);
                if (str.length() <= 0) {
                    bigDecimal = new BigDecimal(0);
                }
            }
            BigDecimal multiply = bigDecimal.multiply(new BigDecimal((int) DurationKt.NANOS_IN_MILLIS));
            ((StakeTRXContract.View) this.mView).updateBtStatus(false);
            if (compareWithZero(multiply, BigDecimal.ZERO) == 0 || compareWithZero(multiply, BigDecimal.ZERO) == -1) {
                ((StakeTRXContract.View) this.mView).setErrorCountStatus(true, R.string.freeze_empty);
                ((StakeTRXContract.View) this.mView).updateBtStatus(false);
            } else if (compareWithZero(bigDecimal, new BigDecimal(((StakeTRXContract.View) this.mView).getCanUseTrxCount())) == 1) {
                ((StakeTRXContract.View) this.mView).setErrorCountStatus(true);
            } else {
                ((StakeTRXContract.View) this.mView).setErrorCountStatus(false);
                SelectStakeReceiveAccountActvitiy.start(((StakeTRXContract.View) this.mView).getIContext(), this.mSelectAddress, ((StakeTRXContract.View) this.mView).getSelectedAddressName(), bigDecimal.longValue(), ((StakeTRXContract.View) this.mView).getCanUseTrxCount(), ((StakeTRXContract.View) this.mView).isMultisign(), ((StakeTRXContract.View) this.mView).getResourceCount(), ((StakeTRXContract.View) this.mView).isFreezeBandwidth(), ((StakeTRXContract.View) this.mView).getStatAction());
            }
        }
    }

    public void stake2() {
        Protocol.Account account;
        String str;
        BigDecimal bigDecimal;
        if (!TronConfig.hasNet) {
            ((StakeTRXContract.View) this.mView).ToastError(R.string.time_out);
        } else if (SamsungMultisignUtils.isSamsungWallet(this.mSelectAddress) && !SpAPI.THIS.getCurIsMainChain()) {
            toast(getString(R.string.no_samsung_to_shield));
        } else if (SamsungMultisignUtils.isSamsungWallet(this.mSelectAddress) && ((StakeTRXContract.View) this.mView).isMultisign()) {
            toast(getString(R.string.no_samsung_to_shield));
        } else {
            Wallet wallet = this.mWallet;
            if (wallet != null && LedgerWallet.isLedger(wallet) && !SpAPI.THIS.getCurrentChain().isMainChain) {
                toast(getString(R.string.ledger_not_support_on_dappchain));
                return;
            }
            Wallet wallet2 = this.mWallet;
            if (wallet2 != null && wallet2.getCreateType() == 7) {
                toast(getString(R.string.no_samsung_to_shield));
                return;
            }
            Wallet wallet3 = this.mWallet;
            if (wallet3 != null && wallet3.isWatchOnly() && !SpAPI.THIS.getCurrentChain().isMainChain) {
                toast(getString(R.string.no_support));
            } else if (SamsungMultisignUtils.isSamsungMultisign(this.mSelectAddress) || ((account = this.account) != null && SamsungMultisignUtils.isSamsungMultiOwner(this.mSelectAddress, account.getOwnerPermission()))) {
                toast(getString(R.string.no_samsung_to_shield));
            } else {
                TronConfig.currentPwdType = 5;
                try {
                    str = NumberFormat.getNumberInstance(Locale.US).parse(((StakeTRXContract.View) this.mView).getEtAmount().getText().toString()).longValue() + "";
                } catch (ParseException e) {
                    LogUtils.e(e);
                    str = null;
                }
                if (TextUtils.isEmpty(str)) {
                    ((StakeTRXContract.View) this.mView).setErrorCountStatus(true, R.string.freeze_empty);
                    ((StakeTRXContract.View) this.mView).updateBtStatus(false);
                    return;
                }
                if (!TextUtils.isEmpty(str)) {
                    bigDecimal = new BigDecimal(str.trim());
                    if (str.length() <= 0) {
                        bigDecimal = new BigDecimal(0);
                    }
                } else {
                    bigDecimal = new BigDecimal(0);
                    if (str.length() <= 0) {
                        bigDecimal = new BigDecimal(0);
                    }
                }
                final BigDecimal multiply = bigDecimal.multiply(new BigDecimal((int) DurationKt.NANOS_IN_MILLIS));
                ((StakeTRXContract.View) this.mView).updateBtStatus(false);
                if (compareWithZero(multiply, BigDecimal.ZERO) == 0 || compareWithZero(multiply, BigDecimal.ZERO) == -1) {
                    ((StakeTRXContract.View) this.mView).setErrorCountStatus(true, R.string.freeze_empty);
                    ((StakeTRXContract.View) this.mView).updateBtStatus(false);
                } else if (compareWithZero(bigDecimal, new BigDecimal(((StakeTRXContract.View) this.mView).getCanUseTrxCount())) == 1) {
                    ((StakeTRXContract.View) this.mView).setErrorCountStatus(true);
                } else {
                    ((StakeTRXContract.View) this.mView).setErrorCountStatus(false);
                    ((StakeTRXContract.View) this.mView).getNextButton().setEnabled(false);
                    ((StakeTRXContract.View) this.mView).showLoadingDialog();
                    ((StakeTRXContract.View) this.mView).runOnThreeThread(new OnBackground() {
                        @Override
                        public final void doOnBackground() {
                            lambda$stake2$3(multiply);
                        }
                    });
                }
            }
        }
    }

    public void lambda$stake2$3(final BigDecimal bigDecimal) {
        final GrpcAPI.TransactionExtention transactionExtention;
        try {
            transactionExtention = TronAPI.freezeBalanceV2(this.mSelectAddress, bigDecimal.longValue(), ((StakeTRXContract.View) this.mView).isFreezeBandwidth() ? Common.ResourceCode.BANDWIDTH : Common.ResourceCode.ENERGY);
        } catch (Exception unused) {
            transactionExtention = null;
        }
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                lambda$stake2$2(transactionExtention, bigDecimal);
            }
        });
    }

    public void lambda$stake2$2(GrpcAPI.TransactionExtention transactionExtention, BigDecimal bigDecimal) {
        ((StakeTRXContract.View) this.mView).getNextButton().setEnabled(true);
        ((StakeTRXContract.View) this.mView).dismissLoadingDialog();
        if (transactionExtention != null && transactionExtention.hasResult()) {
            if (new String(transactionExtention.getResult().getMessage().toByteArray()).contains("contract validate error :") && new String(transactionExtention.getResult().getMessage().toByteArray()).contains("not exists")) {
                ((StakeTRXContract.View) this.mView).setErrorCountStatus(true, R.string.address_unuse);
                return;
            } else if (TransactionUtils.checkTransactionEmpty(transactionExtention)) {
                ((StakeTRXContract.View) this.mView).ToastSuc(R.string.create_transaction_fail);
                return;
            } else if (((StakeTRXContract.View) this.mView).isFreezeBandwidth()) {
                toConfirmWithSamsungSdk(transactionExtention.getTransaction(), bigDecimal);
                return;
            } else {
                toConfirmWithSamsungSdk(transactionExtention.getTransaction(), bigDecimal);
                return;
            }
        }
        ToastUtil.getInstance().showToast(((StakeTRXContract.View) this.mView).getIContext(), R.string.freeze_fail);
    }

    public void toConfirmWithSamsungSdk(Protocol.Transaction transaction, BigDecimal bigDecimal) {
        toConfirm(transaction, bigDecimal);
    }

    public void toConfirm(Protocol.Transaction transaction, BigDecimal bigDecimal) {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        boolean z = selectedPublicWallet != null && selectedPublicWallet.getCreateType() == 7;
        String str = this.mSelectAddress;
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(this.mSelectAddress);
        if (!TextUtils.isEmpty(nameByAddress)) {
            str = String.format(String.format("%s\n(%s)", nameByAddress, this.mSelectAddress), new Object[0]);
        }
        ConfirmTransactionNewActivity.startActivity(((StakeTRXContract.View) this.mView).getIContext(), ParamBuildUtils.getFreezeTransactionParamBuilder(((StakeTRXContract.View) this.mView).isFreezeBandwidth(), !((StakeTRXContract.View) this.mView).isMultisign(), true, ((StakeTRXContract.View) this.mView).getCanUseTrxCount(), bigDecimal.longValue() / 1000000.0d, ((StakeTRXContract.View) this.mView).getResourceCount(), str, this.numberFormat.format(bigDecimal.longValue() / 1000000.0d) + " TRX", ((StakeTRXContract.View) this.mView).getStatAction(), transaction), z);
    }

    private void showDialog(final Protocol.Transaction transaction, final String str, final BigDecimal bigDecimal) {
        Common7Dialog cancleBt = new Common7Dialog(((StakeTRXContract.View) this.mView).getIContext()).setTitle(R.string.tips).setContent(R.string.freeze_tips1).setContent1(R.string.freeze_tips2).setBtListener(R.string.confirm_no_mistake, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWallet != null) {
                    SpAPI.THIS.setShowTips(mWallet.getWalletName(), str, true);
                }
                toConfirmWithSamsungSdk(transaction, bigDecimal);
                dialog.dismiss();
            }
        }).setCancleBt(R.string.cancle, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        this.dialog = cancleBt;
        cancleBt.show();
    }

    public int compareWithZero(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2);
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$4(obj);
            }
        });
        this.mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$5(obj);
            }
        });
        this.mRxManager.on(Event.BackToVoteHome, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$6(obj);
            }
        });
        this.mRxManager.on(Event.VOTE_TO_MULTI_VOTE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$7(obj);
            }
        });
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$8(obj);
            }
        });
    }

    public void lambda$onStart$4(Object obj) throws Exception {
        if (this.mView != 0) {
            ((StakeTRXContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$5(Object obj) throws Exception {
        if (this.mView != 0) {
            ((StakeTRXContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$6(Object obj) throws Exception {
        if (this.mView != 0) {
            ((StakeTRXContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$7(Object obj) throws Exception {
        if (this.mView != 0) {
            ((StakeTRXContract.View) this.mView).exit();
        }
    }

    public void lambda$onStart$8(Object obj) throws Exception {
        AccountUpdater.singleShot(0L);
        getData();
    }
}
