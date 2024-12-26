package com.tron.wallet.business.tabdapp.home.adapter;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.common.utils.UIUtils;
public class RecommendListItemDecoration extends RecyclerView.ItemDecoration {
    static final int COUNT_PER_PAGE = 4;
    static final int screenWidth = UIUtils.getScreenWidth(AppContextUtil.getContext());
    static final int itemWidth = UIUtils.dip2px(50.0f);
    static final int paddingHorizontal = UIUtils.dip2px(15.0f);
    static final int extraItemWidth = UIUtils.dip2px(15.0f);

    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (itemCount <= 4) {
            int i2 = screenWidth;
            int i3 = paddingHorizontal;
            i = (((i2 - (i3 * 2)) - (itemWidth * 4)) - (i3 * 2)) / 3;
        } else {
            int i4 = screenWidth;
            int i5 = paddingHorizontal;
            i = ((((i4 - i5) - extraItemWidth) - (itemWidth * 4)) - (i5 * 2)) / 4;
        }
        if (childAdapterPosition == 0) {
            rect.set(paddingHorizontal, 0, 0, 0);
        } else if (childAdapterPosition == itemCount - 1) {
            rect.set(i, 0, paddingHorizontal, 0);
        } else {
            rect.set(i, 0, 0, 0);
        }
    }
}
