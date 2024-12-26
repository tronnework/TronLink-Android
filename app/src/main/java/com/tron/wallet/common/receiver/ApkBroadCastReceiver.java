package com.tron.wallet.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.config.AppConstant;
import com.tron.wallet.common.utils.FileUtils;
public class ApkBroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "ApkBroadCastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
            LogUtils.d(TAG, " Add listening to system broadcast");
        }
        if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            LogUtils.d(TAG, " System broadcast removal detected");
        }
        if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction())) {
            LogUtils.d(TAG, " System broadcast replacement detected");
            if (TextUtils.isEmpty(AppConstant.APK_FILE_NAME)) {
                return;
            }
            FileUtils.rmoveFile(AppConstant.APK_FILE_PATH + AppConstant.APK_FILE_NAME);
        }
    }
}
