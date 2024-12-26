package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
public class NoScrollViewPager extends ViewPager {
    private boolean noScroll;

    public void setNoScroll(boolean z) {
        this.noScroll = z;
    }

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.noScroll = true;
    }

    public NoScrollViewPager(Context context) {
        super(context);
        this.noScroll = true;
    }

    @Override
    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override
    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i, z);
    }

    @Override
    public void setCurrentItem(int i) {
        super.setCurrentItem(i);
    }
}
