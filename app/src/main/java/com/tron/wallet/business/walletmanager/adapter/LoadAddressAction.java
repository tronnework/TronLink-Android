package com.tron.wallet.business.walletmanager.adapter;

import android.content.Context;
import android.os.Bundle;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public interface LoadAddressAction<T extends Wallet> {
    <M extends BaseModel> void adaptUI(BaseActivity<? extends BasePresenter<M, ? extends BaseView>, M> baseActivity);

    Observable<List<T>> createBatchWallets(Bundle bundle, WalletPath walletPath, int i, int i2);

    Observable<T> createWallet(Bundle bundle, WalletPath walletPath);

    Observable<Set<String>> getAllExistsWalletAddress();

    Observable<AccountBalanceOutput> getBalance(String str);

    Observable<AccountBalanceOutput> getBalances(ArrayList<String> arrayList);

    WalletPath getWalletPath(Wallet wallet);

    void startChooseAddress(Context context, Bundle bundle, WalletPath walletPath, AddressItem addressItem, boolean z);

    void startChooseAddresses(Context context, Bundle bundle, ArrayList<AddressItem> arrayList, boolean z, String str, String str2, boolean z2);

    void startNext(Context context, Bundle bundle, Wallet wallet);
}
