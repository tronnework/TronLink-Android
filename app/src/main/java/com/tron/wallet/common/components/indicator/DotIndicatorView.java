package com.tron.wallet.common.components.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
public class DotIndicatorView extends View {
    static final int DEFAULT_ALPHA = 76;
    private int alphaValue;
    Paint dotPaint;
    private int left;
    private int top;

    public void animateIn() {
        this.alphaValue = 255;
    }

    public void animateOut() {
        this.alphaValue = 76;
    }

    @Override
    public void setSelected(boolean z) {
        this.alphaValue = z ? 255 : 76;
    }

    public DotIndicatorView(Context context) {
        this(context, null);
    }

    public DotIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint();
        this.dotPaint = paint;
        paint.setColor(-1);
        this.dotPaint.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
    }

    public void animAlpha(float f) {
        this.alphaValue = (int) Math.min(255.0f, f * 255.0f);
    }

    public void drawSelf(Canvas canvas, int i, int i2) {
        this.left = i;
        this.top = i2;
        draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int min = Math.min(getMeasuredWidth(), getMeasuredHeight()) >> 1;
        this.dotPaint.setAlpha(this.alphaValue);
        canvas.drawCircle(this.left + min, this.top + min, min, this.dotPaint);
    }
}
