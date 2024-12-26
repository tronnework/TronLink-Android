package com.tron.wallet.business.cold;

import android.content.Intent;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.base.EmptyModel;
import org.tron.walletserver.Wallet;
public interface ColdContract {

    public static abstract class Presenter extends BasePresenter<EmptyModel, View> {
        abstract void backup();

        abstract boolean checkWalletWatchOnly();

        abstract Wallet getWallet();

        abstract void onActivityResult(int i, int i2, Intent intent);

        abstract void updateSelectedWallet();
    }

    public interface View extends BaseView {
        void refreshOfflineShastaView();

        void refreshOfflineWallet(Wallet wallet);

        void showNoNetTipView(boolean z);

        void showOrHidenSafeTipView(boolean z);

        void updateColdView();
    }
}
