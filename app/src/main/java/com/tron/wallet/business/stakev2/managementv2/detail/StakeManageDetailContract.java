package com.tron.wallet.business.stakev2.managementv2.detail;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import io.reactivex.Observable;
import java.util.HashMap;
public interface StakeManageDetailContract {

    public interface Model extends BaseModel {
        Observable<HashMap<String, NameAddressExtraBean>> getAllAddress();

        Observable<StakeResourceDetailForMeOutput> getStakeResourceForMe(String str, int i, int i2);

        Observable<StakeResourceDetailOutput> getStakeResourceForOther(String str, int i, boolean z, long j);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getAllAddresses();

        abstract void loadDataStakeForMe(String str, int i, int i2);

        abstract void loadDataStakeForOther(String str, int i, boolean z, long j);
    }

    public interface View {
        void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap);

        void updateStakeForMeList(StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput);

        void updateStakeForOtherList(long j, StakeResourceDetailOutput stakeResourceDetailOutput);

        void updateStakeListFail(long j);

        void updateUI(long j, long j2);
    }
}
