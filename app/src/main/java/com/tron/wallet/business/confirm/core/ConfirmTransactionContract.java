package com.tron.wallet.business.confirm.core;

import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.bean.token.TransactionResult;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface ConfirmTransactionContract {

    public interface Model extends BaseModel {
        GrpcAPI.AccountResourceMessage getAccountResourceMessage();

        long getBandwidthCost(Protocol.Transaction.Contract contract);

        long getCurrentBandwidth();

        long getEnergy();

        long getNewBandwidth();

        void init(GrpcAPI.AccountResourceMessage accountResourceMessage);

        boolean isEnoughBandwidth();

        Observable<TransactionResult> transaction(RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract Wallet getCurrentWallet();

        abstract void onActivityResult(int i, int i2, Intent intent);

        abstract void send();
    }

    public interface View extends BaseView {

        public final class -CC {
            public static String $default$getpassword(View _this) {
                return "";
            }

            public static void $default$setButtonEnable(View _this, boolean z) {
            }

            public static void $default$showErrorText(View _this) {
            }

            public static void $default$showErrorText(View _this, String str) {
            }

            public static void $default$showLoadingFragment(View _this) {
            }

            public static void $default$updateLoadingFragment(View _this, GrpcAPI.Return r1, State state, int i) {
            }

            public static void $default$updateUI(View _this) {
            }
        }

        BaseParam getBaseParam();

        Intent getIIntent();

        String getpassword();

        boolean isActives();

        void setButtonEnable(boolean z);

        void setRootV(boolean z);

        void showErrorText();

        void showErrorText(String str);

        void showLoadingFragment();

        void updateLoadingFragment(GrpcAPI.Return r1, State state, int i);

        void updateUI();
    }
}
