package com.tron.wallet.common.components;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import com.tron.wallet.R;
public class SwitchButton extends View implements Checkable {
    private final int ANIMATE_STATE_DRAGING;
    private final int ANIMATE_STATE_NONE;
    private final int ANIMATE_STATE_PENDING_DRAG;
    private final int ANIMATE_STATE_PENDING_RESET;
    private final int ANIMATE_STATE_PENDING_SETTLE;
    private final int ANIMATE_STATE_SWITCH;
    private ViewState afterState;
    private int animateState;
    private Animator.AnimatorListener animatorListener;
    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
    private final ArgbEvaluator argbEvaluator;
    private int background;
    private ViewState beforeState;
    private int borderWidth;
    private float bottom;
    private float buttonMaxX;
    private float buttonMinX;
    private Paint buttonPaint;
    private float buttonRadius;
    private float centerX;
    private float centerY;
    private int checkLineColor;
    private float checkLineLength;
    private int checkLineWidth;
    private int checkedButtonColor;
    private int checkedColor;
    private float checkedLineOffsetX;
    private float checkedLineOffsetY;
    private boolean enableEffect;
    private float height;
    private boolean isChecked;
    private boolean isEventBroadcast;
    private boolean isTouchingDown;
    private boolean isUiInited;
    private float left;
    private OnCheckedChangeListener onCheckedChangeListener;
    private Paint paint;
    private Runnable postPendingDrag;
    private RectF rect;
    private float right;
    private int shadowColor;
    private boolean shadowEffect;
    private int shadowOffset;
    private int shadowRadius;
    private boolean showIndicator;
    private float top;
    private long touchDownTime;
    private int uncheckButtonColor;
    private int uncheckCircleColor;
    private float uncheckCircleOffsetX;
    private float uncheckCircleRadius;
    private int uncheckCircleWidth;
    private int uncheckColor;
    private ValueAnimator valueAnimator;
    private float viewRadius;
    private ViewState viewState;
    private float width;
    private static final int DEFAULT_WIDTH = dp2pxInt(58.0f);
    private static final int DEFAULT_HEIGHT = dp2pxInt(36.0f);

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SwitchButton switchButton, boolean z);
    }

    private boolean isDragState() {
        return this.animateState == 2;
    }

    public boolean isInAnimating() {
        return this.animateState != 0;
    }

    private boolean isPendingDragState() {
        int i = this.animateState;
        return i == 1 || i == 3;
    }

    @Override
    public boolean isChecked() {
        return this.isChecked;
    }

    public void setEnableEffect(boolean z) {
        this.enableEffect = z;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public final void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override
    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
    }

    public SwitchButton(Context context) {
        super(context);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() {
            @Override
            public void run() {
                if (isInAnimating()) {
                    return;
                }
                pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i = animateState;
                if (i == 1 || i == 3 || i == 4) {
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkedLineColor), Integer.valueOf(afterState.checkedLineColor))).intValue();
                    viewState.radius = beforeState.radius + ((afterState.radius - beforeState.radius) * floatValue);
                    if (animateState != 1) {
                        viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    }
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkStateColor), Integer.valueOf(afterState.checkStateColor))).intValue();
                } else if (i == 5) {
                    viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    float f = (viewState.buttonX - buttonMinX) / (buttonMaxX - buttonMinX);
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(f, Integer.valueOf(uncheckColor), Integer.valueOf(checkedColor))).intValue();
                    viewState.radius = viewRadius * f;
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(f, 0, Integer.valueOf(checkLineColor))).intValue();
                }
                postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                int i = animateState;
                if (i == 1) {
                    animateState = 2;
                    viewState.checkedLineColor = 0;
                    viewState.radius = viewRadius;
                    postInvalidate();
                } else if (i == 3) {
                    animateState = 0;
                    postInvalidate();
                } else if (i == 4) {
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                } else if (i != 5) {
                } else {
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                }
            }
        };
        init(context, null);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() {
            @Override
            public void run() {
                if (isInAnimating()) {
                    return;
                }
                pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i = animateState;
                if (i == 1 || i == 3 || i == 4) {
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkedLineColor), Integer.valueOf(afterState.checkedLineColor))).intValue();
                    viewState.radius = beforeState.radius + ((afterState.radius - beforeState.radius) * floatValue);
                    if (animateState != 1) {
                        viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    }
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkStateColor), Integer.valueOf(afterState.checkStateColor))).intValue();
                } else if (i == 5) {
                    viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    float f = (viewState.buttonX - buttonMinX) / (buttonMaxX - buttonMinX);
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(f, Integer.valueOf(uncheckColor), Integer.valueOf(checkedColor))).intValue();
                    viewState.radius = viewRadius * f;
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(f, 0, Integer.valueOf(checkLineColor))).intValue();
                }
                postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                int i = animateState;
                if (i == 1) {
                    animateState = 2;
                    viewState.checkedLineColor = 0;
                    viewState.radius = viewRadius;
                    postInvalidate();
                } else if (i == 3) {
                    animateState = 0;
                    postInvalidate();
                } else if (i == 4) {
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                } else if (i != 5) {
                } else {
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                }
            }
        };
        init(context, attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() {
            @Override
            public void run() {
                if (isInAnimating()) {
                    return;
                }
                pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i2 = animateState;
                if (i2 == 1 || i2 == 3 || i2 == 4) {
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkedLineColor), Integer.valueOf(afterState.checkedLineColor))).intValue();
                    viewState.radius = beforeState.radius + ((afterState.radius - beforeState.radius) * floatValue);
                    if (animateState != 1) {
                        viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    }
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkStateColor), Integer.valueOf(afterState.checkStateColor))).intValue();
                } else if (i2 == 5) {
                    viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    float f = (viewState.buttonX - buttonMinX) / (buttonMaxX - buttonMinX);
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(f, Integer.valueOf(uncheckColor), Integer.valueOf(checkedColor))).intValue();
                    viewState.radius = viewRadius * f;
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(f, 0, Integer.valueOf(checkLineColor))).intValue();
                }
                postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                int i2 = animateState;
                if (i2 == 1) {
                    animateState = 2;
                    viewState.checkedLineColor = 0;
                    viewState.radius = viewRadius;
                    postInvalidate();
                } else if (i2 == 3) {
                    animateState = 0;
                    postInvalidate();
                } else if (i2 == 4) {
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                } else if (i2 != 5) {
                } else {
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                }
            }
        };
        init(context, attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() {
            @Override
            public void run() {
                if (isInAnimating()) {
                    return;
                }
                pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i22 = animateState;
                if (i22 == 1 || i22 == 3 || i22 == 4) {
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkedLineColor), Integer.valueOf(afterState.checkedLineColor))).intValue();
                    viewState.radius = beforeState.radius + ((afterState.radius - beforeState.radius) * floatValue);
                    if (animateState != 1) {
                        viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    }
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(floatValue, Integer.valueOf(beforeState.checkStateColor), Integer.valueOf(afterState.checkStateColor))).intValue();
                } else if (i22 == 5) {
                    viewState.buttonX = beforeState.buttonX + ((afterState.buttonX - beforeState.buttonX) * floatValue);
                    float f = (viewState.buttonX - buttonMinX) / (buttonMaxX - buttonMinX);
                    viewState.checkStateColor = ((Integer) argbEvaluator.evaluate(f, Integer.valueOf(uncheckColor), Integer.valueOf(checkedColor))).intValue();
                    viewState.radius = viewRadius * f;
                    viewState.checkedLineColor = ((Integer) argbEvaluator.evaluate(f, 0, Integer.valueOf(checkLineColor))).intValue();
                }
                postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                int i22 = animateState;
                if (i22 == 1) {
                    animateState = 2;
                    viewState.checkedLineColor = 0;
                    viewState.radius = viewRadius;
                    postInvalidate();
                } else if (i22 == 3) {
                    animateState = 0;
                    postInvalidate();
                } else if (i22 == 4) {
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                } else if (i22 != 5) {
                } else {
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    animateState = 0;
                    postInvalidate();
                    broadcastEvent();
                }
            }
        };
        init(context, attributeSet);
    }

    @Override
    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(0, 0, 0, 0);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = attributeSet != null ? context.obtainStyledAttributes(attributeSet, R.styleable.SwitchButton) : null;
        this.shadowEffect = optBoolean(obtainStyledAttributes, 11, true);
        this.uncheckCircleColor = optColor(obtainStyledAttributes, 17, -5592406);
        this.uncheckCircleWidth = optPixelSize(obtainStyledAttributes, 19, dp2pxInt(1.5f));
        this.uncheckCircleOffsetX = dp2px(10.0f);
        this.uncheckCircleRadius = optPixelSize(obtainStyledAttributes, 18, dp2px(4.0f));
        this.checkedLineOffsetX = dp2px(4.0f);
        this.checkedLineOffsetY = dp2px(4.0f);
        this.shadowRadius = optPixelSize(obtainStyledAttributes, 13, dp2pxInt(2.5f));
        this.shadowOffset = optPixelSize(obtainStyledAttributes, 12, dp2pxInt(1.5f));
        this.shadowColor = optColor(obtainStyledAttributes, 10, 855638016);
        this.uncheckColor = optColor(obtainStyledAttributes, 15, -2236963);
        this.checkedColor = optColor(obtainStyledAttributes, 4, -10107802);
        this.borderWidth = optPixelSize(obtainStyledAttributes, 1, dp2pxInt(1.0f));
        this.checkLineColor = optColor(obtainStyledAttributes, 6, -1);
        this.checkLineWidth = optPixelSize(obtainStyledAttributes, 7, dp2pxInt(1.0f));
        this.checkLineLength = dp2px(6.0f);
        int optColor = optColor(obtainStyledAttributes, 2, -1);
        this.uncheckButtonColor = optColor(obtainStyledAttributes, 16, optColor);
        this.checkedButtonColor = optColor(obtainStyledAttributes, 5, optColor);
        int optInt = optInt(obtainStyledAttributes, 8, 300);
        this.isChecked = optBoolean(obtainStyledAttributes, 3, false);
        this.showIndicator = optBoolean(obtainStyledAttributes, 14, true);
        this.background = optColor(obtainStyledAttributes, 0, -1);
        this.enableEffect = optBoolean(obtainStyledAttributes, 9, true);
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        this.paint = new Paint(1);
        Paint paint = new Paint(1);
        this.buttonPaint = paint;
        paint.setColor(optColor);
        if (this.shadowEffect) {
            this.buttonPaint.setShadowLayer(this.shadowRadius, 0.0f, this.shadowOffset, this.shadowColor);
        }
        this.viewState = new ViewState();
        this.beforeState = new ViewState();
        this.afterState = new ViewState();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.valueAnimator = ofFloat;
        ofFloat.setDuration(optInt);
        this.valueAnimator.setRepeatCount(0);
        this.valueAnimator.addUpdateListener(this.animatorUpdateListener);
        this.valueAnimator.addListener(this.animatorListener);
        super.setClickable(true);
        setPadding(0, 0, 0, 0);
        setLayerType(1, null);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(DEFAULT_WIDTH, MeasureSpec.AT_MOST);
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(DEFAULT_HEIGHT, MeasureSpec.AT_MOST);
        }
        super.onMeasure(i, i2);
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float max = Math.max(this.shadowRadius + this.shadowOffset, this.borderWidth);
        float f = i2 - max;
        float f2 = f - max;
        this.height = f2;
        float f3 = i - max;
        this.width = f3 - max;
        float f4 = f2 * 0.5f;
        this.viewRadius = f4;
        this.buttonRadius = f4 - this.borderWidth;
        this.left = max;
        this.top = max;
        this.right = f3;
        this.bottom = f;
        this.centerX = (max + f3) * 0.5f;
        this.centerY = (f + max) * 0.5f;
        this.buttonMinX = max + f4;
        this.buttonMaxX = f3 - f4;
        if (isChecked()) {
            setCheckedViewState(this.viewState);
        } else {
            setUncheckViewState(this.viewState);
        }
        this.isUiInited = true;
        postInvalidate();
    }

    private void setUncheckViewState(ViewState viewState) {
        viewState.radius = 0.0f;
        viewState.checkStateColor = this.uncheckColor;
        viewState.checkedLineColor = 0;
        viewState.buttonX = this.buttonMinX;
        this.buttonPaint.setColor(this.uncheckButtonColor);
    }

    private void setCheckedViewState(ViewState viewState) {
        viewState.radius = this.viewRadius;
        viewState.checkStateColor = this.checkedColor;
        viewState.checkedLineColor = this.checkLineColor;
        viewState.buttonX = this.buttonMaxX;
        this.buttonPaint.setColor(this.checkedButtonColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(this.borderWidth);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.background);
        drawRoundRect(canvas, this.left, this.top, this.right, this.bottom, this.viewRadius, this.paint);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.uncheckColor);
        drawRoundRect(canvas, this.left, this.top, this.right, this.bottom, this.viewRadius, this.paint);
        if (this.showIndicator) {
            drawUncheckIndicator(canvas);
        }
        float f = this.viewState.radius * 0.5f;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.viewState.checkStateColor);
        this.paint.setStrokeWidth(this.borderWidth + (f * 2.0f));
        drawRoundRect(canvas, this.left + f, this.top + f, this.right - f, this.bottom - f, this.viewRadius, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(1.0f);
        float f2 = this.left;
        float f3 = this.top;
        float f4 = this.viewRadius;
        drawArc(canvas, f2, f3, f2 + (f4 * 2.0f), f3 + (f4 * 2.0f), 90.0f, 180.0f, this.paint);
        canvas.drawRect(this.left + this.viewRadius, this.top, this.viewState.buttonX, this.top + (this.viewRadius * 2.0f), this.paint);
        if (this.showIndicator) {
            drawCheckedIndicator(canvas);
        }
        drawButton(canvas, this.viewState.buttonX, this.centerY);
    }

    protected void drawCheckedIndicator(Canvas canvas) {
        int i = this.viewState.checkedLineColor;
        float f = this.checkLineWidth;
        float f2 = this.left;
        float f3 = this.viewRadius;
        float f4 = (f2 + f3) - this.checkedLineOffsetX;
        float f5 = this.centerY;
        float f6 = this.checkLineLength;
        drawCheckedIndicator(canvas, i, f, f4, f5 - f6, (f2 + f3) - this.checkedLineOffsetY, f5 + f6, this.paint);
    }

    protected void drawCheckedIndicator(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i);
        paint.setStrokeWidth(f);
        canvas.drawLine(f2, f3, f4, f5, paint);
    }

    private void drawUncheckIndicator(Canvas canvas) {
        drawUncheckIndicator(canvas, this.uncheckCircleColor, this.uncheckCircleWidth, this.right - this.uncheckCircleOffsetX, this.centerY, this.uncheckCircleRadius, this.paint);
    }

    protected void drawUncheckIndicator(Canvas canvas, int i, float f, float f2, float f3, float f4, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i);
        paint.setStrokeWidth(f);
        canvas.drawCircle(f2, f3, f4, paint);
    }

    private void drawArc(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, Paint paint) {
        canvas.drawArc(f, f2, f3, f4, f5, f6, true, paint);
    }

    private void drawRoundRect(Canvas canvas, float f, float f2, float f3, float f4, float f5, Paint paint) {
        canvas.drawRoundRect(f, f2, f3, f4, f5, f5, paint);
    }

    private void drawButton(Canvas canvas, float f, float f2) {
        canvas.drawCircle(f, f2, this.buttonRadius, this.buttonPaint);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setColor(-2236963);
        canvas.drawCircle(f, f2, this.buttonRadius, this.paint);
    }

    @Override
    public void setChecked(boolean z) {
        if (z == isChecked()) {
            postInvalidate();
        } else {
            toggle(this.enableEffect, false);
        }
    }

    @Override
    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean z) {
        toggle(z, true);
    }

    private void toggle(boolean z, boolean z2) {
        if (isEnabled()) {
            if (this.isEventBroadcast) {
                throw new RuntimeException("should NOT switch the state in method: [onCheckedChanged]!");
            }
            if (!this.isUiInited) {
                this.isChecked = !this.isChecked;
                if (z2) {
                    broadcastEvent();
                    return;
                }
                return;
            }
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            if (!this.enableEffect || !z) {
                this.isChecked = !this.isChecked;
                if (isChecked()) {
                    setCheckedViewState(this.viewState);
                } else {
                    setUncheckViewState(this.viewState);
                }
                postInvalidate();
                if (z2) {
                    broadcastEvent();
                    return;
                }
                return;
            }
            this.animateState = 5;
            this.beforeState.copy(this.viewState);
            if (isChecked()) {
                setUncheckViewState(this.afterState);
            } else {
                setCheckedViewState(this.afterState);
            }
            this.valueAnimator.start();
        }
    }

    public void broadcastEvent() {
        OnCheckedChangeListener onCheckedChangeListener = this.onCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            this.isEventBroadcast = true;
            onCheckedChangeListener.onCheckedChanged(this, isChecked());
        }
        this.isEventBroadcast = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.isTouchingDown = true;
                this.touchDownTime = System.currentTimeMillis();
                removeCallbacks(this.postPendingDrag);
                postDelayed(this.postPendingDrag, 100L);
            } else if (actionMasked == 1) {
                this.isTouchingDown = false;
                removeCallbacks(this.postPendingDrag);
                if (System.currentTimeMillis() - this.touchDownTime <= 300) {
                    toggle();
                } else if (isDragState()) {
                    boolean z = Math.max(0.0f, Math.min(1.0f, motionEvent.getX() / ((float) getWidth()))) > 0.5f;
                    if (z == isChecked()) {
                        pendingCancelDragState();
                    } else {
                        this.isChecked = z;
                        pendingSettleState();
                    }
                } else if (isPendingDragState()) {
                    pendingCancelDragState();
                }
            } else if (actionMasked == 2) {
                float x = motionEvent.getX();
                if (isPendingDragState()) {
                    float max = Math.max(0.0f, Math.min(1.0f, x / getWidth()));
                    ViewState viewState = this.viewState;
                    float f = this.buttonMinX;
                    viewState.buttonX = f + ((this.buttonMaxX - f) * max);
                } else if (isDragState()) {
                    float max2 = Math.max(0.0f, Math.min(1.0f, x / getWidth()));
                    ViewState viewState2 = this.viewState;
                    float f2 = this.buttonMinX;
                    viewState2.buttonX = f2 + ((this.buttonMaxX - f2) * max2);
                    this.viewState.checkStateColor = ((Integer) this.argbEvaluator.evaluate(max2, Integer.valueOf(this.uncheckColor), Integer.valueOf(this.checkedColor))).intValue();
                    postInvalidate();
                }
            } else if (actionMasked == 3) {
                this.isTouchingDown = false;
                removeCallbacks(this.postPendingDrag);
                if (isPendingDragState() || isDragState()) {
                    pendingCancelDragState();
                }
            }
            return true;
        }
        return false;
    }

    public void setShadowEffect(boolean z) {
        if (this.shadowEffect == z) {
            return;
        }
        this.shadowEffect = z;
        if (z) {
            this.buttonPaint.setShadowLayer(this.shadowRadius, 0.0f, this.shadowOffset, this.shadowColor);
        } else {
            this.buttonPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        }
    }

    public void pendingDragState() {
        if (!isInAnimating() && this.isTouchingDown) {
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            this.animateState = 1;
            this.beforeState.copy(this.viewState);
            this.afterState.copy(this.viewState);
            if (isChecked()) {
                this.afterState.checkStateColor = this.checkedColor;
                this.afterState.buttonX = this.buttonMaxX;
                this.afterState.checkedLineColor = this.checkedColor;
            } else {
                this.afterState.checkStateColor = this.uncheckColor;
                this.afterState.buttonX = this.buttonMinX;
                this.afterState.radius = this.viewRadius;
            }
            this.valueAnimator.start();
        }
    }

    private void pendingCancelDragState() {
        if (isDragState() || isPendingDragState()) {
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            this.animateState = 3;
            this.beforeState.copy(this.viewState);
            if (isChecked()) {
                setCheckedViewState(this.afterState);
            } else {
                setUncheckViewState(this.afterState);
            }
            this.valueAnimator.start();
        }
    }

    private void pendingSettleState() {
        if (this.valueAnimator.isRunning()) {
            this.valueAnimator.cancel();
        }
        this.animateState = 4;
        this.beforeState.copy(this.viewState);
        if (isChecked()) {
            setCheckedViewState(this.afterState);
        } else {
            setUncheckViewState(this.afterState);
        }
        this.valueAnimator.start();
    }

    private static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    private static int dp2pxInt(float f) {
        return (int) dp2px(f);
    }

    private static int optInt(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getInt(i, i2);
    }

    private static float optPixelSize(TypedArray typedArray, int i, float f) {
        return typedArray == null ? f : typedArray.getDimension(i, f);
    }

    private static int optPixelSize(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getDimensionPixelOffset(i, i2);
    }

    private static int optColor(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getColor(i, i2);
    }

    private static boolean optBoolean(TypedArray typedArray, int i, boolean z) {
        return typedArray == null ? z : typedArray.getBoolean(i, z);
    }

    public static class ViewState {
        float buttonX;
        int checkStateColor;
        int checkedLineColor;
        float radius;

        ViewState() {
        }

        public void copy(ViewState viewState) {
            this.buttonX = viewState.buttonX;
            this.checkStateColor = viewState.checkStateColor;
            this.checkedLineColor = viewState.checkedLineColor;
            this.radius = viewState.radius;
        }
    }
}
