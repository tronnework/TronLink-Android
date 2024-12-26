package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.two;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface DappApproveConfirmTwoContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
    }

    public interface View extends BaseView {
        void switchToOne();
    }
}
