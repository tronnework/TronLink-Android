package com.tron.wallet.business.stakev2.managementv2;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import io.reactivex.Observable;
import java.util.HashMap;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface ResourceManagementV2Contract {

    public interface Model extends BaseModel {
        Observable<HashMap<String, NameAddressExtraBean>> getAllAddress();

        Observable<Long> getCanDelegatedResourceMaxSize(int i, String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract Protocol.Account getAccount();

        abstract GrpcAPI.AccountResourceMessage getAccountResourceMessage();

        abstract void refreshAccountResources();

        abstract void setConfig(boolean z, String str);

        abstract void start();
    }

    public interface View extends BaseView {
        void showResourceIntro();

        void updateUI(int i, long j, long j2);

        void updateUI(ResourcesBean resourcesBean);
    }
}
