package com.tron.wallet.common.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.arasthel.asyncjob.AsyncJob;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.user.DownInfo;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
public class DownOfHttpUtil {
    private static final String TAG = "DownOfHttpUtil";
    private downLoadCallBack callBack;
    private boolean isUseBreak = false;

    public interface downLoadCallBack {
        void LoadFail();

        void LoadSuccess(String str, String str2);

        void showProgress(int i);
    }

    public void getDownFileLocalPath(final String str, final String str2, String str3, boolean z, final downLoadCallBack downloadcallback) {
        this.isUseBreak = z;
        final String localPath = getLocalPath(str3);
        if (!TextUtils.isEmpty(str)) {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    getFileSize(str, localPath, str2, downloadcallback);
                }
            });
        } else {
            downloadcallback.LoadFail();
        }
    }

    public void getFileSize(String str, String str2, String str3, downLoadCallBack downloadcallback) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.GET);
            httpURLConnection.disconnect();
            String absolutePath = AppContextUtil.getContext().getFilesDir().getAbsolutePath();
            File file = new File(absolutePath + str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            checkDownType(-1L, str, str2, str3, downloadcallback);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private String getLocalPath(String str) {
        PackageInfo packageInfo;
        if (TextUtils.isEmpty(str)) {
            try {
                packageInfo = AppContextUtil.getContext().getPackageManager().getPackageInfo(AppContextUtil.getContext().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                LogUtils.e(e);
                packageInfo = null;
            }
            String str2 = packageInfo != null ? packageInfo.versionName : "";
            return "toon/file/" + str2;
        }
        return str;
    }

    public void checkDownType(long j, String str, String str2, String str3, downLoadCallBack downloadcallback) {
        DownInfo downInfo = new DownInfo();
        downInfo.setUrl(str);
        downInfo.setLength(j);
        downInfo.setStart(0L);
        downInfo.setNow(0L);
        downAndInputFile(downInfo, j, str2, str3, downloadcallback);
    }

    private void downAndInputFile(com.tron.wallet.common.bean.user.DownInfo r17, long r18, java.lang.String r20, java.lang.String r21, com.tron.wallet.common.utils.DownOfHttpUtil.downLoadCallBack r22) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.DownOfHttpUtil.downAndInputFile(com.tron.wallet.common.bean.user.DownInfo, long, java.lang.String, java.lang.String, com.tron.wallet.common.utils.DownOfHttpUtil$downLoadCallBack):void");
    }

    public void deleted(String str) {
        try {
            String absolutePath = AppContextUtil.getContext().getFilesDir().getAbsolutePath();
            File file = new File(absolutePath + str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        } catch (Exception unused) {
        }
    }

    private boolean deleteDir(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
