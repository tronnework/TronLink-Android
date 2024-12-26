package com.tron.wallet.business.walletmanager.selectwallet.finance;

import android.content.Context;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.wallet.business.finance.bean.FinanceDataSummaryOutput;
import com.tron.wallet.business.walletmanager.selectwallet.bean.FinanceSelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import io.reactivex.Observable;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectWalletFinanceContract {
    public static final int MODE_ALL = 0;
    public static final int MODE_BALANCE = 1;
    public static final int MODE_STAKED = 2;

    interface IView {
        Context getIContext();

        void onSearchComplete(List<FinanceSelectWalletBean> list, String str);
    }

    interface Model extends BaseModel {
        void clearCache();

        FinanceDataSummaryOutput.DataSummary getFinanceDataSummary();

        Observable<List<FinanceSelectWalletBean>> getSearchObservable(String str, WalletSortType walletSortType);

        void setConfig(int i, String str, String str2, String str3);

        void setSearchedTextColor(int i);
    }

    public static abstract class Presenter extends BasePresenter<Model, IView> {
        public abstract FinanceDataSummaryOutput.DataSummary getDataSummary();

        public abstract void search(String str);

        public abstract void setSelectedWallet(Wallet wallet);
    }
}
