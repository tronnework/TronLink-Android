package com.journeyapps.barcodescanner;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;
public class RotationListener {
    private RotationCallback callback;
    private int lastRotation;
    private OrientationEventListener orientationEventListener;
    private WindowManager windowManager;

    public void listen(Context context, RotationCallback rotationCallback) {
        stop();
        Context applicationContext = context.getApplicationContext();
        this.callback = rotationCallback;
        this.windowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        OrientationEventListener orientationEventListener = new OrientationEventListener(applicationContext, 3) {
            @Override
            public void onOrientationChanged(int i) {
                int rotation;
                WindowManager windowManager = windowManager;
                RotationCallback rotationCallback2 = callback;
                if (windowManager == null || rotationCallback2 == null || (rotation = windowManager.getDefaultDisplay().getRotation()) == lastRotation) {
                    return;
                }
                lastRotation = rotation;
                rotationCallback2.onRotationChanged(rotation);
            }
        };
        this.orientationEventListener = orientationEventListener;
        orientationEventListener.enable();
        this.lastRotation = this.windowManager.getDefaultDisplay().getRotation();
    }

    public void stop() {
        OrientationEventListener orientationEventListener = this.orientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        this.orientationEventListener = null;
        this.windowManager = null;
        this.callback = null;
    }
}
