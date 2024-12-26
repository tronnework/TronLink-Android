package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
public interface SwipeableItemAdapter<T extends RecyclerView.ViewHolder> {
    int onGetSwipeReactionType(T t, int i, int i2, int i3);

    void onSetSwipeBackground(T t, int i, int i2);

    SwipeResultAction onSwipeItem(T t, int i, int i2);

    void onSwipeItemStarted(T t, int i);
}
