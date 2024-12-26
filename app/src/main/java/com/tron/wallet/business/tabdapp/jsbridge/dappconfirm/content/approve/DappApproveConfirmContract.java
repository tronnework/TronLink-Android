package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import io.reactivex.Observable;
public interface DappApproveConfirmContract {

    public interface Model extends BaseModel {
        Observable<DappProjectIOutput> getProjectInfo(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
    }

    public interface View extends BaseView {
        void switchOne();

        void updateApproveAmount(String str);

        void updateHeaderInfoForProject(DappProjectInfoBean dappProjectInfoBean, boolean z);
    }
}
