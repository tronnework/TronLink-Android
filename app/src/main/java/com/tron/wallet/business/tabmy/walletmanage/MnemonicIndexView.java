package com.tron.wallet.business.tabmy.walletmanage;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MnemonicIndexView extends ViewGroup {
    private static final int COUNT = 12;
    private static final int NUM_COLUMNS = 3;
    private static final float PADDING_IN_DP = 10.0f;
    private int prevSelectChild;
    private List<Integer> prevSelectChildList;

    public MnemonicIndexView(Context context) {
        this(context, null);
    }

    public MnemonicIndexView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MnemonicIndexView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.prevSelectChild = -1;
        this.prevSelectChildList = new ArrayList();
        addChildren();
        updateSelectedChild(this.prevSelectChild);
    }

    private void addChildren() {
        int i = 0;
        while (i < 12) {
            addView(View.inflate(getContext(), R.layout.layout_mnemonic_indicies, null));
            i++;
            ((TextView) Objects.requireNonNull(getChildTextView(i))).setText(String.valueOf(i));
        }
    }

    private TextView getChildTextView(int i) {
        if (i < 0 || i >= 12) {
            return null;
        }
        return (TextView) getChildAt(i).findViewById(R.id.indices_tv_number);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int dip2px = UIUtils.dip2px(getContext(), PADDING_IN_DP);
        for (int i5 = 0; i5 < 12; i5++) {
            View childAt = getChildAt(i5);
            int i6 = childAt.getLayoutParams().width;
            int i7 = childAt.getLayoutParams().height;
            int i8 = (i5 % 3) * (i6 + dip2px);
            int i9 = (i5 / 3) * (i7 + dip2px);
            childAt.layout(i8, i9, i6 + i8, i7 + i9);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int dip2px = UIUtils.dip2px(getContext(), PADDING_IN_DP);
        int i3 = (measuredWidth - (dip2px * 2)) / 3;
        int i4 = (measuredHeight - (dip2px * 3)) / 4;
        measureChildren(View.MeasureSpec.makeMeasureSpec(i3, View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec(i4, View.MeasureSpec.getMode(i2)));
        for (int i5 = 0; i5 < 12; i5++) {
            View childAt = getChildAt(i5);
            childAt.getLayoutParams().width = i3;
            childAt.getLayoutParams().height = i4;
        }
    }

    public void updateSelectedChild(int i) {
        TextView childTextView;
        if (i < 0 || i >= 12 || (childTextView = getChildTextView(i)) == null) {
            return;
        }
        childTextView.setTextColor(getContext().getResources().getColor(R.color.black_23));
        childTextView.setTypeface(Typeface.defaultFromStyle(1));
        childTextView.setTextSize(20.0f);
        int i2 = this.prevSelectChild;
        if (i2 >= 0 && i2 < 12) {
            TextView childTextView2 = getChildTextView(i2);
            childTextView2.setTextColor(getContext().getResources().getColor(R.color.gray_9B));
            childTextView2.setTextSize(14.0f);
            childTextView2.setTypeface(Typeface.defaultFromStyle(1));
        }
        this.prevSelectChild = i;
    }

    public void updateSelectedChildes(List<Integer> list) {
        int intValue;
        TextView childTextView;
        if (list.size() == 0) {
            return;
        }
        if (this.prevSelectChildList.size() != 0) {
            Iterator<Integer> it = this.prevSelectChildList.iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                int intValue2 = next == null ? -1 : next.intValue();
                if (intValue2 >= 0 && intValue2 < 12) {
                    TextView childTextView2 = getChildTextView(intValue2);
                    childTextView2.setTextColor(getContext().getResources().getColor(R.color.gray_9B));
                    childTextView2.setTextSize(14.0f);
                    childTextView2.setTypeface(Typeface.defaultFromStyle(1));
                }
            }
            this.prevSelectChildList.clear();
        }
        Iterator<Integer> it2 = list.iterator();
        while (it2.hasNext() && (intValue = it2.next().intValue()) >= 0 && intValue < 12 && (childTextView = getChildTextView(intValue)) != null) {
            childTextView.setTextColor(getContext().getResources().getColor(R.color.black_23));
            childTextView.setTypeface(Typeface.defaultFromStyle(1));
            childTextView.setTextSize(20.0f);
            this.prevSelectChildList.add(Integer.valueOf(intValue));
        }
    }
}
