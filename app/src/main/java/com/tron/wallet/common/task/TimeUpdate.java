package com.tron.wallet.common.task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.utils.rb.RbUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TimeUpdate {
    private static Context mContext;
    private static ExecutorService mExecutorService;
    private static long mInterval;
    private static TimeUpdaterRunnable mPriceUpdaterRunnable;
    private static boolean mRunning;
    private static Price mTRX_price;
    private static Handler mTaskHandler;

    public static boolean isInitialized() {
        return mContext != null;
    }

    public static boolean isRunning() {
        return mRunning;
    }

    TimeUpdate() {
    }

    public static void init(Context context, long j) {
        if (mContext == null) {
            mContext = context;
            mInterval = j;
            mRunning = false;
            mTaskHandler = new Handler(Looper.getMainLooper());
            mPriceUpdaterRunnable = new TimeUpdaterRunnable();
            mExecutorService = Executors.newSingleThreadExecutor();
        }
    }

    public static void start() {
        stop();
        mRunning = true;
        mTaskHandler.post(mPriceUpdaterRunnable);
    }

    public static void startDelayed(long j) {
        stop();
        mRunning = true;
        mTaskHandler.postDelayed(mPriceUpdaterRunnable, j);
    }

    public static void stop() {
        mRunning = false;
        mTaskHandler.removeCallbacks(mPriceUpdaterRunnable);
    }

    public static void setInterval(long j, boolean z) {
        mInterval = j;
        if (z) {
            start();
        }
    }

    public static void singleShot(long j) {
        if (j <= 0) {
            mTaskHandler.post(mPriceUpdaterRunnable);
        } else {
            mTaskHandler.postDelayed(mPriceUpdaterRunnable, j);
        }
    }

    public static Price getTRX_price() {
        Price price = mTRX_price;
        return price != null ? price : new Price();
    }

    public static class TimeUpdaterRunnable implements Runnable {
        private TimeUpdaterRunnable() {
        }

        @Override
        public void run() {
            RbUtil.THIS.sendTime();
            if (TimeUpdate.mRunning) {
                TimeUpdate.mTaskHandler.removeCallbacks(TimeUpdate.mPriceUpdaterRunnable);
                TimeUpdate.mTaskHandler.postDelayed(TimeUpdate.mPriceUpdaterRunnable, TimeUpdate.mInterval);
            }
        }
    }
}
