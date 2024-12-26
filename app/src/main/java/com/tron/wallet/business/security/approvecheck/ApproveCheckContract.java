package com.tron.wallet.business.security.approvecheck;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import io.reactivex.Observable;
import java.util.HashMap;
public interface ApproveCheckContract {

    public interface Model extends BaseModel {
        Observable<HashMap<String, NameAddressExtraBean>> getAllAddress();

        Observable<ApproveListDatabeanOutput> requestApproveList(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void reQuestApproveList(String str, String str2);
    }

    public interface View extends BaseView {
        void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap);

        void updateApproveList(String str, ApproveListDatabeanOutput approveListDatabeanOutput);
    }
}
