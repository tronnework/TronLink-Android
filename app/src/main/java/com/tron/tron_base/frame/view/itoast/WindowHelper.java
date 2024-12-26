package com.tron.tron_base.frame.view.itoast;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.WindowManager;
public final class WindowHelper implements Application.ActivityLifecycleCallbacks {
    private final ArrayMap<String, Activity> mActivitySet = new ArrayMap<>();
    private String mCurrentTag;
    private final ToastHelper mToastHelper;

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    private WindowHelper(ToastHelper toastHelper) {
        this.mToastHelper = toastHelper;
    }

    public static WindowHelper register(ToastHelper toastHelper, Application application) {
        WindowHelper windowHelper = new WindowHelper(toastHelper);
        application.registerActivityLifecycleCallbacks(windowHelper);
        return windowHelper;
    }

    public WindowManager getWindowManager() throws NullPointerException {
        Activity activity;
        String str = this.mCurrentTag;
        if (str != null && (activity = this.mActivitySet.get(str)) != null) {
            return getWindowManagerObject(activity);
        }
        throw null;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        String objectTag = getObjectTag(activity);
        this.mCurrentTag = objectTag;
        this.mActivitySet.put(objectTag, activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        this.mCurrentTag = getObjectTag(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        this.mCurrentTag = getObjectTag(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        this.mToastHelper.cancel();
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        this.mActivitySet.remove(getObjectTag(activity));
        if (getObjectTag(activity).equals(this.mCurrentTag)) {
            this.mCurrentTag = null;
        }
    }

    private static String getObjectTag(Object obj) {
        return obj.getClass().getName() + Integer.toHexString(obj.hashCode());
    }

    private static WindowManager getWindowManagerObject(Activity activity) {
        return (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
    }
}
