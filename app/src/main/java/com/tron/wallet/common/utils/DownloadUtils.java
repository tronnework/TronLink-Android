package com.tron.wallet.common.utils;

import android.os.Handler;
import android.os.Looper;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.tron.wallet.common.utils.DownloadUtils;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
public class DownloadUtils {
    private static DownloadUtils instance;
    private Call downloadCall;
    private volatile int identifyId;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private OkHttpClient okHttpClient = ProgressManager.getInstance().with(new OkHttpClient.Builder()).build();

    public interface OnDownloadListener {
        void onDownloadCanceled();

        void onDownloadFailed();

        void onDownloadSuccess();

        void onDownloading(ProgressInfo progressInfo);
    }

    public boolean isDownloadCallEmpty() {
        return this.downloadCall == null;
    }

    private DownloadUtils() {
    }

    public static DownloadUtils getInstance() {
        if (instance == null) {
            synchronized (DownloadUtils.class) {
                if (instance == null) {
                    instance = new DownloadUtils();
                }
            }
        }
        return instance;
    }

    public void stop() {
        this.identifyId++;
        Call call = this.downloadCall;
        if (call == null || call.isCanceled()) {
            return;
        }
        this.downloadCall.cancel();
        this.downloadCall = null;
    }

    public void download(String str, String str2, String str3, OnDownloadListener onDownloadListener) {
        this.identifyId++;
        Call newCall = this.okHttpClient.newCall(new Request.Builder().url(str.trim()).build());
        this.downloadCall = newCall;
        FirebasePerfOkHttpClient.enqueue(newCall, new fun1(onDownloadListener, str2, str3));
    }

    class fun1 implements Callback {
        private int id;
        final OnDownloadListener val$listener;
        final String val$saveDir;
        final String val$saveName;

        fun1(OnDownloadListener onDownloadListener, String str, String str2) {
            this.val$listener = onDownloadListener;
            this.val$saveDir = str;
            this.val$saveName = str2;
            this.id = identifyId;
        }

        @Override
        public void onFailure(Call call, IOException iOException) {
            if (this.id != identifyId) {
                return;
            }
            if (iOException.toString().contains("closed")) {
                Handler handler = mHandler;
                final OnDownloadListener onDownloadListener = this.val$listener;
                handler.post(new Runnable() {
                    @Override
                    public final void run() {
                        DownloadUtils.OnDownloadListener.this.onDownloadCanceled();
                    }
                });
                return;
            }
            Handler handler2 = mHandler;
            final OnDownloadListener onDownloadListener2 = this.val$listener;
            handler2.post(new Runnable() {
                @Override
                public final void run() {
                    DownloadUtils.OnDownloadListener.this.onDownloadFailed();
                }
            });
        }

        @Override
        public void onResponse(okhttp3.Call r10, okhttp3.Response r11) throws java.io.IOException {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.DownloadUtils.1.onResponse(okhttp3.Call, okhttp3.Response):void");
        }
    }

    public static class ProgressInfo {
        private long contentLength;
        private long currentBytes;
        private boolean finish;
        private long intervalBytes;
        private long intervalTime;

        public long getContentLength() {
            return this.contentLength;
        }

        public long getCurrentBytes() {
            return this.currentBytes;
        }

        public long getIntervalBytes() {
            return this.intervalBytes;
        }

        public long getIntervalTime() {
            return this.intervalTime;
        }

        public boolean isFinish() {
            return this.finish;
        }

        public void setContentLength(long j) {
            this.contentLength = j;
        }

        public void setCurrentBytes(long j) {
            this.currentBytes = j;
        }

        public void setFinish(boolean z) {
            this.finish = z;
        }

        public void setIntervalBytes(long j) {
            this.intervalBytes = j;
        }

        public void setIntervalTime(long j) {
            this.intervalTime = j;
        }

        public int getPercent() {
            if (getCurrentBytes() <= 0 || getContentLength() <= 0) {
                return 0;
            }
            return (int) ((getCurrentBytes() * 100) / getContentLength());
        }
    }
}
