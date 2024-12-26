package com.tron.wallet.common.components.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.tron.wallet.common.utils.UIUtils;
public class DotViewPagerIndicator extends ViewGroup {
    private static final int DEFAULT_ITEM_SIZE = UIUtils.dip2px(5.0f);

    public DotViewPagerIndicator(Context context) {
        this(context, null);
    }

    public DotViewPagerIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotViewPagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
    }

    private void createIndicators(int i, int i2) {
        removeAllViews();
        int i3 = 0;
        while (i3 < i) {
            DotIndicatorView dotIndicatorView = new DotIndicatorView(getContext());
            dotIndicatorView.setSelected(i3 == i2);
            dotIndicatorView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            addView(dotIndicatorView);
            i3++;
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = DEFAULT_ITEM_SIZE;
        measureChildren(View.MeasureSpec.makeMeasureSpec(i3, i), View.MeasureSpec.makeMeasureSpec(i3, i2));
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = i - getPaddingLeft();
        int paddingTop = i2 - getPaddingTop();
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            int measuredWidth = (childAt.getMeasuredWidth() * i5 * 2) + paddingLeft;
            childAt.layout(measuredWidth, paddingTop, childAt.getMeasuredWidth() + measuredWidth, childAt.getMeasuredHeight() + paddingTop);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            DotIndicatorView dotIndicatorView = (DotIndicatorView) getChildAt(i);
            dotIndicatorView.drawSelf(canvas, dotIndicatorView.getMeasuredWidth() * i * 2, 0);
        }
    }

    public void onChange(int i, int i2) {
        createIndicators(i, i2);
    }

    public void onPositionChange(int i) {
        final int childCount = i % getChildCount();
        postOnAnimation(new Runnable() {
            @Override
            public final void run() {
                lambda$onPositionChange$0(childCount);
            }
        });
    }

    public void lambda$onPositionChange$0(int i) {
        int i2 = 0;
        while (i2 < getChildCount()) {
            getChildAt(i2).setSelected(i == i2);
            i2++;
        }
        postInvalidate();
    }
}
