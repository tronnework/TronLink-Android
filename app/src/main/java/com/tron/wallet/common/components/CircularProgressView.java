package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.R;
import java.util.concurrent.atomic.AtomicReference;
public class CircularProgressView extends View {
    private static final int DEFAULT_ANIM_DURATION = 500;
    private static final int DEFAULT_PRIMARY_COLOR = -3219716;
    private static final int DEFAULT_PROGRESS_COLOR = -166025;
    private static final int DEFAULT_SWIPE_ANGLE = 180;
    private int animDuration;
    private final AtomicReference<Float> animProgress;
    private final int[] center;
    private boolean detachedFromWindow;
    private Shader gradient;
    private final Paint primaryBarPaint;
    private int primaryBarWidth;
    private int primaryColor;
    private final AtomicReference<Float> progress;
    private final Paint progressBarPaint;
    private int progressBarWidth;
    private int[] progressColor;
    private int radius;
    private boolean showAnimation;
    private int startAngle;
    private int swipeAngle;

    public void animDuration(int i) {
        this.animDuration = i;
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    public void setProgressColors(int[] iArr) {
        this.progressColor = iArr;
    }

    public void setSwipeAngle(int i) {
        this.swipeAngle = i;
    }

    public void showAnimation(boolean z) {
        this.showAnimation = z;
    }

    public CircularProgressView(Context context) {
        this(context, null);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.center = new int[2];
        Float valueOf = Float.valueOf(0.0f);
        this.progress = new AtomicReference<>(valueOf);
        this.animProgress = new AtomicReference<>(valueOf);
        this.showAnimation = true;
        this.animDuration = 500;
        this.progressColor = new int[]{DEFAULT_PROGRESS_COLOR, -5083147, -12813069};
        initAttrs(context, attributeSet, i);
        this.primaryBarPaint = createCommonPaint();
        this.progressBarPaint = createCommonPaint();
    }

    private void initAttrs(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgressView);
        this.primaryColor = obtainStyledAttributes.getColor(2, DEFAULT_PRIMARY_COLOR);
        this.progressColor[0] = obtainStyledAttributes.getColor(5, DEFAULT_PROGRESS_COLOR);
        this.radius = obtainStyledAttributes.getDimensionPixelSize(6, XPopupUtils.dp2px(context, 25.0f));
        this.primaryBarWidth = obtainStyledAttributes.getDimensionPixelSize(1, XPopupUtils.dp2px(context, 10.0f));
        this.showAnimation = obtainStyledAttributes.getBoolean(7, true);
        this.animDuration = obtainStyledAttributes.getInt(0, 500);
        this.progress.set(Float.valueOf(obtainStyledAttributes.getFloat(3, 0.2f)));
        this.startAngle = obtainStyledAttributes.getInteger(8, 180);
        this.swipeAngle = obtainStyledAttributes.getInteger(9, 180);
        this.progressBarWidth = obtainStyledAttributes.getDimensionPixelSize(4, XPopupUtils.dp2px(context, 15.0f));
        obtainStyledAttributes.recycle();
    }

    private Paint createCommonPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStrokeWidth(this.primaryBarWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    @Override
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != MeasureSpec.AT_MOST) {
            i = View.MeasureSpec.makeMeasureSpec((this.radius << 1) + getPaddingLeft() + getPaddingRight(), MeasureSpec.AT_MOST);
        } else {
            this.radius = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) >> 1;
        }
        if (this.swipeAngle == 180) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.radius + getPaddingTop() + getPaddingBottom() + getAdjustPaddingSize(), MeasureSpec.AT_MOST);
        }
        super.onMeasure(i, i2);
        int paddingTop = this.radius + getPaddingTop() + (Math.min(this.progressBarWidth, this.primaryBarWidth) >> 1);
        int[] iArr = this.center;
        iArr[0] = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) >> 1;
        iArr[1] = paddingTop;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPrimaryBar(canvas);
        drawProgressBar(canvas);
    }

    private void drawProgressBar(Canvas canvas) {
        RectF drawBounds = getDrawBounds(this.center, this.radius, getAdjustPaddingSize() >> 1);
        if (this.showAnimation) {
            AtomicReference<Float> atomicReference = this.animProgress;
            atomicReference.set(Float.valueOf(atomicReference.get().floatValue() + ((this.progress.get().floatValue() * 16.0f) / this.animDuration)));
        } else {
            this.animProgress.set(this.progress.get());
        }
        AtomicReference<Float> atomicReference2 = this.animProgress;
        atomicReference2.set(Float.valueOf(Math.min(atomicReference2.get().floatValue(), this.progress.get().floatValue())));
        float floatValue = this.animProgress.get().floatValue() * this.swipeAngle;
        this.progressBarPaint.setStrokeWidth(this.progressBarWidth);
        int[] iArr = this.progressColor;
        if (iArr.length > 1) {
            if (this.gradient == null) {
                this.gradient = new LinearGradient(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.top, this.progressColor, (float[]) null, Shader.TileMode.CLAMP);
            }
            this.progressBarPaint.setShader(this.gradient);
        } else {
            this.progressBarPaint.setColor(iArr[0]);
        }
        canvas.save();
        canvas.drawArc(drawBounds, this.startAngle, floatValue, false, this.progressBarPaint);
        canvas.restore();
        if (this.animProgress.get().floatValue() < this.progress.get().floatValue()) {
            postInvalidate();
        }
    }

    private void drawPrimaryBar(Canvas canvas) {
        canvas.save();
        this.primaryBarPaint.setColor(this.primaryColor);
        this.primaryBarPaint.setStrokeWidth(this.primaryBarWidth);
        canvas.drawArc(getDrawBounds(this.center, this.radius, getAdjustPaddingSize() >> 1), this.startAngle, this.swipeAngle, false, this.primaryBarPaint);
        canvas.restore();
    }

    private RectF getDrawBounds(int[] iArr, int i, int i2) {
        int i3 = iArr[0];
        int i4 = iArr[1];
        return new RectF((i3 - i) + i2, (i4 - i) + i2, (i3 + i) - i2, (i4 + i) - i2);
    }

    public void setProgress(float f) {
        Float valueOf = Float.valueOf(0.0f);
        if (f < 0.0f) {
            this.progress.set(valueOf);
        } else if (f > 1.0f) {
            this.progress.set(Float.valueOf(1.0f));
        } else {
            if (this.progress.get().floatValue() > f) {
                this.animProgress.set(valueOf);
            }
            this.progress.set(Float.valueOf(f));
        }
        if (this.detachedFromWindow) {
            return;
        }
        postInvalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.detachedFromWindow = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.detachedFromWindow = false;
    }

    private int getAdjustPaddingSize() {
        return Math.max(this.primaryBarWidth, this.progressBarWidth);
    }
}
