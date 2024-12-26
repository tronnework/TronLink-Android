package com.tron.wallet.business.walletmanager.backup;

import android.content.Context;
import android.graphics.Bitmap;
import com.tron.wallet.business.walletmanager.backup.BackupContract;
import com.tron.wallet.common.components.qr.CodeUtils;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.UIUtils;
public class BackPresenter extends BackupContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void onBackupFinish() {
        ((BackupContract.View) this.mView).exit();
        this.mRxManager.post(Event.BACKUP, "111");
    }

    public Bitmap createQrBitmap(Context context, String str) {
        return CodeUtils.createQRCode(str, UIUtils.dip2px(180.0f));
    }
}
