package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
public class VerticalScrollView extends ScrollView {
    private int maxY;

    public VerticalScrollView(Context context) {
        super(context);
    }

    public VerticalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VerticalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public VerticalScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.maxY = getChildAt(0).getMeasuredHeight() - getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 1) {
            getParent().getParent().requestDisallowInterceptTouchEvent(false);
        } else if (action == 2) {
            if (getScrollY() > 0 && getScrollY() < this.maxY) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
