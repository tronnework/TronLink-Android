package com.tron.wallet.business.transfer;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import io.reactivex.Observable;
public interface TransactionDetailContract {

    public interface Model extends BaseModel {
        Observable<TransactionInfoBean> getTransactionInfo(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addSome();

        public abstract void getFee(String str);

        protected abstract void handleScam(TransactionInfoBean transactionInfoBean);
    }

    public interface View extends BaseView {
        void updateContent(TransactionInfoBean transactionInfoBean);

        void updateDefaultView(TransactionInfoBean transactionInfoBean);

        void updateScam(int i);

        void updateTokenScamFlag();
    }
}
