package com.tron.wallet.business.walletmanager.relation;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public interface RelationContract {

    public interface Model extends BaseModel {
        Observable<AccountBalanceOutput> getAccountInfosList(String str, RequestBody requestBody);

        Map<String, AccountBalanceOutput.DataBean.BalanceListBean> getLocalData(ArrayList<Map<String, Integer>> arrayList);

        double getTotalTRX();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addData();

        public abstract void getData();

        abstract void getRelationWallet(String str);
    }

    public interface View extends BaseView {
        boolean isShieldManage();

        void updateUi(List<Wallet> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map);
    }
}
