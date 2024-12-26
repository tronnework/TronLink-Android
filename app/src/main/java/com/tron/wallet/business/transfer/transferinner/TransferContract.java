package com.tron.wallet.business.transfer.transferinner;

import android.content.Intent;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.NameAddressBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import io.reactivex.Observable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface TransferContract {

    public interface Model extends BaseModel {
        int getCurrentTokenIndex(List<TokenBean> list, TokenBean tokenBean);

        Observable<List<TokenBean>> getMyAssets(String str);

        Observable<List<TokenBean>> getMyAssetsDb(String str);

        GrpcAPI.ReceiveNote getReceiveNote(long j, String str, String str2);

        String isTRX(TokenBean tokenBean);

        @Deprecated
        int removeZeroBalance(CopyOnWriteArrayList<TokenBean> copyOnWriteArrayList, TokenBean tokenBean);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addToAddressBook(String str);

        abstract void doSome();

        abstract void getAccount(int i, String str);

        abstract void getAccount(int i, String str, ViewListener viewListener);

        abstract void getAssetsHomeData(String str);

        abstract TokenBean getShiledToken(boolean z);

        abstract TokenBean getToken();

        abstract CopyOnWriteArrayList<TokenBean> getTokenList();

        abstract Wallet getWallet();

        abstract void initRecentAddressAdapter();

        abstract void notifyData(int i);

        abstract void send(String str, String str2, String str3, BigDecimal bigDecimal);

        abstract void sendShieldTtoZ(String str, String str2, BigDecimal bigDecimal);

        abstract void sendShieldZtoT(String str, String str2, BigDecimal bigDecimal, boolean z);

        abstract void sendShieldZtoZ(String str, String str2, BigDecimal bigDecimal);

        abstract void updateIsTrx(String str);
    }

    public interface View extends BaseView {
        void afterToAddressChanged();

        void changeDeletOrScan(int i, boolean z);

        ErrorEdiTextLayout getEditSendAddress();

        EditText getEtNote();

        String getEtToAddress();

        Intent getIIntent();

        double getNmBalacne();

        RecyclerView getRecentAddressListView();

        boolean isActive();

        void onNetReconnect();

        void setButtonEnable(boolean z);

        void setButtonText(int i);

        void setCurSendWalletResMessage(GrpcAPI.AccountResourceMessage accountResourceMessage);

        void setIsTrx(String str);

        void setReceiveAddressErrorText(int i);

        void setSendAddressErrorText(int i);

        void setToAddress(NameAddressBean nameAddressBean);

        void setTransferCountErrorText(int i);

        void showReceiveAddressError(boolean z);

        void showSendAddressError(boolean z);

        void showTransferCountError(boolean z);

        void updateAddToAddressBook();

        void updateBalance(String str, TokenBean tokenBean);

        void updateFee(long j, long j2, long j3);
    }

    public interface ViewListener {
        void connectionErrorListener();

        void viewListener(Protocol.Account account);
    }
}
