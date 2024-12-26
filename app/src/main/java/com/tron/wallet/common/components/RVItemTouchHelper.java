package com.tron.wallet.common.components;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
public class RVItemTouchHelper extends ItemTouchHelper {
    private ItemTouchHelper.Callback callback;
    private boolean draggable;
    private boolean swipeEnable;

    public interface ItemTouchListener {
        void onMove(RecyclerView recyclerView, int i, int i2);

        void onSelect(RecyclerView.ViewHolder viewHolder, boolean z);
    }

    public RVItemTouchHelper(ItemTouchHelper.Callback callback) {
        super(callback);
        this.callback = callback;
    }

    public RVItemTouchHelper(ItemTouchListener itemTouchListener) {
        this(new ItemTouchCallback());
        setItemTouchListener(itemTouchListener);
        setDraggable(true);
        setSwipeEnable(false);
    }

    public void setItemTouchListener(ItemTouchListener itemTouchListener) {
        ItemTouchHelper.Callback callback = this.callback;
        if (callback == null || !(callback instanceof ItemTouchCallback)) {
            return;
        }
        ((ItemTouchCallback) callback).setListener(itemTouchListener);
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
        ItemTouchHelper.Callback callback = this.callback;
        if (callback == null || !(callback instanceof ItemTouchCallback)) {
            return;
        }
        ((ItemTouchCallback) callback).setDefaultDragDirs(z ? 3 : 0);
    }

    public void setSwipeEnable(boolean z) {
        ItemTouchHelper.Callback callback;
        this.swipeEnable = z;
        if (z || (callback = this.callback) == null || !(callback instanceof ItemTouchCallback)) {
            return;
        }
        ((ItemTouchCallback) callback).setDefaultSwipeDirs(0);
    }

    public static class ItemTouchCallback extends ItemTouchHelper.SimpleCallback {
        private ItemTouchListener listener;

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }

        void setListener(ItemTouchListener itemTouchListener) {
            this.listener = itemTouchListener;
        }

        public ItemTouchCallback() {
            this(3, 12);
        }

        public ItemTouchCallback(int i, int i2) {
            super(i, i2);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            this.listener.onMove(recyclerView, viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
            return false;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            if (i == 0) {
                super.onSelectedChanged(viewHolder, i);
            } else {
                this.listener.onSelect(viewHolder, true);
            }
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            this.listener.onSelect(viewHolder, false);
        }
    }
}
