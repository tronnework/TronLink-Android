package com.tron.wallet.business.confirm.core.pending;

import android.os.Bundle;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import io.reactivex.Observable;
import org.tron.api.GrpcAPI;
public interface ConfirmPendingContract {

    public interface Model extends BaseModel {
        Observable<TransactionInfoBean> getTransactionInfo(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getTransactionInfo(String str);

        public abstract void initData();

        public abstract GrpcAPI.Return parseReturn(byte[] bArr);
    }

    public interface View extends BaseView {
        Bundle getIArguments();

        void onFail(BaseParam baseParam, int i, String str);

        void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam);
    }
}
