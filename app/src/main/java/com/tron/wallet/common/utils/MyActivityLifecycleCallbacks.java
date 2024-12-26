package com.tron.wallet.common.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;
public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public static int sAnimationId;
    private List<Activity> activities = new LinkedList();

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        addActivity(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }

    public void addActivity(Activity activity) {
        if (this.activities == null) {
            this.activities = new LinkedList();
        }
        if (this.activities.contains(activity)) {
            return;
        }
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        List<Activity> list = this.activities;
        if (list != null) {
            if (list.contains(activity)) {
                this.activities.remove(activity);
            }
            if (this.activities.size() == 0) {
                this.activities = null;
            }
        }
    }

    public void removeAllActivities() {
        for (Activity activity : this.activities) {
            if (activity != null) {
                activity.finish();
                activity.overridePendingTransition(0, sAnimationId);
            }
        }
    }
}
