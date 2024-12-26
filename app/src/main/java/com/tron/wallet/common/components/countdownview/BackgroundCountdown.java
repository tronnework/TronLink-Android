package com.tron.wallet.common.components.countdownview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.core.view.ViewCompat;
class BackgroundCountdown extends BaseCountdown {
    private static final float DEFAULT_TIME_BG_BORDER_SIZE = 1.0f;
    private static final float DEFAULT_TIME_BG_DIVISION_LINE_SIZE = 0.5f;
    private boolean isDrawBg;
    private boolean isShowTimeBgBorder;
    private boolean isShowTimeBgDivisionLine;
    private RectF mDayBgBorderRectF;
    private RectF mDayBgRectF;
    private float mDayTimeBgWidth;
    private float mDefSetTimeBgSize;
    private RectF mHourBgBorderRectF;
    private RectF mHourBgRectF;
    private RectF mMillisecondBgBorderRectF;
    private RectF mMillisecondBgRectF;
    private RectF mMinuteBgBorderRectF;
    private RectF mMinuteBgRectF;
    private RectF mSecondBgBorderRectF;
    private RectF mSecondBgRectF;
    private int mTimeBgBorderColor;
    private Paint mTimeBgBorderPaint;
    private float mTimeBgBorderRadius;
    private float mTimeBgBorderSize;
    private int mTimeBgColor;
    private int mTimeBgDivisionLineColor;
    private Paint mTimeBgDivisionLinePaint;
    private float mTimeBgDivisionLineSize;
    private float mTimeBgDivisionLineYPos;
    private Paint mTimeBgPaint;
    private float mTimeBgRadius;
    private float mTimeBgSize;
    private float mTimeTextBaseY;

    @Override
    public int getAllContentHeight() {
        return (int) (this.mTimeBgSize + (this.mTimeBgBorderSize * 2.0f));
    }

    @Override
    public void initStyleAttr(Context context, TypedArray typedArray) {
        super.initStyleAttr(context, typedArray);
        this.mTimeBgColor = typedArray.getColor(33, -12303292);
        this.mTimeBgRadius = typedArray.getDimension(36, 0.0f);
        boolean z = true;
        this.isShowTimeBgDivisionLine = typedArray.getBoolean(8, true);
        this.mTimeBgDivisionLineColor = typedArray.getColor(34, Color.parseColor("#30FFFFFF"));
        this.mTimeBgDivisionLineSize = typedArray.getDimension(35, Utils.dp2px(context, 0.5f));
        float dimension = typedArray.getDimension(37, 0.0f);
        this.mTimeBgSize = dimension;
        this.mDefSetTimeBgSize = dimension;
        this.mTimeBgBorderSize = typedArray.getDimension(32, Utils.dp2px(context, 1.0f));
        this.mTimeBgBorderRadius = typedArray.getDimension(31, 0.0f);
        this.mTimeBgBorderColor = typedArray.getColor(30, ViewCompat.MEASURED_STATE_MASK);
        this.isShowTimeBgBorder = typedArray.getBoolean(7, false);
        if (!typedArray.hasValue(33) && this.isShowTimeBgBorder) {
            z = false;
        }
        this.isDrawBg = z;
    }

    @Override
    public void initPaint() {
        super.initPaint();
        Paint paint = new Paint(1);
        this.mTimeBgPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mTimeBgPaint.setColor(this.mTimeBgColor);
        if (this.isShowTimeBgBorder) {
            initTimeBgBorderPaint();
        }
        if (this.isShowTimeBgDivisionLine) {
            initTimeTextBgDivisionLinePaint();
        }
    }

    private void initTimeBgBorderPaint() {
        if (this.mTimeBgBorderPaint != null) {
            return;
        }
        Paint paint = new Paint(1);
        this.mTimeBgBorderPaint = paint;
        paint.setColor(this.mTimeBgBorderColor);
        if (this.isDrawBg) {
            return;
        }
        this.mTimeBgBorderPaint.setStrokeWidth(this.mTimeBgBorderSize);
        this.mTimeBgBorderPaint.setStyle(Paint.Style.STROKE);
    }

    private void initTimeTextBgDivisionLinePaint() {
        if (this.mTimeBgDivisionLinePaint != null) {
            return;
        }
        Paint paint = new Paint(1);
        this.mTimeBgDivisionLinePaint = paint;
        paint.setColor(this.mTimeBgDivisionLineColor);
        this.mTimeBgDivisionLinePaint.setStrokeWidth(this.mTimeBgDivisionLineSize);
    }

    @Override
    public void initTimeTextBaseInfo() {
        super.initTimeTextBaseInfo();
        if (this.mDefSetTimeBgSize == 0.0f || this.mTimeBgSize < this.mTimeTextWidth) {
            this.mTimeBgSize = this.mTimeTextWidth + (Utils.dp2px(this.mContext, 2.0f) * 4);
        }
    }

    private void initTimeBgRect(float r11) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.countdownview.BackgroundCountdown.initTimeBgRect(float):void");
    }

    private float getSuffixTextBaseLine(String str, float f) {
        float f2;
        float f3;
        Rect rect = new Rect();
        this.mSuffixTextPaint.getTextBounds(str, 0, str.length(), rect);
        int i = this.mSuffixGravity;
        if (i != 0) {
            if (i != 2) {
                float f4 = this.mTimeBgSize;
                f2 = ((f + f4) - (f4 / 2.0f)) + (rect.height() / 2);
                f3 = this.mTimeBgBorderSize;
            } else {
                f2 = (f + this.mTimeBgSize) - rect.bottom;
                f3 = this.mTimeBgBorderSize * 2.0f;
            }
            return f2 + f3;
        }
        return f - rect.top;
    }

    private void initHasBackgroundTextBaseY(RectF rectF) {
        Paint.FontMetrics fontMetrics = this.mTimeTextPaint.getFontMetrics();
        this.mTimeTextBaseY = ((rectF.top + ((((rectF.bottom - rectF.top) - fontMetrics.bottom) + fontMetrics.top) / 2.0f)) - fontMetrics.top) - this.mTimeTextBottom;
        this.mTimeBgDivisionLineYPos = rectF.centerY() + (this.mTimeBgDivisionLineSize == ((float) Utils.dp2px(this.mContext, 0.5f)) ? this.mTimeBgDivisionLineSize : this.mTimeBgDivisionLineSize / 2.0f);
    }

    private float initTimeTextBaselineAndTimeBgTopPadding(int i, int i2, int i3, int i4) {
        float f = i2 == i3 ? (i - i4) / 2 : i2;
        if (this.isShowDay && this.mSuffixDayTextWidth > 0.0f) {
            this.mSuffixDayTextBaseline = getSuffixTextBaseLine(this.mSuffixDay, f);
        }
        if (this.isShowHour && this.mSuffixHourTextWidth > 0.0f) {
            this.mSuffixHourTextBaseline = getSuffixTextBaseLine(this.mSuffixHour, f);
        }
        if (this.isShowMinute && this.mSuffixMinuteTextWidth > 0.0f) {
            this.mSuffixMinuteTextBaseline = getSuffixTextBaseLine(this.mSuffixMinute, f);
        }
        if (this.mSuffixSecondTextWidth > 0.0f) {
            this.mSuffixSecondTextBaseline = getSuffixTextBaseLine(this.mSuffixSecond, f);
        }
        if (this.isShowMillisecond && this.mSuffixMillisecondTextWidth > 0.0f) {
            this.mSuffixMillisecondTextBaseline = getSuffixTextBaseLine(this.mSuffixMillisecond, f);
        }
        return f;
    }

    @Override
    public int getAllContentWidth() {
        float f;
        float allContentWidthBase = getAllContentWidthBase(this.mTimeBgSize + (this.mTimeBgBorderSize * 2.0f));
        if (this.isShowDay) {
            if (this.isDayLargeNinetyNine) {
                Rect rect = new Rect();
                String valueOf = String.valueOf(this.mDay);
                this.mTimeTextPaint.getTextBounds(valueOf, 0, valueOf.length(), rect);
                f = rect.width() + (Utils.dp2px(this.mContext, 2.0f) * 4);
                this.mDayTimeBgWidth = f;
            } else {
                f = this.mTimeBgSize;
                this.mDayTimeBgWidth = f;
            }
            allContentWidthBase = allContentWidthBase + f + (this.mTimeBgBorderSize * 2.0f);
        }
        return (int) Math.ceil(allContentWidthBase);
    }

    @Override
    public void onMeasure(View view, int i, int i2, int i3, int i4) {
        float initTimeTextBaselineAndTimeBgTopPadding = initTimeTextBaselineAndTimeBgTopPadding(i2, view.getPaddingTop(), view.getPaddingBottom(), i4);
        this.mLeftPaddingSize = view.getPaddingLeft() == view.getPaddingRight() ? (i - i3) / 2 : view.getPaddingLeft();
        initTimeBgRect(initTimeTextBaselineAndTimeBgTopPadding);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float f;
        if (this.isShowDay) {
            if (this.isShowTimeBgBorder) {
                RectF rectF = this.mDayBgBorderRectF;
                float f2 = this.mTimeBgBorderRadius;
                canvas.drawRoundRect(rectF, f2, f2, this.mTimeBgBorderPaint);
            }
            if (this.isDrawBg) {
                RectF rectF2 = this.mDayBgRectF;
                float f3 = this.mTimeBgRadius;
                canvas.drawRoundRect(rectF2, f3, f3, this.mTimeBgPaint);
                if (this.isShowTimeBgDivisionLine) {
                    canvas.drawLine(this.mLeftPaddingSize + this.mTimeBgBorderSize, this.mTimeBgDivisionLineYPos, this.mLeftPaddingSize + this.mDayTimeBgWidth + this.mTimeBgBorderSize, this.mTimeBgDivisionLineYPos, this.mTimeBgDivisionLinePaint);
                }
            }
            canvas.drawText(Utils.formatNum(this.mDay), this.mDayBgRectF.centerX(), this.mTimeTextBaseY, this.mTimeTextPaint);
            if (this.mSuffixDayTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixDay, this.mLeftPaddingSize + this.mDayTimeBgWidth + this.mSuffixDayLeftMargin + (this.mTimeBgBorderSize * 2.0f), this.mSuffixDayTextBaseline, this.mSuffixTextPaint);
            }
            f = this.mLeftPaddingSize + this.mDayTimeBgWidth + this.mSuffixDayTextWidth + this.mSuffixDayLeftMargin + this.mSuffixDayRightMargin + (this.mTimeBgBorderSize * 2.0f);
        } else {
            f = this.mLeftPaddingSize;
        }
        if (this.isShowHour) {
            if (this.isShowTimeBgBorder) {
                RectF rectF3 = this.mHourBgBorderRectF;
                float f4 = this.mTimeBgBorderRadius;
                canvas.drawRoundRect(rectF3, f4, f4, this.mTimeBgBorderPaint);
            }
            if (this.isDrawBg) {
                RectF rectF4 = this.mHourBgRectF;
                float f5 = this.mTimeBgRadius;
                canvas.drawRoundRect(rectF4, f5, f5, this.mTimeBgPaint);
                if (this.isShowTimeBgDivisionLine) {
                    float f6 = this.mTimeBgBorderSize;
                    float f7 = this.mTimeBgDivisionLineYPos;
                    canvas.drawLine(f + f6, f7, this.mTimeBgSize + f + f6, f7, this.mTimeBgDivisionLinePaint);
                }
            }
            canvas.drawText(Utils.formatNum(this.mHour), this.mHourBgRectF.centerX(), this.mTimeTextBaseY, this.mTimeTextPaint);
            if (this.mSuffixHourTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixHour, this.mTimeBgSize + f + this.mSuffixHourLeftMargin + (this.mTimeBgBorderSize * 2.0f), this.mSuffixHourTextBaseline, this.mSuffixTextPaint);
            }
            f = f + this.mTimeBgSize + this.mSuffixHourTextWidth + this.mSuffixHourLeftMargin + this.mSuffixHourRightMargin + (this.mTimeBgBorderSize * 2.0f);
        }
        if (this.isShowMinute) {
            if (this.isShowTimeBgBorder) {
                RectF rectF5 = this.mMinuteBgBorderRectF;
                float f8 = this.mTimeBgBorderRadius;
                canvas.drawRoundRect(rectF5, f8, f8, this.mTimeBgBorderPaint);
            }
            if (this.isDrawBg) {
                RectF rectF6 = this.mMinuteBgRectF;
                float f9 = this.mTimeBgRadius;
                canvas.drawRoundRect(rectF6, f9, f9, this.mTimeBgPaint);
                if (this.isShowTimeBgDivisionLine) {
                    float f10 = this.mTimeBgBorderSize;
                    float f11 = this.mTimeBgDivisionLineYPos;
                    canvas.drawLine(f + f10, f11, this.mTimeBgSize + f + f10, f11, this.mTimeBgDivisionLinePaint);
                }
            }
            canvas.drawText(Utils.formatNum(this.mMinute), this.mMinuteBgRectF.centerX(), this.mTimeTextBaseY, this.mTimeTextPaint);
            if (this.mSuffixMinuteTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixMinute, this.mTimeBgSize + f + this.mSuffixMinuteLeftMargin + (this.mTimeBgBorderSize * 2.0f), this.mSuffixMinuteTextBaseline, this.mSuffixTextPaint);
            }
            f = f + this.mTimeBgSize + this.mSuffixMinuteTextWidth + this.mSuffixMinuteLeftMargin + this.mSuffixMinuteRightMargin + (this.mTimeBgBorderSize * 2.0f);
        }
        if (this.isShowSecond) {
            if (this.isShowTimeBgBorder) {
                RectF rectF7 = this.mSecondBgBorderRectF;
                float f12 = this.mTimeBgBorderRadius;
                canvas.drawRoundRect(rectF7, f12, f12, this.mTimeBgBorderPaint);
            }
            if (this.isDrawBg) {
                RectF rectF8 = this.mSecondBgRectF;
                float f13 = this.mTimeBgRadius;
                canvas.drawRoundRect(rectF8, f13, f13, this.mTimeBgPaint);
                if (this.isShowTimeBgDivisionLine) {
                    float f14 = this.mTimeBgBorderSize;
                    float f15 = this.mTimeBgDivisionLineYPos;
                    canvas.drawLine(f + f14, f15, this.mTimeBgSize + f + f14, f15, this.mTimeBgDivisionLinePaint);
                }
            }
            canvas.drawText(Utils.formatNum(this.mSecond), this.mSecondBgRectF.centerX(), this.mTimeTextBaseY, this.mTimeTextPaint);
            if (this.mSuffixSecondTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixSecond, this.mTimeBgSize + f + this.mSuffixSecondLeftMargin + (this.mTimeBgBorderSize * 2.0f), this.mSuffixSecondTextBaseline, this.mSuffixTextPaint);
            }
            if (this.isShowMillisecond) {
                if (this.isShowTimeBgBorder) {
                    RectF rectF9 = this.mMillisecondBgBorderRectF;
                    float f16 = this.mTimeBgBorderRadius;
                    canvas.drawRoundRect(rectF9, f16, f16, this.mTimeBgBorderPaint);
                }
                float f17 = f + this.mTimeBgSize + this.mSuffixSecondTextWidth + this.mSuffixSecondLeftMargin + this.mSuffixSecondRightMargin + (this.mTimeBgBorderSize * 2.0f);
                if (this.isDrawBg) {
                    RectF rectF10 = this.mMillisecondBgRectF;
                    float f18 = this.mTimeBgRadius;
                    canvas.drawRoundRect(rectF10, f18, f18, this.mTimeBgPaint);
                    if (this.isShowTimeBgDivisionLine) {
                        float f19 = this.mTimeBgBorderSize;
                        float f20 = this.mTimeBgDivisionLineYPos;
                        canvas.drawLine(f17 + f19, f20, this.mTimeBgSize + f17 + f19, f20, this.mTimeBgDivisionLinePaint);
                    }
                }
                canvas.drawText(Utils.formatMillisecond(this.mMillisecond), this.mMillisecondBgRectF.centerX(), this.mTimeTextBaseY, this.mTimeTextPaint);
                if (this.mSuffixMillisecondTextWidth > 0.0f) {
                    canvas.drawText(this.mSuffixMillisecond, f17 + this.mTimeBgSize + this.mSuffixMillisecondLeftMargin + (this.mTimeBgBorderSize * 2.0f), this.mSuffixMillisecondTextBaseline, this.mSuffixTextPaint);
                }
            }
        }
    }

    public void setTimeBgSize(float f) {
        this.mTimeBgSize = Utils.dp2px(this.mContext, f);
    }

    public void setTimeBgColor(int i) {
        this.mTimeBgColor = i;
        this.mTimeBgPaint.setColor(i);
        if (i == 0 && this.isShowTimeBgBorder) {
            this.isDrawBg = false;
            this.mTimeBgBorderPaint.setStrokeWidth(this.mTimeBgBorderSize);
            this.mTimeBgBorderPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        this.isDrawBg = true;
        if (this.isShowTimeBgBorder) {
            this.mTimeBgBorderPaint.setStrokeWidth(0.0f);
            this.mTimeBgBorderPaint.setStyle(Paint.Style.FILL);
        }
    }

    public void setTimeBgRadius(float f) {
        this.mTimeBgRadius = Utils.dp2px(this.mContext, f);
    }

    public void setIsShowTimeBgDivisionLine(boolean z) {
        this.isShowTimeBgDivisionLine = z;
        if (z) {
            initTimeTextBgDivisionLinePaint();
        } else {
            this.mTimeBgDivisionLinePaint = null;
        }
    }

    public void setTimeBgDivisionLineColor(int i) {
        this.mTimeBgDivisionLineColor = i;
        Paint paint = this.mTimeBgDivisionLinePaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public void setTimeBgDivisionLineSize(float f) {
        float dp2px = Utils.dp2px(this.mContext, f);
        this.mTimeBgDivisionLineSize = dp2px;
        Paint paint = this.mTimeBgDivisionLinePaint;
        if (paint != null) {
            paint.setStrokeWidth(dp2px);
        }
    }

    public void setIsShowTimeBgBorder(boolean z) {
        this.isShowTimeBgBorder = z;
        if (z) {
            initTimeBgBorderPaint();
            return;
        }
        this.mTimeBgBorderPaint = null;
        this.mTimeBgBorderSize = 0.0f;
    }

    public void setTimeBgBorderColor(int i) {
        this.mTimeBgBorderColor = i;
        Paint paint = this.mTimeBgBorderPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public void setTimeBgBorderSize(float f) {
        float dp2px = Utils.dp2px(this.mContext, f);
        this.mTimeBgBorderSize = dp2px;
        Paint paint = this.mTimeBgBorderPaint;
        if (paint == null || this.isDrawBg) {
            return;
        }
        paint.setStrokeWidth(dp2px);
        this.mTimeBgBorderPaint.setStyle(Paint.Style.STROKE);
    }

    public void setTimeBgBorderRadius(float f) {
        this.mTimeBgBorderRadius = Utils.dp2px(this.mContext, f);
    }
}
