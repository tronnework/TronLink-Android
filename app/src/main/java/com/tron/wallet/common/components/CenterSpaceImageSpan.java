package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import java.lang.ref.WeakReference;
public class CenterSpaceImageSpan extends ImageSpan {
    private WeakReference<Drawable> mDrawableRef;
    private final int mMarginLeft;
    private final int mMarginRight;

    public CenterSpaceImageSpan(Drawable drawable) {
        this(drawable, 0, 0);
    }

    public CenterSpaceImageSpan(Drawable drawable, int i, int i2) {
        super(drawable);
        this.mMarginLeft = i;
        this.mMarginRight = i2;
    }

    public CenterSpaceImageSpan(Context context, int i, int i2, int i3, int i4) {
        super(context, i, i2);
        this.mMarginLeft = i3;
        this.mMarginRight = i4;
    }

    @Override
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        canvas.save();
        canvas.translate(this.mMarginLeft + f, ((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return this.mMarginLeft + super.getSize(paint, charSequence, i, i2, fontMetricsInt) + this.mMarginRight;
    }

    private Drawable getCachedDrawable() {
        WeakReference<Drawable> weakReference = this.mDrawableRef;
        Drawable drawable = weakReference != null ? weakReference.get() : null;
        if (drawable == null) {
            Drawable drawable2 = getDrawable();
            this.mDrawableRef = new WeakReference<>(drawable2);
            return drawable2;
        }
        return drawable;
    }
}
