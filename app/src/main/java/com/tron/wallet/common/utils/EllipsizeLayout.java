package com.tron.wallet.common.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
public class EllipsizeLayout extends LinearLayout {
    public EllipsizeLayout(Context context) {
        super(context);
    }

    public EllipsizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EllipsizeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        boolean z;
        if (getOrientation() == 0 && View.MeasureSpec.getMode(i) == MeasureSpec.AT_MOST) {
            int childCount = getChildCount();
            TextView textView = null;
            int i3 = 0;
            boolean z2 = false;
            int i4 = 0;
            while (true) {
                if (i3 >= childCount || z2) {
                    break;
                }
                View childAt = getChildAt(i3);
                if (childAt != null && childAt.getVisibility() != 8) {
                    if (childAt instanceof TextView) {
                        TextView textView2 = (TextView) childAt;
                        if (textView2.getEllipsize() != null) {
                            if (textView == null) {
                                textView2.setMaxWidth(Integer.MAX_VALUE);
                                textView = textView2;
                            } else {
                                z2 = true;
                            }
                        }
                    }
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                    z2 |= layoutParams.weight > 0.0f;
                    measureChildWithMargins(childAt, i, 0, i2, 0);
                    i4 += childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                }
                i3++;
            }
            if (textView != null && i4 != 0) {
                z = false;
            }
            boolean z3 = z2 | z;
            int size = View.MeasureSpec.getSize(i);
            if (!z3 && i4 > size) {
                int measuredWidth = textView.getMeasuredWidth() - (i4 - size);
                textView.setMaxWidth(measuredWidth >= 0 ? measuredWidth : 0);
            }
        }
        super.onMeasure(i, i2);
    }
}
