package com.tron.wallet.common.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import java.util.HashMap;
import java.util.Map;
public class TaskHandler {
    private static final String LOCK = "LOCK";
    private static final int MSG_CLEARPRIMARYCLIP = 1;
    public static final String TAG = "TaskHandler";
    private static final String UNLOCK = "UNLOCK";
    private static TaskHandler mTaskHandler;
    private Map<String, String> eventMaps = new HashMap();
    private Handler mDefaultHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            if (message.what == 1) {
                try {
                    LogUtils.i("MSG_CLEARPRIMARYCLIP", "MSG_CLEARPRIMARYCLIP");
                    ClipboardManager clipboardManager = (ClipboardManager) AppContextUtil.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    if (Build.VERSION.SDK_INT >= 28) {
                        clipboardManager.clearPrimaryClip();
                    } else {
                        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, null));
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            super.handleMessage(message);
        }
    };

    private TaskHandler() {
    }

    public static TaskHandler getInstance() {
        if (mTaskHandler == null) {
            mTaskHandler = new TaskHandler();
        }
        return mTaskHandler;
    }

    public void postClearPrimaryClipMessage() {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1;
            if (this.mDefaultHandler.hasMessages(obtain.what)) {
                this.mDefaultHandler.removeMessages(obtain.what);
            }
            this.mDefaultHandler.sendMessageDelayed(obtain, 60000L);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void exit() {
        Handler handler = this.mDefaultHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
