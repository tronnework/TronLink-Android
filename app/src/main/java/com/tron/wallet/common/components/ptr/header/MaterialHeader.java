package com.tron.wallet.common.components.ptr.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrUIHandler;
import com.tron.wallet.common.components.ptr.PtrUIHandlerHook;
import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
public class MaterialHeader extends View implements PtrUIHandler {
    private MaterialProgressDrawable mDrawable;
    private PtrFrameLayout mPtrFrameLayout;
    private float mScale;
    private Animation mScaleAnimation;

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
    }

    public MaterialHeader(Context context) {
        super(context);
        this.mScale = 1.0f;
        this.mScaleAnimation = new Animation() {
            @Override
            public void applyTransformation(float f, Transformation transformation) {
                mScale = 1.0f - f;
                mDrawable.setAlpha((int) (mScale * 255.0f));
                invalidate();
            }
        };
        initView();
    }

    public MaterialHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScale = 1.0f;
        this.mScaleAnimation = new Animation() {
            @Override
            public void applyTransformation(float f, Transformation transformation) {
                mScale = 1.0f - f;
                mDrawable.setAlpha((int) (mScale * 255.0f));
                invalidate();
            }
        };
        initView();
    }

    public MaterialHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScale = 1.0f;
        this.mScaleAnimation = new Animation() {
            @Override
            public void applyTransformation(float f, Transformation transformation) {
                mScale = 1.0f - f;
                mDrawable.setAlpha((int) (mScale * 255.0f));
                invalidate();
            }
        };
        initView();
    }

    public void setPtrFrameLayout(PtrFrameLayout ptrFrameLayout) {
        final PtrUIHandlerHook ptrUIHandlerHook = new PtrUIHandlerHook() {
            @Override
            public void run() {
                MaterialHeader materialHeader = MaterialHeader.this;
                materialHeader.startAnimation(materialHeader.mScaleAnimation);
            }
        };
        this.mScaleAnimation.setDuration(200L);
        this.mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ptrUIHandlerHook.resume();
            }
        });
        this.mPtrFrameLayout = ptrFrameLayout;
        ptrFrameLayout.setRefreshCompleteHook(ptrUIHandlerHook);
    }

    private void initView() {
        MaterialProgressDrawable materialProgressDrawable = new MaterialProgressDrawable(getContext(), this);
        this.mDrawable = materialProgressDrawable;
        materialProgressDrawable.setBackgroundColor(-1);
        this.mDrawable.setCallback(this);
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.mDrawable) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void setColorSchemeColors(int[] iArr) {
        this.mDrawable.setColorSchemeColors(iArr);
        invalidate();
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.mDrawable.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom(), MeasureSpec.AT_MOST));
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
        this.mDrawable.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = this.mDrawable.getBounds();
        canvas.translate(getPaddingLeft() + ((getMeasuredWidth() - this.mDrawable.getIntrinsicWidth()) / 2), getPaddingTop());
        float f = this.mScale;
        canvas.scale(f, f, bounds.exactCenterX(), bounds.exactCenterY());
        this.mDrawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        this.mScale = 1.0f;
        this.mDrawable.stop();
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.mDrawable.setAlpha(255);
        this.mDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z) {
        this.mDrawable.stop();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        float min = Math.min(1.0f, ptrIndicator.getCurrentPercent());
        if (b == 2) {
            this.mDrawable.setAlpha((int) (255.0f * min));
            this.mDrawable.showArrow(true);
            this.mDrawable.setStartEndTrim(0.0f, Math.min(0.8f, min * 0.8f));
            this.mDrawable.setArrowScale(Math.min(1.0f, min));
            this.mDrawable.setProgressRotation((((0.4f * min) - 0.25f) + (min * 2.0f)) * 0.5f);
            invalidate();
        }
    }
}
