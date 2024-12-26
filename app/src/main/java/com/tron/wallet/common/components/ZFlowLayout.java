package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
public class ZFlowLayout extends ViewGroup {
    private List<List<View>> mAllChildViews;
    private List<Integer> mLineHeight;

    public ZFlowLayout(Context context) {
        this(context, null);
    }

    public ZFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAllChildViews = new ArrayList();
        this.mLineHeight = new ArrayList();
    }

    @Override
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            measureChild(childAt, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int i8 = size2;
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i9 = i4 + measuredWidth;
            if (i9 > size) {
                i5 = Math.max(i5, i4);
                i7 += i6;
                i6 = measuredHeight;
                i4 = measuredWidth;
            } else {
                i6 = Math.max(i6, measuredHeight);
                i4 = i9;
            }
            if (i3 == childCount - 1) {
                i7 += i6;
                i5 = Math.max(i5, i4);
            }
            i3++;
            size2 = i8;
        }
        int i10 = size2;
        if (mode != MeasureSpec.AT_MOST) {
            size = i5;
        }
        setMeasuredDimension(size, mode2 == MeasureSpec.AT_MOST ? i10 : i7);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mAllChildViews.clear();
        this.mLineHeight.clear();
        int width = getWidth();
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredWidth + i6 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin > width) {
                this.mLineHeight.add(Integer.valueOf(i5));
                this.mAllChildViews.add(arrayList);
                i5 = marginLayoutParams.bottomMargin + marginLayoutParams.topMargin + measuredHeight;
                arrayList = new ArrayList();
                i6 = 0;
            }
            i6 += measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            i5 = Math.max(i5, measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
            arrayList.add(childAt);
        }
        this.mLineHeight.add(Integer.valueOf(i5));
        this.mAllChildViews.add(arrayList);
        int size = this.mAllChildViews.size();
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            List<View> list = this.mAllChildViews.get(i9);
            int intValue = this.mLineHeight.get(i9).intValue();
            int i10 = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                View view = list.get(i11);
                if (view.getVisibility() != 8) {
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i12 = marginLayoutParams2.leftMargin + i10;
                    int i13 = marginLayoutParams2.topMargin + i8;
                    view.layout(i12, i13, view.getMeasuredWidth() + i12, view.getMeasuredHeight() + i13);
                    i10 += view.getMeasuredWidth() + marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin;
                }
            }
            i8 += intValue;
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }
}
