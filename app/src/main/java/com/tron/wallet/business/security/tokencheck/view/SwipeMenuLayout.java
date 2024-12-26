package com.tron.wallet.business.security.tokencheck.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.tron.wallet.R;
public class SwipeMenuLayout extends ViewGroup {
    private static final String TAG = "SwipeMenuLayout";
    private static SwipeMenuLayout mCacheView;
    private int animDuration;
    private boolean chokeIntercept;
    private boolean isClickMenuAndClose;
    private boolean isEnableLeftMenu;
    private boolean isEnableSwipe;
    private boolean isFingerTouch;
    private boolean isOpenChoke;
    private ValueAnimator mCloseAnim;
    private View mContentView;
    private final Context mContext;
    private ValueAnimator mExpandAnim;
    private float mFirstRawX;
    private float mLastRawX;
    private int mMenuWidth;
    private int mPointerId;
    private int mScaledMaximumFlingVelocity;
    private int mScaledTouchSlop;
    private SwipeMenuStateListener mSwipeMenuStateListener;
    private VelocityTracker mVelocityTracker;

    public SwipeMenuLayout getCacheView() {
        return mCacheView;
    }

    public boolean isClickMenuAndClose() {
        return this.isClickMenuAndClose;
    }

    public boolean isEnableLeftMenu() {
        return this.isEnableLeftMenu;
    }

    public boolean isEnableSwipe() {
        return this.isEnableSwipe;
    }

    public boolean isOpenChoke() {
        return this.isOpenChoke;
    }

    public SwipeMenuLayout setClickMenuAndClose(boolean z) {
        this.isClickMenuAndClose = z;
        return this;
    }

    public SwipeMenuLayout setEnableLeftMenu(boolean z) {
        this.isEnableLeftMenu = z;
        return this;
    }

    public SwipeMenuLayout setEnableSwipe(boolean z) {
        this.isEnableSwipe = z;
        return this;
    }

    public SwipeMenuLayout setOpenChoke(boolean z) {
        this.isOpenChoke = z;
        return this;
    }

    public SwipeMenuLayout setSwipeMenuStateListener(SwipeMenuStateListener swipeMenuStateListener) {
        this.mSwipeMenuStateListener = swipeMenuStateListener;
        return this;
    }

    public SwipeMenuLayout(Context context) {
        this(context, null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLastRawX = 0.0f;
        this.mFirstRawX = 0.0f;
        this.isFingerTouch = false;
        this.animDuration = 300;
        this.chokeIntercept = false;
        this.isOpenChoke = true;
        this.isEnableSwipe = true;
        this.isEnableLeftMenu = false;
        this.isClickMenuAndClose = false;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwipeMenuLayout, i, 0);
        this.isEnableSwipe = obtainStyledAttributes.getBoolean(2, true);
        this.isEnableLeftMenu = obtainStyledAttributes.getBoolean(1, false);
        this.isOpenChoke = obtainStyledAttributes.getBoolean(3, true);
        this.isClickMenuAndClose = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        this.mScaledTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        this.mScaledMaximumFlingVelocity = ViewConfiguration.get(this.mContext).getScaledMaximumFlingVelocity();
        setClickable(true);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        this.mMenuWidth = 0;
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (i5 == 0) {
                    layoutParams.width = getMeasuredWidth();
                    this.mContentView = childAt;
                }
                measureChild(childAt, i, i2);
                if (mode != MeasureSpec.AT_MOST) {
                    i3 = Math.max(i3, childAt.getMeasuredHeight());
                }
                if (i5 == 0) {
                    i4 = childAt.getMeasuredWidth();
                } else {
                    this.mMenuWidth += childAt.getMeasuredWidth();
                }
            }
        }
        setMeasuredDimension(i4, Math.max(getMeasuredHeight(), i3));
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                if (i7 == 0) {
                    childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                    measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                } else if (this.isEnableLeftMenu) {
                    childAt.layout(i6 - childAt.getMeasuredWidth(), paddingTop, i6, childAt.getMeasuredHeight() + paddingTop);
                    i6 -= childAt.getMeasuredWidth();
                } else {
                    childAt.layout(i5, paddingTop, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight() + paddingTop);
                    measuredWidth = childAt.getMeasuredWidth();
                }
                i5 += measuredWidth;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mFirstRawX = motionEvent.getRawX();
            getParent().requestDisallowInterceptTouchEvent(false);
            this.chokeIntercept = false;
            SwipeMenuLayout swipeMenuLayout = mCacheView;
            if (swipeMenuLayout != null) {
                if (swipeMenuLayout != this) {
                    swipeMenuLayout.closeMenuAnim();
                    this.chokeIntercept = this.isOpenChoke;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action == 1 || action == 3) {
            this.isFingerTouch = false;
            if (Math.abs(getScrollX()) == Math.abs(this.mMenuWidth)) {
                if ((!this.isEnableLeftMenu || motionEvent.getX() >= this.mMenuWidth) && (this.isEnableLeftMenu || motionEvent.getX() <= getMeasuredWidth() - this.mMenuWidth)) {
                    closeMenuAnim();
                    return true;
                } else if (this.isClickMenuAndClose) {
                    closeMenuAnim();
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.isEnableSwipe) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2 && Math.abs(motionEvent.getRawX() - this.mFirstRawX) >= this.mScaledTouchSlop) {
                longClickable(false);
                return true;
            }
        } else if (this.isFingerTouch) {
            return true;
        } else {
            this.isFingerTouch = true;
            this.mPointerId = motionEvent.getPointerId(0);
            this.mLastRawX = motionEvent.getRawX();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.security.tokencheck.view.SwipeMenuLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void acquireVelocityTracker(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void expandMenuAnim() {
        longClickable(false);
        cleanAnim();
        mCacheView = this;
        int[] iArr = new int[2];
        iArr[0] = getScrollX();
        iArr[1] = this.isEnableLeftMenu ? -this.mMenuWidth : this.mMenuWidth;
        ValueAnimator ofInt = ValueAnimator.ofInt(iArr);
        this.mExpandAnim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mExpandAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (mSwipeMenuStateListener != null) {
                    mSwipeMenuStateListener.menuIsOpen(true);
                }
            }
        });
        this.mExpandAnim.setInterpolator(new OvershootInterpolator());
        this.mExpandAnim.setDuration(this.animDuration).start();
    }

    public void closeMenuAnim() {
        mCacheView = null;
        cleanAnim();
        ValueAnimator ofInt = ValueAnimator.ofInt(getScrollX(), 0);
        this.mCloseAnim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mCloseAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                longClickable(true);
                if (mSwipeMenuStateListener != null) {
                    mSwipeMenuStateListener.menuIsOpen(false);
                }
            }
        });
        this.mCloseAnim.setInterpolator(new AccelerateInterpolator());
        this.mCloseAnim.setDuration(this.animDuration).start();
    }

    private void cleanAnim() {
        ValueAnimator valueAnimator = this.mCloseAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mCloseAnim.cancel();
        }
        ValueAnimator valueAnimator2 = this.mExpandAnim;
        if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
            return;
        }
        this.mExpandAnim.cancel();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (getScrollX() != 0) {
            quickCloseMenu();
            mCacheView = null;
        }
        super.onDetachedFromWindow();
    }

    public void quickCloseMenu() {
        if (getScrollX() != 0) {
            cleanAnim();
            scrollTo(0, 0);
            mCacheView = null;
        }
    }

    public void quickExpandMenu() {
        if (getScrollX() == 0) {
            cleanAnim();
            scrollTo(this.isEnableLeftMenu ? -this.mMenuWidth : this.mMenuWidth, 0);
            mCacheView = null;
        }
    }

    public void longClickable(boolean z) {
        setLongClickable(z);
    }

    @Override
    public boolean performLongClick() {
        if (getScrollX() != 0) {
            return true;
        }
        return super.performLongClick();
    }

    public boolean isExpandMenu() {
        return Math.abs(getScaleX()) >= ((float) this.mMenuWidth);
    }
}
