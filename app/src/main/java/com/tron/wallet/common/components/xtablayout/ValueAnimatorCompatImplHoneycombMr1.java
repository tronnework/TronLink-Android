package com.tron.wallet.common.components.xtablayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import com.tron.wallet.common.components.xtablayout.ValueAnimatorCompat;
class ValueAnimatorCompatImplHoneycombMr1 extends ValueAnimatorCompat.Impl {
    final ValueAnimator mValueAnimator = new ValueAnimator();

    @Override
    public void start() {
        this.mValueAnimator.start();
    }

    @Override
    public boolean isRunning() {
        return this.mValueAnimator.isRunning();
    }

    @Override
    public void setInterpolator(Interpolator interpolator) {
        this.mValueAnimator.setInterpolator(interpolator);
    }

    @Override
    public void setUpdateListener(final ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorUpdateListenerProxy) {
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
        });
    }

    @Override
    public void setListener(final ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorListenerProxy) {
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                animatorListenerProxy.onAnimationStart();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorListenerProxy.onAnimationEnd();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                animatorListenerProxy.onAnimationCancel();
            }
        });
    }

    @Override
    public void setIntValues(int i, int i2) {
        this.mValueAnimator.setIntValues(i, i2);
    }

    @Override
    public int getAnimatedIntValue() {
        return ((Integer) this.mValueAnimator.getAnimatedValue()).intValue();
    }

    @Override
    public void setFloatValues(float f, float f2) {
        this.mValueAnimator.setFloatValues(f, f2);
    }

    @Override
    public float getAnimatedFloatValue() {
        return ((Float) this.mValueAnimator.getAnimatedValue()).floatValue();
    }

    @Override
    public void setDuration(int i) {
        this.mValueAnimator.setDuration(i);
    }

    @Override
    public void cancel() {
        this.mValueAnimator.cancel();
    }

    @Override
    public float getAnimatedFraction() {
        return this.mValueAnimator.getAnimatedFraction();
    }

    @Override
    public void end() {
        this.mValueAnimator.end();
    }

    @Override
    public long getDuration() {
        return this.mValueAnimator.getDuration();
    }
}
