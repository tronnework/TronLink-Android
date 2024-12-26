package com.tron.wallet.business.walletmanager.backup.tips;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface BackupTipsContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
    }

    public interface View extends BaseView {
    }
}
