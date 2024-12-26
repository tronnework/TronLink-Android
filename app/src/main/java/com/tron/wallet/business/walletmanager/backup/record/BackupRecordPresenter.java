package com.tron.wallet.business.walletmanager.backup.record;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordContract;
import io.reactivex.functions.Consumer;
import java.util.List;
public class BackupRecordPresenter extends BackupRecordContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getBackupRecords() {
        ((BackupRecordContract.Model) this.mModel).getBackupRecords().subscribe(new Consumer<List<BackupRecordBean>>() {
            @Override
            public void accept(List<BackupRecordBean> list) throws Exception {
                LogUtils.e("getBackupRecords", list.toString());
                ((BackupRecordContract.View) mView).dismissLoading();
                if (list != null && list.size() > 0) {
                    ((BackupRecordContract.View) mView).updateUI(list);
                } else {
                    ((BackupRecordContract.View) mView).showNoDataView();
                }
            }
        });
    }
}
