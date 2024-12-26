package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.CustomFontUtils;
public final class BadgeButton extends AppCompatTextView {
    private int btnTextColor;
    private BadgeDrawable mBadgeDrawable;

    public BadgeButton(Context context) {
        this(context, null);
    }

    public BadgeButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BadgeButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BadgeButton);
        String string = obtainStyledAttributes.getString(2);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        int integer = obtainStyledAttributes.getInteger(6, 0);
        int color = obtainStyledAttributes.getColor(0, -2396068);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, (int) (getResources().getDisplayMetrics().density * 12.0f));
        boolean z = obtainStyledAttributes.getBoolean(3, false);
        this.btnTextColor = obtainStyledAttributes.getColor(4, -1);
        obtainStyledAttributes.recycle();
        BadgeDrawable badgeDrawable = new BadgeDrawable(dimensionPixelSize2, color, this.btnTextColor, dimensionPixelSize, integer, CustomFontUtils.getTypeface(context, 0));
        this.mBadgeDrawable = badgeDrawable;
        badgeDrawable.setVisible(z);
        this.mBadgeDrawable.setText(string);
        setIcon(getCompoundDrawables()[1]);
    }

    public BadgeButton setIcon(Drawable drawable) {
        if (drawable != null && drawable.getBounds().isEmpty()) {
            drawable.setBounds(0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicHeight());
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
        return this;
    }

    @Override
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    public BadgeButton setBadgeText(String str) {
        this.mBadgeDrawable.setText(str);
        return this;
    }

    public BadgeButton setBadgeVisible(boolean z) {
        this.mBadgeDrawable.setVisible(z);
        return this;
    }

    public void setBadgeTextDef(String str) {
        this.mBadgeDrawable.setText(str);
        requestLayout();
    }

    public void setBadgeVisibleDef(boolean z) {
        this.mBadgeDrawable.setVisible(z);
        requestLayout();
    }

    @Override
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        if (getCompoundDrawables()[1] != null) {
            this.mBadgeDrawable.layout(measuredWidth, getPaddingTop(), measuredWidth);
        } else {
            this.mBadgeDrawable.layout(measuredWidth, getPaddingTop(), measuredWidth);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.mBadgeDrawable.draw(canvas);
    }

    private static class BadgeDrawable extends GradientDrawable {
        private int btnType;
        private int mHeight;
        private boolean mIsVisible;
        private TextPaint mPaint = new TextPaint(1);
        private String mText;

        public BadgeDrawable(int i, int i2, int i3, int i4, int i5, Typeface typeface) {
            this.mHeight = 0;
            setColor(i2);
            this.mPaint.setColor(i3);
            this.mPaint.setTypeface(typeface);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            if (i4 > 0) {
                this.mPaint.setTextSize(i4);
            } else {
                this.mPaint.setTextSize(i * 0.9f);
            }
            this.mHeight = i;
            this.btnType = i5;
        }

        void layout(int i, int i2, int i3) {
            Rect bounds = getBounds();
            if (this.btnType == 0) {
                bounds.offsetTo(Math.min(i, (i3 - bounds.width()) - ((int) (this.mHeight * 0.2f))), Math.max(0, i2 - (bounds.height() / 2)));
            } else {
                bounds.offsetTo(Math.min(i, i3 - bounds.width()), Math.max(0, i2 - (bounds.height() / 2)));
            }
            setBounds(bounds);
        }

        void resize(int i, int i2) {
            Rect bounds = getBounds();
            setBounds(bounds.left, bounds.top, bounds.left + i, bounds.top + i2);
            invalidateSelf();
        }

        public void setText(String str) {
            this.mText = str;
            if (TextUtils.isEmpty(str)) {
                int i = (int) (this.mHeight * 0.65d);
                resize(i, i);
                return;
            }
            double measureText = this.mPaint.measureText(this.mText);
            int i2 = this.mHeight;
            resize(Math.max((int) (measureText + (i2 * 0.4d)), i2), this.mHeight);
        }

        public void setVisible(boolean z) {
            if (this.mIsVisible != z) {
                invalidateSelf();
            }
            this.mIsVisible = z;
        }

        @Override
        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            setCornerRadius(getBounds().height() / 2.0f);
        }

        @Override
        public void draw(Canvas canvas) {
            if (this.mIsVisible) {
                super.draw(canvas);
                if (TextUtils.isEmpty(this.mText)) {
                    return;
                }
                canvas.drawText(this.mText, getBounds().exactCenterX(), getBounds().exactCenterY() - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f), this.mPaint);
            }
        }
    }
}
