package com.tron.wallet.business.walletmanager.selectwallet;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
public interface SelectWalletContract {

    public interface Model extends BaseModel {
        List<TRXAccountBalanceBean> getAccountBalance();

        Observable<AccountBalanceOutput> getAccountInfosList(String str, RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract WalletSortType getWalletSortType();

        abstract void getWallets();

        abstract int getWalletsSize();

        abstract void setShowShieldWallet(boolean z);

        abstract void setWalletSortType(WalletSortType walletSortType);
    }

    public interface View extends BaseView {
        void refreshData();

        void updateData(List<SelectWalletBean> list);
    }
}
