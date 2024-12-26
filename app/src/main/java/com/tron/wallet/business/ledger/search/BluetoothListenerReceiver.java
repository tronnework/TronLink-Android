package com.tron.wallet.business.ledger.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
public class BluetoothListenerReceiver extends BroadcastReceiver {
    private static final String TAG = "BleSearchLedgerPresenter";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d(TAG, "blueState: onReceive");
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
            switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
                case 10:
                    LogUtils.d(TAG, "blueState: STATE_OFF");
                    new RxManager().post(Event.BlueToothOff, "");
                    return;
                case 11:
                    LogUtils.d(TAG, "blueState: STATE_TURNING_ON");
                    return;
                case 12:
                    LogUtils.d(TAG, "blueState: STATE_ON");
                    return;
                case 13:
                    LogUtils.d(TAG, "blueState: STATE_TURNING_OFF");
                    return;
                default:
                    return;
            }
        }
    }
}
