package com.tron.wallet.business.walletmanager.selectaddress;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.walletmanager.adapter.LoadAddressAction;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public interface SelectAddressContract {

    public interface Model extends BaseModel {
        Observable<? extends List<? extends Wallet>> deriveAddress(String str, WalletPath walletPath, int i, int i2);

        Observable<AccountBalanceOutput> getBalances(List<? extends Wallet> list);

        LoadAddressAction<? extends Wallet> getLoadAddressAction();

        void setLoadAddressAction(LoadAddressAction<? extends Wallet> loadAddressAction);
    }

    public interface View extends BaseView {
        WalletPath getArgumentPath();

        android.view.View getRootView();

        void onAddressCreateFail(Throwable th);

        void onAddressCreated(Set<String> set, List<? extends Wallet> list);

        void onGetBalances(AccountBalanceOutput accountBalanceOutput);

        void showUnCancelableLoadingDialog();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void createWallets(ArrayList<AddressItem> arrayList, ArrayList<Wallet> arrayList2, String str, String str2, boolean z, boolean z2, boolean z3);

        abstract void deriveAddress(String str, int i, int i2);

        abstract void getBalances(List<? extends Wallet> list);

        abstract void onAddressSelected(ArrayList<AddressItem> arrayList, Wallet wallet);

        public final void setLoadAddressAction(LoadAddressAction<? extends Wallet> loadAddressAction) {
            if (this.mModel != 0) {
                ((Model) this.mModel).setLoadAddressAction(loadAddressAction);
            }
        }
    }
}
