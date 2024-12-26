package com.tron.wallet.business.walletmanager.detail;

import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Map;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public interface WalletDetailContract {

    public interface Model extends BaseModel {
        Observable<AccountBalanceOutput> getAccountInfosList(String str, RequestBody requestBody);

        Map<String, AccountBalanceOutput.DataBean.BalanceListBean> getLocalData(ArrayList<Map<String, Integer>> arrayList);

        double getTotalTRX();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addSome();

        abstract void onActivityResult(int i, int i2, Intent intent);
    }

    public interface View extends BaseView {
        Wallet getCurrentWallet();

        void setCurrentWallet(Wallet wallet);

        void setHasSwitchWallet(boolean z);

        void setHdWalletRelationShip(HdTronRelationshipBean hdTronRelationshipBean);

        void updateCardBg();

        void updateWaitSignCount(int i);

        void updateWalletName(String str);

        void updateWalletPath();

        void updateWalletToColdWatch();
    }
}
