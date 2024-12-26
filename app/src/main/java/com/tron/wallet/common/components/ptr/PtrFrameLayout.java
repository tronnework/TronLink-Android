package com.tron.wallet.common.components.ptr;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;
import com.tron.wallet.R;
import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
import com.tron.wallet.common.components.ptr.util.PtrCLog;
import java.util.ArrayList;
public class PtrFrameLayout extends ViewGroup {
    public static boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static byte FLAG_AUTO_REFRESH_AT_ONCE = 1;
    private static byte FLAG_AUTO_REFRESH_BUT_LATER = 2;
    private static byte FLAG_ENABLE_NEXT_PTR_AT_ONCE = 4;
    private static byte FLAG_PIN_CONTENT = 8;
    private static int ID = 1;
    private static byte MASK_AUTO_REFRESH = 3;
    public static final byte PTR_STATUS_COMPLETE = 4;
    public static final byte PTR_STATUS_INIT = 1;
    public static final byte PTR_STATUS_LOADING = 3;
    public static final byte PTR_STATUS_PREPARE = 2;
    protected final String LOG_TAG;
    private int mContainerId;
    protected View mContent;
    private boolean mDisableWhenHorizontalMove;
    private int mDurationToBackFooter;
    private int mDurationToBackHeader;
    private int mDurationToCloseFooter;
    private int mDurationToCloseHeader;
    private int mFlag;
    private int mFooterHeight;
    private int mFooterId;
    private View mFooterView;
    private boolean mForceBackWhenComplete;
    private boolean mHasSendCancelEvent;
    private int mHeaderHeight;
    private int mHeaderId;
    private View mHeaderView;
    private boolean mKeepHeaderWhenRefresh;
    private MotionEvent mLastMoveEvent;
    private int mLoadingMinTime;
    private long mLoadingStartTime;
    private Mode mMode;
    private int mPagingTouchSlop;
    private Runnable mPerformRefreshCompleteDelay;
    private boolean mPreventForHorizontal;
    private PtrHandler mPtrHandler;
    private PtrIndicator mPtrIndicator;
    private PtrUIHandlerHolder mPtrUIHandlerHolder;
    private boolean mPullToRefresh;
    private PtrUIHandlerHook mRefreshCompleteHook;
    private ScrollChecker mScrollChecker;
    private byte mStatus;

    public enum Mode {
        NONE,
        REFRESH,
        LOAD_MORE,
        BOTH
    }

    private void clearFlag() {
        this.mFlag &= ~MASK_AUTO_REFRESH;
    }

    private void onPositionChange(boolean z, byte b, PtrIndicator ptrIndicator) {
    }

    private boolean performAutoRefreshButLater() {
        return (this.mFlag & MASK_AUTO_REFRESH) == FLAG_AUTO_REFRESH_BUT_LATER;
    }

    public void disableWhenHorizontalMove(boolean z) {
        this.mDisableWhenHorizontalMove = z;
    }

    public View getContentView() {
        return this.mContent;
    }

    public int getDurationToBackFooter() {
        return this.mDurationToBackFooter;
    }

    public int getDurationToBackHeader() {
        return this.mDurationToBackHeader;
    }

    public float getDurationToClose() {
        return this.mDurationToCloseHeader;
    }

    public long getDurationToCloseFooter() {
        return this.mDurationToCloseFooter;
    }

    public long getDurationToCloseHeader() {
        return this.mDurationToCloseHeader;
    }

    public int getFooterHeight() {
        return this.mFooterHeight;
    }

    public int getHeaderHeight() {
        return this.mHeaderHeight;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public Mode getMode() {
        return this.mMode;
    }

    public PtrIndicator getPtrIndicator() {
        return this.mPtrIndicator;
    }

    public boolean isAutoRefresh() {
        return (this.mFlag & MASK_AUTO_REFRESH) > 0;
    }

    public boolean isEnabledNextPtrAtOnce() {
        return (this.mFlag & FLAG_ENABLE_NEXT_PTR_AT_ONCE) > 0;
    }

    public boolean isForceBackWhenComplete() {
        return this.mForceBackWhenComplete;
    }

    public boolean isKeepHeaderWhenRefresh() {
        return this.mKeepHeaderWhenRefresh;
    }

    public boolean isPinContent() {
        return (this.mFlag & FLAG_PIN_CONTENT) > 0;
    }

    public boolean isPullToRefresh() {
        return this.mPullToRefresh;
    }

    public boolean isRefreshing() {
        return this.mStatus == 3;
    }

    public void setDurationToBackFooter(int i) {
        this.mDurationToBackFooter = i;
    }

    public void setDurationToBackHeader(int i) {
        this.mDurationToBackHeader = i;
    }

    public void setDurationToCloseFooter(int i) {
        this.mDurationToCloseFooter = i;
    }

    public void setDurationToCloseHeader(int i) {
        this.mDurationToCloseHeader = i;
    }

    public void setEnabledNextPtrAtOnce(boolean z) {
        if (z) {
            this.mFlag |= FLAG_ENABLE_NEXT_PTR_AT_ONCE;
        } else {
            this.mFlag &= ~FLAG_ENABLE_NEXT_PTR_AT_ONCE;
        }
    }

    public void setForceBackWhenComplete(boolean z) {
        this.mForceBackWhenComplete = z;
    }

    @Deprecated
    public void setInterceptEventWhileWorking(boolean z) {
    }

    public void setKeepHeaderWhenRefresh(boolean z) {
        this.mKeepHeaderWhenRefresh = z;
    }

    public void setLoadingMinTime(int i) {
        this.mLoadingMinTime = i;
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    public void setPinContent(boolean z) {
        if (z) {
            this.mFlag |= FLAG_PIN_CONTENT;
        } else {
            this.mFlag &= ~FLAG_PIN_CONTENT;
        }
    }

    public void setPtrHandler(PtrHandler ptrHandler) {
        this.mPtrHandler = ptrHandler;
    }

    public void setPullToRefresh(boolean z) {
        this.mPullToRefresh = z;
    }

    public PtrFrameLayout(Context context) {
        this(context, null);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStatus = (byte) 1;
        StringBuilder sb = new StringBuilder("ptr-frame-");
        int i2 = ID + 1;
        ID = i2;
        sb.append(i2);
        this.LOG_TAG = sb.toString();
        this.mHeaderId = 0;
        this.mContainerId = 0;
        this.mFooterId = 0;
        this.mMode = Mode.BOTH;
        this.mDurationToBackHeader = 200;
        this.mDurationToBackFooter = 200;
        this.mDurationToCloseHeader = 1000;
        this.mDurationToCloseFooter = 1000;
        this.mKeepHeaderWhenRefresh = true;
        this.mPullToRefresh = false;
        this.mForceBackWhenComplete = false;
        this.mPtrUIHandlerHolder = PtrUIHandlerHolder.create();
        this.mDisableWhenHorizontalMove = false;
        this.mFlag = 0;
        this.mPreventForHorizontal = false;
        this.mLoadingMinTime = 500;
        this.mLoadingStartTime = 0L;
        this.mHasSendCancelEvent = false;
        this.mPerformRefreshCompleteDelay = new Runnable() {
            @Override
            public void run() {
                performRefreshComplete();
            }
        };
        this.mPtrIndicator = new PtrIndicator();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PtrFrameLayout, 0, 0);
        if (obtainStyledAttributes != null) {
            this.mHeaderId = obtainStyledAttributes.getResourceId(8, this.mHeaderId);
            this.mContainerId = obtainStyledAttributes.getResourceId(0, this.mContainerId);
            this.mFooterId = obtainStyledAttributes.getResourceId(7, this.mFooterId);
            PtrIndicator ptrIndicator = this.mPtrIndicator;
            ptrIndicator.setResistanceHeader(obtainStyledAttributes.getFloat(13, ptrIndicator.getResistanceHeader()));
            PtrIndicator ptrIndicator2 = this.mPtrIndicator;
            ptrIndicator2.setResistanceFooter(obtainStyledAttributes.getFloat(13, ptrIndicator2.getResistanceFooter()));
            PtrIndicator ptrIndicator3 = this.mPtrIndicator;
            ptrIndicator3.setResistanceHeader(obtainStyledAttributes.getFloat(15, ptrIndicator3.getResistanceHeader()));
            PtrIndicator ptrIndicator4 = this.mPtrIndicator;
            ptrIndicator4.setResistanceFooter(obtainStyledAttributes.getFloat(14, ptrIndicator4.getResistanceFooter()));
            this.mDurationToBackHeader = obtainStyledAttributes.getInt(3, this.mDurationToCloseHeader);
            this.mDurationToBackFooter = obtainStyledAttributes.getInt(3, this.mDurationToCloseHeader);
            this.mDurationToBackHeader = obtainStyledAttributes.getInt(2, this.mDurationToCloseHeader);
            this.mDurationToBackFooter = obtainStyledAttributes.getInt(1, this.mDurationToCloseHeader);
            this.mDurationToCloseHeader = obtainStyledAttributes.getInt(4, this.mDurationToCloseHeader);
            this.mDurationToCloseFooter = obtainStyledAttributes.getInt(4, this.mDurationToCloseFooter);
            this.mDurationToCloseHeader = obtainStyledAttributes.getInt(6, this.mDurationToCloseHeader);
            this.mDurationToCloseFooter = obtainStyledAttributes.getInt(5, this.mDurationToCloseFooter);
            this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(obtainStyledAttributes.getFloat(12, this.mPtrIndicator.getRatioOfHeaderToHeightRefresh()));
            this.mKeepHeaderWhenRefresh = obtainStyledAttributes.getBoolean(9, this.mKeepHeaderWhenRefresh);
            this.mPullToRefresh = obtainStyledAttributes.getBoolean(11, this.mPullToRefresh);
            this.mMode = getModeFromIndex(obtainStyledAttributes.getInt(10, 4));
            obtainStyledAttributes.recycle();
        }
        this.mScrollChecker = new ScrollChecker();
        this.mPagingTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 2;
    }

    private Mode getModeFromIndex(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return Mode.BOTH;
                    }
                    return Mode.BOTH;
                }
                return Mode.LOAD_MORE;
            }
            return Mode.REFRESH;
        }
        return Mode.NONE;
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 3) {
            throw new IllegalStateException("PtrFrameLayout only can host 3 elements");
        }
        if (childCount == 3) {
            int i = this.mHeaderId;
            if (i != 0 && this.mHeaderView == null) {
                this.mHeaderView = findViewById(i);
            }
            int i2 = this.mContainerId;
            if (i2 != 0 && this.mContent == null) {
                this.mContent = findViewById(i2);
            }
            int i3 = this.mFooterId;
            if (i3 != 0 && this.mFooterView == null) {
                this.mFooterView = findViewById(i3);
            }
            if (this.mContent == null || this.mHeaderView == null || this.mFooterView == null) {
                View childAt = getChildAt(0);
                View childAt2 = getChildAt(1);
                View childAt3 = getChildAt(2);
                if (this.mContent == null && this.mHeaderView == null && this.mFooterView == null) {
                    this.mHeaderView = childAt;
                    this.mContent = childAt2;
                    this.mFooterView = childAt3;
                } else {
                    ArrayList<View> arrayList = new ArrayList<View>(3, childAt, childAt2, childAt3) {
                        final View val$child1;
                        final View val$child2;
                        final View val$child3;

                        {
                            this.val$child1 = childAt;
                            this.val$child2 = childAt2;
                            this.val$child3 = childAt3;
                            add(childAt);
                            add(childAt2);
                            add(childAt3);
                        }
                    };
                    View view = this.mHeaderView;
                    if (view != null) {
                        arrayList.remove(view);
                    }
                    View view2 = this.mContent;
                    if (view2 != null) {
                        arrayList.remove(view2);
                    }
                    View view3 = this.mFooterView;
                    if (view3 != null) {
                        arrayList.remove(view3);
                    }
                    if (this.mHeaderView == null && arrayList.size() > 0) {
                        this.mHeaderView = arrayList.get(0);
                        arrayList.remove(0);
                    }
                    if (this.mContent == null && arrayList.size() > 0) {
                        this.mContent = arrayList.get(0);
                        arrayList.remove(0);
                    }
                    if (this.mFooterView == null && arrayList.size() > 0) {
                        this.mFooterView = arrayList.get(0);
                        arrayList.remove(0);
                    }
                }
            }
        } else if (childCount == 2) {
            int i4 = this.mHeaderId;
            if (i4 != 0 && this.mHeaderView == null) {
                this.mHeaderView = findViewById(i4);
            }
            int i5 = this.mContainerId;
            if (i5 != 0 && this.mContent == null) {
                this.mContent = findViewById(i5);
            }
            if (this.mContent == null || this.mHeaderView == null) {
                View childAt4 = getChildAt(0);
                View childAt5 = getChildAt(1);
                if (childAt4 instanceof PtrUIHandler) {
                    this.mHeaderView = childAt4;
                    this.mContent = childAt5;
                } else if (childAt5 instanceof PtrUIHandler) {
                    this.mHeaderView = childAt5;
                    this.mContent = childAt4;
                } else {
                    View view4 = this.mContent;
                    if (view4 == null && this.mHeaderView == null) {
                        this.mHeaderView = childAt4;
                        this.mContent = childAt5;
                    } else {
                        View view5 = this.mHeaderView;
                        if (view5 == null) {
                            if (view4 == childAt4) {
                                childAt4 = childAt5;
                            }
                            this.mHeaderView = childAt4;
                        } else {
                            if (view5 == childAt4) {
                                childAt4 = childAt5;
                            }
                            this.mContent = childAt4;
                        }
                    }
                }
            }
        } else if (childCount == 1) {
            this.mContent = getChildAt(0);
        } else {
            TextView textView = new TextView(getContext());
            textView.setClickable(true);
            textView.setTextColor(-39424);
            textView.setGravity(17);
            textView.setTextSize(20.0f);
            textView.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
            this.mContent = textView;
            addView(textView);
        }
        View view6 = this.mHeaderView;
        if (view6 != null) {
            if (view6 instanceof PtrUIHandler) {
                addPtrUIHandler((PtrUIHandler) view6);
            }
            this.mHeaderView.bringToFront();
        }
        View view7 = this.mFooterView;
        if (view7 != null) {
            if (view7 instanceof PtrUIHandler) {
                addPtrUIHandler((PtrUIHandler) view7);
            }
            this.mFooterView.bringToFront();
        }
        super.onFinishInflate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ScrollChecker scrollChecker = this.mScrollChecker;
        if (scrollChecker != null) {
            scrollChecker.destroy();
        }
        Runnable runnable = this.mPerformRefreshCompleteDelay;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.mHeaderView;
        if (view != null) {
            measureChildWithMargins(view, i, 0, i2, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            int measuredHeight = this.mHeaderView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            this.mHeaderHeight = measuredHeight;
            this.mPtrIndicator.setHeaderHeight(measuredHeight);
        }
        View view2 = this.mFooterView;
        if (view2 != null) {
            measureChildWithMargins(view2, i, 0, i2, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mFooterView.getLayoutParams();
            int measuredHeight2 = this.mFooterView.getMeasuredHeight() + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
            this.mFooterHeight = measuredHeight2;
            this.mPtrIndicator.setFooterHeight(measuredHeight2);
        }
        View view3 = this.mContent;
        if (view3 != null) {
            measureContentView(view3, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            if (getLayoutParams().height == -2) {
                super.setMeasuredDimension(getMeasuredWidth(), this.mContent.getMeasuredHeight() + marginLayoutParams3.topMargin + marginLayoutParams3.bottomMargin);
            }
        }
    }

    private void measureContentView(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin, marginLayoutParams.height));
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutChildren();
    }

    private void layoutChildren() {
        int currentPosY;
        int i;
        int i2;
        int i3;
        int i4;
        int measuredWidth;
        int measuredHeight;
        if (this.mPtrIndicator.isHeader()) {
            i = this.mPtrIndicator.getCurrentPosY();
            currentPosY = 0;
        } else {
            currentPosY = this.mPtrIndicator.getCurrentPosY();
            i = 0;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        View view = this.mHeaderView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int i5 = marginLayoutParams.leftMargin + paddingLeft;
            int i6 = ((marginLayoutParams.topMargin + paddingTop) + i) - this.mHeaderHeight;
            this.mHeaderView.layout(i5, i6, this.mHeaderView.getMeasuredWidth() + i5, this.mHeaderView.getMeasuredHeight() + i6);
        }
        View view2 = this.mContent;
        if (view2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
            if (this.mPtrIndicator.isHeader()) {
                i3 = marginLayoutParams2.leftMargin + paddingLeft;
                int i7 = marginLayoutParams2.topMargin + paddingTop;
                if (isPinContent()) {
                    i = 0;
                }
                i4 = i7 + i;
                measuredWidth = this.mContent.getMeasuredWidth() + i3;
                measuredHeight = this.mContent.getMeasuredHeight();
            } else {
                i3 = paddingLeft + marginLayoutParams2.leftMargin;
                i4 = (marginLayoutParams2.topMargin + paddingTop) - (isPinContent() ? 0 : currentPosY);
                measuredWidth = this.mContent.getMeasuredWidth() + i3;
                measuredHeight = this.mContent.getMeasuredHeight();
            }
            i2 = measuredHeight + i4;
            this.mContent.layout(i3, i4, measuredWidth, i2);
        } else {
            i2 = 0;
        }
        View view3 = this.mFooterView;
        if (view3 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
            int i8 = paddingLeft + marginLayoutParams3.leftMargin;
            int i9 = ((paddingTop + marginLayoutParams3.topMargin) + i2) - (isPinContent() ? currentPosY : 0);
            this.mFooterView.layout(i8, i9, this.mFooterView.getMeasuredWidth() + i8, this.mFooterView.getMeasuredHeight() + i9);
        }
    }

    public boolean dispatchTouchEventSupper(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View view;
        if (!isEnabled() || this.mContent == null || this.mHeaderView == null) {
            return dispatchTouchEventSupper(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    this.mLastMoveEvent = motionEvent;
                    this.mPtrIndicator.onMove(motionEvent.getX(), motionEvent.getY());
                    float offsetX = this.mPtrIndicator.getOffsetX();
                    float offsetY = this.mPtrIndicator.getOffsetY();
                    if (this.mDisableWhenHorizontalMove && !this.mPreventForHorizontal && Math.abs(offsetX) > this.mPagingTouchSlop && Math.abs(offsetX) > Math.abs(offsetY) && this.mPtrIndicator.isInStartPosition()) {
                        this.mPreventForHorizontal = true;
                    }
                    if (this.mPreventForHorizontal) {
                        return dispatchTouchEventSupper(motionEvent);
                    }
                    boolean z = offsetY > 0.0f;
                    boolean z2 = !z;
                    boolean z3 = this.mPtrIndicator.isHeader() && this.mPtrIndicator.hasLeftStartPosition();
                    boolean z4 = (this.mFooterView == null || this.mPtrIndicator.isHeader() || !this.mPtrIndicator.hasLeftStartPosition()) ? false : true;
                    PtrHandler ptrHandler = this.mPtrHandler;
                    boolean z5 = ptrHandler != null && ptrHandler.checkCanDoRefresh(this, this.mContent, this.mHeaderView) && (this.mMode.ordinal() & 1) > 0;
                    PtrHandler ptrHandler2 = this.mPtrHandler;
                    boolean z6 = ptrHandler2 != null && (view = this.mFooterView) != null && (ptrHandler2 instanceof PtrHandler2) && ((PtrHandler2) ptrHandler2).checkCanDoLoadMore(this, this.mContent, view) && (this.mMode.ordinal() & 2) > 0;
                    if (DEBUG) {
                        PtrCLog.v(this.LOG_TAG, "ACTION_MOVE: offsetY:%s, currentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s canHeaderMoveDown: %s canFooterMoveUp: %s", Float.valueOf(offsetY), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z), Boolean.valueOf(z4), Boolean.valueOf(z5), Boolean.valueOf(z6));
                    }
                    if (!z3 && !z4) {
                        if (z && !z5) {
                            return dispatchTouchEventSupper(motionEvent);
                        }
                        if (z2 && !z6) {
                            return dispatchTouchEventSupper(motionEvent);
                        }
                        if (z) {
                            moveHeaderPos(offsetY);
                            return true;
                        } else if (z2) {
                            moveFooterPos(offsetY);
                            return true;
                        }
                    }
                    if (z3) {
                        moveHeaderPos(offsetY);
                        return true;
                    } else if (z4) {
                        if (this.mForceBackWhenComplete && this.mStatus == 4) {
                            return dispatchTouchEventSupper(motionEvent);
                        }
                        moveFooterPos(offsetY);
                        return true;
                    }
                } else if (action != 3) {
                }
                return dispatchTouchEventSupper(motionEvent);
            }
            this.mPtrIndicator.onRelease();
            if (this.mPtrIndicator.hasLeftStartPosition()) {
                if (DEBUG) {
                    PtrCLog.d(this.LOG_TAG, "call onRelease when user release");
                }
                onRelease(false);
                if (this.mPtrIndicator.hasMovedAfterPressedDown()) {
                    sendCancelEvent();
                    return true;
                }
                return dispatchTouchEventSupper(motionEvent);
            }
            return dispatchTouchEventSupper(motionEvent);
        }
        this.mHasSendCancelEvent = false;
        this.mPtrIndicator.onPressDown(motionEvent.getX(), motionEvent.getY());
        if (!this.mForceBackWhenComplete) {
            this.mScrollChecker.abortIfWorking();
        } else if (this.mPtrIndicator.isHeader() || !this.mPtrIndicator.hasLeftStartPosition() || this.mStatus != 4) {
            this.mScrollChecker.abortIfWorking();
        }
        this.mPreventForHorizontal = false;
        dispatchTouchEventSupper(motionEvent);
        return true;
    }

    public void moveFooterPos(float f) {
        this.mPtrIndicator.setIsHeader(false);
        movePos(-f);
    }

    public void moveHeaderPos(float f) {
        this.mPtrIndicator.setIsHeader(true);
        movePos(f);
    }

    private void movePos(float f) {
        int i = 0;
        if (f < 0.0f && this.mPtrIndicator.isInStartPosition()) {
            if (DEBUG) {
                PtrCLog.e(this.LOG_TAG, String.format("has reached the top", new Object[0]));
                return;
            }
            return;
        }
        int currentPosY = this.mPtrIndicator.getCurrentPosY() + ((int) f);
        if (!this.mPtrIndicator.willOverTop(currentPosY)) {
            i = currentPosY;
        } else if (DEBUG) {
            PtrCLog.e(this.LOG_TAG, String.format("over top", new Object[0]));
        }
        this.mPtrIndicator.setCurrentPos(i);
        int lastPosY = i - this.mPtrIndicator.getLastPosY();
        if (!this.mPtrIndicator.isHeader()) {
            lastPosY = -lastPosY;
        }
        updatePos(lastPosY);
    }

    private void updatePos(int i) {
        if (i == 0) {
            return;
        }
        boolean isUnderTouch = this.mPtrIndicator.isUnderTouch();
        if (isUnderTouch && !this.mHasSendCancelEvent && this.mPtrIndicator.hasMovedAfterPressedDown()) {
            this.mHasSendCancelEvent = true;
            sendCancelEvent();
        }
        if ((this.mPtrIndicator.hasJustLeftStartPosition() && this.mStatus == 1) || (this.mPtrIndicator.goDownCrossFinishPosition() && this.mStatus == 4 && isEnabledNextPtrAtOnce())) {
            this.mStatus = (byte) 2;
            this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
            if (DEBUG) {
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag));
            }
        }
        if (this.mPtrIndicator.hasJustBackToStartPosition()) {
            tryToNotifyReset();
            if (isUnderTouch) {
                sendDownEvent();
            }
        }
        if (this.mStatus == 2) {
            if (isUnderTouch && !isAutoRefresh() && this.mPullToRefresh && this.mPtrIndicator.crossRefreshLineFromTopToBottom()) {
                tryToPerformRefresh();
            }
            if (performAutoRefreshButLater() && this.mPtrIndicator.hasJustReachedHeaderHeightFromTopToBottom()) {
                tryToPerformRefresh();
            }
        }
        if (DEBUG) {
            PtrCLog.v(this.LOG_TAG, "updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s", Integer.valueOf(i), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Integer.valueOf(this.mPtrIndicator.getLastPosY()), Integer.valueOf(this.mContent.getTop()), Integer.valueOf(this.mHeaderHeight));
        }
        if (this.mPtrIndicator.isHeader()) {
            this.mHeaderView.offsetTopAndBottom(i);
        } else {
            this.mFooterView.offsetTopAndBottom(i);
        }
        if (!isPinContent()) {
            this.mContent.offsetTopAndBottom(i);
        }
        invalidate();
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIPositionChange(this, isUnderTouch, this.mStatus, this.mPtrIndicator);
        }
        onPositionChange(isUnderTouch, this.mStatus, this.mPtrIndicator);
    }

    private void onRelease(boolean z) {
        tryToPerformRefresh();
        byte b = this.mStatus;
        if (b != 3) {
            if (b == 4) {
                notifyUIRefreshComplete(false);
            } else {
                tryScrollBackToTopAbortRefresh();
            }
        } else if (this.mKeepHeaderWhenRefresh) {
            if (!this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading() || z) {
                return;
            }
            if (this.mPtrIndicator.isHeader()) {
                this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading(), this.mDurationToBackHeader);
            } else {
                this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading(), this.mDurationToBackFooter);
            }
        } else {
            tryScrollBackToTopWhileLoading();
        }
    }

    public void setRefreshCompleteHook(PtrUIHandlerHook ptrUIHandlerHook) {
        this.mRefreshCompleteHook = ptrUIHandlerHook;
        ptrUIHandlerHook.setResumeAction(new Runnable() {
            @Override
            public void run() {
                if (PtrFrameLayout.DEBUG) {
                    PtrCLog.d(LOG_TAG, "mRefreshCompleteHook resume.");
                }
                notifyUIRefreshComplete(true);
            }
        });
    }

    private void tryScrollBackToTop() {
        if (!this.mPtrIndicator.isUnderTouch() && this.mPtrIndicator.hasLeftStartPosition()) {
            this.mScrollChecker.tryToScrollTo(0, this.mPtrIndicator.isHeader() ? this.mDurationToCloseHeader : this.mDurationToCloseFooter);
        } else if (this.mForceBackWhenComplete && !this.mPtrIndicator.isHeader() && this.mStatus == 4) {
            this.mScrollChecker.tryToScrollTo(0, this.mDurationToCloseFooter);
        }
    }

    private void tryScrollBackToTopWhileLoading() {
        tryScrollBackToTop();
    }

    private void tryScrollBackToTopAfterComplete() {
        tryScrollBackToTop();
    }

    private void tryScrollBackToTopAbortRefresh() {
        tryScrollBackToTop();
    }

    private boolean tryToPerformRefresh() {
        if (this.mStatus != 2) {
            return false;
        }
        if ((this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading() && isAutoRefresh()) || this.mPtrIndicator.isOverOffsetToRefresh()) {
            this.mStatus = (byte) 3;
            performRefresh();
        }
        return false;
    }

    private void performRefresh() {
        this.mLoadingStartTime = System.currentTimeMillis();
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIRefreshBegin(this);
            if (DEBUG) {
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshBegin");
            }
        }
        if (this.mPtrHandler != null) {
            if (this.mPtrIndicator.isHeader()) {
                this.mPtrHandler.onRefreshBegin(this);
                return;
            }
            PtrHandler ptrHandler = this.mPtrHandler;
            if (ptrHandler instanceof PtrHandler2) {
                ((PtrHandler2) ptrHandler).onLoadMoreBegin(this);
            }
        }
    }

    private boolean tryToNotifyReset() {
        byte b = this.mStatus;
        if ((b == 4 || b == 2) && this.mPtrIndicator.isInStartPosition()) {
            if (this.mPtrUIHandlerHolder.hasHandler()) {
                this.mPtrUIHandlerHolder.onUIReset(this);
                if (DEBUG) {
                    PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIReset");
                }
            }
            this.mStatus = (byte) 1;
            clearFlag();
            return true;
        }
        return false;
    }

    protected void onPtrScrollAbort() {
        if (this.mPtrIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "call onRelease after scroll abort");
            }
            onRelease(true);
        }
    }

    protected void onPtrScrollFinish() {
        if (this.mPtrIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "call onRelease after scroll finish");
            }
            onRelease(true);
        }
    }

    public final void refreshComplete() {
        if (DEBUG) {
            PtrCLog.i(this.LOG_TAG, "refreshComplete");
        }
        PtrUIHandlerHook ptrUIHandlerHook = this.mRefreshCompleteHook;
        if (ptrUIHandlerHook != null) {
            ptrUIHandlerHook.reset();
        }
        int currentTimeMillis = (int) (this.mLoadingMinTime - (System.currentTimeMillis() - this.mLoadingStartTime));
        if (currentTimeMillis <= 0) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "performRefreshComplete at once");
            }
            performRefreshComplete();
            return;
        }
        postDelayed(this.mPerformRefreshCompleteDelay, currentTimeMillis);
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "performRefreshComplete after delay: %s", Integer.valueOf(currentTimeMillis));
        }
    }

    public void performRefreshComplete() {
        this.mStatus = (byte) 4;
        if (!this.mScrollChecker.mIsRunning || !isAutoRefresh()) {
            notifyUIRefreshComplete(false);
        } else if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", Boolean.valueOf(this.mScrollChecker.mIsRunning), Integer.valueOf(this.mFlag));
        }
    }

    public void notifyUIRefreshComplete(boolean z) {
        if (this.mPtrIndicator.hasLeftStartPosition() && !z && this.mRefreshCompleteHook != null) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "notifyUIRefreshComplete mRefreshCompleteHook run.");
            }
            this.mRefreshCompleteHook.takeOver();
            return;
        }
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            if (DEBUG) {
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshComplete");
            }
            this.mPtrUIHandlerHolder.onUIRefreshComplete(this, this.mPtrIndicator.isHeader());
        }
        this.mPtrIndicator.onUIRefreshComplete();
        tryScrollBackToTopAfterComplete();
        tryToNotifyReset();
    }

    public void autoRefresh() {
        autoRefresh(true, true);
    }

    public void autoRefresh(boolean z) {
        autoRefresh(z, true);
    }

    public void autoLoadMore() {
        autoRefresh(true, false);
    }

    public void autoLoadMore(boolean z) {
        autoRefresh(z, false);
    }

    public void autoRefresh(boolean z, boolean z2) {
        if (this.mStatus != 1) {
            return;
        }
        this.mFlag |= z ? FLAG_AUTO_REFRESH_AT_ONCE : FLAG_AUTO_REFRESH_BUT_LATER;
        this.mStatus = (byte) 2;
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
            if (DEBUG) {
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag));
            }
        }
        this.mPtrIndicator.setIsHeader(z2);
        this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToRefresh(), z2 ? this.mDurationToCloseHeader : this.mDurationToCloseFooter);
        if (z) {
            this.mStatus = (byte) 3;
            performRefresh();
        }
    }

    public void addPtrUIHandler(PtrUIHandler ptrUIHandler) {
        PtrUIHandlerHolder.addHandler(this.mPtrUIHandlerHolder, ptrUIHandler);
    }

    public void removePtrUIHandler(PtrUIHandler ptrUIHandler) {
        this.mPtrUIHandlerHolder = PtrUIHandlerHolder.removeHandler(this.mPtrUIHandlerHolder, ptrUIHandler);
    }

    public void setPtrIndicator(PtrIndicator ptrIndicator) {
        PtrIndicator ptrIndicator2 = this.mPtrIndicator;
        if (ptrIndicator2 != null && ptrIndicator2 != ptrIndicator) {
            ptrIndicator.convertFrom(ptrIndicator2);
        }
        this.mPtrIndicator = ptrIndicator;
    }

    public float getResistanceHeader() {
        return this.mPtrIndicator.getResistanceHeader();
    }

    public float getResistanceFooter() {
        return this.mPtrIndicator.getResistanceFooter();
    }

    public void setResistance(float f) {
        setResistanceHeader(f);
        setResistanceFooter(f);
    }

    public void setResistanceHeader(float f) {
        this.mPtrIndicator.setResistanceHeader(f);
    }

    public void setResistanceFooter(float f) {
        this.mPtrIndicator.setResistanceFooter(f);
    }

    public void setDurationToBack(int i) {
        setDurationToBackHeader(i);
        setDurationToBackFooter(i);
    }

    public void setDurationToClose(int i) {
        setDurationToCloseHeader(i);
        setDurationToCloseFooter(i);
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(f);
    }

    public int getOffsetToRefresh() {
        return this.mPtrIndicator.getOffsetToRefresh();
    }

    public void setOffsetToRefresh(int i) {
        this.mPtrIndicator.setOffsetToRefresh(i);
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.mPtrIndicator.getRatioOfHeaderToHeightRefresh();
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading();
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        this.mPtrIndicator.setOffsetToKeepHeaderWhileLoading(i);
    }

    public void setHeaderView(View view) {
        View view2 = this.mHeaderView;
        if (view2 != null && view != null && view2 != view) {
            removeView(view2);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        }
        this.mHeaderView = view;
        addView(view);
    }

    public void setFooterView(View view) {
        View view2 = this.mFooterView;
        if (view2 != null && view != null && view2 != view) {
            removeView(view2);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        }
        this.mFooterView = view;
        addView(view);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    private void sendCancelEvent() {
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "send cancel event");
        }
        MotionEvent motionEvent = this.mLastMoveEvent;
        if (motionEvent == null) {
            return;
        }
        dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ViewConfiguration.getLongPressTimeout(), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    private void sendDownEvent() {
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "send down event");
        }
        MotionEvent motionEvent = this.mLastMoveEvent;
        dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public class ScrollChecker implements Runnable {
        private boolean mIsRunning = false;
        private int mLastFlingY;
        private Scroller mScroller;
        private int mStart;
        private int mTo;

        public ScrollChecker() {
            this.mScroller = new Scroller(getContext());
        }

        @Override
        public void run() {
            boolean z = !this.mScroller.computeScrollOffset() || this.mScroller.isFinished();
            int currY = this.mScroller.getCurrY();
            int i = currY - this.mLastFlingY;
            if (PtrFrameLayout.DEBUG && i != 0) {
                PtrCLog.v(LOG_TAG, "scroll: %s, start: %s, to: %s, currentPos: %s, current :%s, last: %s, delta: %s", Boolean.valueOf(z), Integer.valueOf(this.mStart), Integer.valueOf(this.mTo), Integer.valueOf(mPtrIndicator.getCurrentPosY()), Integer.valueOf(currY), Integer.valueOf(this.mLastFlingY), Integer.valueOf(i));
            }
            if (!z) {
                this.mLastFlingY = currY;
                if (mPtrIndicator.isHeader()) {
                    moveHeaderPos(i);
                } else {
                    moveFooterPos(-i);
                }
                post(this);
                return;
            }
            finish();
        }

        public boolean isRunning() {
            return this.mScroller.isFinished();
        }

        private void finish() {
            if (PtrFrameLayout.DEBUG) {
                PtrCLog.v(LOG_TAG, "finish, currentPos:%s", Integer.valueOf(mPtrIndicator.getCurrentPosY()));
            }
            reset();
            onPtrScrollFinish();
        }

        private void reset() {
            this.mIsRunning = false;
            this.mLastFlingY = 0;
            removeCallbacks(this);
        }

        public void destroy() {
            reset();
            if (this.mScroller.isFinished()) {
                return;
            }
            this.mScroller.forceFinished(true);
        }

        public void abortIfWorking() {
            if (this.mIsRunning) {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.forceFinished(true);
                }
                onPtrScrollAbort();
                reset();
            }
        }

        public void tryToScrollTo(int i, int i2) {
            if (mPtrIndicator.isAlreadyHere(i)) {
                return;
            }
            int currentPosY = mPtrIndicator.getCurrentPosY();
            this.mStart = currentPosY;
            this.mTo = i;
            int i3 = i - currentPosY;
            if (PtrFrameLayout.DEBUG) {
                PtrCLog.d(LOG_TAG, "tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(this.mStart), Integer.valueOf(i3), Integer.valueOf(i));
            }
            removeCallbacks(this);
            this.mLastFlingY = 0;
            if (!this.mScroller.isFinished()) {
                this.mScroller.forceFinished(true);
            }
            if (i2 > 0) {
                this.mScroller.startScroll(0, 0, 0, i3, i2);
                post(this);
                this.mIsRunning = true;
                return;
            }
            if (mPtrIndicator.isHeader()) {
                moveHeaderPos(i3);
            } else {
                moveFooterPos(-i3);
            }
            this.mIsRunning = false;
        }
    }
}
