package com.tron.wallet.business.walletmanager.adapter;

import android.content.Context;
import android.os.Bundle;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletModel;
import com.tron.wallet.business.walletmanager.selectaddress.SelectAnotherAddressActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.tron.walletserver.I_TYPE;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class MnemonicLoadAddressAction extends BaseLoadAddressAction<Wallet> {
    private final ImportWalletModel innerModel = new ImportWalletModel();

    @Override
    public void startNext(Context context, Bundle bundle, Wallet wallet) {
    }

    @Override
    public <M extends BaseModel> void adaptUI(BaseActivity<? extends BasePresenter<M, ? extends BaseView>, M> baseActivity) {
        baseActivity.setHeaderBar(baseActivity.getString(R.string.hd_mnemonic_import));
        baseActivity.setCommonTitle2(baseActivity.getString(R.string.step_2_4));
    }

    @Override
    public Observable<Wallet> createWallet(final Bundle bundle, final WalletPath walletPath) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$createWallet$0(bundle, walletPath, observableEmitter);
            }
        });
    }

    public void lambda$createWallet$0(Bundle bundle, WalletPath walletPath, ObservableEmitter observableEmitter) throws Exception {
        Wallet wallet;
        String string = bundle.getString(TronConfig.IMPORT_CONTENT);
        boolean z = bundle.getBoolean(AddWalletType.INTENT_KEY_SHIELD, false);
        if (z) {
            wallet = this.innerModel.create(z, I_TYPE.MNEMONIC, string);
            wallet.setShieldedWallet(true);
        } else {
            wallet = new Wallet(string, walletPath);
        }
        if (wallet == null) {
            wallet = new Wallet(string, walletPath);
        }
        observableEmitter.onNext(wallet);
    }

    @Override
    public Observable<List<Wallet>> createBatchWallets(Bundle bundle, final WalletPath walletPath, final int i, final int i2) {
        final boolean z = i == 0;
        final String string = bundle.getString(TronConfig.IMPORT_CONTENT);
        final boolean z2 = bundle.getBoolean(AddWalletType.INTENT_KEY_SHIELD, false);
        final WalletPath createDefault = WalletPath.createDefault();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$createBatchWallets$3(i, i2, createDefault, z2, string, walletPath, z, atomicBoolean, observableEmitter);
            }
        });
    }

    public void lambda$createBatchWallets$3(int i, int i2, final WalletPath walletPath, final boolean z, final String str, final WalletPath walletPath2, final boolean z2, final AtomicBoolean atomicBoolean, ObservableEmitter observableEmitter) throws Exception {
        final ArrayList arrayList = new ArrayList();
        final AtomicReference atomicReference = new AtomicReference();
        Observable.range(i, i2).doOnSubscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                atomicReference.set((Disposable) obj);
            }
        }).doOnComplete(new Action() {
            @Override
            public final void run() {
                MnemonicLoadAddressAction.lambda$createBatchWallets$1(atomicReference);
            }
        }).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$createBatchWallets$2(walletPath, z, str, walletPath2, z2, arrayList, atomicBoolean, (Integer) obj);
            }
        });
        LogUtils.w("CreateAccount", "createBatchWallets: final result");
        if (!atomicBoolean.get() && z2) {
            arrayList.add(0, new Wallet(str, walletPath2));
        }
        observableEmitter.onNext(arrayList);
        observableEmitter.onComplete();
    }

    public static void lambda$createBatchWallets$1(AtomicReference atomicReference) throws Exception {
        if (atomicReference.get() != null) {
            ((Disposable) atomicReference.get()).dispose();
        }
    }

    public void lambda$createBatchWallets$2(WalletPath walletPath, boolean z, String str, WalletPath walletPath2, boolean z2, List list, AtomicBoolean atomicBoolean, Integer num) throws Exception {
        LogUtils.w("CreateAccount", "createBatchWallets: index = " + num);
        walletPath.accountIndex = num.intValue();
        Wallet createActual = createActual(z, str, walletPath);
        boolean equals = walletPath.equals(walletPath2);
        if (equals && z2) {
            list.add(0, createActual);
            atomicBoolean.set(true);
        } else if (equals) {
        } else {
            list.add(createActual);
        }
    }

    private Wallet createActual(boolean z, String str, WalletPath walletPath) {
        return z ? this.innerModel.create(z, I_TYPE.MNEMONIC, str) : new Wallet(str, walletPath);
    }

    @Override
    public void startChooseAddress(Context context, Bundle bundle, WalletPath walletPath, AddressItem addressItem, boolean z) {
        if (bundle == null || context == null) {
            return;
        }
        if (walletPath == null) {
            WalletPath.createDefault();
        }
        bundle.getString(TronConfig.IMPORT_CONTENT);
    }

    @Override
    public void startChooseAddresses(Context context, Bundle bundle, ArrayList<AddressItem> arrayList, boolean z, String str, String str2, boolean z2) {
        if (bundle == null || context == null) {
            return;
        }
        SelectAnotherAddressActivity.start(context, bundle.getString(TronConfig.IMPORT_CONTENT), arrayList, z, false, CommonKey.REQUEST_CODE_CHANGE_ADDRESS, 0, true, str, str2, z2);
    }
}
