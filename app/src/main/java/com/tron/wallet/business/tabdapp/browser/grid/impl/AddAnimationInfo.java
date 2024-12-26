package com.tron.wallet.business.tabdapp.browser.grid.impl;

import androidx.recyclerview.widget.RecyclerView;
public class AddAnimationInfo extends ItemAnimationInfo {
    public RecyclerView.ViewHolder holder;

    @Override
    public void clear(RecyclerView.ViewHolder viewHolder) {
        if (this.holder == viewHolder) {
            this.holder = null;
        }
    }

    @Override
    public RecyclerView.ViewHolder getAvailableViewHolder() {
        return this.holder;
    }

    public AddAnimationInfo(RecyclerView.ViewHolder viewHolder) {
        this.holder = viewHolder;
    }

    public String toString() {
        return "AddAnimationInfo{holder=" + this.holder + '}';
    }
}
