package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
public interface WrappedAdapter<VH extends RecyclerView.ViewHolder> {
    boolean onFailedToRecycleView(VH vh, int i);

    void onViewAttachedToWindow(VH vh, int i);

    void onViewDetachedFromWindow(VH vh, int i);

    void onViewRecycled(VH vh, int i);
}
