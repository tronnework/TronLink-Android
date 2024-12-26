package com.tron.wallet.business.tabmy.myhome.settings;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeBean;
public interface NodeSpeedContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void initSwitchDialog(int i, NodeBean nodeBean);
    }

    public interface View extends BaseView {
        void dismisLoadingDialog();

        @Override
        void showLoadingDialog();

        void switchToNode(NodeBean nodeBean);
    }
}
