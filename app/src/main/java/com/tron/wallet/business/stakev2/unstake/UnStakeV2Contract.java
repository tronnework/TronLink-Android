package com.tron.wallet.business.stakev2.unstake;

import android.content.Intent;
import android.text.Editable;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.business.stakev2.unstake.ResSwitch;
import com.tron.wallet.business.stakev2.unstake.UnStakeV2Activity;
import com.tron.wallet.common.components.StakePercentView;
import io.reactivex.Observable;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface UnStakeV2Contract {

    public interface Model extends BaseModel {
        Observable<Protocol.Account> getAccount(Protocol.Account account, String str);

        Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(GrpcAPI.AccountResourceMessage accountResourceMessage, String str);

        UnStakeV2Bean getViewData(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage);
    }

    public static abstract class Presenter extends BasePresenter<EmptyModel, View> {
        abstract StakePercentView.OnClickPercentListener OnClickPercentListener();

        abstract ResSwitch.OnResSwitchListener OnResSwitchListener();

        abstract void afterTextChanged(Editable editable);

        abstract boolean checkInput(String str);

        abstract void loadData();

        abstract void next(boolean z);

        abstract void toManager(boolean z);
    }

    public interface View extends BaseView {
        Intent getIIntent();

        String getInputText();

        void hideArrow(boolean z);

        void resetPercent(ResState resState);

        void setButtonEnable(boolean z);

        void setInputText(String str);

        void showError(UnStakeV2Activity.Error error);

        void updateInput(String str, String str2);

        void updateUI(UnStakeV2Bean unStakeV2Bean, ResState resState);
    }
}
