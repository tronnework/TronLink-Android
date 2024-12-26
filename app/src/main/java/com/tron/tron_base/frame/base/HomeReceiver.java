package com.tron.tron_base.frame.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class HomeReceiver extends BroadcastReceiver {
    private Activity activity;
    final String SYSTEM_DIALOG_REASON_KEY = "reason";
    final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    final String SYSTEM_DIALOG_REASON_RECENT_APPS_GESTURE = "fs_gesture";
    final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        Activity activity;
        if (!"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) || (stringExtra = intent.getStringExtra("reason")) == null || this.activity == null) {
            return;
        }
        if (stringExtra.equals("homekey")) {
            this.activity.getWindow().setFlags(8192, 8192);
        } else if ((!stringExtra.equals("recentapps") && !stringExtra.equals("fs_gesture")) || (activity = this.activity) == null || activity.getWindow() == null) {
        } else {
            this.activity.getWindow().setFlags(8192, 8192);
        }
    }
}
