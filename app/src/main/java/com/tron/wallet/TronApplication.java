package com.tron.wallet;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.webkit.WebView;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.tencent.bugly.crashreport.CrashReport;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.tron_base.frame.view.itoast.ToastUtils;
import com.tron.tron_base.frame.view.itoast.style.ToastBlackStyle;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.message.db.TransactionMessageManager;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.components.frescoimage.BigImageViewer;
import com.tron.wallet.common.components.frescoimage.FrescoImageLoader;
import com.tron.wallet.common.config.AppConstant;
import com.tron.wallet.common.config.ImageLoaderConfig;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.receiver.ApkBroadCastReceiver;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.task.TimeUpdate;
import com.tron.wallet.common.utils.AppUtils;
import com.tron.wallet.common.utils.AutoSelectServer;
import com.tron.wallet.common.utils.ChannelUtils;
import com.tron.wallet.common.utils.CrashHandler;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.WebSocketManager;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import java.io.IOException;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
public class TronApplication extends MultiDexApplication {
    public LinkedList<Activity> activityLinkedList = new LinkedList<>();
    Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityPaused(Activity activity) {
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
        public void onActivityPreCreated(Activity activity, Bundle bundle) {
            try {
                LanguageUtils.setLocal(activity);
            } catch (Exception e) {
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
            activity.getTheme().applyStyle(com.tronlinkpro.wallet.R.style.CustomFont, true);
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            LogUtils.d("TronApplication", "Created   " + activity.getClass().getSimpleName());
            activityLinkedList.add(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            currentActivity = activity;
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogUtils.d("TronApplication", "Destroyed:  " + activity.getClass().getSimpleName());
            activityLinkedList.remove(activity);
        }
    };
    public Activity currentActivity;
    public static HashMap<String, String> WALlET_AES = new HashMap<>();
    public static HashMap<String, String> FUNCTIONSELECTOR_MAP = new HashMap<>();
    public static boolean sdStorageChecked = false;

    public void initSentry() {
    }

    public void destroyStakeHomeActivity() {
        for (int i = 0; i < this.activityLinkedList.size(); i++) {
            Activity activity = this.activityLinkedList.get(i);
            if (activity.getClass().getSimpleName().equals("StakeHomeActivity")) {
                LogUtils.d("TronApplication", "destroyStakeHomeActivity ");
                activity.finish();
            }
        }
    }

    public boolean isResourceHomeIn() {
        LogUtils.d("TronApplication", "isResourceHomeIn");
        boolean z = false;
        for (int i = 0; i < this.activityLinkedList.size(); i++) {
            if (this.activityLinkedList.get(i).getClass().getSimpleName().equals("ResourceManagementV2Activity")) {
                LogUtils.d("TronApplication", "isResourceHomeIn  isExist = true");
                z = true;
            }
        }
        return z;
    }

    @Override
    public void attachBaseContext(Context context) {
        MultiDex.install(this);
        super.attachBaseContext(LanguageUtils.setLocal(context));
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LanguageUtils.onConfigurationChanged(getApplicationContext());
    }

    @Override
    public void onCreate() {
        String str;
        super.onCreate();
        RxJavaPlugins.setErrorHandler(new Consumer() {
            @Override
            public final void accept(Object obj) {
                TronApplication.lambda$onCreate$0((Throwable) obj);
            }
        });
        try {
            ToastUtils.init(this, new ToastBlackStyle(this));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        AppContextUtil.init(getApplicationContext(), this, true, false);
        LanguageUtils.onConfigurationChanged(getApplicationContext());
        try {
            OkHttpManager.getInstance().setTrustCertificates(getAssets().open(IRequest.getCrt()));
        } catch (IOException e2) {
            SentryUtil.captureException(e2);
            LogUtils.e(e2);
        }
        Fresco.initialize(this, ImageLoaderConfig.getImagePipelineConfig(this));
        BigImageViewer.initialize(FrescoImageLoader.with(getApplicationContext()));
        AccountUpdater.init(this, TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL);
        PriceUpdater.init(this, TronConfig.PRICE_UPDATE_INTERVAL);
        TimeUpdate.init(this, 1000L);
        registerActivityLifecycleCallbacks(this.callbacks);
        TransactionMessageManager.getInstance().init();
        CrashHandler.getInstance().init(this, false, true, 0L, WelcomeActivity.class);
        if (ChannelUtils.getChannel(this).equals("global")) {
            str = AppConstant.BUGLY_GLOBAL_RELEASE_ID;
        } else {
            str = AppConstant.BUGLY_RELEASE_ID;
        }
        CrashReport.initCrashReport(getApplicationContext(), str, false);
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$onCreate$1();
            }
        });
        Stetho.initializeWithDefaults(this);
        closeAndroidPDialog();
        registerAppInstallReceiver();
        initCrash();
        initWebview();
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                TronApplication.lambda$onCreate$2();
            }
        });
        AssetsConfig.setIsMainChain(SpAPI.THIS.getCurIsMainChain());
        AutoSelectServer.getInstance();
        clearCache();
    }

    public static void lambda$onCreate$0(Throwable th) throws Exception {
        LogUtils.e("RxError", th);
        SentryUtil.captureException(th);
    }

    public void lambda$onCreate$1() {
        ChannelUtils.saveConfigInfo(getApplicationContext());
    }

    public static void lambda$onCreate$2() {
        WebSocketManager.getInstance().start();
        WalletUtils.cleanWalletCache();
    }

    private void registerAppInstallReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(new ApkBroadCastReceiver(), intentFilter, 2);
        } else {
            registerReceiver(new ApkBroadCastReceiver(), intentFilter);
        }
    }

    private void closeAndroidPDialog() {
        try {
            Class.forName("android.content.pm.PackageParser$Package").getDeclaredConstructor(String.class).setAccessible(true);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
            declaredField.setAccessible(true);
            declaredField.setBoolean(invoke, true);
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
    }

    private void initCrash() {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public final void uncaughtException(Thread thread, Throwable th) {
                TronApplication.lambda$initCrash$3(defaultUncaughtExceptionHandler, thread, th);
            }
        });
    }

    public static void lambda$initCrash$3(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Thread thread, Throwable th) {
        if (th instanceof AbstractMethodError) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }

    private void initWebview() {
        try {
            String packageName = AppContextUtil.getContext().getPackageName();
            if (!StringTronUtil.isEmpty(getPackageName())) {
                packageName = getPackageName();
            }
            if (Build.VERSION.SDK_INT >= 28) {
                String processName = getProcessName(this);
                if (packageName.equals(processName)) {
                    return;
                }
                WebView.setDataDirectorySuffix(processName);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public String getProcessName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    private void clearCache() {
        long currentVersion = SpAPI.THIS.getCurrentVersion();
        long versionCode = AppUtils.getVersionCode();
        if (versionCode <= currentVersion) {
            return;
        }
        Fresco.getImagePipeline().clearCaches();
        SpAPI.THIS.setCurrentVersion(versionCode);
    }
}
