package com.tron.wallet.update.location;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.ChannelUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class UpdateHelper {
    ConfirmCustomPopupView mDialog;

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onPermissionResult(int i, String[] strArr, int[] iArr) {
    }

    public void showUpdateDialog(final Context context, View.OnClickListener onClickListener, XPopupCallback xPopupCallback) {
        if (this.mDialog == null) {
            this.mDialog = ConfirmCustomPopupView.getBuilder(context).setBtnStyle(2).setTitle(TronConfig.updateOutput.data.title).setInfo(TronConfig.updateOutput.data.strategy).setInfoGravity(3).setConfirmText(StringTronUtil.getResString(R.string.update_immediately)).setCancelText(StringTronUtil.getResString(R.string.say_next_time)).setAutoDismissOutSide(false).setConfirmListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$showUpdateDialog$0(context, view);
                }
            }).setPopupCallback(xPopupCallback).build();
        }
        this.mDialog.show();
    }

    public void lambda$showUpdateDialog$0(Context context, View view) {
        getApkUrlStrategy(context);
    }

    private void getApkUrlStrategy(Context context) {
        ChannelUtils.toMarket(context);
        SpAPI.THIS.setLastestClick(TronConfig.updateOutput.data.version, true);
        new RxManager().post(Event.DD, "1111");
        if (TronConfig.updateOutput.data.force) {
            return;
        }
        this.mDialog.dismiss();
    }
}
