package com.tron.wallet.common.task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.Controller.TranscationController;
import com.tron.wallet.db.bean.TranscationBean;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TransferUpdater {
    private static Context mContext;
    private static ExecutorService mExecutorService;
    private static long mInterval;
    private static boolean mRunning;
    private static Handler mTaskHandler;
    private static TransferUpdaterRunnable mTransferUpdaterRunnable;
    private static RxManager rxManager;
    private static List<TranscationBean> transcationBeans;

    public static boolean isInitialized() {
        return mContext != null;
    }

    public static boolean isRunning() {
        return mRunning;
    }

    public static void lambda$init$0(Object obj) throws Exception {
    }

    TransferUpdater() {
    }

    public static void init(Context context, long j) {
        if (mContext == null) {
            mContext = context;
            mInterval = j;
            mRunning = false;
            mTaskHandler = new Handler(Looper.getMainLooper());
            mTransferUpdaterRunnable = new TransferUpdaterRunnable();
            mExecutorService = Executors.newSingleThreadExecutor();
        }
        RxManager rxManager2 = new RxManager();
        rxManager = rxManager2;
        rxManager2.on(Event.TRANSFER_RECORD, new Consumer() {
            @Override
            public final void accept(Object obj) {
                TransferUpdater.lambda$init$0(obj);
            }
        });
        transcationBeans = TranscationController.getInstance(mContext).searchAll();
    }

    public static void start() {
        stop();
        mRunning = true;
        mTaskHandler.post(mTransferUpdaterRunnable);
    }

    public static void startDelayed(long j) {
        stop();
        mRunning = true;
        mTaskHandler.postDelayed(mTransferUpdaterRunnable, j);
    }

    public static void stop() {
        mRunning = false;
        mTaskHandler.removeCallbacks(mTransferUpdaterRunnable);
    }

    public static void setInterval(long j, boolean z) {
        mInterval = j;
        if (z) {
            start();
        }
    }

    public static void singleShot(long j) {
        if (j <= 0) {
            mTaskHandler.post(mTransferUpdaterRunnable);
        } else {
            mTaskHandler.postDelayed(mTransferUpdaterRunnable, j);
        }
    }

    public static class TransferUpdaterRunnable implements Runnable {
        private TransferUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    for (int i = 0; i < TransferUpdater.transcationBeans.size(); i++) {
                        TronAPI.getTransactionInfoByIdSo(((TranscationBean) TransferUpdater.transcationBeans.get(i)).getHash());
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            if (TransferUpdater.mRunning) {
                                TransferUpdater.mTaskHandler.removeCallbacks(TransferUpdater.mTransferUpdaterRunnable);
                                TransferUpdater.mTaskHandler.postDelayed(TransferUpdater.mTransferUpdaterRunnable, TransferUpdater.mInterval);
                            }
                        }
                    });
                }
            }, TransferUpdater.mExecutorService);
        }
    }
}
