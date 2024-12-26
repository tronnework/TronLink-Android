package com.tron.wallet.business.tabdapp.browser.grid.impl;

import androidx.recyclerview.widget.RecyclerView;
public abstract class ItemAnimationInfo {
    public abstract void clear(RecyclerView.ViewHolder viewHolder);

    public abstract RecyclerView.ViewHolder getAvailableViewHolder();
}
