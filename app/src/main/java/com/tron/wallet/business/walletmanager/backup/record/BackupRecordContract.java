package com.tron.wallet.business.walletmanager.backup.record;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import io.reactivex.Observable;
import java.util.List;
public interface BackupRecordContract {

    public interface Model extends BaseModel {
        Observable<List<BackupRecordBean>> getBackupRecords();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getBackupRecords();
    }

    public interface View extends BaseView {
        void showNoDataView();

        void updateUI(List<BackupRecordBean> list);
    }
}
