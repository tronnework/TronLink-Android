package com.tron.wallet.business.transfer.selectaddress;

import android.content.ClipboardManager;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressContract;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.RiskAccountOutput;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AccountPermissionUtils;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.StringTronUtil;
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
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SelectSendAddressPresenter extends SelectSendAddressContract.Presenter {
    private static final String[][] blackHoleAddress = {new String[]{"TLsV52sRDL79HXGGm9yzwKibb6BeruhUzy", "T9yD14Nj9j7xAB4dbGeiX9h8unkKHxuWwb"}, new String[]{"TNJ1YHzREsf7AoKhstf627ZrbtCzTj7f55", "TDPJULRzVtzVjnBmZvfaTcTNQ2tsVi6XxQ"}};
    private BaseTextWatcher editTextWatcher;
    private PermissionHelper permissionHelper;
    private PublishSubject<String> publishSubject;
    private Protocol.Account receiverAccount;

    @Override
    public Protocol.Account getReceivedAccount() {
        return this.receiverAccount;
    }

    @Override
    protected void onStart() {
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
                return 50001;
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
                ScannerActivity.start(((SelectSendAddressContract.View) mView).getScannerResultLauncher());
            }
        };
    }

    @Override
    public void checkInputAddress(final String str, final int i, final NameAddressExtraBean nameAddressExtraBean) {
        final String trim = nameAddressExtraBean.getAddress().toString().trim();
        Observable create = Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$checkInputAddress$0(nameAddressExtraBean, str, observableEmitter);
            }
        });
        final Observable compose = Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                SelectSendAddressPresenter.lambda$checkInputAddress$1(trim, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
        final Observable<RiskAccountOutput> onErrorReturn = ((SelectSendAddressContract.Model) this.mModel).checkAccountRisk(trim).onErrorReturn(new Function() {
            @Override
            public final Object apply(Object obj) {
                RiskAccountOutput createDefault;
                Throwable th = (Throwable) obj;
                createDefault = RiskAccountOutput.createDefault();
                return createDefault;
            }
        });
        create.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$checkInputAddress$4;
                lambda$checkInputAddress$4 = lambda$checkInputAddress$4(compose, onErrorReturn, i, (SelectSendAddressContract.InputAddressBean) obj);
                return lambda$checkInputAddress$4;
            }
        }).subscribe(new IObserver(new ICallback<SelectSendAddressContract.InputAddressBean>() {
            @Override
            public void onResponse(String str2, SelectSendAddressContract.InputAddressBean inputAddressBean) {
                Pair<CharSequence, CharSequence> findSimilarAddress;
                ((SelectSendAddressContract.View) mView).updateInputCheckResult(inputAddressBean);
                if (inputAddressBean == null || inputAddressBean.getError().ordinal() >= SelectSendAddressContract.InputError.ERROR.ordinal() || (findSimilarAddress = ((SelectSendAddressContract.Model) mModel).findSimilarAddress(((SelectSendAddressContract.View) mView).getIContext(), inputAddressBean.getAddressBean().getAddress().toString())) == null) {
                    return;
                }
                ((SelectSendAddressContract.View) mView).updateWarningSimilarAddress(findSimilarAddress);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((SelectSendAddressContract.View) mView).toast(((SelectSendAddressContract.View) mView).getIContext().getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "checkInputAddress"));
    }

    public void lambda$checkInputAddress$0(NameAddressExtraBean nameAddressExtraBean, String str, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(preCheck(str, nameAddressExtraBean.copy(), null));
        observableEmitter.onComplete();
    }

    public static void lambda$checkInputAddress$1(String str, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(TronAPI.queryAccount(StringTronUtil.decode58Check(str), false));
        observableEmitter.onComplete();
    }

    public ObservableSource lambda$checkInputAddress$4(Observable observable, Observable observable2, final int i, final SelectSendAddressContract.InputAddressBean inputAddressBean) throws Exception {
        if (inputAddressBean.getError().ordinal() >= SelectSendAddressContract.InputError.WARNING.ordinal()) {
            return Observable.just(inputAddressBean);
        }
        return Observable.zip(observable, observable2, new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                SelectSendAddressContract.InputAddressBean lambda$checkInputAddress$3;
                lambda$checkInputAddress$3 = lambda$checkInputAddress$3(i, inputAddressBean, (Protocol.Account) obj, (RiskAccountOutput) obj2);
                return lambda$checkInputAddress$3;
            }
        });
    }

    public SelectSendAddressContract.InputAddressBean lambda$checkInputAddress$3(int i, SelectSendAddressContract.InputAddressBean inputAddressBean, Protocol.Account account, RiskAccountOutput riskAccountOutput) throws Exception {
        this.receiverAccount = account;
        return getCheckInputResult(i, inputAddressBean, riskAccountOutput);
    }

    private SelectSendAddressContract.InputAddressBean getCheckInputResult(int i, final SelectSendAddressContract.InputAddressBean inputAddressBean, RiskAccountOutput riskAccountOutput) {
        String string;
        if (riskAccountOutput.getData().isRisk()) {
            inputAddressBean.setError(SelectSendAddressContract.InputError.SCAM);
            inputAddressBean.setMessage(((SelectSendAddressContract.View) this.mView).getIContext().getString(R.string.risk_account_tip));
            inputAddressBean.setRiskType(SelectSendAddressContract.RiskType.RISK_ACCOUNT);
            return inputAddressBean;
        }
        if (isNullAccount(this.receiverAccount)) {
            if (((SelectSendAddressContract.View) this.mView).getPageType() == PageType.DELEGATE_BANDWIDTH || ((SelectSendAddressContract.View) this.mView).getPageType() == PageType.DELEGATE_ENERGY) {
                inputAddressBean.error = SelectSendAddressContract.InputError.ERROR;
                string = ((SelectSendAddressContract.View) this.mView).getIContext().getString(R.string.deletegate_not_activate);
            } else {
                inputAddressBean.error = SelectSendAddressContract.InputError.INFO;
                if (i == 1 || i == 0) {
                    string = ((SelectSendAddressContract.View) this.mView).getIContext().getString(R.string.transfer_not_activate_10);
                } else if (i == 5) {
                    string = ((SelectSendAddressContract.View) this.mView).getIContext().getString(R.string.transfer_not_activate_20, "TRC721");
                } else {
                    string = ((SelectSendAddressContract.View) this.mView).getIContext().getString(R.string.transfer_not_activate_20, TronConfig.TRC20);
                }
            }
            inputAddressBean.setMessage(string);
        } else if (this.receiverAccount.getType().equals(Protocol.AccountType.Contract)) {
            if (((SelectSendAddressContract.View) this.mView).getPageType() == PageType.DELEGATE_BANDWIDTH || ((SelectSendAddressContract.View) this.mView).getPageType() == PageType.DELEGATE_ENERGY) {
                inputAddressBean.error = SelectSendAddressContract.InputError.ERROR;
                inputAddressBean.setMessage(AppContextUtil.getContext().getString(R.string.incorrect_address_contract_for_delegate));
            } else {
                inputAddressBean.error = SelectSendAddressContract.InputError.WARNING;
                inputAddressBean.setMessage(AppContextUtil.getContext().getString(R.string.incorrect_address_contract));
                inputAddressBean.riskType = SelectSendAddressContract.RiskType.CONTRACT;
            }
        } else {
            AccountPermissionUtils.isAccountPermissionMultiSign(((SelectSendAddressContract.View) this.mView).getIContext(), this.receiverAccount, new BiConsumer() {
                @Override
                public final void accept(Object obj, Object obj2) {
                    lambda$getCheckInputResult$5(inputAddressBean, (Boolean) obj, (String) obj2);
                }
            });
        }
        return inputAddressBean;
    }

    public void lambda$getCheckInputResult$5(SelectSendAddressContract.InputAddressBean inputAddressBean, Boolean bool, String str) throws Exception {
        if (bool.booleanValue()) {
            inputAddressBean.setMessage(((SelectSendAddressContract.View) this.mView).getIContext().getString(ResourceStringProvider.getError(((SelectSendAddressContract.View) this.mView).getPageType()).multiSignAccount));
            inputAddressBean.setError(SelectSendAddressContract.InputError.INFO);
            inputAddressBean.riskType = SelectSendAddressContract.RiskType.MULTI_SIGN;
        }
    }

    private SelectSendAddressContract.InputAddressBean preCheck(String str, NameAddressExtraBean nameAddressExtraBean, SelectSendAddressContract.InputAddressBean inputAddressBean) {
        int i;
        NameAddressExtraBean findFirst;
        if (inputAddressBean == null) {
            inputAddressBean = new SelectSendAddressContract.InputAddressBean();
        }
        inputAddressBean.setAddressBean(nameAddressExtraBean);
        String trim = nameAddressExtraBean.getAddress().toString().trim();
        nameAddressExtraBean.setIndentAddress(StringTronUtil.indentAddress(trim, 6));
        SelectSendAddressContract.InputError inputError = SelectSendAddressContract.InputError.OK;
        if (trim.length() != 34 || StringTronUtil.TronAddress.TRON != StringTronUtil.isAddressValid2(trim)) {
            inputError = SelectSendAddressContract.InputError.ERROR;
            i = R.string.incorrect_address_format;
        } else if (TextUtils.equals(str, trim)) {
            inputError = SelectSendAddressContract.InputError.ERROR;
            i = R.string.incorrect_address_same;
        } else if (!Arrays.asList(getCurrentBlackHoleAddress()).contains(trim)) {
            i = 0;
        } else if (((SelectSendAddressContract.View) this.mView).getPageType() == PageType.DELEGATE_BANDWIDTH || ((SelectSendAddressContract.View) this.mView).getPageType() == PageType.DELEGATE_ENERGY) {
            inputError = SelectSendAddressContract.InputError.ERROR;
            i = R.string.incorrect_address_burn_token_for_delegate;
        } else {
            inputError = SelectSendAddressContract.InputError.WARNING;
            i = R.string.incorrect_address_burn_token;
        }
        if (inputError.ordinal() < SelectSendAddressContract.InputError.ERROR.ordinal() && TextUtils.isEmpty(nameAddressExtraBean.getName()) && (findFirst = ((SelectSendAddressContract.Model) this.mModel).findFirst(str, trim)) != null) {
            nameAddressExtraBean.setName(findFirst.getName());
            nameAddressExtraBean.setStorage(findFirst.getStorage());
        }
        inputAddressBean.setError(inputError);
        if (i > 0) {
            inputAddressBean.setMessage(AppContextUtil.getContext().getString(i));
        }
        return inputAddressBean;
    }

    private String[] getCurrentBlackHoleAddress() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE) {
            return blackHoleAddress[0];
        }
        return blackHoleAddress[1];
    }

    public ObservableSource lambda$subscribeSearchEvent$7(final String str) throws Exception {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$subscribeSearchEvent$6(str, observableEmitter);
            }
        });
    }

    @Override
    public void subscribeSearchEvent(final EditText editText) {
        bindEditText(editText, new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$subscribeSearchEvent$7;
                lambda$subscribeSearchEvent$7 = lambda$subscribeSearchEvent$7((String) obj);
                return lambda$subscribeSearchEvent$7;
            }
        }).subscribe(new IObserver(new ICallback<List<NameAddressExtraBean>>() {
            @Override
            public void onResponse(String str, List<NameAddressExtraBean> list) {
                ((SelectSendAddressContract.View) mView).updateSearchResult(list, editText.getText().toString().trim());
            }

            @Override
            public void onFailure(String str, String str2) {
                ((SelectSendAddressContract.View) mView).toast(((SelectSendAddressContract.View) mView).getIContext().getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "subscribeSearchEvent"));
    }

    public void lambda$subscribeSearchEvent$6(String str, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(doSearch(str));
    }

    @Override
    List<NameAddressExtraBean> doSearch(String str) {
        return ((SelectSendAddressContract.Model) this.mModel).doSearch(((SelectSendAddressActivity) this.mView).selectWallet.getAddress(), str);
    }

    @Override
    public void loadAddress(final String str) {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$loadAddress$8(str, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<SparseArray<List<NameAddressExtraBean>>>() {
            @Override
            public void onResponse(String str2, SparseArray<List<NameAddressExtraBean>> sparseArray) {
                if (sparseArray != null) {
                    ((SelectSendAddressContract.View) mView).updateAddressList(sparseArray, false);
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((SelectSendAddressContract.View) mView).toast(((SelectSendAddressContract.View) mView).getIContext().getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "loadAddress"));
    }

    public void lambda$loadAddress$8(String str, ObservableEmitter observableEmitter) throws Exception {
        ((SelectSendAddressContract.Model) this.mModel).initType(((SelectSendAddressContract.View) this.mView).getPageType());
        observableEmitter.onNext(((SelectSendAddressContract.Model) this.mModel).getAllAddresses(str));
        observableEmitter.onComplete();
    }

    @Override
    public String parseClipContent() {
        try {
            return ((ClipboardManager) ((SelectSendAddressContract.View) this.mView).getIContext().getSystemService(Context.CLIPBOARD_SERVICE)).getPrimaryClip().getItemAt(0).getText().toString();
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }

    @Override
    public Observable<Protocol.Account> queryAccount(final String str, final String str2) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$queryAccount$9(str, str2, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public void lambda$queryAccount$9(String str, String str2, ObservableEmitter observableEmitter) throws Exception {
        Protocol.Account account = WalletUtils.getAccount(((SelectSendAddressContract.View) this.mView).getIContext(), str);
        if (isNullAccount(account)) {
            account = TronAPI.queryAccount(StringTronUtil.decode58Check(str2), false);
        }
        observableEmitter.onNext(account);
        observableEmitter.onComplete();
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
    public Pair<Boolean, Integer> checkTransferSupportability(Wallet wallet) {
        if (wallet.isSamsungWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            return new Pair<>(false, Integer.valueOf((int) R.string.no_samsung_to_shield));
        }
        if (wallet.isLedgerHDWallet() && !SpAPI.THIS.getCurrentChain().isMainChain) {
            return new Pair<>(false, Integer.valueOf((int) R.string.ledger_not_support_on_dappchain));
        }
        return new Pair<>(true, 0);
    }

    @Override
    public NameAddressExtraBean insertNewAddedAddress(String str, String str2, String str3) {
        return ((SelectSendAddressContract.Model) this.mModel).insertNewAddedAddress(str, str2, str3);
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
                if (trim.length() == 34) {
                    ((SelectSendAddressContract.View) mView).checkInputAddress(trim);
                } else {
                    ((SelectSendAddressActivity) mView).errorView.setVisibility(View.GONE);
                }
            }
        };
        this.editTextWatcher = baseTextWatcher;
        editText.addTextChangedListener(baseTextWatcher);
        return compose;
    }

    public boolean considerNoInput(String str) {
        return ((SelectSendAddressContract.Model) this.mModel).considerNoInput(str);
    }

    public boolean isNullAccount(Protocol.Account account) {
        return account == null || account.toString().length() <= 0;
    }

    public class fun6 implements SingleOnSubscribe<Boolean> {
        final NameAddressExtraBean val$bean;

        fun6(NameAddressExtraBean nameAddressExtraBean) {
            this.val$bean = nameAddressExtraBean;
        }

        @Override
        public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
            List<AddressDao> searchAll = AddressController.getInstance(((SelectSendAddressContract.View) mView).getIContext()).searchAll();
            if (searchAll.size() >= 300) {
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
        return Single.create(new fun6(nameAddressExtraBean)).compose(RxSchedulers2.io_main_single());
    }
}
