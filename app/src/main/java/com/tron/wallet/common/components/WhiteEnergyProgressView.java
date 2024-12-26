package com.tron.wallet.common.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.R;
public class WhiteEnergyProgressView extends View {
    private float animateProgressValue;
    private boolean isAnimating;
    private int mHeight;
    private int mItemFinishedColor;
    private int mItemUnFinishColor;
    private Paint mPaint;
    private float mProgressValue;
    private int mWidth;
    private boolean shouldStartAnimate;

    public void setShouldStartAnimate(boolean z) {
        this.shouldStartAnimate = z;
    }

    public WhiteEnergyProgressView(Context context) {
        super(context, null);
        this.mProgressValue = 0.0f;
        this.animateProgressValue = 0.0f;
    }

    public WhiteEnergyProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WhiteEnergyProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgressValue = 0.0f;
        this.animateProgressValue = 0.0f;
        initView(context, attributeSet);
    }

    public void setProgressValue(final float f) {
        if (this.isAnimating) {
            this.shouldStartAnimate = false;
            postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$setProgressValue$0(f);
                }
            }, 500L);
            return;
        }
        this.mProgressValue = f;
        if (this.shouldStartAnimate) {
            this.animateProgressValue = 0.0f;
            this.shouldStartAnimate = false;
            startProgressAnimator();
            return;
        }
        this.animateProgressValue = f;
        invalidate();
    }

    public void lambda$setProgressValue$0(float f) {
        this.isAnimating = false;
        setProgressValue(f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setColor(this.mItemUnFinishColor);
        float dp2px = XPopupUtils.dp2px(getContext(), 10.0f);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, this.mWidth, this.mHeight), dp2px, dp2px, this.mPaint);
        this.mPaint.setColor(this.mItemFinishedColor);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, Math.round(this.animateProgressValue * this.mWidth), this.mHeight), dp2px, dp2px, this.mPaint);
    }

    private void startProgressAnimator() {
        this.isAnimating = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, this.mProgressValue);
        ofFloat.setDuration(500L);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                isAnimating = false;
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animateProgressValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                postInvalidateOnAnimation();
            }
        });
        ofFloat.start();
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.mPaint = new Paint(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WhiteEnergyProgressView);
        this.mItemUnFinishColor = obtainStyledAttributes.getColor(1, Color.parseColor("#33FFFFFF"));
        this.mItemFinishedColor = obtainStyledAttributes.getColor(0, Color.parseColor("#FFFFFF"));
        obtainStyledAttributes.recycle();
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
    }
}
