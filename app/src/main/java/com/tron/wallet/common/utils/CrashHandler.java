package com.tron.wallet.common.utils;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private static CrashHandler mCrashHandler;
    private static Toast mCustomToast;
    private boolean hasToast;
    public Application mApplication;
    private Class mClassOfFirstActivity;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private boolean mIsDebug;
    private boolean mIsRestartApp;
    private long mRestartTime;
    MyActivityLifecycleCallbacks mMyActivityLifecycleCallbacks = new MyActivityLifecycleCallbacks();
    private Map<String, String> infos = new HashMap();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    public static void setCustomToast(Toast toast) {
        mCustomToast = toast;
    }

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            mCrashHandler = new CrashHandler();
        }
        return mCrashHandler;
    }

    public static void setCloseAnimation(int i) {
        MyActivityLifecycleCallbacks.sAnimationId = i;
    }

    public void init(Application application, boolean z, boolean z2, long j, Class cls) {
        this.mIsRestartApp = z2;
        this.mRestartTime = j;
        this.mClassOfFirstActivity = cls;
        initCrashHandler(application, z);
    }

    public void init(Application application, boolean z) {
        initCrashHandler(application, z);
    }

    private void initCrashHandler(Application application, boolean z) {
        this.mIsDebug = z;
        this.mApplication = application;
        application.registerActivityLifecycleCallbacks(this.mMyActivityLifecycleCallbacks);
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(th) && (uncaughtExceptionHandler = this.mDefaultHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(2800L);
        } catch (InterruptedException e) {
            LogUtils.e(TAG, "uncaughtException() InterruptedException:" + e);
        }
        if (this.mIsRestartApp) {
            AlarmManager alarmManager = (AlarmManager) this.mApplication.getSystemService(NotificationCompat.CATEGORY_ALARM);
            try {
                Intent intent = new Intent(this.mApplication, this.mClassOfFirstActivity);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                alarmManager.set(1, System.currentTimeMillis() + this.mRestartTime, PendingIntent.getActivity(this.mApplication, 0, intent, 67108864));
            } catch (Exception e2) {
                LogUtils.e(TAG, "first class error:" + e2);
            }
        }
        this.mMyActivityLifecycleCallbacks.removeAllActivities();
        Process.killProcess(Process.myPid());
        System.exit(1);
        System.gc();
    }

    private boolean handleException(Throwable th) {
        if (!this.hasToast) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Looper.prepare();
                        if (CrashHandler.mCustomToast != null) {
                            Toast unused = CrashHandler.mCustomToast;
                        }
                        Looper.loop();
                        hasToast = true;
                    } catch (Exception e) {
                        LogUtils.e(CrashHandler.TAG, "handleException Toast error" + e);
                    }
                }
            }).start();
        }
        if (th == null) {
            return false;
        }
        if (this.mIsDebug) {
            collectDeviceInfo();
            saveCatchInfo2File(th);
            return true;
        }
        return true;
    }

    public void collectDeviceInfo() {
        Field[] declaredFields;
        try {
            PackageInfo packageInfo = this.mApplication.getPackageManager().getPackageInfo(this.mApplication.getPackageName(), 1);
            if (packageInfo != null) {
                this.infos.put("versionName", packageInfo.versionName == null ? "null" : packageInfo.versionName);
                this.infos.put("versionCode", packageInfo.versionCode + "");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtils.e(TAG, "collectDeviceInfo() an error occured when collect package info NameNotFoundException:");
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
                LogUtils.i(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception unused2) {
                LogUtils.e(TAG, "collectDeviceInfo() an error occured when collect crash info Exception:");
            }
        }
    }

    private java.lang.String saveCatchInfo2File(java.lang.Throwable r9) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.CrashHandler.saveCatchInfo2File(java.lang.Throwable):java.lang.String");
    }

    private void LogcatCrashInfo(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        IOException e;
        BufferedReader bufferedReader;
        FileNotFoundException e2;
        if (!new File((String) str).exists()) {
            LogUtils.e(TAG, "LogcatCrashInfo() log file does not exist");
            return;
        }
        try {
            try {
                try {
                    fileInputStream = new FileInputStream((String) str);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e3) {
                fileInputStream = null;
                e2 = e3;
                bufferedReader = null;
            } catch (IOException e4) {
                fileInputStream = null;
                e = e4;
                bufferedReader = null;
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                str = 0;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            bufferedReader.close();
                            fileInputStream.close();
                            return;
                        }
                        LogUtils.e(TAG, readLine);
                    } catch (FileNotFoundException e5) {
                        e2 = e5;
                        LogUtils.e(e2);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                            return;
                        }
                        return;
                    } catch (IOException e6) {
                        e = e6;
                        LogUtils.e(e);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                            return;
                        }
                        return;
                    }
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                bufferedReader = null;
            } catch (IOException e8) {
                e = e8;
                bufferedReader = null;
            } catch (Throwable th4) {
                th = th4;
                str = 0;
                if (str != 0) {
                    try {
                        str.close();
                    } catch (IOException e9) {
                        LogUtils.e(e9);
                        throw th;
                    }
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (IOException e10) {
            LogUtils.e(e10);
        }
    }

    public String getCrashInfo(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.setStackTrace(th.getStackTrace());
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
}
