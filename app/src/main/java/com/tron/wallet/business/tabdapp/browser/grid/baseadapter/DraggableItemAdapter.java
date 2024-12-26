package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.tron.wallet.business.tabdapp.browser.grid.base.ItemDraggableRange;
public interface DraggableItemAdapter<T extends RecyclerView.ViewHolder> {
    boolean onCheckCanDrop(int i, int i2);

    boolean onCheckCanStartDrag(T t, int i, int i2, int i3);

    ItemDraggableRange onGetItemDraggableRange(T t, int i);

    void onItemDragFinished(int i, int i2, boolean z);

    void onItemDragStarted(int i);

    void onMoveItem(int i, int i2);
}
