package com.tron.wallet.business.walletmanager.importwallet;

import android.util.Pair;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface ImportWalletContract {

    public interface Model extends BaseModel {
        Pair<Integer, String> saveWallet(boolean z, String str, String str2, String str3, int i);

        boolean saveWalletWithMnemonic(String str, String str2, String str3);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void importWalletWithKeyStore(String str, String str2, String str3);

        public abstract void importWalletWithMnemonic(String str, String str2, String str3, String str4);

        public abstract void importWalletWithPrivateKey(String str, String str2, String str3, String str4);
    }

    public interface View extends BaseView {
        android.view.View getRootView();

        boolean isShield();

        void showLocalHDTips(boolean z);
    }
}
