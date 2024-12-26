package com.tron.wallet.business.walletmanager.selectaddress;

import android.app.Activity;
import android.content.Intent;
import android.widget.PopupWindow;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.migrate.MigrateActivityExternalSyntheticLambda2;
import com.tron.wallet.business.walletmanager.adapter.LoadAddressAction;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.selectaddress.SelectAddressContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.interfaces.MultiImportListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.tron.walletserver.Wallet;
public class SelectAddressPresenter extends SelectAddressContract.Presenter {
    private Set<String> addresses;
    private final AtomicBoolean isLoading = new AtomicBoolean(false);
    private String mnemonic;
    private PopupWindow multiImportPop;

    public void lambda$deriveAddress$0(Set set) throws Exception {
        this.addresses = set;
    }

    @Override
    public void deriveAddress(String str, int i, int i2) {
        this.mnemonic = str;
        if (this.isLoading.get()) {
            return;
        }
        this.isLoading.set(true);
        addDisposable(((SelectAddressContract.Model) this.mModel).deriveAddress(str, ((SelectAddressContract.View) this.mView).getArgumentPath(), i, i2).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$deriveAddress$1;
                lambda$deriveAddress$1 = lambda$deriveAddress$1((List) obj);
                return lambda$deriveAddress$1;
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$deriveAddress$2((List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$deriveAddress$3((Throwable) obj);
            }
        }));
    }

    public ObservableSource lambda$deriveAddress$1(List list) throws Exception {
        LoadAddressAction<? extends Wallet> loadAddressAction = ((SelectAddressContract.Model) this.mModel).getLoadAddressAction();
        if (loadAddressAction != null && this.addresses == null) {
            addDisposable(loadAddressAction.getAllExistsWalletAddress().subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$deriveAddress$0((Set) obj);
                }
            }, new MigrateActivityExternalSyntheticLambda2()));
        }
        return Observable.just(list);
    }

    public void lambda$deriveAddress$2(List list) throws Exception {
        ((SelectAddressContract.View) this.mView).onAddressCreated(this.addresses, list);
        getBalances(list);
        this.isLoading.set(false);
    }

    public void lambda$deriveAddress$3(Throwable th) throws Exception {
        this.isLoading.set(false);
        ((SelectAddressContract.View) this.mView).onAddressCreateFail(th);
    }

    @Override
    void getBalances(List<? extends Wallet> list) {
        ((SelectAddressContract.Model) this.mModel).getBalances(list).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
                ((SelectAddressContract.View) mView).onGetBalances(accountBalanceOutput);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "ChangeAddressPresenter-getBalance"));
    }

    @Override
    public void createWallets(final ArrayList<AddressItem> arrayList, final ArrayList<Wallet> arrayList2, final String str, final String str2, final boolean z, boolean z2, final boolean z3) {
        if (arrayList == null) {
            return;
        }
        Intent intent = ((Activity) ((SelectAddressContract.View) this.mView).getIContext()).getIntent();
        final int intExtra = intent.getIntExtra("from", 0);
        intent.getBooleanExtra(CommonKey.IS_FIRST_IMPORT, false);
        Collections.sort(arrayList, new Comparator<AddressItem>() {
            @Override
            public int compare(AddressItem addressItem, AddressItem addressItem2) {
                return addressItem.getIndex() - addressItem2.getIndex();
            }
        });
        try {
            ((SelectAddressContract.View) this.mView).showUnCancelableLoadingDialog();
            ((SelectAddressContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$createWallets$6(arrayList2, arrayList, intExtra, str, z3, z, str2);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$createWallets$6(java.util.ArrayList r18, java.util.ArrayList r19, int r20, java.lang.String r21, boolean r22, boolean r23, java.lang.String r24) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.walletmanager.selectaddress.SelectAddressPresenter.lambda$createWallets$6(java.util.ArrayList, java.util.ArrayList, int, java.lang.String, boolean, boolean, java.lang.String):void");
    }

    public void lambda$createWallets$4() {
        ((SelectAddressContract.View) this.mView).dismissLoadingDialog();
        ((SelectAddressContract.View) this.mView).toast(((SelectAddressContract.View) this.mView).getIContext().getString(R.string.import_failure));
    }

    public void lambda$createWallets$5() {
        ((SelectAddressContract.View) this.mView).dismissLoadingDialog();
    }

    private void toMain(final ArrayList<Wallet> arrayList, final boolean z, final boolean z2) {
        ((SelectAddressContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$toMain$7(z, arrayList, z2);
            }
        });
    }

    public void lambda$toMain$7(boolean z, ArrayList arrayList, boolean z2) {
        ((SelectAddressContract.View) this.mView).dismissLoadingDialog();
        PopupWindow popupWindow = this.multiImportPop;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        if (!z) {
            WalletUtils.setSelectedWallet(((Wallet) arrayList.get(0)).getWalletName());
        }
        if (SpAPI.THIS.isCold()) {
            SpAPI.THIS.setColdWalletSelected(((Wallet) arrayList.get(0)).getWalletName());
        }
        if (z2) {
            showPop();
        } else {
            successNext();
        }
    }

    public void successNext() {
        Intent intent = new Intent(((SelectAddressContract.View) this.mView).getIContext(), MainTabActivity.class);
        intent.setFlags(67108864);
        this.mRxManager.post(Event.JumpToMain, "");
        ((SelectAddressContract.View) this.mView).go(intent);
        ((SelectAddressContract.View) this.mView).exit();
    }

    @Override
    public void onAddressSelected(ArrayList<AddressItem> arrayList, Wallet wallet) {
        if (arrayList == null) {
            return;
        }
        Intent intent = new Intent();
        arrayList.get(0).setSelected(true);
        intent.putExtra("key_data", (Serializable) arrayList.get(0));
        ((Activity) ((SelectAddressContract.View) this.mView).getIContext()).setResult(CommonKey.RESULT_CODE_CHANGE_ADDRESS, intent);
        ((Activity) ((SelectAddressContract.View) this.mView).getIContext()).finish();
    }

    @Override
    protected void onStart() {
        List<Wallet> allWallets = WalletUtils.getAllWallets();
        this.addresses = new HashSet();
        for (int i = 0; i < allWallets.size(); i++) {
            Wallet wallet = allWallets.get(i);
            if (wallet != null) {
                this.addresses.add(wallet.getAddress());
            }
        }
    }

    public void showPop() {
        if (this.multiImportPop == null) {
            this.multiImportPop = PopupWindowUtil.showMultiImport(((SelectAddressContract.View) this.mView).getIContext(), new MultiImportListener() {
                @Override
                public void success() {
                    if (multiImportPop != null) {
                        multiImportPop.dismiss();
                    }
                }

                @Override
                public void dismiss() {
                    successNext();
                }
            });
        }
        this.multiImportPop.showAtLocation(((SelectAddressContract.View) this.mView).getRootView(), 17, 0, 0);
    }

    @Override
    public void onBackPressed() {
        PopupWindow popupWindow = this.multiImportPop;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
