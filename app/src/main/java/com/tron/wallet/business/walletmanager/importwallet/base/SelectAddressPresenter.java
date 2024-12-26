package com.tron.wallet.business.walletmanager.importwallet.base;

import android.os.Bundle;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.importwallet.base.SelectAddressContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import j$.util.Objects;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectAddressPresenter extends SelectAddressContract.Presenter {
    private Set<String> addresses;
    protected Wallet mWallet;

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

    @Override
    public void onClickNext(Bundle bundle) {
        if (this.mWallet == null || this.action == null) {
            return;
        }
        this.action.startNext(((SelectAddressContract.View) this.mView).getIContext(), bundle, this.mWallet);
    }

    @Override
    public void getBalance(String str) {
        this.action.getBalance(str).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, AccountBalanceOutput accountBalanceOutput) {
                ((SelectAddressContract.View) mView).onGetBalance(accountBalanceOutput);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "SelectAddressPresenter-getBalance"));
    }

    public void recreateWallet(WalletPath walletPath, boolean z) {
        WalletPath createDefault = WalletPath.createDefault();
        createDefault.account = walletPath.account + 1;
        createWallet(createDefault, z);
    }

    @Override
    public void createWallet(final WalletPath walletPath, final boolean z) {
        Observable<R> compose = this.action.createWallet(((SelectAddressContract.View) this.mView).getViewIntent().getExtras(), walletPath).compose(RxSchedulers2.io_main());
        Consumer consumer = new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$createWallet$0(walletPath, z, (Wallet) obj);
            }
        };
        final SelectAddressContract.View view = (SelectAddressContract.View) this.mView;
        Objects.requireNonNull(view);
        addDisposable(compose.subscribe(consumer, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SelectAddressContract.View.this.onCreateWalletFailed((Throwable) obj);
            }
        }));
    }

    public void lambda$createWallet$0(WalletPath walletPath, boolean z, Wallet wallet) throws Exception {
        Set<String> set = this.addresses;
        if (set != null && set.contains(wallet.getAddress())) {
            recreateWallet(walletPath, z);
            return;
        }
        this.mWallet = wallet;
        ((SelectAddressContract.View) this.mView).onCreateWallet(wallet, z);
        LogUtils.w("SelectAddressPresenter", String.format("onNext: walletAddress = %s", wallet.getAddress()));
    }

    @Override
    public WalletPath getPathFromWallet(Wallet wallet) {
        if (this.action != null) {
            return this.action.getWalletPath(wallet);
        }
        return WalletPath.createDefault();
    }

    @Override
    public void onClickAddress(WalletPath walletPath, AddressItem addressItem, boolean z) {
        if (this.action != null) {
            this.action.startChooseAddress(((SelectAddressContract.View) this.mView).getIContext(), ((SelectAddressContract.View) this.mView).getViewIntent().getExtras(), walletPath, addressItem, z);
        }
    }
}
