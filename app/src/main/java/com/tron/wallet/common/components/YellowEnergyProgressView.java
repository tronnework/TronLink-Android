package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.UIUtils;
public class YellowEnergyProgressView extends View {
    private int mHeight;
    private int mItemFinishedColor;
    private int mItemUnFinishColor;
    private Paint mPaint;
    private float mProgressValue;
    private int mWidth;
    private RectF rectBg;
    private RectF rectItem;

    public void setProgressValue(float f) {
        this.mProgressValue = f;
        invalidate();
    }

    public YellowEnergyProgressView(Context context) {
        super(context, null);
        this.mProgressValue = 1.0f;
    }

    public YellowEnergyProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YellowEnergyProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgressValue = 1.0f;
        initView(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dip2px = UIUtils.dip2px(10.0f);
        if (this.mWidth == 0) {
            this.mWidth = getMeasuredWidth();
        }
        if (this.mHeight == 0) {
            this.mHeight = getMeasuredHeight();
        }
        setInitPaint();
        this.rectBg.set(getPaddingLeft(), getPaddingTop(), this.mWidth - getPaddingRight(), this.mHeight - getPaddingBottom());
        canvas.drawRoundRect(this.rectBg, dip2px, dip2px, this.mPaint);
        this.mPaint.setColor(this.mItemFinishedColor);
        this.rectItem.set(this.rectBg.left, this.rectBg.top, Math.round(this.mProgressValue * (this.rectBg.right - this.rectBg.left)) + this.rectBg.left, this.rectBg.bottom);
        canvas.drawRoundRect(this.rectItem, dip2px, dip2px, this.mPaint);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.mPaint = new Paint(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.YellowEnergyProgressView);
        this.mItemUnFinishColor = obtainStyledAttributes.getColor(0, Color.parseColor("#29135DCD"));
        this.mItemFinishedColor = obtainStyledAttributes.getColor(1, Color.parseColor("#FF95BFFF"));
        obtainStyledAttributes.recycle();
        this.rectBg = new RectF();
        this.rectItem = new RectF();
        setInitPaint();
    }

    private void setInitPaint() {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mItemUnFinishColor);
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
    }
}
