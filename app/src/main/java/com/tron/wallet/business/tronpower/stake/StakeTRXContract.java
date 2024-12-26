package com.tron.wallet.business.tronpower.stake;

import android.widget.Button;
import android.widget.EditText;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.utils.DataStatHelper;
import io.reactivex.Observable;
import java.math.BigDecimal;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface StakeTRXContract {

    public interface Model extends BaseModel {
        Observable<Protocol.Account> getAccount(Protocol.Account account, String str);

        Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getData();

        abstract boolean hasOwnerPermission(String str, Protocol.Account account);

        public abstract void init(Protocol.Account account);

        abstract Observable<Protocol.Account> queryAccount(String str, String str2);
    }

    public interface View extends BaseView {
        BigDecimal expectGetBandWidth(BigDecimal bigDecimal);

        BigDecimal expectGetEnergy(BigDecimal bigDecimal);

        Protocol.Account getAccount();

        GrpcAPI.AccountResourceMessage getAccountResMessage();

        double getCanUseTrxCount();

        Wallet getDefaultWallet();

        EditText getEtAmount();

        Button getNextButton();

        String getResourceCount();

        String getSelectedAddress();

        String getSelectedAddressName();

        DataStatHelper.StatAction getStatAction();

        boolean isFreezeBandwidth();

        boolean isMultisign();

        void setButtonEnable(boolean z);

        void setErrorCountStatus(boolean z);

        void setErrorCountStatus(boolean z, int i);

        void updateBtStatus(boolean z);

        void updateUI(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage);
    }
}
