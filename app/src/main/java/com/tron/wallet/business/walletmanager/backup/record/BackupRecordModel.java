package com.tron.wallet.business.walletmanager.backup.record;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordContract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.List;
public class BackupRecordModel implements BackupRecordContract.Model {
    BackupHistoryManager backupHistoryManager = BackupHistoryManager.getInstance();

    @Override
    public Observable<List<BackupRecordBean>> getBackupRecords() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$getBackupRecords$0(observableEmitter);
            }
        }).compose(RxSchedulers.io_main());
    }

    public void lambda$getBackupRecords$0(ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(this.backupHistoryManager.queryHistory());
        observableEmitter.onComplete();
    }
}
