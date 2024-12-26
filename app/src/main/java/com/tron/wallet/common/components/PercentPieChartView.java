package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.R;
public class PercentPieChartView extends View {
    private static final int BASE_COLOR = -14472127;
    private static final int PERCENT_COLOR = -744886;
    private int baseColor;
    private final Paint basePaint;
    private final int[] center;
    private boolean detachedFromWindow;
    private float percent;
    private int percentColor;
    private final Paint percentPaint;
    private int radius;

    @Override
    public boolean isInEditMode() {
        return true;
    }

    public PercentPieChartView(Context context) {
        this(context, null);
    }

    public PercentPieChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PercentPieChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.center = new int[2];
        initAttrs(context, attributeSet, i);
        this.basePaint = createCommonPaint();
        this.percentPaint = createCommonPaint();
    }

    private void initAttrs(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgressView);
        this.baseColor = obtainStyledAttributes.getColor(0, BASE_COLOR);
        this.percentColor = obtainStyledAttributes.getColor(2, PERCENT_COLOR);
        this.radius = obtainStyledAttributes.getDimensionPixelSize(3, XPopupUtils.dp2px(context, 6.0f));
        this.percent = obtainStyledAttributes.getFloat(1, 0.2f);
        obtainStyledAttributes.recycle();
    }

    private Paint createCommonPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != MeasureSpec.AT_MOST) {
            i = View.MeasureSpec.makeMeasureSpec((this.radius << 1) + getPaddingLeft() + getPaddingRight(), MeasureSpec.AT_MOST);
            i2 = View.MeasureSpec.makeMeasureSpec((this.radius << 1) + getPaddingTop() + getPaddingBottom(), MeasureSpec.AT_MOST);
        }
        super.onMeasure(i, i2);
        int paddingTop = this.radius + getPaddingTop();
        int[] iArr = this.center;
        iArr[0] = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) >> 1;
        iArr[1] = paddingTop;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBase(canvas);
        drawProgressBar(canvas);
    }

    private void drawProgressBar(Canvas canvas) {
        RectF drawBounds = getDrawBounds(this.center, this.radius, false);
        canvas.save();
        this.percentPaint.setColor(this.percentColor);
        canvas.drawArc(drawBounds, -90.0f, this.percent * 360.0f, true, this.percentPaint);
        canvas.restore();
    }

    private void drawBase(Canvas canvas) {
        canvas.save();
        this.basePaint.setColor(this.baseColor);
        canvas.drawArc(getDrawBounds(this.center, this.radius, true), (this.percent * 360.0f) - 90.0f, 360.0f - (this.percent * 360.0f), true, this.basePaint);
        canvas.restore();
    }

    private RectF getDrawBounds(int[] iArr, int i, boolean z) {
        if (z) {
            int i2 = iArr[0];
            int i3 = iArr[1];
            return new RectF(i2 - i, i3 - i, i2 + i, i3 + i);
        }
        int i4 = iArr[0];
        int i5 = iArr[1];
        return new RectF(i4 - i, i5 - i, i4 + i, i5 + i);
    }

    public void setPercent(float f) {
        if (f < 0.0f) {
            this.percent = 0.0f;
        } else if (f > 0.0f && f < 0.02d) {
            this.percent = 0.02f;
        } else if (f > 1.0f) {
            this.percent = 1.0f;
        } else {
            this.percent = f;
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
}
