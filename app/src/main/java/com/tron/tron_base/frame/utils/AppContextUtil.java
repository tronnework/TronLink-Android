package com.tron.tron_base.frame.utils;

import android.app.Application;
import android.content.Context;
public class AppContextUtil {
    private static Application mApplication;
    private static Context mContext;
    public static boolean mIsGlobal;
    public static boolean mIsGooGlePlay;

    public static void init(Context context, Application application, boolean z, boolean z2) {
        mContext = context;
        mIsGooGlePlay = z;
        mIsGlobal = z2;
        if (application != null) {
            mApplication = application;
        }
    }

    public static Context getContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }
        throw new IllegalArgumentException("need init");
    }

    public static Application getmApplication() {
        Application application = mApplication;
        if (application != null) {
            return application;
        }
        throw new IllegalArgumentException("need init");
    }
}
