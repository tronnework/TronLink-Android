package com.tron.wallet.business.stakev2.stake;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import io.reactivex.Observable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface StakeHomeContract {

    public interface Model extends BaseModel {
        Observable<Protocol.Account> getAccount(Protocol.Account account, String str);

        Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(GrpcAPI.AccountResourceMessage accountResourceMessage, String str);

        Observable<WitnessOutput> getWitnessList();

        Observable<WitnessOutput> requestWitnessList(String str, int i, int i2, int i3, int i4);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract StakeHomeAprBean calculateApr(WitnessOutput witnessOutput, WitnessOutput witnessOutput2);

        protected abstract void getMaxVoteApr();

        protected abstract void getMaxVoteAprOrCurrentApr();

        abstract void loadData();

        public abstract void setConfig(boolean z, String str);

        public abstract void start();
    }

    public interface View extends BaseView {
        void hiddenTheStakeV1Pop();

        void showEmptyPage();

        void updateNoStakeApr(String str);

        void updateUI(ResourcesBean resourcesBean, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage);

        void updateVoteApr(StakeHomeAprBean stakeHomeAprBean);
    }
}
