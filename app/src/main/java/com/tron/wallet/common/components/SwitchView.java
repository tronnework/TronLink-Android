package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.SentryUtil;
public class SwitchView extends View {
    private static final int STATE_SWITCH_OFF = 1;
    private static final int STATE_SWITCH_OFF2 = 2;
    private static final int STATE_SWITCH_ON = 4;
    private static final int STATE_SWITCH_ON2 = 3;
    protected float animationSpeed;
    private float bAnim;
    private float bLeft;
    private float bOff2LeftX;
    private float bOffLeftX;
    private float bOffset;
    private float bOn2LeftX;
    private float bOnLeftX;
    private final Path bPath;
    private float bRadius;
    private final RectF bRectF;
    private float bRight;
    private float bStrokeWidth;
    private float bWidth;
    protected int colorOff;
    protected int colorOffDark;
    protected int colorPrimary;
    protected int colorPrimaryDark;
    protected int colorShadow;
    protected boolean hasShadow;
    private final AccelerateInterpolator interpolator;
    private boolean isCanVisibleDrawing;
    protected boolean isOpened;
    private int lastState;
    private OnStateChangedListener listener;
    private View.OnClickListener mOnClickListener;
    private final Paint paint;
    protected float ratioAspect;
    private float sAnim;
    private float sCenterX;
    private float sCenterY;
    private final Path sPath;
    private float sRight;
    private float sScale;
    private RadialGradient shadowGradient;
    private float shadowReservedHeight;
    private int state;

    public interface OnStateChangedListener {
        void toggleToOff(SwitchView switchView);

        void toggleToOn(SwitchView switchView);
    }

    private float calcBTranslate(float f) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int i = this.state;
        int i2 = i - this.lastState;
        if (i2 != -3) {
            if (i2 != -2) {
                if (i2 != -1) {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            if (i == 4) {
                                f5 = this.bOnLeftX;
                                f6 = this.bOffLeftX;
                            } else {
                                if (i == 3) {
                                    f5 = this.bOn2LeftX;
                                    f6 = this.bOffLeftX;
                                }
                                f4 = 0.0f;
                            }
                            f4 = f5 - ((f5 - f6) * f);
                        } else if (i2 == 3) {
                            f5 = this.bOnLeftX;
                            f6 = this.bOffLeftX;
                            f4 = f5 - ((f5 - f6) * f);
                        } else if (i == 1) {
                            f4 = this.bOffLeftX;
                        } else {
                            if (i == 4) {
                                f4 = this.bOnLeftX;
                            }
                            f4 = 0.0f;
                        }
                    } else if (i == 2) {
                        f4 = this.bOffLeftX;
                    } else {
                        if (i == 4) {
                            f5 = this.bOnLeftX;
                            f6 = this.bOn2LeftX;
                            f4 = f5 - ((f5 - f6) * f);
                        }
                        f4 = 0.0f;
                    }
                } else if (i == 3) {
                    f2 = this.bOn2LeftX;
                    f3 = this.bOnLeftX;
                } else {
                    if (i == 1) {
                        f4 = this.bOffLeftX;
                    }
                    f4 = 0.0f;
                }
            } else if (i == 1) {
                f2 = this.bOffLeftX;
                f3 = this.bOn2LeftX;
            } else {
                if (i == 2) {
                    f2 = this.bOff2LeftX;
                    f3 = this.bOnLeftX;
                }
                f4 = 0.0f;
            }
            return f4 - this.bOffLeftX;
        }
        f2 = this.bOffLeftX;
        f3 = this.bOnLeftX;
        f4 = f2 + ((f3 - f2) * f);
        return f4 - this.bOffLeftX;
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.interpolator = new AccelerateInterpolator(2.0f);
        this.paint = new Paint();
        this.sPath = new Path();
        this.bPath = new Path();
        this.bRectF = new RectF();
        this.ratioAspect = 0.68f;
        this.animationSpeed = 0.1f;
        this.isCanVisibleDrawing = false;
        this.listener = new OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView switchView) {
                toggleSwitch(true);
            }

            @Override
            public void toggleToOff(SwitchView switchView) {
                toggleSwitch(false);
            }
        };
        setLayerType(1, null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwitchView);
        this.colorPrimary = obtainStyledAttributes.getColor(4, -11806877);
        this.colorPrimaryDark = obtainStyledAttributes.getColor(5, -12925358);
        this.colorOff = obtainStyledAttributes.getColor(2, -1842205);
        this.colorOffDark = obtainStyledAttributes.getColor(3, -4210753);
        this.colorShadow = obtainStyledAttributes.getColor(7, -13421773);
        this.ratioAspect = obtainStyledAttributes.getFloat(6, 0.68f);
        this.hasShadow = obtainStyledAttributes.getBoolean(0, true);
        boolean z = obtainStyledAttributes.getBoolean(1, false);
        this.isOpened = z;
        int i = z ? 4 : 1;
        this.state = i;
        this.lastState = i;
        obtainStyledAttributes.recycle();
        if (this.colorPrimary == -11806877 && this.colorPrimaryDark == -12925358) {
            try {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16843827, typedValue, true);
                if (typedValue.data > 0) {
                    this.colorPrimary = typedValue.data;
                }
                TypedValue typedValue2 = new TypedValue();
                context.getTheme().resolveAttribute(16843828, typedValue2, true);
                if (typedValue2.data > 0) {
                    this.colorPrimaryDark = typedValue2.data;
                }
            } catch (Exception e) {
                SentryUtil.captureException(e);
            }
        }
    }

    public void setColor(int i, int i2) {
        setColor(i, i2, this.colorOff, this.colorOffDark);
    }

    public void setColor(int i, int i2, int i3, int i4) {
        setColor(i, i2, i3, i4, this.colorShadow);
    }

    public void setColor(int i, int i2, int i3, int i4, int i5) {
        this.colorPrimary = i;
        this.colorPrimaryDark = i2;
        this.colorOff = i3;
        this.colorOffDark = i4;
        this.colorShadow = i5;
        invalidate();
    }

    public void setShadow(boolean z) {
        this.hasShadow = z;
        invalidate();
    }

    public void setOpened(boolean z) {
        int i = z ? 4 : 1;
        if (i == this.state) {
            return;
        }
        refreshState(i);
    }

    public void toggleSwitch(boolean z) {
        int i = z ? 4 : 1;
        int i2 = this.state;
        if (i == i2) {
            return;
        }
        if ((i == 4 && (i2 == 1 || i2 == 2)) || (i == 1 && (i2 == 4 || i2 == 3))) {
            this.sAnim = 1.0f;
        }
        this.bAnim = 1.0f;
        refreshState(i);
    }

    private void refreshState(int i) {
        boolean z = this.isOpened;
        if (!z && i == 4) {
            this.isOpened = true;
        } else if (z && i == 1) {
            this.isOpened = false;
        }
        this.lastState = this.state;
        this.state = i;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (mode != MeasureSpec.AT_MOST) {
            int paddingLeft = ((int) ((getResources().getDisplayMetrics().density * 56.0f) + 0.5f)) + getPaddingLeft() + getPaddingRight();
            size = mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
        }
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 != MeasureSpec.AT_MOST) {
            int paddingTop = ((int) (size * this.ratioAspect)) + getPaddingTop() + getPaddingBottom();
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(paddingTop, size2) : paddingTop;
        }
        setMeasuredDimension(size, size2);
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int width;
        int paddingTop;
        int height;
        super.onSizeChanged(i, i2, i3, i4);
        boolean z = i > getPaddingLeft() + getPaddingRight() && i2 > getPaddingTop() + getPaddingBottom();
        this.isCanVisibleDrawing = z;
        if (z) {
            int paddingLeft2 = (i - getPaddingLeft()) - getPaddingRight();
            int paddingTop2 = (i2 - getPaddingTop()) - getPaddingBottom();
            float f = paddingLeft2;
            float f2 = this.ratioAspect;
            float f3 = paddingTop2;
            if (f * f2 < f3) {
                paddingLeft = getPaddingLeft();
                width = i - getPaddingRight();
                int i5 = ((int) (f3 - (f * this.ratioAspect))) / 2;
                paddingTop = getPaddingTop() + i5;
                height = (getHeight() - getPaddingBottom()) - i5;
            } else {
                int i6 = ((int) (f - (f3 / f2))) / 2;
                paddingLeft = getPaddingLeft() + i6;
                width = (getWidth() - getPaddingRight()) - i6;
                paddingTop = getPaddingTop();
                height = getHeight() - getPaddingBottom();
            }
            float f4 = (int) ((height - paddingTop) * 0.07f);
            this.shadowReservedHeight = f4;
            float f5 = paddingLeft;
            float f6 = paddingTop + f4;
            float f7 = width;
            this.sRight = f7;
            float f8 = height - f4;
            float f9 = f8 - f6;
            this.sCenterX = (f7 + f5) / 2.0f;
            float f10 = (f8 + f6) / 2.0f;
            this.sCenterY = f10;
            this.bLeft = f5;
            this.bWidth = f9;
            this.bRight = f5 + f9;
            float f11 = f9 / 2.0f;
            float f12 = 0.95f * f11;
            this.bRadius = f12;
            float f13 = 0.2f * f12;
            this.bOffset = f13;
            float f14 = (f11 - f12) * 2.0f;
            this.bStrokeWidth = f14;
            float f15 = f7 - f9;
            this.bOnLeftX = f15;
            this.bOn2LeftX = f15 - f13;
            this.bOffLeftX = f5;
            this.bOff2LeftX = f13 + f5;
            this.sScale = 1.0f - (f14 / f9);
            this.sPath.reset();
            RectF rectF = new RectF();
            rectF.top = f6;
            rectF.bottom = f8;
            rectF.left = f5;
            rectF.right = f5 + f9;
            this.sPath.arcTo(rectF, 90.0f, 180.0f);
            rectF.left = this.sRight - f9;
            rectF.right = this.sRight;
            this.sPath.arcTo(rectF, 270.0f, 180.0f);
            this.sPath.close();
            this.bRectF.left = this.bLeft;
            this.bRectF.right = this.bRight;
            this.bRectF.top = f6 + (this.bStrokeWidth / 2.0f);
            this.bRectF.bottom = f8 - (this.bStrokeWidth / 2.0f);
            float f16 = (this.bRight + this.bLeft) / 2.0f;
            int i7 = this.colorShadow;
            int i8 = (i7 >> 16) & 255;
            int i9 = (i7 >> 8) & 255;
            int i10 = i7 & 255;
            this.shadowGradient = new RadialGradient(f16, f10, this.bRadius, Color.argb(200, i8, i9, i10), Color.argb(25, i8, i9, i10), Shader.TileMode.CLAMP);
        }
    }

    private void calcBPath(float f) {
        this.bPath.reset();
        this.bRectF.left = this.bLeft + (this.bStrokeWidth / 2.0f);
        this.bRectF.right = this.bRight - (this.bStrokeWidth / 2.0f);
        this.bPath.arcTo(this.bRectF, 90.0f, 180.0f);
        this.bRectF.left = this.bLeft + (this.bOffset * f) + (this.bStrokeWidth / 2.0f);
        this.bRectF.right = (this.bRight + (f * this.bOffset)) - (this.bStrokeWidth / 2.0f);
        this.bPath.arcTo(this.bRectF, 270.0f, 180.0f);
        this.bPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isCanVisibleDrawing) {
            boolean z = true;
            this.paint.setAntiAlias(true);
            int i = this.state;
            if (i != 4 && i != 3) {
                z = false;
            }
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(z ? this.colorPrimary : this.colorOff);
            canvas.drawPath(this.sPath, this.paint);
            float f = this.sAnim;
            float f2 = this.animationSpeed;
            float f3 = f - f2 > 0.0f ? f - f2 : 0.0f;
            this.sAnim = f3;
            float f4 = this.bAnim;
            this.bAnim = f4 - f2 > 0.0f ? f4 - f2 : 0.0f;
            float interpolation = this.interpolator.getInterpolation(f3);
            float interpolation2 = this.interpolator.getInterpolation(this.bAnim);
            float f5 = this.sScale * (z ? interpolation : 1.0f - interpolation);
            float f6 = (this.sRight - this.sCenterX) - this.bRadius;
            if (z) {
                interpolation = 1.0f - interpolation;
            }
            canvas.save();
            canvas.scale(f5, f5, this.sCenterX + (f6 * interpolation), this.sCenterY);
            this.paint.setColor(-4276546);
            canvas.drawPath(this.sPath, this.paint);
            canvas.restore();
            canvas.save();
            canvas.translate(calcBTranslate(interpolation2), this.shadowReservedHeight);
            int i2 = this.state;
            if (i2 == 3 || i2 == 2) {
                interpolation2 = 1.0f - interpolation2;
            }
            calcBPath(interpolation2);
            if (this.hasShadow) {
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setShader(this.shadowGradient);
                canvas.drawPath(this.bPath, this.paint);
                this.paint.setShader(null);
            }
            canvas.translate(0.0f, -this.shadowReservedHeight);
            float f7 = this.bWidth;
            canvas.scale(0.98f, 0.98f, f7 / 2.0f, f7 / 2.0f);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(-1);
            canvas.drawPath(this.bPath, this.paint);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(this.bStrokeWidth * 0.5f);
            this.paint.setColor(z ? this.colorPrimaryDark : this.colorOffDark);
            canvas.drawPath(this.bPath, this.paint);
            canvas.restore();
            this.paint.reset();
            if (this.sAnim > 0.0f || this.bAnim > 0.0f) {
                invalidate();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = this.state;
        if ((i == 4 || i == 1) && this.sAnim * this.bAnim == 0.0f) {
            int action = motionEvent.getAction();
            if (action == 0) {
                return true;
            }
            if (action == 1) {
                int i2 = this.state;
                this.lastState = i2;
                this.bAnim = 1.0f;
                if (i2 == 1) {
                    refreshState(2);
                    this.listener.toggleToOn(this);
                } else if (i2 == 4) {
                    refreshState(3);
                    this.listener.toggleToOff(this);
                }
                View.OnClickListener onClickListener = this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(this);
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.mOnClickListener = onClickListener;
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        if (onStateChangedListener == null) {
            throw new IllegalArgumentException("empty listener");
        }
        this.listener = onStateChangedListener;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.isOpened = this.isOpened;
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        boolean z = savedState.isOpened;
        this.isOpened = z;
        this.state = z ? 4 : 1;
        invalidate();
    }

    public static final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private boolean isOpened;

        @Override
        public int describeContents() {
            return 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.isOpened = 1 == parcel.readInt();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isOpened ? 1 : 0);
        }
    }
}
