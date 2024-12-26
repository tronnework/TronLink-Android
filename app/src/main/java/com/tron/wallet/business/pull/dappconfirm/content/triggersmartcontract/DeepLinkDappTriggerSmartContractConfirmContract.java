package com.tron.wallet.business.pull.dappconfirm.content.triggersmartcontract;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import io.reactivex.Observable;
public interface DeepLinkDappTriggerSmartContractConfirmContract {

    public interface Model extends BaseModel {
        Observable<DappProjectIOutput> getProjectInfo(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getProjectInfo(String str);
    }

    public interface View extends BaseView {
        void updateHeaderInfoForProject(DappProjectInfoBean dappProjectInfoBean, boolean z);
    }
}
