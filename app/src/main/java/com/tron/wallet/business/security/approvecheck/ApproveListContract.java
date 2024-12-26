package com.tron.wallet.business.security.approvecheck;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import org.tron.api.GrpcAPI;
public interface ApproveListContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void approve(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11);
    }

    public interface View extends BaseView {
        void startCancelConfirmApprove(GrpcAPI.TransactionExtention transactionExtention, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11);
    }
}
