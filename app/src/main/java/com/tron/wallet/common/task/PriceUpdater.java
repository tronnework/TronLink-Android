package com.tron.wallet.common.task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.bean.TrxPriceOutput;
import com.tron.wallet.common.utils.rb.RbUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class PriceUpdater {
    private static Context mContext;
    private static ExecutorService mExecutorService;
    private static long mInterval;
    private static PriceUpdaterRunnable mPriceUpdaterRunnable;
    private static boolean mRunning;
    private static Price mTRX_price;
    private static Handler mTaskHandler;

    public static boolean isInitialized() {
        return mContext != null;
    }

    public static boolean isRunning() {
        return mRunning;
    }

    PriceUpdater() {
    }

    public static void init(Context context, long j) {
        if (mContext == null) {
            mContext = context;
            mInterval = j;
            mRunning = false;
            mTaskHandler = new Handler(Looper.getMainLooper());
            mPriceUpdaterRunnable = new PriceUpdaterRunnable();
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
        if (mTRX_price == null) {
            Price price = new Price();
            mTRX_price = price;
            price.setCnyPrice(SpAPI.THIS.getCnyPrice());
            mTRX_price.setUsdPrice(SpAPI.THIS.getUsdPrice());
        }
        return mTRX_price;
    }

    public static Observable<TrxPriceOutput> getTrxPrice(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTrxPrice().compose(RxSchedulers.io_main());
    }

    public static class PriceUpdaterRunnable implements Runnable {
        private PriceUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    if (PriceUpdater.mContext != null) {
                        try {
                            String str = SpAPI.THIS.isUsdPrice() ? "usd" : "CNY";
                            new RxManager();
                            PriceUpdater.getTrxPrice(str).subscribe(new IObserver(new ICallback<TrxPriceOutput>() {
                                @Override
                                public void onFailure(String str2, String str3) {
                                }

                                @Override
                                public void onSubscribe(Disposable disposable) {
                                }

                                @Override
                                public void onResponse(String str2, TrxPriceOutput trxPriceOutput) {
                                    if (trxPriceOutput.code != 0 || trxPriceOutput.data == null) {
                                        return;
                                    }
                                    Price price = new Price();
                                    float parseFloat = Float.parseFloat(trxPriceOutput.data.price_cny) / Float.parseFloat(trxPriceOutput.data.price_usd);
                                    SpAPI.THIS.setUsdPrice(Float.parseFloat(trxPriceOutput.data.price_usd));
                                    price.setUsdPrice(Float.parseFloat(trxPriceOutput.data.price_usd));
                                    SpAPI.THIS.setCnyPrice(Float.parseFloat(trxPriceOutput.data.price_cny));
                                    price.setCnyPrice(Float.parseFloat(trxPriceOutput.data.price_cny));
                                    SpAPI.THIS.setUsdToRmb(parseFloat);
                                    price.usdToRmb = parseFloat;
                                    PriceUpdater.mTRX_price = price;
                                }
                            }, "getTrxPrice"));
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            RbUtil.THIS.sendPrice(PriceUpdater.mTRX_price);
                            if (PriceUpdater.mRunning) {
                                PriceUpdater.mTaskHandler.removeCallbacks(PriceUpdater.mPriceUpdaterRunnable);
                                PriceUpdater.mTaskHandler.postDelayed(PriceUpdater.mPriceUpdaterRunnable, PriceUpdater.mInterval);
                            }
                        }
                    });
                }
            }, PriceUpdater.mExecutorService);
        }

        private Price getPrice() {
            return new Price();
        }
    }
}
