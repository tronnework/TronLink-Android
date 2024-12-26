package com.tron.wallet.business.tabdapp.browser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import com.tron.wallet.common.components.MyWebView;
public class NestedWebView extends MyWebView implements NestedScrollingChild {
    private NestedScrollingChildHelper mChildHelper;
    private int mLastY;
    private int mNestedOffsetY;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;

    public NestedWebView(Context context) {
        this(context, null);
    }

    public NestedWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842885);
    }

    public NestedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(obtain);
        if (actionMasked == 0) {
            this.mNestedOffsetY = 0;
        }
        int y = (int) obtain.getY();
        obtain.offsetLocation(0.0f, this.mNestedOffsetY);
        if (actionMasked == 0) {
            boolean onTouchEvent = super.onTouchEvent(obtain);
            this.mLastY = y;
            startNestedScroll(2);
            return onTouchEvent;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                int i2 = this.mLastY - y;
                if (dispatchNestedPreScroll(0, i2, this.mScrollConsumed, this.mScrollOffset)) {
                    i2 -= this.mScrollConsumed[1];
                    this.mLastY = y - this.mScrollOffset[1];
                    obtain.offsetLocation(0.0f, -i);
                    this.mNestedOffsetY += this.mScrollOffset[1];
                }
                int i3 = i2;
                boolean onTouchEvent2 = super.onTouchEvent(obtain);
                if (dispatchNestedScroll(0, this.mScrollOffset[1], 0, i3, this.mScrollConsumed)) {
                    obtain.offsetLocation(0.0f, this.mScrollOffset[1]);
                    int i4 = this.mNestedOffsetY;
                    int i5 = this.mScrollOffset[1];
                    this.mNestedOffsetY = i4 + i5;
                    this.mLastY -= i5;
                    return onTouchEvent2;
                }
                return onTouchEvent2;
            } else if (actionMasked != 3) {
                return false;
            }
        }
        boolean onTouchEvent3 = super.onTouchEvent(obtain);
        stopNestedScroll();
        return onTouchEvent3;
    }

    @Override
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    @Override
    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }
}
