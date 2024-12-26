package com.tron.wallet.business.tabdapp.browser.search;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.tron.wallet.common.utils.UIUtils;
import java.util.ArrayList;
public class HistoryFlowLayout extends ViewGroup {
    private static final int horSpacing = UIUtils.dip2px(10.0f);
    private static final int verSpacing = UIUtils.dip2px(5.0f);
    private ArrayList<Integer> listLineHeight;
    private ArrayList<ArrayList<View>> listlistView;
    private int maxLinesCount;

    public void setMaxLinesCount(int i) {
        this.maxLinesCount = i;
    }

    public HistoryFlowLayout(Context context) {
        super(context);
        this.maxLinesCount = 2;
    }

    public HistoryFlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxLinesCount = 2;
    }

    public HistoryFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxLinesCount = 2;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.listlistView = new ArrayList<>();
        this.listLineHeight = new ArrayList<>();
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ArrayList<View> arrayList = new ArrayList<>();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(i, childAt.getPaddingLeft() + childAt.getPaddingRight(), marginLayoutParams.width), getChildMeasureSpec(i2, childAt.getPaddingTop() + childAt.getPaddingBottom(), marginLayoutParams.height));
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i8 = horSpacing;
            if (i3 + measuredWidth + i8 > size && this.listlistView.size() < 2) {
                this.listlistView.add(arrayList);
                this.listLineHeight.add(Integer.valueOf(i5));
                int i9 = verSpacing;
                i4 = Math.max(i3 + i9, i4 + i9);
                i6 += i5;
                arrayList = new ArrayList<>();
                i3 = 0;
                i5 = 0;
            }
            arrayList.add(childAt);
            i3 += measuredWidth + i8;
            i5 = Math.max(i5, measuredHeight + verSpacing);
            if (i7 == getChildCount() - 1 && this.listlistView.size() < 2) {
                this.listlistView.add(arrayList);
                this.listLineHeight.add(Integer.valueOf(i5));
                i6 += i8 + i5;
            }
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        Log.i("EXACTLY", size + "\t" + size2);
        if (mode != MeasureSpec.AT_MOST) {
            size = i4;
        }
        if (mode2 != MeasureSpec.AT_MOST) {
            size2 = i6;
        }
        setMeasuredDimension(size, size2);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < this.listlistView.size(); i5++) {
            ArrayList<View> arrayList = this.listlistView.get(i5);
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                View view = arrayList.get(i6);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int i7 = paddingLeft + marginLayoutParams.leftMargin;
                int measuredWidth = view.getMeasuredWidth() + i7;
                int i8 = marginLayoutParams.topMargin + paddingTop;
                int measuredHeight = view.getMeasuredHeight() + i8;
                Log.i("onLayoutView1", i5 + "\t" + i7 + "\t" + measuredWidth + "\t" + i8 + "\t" + measuredHeight);
                StringBuilder sb = new StringBuilder();
                sb.append(i5);
                sb.append("\t");
                sb.append(view.getMeasuredWidth());
                sb.append("\t");
                sb.append(view.getMeasuredHeight());
                Log.i("onLayoutView2", sb.toString());
                view.layout(i7, i8, measuredWidth, measuredHeight);
                paddingLeft = marginLayoutParams.rightMargin + measuredWidth + horSpacing;
            }
            paddingTop += this.listLineHeight.get(i5).intValue() + verSpacing;
            paddingLeft = getPaddingLeft();
        }
    }
}
