package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.tron.wallet.common.utils.UIUtils;
public class SignatureProgressView extends View {
    private int mHeight;
    private int mItemFinishedColor;
    private int mItemUnFinishColor;
    private Paint mPaint;
    private float mProgressValue;
    private int mWidth;

    public void setProgressValue(float f) {
        this.mProgressValue = f;
        invalidate();
    }

    public SignatureProgressView(Context context) {
        super(context, null);
        this.mProgressValue = 0.0f;
        this.mItemUnFinishColor = Color.parseColor("#EBEDF0");
        this.mItemFinishedColor = Color.parseColor("#3C7CF3");
    }

    public SignatureProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignatureProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mProgressValue = 0.0f;
        this.mItemUnFinishColor = Color.parseColor("#EBEDF0");
        this.mItemFinishedColor = Color.parseColor("#3C7CF3");
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dip2px = UIUtils.dip2px(10.0f);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, this.mWidth, this.mHeight), dip2px, dip2px, this.mPaint);
        int round = Math.round(this.mProgressValue * this.mWidth);
        this.mPaint.setColor(this.mItemFinishedColor);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, round, this.mHeight), dip2px, dip2px, this.mPaint);
        setInitPaint();
    }

    private void initView() {
        this.mPaint = new Paint(1);
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
