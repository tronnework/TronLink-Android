package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.journeyapps.barcodescanner.camera.AutoFocusManager;
import java.util.ArrayList;
import java.util.Collection;
public final class AutoFocusManager {
    private static final long AUTO_FOCUS_INTERVAL_MS = 2000;
    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private static final String TAG = "AutoFocusManager";
    private int MESSAGE_FOCUS = 1;
    private final Camera.AutoFocusCallback autoFocusCallback;
    private final Camera camera;
    private final Handler.Callback focusHandlerCallback;
    private boolean focusing;
    private Handler handler;
    private boolean stopped;
    private final boolean useAutoFocus;

    static {
        ArrayList arrayList = new ArrayList(2);
        FOCUS_MODES_CALLING_AF = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }

    public class fun2 implements Camera.AutoFocusCallback {
        fun2() {
        }

        @Override
        public void onAutoFocus(boolean z, Camera camera) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    AutoFocusManager.2.this.lambda$onAutoFocus$0$com-journeyapps-barcodescanner-camera-AutoFocusManager$2();
                }
            });
        }

        public void lambda$onAutoFocus$0$com-journeyapps-barcodescanner-camera-AutoFocusManager$2() {
            focusing = false;
            autoFocusAgainLater();
        }
    }

    public AutoFocusManager(Camera camera, CameraSettings cameraSettings) {
        boolean z = true;
        Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == MESSAGE_FOCUS) {
                    focus();
                    return true;
                }
                return false;
            }
        };
        this.focusHandlerCallback = callback;
        this.autoFocusCallback = new fun2();
        this.handler = new Handler(callback);
        this.camera = camera;
        String focusMode = camera.getParameters().getFocusMode();
        z = (cameraSettings.isAutoFocusEnabled() && FOCUS_MODES_CALLING_AF.contains(focusMode)) ? false : false;
        this.useAutoFocus = z;
        String str = TAG;
        Log.i(str, "Current focus mode '" + focusMode + "'; use auto focus? " + z);
        start();
    }

    public synchronized void autoFocusAgainLater() {
        if (!this.stopped && !this.handler.hasMessages(this.MESSAGE_FOCUS)) {
            Handler handler = this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(this.MESSAGE_FOCUS), AUTO_FOCUS_INTERVAL_MS);
        }
    }

    public void start() {
        this.stopped = false;
        focus();
    }

    public void focus() {
        if (!this.useAutoFocus || this.stopped || this.focusing) {
            return;
        }
        try {
            this.camera.autoFocus(this.autoFocusCallback);
            this.focusing = true;
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected exception while focusing", e);
            autoFocusAgainLater();
        }
    }

    private void cancelOutstandingTask() {
        this.handler.removeMessages(this.MESSAGE_FOCUS);
    }

    public void stop() {
        this.stopped = true;
        this.focusing = false;
        cancelOutstandingTask();
        if (this.useAutoFocus) {
            try {
                this.camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
