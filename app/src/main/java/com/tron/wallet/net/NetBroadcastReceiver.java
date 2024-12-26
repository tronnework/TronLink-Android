package com.tron.wallet.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
public class NetBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            addNetWorkState(context);
        }
    }

    public void addNetWorkState(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.getType() == 1) {
                    TronConfig.hasNet = true;
                    getHostIp();
                } else if (activeNetworkInfo.getType() == 0) {
                    TronConfig.hasNet = true;
                    getHostIp();
                } else if (activeNetworkInfo.getType() == 9) {
                    getHostIp();
                }
            } else {
                TronConfig.hasNet = false;
            }
            RxBus.getInstance().post(Event.NET_CHANGE, "");
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void getHostIp() {
        checkLocationChanged();
    }

    private synchronized void checkLocationChanged() {
        RxManager rxManager = new RxManager();
        LogUtils.d("NetBroadcastReceiver", "rxManager.post country:  ");
        rxManager.post(Event.IP_LOCATION_CHANGED, "");
    }
}
