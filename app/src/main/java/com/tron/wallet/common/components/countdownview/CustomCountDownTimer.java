package com.tron.wallet.common.components.countdownview;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
public abstract class CustomCountDownTimer {
    private static final int MSG = 1;
    private final long mCountdownInterval;
    private final long mMillisInFuture;
    private long mPauseTimeInFuture;
    private long mStopTimeInFuture;
    private boolean isStop = false;
    private boolean isPause = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            synchronized (CustomCountDownTimer.this) {
                if (!isStop && !isPause) {
                    long elapsedRealtime = mStopTimeInFuture - SystemClock.elapsedRealtime();
                    if (elapsedRealtime <= 0) {
                        onFinish();
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        onTick(elapsedRealtime);
                        long elapsedRealtime3 = (elapsedRealtime2 + mCountdownInterval) - SystemClock.elapsedRealtime();
                        while (elapsedRealtime3 < 0) {
                            elapsedRealtime3 += mCountdownInterval;
                        }
                        sendMessageDelayed(obtainMessage(1), elapsedRealtime3);
                    }
                }
            }
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j);

    public CustomCountDownTimer(long j, long j2) {
        this.mMillisInFuture = j2 > 1000 ? j + 15 : j;
        this.mCountdownInterval = j2;
    }

    private synchronized CustomCountDownTimer start(long j) {
        this.isStop = false;
        if (j <= 0) {
            onFinish();
            return this;
        }
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + j;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
        return this;
    }

    public final synchronized void start() {
        start(this.mMillisInFuture);
    }

    public final synchronized void stop() {
        this.isStop = true;
        this.mHandler.removeMessages(1);
    }

    public final synchronized void pause() {
        if (this.isStop) {
            return;
        }
        this.isPause = true;
        this.mPauseTimeInFuture = this.mStopTimeInFuture - SystemClock.elapsedRealtime();
        this.mHandler.removeMessages(1);
    }

    public final synchronized void restart() {
        if (!this.isStop && this.isPause) {
            this.isPause = false;
            start(this.mPauseTimeInFuture);
        }
    }
}
