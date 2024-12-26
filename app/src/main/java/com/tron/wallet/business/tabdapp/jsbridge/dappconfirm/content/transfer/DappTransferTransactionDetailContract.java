package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface DappTransferTransactionDetailContract {

    public interface Model extends BaseModel {
        void getAccount(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getAccount(String str);
    }

    public interface View extends BaseView {
        void bindDataToResourceUI(int i);
    }
}
