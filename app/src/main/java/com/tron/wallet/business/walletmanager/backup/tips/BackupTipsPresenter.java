package com.tron.wallet.business.walletmanager.backup.tips;

import com.tron.wallet.business.walletmanager.backup.tips.BackupTipsContract;
import com.tron.wallet.common.config.Event;
import io.reactivex.functions.Consumer;
public class BackupTipsPresenter extends BackupTipsContract.Presenter {
    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BACKUP, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((BackupTipsContract.View) this.mView).exit();
    }
}
