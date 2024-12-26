package com.tron.wallet.business.tabdapp.browser.grid.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.common.utils.UIUtils;
public class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;

    public LinearSpacingItemDecoration(Context context) {
        this.spacing = UIUtils.dip2px(11.0f);
    }

    public LinearSpacingItemDecoration(int i) {
        this.spacing = i;
    }

    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        rect.top = this.spacing;
        if (childAdapterPosition % 2 == 0) {
            rect.right = this.spacing / 2;
            rect.left = this.spacing;
            return;
        }
        rect.left = this.spacing / 2;
        rect.right = this.spacing;
    }
}
