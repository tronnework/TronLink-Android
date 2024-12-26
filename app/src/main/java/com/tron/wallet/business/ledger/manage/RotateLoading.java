package com.tron.wallet.business.ledger.manage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.R;
public class RotateLoading extends View {
    private final int DEFAULT_COLOR;
    private final int DEFAULT_SHADOW_POSITION;
    private final boolean DEFAULT_VISIBLE;
    private final int DEFAULT_WIDTH;
    private float arc;
    private int bottomDegree;
    private boolean changeBigger;
    private int color;
    private boolean isStart;
    private RectF loadingRectF;
    private Paint mPaint;
    private int shadowOffset;
    private RectF shadowRectF;
    private int topDegree;
    private int width;

    public boolean isStart() {
        return this.isStart;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public RotateLoading(Context context) {
        this(context, null);
    }

    public RotateLoading(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RotateLoading(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DEFAULT_COLOR = -1;
        this.DEFAULT_WIDTH = 6;
        this.DEFAULT_SHADOW_POSITION = 2;
        this.DEFAULT_VISIBLE = true;
        this.color = -1;
        this.width = dpToPx(6);
        this.shadowOffset = dpToPx(2);
        this.isStart = true;
        this.mPaint = new Paint();
        this.topDegree = 10;
        this.bottomDegree = 190;
        this.changeBigger = true;
        obtainStyleAttrs(attributeSet);
        initView();
    }

    private void obtainStyleAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RotateLoading);
        this.color = obtainStyledAttributes.getColor(0, this.color);
        this.width = (int) obtainStyledAttributes.getDimension(2, this.width);
        this.shadowOffset = (int) obtainStyledAttributes.getDimension(3, this.shadowOffset);
        this.isStart = obtainStyledAttributes.getBoolean(1, this.isStart);
        obtainStyledAttributes.recycle();
    }

    private void initView() {
        this.mPaint.setColor(this.color);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.width);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isStart) {
            this.mPaint.setColor(Color.parseColor("#1a000000"));
            canvas.drawArc(this.shadowRectF, this.topDegree, this.arc, false, this.mPaint);
            canvas.drawArc(this.shadowRectF, this.bottomDegree, this.arc, false, this.mPaint);
            this.mPaint.setColor(this.color);
            canvas.drawArc(this.loadingRectF, this.topDegree, this.arc, false, this.mPaint);
            canvas.drawArc(this.loadingRectF, this.bottomDegree, this.arc, false, this.mPaint);
            try {
                Thread.sleep(2L);
            } catch (InterruptedException e) {
                LogUtils.e(e);
            }
            int i = this.topDegree;
            int i2 = i + 10;
            this.topDegree = i2;
            int i3 = this.bottomDegree;
            int i4 = i3 + 10;
            this.bottomDegree = i4;
            if (i2 > 360) {
                this.topDegree = i - 350;
            }
            if (i4 > 360) {
                this.bottomDegree = i3 - 350;
            }
            if (this.changeBigger) {
                float f = this.arc;
                if (f < 160.0f) {
                    this.arc = (float) (f + 2.5d);
                    invalidate();
                }
            } else {
                float f2 = this.arc;
                if (f2 > 10.0f) {
                    this.arc = f2 - 5.0f;
                    invalidate();
                }
            }
            float f3 = this.arc;
            if (f3 == 160.0f || f3 == 10.0f) {
                this.changeBigger = !this.changeBigger;
                invalidate();
            }
        }
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.arc = 10.0f;
        int i5 = this.width;
        this.loadingRectF = new RectF(i5 * 2, i5 * 2, i - (i5 * 2), i2 - (i5 * 2));
        int i6 = this.width;
        int i7 = this.shadowOffset;
        this.shadowRectF = new RectF((i6 * 2) + i7, (i6 * 2) + i7, (i - (i6 * 2)) + i7, (i2 - (i6 * 2)) + i7);
    }

    public void start() {
        startAnimator();
        this.isStart = true;
        invalidate();
    }

    public void stop() {
        stopAnimator();
        invalidate();
    }

    private void startAnimator() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1.0f);
        ofFloat.setDuration(500L);
        ofFloat2.setDuration(500L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.start();
    }

    private void stopAnimator() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.0f);
        ofFloat.setDuration(500L);
        ofFloat2.setDuration(500L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                isStart = false;
            }
        });
        animatorSet.start();
    }

    public void setWidth(int i) {
        this.width = dpToPx(i);
        this.mPaint.setStrokeWidth(i);
    }

    public void setShadowOffset(int i) {
        this.shadowOffset = dpToPx(i);
    }

    private int dpToPx(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }
}
