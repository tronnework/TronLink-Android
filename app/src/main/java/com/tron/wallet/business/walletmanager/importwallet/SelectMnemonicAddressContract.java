package com.tron.wallet.business.walletmanager.importwallet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.walletmanager.adapter.LoadAddressAction;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import java.util.ArrayList;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public interface SelectMnemonicAddressContract {

    public static abstract class Model implements BaseModel {
        public abstract Pair<WalletPath, Wallet> getNextWallet(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected LoadAddressAction<? extends Wallet> action;

        public abstract void createWallet(WalletPath walletPath, boolean z);

        public abstract void getBalance(String str);

        public abstract void getBalances(ArrayList<String> arrayList);

        public abstract WalletPath getPathFromWallet(Wallet wallet);

        public abstract void onClickAddress(WalletPath walletPath, boolean z);

        public abstract void onClickNext(Bundle bundle);

        public void setLoadAddressAction(LoadAddressAction<? extends Wallet> loadAddressAction) {
            this.action = loadAddressAction;
        }
    }

    public interface View extends BaseView {
        ArrayList<AddressItem> getAddressItems();

        String getContent();

        String getName();

        String getPassword();

        android.view.View getRootView();

        Intent getViewIntent();

        ArrayList<Wallet> getWalletLists();

        boolean isCheckNoHD();

        boolean isShield();

        void onCreateWallet(Wallet wallet, boolean z);

        void onCreateWalletFailed(Throwable th);

        void onGetBalance(AccountBalanceOutput accountBalanceOutput);

        void showUnCanceledLoading(String str);
    }
}
