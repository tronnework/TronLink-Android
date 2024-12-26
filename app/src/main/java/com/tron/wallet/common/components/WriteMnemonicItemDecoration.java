package com.tron.wallet.common.components;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.common.utils.UIUtils;
public class WriteMnemonicItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view) + 1;
        int ceil = (int) Math.ceil(childAdapterPosition / 3.0f);
        int i = childAdapterPosition % 3;
        if (ceil != 0) {
            rect.top = UIUtils.dip2px(10.0f);
        } else {
            rect.top = 0;
        }
        if (i == 2 || i == 0) {
            rect.left = UIUtils.dip2px(10.0f);
            rect.right = UIUtils.dip2px(0.0f);
            return;
        }
        rect.left = 0;
        rect.right = 0;
    }
}
