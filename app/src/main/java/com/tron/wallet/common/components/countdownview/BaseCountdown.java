package com.tron.wallet.common.components.countdownview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.tron.tron_base.frame.utils.LogUtils;
class BaseCountdown {
    private static final String DEFAULT_SUFFIX = ":";
    private static final float DEFAULT_SUFFIX_LR_MARGIN = 3.0f;
    private boolean hasCustomSomeSuffix;
    private boolean hasSetSuffixDay;
    private boolean hasSetSuffixHour;
    private boolean hasSetSuffixMillisecond;
    private boolean hasSetSuffixMinute;
    private boolean hasSetSuffixSecond;
    public boolean isConvertDaysToHours;
    protected boolean isDayLargeNinetyNine;
    public boolean isShowDay;
    public boolean isShowHour;
    public boolean isShowMillisecond;
    public boolean isShowMinute;
    public boolean isShowSecond;
    private boolean isSuffixTextBold;
    private boolean isTimeTextBold;
    protected Context mContext;
    public int mDay;
    private float mDayTimeTextWidth;
    public boolean mHasSetIsShowDay;
    public boolean mHasSetIsShowHour;
    public int mHour;
    private float mHourTimeTextWidth;
    protected float mLeftPaddingSize;
    protected Paint mMeasureHourWidthPaint;
    public int mMillisecond;
    public int mMinute;
    public int mSecond;
    protected String mSuffix;
    protected String mSuffixDay;
    protected float mSuffixDayLeftMargin;
    protected float mSuffixDayRightMargin;
    protected float mSuffixDayTextBaseline;
    protected float mSuffixDayTextWidth;
    protected int mSuffixGravity;
    protected String mSuffixHour;
    protected float mSuffixHourLeftMargin;
    protected float mSuffixHourRightMargin;
    protected float mSuffixHourTextBaseline;
    protected float mSuffixHourTextWidth;
    private float mSuffixLRMargin;
    protected String mSuffixMillisecond;
    protected float mSuffixMillisecondLeftMargin;
    protected float mSuffixMillisecondTextBaseline;
    protected float mSuffixMillisecondTextWidth;
    protected String mSuffixMinute;
    protected float mSuffixMinuteLeftMargin;
    protected float mSuffixMinuteRightMargin;
    protected float mSuffixMinuteTextBaseline;
    protected float mSuffixMinuteTextWidth;
    protected String mSuffixSecond;
    protected float mSuffixSecondLeftMargin;
    protected float mSuffixSecondRightMargin;
    protected float mSuffixSecondTextBaseline;
    protected float mSuffixSecondTextWidth;
    private int mSuffixTextColor;
    protected Paint mSuffixTextPaint;
    private float mSuffixTextSize;
    private float mTempSuffixDayLeftMargin;
    private float mTempSuffixDayRightMargin;
    private float mTempSuffixHourLeftMargin;
    private float mTempSuffixHourRightMargin;
    private float mTempSuffixMillisecondLeftMargin;
    private String mTempSuffixMinute;
    private float mTempSuffixMinuteLeftMargin;
    private float mTempSuffixMinuteRightMargin;
    private String mTempSuffixSecond;
    private float mTempSuffixSecondLeftMargin;
    private float mTempSuffixSecondRightMargin;
    private float mTimeTextBaseline;
    protected float mTimeTextBottom;
    private int mTimeTextColor;
    protected float mTimeTextHeight;
    protected Paint mTimeTextPaint;
    private float mTimeTextSize;
    protected float mTimeTextWidth;

    private void initTempSuffixMargin() {
        this.mTempSuffixDayLeftMargin = this.mSuffixDayLeftMargin;
        this.mTempSuffixDayRightMargin = this.mSuffixDayRightMargin;
        this.mTempSuffixHourLeftMargin = this.mSuffixHourLeftMargin;
        this.mTempSuffixHourRightMargin = this.mSuffixHourRightMargin;
        this.mTempSuffixMinuteLeftMargin = this.mSuffixMinuteLeftMargin;
        this.mTempSuffixMinuteRightMargin = this.mSuffixMinuteRightMargin;
        this.mTempSuffixSecondLeftMargin = this.mSuffixSecondLeftMargin;
        this.mTempSuffixSecondRightMargin = this.mSuffixSecondRightMargin;
        this.mTempSuffixMillisecondLeftMargin = this.mSuffixMillisecondLeftMargin;
    }

    public int getAllContentHeight() {
        return (int) this.mTimeTextHeight;
    }

    public boolean handlerDayLargeNinetyNine() {
        if (this.isShowDay) {
            boolean z = this.isDayLargeNinetyNine;
            if (!z && this.mDay > 99) {
                this.isDayLargeNinetyNine = true;
            } else if (!z || this.mDay > 99) {
                return false;
            } else {
                this.isDayLargeNinetyNine = false;
            }
            return true;
        }
        return false;
    }

    public void reSetTimeShow(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.isShowDay = z;
        this.isShowHour = z2;
        this.isShowMinute = z3;
        this.isShowSecond = z4;
        this.isShowMillisecond = z5;
    }

    public boolean refTimeShow(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6 = false;
        if (!z4) {
            z5 = false;
        }
        if (this.isShowDay != z) {
            this.isShowDay = z;
            if (z) {
                this.mSuffixDayLeftMargin = this.mTempSuffixDayLeftMargin;
                this.mSuffixDayRightMargin = this.mTempSuffixDayRightMargin;
            }
        }
        if (this.isShowHour != z2) {
            this.isShowHour = z2;
            if (z2) {
                this.mSuffixHourLeftMargin = this.mTempSuffixHourLeftMargin;
                this.mSuffixHourRightMargin = this.mTempSuffixHourRightMargin;
            }
        }
        if (this.isShowMinute != z3) {
            this.isShowMinute = z3;
            if (z3) {
                this.mSuffixMinuteLeftMargin = this.mTempSuffixMinuteLeftMargin;
                this.mSuffixMinuteRightMargin = this.mTempSuffixMinuteRightMargin;
                this.mSuffixMinute = this.mTempSuffixMinute;
            }
        }
        if (this.isShowSecond != z4) {
            this.isShowSecond = z4;
            if (z4) {
                this.mSuffixSecondLeftMargin = this.mTempSuffixSecondLeftMargin;
                this.mSuffixSecondRightMargin = this.mTempSuffixSecondRightMargin;
                this.mSuffixSecond = this.mTempSuffixSecond;
            } else {
                this.mSuffixMinute = this.mTempSuffixMinute;
            }
            this.mSuffixMinuteLeftMargin = this.mTempSuffixMinuteLeftMargin;
            this.mSuffixMinuteRightMargin = this.mTempSuffixMinuteRightMargin;
            z6 = true;
        }
        if (this.isShowMillisecond != z5) {
            this.isShowMillisecond = z5;
            if (z5) {
                this.mSuffixMillisecondLeftMargin = this.mTempSuffixMillisecondLeftMargin;
            } else {
                this.mSuffixSecond = this.mTempSuffixSecond;
            }
            this.mSuffixSecondLeftMargin = this.mTempSuffixSecondLeftMargin;
            this.mSuffixSecondRightMargin = this.mTempSuffixSecondRightMargin;
            return true;
        }
        return z6;
    }

    public boolean setConvertDaysToHours(boolean z) {
        if (this.isConvertDaysToHours == z) {
            return false;
        }
        this.isConvertDaysToHours = z;
        return true;
    }

    public void setSuffixGravity(int i) {
        this.mSuffixGravity = i;
    }

    public void setTimes(int i, int i2, int i3, int i4, int i5) {
        this.mDay = i;
        this.mHour = i2;
        this.mMinute = i3;
        this.mSecond = i4;
        this.mMillisecond = i5;
    }

    public void initStyleAttr(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.isTimeTextBold = typedArray.getBoolean(10, false);
        this.mTimeTextSize = typedArray.getDimension(39, Utils.sp2px(this.mContext, 12.0f));
        this.mTimeTextColor = typedArray.getColor(38, ViewCompat.MEASURED_STATE_MASK);
        this.isShowDay = typedArray.getBoolean(2, false);
        this.isShowHour = typedArray.getBoolean(3, false);
        this.isShowMinute = typedArray.getBoolean(5, true);
        this.isShowSecond = typedArray.getBoolean(6, true);
        this.isShowMillisecond = typedArray.getBoolean(4, false);
        if (typedArray.getBoolean(1, true)) {
            this.isConvertDaysToHours = typedArray.getBoolean(0, false);
        }
        this.isSuffixTextBold = typedArray.getBoolean(9, false);
        this.mSuffixTextSize = typedArray.getDimension(29, Utils.sp2px(this.mContext, 12.0f));
        this.mSuffixTextColor = typedArray.getColor(28, ViewCompat.MEASURED_STATE_MASK);
        this.mSuffix = typedArray.getString(11);
        this.mSuffixDay = typedArray.getString(12);
        this.mSuffixHour = typedArray.getString(16);
        this.mSuffixMinute = typedArray.getString(22);
        this.mSuffixSecond = typedArray.getString(25);
        this.mSuffixMillisecond = typedArray.getString(20);
        this.mSuffixGravity = typedArray.getInt(15, 1);
        this.mSuffixLRMargin = typedArray.getDimension(19, -1.0f);
        this.mSuffixDayLeftMargin = typedArray.getDimension(13, -1.0f);
        this.mSuffixDayRightMargin = typedArray.getDimension(14, -1.0f);
        this.mSuffixHourLeftMargin = typedArray.getDimension(17, -1.0f);
        this.mSuffixHourRightMargin = typedArray.getDimension(18, -1.0f);
        this.mSuffixMinuteLeftMargin = typedArray.getDimension(23, -1.0f);
        this.mSuffixMinuteRightMargin = typedArray.getDimension(24, -1.0f);
        this.mSuffixSecondLeftMargin = typedArray.getDimension(26, -1.0f);
        this.mSuffixSecondRightMargin = typedArray.getDimension(27, -1.0f);
        this.mSuffixMillisecondLeftMargin = typedArray.getDimension(21, -1.0f);
        this.mHasSetIsShowDay = typedArray.hasValue(2);
        this.mHasSetIsShowHour = typedArray.hasValue(3);
        initTempSuffixMargin();
        if (!this.isShowDay && !this.isShowHour && !this.isShowMinute) {
            this.isShowSecond = true;
        }
        if (this.isShowSecond) {
            return;
        }
        this.isShowMillisecond = false;
    }

    public void initialize() {
        initSuffixBase();
        initPaint();
        initSuffix();
        if (!this.isShowSecond) {
            this.isShowMillisecond = false;
        }
        initTimeTextBaseInfo();
    }

    private void initSuffixBase() {
        this.hasSetSuffixDay = !TextUtils.isEmpty(this.mSuffixDay);
        this.hasSetSuffixHour = !TextUtils.isEmpty(this.mSuffixHour);
        this.hasSetSuffixMinute = !TextUtils.isEmpty(this.mSuffixMinute);
        this.hasSetSuffixSecond = !TextUtils.isEmpty(this.mSuffixSecond);
        boolean z = !TextUtils.isEmpty(this.mSuffixMillisecond);
        this.hasSetSuffixMillisecond = z;
        if ((this.isShowDay && this.hasSetSuffixDay) || ((this.isShowHour && this.hasSetSuffixHour) || ((this.isShowMinute && this.hasSetSuffixMinute) || ((this.isShowSecond && this.hasSetSuffixSecond) || (this.isShowMillisecond && z))))) {
            this.hasCustomSomeSuffix = true;
        }
        this.mTempSuffixMinute = this.mSuffixMinute;
        this.mTempSuffixSecond = this.mSuffixSecond;
    }

    public void initPaint() {
        Paint paint = new Paint(1);
        this.mTimeTextPaint = paint;
        paint.setColor(this.mTimeTextColor);
        this.mTimeTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTimeTextPaint.setTextSize(this.mTimeTextSize);
        if (this.isTimeTextBold) {
            this.mTimeTextPaint.setFakeBoldText(true);
        }
        Paint paint2 = new Paint(1);
        this.mSuffixTextPaint = paint2;
        paint2.setColor(this.mSuffixTextColor);
        this.mSuffixTextPaint.setTextSize(this.mSuffixTextSize);
        if (this.isSuffixTextBold) {
            this.mSuffixTextPaint.setFakeBoldText(true);
        }
        Paint paint3 = new Paint();
        this.mMeasureHourWidthPaint = paint3;
        paint3.setTextSize(this.mTimeTextSize);
        if (this.isTimeTextBold) {
            this.mMeasureHourWidthPaint.setFakeBoldText(true);
        }
    }

    private void initSuffix() {
        boolean z;
        float f;
        float measureText = this.mSuffixTextPaint.measureText(DEFAULT_SUFFIX);
        if (TextUtils.isEmpty(this.mSuffix)) {
            z = true;
            f = 0.0f;
        } else {
            f = this.mSuffixTextPaint.measureText(this.mSuffix);
            z = false;
        }
        if (!this.isShowDay) {
            this.mSuffixDayTextWidth = 0.0f;
        } else if (this.hasSetSuffixDay) {
            this.mSuffixDayTextWidth = this.mSuffixTextPaint.measureText(this.mSuffixDay);
        } else if (!z) {
            this.mSuffixDay = this.mSuffix;
            this.mSuffixDayTextWidth = f;
        } else if (!this.hasCustomSomeSuffix) {
            this.mSuffixDay = DEFAULT_SUFFIX;
            this.mSuffixDayTextWidth = measureText;
        }
        if (!this.isShowHour) {
            this.mSuffixHourTextWidth = 0.0f;
        } else if (this.hasSetSuffixHour) {
            this.mSuffixHourTextWidth = this.mSuffixTextPaint.measureText(this.mSuffixHour);
        } else if (!z) {
            this.mSuffixHour = this.mSuffix;
            this.mSuffixHourTextWidth = f;
        } else if (!this.hasCustomSomeSuffix) {
            this.mSuffixHour = DEFAULT_SUFFIX;
            this.mSuffixHourTextWidth = measureText;
        }
        if (!this.isShowMinute) {
            this.mSuffixMinuteTextWidth = 0.0f;
        } else if (this.hasSetSuffixMinute) {
            this.mSuffixMinuteTextWidth = this.mSuffixTextPaint.measureText(this.mSuffixMinute);
        } else if (!this.isShowSecond) {
            this.mSuffixMinuteTextWidth = 0.0f;
        } else if (!z) {
            this.mSuffixMinute = this.mSuffix;
            this.mSuffixMinuteTextWidth = f;
        } else if (!this.hasCustomSomeSuffix) {
            this.mSuffixMinute = DEFAULT_SUFFIX;
            this.mSuffixMinuteTextWidth = measureText;
        }
        if (!this.isShowSecond) {
            this.mSuffixSecondTextWidth = 0.0f;
        } else if (this.hasSetSuffixSecond) {
            this.mSuffixSecondTextWidth = this.mSuffixTextPaint.measureText(this.mSuffixSecond);
        } else if (!this.isShowMillisecond) {
            this.mSuffixSecondTextWidth = 0.0f;
        } else if (!z) {
            this.mSuffixSecond = this.mSuffix;
            this.mSuffixSecondTextWidth = f;
        } else if (!this.hasCustomSomeSuffix) {
            this.mSuffixSecond = DEFAULT_SUFFIX;
            this.mSuffixSecondTextWidth = measureText;
        }
        if (this.isShowMillisecond && this.hasCustomSomeSuffix && this.hasSetSuffixMillisecond) {
            this.mSuffixMillisecondTextWidth = this.mSuffixTextPaint.measureText(this.mSuffixMillisecond);
        } else {
            this.mSuffixMillisecondTextWidth = 0.0f;
        }
        initSuffixMargin();
    }

    private void initSuffixMargin() {
        int dp2px = Utils.dp2px(this.mContext, DEFAULT_SUFFIX_LR_MARGIN);
        float f = this.mSuffixLRMargin;
        boolean z = f < 0.0f;
        if (!this.isShowDay || this.mSuffixDayTextWidth <= 0.0f) {
            this.mSuffixDayLeftMargin = 0.0f;
            this.mSuffixDayRightMargin = 0.0f;
        } else {
            if (this.mSuffixDayLeftMargin < 0.0f) {
                if (z) {
                    this.mSuffixDayLeftMargin = dp2px;
                } else {
                    this.mSuffixDayLeftMargin = f;
                }
            }
            if (this.mSuffixDayRightMargin < 0.0f) {
                if (z) {
                    this.mSuffixDayRightMargin = dp2px;
                } else {
                    this.mSuffixDayRightMargin = f;
                }
            }
        }
        if (!this.isShowHour || this.mSuffixHourTextWidth <= 0.0f) {
            this.mSuffixHourLeftMargin = 0.0f;
            this.mSuffixHourRightMargin = 0.0f;
        } else {
            if (this.mSuffixHourLeftMargin < 0.0f) {
                if (z) {
                    this.mSuffixHourLeftMargin = dp2px;
                } else {
                    this.mSuffixHourLeftMargin = f;
                }
            }
            if (this.mSuffixHourRightMargin < 0.0f) {
                if (z) {
                    this.mSuffixHourRightMargin = dp2px;
                } else {
                    this.mSuffixHourRightMargin = f;
                }
            }
        }
        if (!this.isShowMinute || this.mSuffixMinuteTextWidth <= 0.0f) {
            this.mSuffixMinuteLeftMargin = 0.0f;
            this.mSuffixMinuteRightMargin = 0.0f;
        } else {
            if (this.mSuffixMinuteLeftMargin < 0.0f) {
                if (z) {
                    this.mSuffixMinuteLeftMargin = dp2px;
                } else {
                    this.mSuffixMinuteLeftMargin = f;
                }
            }
            if (!this.isShowSecond) {
                this.mSuffixMinuteRightMargin = 0.0f;
            } else if (this.mSuffixMinuteRightMargin < 0.0f) {
                if (z) {
                    this.mSuffixMinuteRightMargin = dp2px;
                } else {
                    this.mSuffixMinuteRightMargin = f;
                }
            }
        }
        if (!this.isShowSecond) {
            this.mSuffixSecondLeftMargin = 0.0f;
            this.mSuffixSecondRightMargin = 0.0f;
            this.mSuffixMillisecondLeftMargin = 0.0f;
            return;
        }
        if (this.mSuffixSecondTextWidth > 0.0f) {
            if (this.mSuffixSecondLeftMargin < 0.0f) {
                if (z) {
                    this.mSuffixSecondLeftMargin = dp2px;
                } else {
                    this.mSuffixSecondLeftMargin = f;
                }
            }
            if (!this.isShowMillisecond) {
                this.mSuffixSecondRightMargin = 0.0f;
            } else if (this.mSuffixSecondRightMargin < 0.0f) {
                if (z) {
                    this.mSuffixSecondRightMargin = dp2px;
                } else {
                    this.mSuffixSecondRightMargin = f;
                }
            }
        } else {
            this.mSuffixSecondLeftMargin = 0.0f;
            this.mSuffixSecondRightMargin = 0.0f;
        }
        if (!this.isShowMillisecond || this.mSuffixMillisecondTextWidth <= 0.0f) {
            this.mSuffixMillisecondLeftMargin = 0.0f;
        } else if (this.mSuffixMillisecondLeftMargin < 0.0f) {
            if (z) {
                this.mSuffixMillisecondLeftMargin = dp2px;
            } else {
                this.mSuffixMillisecondLeftMargin = f;
            }
        }
    }

    public void initTimeTextBaseInfo() {
        Rect rect = new Rect();
        this.mTimeTextPaint.getTextBounds("00", 0, 2, rect);
        this.mTimeTextWidth = rect.width();
        this.mTimeTextHeight = rect.height();
        this.mTimeTextBottom = rect.bottom;
    }

    private void initTimeTextBaseline(int i, int i2, int i3) {
        if (i2 == i3) {
            this.mTimeTextBaseline = ((i / 2) + (this.mTimeTextHeight / 2.0f)) - this.mTimeTextBottom;
        } else {
            this.mTimeTextBaseline = ((i - (i - i2)) + this.mTimeTextHeight) - this.mTimeTextBottom;
        }
        if (this.isShowDay && this.mSuffixDayTextWidth > 0.0f) {
            this.mSuffixDayTextBaseline = getSuffixTextBaseLine(this.mSuffixDay);
        }
        if (this.isShowHour && this.mSuffixHourTextWidth > 0.0f) {
            this.mSuffixHourTextBaseline = getSuffixTextBaseLine(this.mSuffixHour);
        }
        if (this.isShowMinute && this.mSuffixMinuteTextWidth > 0.0f) {
            this.mSuffixMinuteTextBaseline = getSuffixTextBaseLine(this.mSuffixMinute);
        }
        if (this.mSuffixSecondTextWidth > 0.0f) {
            this.mSuffixSecondTextBaseline = getSuffixTextBaseLine(this.mSuffixSecond);
        }
        if (!this.isShowMillisecond || this.mSuffixMillisecondTextWidth <= 0.0f) {
            return;
        }
        this.mSuffixMillisecondTextBaseline = getSuffixTextBaseLine(this.mSuffixMillisecond);
    }

    private float getSuffixTextBaseLine(String str) {
        float f;
        int i;
        Rect rect = new Rect();
        this.mSuffixTextPaint.getTextBounds(str, 0, str.length(), rect);
        int i2 = this.mSuffixGravity;
        if (i2 == 0) {
            f = this.mTimeTextBaseline - this.mTimeTextHeight;
            i = rect.top;
        } else if (i2 != 2) {
            return (this.mTimeTextBaseline - (this.mTimeTextHeight / 2.0f)) + (rect.height() / 2);
        } else {
            f = this.mTimeTextBaseline;
            i = rect.bottom;
        }
        return f - i;
    }

    public final float getAllContentWidthBase(float f) {
        float f2 = this.mSuffixDayTextWidth + this.mSuffixHourTextWidth + this.mSuffixMinuteTextWidth + this.mSuffixSecondTextWidth + this.mSuffixMillisecondTextWidth + this.mSuffixDayLeftMargin + this.mSuffixDayRightMargin + this.mSuffixHourLeftMargin + this.mSuffixHourRightMargin + this.mSuffixMinuteLeftMargin + this.mSuffixMinuteRightMargin + this.mSuffixSecondLeftMargin + this.mSuffixSecondRightMargin + this.mSuffixMillisecondLeftMargin;
        if (this.isConvertDaysToHours) {
            f2 += getDayAndHourContentWidth();
        } else if (this.isShowHour) {
            f2 += f;
        }
        if (this.isShowMinute) {
            f2 += f;
        }
        if (this.isShowSecond) {
            f2 += f;
        }
        return this.isShowMillisecond ? f2 + f : f2;
    }

    private float getDayAndHourContentWidth() {
        Rect rect = new Rect();
        float f = 0.0f;
        if (this.isShowDay) {
            String formatNum = Utils.formatNum(this.mDay);
            this.mTimeTextPaint.getTextBounds(formatNum, 0, formatNum.length(), rect);
            float width = rect.width();
            this.mDayTimeTextWidth = width;
            f = 0.0f + width;
        }
        if (this.isShowHour) {
            String formatNum2 = Utils.formatNum(this.mHour);
            this.mMeasureHourWidthPaint.getTextBounds(formatNum2, 0, formatNum2.length(), rect);
            float width2 = rect.width();
            this.mHourTimeTextWidth = width2;
            return f + width2;
        }
        return f;
    }

    public int getAllContentWidth() {
        float f;
        float allContentWidthBase = getAllContentWidthBase(this.mTimeTextWidth);
        if (!this.isConvertDaysToHours && this.isShowDay) {
            if (this.isDayLargeNinetyNine) {
                Rect rect = new Rect();
                String valueOf = String.valueOf(this.mDay);
                this.mTimeTextPaint.getTextBounds(valueOf, 0, valueOf.length(), rect);
                f = rect.width();
                this.mDayTimeTextWidth = f;
            } else {
                f = this.mTimeTextWidth;
                this.mDayTimeTextWidth = f;
            }
            allContentWidthBase += f;
        }
        return (int) Math.ceil(allContentWidthBase);
    }

    public void onMeasure(View view, int i, int i2, int i3, int i4) {
        initTimeTextBaseline(i2, view.getPaddingTop(), view.getPaddingBottom());
        this.mLeftPaddingSize = view.getPaddingLeft() == view.getPaddingRight() ? (i - i3) / 2 : view.getPaddingLeft();
    }

    public void onDraw(Canvas canvas) {
        float f;
        if (this.mSecond == 0 && this.mHour == 0 && this.mDay == 0 && this.mMillisecond < 1000 && this.mMinute == 0) {
            canvas.drawText("0S", this.mLeftPaddingSize + (this.mTimeTextWidth / 2.0f), this.mTimeTextBaseline, this.mTimeTextPaint);
            return;
        }
        if (this.isShowDay) {
            canvas.drawText(Utils.formatNum(this.mDay), this.mLeftPaddingSize + (this.mDayTimeTextWidth / 2.0f), this.mTimeTextBaseline, this.mTimeTextPaint);
            if (this.mSuffixDayTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixDay, this.mLeftPaddingSize + this.mDayTimeTextWidth + this.mSuffixDayLeftMargin, this.mSuffixDayTextBaseline, this.mSuffixTextPaint);
            }
            f = this.mLeftPaddingSize + this.mDayTimeTextWidth + this.mSuffixDayTextWidth + this.mSuffixDayLeftMargin + this.mSuffixDayRightMargin;
        } else {
            f = this.mLeftPaddingSize;
        }
        if (this.isShowHour) {
            float f2 = this.isConvertDaysToHours ? this.mHourTimeTextWidth : this.mTimeTextWidth;
            canvas.drawText(Utils.formatNum(this.mHour), (f2 / 2.0f) + f, this.mTimeTextBaseline, this.mTimeTextPaint);
            if (this.mSuffixHourTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixHour, f + f2 + this.mSuffixHourLeftMargin, this.mSuffixHourTextBaseline, this.mSuffixTextPaint);
            }
            f = f + f2 + this.mSuffixHourTextWidth + this.mSuffixHourLeftMargin + this.mSuffixHourRightMargin;
        }
        if (this.isShowMinute) {
            canvas.drawText(Utils.formatNum(this.mMinute), (this.mTimeTextWidth / 2.0f) + f, this.mTimeTextBaseline, this.mTimeTextPaint);
            if (this.mSuffixMinuteTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixMinute, this.mTimeTextWidth + f + this.mSuffixMinuteLeftMargin, this.mSuffixMinuteTextBaseline, this.mSuffixTextPaint);
            }
            f = f + this.mTimeTextWidth + this.mSuffixMinuteTextWidth + this.mSuffixMinuteLeftMargin + this.mSuffixMinuteRightMargin;
        }
        if (this.isShowSecond) {
            LogUtils.i("BaseCountdown", "mSecond: " + this.mSecond + " mHour: " + this.mHour + "  mDay: " + this.mDay + "  mMillisecond:  " + this.mMillisecond + "  mMinute:  " + this.mMinute);
            int i = this.mSecond;
            if (i == 0 && this.mHour == 0 && this.mDay == 0 && this.mMillisecond < 1000 && this.mMinute == 0) {
                canvas.drawText(Utils.formatZero(i), (this.mTimeTextWidth / 2.0f) + f, this.mTimeTextBaseline, this.mTimeTextPaint);
            } else {
                canvas.drawText(Utils.formatNum(i), (this.mTimeTextWidth / 2.0f) + f, this.mTimeTextBaseline, this.mTimeTextPaint);
            }
            if (this.mSuffixSecondTextWidth > 0.0f) {
                canvas.drawText(this.mSuffixSecond, this.mTimeTextWidth + f + this.mSuffixSecondLeftMargin, this.mSuffixSecondTextBaseline, this.mSuffixTextPaint);
            }
            if (this.isShowMillisecond) {
                float f3 = f + this.mTimeTextWidth + this.mSuffixSecondTextWidth + this.mSuffixSecondLeftMargin + this.mSuffixSecondRightMargin;
                canvas.drawText(Utils.formatMillisecond(this.mMillisecond), (this.mTimeTextWidth / 2.0f) + f3, this.mTimeTextBaseline, this.mTimeTextPaint);
                if (this.mSuffixMillisecondTextWidth > 0.0f) {
                    canvas.drawText(this.mSuffixMillisecond, f3 + this.mTimeTextWidth + this.mSuffixMillisecondLeftMargin, this.mSuffixMillisecondTextBaseline, this.mSuffixTextPaint);
                }
            }
        }
    }

    public boolean handlerAutoShowTime() {
        if (!this.mHasSetIsShowDay) {
            boolean z = this.isShowDay;
            if (!z && this.mDay > 0) {
                if (!this.mHasSetIsShowHour) {
                    refTimeShow(true, true, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                    return true;
                }
                refTimeShow(true, this.isShowHour, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                return true;
            } else if (z && this.mDay == 0) {
                refTimeShow(false, this.isShowHour, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                return true;
            } else if (!this.mHasSetIsShowHour) {
                boolean z2 = this.isShowHour;
                if (!z2 && (this.mDay > 0 || this.mHour > 0)) {
                    refTimeShow(z, true, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                    return true;
                } else if (z2 && this.mDay == 0 && this.mHour == 0) {
                    refTimeShow(false, false, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                    return true;
                }
            }
        } else if (!this.mHasSetIsShowHour) {
            boolean z3 = this.isShowHour;
            if (!z3 && (this.mDay > 0 || this.mHour > 0)) {
                refTimeShow(this.isShowDay, true, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                return true;
            } else if (z3 && this.mDay == 0 && this.mHour == 0) {
                refTimeShow(this.isShowDay, false, this.isShowMinute, this.isShowSecond, this.isShowMillisecond);
                return true;
            }
        }
        return false;
    }

    public void reLayout() {
        initSuffix();
        initTimeTextBaseInfo();
    }

    public void setTimeTextSize(float f) {
        if (f > 0.0f) {
            float sp2px = Utils.sp2px(this.mContext, f);
            this.mTimeTextSize = sp2px;
            this.mTimeTextPaint.setTextSize(sp2px);
        }
    }

    public void setTimeTextColor(int i) {
        this.mTimeTextColor = i;
        this.mTimeTextPaint.setColor(i);
    }

    public void setTimeTextBold(boolean z) {
        this.isTimeTextBold = z;
        this.mTimeTextPaint.setFakeBoldText(z);
    }

    public void setSuffixTextSize(float f) {
        if (f > 0.0f) {
            float sp2px = Utils.sp2px(this.mContext, f);
            this.mSuffixTextSize = sp2px;
            this.mSuffixTextPaint.setTextSize(sp2px);
        }
    }

    public void setSuffixTextColor(int i) {
        this.mSuffixTextColor = i;
        this.mSuffixTextPaint.setColor(i);
    }

    public void setSuffixTextBold(boolean z) {
        this.isSuffixTextBold = z;
        this.mSuffixTextPaint.setFakeBoldText(z);
    }

    public void setSuffix(String str) {
        this.mSuffix = str;
        setSuffix(str, str, str, str, str);
    }

    public boolean setSuffix(String str, String str2, String str3, String str4, String str5) {
        boolean z;
        boolean z2 = true;
        if (str != null) {
            this.mSuffixDay = str;
            z = true;
        } else {
            z = false;
        }
        if (str2 != null) {
            this.mSuffixHour = str2;
            z = true;
        }
        if (str3 != null) {
            this.mSuffixMinute = str3;
            z = true;
        }
        if (str4 != null) {
            this.mSuffixSecond = str4;
            z = true;
        }
        if (str5 != null) {
            this.mSuffixMillisecond = str5;
        } else {
            z2 = z;
        }
        if (z2) {
            initSuffixBase();
        }
        return z2;
    }

    public void setSuffixLRMargin(float f) {
        this.mSuffixLRMargin = Utils.dp2px(this.mContext, f);
        setSuffixMargin(Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin), Float.valueOf(this.mSuffixDayLeftMargin));
    }

    public boolean setSuffixMargin(Float f, Float f2, Float f3, Float f4, Float f5, Float f6, Float f7, Float f8, Float f9) {
        boolean z;
        boolean z2 = true;
        if (f != null) {
            this.mSuffixDayLeftMargin = Utils.dp2px(this.mContext, f.floatValue());
            z = true;
        } else {
            z = false;
        }
        if (f2 != null) {
            this.mSuffixDayRightMargin = Utils.dp2px(this.mContext, f2.floatValue());
            z = true;
        }
        if (f3 != null) {
            this.mSuffixHourLeftMargin = Utils.dp2px(this.mContext, f3.floatValue());
            z = true;
        }
        if (f4 != null) {
            this.mSuffixHourRightMargin = Utils.dp2px(this.mContext, f4.floatValue());
            z = true;
        }
        if (f5 != null) {
            this.mSuffixMinuteLeftMargin = Utils.dp2px(this.mContext, f5.floatValue());
            z = true;
        }
        if (f6 != null) {
            this.mSuffixMinuteRightMargin = Utils.dp2px(this.mContext, f6.floatValue());
            z = true;
        }
        if (f7 != null) {
            this.mSuffixSecondLeftMargin = Utils.dp2px(this.mContext, f7.floatValue());
            z = true;
        }
        if (f8 != null) {
            this.mSuffixSecondRightMargin = Utils.dp2px(this.mContext, f8.floatValue());
            z = true;
        }
        if (f9 != null) {
            this.mSuffixMillisecondLeftMargin = Utils.dp2px(this.mContext, f9.floatValue());
        } else {
            z2 = z;
        }
        if (z2) {
            initTempSuffixMargin();
        }
        return z2;
    }
}
