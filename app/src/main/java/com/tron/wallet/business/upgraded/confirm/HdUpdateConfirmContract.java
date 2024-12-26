package com.tron.wallet.business.upgraded.confirm;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.upgraded.bean.UpgradeHdBean;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmModel;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
public interface HdUpdateConfirmContract {

    public interface Model extends BaseModel {
        Observable<AccountBalanceOutput> getBalances(List<HdTronRelationshipBean> list);

        List<HdTronRelationshipBean> getHdAddressList();

        boolean refreshDb(UpgradeHdBean upgradeHdBean);

        List<UpgradeHdBean> sortWallet(List<HdTronRelationshipBean> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map, HdUpdateConfirmModel.SortType sortType, boolean z);

        Map<String, AccountBalanceOutput.DataBean.BalanceListBean> toMap(List<AccountBalanceOutput.DataBean.BalanceListBean> list);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void onNext(UpgradeHdBean upgradeHdBean, UpgradeHdBean upgradeHdBean2);
    }

    public interface View extends BaseView {
        void enabledButton(boolean z);

        void refreshData(List<UpgradeHdBean> list);
    }
}
