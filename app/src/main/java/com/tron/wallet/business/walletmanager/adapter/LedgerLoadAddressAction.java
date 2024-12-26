package com.tron.wallet.business.walletmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.ImportType;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tron.wallet.ledger.bleclient.BleTransport;
import com.tron.wallet.ledger.bleclient.LedgerTrx;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.blemodule.Device;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class LedgerLoadAddressAction extends BaseLoadAddressAction<Wallet> {
    @Override
    public void startChooseAddresses(Context context, Bundle bundle, ArrayList<AddressItem> arrayList, boolean z, String str, String str2, boolean z2) {
    }

    public LedgerLoadAddressAction() {
        BleClientManager.getInstance().createClient();
        LogUtils.w("LedgerAdapter", "onLifeCycleCreate: createClient");
    }

    private Observable<LedgerTrx> openBle(final String str) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$openBle$3(str, observableEmitter);
            }
        });
    }

    public void lambda$openBle$3(String str, final ObservableEmitter observableEmitter) throws Exception {
        Observable<Device> just;
        BleTransport bleTransport = (BleTransport) BleClientManager.getInstance().getTransport(str);
        final LedgerTrx ledgerTrx = new LedgerTrx(bleTransport);
        Device device = bleTransport.getDevice();
        if (device == null) {
            just = bleTransport.open().doOnError(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    LedgerLoadAddressAction.lambda$openBle$0((Throwable) obj);
                }
            });
        } else {
            just = Observable.just(device);
        }
        just.concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource doOnError;
                Device device2 = (Device) obj;
                doOnError = LedgerTrx.this.isAppOpened().doOnError(new Consumer() {
                    @Override
                    public final void accept(Object obj2) {
                        LedgerLoadAddressAction.lambda$openBle$1((Throwable) obj2);
                    }
                });
                return doOnError;
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean bool) {
                if (observableEmitter.isDisposed()) {
                    return;
                }
                observableEmitter.onNext(ledgerTrx);
            }

            @Override
            public void onError(Throwable th) {
                if (observableEmitter.isDisposed()) {
                    return;
                }
                LogUtils.e(th);
                observableEmitter.onError(th);
                dispose();
            }

            @Override
            public void onComplete() {
                if (observableEmitter.isDisposed()) {
                    return;
                }
                observableEmitter.onComplete();
                dispose();
            }
        });
    }

    public static void lambda$openBle$0(Throwable th) throws Exception {
        LogUtils.w("LedgerAdapter", "createWallet: transport open error");
        th.printStackTrace();
    }

    public static void lambda$openBle$1(Throwable th) throws Exception {
        LogUtils.w("LedgerAdapter", "openBle: open app error");
        th.printStackTrace();
    }

    @Override
    public <M extends BaseModel> void adaptUI(BaseActivity<? extends BasePresenter<M, ? extends BaseView>, M> baseActivity) {
        baseActivity.setHeaderBar(baseActivity.getString(R.string.import_address));
        if (baseActivity.getIntent().getBooleanExtra(ImportType.KEY_SHOW_STEP, false)) {
            baseActivity.setCommonTitle2(baseActivity.getString(R.string.step_2_2));
        }
        try {
            ((Button) baseActivity.findViewById(R.id.btn_next)).setText(R.string.confirm_import);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public Observable<Wallet> createWallet(Bundle bundle, final WalletPath walletPath) {
        return openBle(bundle.getString(TronConfig.IMPORT_CONTENT)).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$createWallet$7;
                lambda$createWallet$7 = lambda$createWallet$7(walletPath, (LedgerTrx) obj);
                return lambda$createWallet$7;
            }
        }).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$createWallet$8;
                lambda$createWallet$8 = lambda$createWallet$8(walletPath, (LedgerTrx.Address) obj);
                return lambda$createWallet$8;
            }
        });
    }

    public ObservableSource lambda$createWallet$7(final WalletPath walletPath, final LedgerTrx ledgerTrx) throws Exception {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$createWallet$6(ledgerTrx, walletPath, observableEmitter);
            }
        });
    }

    public void lambda$createWallet$6(LedgerTrx ledgerTrx, WalletPath walletPath, final ObservableEmitter observableEmitter) throws Exception {
        Observable<LedgerTrx.Address> address = getAddress(ledgerTrx, WalletPath.buildPath(walletPath));
        Objects.requireNonNull(observableEmitter);
        address.subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                ObservableEmitter.this.onNext((LedgerTrx.Address) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LedgerLoadAddressAction.lambda$createWallet$4(ObservableEmitter.this, (Throwable) obj);
            }
        }, new Action() {
            @Override
            public final void run() {
                LogUtils.w("LedgerAdapter", "createWallet: onComplete");
            }
        });
    }

    public static void lambda$createWallet$4(ObservableEmitter observableEmitter, Throwable th) throws Exception {
        LogUtils.e(th);
        observableEmitter.onError(th);
    }

    public ObservableSource lambda$createWallet$8(WalletPath walletPath, LedgerTrx.Address address) throws Exception {
        return Observable.just(createActual(address, walletPath));
    }

    @Override
    public Observable<List<Wallet>> createBatchWallets(Bundle bundle, final WalletPath walletPath, final int i, final int i2) {
        LogUtils.w("LedgerAdapter", "createBatchWallets: createBatchWallets");
        final boolean z = i == 0;
        return openBle(bundle.getString(TronConfig.IMPORT_CONTENT)).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$createBatchWallets$13;
                lambda$createBatchWallets$13 = lambda$createBatchWallets$13(i, i2, walletPath, z, (LedgerTrx) obj);
                return lambda$createBatchWallets$13;
            }
        });
    }

    public ObservableSource lambda$createBatchWallets$13(final int i, final int i2, final WalletPath walletPath, final boolean z, final LedgerTrx ledgerTrx) throws Exception {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$createBatchWallets$12(i, i2, ledgerTrx, walletPath, z, observableEmitter);
            }
        });
    }

    public void lambda$createBatchWallets$12(int i, int i2, final LedgerTrx ledgerTrx, WalletPath walletPath, boolean z, ObservableEmitter observableEmitter) throws Exception {
        Observable.range(i, i2).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$createBatchWallets$10;
                lambda$createBatchWallets$10 = lambda$createBatchWallets$10(ledgerTrx, (Integer) obj);
                return lambda$createBatchWallets$10;
            }
        }).doOnError(new Consumer() {
            @Override
            public final void accept(Object obj) {
                LedgerLoadAddressAction.lambda$createBatchWallets$11((Throwable) obj);
            }
        }).subscribe(new InnerObserver(observableEmitter, walletPath, z));
    }

    public ObservableSource lambda$createBatchWallets$10(LedgerTrx ledgerTrx, Integer num) throws Exception {
        final WalletPath createDefault = WalletPath.createDefault();
        createDefault.setAccount(num.intValue());
        return getAddress(ledgerTrx, WalletPath.buildPath(createDefault)).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$createBatchWallets$9;
                lambda$createBatchWallets$9 = lambda$createBatchWallets$9(createDefault, (LedgerTrx.Address) obj);
                return lambda$createBatchWallets$9;
            }
        });
    }

    public ObservableSource lambda$createBatchWallets$9(WalletPath walletPath, LedgerTrx.Address address) throws Exception {
        return Observable.just(createActual(address, walletPath));
    }

    public static void lambda$createBatchWallets$11(Throwable th) throws Exception {
        LogUtils.w("LedgerAdapter", "createBatchWallets: get address error");
        th.printStackTrace();
    }

    private Wallet createActual(LedgerTrx.Address address, WalletPath walletPath) {
        LogUtils.w("LedgerAdapter", "createActual: address " + address);
        return new LedgerWallet.Builder().setAddress(address.getAddress()).setPathJson(JSON.toJSONString(walletPath)).build();
    }

    private Observable<LedgerTrx.Address> getAddress(LedgerTrx ledgerTrx, String str) throws Exception {
        LogUtils.w("LedgerAdapter", "getAddress: ");
        return ledgerTrx.getAddress(str);
    }

    @Override
    public void startChooseAddress(Context context, Bundle bundle, WalletPath walletPath, AddressItem addressItem, boolean z) {
        super.startChooseAddress(context, bundle, walletPath, addressItem, z, 1);
    }

    public static final class InnerObserver extends DisposableObserver<Wallet> {
        private final ObservableEmitter<List<Wallet>> emitter;
        private final WalletPath filterPath;
        private final boolean isFirstPage;
        private final AtomicBoolean createdTopOne = new AtomicBoolean(false);
        private final AtomicReference<Wallet> cachedTopOne = new AtomicReference<>();
        private final List<Wallet> result = new ArrayList();

        InnerObserver(ObservableEmitter<List<Wallet>> observableEmitter, WalletPath walletPath, boolean z) {
            this.emitter = observableEmitter;
            this.filterPath = walletPath;
            this.isFirstPage = z;
        }

        @Override
        public void onNext(Wallet wallet) {
            if (this.emitter.isDisposed()) {
                dispose();
                return;
            }
            boolean equals = TextUtils.equals(wallet.getMnemonicPathString(), JSON.toJSONString(this.filterPath));
            if (equals && this.isFirstPage) {
                this.result.add(0, wallet);
                this.createdTopOne.compareAndSet(false, true);
            } else if (!equals) {
                this.result.add(wallet);
            } else {
                this.cachedTopOne.set(wallet);
            }
            LogUtils.w("LedgerAdapter", "inner observer onNext: created wallet");
        }

        @Override
        public void onError(Throwable th) {
            LogUtils.w("LedgerAdapter", "onError: getAddress Error");
            LogUtils.e(th);
            if (isDisposed()) {
                return;
            }
            this.emitter.onError(th);
            dispose();
        }

        @Override
        public void onComplete() {
            if (isDisposed()) {
                return;
            }
            if (!this.createdTopOne.get() && this.isFirstPage && this.cachedTopOne.get() != null) {
                this.result.add(0, this.cachedTopOne.get());
            }
            this.emitter.onNext(this.result);
            this.emitter.onComplete();
            dispose();
            LogUtils.w("LedgerAdapter", "inner observer onComplete: return the final result");
        }
    }

    @Override
    public void startNext(Context context, Bundle bundle, Wallet wallet) {
        try {
            String string = bundle.getString(TronConfig.IMPORT_NAME, "");
            wallet.setWalletName(string);
            WalletUtils.saveWatchOnly(wallet);
            WalletUtils.setSelectedWallet(string);
            LogUtils.w("LedgerAdapter", String.format("startNext: saveWallet: path %s, address %s", wallet.getMnemonicPathString(), wallet.getAddress()));
            Intent intent = new Intent(context, MainTabActivity.class);
            intent.setFlags(67108864);
            if (bundle != null && bundle.containsKey(TronConfig.WALLET_TYPE)) {
                WalletNameGeneratorUtils.finish(bundle.getInt(TronConfig.WALLET_TYPE), bundle.getBoolean(AddWalletType.INTENT_KEY_SHIELD, false));
            }
            if (bundle.getBoolean(AddWalletType.INTENT_KEY_SHIELD, false)) {
                intent.putExtra(TronConfig.SHIELD_IS_TRC20, true);
            }
            context.startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        } catch (CipherException | DuplicateNameException | InvalidNameException e) {
            LogUtils.e(e);
        }
    }
}
