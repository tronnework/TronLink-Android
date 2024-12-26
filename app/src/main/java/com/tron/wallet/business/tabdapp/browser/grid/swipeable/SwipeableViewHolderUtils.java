package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
class SwipeableViewHolderUtils {
    SwipeableViewHolderUtils() {
    }

    public static View getSwipeableContainerView(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            return getSwipeableContainerView((SwipeableItemViewHolder) viewHolder);
        }
        return null;
    }

    public static View getSwipeableContainerView(SwipeableItemViewHolder swipeableItemViewHolder) {
        if (swipeableItemViewHolder instanceof RecyclerView.ViewHolder) {
            View swipeableContainerView = swipeableItemViewHolder.getSwipeableContainerView();
            if (swipeableContainerView != ((RecyclerView.ViewHolder) swipeableItemViewHolder).itemView) {
                return swipeableContainerView;
            }
            throw new IllegalStateException("Inconsistency detected! getSwipeableContainerView() returns itemView");
        }
        return null;
    }
}
