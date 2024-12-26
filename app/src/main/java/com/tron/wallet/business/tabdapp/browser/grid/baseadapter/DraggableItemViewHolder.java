package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import com.tron.wallet.business.tabdapp.browser.grid.draggable.DraggableItemState;
public interface DraggableItemViewHolder {
    DraggableItemState getDragState();

    int getDragStateFlags();

    void setDragStateFlags(int i);
}
