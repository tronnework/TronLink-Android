package com.tron.wallet.business.walletmanager.backup;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface BackupContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void onBackupFinish();
    }

    public interface View extends BaseView {
    }
}
