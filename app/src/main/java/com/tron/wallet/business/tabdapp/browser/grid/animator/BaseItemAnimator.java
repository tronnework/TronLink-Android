package com.tron.wallet.business.tabdapp.browser.grid.animator;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
public abstract class BaseItemAnimator extends SimpleItemAnimator {
    private ItemAnimatorListener mListener;

    public interface ItemAnimatorListener {
        void onAddFinished(RecyclerView.ViewHolder viewHolder);

        void onChangeFinished(RecyclerView.ViewHolder viewHolder);

        void onMoveFinished(RecyclerView.ViewHolder viewHolder);

        void onRemoveFinished(RecyclerView.ViewHolder viewHolder);
    }

    public boolean debugLogEnabled() {
        return false;
    }

    protected void onAddFinishedImpl(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onAddStartingImpl(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onChangeFinishedImpl(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    protected void onChangeStartingItem(RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    protected void onMoveFinishedImpl(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onMoveStartingImpl(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onRemoveFinishedImpl(RecyclerView.ViewHolder viewHolder) {
    }

    protected void onRemoveStartingImpl(RecyclerView.ViewHolder viewHolder) {
    }

    public void setListener(ItemAnimatorListener itemAnimatorListener) {
        this.mListener = itemAnimatorListener;
    }

    @Override
    public final void onAddStarting(RecyclerView.ViewHolder viewHolder) {
        onAddStartingImpl(viewHolder);
    }

    @Override
    public final void onAddFinished(RecyclerView.ViewHolder viewHolder) {
        onAddFinishedImpl(viewHolder);
        ItemAnimatorListener itemAnimatorListener = this.mListener;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onAddFinished(viewHolder);
        }
    }

    @Override
    public final void onChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z) {
        onChangeStartingItem(viewHolder, z);
    }

    @Override
    public final void onChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z) {
        onChangeFinishedImpl(viewHolder, z);
        ItemAnimatorListener itemAnimatorListener = this.mListener;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onChangeFinished(viewHolder);
        }
    }

    @Override
    public final void onMoveStarting(RecyclerView.ViewHolder viewHolder) {
        onMoveStartingImpl(viewHolder);
    }

    @Override
    public final void onMoveFinished(RecyclerView.ViewHolder viewHolder) {
        onMoveFinishedImpl(viewHolder);
        ItemAnimatorListener itemAnimatorListener = this.mListener;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onMoveFinished(viewHolder);
        }
    }

    @Override
    public final void onRemoveStarting(RecyclerView.ViewHolder viewHolder) {
        onRemoveStartingImpl(viewHolder);
    }

    @Override
    public final void onRemoveFinished(RecyclerView.ViewHolder viewHolder) {
        onRemoveFinishedImpl(viewHolder);
        ItemAnimatorListener itemAnimatorListener = this.mListener;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onRemoveFinished(viewHolder);
        }
    }

    public boolean dispatchFinishedWhenDone() {
        if (isRunning()) {
            return false;
        }
        dispatchAnimationsFinished();
        return true;
    }
}
