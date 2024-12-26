package com.tron.wallet.common.components.countdownview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.tron.wallet.R;
import org.apache.commons.lang3.time.DateUtils;
public class CountdownView extends View {
    private boolean isHideTimeBackground;
    private BaseCountdown mCountdown;
    private CustomCountDownTimer mCustomCountDownTimer;
    private long mInterval;
    private OnCountdownEndListener mOnCountdownEndListener;
    private OnCountdownIntervalListener mOnCountdownIntervalListener;
    private long mPreviousIntervalCallbackTime;
    private long mRemainTime;

    public interface OnCountdownEndListener {
        void onEnd(CountdownView countdownView);
    }

    public interface OnCountdownIntervalListener {
        void onInterval(CountdownView countdownView, long j);
    }

    public long getRemainTime() {
        return this.mRemainTime;
    }

    public void setOnCountdownEndListener(OnCountdownEndListener onCountdownEndListener) {
        this.mOnCountdownEndListener = onCountdownEndListener;
    }

    public void setOnCountdownIntervalListener(long j, OnCountdownIntervalListener onCountdownIntervalListener) {
        this.mInterval = j;
        this.mOnCountdownIntervalListener = onCountdownIntervalListener;
    }

    public CountdownView(Context context) {
        this(context, null);
    }

    public CountdownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CountdownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CountdownView);
        boolean z = obtainStyledAttributes.getBoolean(1, true);
        this.isHideTimeBackground = z;
        BaseCountdown baseCountdown = z ? new BaseCountdown() : new BackgroundCountdown();
        this.mCountdown = baseCountdown;
        baseCountdown.initStyleAttr(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        this.mCountdown.initialize();
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int allContentWidth = this.mCountdown.getAllContentWidth();
        int allContentHeight = this.mCountdown.getAllContentHeight();
        int measureSize = measureSize(1, allContentWidth, i);
        int measureSize2 = measureSize(2, allContentHeight, i2);
        setMeasuredDimension(measureSize, measureSize2);
        this.mCountdown.onMeasure(this, measureSize, measureSize2, allContentWidth, allContentHeight);
    }

    private int measureSize(int i, int i2, int i3) {
        int paddingTop;
        int paddingBottom;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode == MeasureSpec.AT_MOST) {
            return Math.max(i2, size);
        }
        if (i == 1) {
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        return paddingTop + paddingBottom + i2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCountdown.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    private void reLayout() {
        this.mCountdown.reLayout();
        requestLayout();
    }

    public void start(long j) {
        long j2;
        if (j <= 0) {
            return;
        }
        this.mPreviousIntervalCallbackTime = 0L;
        CustomCountDownTimer customCountDownTimer = this.mCustomCountDownTimer;
        if (customCountDownTimer != null) {
            customCountDownTimer.stop();
            this.mCustomCountDownTimer = null;
        }
        if (this.mCountdown.isShowMillisecond) {
            updateShow(j);
            j2 = 10;
        } else {
            j2 = 1000;
        }
        CustomCountDownTimer customCountDownTimer2 = new CustomCountDownTimer(j, j2) {
            @Override
            public void onTick(long j3) {
                updateShow(j3);
            }

            @Override
            public void onFinish() {
                if (mOnCountdownEndListener != null) {
                    mOnCountdownEndListener.onEnd(CountdownView.this);
                }
                allShowZero();
            }
        };
        this.mCustomCountDownTimer = customCountDownTimer2;
        customCountDownTimer2.start();
    }

    public void stop() {
        CustomCountDownTimer customCountDownTimer = this.mCustomCountDownTimer;
        if (customCountDownTimer != null) {
            customCountDownTimer.stop();
        }
    }

    public void pause() {
        CustomCountDownTimer customCountDownTimer = this.mCustomCountDownTimer;
        if (customCountDownTimer != null) {
            customCountDownTimer.pause();
        }
    }

    public void restart() {
        CustomCountDownTimer customCountDownTimer = this.mCustomCountDownTimer;
        if (customCountDownTimer != null) {
            customCountDownTimer.restart();
        }
    }

    @Deprecated
    public void customTimeShow(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.mCountdown.mHasSetIsShowDay = true;
        this.mCountdown.mHasSetIsShowHour = true;
        if (this.mCountdown.refTimeShow(z, z2, z3, z4, z5)) {
            start(this.mRemainTime);
        }
    }

    public void allShowZero() {
        this.mCountdown.setTimes(0, 0, 0, 0, 0);
        invalidate();
    }

    public int getDay() {
        return this.mCountdown.mDay;
    }

    public int getHour() {
        return this.mCountdown.mHour;
    }

    public int getMinute() {
        return this.mCountdown.mMinute;
    }

    public int getSecond() {
        return this.mCountdown.mSecond;
    }

    public void updateShow(long j) {
        OnCountdownIntervalListener onCountdownIntervalListener;
        this.mRemainTime = j;
        reSetTime(j);
        long j2 = this.mInterval;
        if (j2 > 0 && (onCountdownIntervalListener = this.mOnCountdownIntervalListener) != null) {
            long j3 = this.mPreviousIntervalCallbackTime;
            if (j3 == 0) {
                this.mPreviousIntervalCallbackTime = j;
            } else if (j2 + j <= j3) {
                this.mPreviousIntervalCallbackTime = j;
                onCountdownIntervalListener.onInterval(this, this.mRemainTime);
            }
        }
        if (this.mCountdown.handlerAutoShowTime() || this.mCountdown.handlerDayLargeNinetyNine()) {
            reLayout();
        } else {
            invalidate();
        }
    }

    private void reSetTime(long j) {
        int i;
        int i2;
        if (!this.mCountdown.isConvertDaysToHours) {
            i2 = (int) (j / DateUtils.MILLIS_PER_DAY);
            i = (int) ((j % DateUtils.MILLIS_PER_DAY) / DateUtils.MILLIS_PER_HOUR);
        } else {
            i = (int) (j / DateUtils.MILLIS_PER_HOUR);
            i2 = 0;
        }
        this.mCountdown.setTimes(i2, i, (int) ((j % DateUtils.MILLIS_PER_HOUR) / 60000), (int) ((j % 60000) / 1000), (int) (j % 1000));
    }

    public void dynamicShow(com.tron.wallet.common.components.countdownview.DynamicConfig r17) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.countdownview.CountdownView.dynamicShow(com.tron.wallet.common.components.countdownview.DynamicConfig):void");
    }
}
