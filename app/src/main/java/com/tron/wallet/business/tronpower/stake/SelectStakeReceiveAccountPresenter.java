package com.tron.wallet.business.tronpower.stake;

import android.content.ClipboardManager;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tronpower.stake.SelectStakeReceiveAccountContract;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.dialog.Common7Dialog;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Stream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
import org.tron.walletserver.Wallet;
public class SelectStakeReceiveAccountPresenter extends SelectStakeReceiveAccountContract.Presenter {
    private static final String[][] blackHoleAddress = {new String[]{"TLsV52sRDL79HXGGm9yzwKibb6BeruhUzy", "T9yD14Nj9j7xAB4dbGeiX9h8unkKHxuWwb"}, new String[]{"TNJ1YHzREsf7AoKhstf627ZrbtCzTj7f55", "TDPJULRzVtzVjnBmZvfaTcTNQ2tsVi6XxQ"}};
    private Common7Dialog dialog;
    private BaseTextWatcher editTextWatcher;
    private Wallet mWallet;
    private NumberFormat numberFormat;
    private PermissionHelper permissionHelper;
    private PublishSubject<String> publishSubject;
    private Protocol.Account receiverAccount;

    @Override
    public void getData() {
    }

    @Override
    public void init() {
        this.mWallet = WalletUtils.getSelectedPublicWallet();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
    }

    @Override
    public void scanQr(FragmentActivity fragmentActivity) {
        if (this.permissionHelper == null) {
            this.permissionHelper = new PermissionHelper(fragmentActivity, getPermissionInterface());
        }
        this.permissionHelper.requestPermissions();
    }

    private PermissionInterface getPermissionInterface() {
        return new PermissionInterface() {
            @Override
            public int getPermissionsRequestCode() {
                return 50002;
            }

            @Override
            public void requestPermissionsFail() {
            }

            @Override
            public String[] getPermissions() {
                return new String[]{"android.permission.CAMERA"};
            }

            @Override
            public void requestPermissionsSuccess() {
                ScannerActivity.start(((SelectStakeReceiveAccountContract.View) mView).getScannerResultLauncher());
            }
        };
    }

    @Override
    public void loadAddress(final String str) {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$loadAddress$0(str, observableEmitter);
            }
        }).subscribe(new IObserver(new ICallback<SparseArray<List<NameAddressExtraBean>>>() {
            @Override
            public void onResponse(String str2, SparseArray<List<NameAddressExtraBean>> sparseArray) {
                ((SelectStakeReceiveAccountContract.View) mView).updateAddressList(sparseArray);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((SelectStakeReceiveAccountContract.View) mView).toast(((SelectStakeReceiveAccountContract.View) mView).getIContext().getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "loadAddress"));
    }

    public void lambda$loadAddress$0(String str, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(((SelectStakeReceiveAccountContract.Model) this.mModel).getAllAddresses(str));
        observableEmitter.onComplete();
    }

    @Override
    public NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3) {
        return ((SelectStakeReceiveAccountContract.Model) this.mModel).insertNewAddedAddress(str, str2, str3);
    }

    @Override
    public void stake() {
        final String stakeAddress = ((SelectStakeReceiveAccountContract.View) this.mView).getStakeAddress();
        final String selectedAddress = ((SelectStakeReceiveAccountContract.View) this.mView).getSelectedAddress();
        final BigDecimal stakeAmount = ((SelectStakeReceiveAccountContract.View) this.mView).getStakeAmount();
        final boolean isFreezeBandwidth = ((SelectStakeReceiveAccountContract.View) this.mView).isFreezeBandwidth();
        if (!StringTronUtil.isEmpty(stakeAddress)) {
            if (!StringTronUtil.isAddressValid(StringTronUtil.decodeFromBase58Check(stakeAddress))) {
                ((SelectStakeReceiveAccountContract.View) this.mView).setErrorCountStatus(true);
                return;
            }
            ((SelectStakeReceiveAccountContract.View) this.mView).setErrorCountStatus(false);
            if (!SpAPI.THIS.getCurIsMainChain() && !TextUtils.equals(stakeAddress, selectedAddress)) {
                ((SelectStakeReceiveAccountContract.View) this.mView).toast(((SelectStakeReceiveAccountContract.View) this.mView).getIContext().getString(((SelectStakeReceiveAccountContract.View) this.mView).isMultiSign() ? R.string.stake_toast_dapp_multisign : R.string.stake_toast_dapp));
                return;
            }
            ((SelectStakeReceiveAccountContract.View) this.mView).getNextButton().setEnabled(false);
            ((SelectStakeReceiveAccountContract.View) this.mView).showLoadingDialog();
            ((SelectStakeReceiveAccountContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$stake$2(selectedAddress, stakeAmount, stakeAddress, isFreezeBandwidth);
                }
            });
            return;
        }
        ((SelectStakeReceiveAccountContract.View) this.mView).setErrorCountStatus(true, R.string.address_empty);
    }

    public void lambda$stake$2(String str, final BigDecimal bigDecimal, final String str2, boolean z) {
        final GrpcAPI.TransactionExtention transactionExtention = null;
        try {
            transactionExtention = TronAPI.createFreezeBalanceTransaction(StringTronUtil.decodeFromBase58Check(str), bigDecimal.longValue(), 3L, str.equals(str2) ? null : StringTronUtil.decodeFromBase58Check(str2), z ? Common.ResourceCode.BANDWIDTH : Common.ResourceCode.ENERGY);
        } catch (Exception unused) {
        }
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                lambda$stake$1(transactionExtention, str2, bigDecimal);
            }
        });
    }

    public void lambda$stake$1(GrpcAPI.TransactionExtention transactionExtention, String str, BigDecimal bigDecimal) {
        ((SelectStakeReceiveAccountContract.View) this.mView).getNextButton().setEnabled(true);
        ((SelectStakeReceiveAccountContract.View) this.mView).dismissLoadingDialog();
        if (transactionExtention != null && transactionExtention.hasResult()) {
            if (new String(transactionExtention.getResult().getMessage().toByteArray()).contains("contract validate error :") && new String(transactionExtention.getResult().getMessage().toByteArray()).contains("not exists")) {
                ((SelectStakeReceiveAccountContract.View) this.mView).setErrorCountStatus(true, R.string.address_unuse);
                return;
            } else if (((SelectStakeReceiveAccountContract.View) this.mView).isFreezeBandwidth()) {
                toConfirmWithSamsungSdk(transactionExtention.getTransaction(), bigDecimal);
                return;
            } else {
                toConfirmWithSamsungSdk(transactionExtention.getTransaction(), bigDecimal);
                return;
            }
        }
        ToastUtil.getInstance().showToast(((SelectStakeReceiveAccountContract.View) this.mView).getIContext(), R.string.freeze_fail);
    }

    private void showDialog(final Protocol.Transaction transaction, final String str, final BigDecimal bigDecimal) {
        Common7Dialog cancleBt = new Common7Dialog(((SelectStakeReceiveAccountContract.View) this.mView).getIContext()).setTitle(R.string.tips).setContent(R.string.freeze_tips1).setContent1(R.string.freeze_tips2).setBtListener(R.string.confirm_no_mistake, new View.OnClickListener() {
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

    public void toConfirmWithSamsungSdk(Protocol.Transaction transaction, BigDecimal bigDecimal) {
        toConfirm(transaction, bigDecimal);
    }

    public void toConfirm(Protocol.Transaction transaction, BigDecimal bigDecimal) {
        boolean z = this.mWallet.getCreateType() == 7;
        if (!z) {
            try {
                WalletUtils.checkHaveOwnerPermissions(this.mWallet.getAddress(), ((SelectStakeReceiveAccountActvitiy) ((SelectStakeReceiveAccountContract.View) this.mView).getIContext()).getAccount().getOwnerPermission());
            } catch (Exception unused) {
            }
        }
        String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(((SelectStakeReceiveAccountContract.View) this.mView).getStakeAddress());
        String stakeAddress = TextUtils.isEmpty(nameByAddress) ? ((SelectStakeReceiveAccountContract.View) this.mView).getStakeAddress() : String.format(String.format("%s\n(%s)", nameByAddress, ((SelectStakeReceiveAccountContract.View) this.mView).getStakeAddress()), new Object[0]);
        boolean equals = TextUtils.equals(WalletUtils.getSelectedWallet().getAddress(), ((SelectStakeReceiveAccountContract.View) this.mView).getStakeAddress());
        ConfirmTransactionNewActivity.startActivity(((SelectStakeReceiveAccountContract.View) this.mView).getIContext(), ParamBuildUtils.getFreezeTransactionParamBuilder(((SelectStakeReceiveAccountContract.View) this.mView).isFreezeBandwidth(), !((SelectStakeReceiveAccountContract.View) this.mView).isMultiSign(), equals, ((SelectStakeReceiveAccountContract.View) this.mView).getCanUseTrxCount(), bigDecimal.longValue() / 1000000.0d, ((SelectStakeReceiveAccountContract.View) this.mView).getAountEnergy(), stakeAddress, this.numberFormat.format(bigDecimal.longValue() / 1000000.0d) + " TRX", ((SelectStakeReceiveAccountContract.View) this.mView).getStatAction(), transaction), z);
    }

    public class fun5 implements SingleOnSubscribe<Boolean> {
        final NameAddressExtraBean val$bean;

        fun5(NameAddressExtraBean nameAddressExtraBean) {
            this.val$bean = nameAddressExtraBean;
        }

        @Override
        public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
            List<AddressDao> searchAll = AddressController.getInstance(((SelectStakeReceiveAccountContract.View) mView).getIContext()).searchAll();
            if (searchAll.size() >= 30) {
                singleEmitter.onSuccess(false);
                return;
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            Stream stream = Collection.-EL.stream(searchAll);
            final NameAddressExtraBean nameAddressExtraBean = this.val$bean;
            stream.filter(new Predicate() {
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
                    equals = TextUtils.equals(((AddressDao) obj).getAddress(), NameAddressExtraBean.this.getAddress());
                    return equals;
                }
            }).findFirst().ifPresent(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    AddressDao addressDao = (AddressDao) obj;
                    atomicBoolean.set(true);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            if (atomicBoolean.get()) {
                singleEmitter.onSuccess(false);
                return;
            }
            Stream stream2 = Collection.-EL.stream(WalletUtils.getAllWallets());
            final NameAddressExtraBean nameAddressExtraBean2 = this.val$bean;
            stream2.filter(new Predicate() {
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
                    equals = TextUtils.equals(((Wallet) obj).getAddress(), NameAddressExtraBean.this.getAddress());
                    return equals;
                }
            }).findFirst().ifPresent(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    Wallet wallet = (Wallet) obj;
                    atomicBoolean.set(true);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            singleEmitter.onSuccess(Boolean.valueOf(!atomicBoolean.get()));
        }
    }

    @Override
    public SingleSource<Boolean> showAddToAddressBook(NameAddressExtraBean nameAddressExtraBean) {
        return Single.create(new fun5(nameAddressExtraBean)).compose(RxSchedulers2.io_main_single());
    }

    @Override
    public void checkInputAddress(final String str, final NameAddressExtraBean nameAddressExtraBean) {
        if (nameAddressExtraBean == null) {
            nameAddressExtraBean = NameAddressExtraBean.getDefault();
            nameAddressExtraBean.setAddress(((SelectStakeReceiveAccountContract.View) this.mView).getStakeAddress());
        }
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$checkInputAddress$3(str, nameAddressExtraBean, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<SelectStakeReceiveAccountContract.InputAddressBean>() {
            @Override
            public void onResponse(String str2, SelectStakeReceiveAccountContract.InputAddressBean inputAddressBean) {
                ((SelectStakeReceiveAccountContract.View) mView).updateInputCheckResult(inputAddressBean);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((SelectStakeReceiveAccountContract.View) mView).toast(((SelectStakeReceiveAccountContract.View) mView).getIContext().getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "checkInputAddress"));
    }

    public void lambda$checkInputAddress$3(String str, NameAddressExtraBean nameAddressExtraBean, ObservableEmitter observableEmitter) throws Exception {
        SelectStakeReceiveAccountContract.InputAddressBean preCheck = preCheck(str, nameAddressExtraBean);
        observableEmitter.onNext(preCheck);
        if (preCheck.getError().ordinal() < SelectStakeReceiveAccountContract.InputError.ERROR.ordinal()) {
            observableEmitter.onNext(getCheckInputBean(preCheck));
        }
        observableEmitter.onComplete();
    }

    @Override
    public void setTextWithoutWatcher(EditText editText, CharSequence charSequence) {
        BaseTextWatcher baseTextWatcher = this.editTextWatcher;
        if (baseTextWatcher == null) {
            return;
        }
        editText.removeTextChangedListener(baseTextWatcher);
        editText.setText(charSequence);
        editText.setSelection(charSequence.length());
        editText.addTextChangedListener(this.editTextWatcher);
    }

    @Override
    public boolean considerNoInput(String str) {
        return str.getBytes().length <= 0;
    }

    private SelectStakeReceiveAccountContract.InputAddressBean preCheck(String str, NameAddressExtraBean nameAddressExtraBean) {
        int i;
        NameAddressExtraBean findFirst;
        SelectStakeReceiveAccountContract.InputAddressBean inputAddressBean = new SelectStakeReceiveAccountContract.InputAddressBean();
        inputAddressBean.setAddressBean(nameAddressExtraBean);
        String charSequence = nameAddressExtraBean.getAddress().toString();
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(charSequence, 6));
        SelectStakeReceiveAccountContract.InputError inputError = SelectStakeReceiveAccountContract.InputError.OK;
        if (StringTronUtil.TronAddress.TRON != StringTronUtil.isAddressValid2(charSequence)) {
            inputError = SelectStakeReceiveAccountContract.InputError.ERROR;
            i = R.string.incorrect_stake_address_format;
        } else if (Arrays.asList(getCurrentBlackHoleAddress()).contains(charSequence)) {
            inputError = SelectStakeReceiveAccountContract.InputError.ERROR;
            i = R.string.incorrect_stake_address_burn_token;
        } else {
            i = 0;
        }
        if (inputError.ordinal() < SelectStakeReceiveAccountContract.InputError.ERROR.ordinal() && (findFirst = ((SelectStakeReceiveAccountContract.Model) this.mModel).findFirst(str, charSequence)) != null) {
            nameAddressExtraBean.setName(findFirst.getName());
            nameAddressExtraBean.setStorage(findFirst.getStorage());
        }
        inputAddressBean.setError(inputError);
        if (i > 0) {
            inputAddressBean.setMessage(AppContextUtil.getContext().getString(i));
        }
        return inputAddressBean;
    }

    private SelectStakeReceiveAccountContract.InputAddressBean getCheckInputBean(SelectStakeReceiveAccountContract.InputAddressBean inputAddressBean) {
        this.receiverAccount = null;
        String charSequence = inputAddressBean.addressBean.getAddress().toString();
        try {
            this.receiverAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(charSequence), false);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (inputAddressBean.error.ordinal() >= SelectStakeReceiveAccountContract.InputError.INFO.ordinal()) {
            return inputAddressBean;
        }
        if (!((SelectStakeReceiveAccountContract.View) this.mView).isMultiSign()) {
            if (!SpAPI.THIS.getCurIsMainChain() && !WalletUtils.getSelectedWallet().getAddress().equals(charSequence)) {
                inputAddressBean.error = SelectStakeReceiveAccountContract.InputError.ERROR;
                inputAddressBean.setMessage(((SelectStakeReceiveAccountContract.View) this.mView).getIContext().getString(R.string.stake_toast_dapp));
                return inputAddressBean;
            }
        } else if (!SpAPI.THIS.getCurIsMainChain() && charSequence != null && !charSequence.equals(((SelectStakeReceiveAccountContract.View) this.mView).getSelectedAddress())) {
            inputAddressBean.error = SelectStakeReceiveAccountContract.InputError.ERROR;
            inputAddressBean.setMessage(((SelectStakeReceiveAccountContract.View) this.mView).getIContext().getString(R.string.stake_toast_dapp_multisign));
            return inputAddressBean;
        }
        if (isNullAccount(this.receiverAccount)) {
            inputAddressBean.error = SelectStakeReceiveAccountContract.InputError.ERROR;
            inputAddressBean.setMessage(((SelectStakeReceiveAccountContract.View) this.mView).getIContext().getString(R.string.address_unuse));
        } else if (this.receiverAccount.getType().equals(Protocol.AccountType.Contract)) {
            inputAddressBean.error = SelectStakeReceiveAccountContract.InputError.ERROR;
            inputAddressBean.setMessage(AppContextUtil.getContext().getString(R.string.incorrect_stake_contract_address_burn_token));
        }
        return inputAddressBean;
    }

    private String[] getCurrentBlackHoleAddress() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE) {
            return blackHoleAddress[0];
        }
        return blackHoleAddress[1];
    }

    public ObservableSource lambda$subscribeSearchEvent$5(final String str) throws Exception {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$subscribeSearchEvent$4(str, observableEmitter);
            }
        });
    }

    @Override
    public void subscribeSearchEvent(final EditText editText) {
        bindEditText(editText, new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$subscribeSearchEvent$5;
                lambda$subscribeSearchEvent$5 = lambda$subscribeSearchEvent$5((String) obj);
                return lambda$subscribeSearchEvent$5;
            }
        }).subscribe(new IObserver(new ICallback<List<NameAddressExtraBean>>() {
            @Override
            public void onResponse(String str, List<NameAddressExtraBean> list) {
                ((SelectStakeReceiveAccountContract.View) mView).updateSearchResult(list, editText.getText().toString().trim());
            }

            @Override
            public void onFailure(String str, String str2) {
                ((SelectStakeReceiveAccountContract.View) mView).toast(((SelectStakeReceiveAccountContract.View) mView).getIContext().getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "subscribeSearchEvent"));
    }

    public void lambda$subscribeSearchEvent$4(String str, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(doSearch(str));
    }

    @Override
    public List<NameAddressExtraBean> doSearch(String str) {
        return ((SelectStakeReceiveAccountContract.Model) this.mModel).doSearch(((SelectStakeReceiveAccountContract.View) this.mView).getSelectedAddress(), str);
    }

    private <T> Observable<List<T>> bindEditText(final EditText editText, Function<String, ObservableSource<List<T>>> function) {
        PublishSubject<String> create = PublishSubject.create();
        this.publishSubject = create;
        Observable<List<T>> compose = create.switchMap(function).compose(RxSchedulers2.io_main());
        BaseTextWatcher baseTextWatcher = new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                String trim = editable.toString().trim();
                editText.setTypeface(Typeface.defaultFromStyle(!TextUtils.isEmpty(trim) ? 1 : 0));
                publishSubject.onNext(trim);
                if (trim.length() >= 34) {
                    ((SelectStakeReceiveAccountContract.View) mView).checkInputAddress(trim);
                } else {
                    ((SelectStakeReceiveAccountActvitiy) mView).errorView.setVisibility(View.GONE);
                }
            }
        };
        this.editTextWatcher = baseTextWatcher;
        editText.addTextChangedListener(baseTextWatcher);
        return compose;
    }

    public boolean isNullAccount(Protocol.Account account) {
        return account == null || account.toString().length() <= 0;
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BroadcastSuccess, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$6(obj);
            }
        });
    }

    public void lambda$onStart$6(Object obj) throws Exception {
        if (this.mView != 0) {
            ((SelectStakeReceiveAccountContract.View) this.mView).exit();
        }
    }

    @Override
    public String parseClipContent() {
        try {
            return ((ClipboardManager) ((SelectStakeReceiveAccountContract.View) this.mView).getIContext().getSystemService(Context.CLIPBOARD_SERVICE)).getPrimaryClip().getItemAt(0).getText().toString();
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }
}
