package com.tron.wallet.business.tabdapp.browser.grid.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.DraggableItemViewHolder;
import com.tron.wallet.business.tabdapp.browser.grid.draggable.DraggableItemState;
public abstract class AbstractDraggableItemViewHolder extends RecyclerView.ViewHolder implements DraggableItemViewHolder {
    private final DraggableItemState mDragState;

    @Override
    public DraggableItemState getDragState() {
        return this.mDragState;
    }

    public AbstractDraggableItemViewHolder(View view) {
        super(view);
        this.mDragState = new DraggableItemState();
    }

    @Override
    public void setDragStateFlags(int i) {
        this.mDragState.setFlags(i);
    }

    @Override
    public int getDragStateFlags() {
        return this.mDragState.getFlags();
    }
}
