package com.tron.wallet.business.ledger.manage;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.interfaces.CloseClickListener;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tronlinkpro.wallet.R;
public class EquipmentHelper {
    private EquipmentHelper() {
    }

    public static EquipmentHelper get() {
        return Nested.instance;
    }

    public void showRemoveDialog(Context context, View view, int i, BleRepoDevice bleRepoDevice, EquipmentRemoveListener equipmentRemoveListener) {
        PopupWindowUtil.showRemovePop(context, i, bleRepoDevice, equipmentRemoveListener).showAtLocation(view, 80, 0, 0);
    }

    public void removeDevice(Context context, final int i, final BleRepoDevice bleRepoDevice, final EquipmentRemoveListener equipmentRemoveListener) {
        ConfirmCustomPopupView.getBuilder(context).setTitle(context.getResources().getString(R.string.remove_device_title)).setInfo(context.getResources().getString(R.string.remove_device_tip)).setConfirmText(context.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                EquipmentRemoveListener.this.remove(i, bleRepoDevice);
            }
        }).build().show();
    }

    public void showConnectDeviceFailed(Context context, View view, int i, BleRepoDevice bleRepoDevice, EquipmentFailListener equipmentFailListener) {
        PopupWindowUtil.showFailPop(context, i, bleRepoDevice, equipmentFailListener).showAtLocation(view, 80, 0, 0);
    }

    public PopupWindow showLoadingDialog(Context context, View view, String str, LedgerProgressView.STATUS status) {
        PopupWindow showLedgerLoadingPop = PopupWindowUtil.showLedgerLoadingPop(context, str, status, null);
        showLedgerLoadingPop.showAtLocation(view, 80, 0, 0);
        return showLedgerLoadingPop;
    }

    public PopupWindow showLoadingDialog(Context context, View view, String str, LedgerProgressView.STATUS status, CloseClickListener closeClickListener) {
        PopupWindow showLedgerLoadingPop = PopupWindowUtil.showLedgerLoadingPop(context, str, status, closeClickListener);
        showLedgerLoadingPop.showAtLocation(view, 80, 0, 0);
        return showLedgerLoadingPop;
    }

    private static class Nested {
        static EquipmentHelper instance = new EquipmentHelper();

        private Nested() {
        }
    }
}
