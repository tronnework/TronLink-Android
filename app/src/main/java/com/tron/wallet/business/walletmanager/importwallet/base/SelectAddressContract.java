package com.tron.wallet.business.walletmanager.importwallet.base;

import android.content.Intent;
import android.os.Bundle;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.wallet.business.walletmanager.adapter.LoadAddressAction;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public interface SelectAddressContract {

    public static abstract class Presenter extends BasePresenter<EmptyModel, View> {
        protected LoadAddressAction<? extends Wallet> action;

        public abstract void createWallet(WalletPath walletPath, boolean z);

        public abstract void getBalance(String str);

        public abstract WalletPath getPathFromWallet(Wallet wallet);

        public abstract void onClickAddress(WalletPath walletPath, AddressItem addressItem, boolean z);

        public abstract void onClickNext(Bundle bundle);

        public void setLoadAddressAction(LoadAddressAction<? extends Wallet> loadAddressAction) {
            this.action = loadAddressAction;
        }
    }

    public interface View extends BaseView {
        Intent getViewIntent();

        void onCreateWallet(Wallet wallet, boolean z);

        void onCreateWalletFailed(Throwable th);

        void onGetBalance(AccountBalanceOutput accountBalanceOutput);
    }
}
