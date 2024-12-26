package com.tron.wallet.common.components.xtablayout;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.tron.wallet.common.components.xtablayout.ValueAnimatorCompat;
class ValueAnimatorCompatImplEclairMr1 extends ValueAnimatorCompat.Impl {
    private static final int DEFAULT_DURATION = 200;
    private static final int HANDLER_DELAY = 10;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private float mAnimatedFraction;
    private Interpolator mInterpolator;
    private boolean mIsRunning;
    private ValueAnimatorCompat.Impl.AnimatorListenerProxy mListener;
    private long mStartTime;
    private ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy mUpdateListener;
    private final int[] mIntValues = new int[2];
    private final float[] mFloatValues = new float[2];
    private int mDuration = 200;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            update();
        }
    };

    @Override
    public float getAnimatedFraction() {
        return this.mAnimatedFraction;
    }

    @Override
    public long getDuration() {
        return this.mDuration;
    }

    @Override
    public boolean isRunning() {
        return this.mIsRunning;
    }

    @Override
    public void setDuration(int i) {
        this.mDuration = i;
    }

    @Override
    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    @Override
    public void setListener(ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy) {
        this.mListener = animatorListenerProxy;
    }

    @Override
    public void setUpdateListener(ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy) {
        this.mUpdateListener = animatorUpdateListenerProxy;
    }

    ValueAnimatorCompatImplEclairMr1() {
    }

    @Override
    public void start() {
        if (this.mIsRunning) {
            return;
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = new AccelerateDecelerateInterpolator();
        }
        this.mStartTime = SystemClock.uptimeMillis();
        this.mIsRunning = true;
        ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
        if (animatorListenerProxy != null) {
            animatorListenerProxy.onAnimationStart();
        }
        sHandler.postDelayed(this.mRunnable, 10L);
    }

    @Override
    public void setIntValues(int i, int i2) {
        int[] iArr = this.mIntValues;
        iArr[0] = i;
        iArr[1] = i2;
    }

    @Override
    public int getAnimatedIntValue() {
        int[] iArr = this.mIntValues;
        return AnimationUtils.lerp(iArr[0], iArr[1], getAnimatedFraction());
    }

    @Override
    public void setFloatValues(float f, float f2) {
        float[] fArr = this.mFloatValues;
        fArr[0] = f;
        fArr[1] = f2;
    }

    @Override
    public float getAnimatedFloatValue() {
        float[] fArr = this.mFloatValues;
        return AnimationUtils.lerp(fArr[0], fArr[1], getAnimatedFraction());
    }

    @Override
    public void cancel() {
        this.mIsRunning = false;
        sHandler.removeCallbacks(this.mRunnable);
        ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
        if (animatorListenerProxy != null) {
            animatorListenerProxy.onAnimationCancel();
        }
    }

    @Override
    public void end() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            sHandler.removeCallbacks(this.mRunnable);
            this.mAnimatedFraction = 1.0f;
            ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy = this.mUpdateListener;
            if (animatorUpdateListenerProxy != null) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
            ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
            if (animatorListenerProxy != null) {
                animatorListenerProxy.onAnimationEnd();
            }
        }
    }

    public void update() {
        if (this.mIsRunning) {
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mDuration;
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                uptimeMillis = interpolator.getInterpolation(uptimeMillis);
            }
            this.mAnimatedFraction = uptimeMillis;
            ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy = this.mUpdateListener;
            if (animatorUpdateListenerProxy != null) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
            if (SystemClock.uptimeMillis() >= this.mStartTime + this.mDuration) {
                this.mIsRunning = false;
                ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy = this.mListener;
                if (animatorListenerProxy != null) {
                    animatorListenerProxy.onAnimationEnd();
                }
            }
        }
        if (this.mIsRunning) {
            sHandler.postDelayed(this.mRunnable, 10L);
        }
    }
}
