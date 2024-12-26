package com.tron.wallet.business.walletmanager.selectwallet.search;

import android.content.Context;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import io.reactivex.Observable;
import java.util.List;
class SearchWalletContract {

    public interface IPresenter {
        void getAccountInfoList();

        void search(String str);
    }

    interface IView {
        Context getIContext();

        void onSearchComplete(List<SelectWalletBean> list, String str);

        void updateAccountInfo(List<SelectWalletBean> list);

        void updateRecentWallets(List<SelectWalletBean> list);
    }

    interface Model extends BaseModel {
        void clearCache();

        Observable<List<SelectWalletBean>> getAccountInfo();

        Observable<List<SelectWalletBean>> getSearchObservable(String str, WalletSortType walletSortType);

        void setSearchedTextColor(int i);
    }

    static abstract class Presenter extends BasePresenter<Model, IView> implements IPresenter {
        abstract void getRecentWallets();

        @Override
        public abstract void search(String str);
    }

    interface View extends BaseView, IView {
    }

    SearchWalletContract() {
    }
}
