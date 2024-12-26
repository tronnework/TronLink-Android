package com.tron.wallet.business.walletmanager.createaccount;

import android.content.Intent;
import android.util.Pair;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import io.reactivex.Observable;
import java.util.List;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public interface CreateAccountContract {

    public interface Model extends BaseModel {
        boolean existWallet(String str);

        Observable<AccountBalanceOutput> getBalances(List<? extends Wallet> list);

        Wallet getHDWallet(String str);

        Pair<WalletPath, Wallet> getNextWallet(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract NoDoubleClickListener btSwitchAccountListener();

        abstract NoDoubleClickListener btSwitchHDListener();

        abstract NoDoubleClickListener btTvRightListener();

        abstract void create();

        abstract void onActivityResult(int i, int i2, Intent intent);

        @Override
        protected void onStart() {
        }
    }

    public interface View extends BaseView {
        Intent _getIntent();

        void generateWalletName(String str);

        String getInputWalletName();

        void hideSwitchHdButton();

        void showNameError(String str);

        void updateBalance(double d);

        void updateBalances(AccountBalanceOutput accountBalanceOutput);

        void updateButtonEnable(boolean z);

        void updateHDUI(Wallet wallet);

        void updateItems(List<AddressItem> list);
    }
}
