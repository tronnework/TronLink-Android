package com.tron.wallet.business.tabdapp.browser.grid.impl;

import androidx.recyclerview.widget.RecyclerView;
public class MoveAnimationInfo extends ItemAnimationInfo {
    public final int fromX;
    public final int fromY;
    public RecyclerView.ViewHolder holder;
    public final int toX;
    public final int toY;

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

    public MoveAnimationInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        this.holder = viewHolder;
        this.fromX = i;
        this.fromY = i2;
        this.toX = i3;
        this.toY = i4;
    }

    public String toString() {
        return "MoveAnimationInfo{holder=" + this.holder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
    }
}
