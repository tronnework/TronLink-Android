package com.tron.wallet.business.tronpower.management;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import io.reactivex.Observable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface ResourceManagementContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getWitnessList();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract Protocol.Account getAccount();

        abstract GrpcAPI.AccountResourceMessage getAccountResourceMessage();

        abstract void refreshAccountResources();

        abstract void start();
    }

    public interface View extends BaseView {
        void showResourceIntro();

        void updateUI(ResourcesBean resourcesBean);

        void updateVoteApr(String str);
    }
}
