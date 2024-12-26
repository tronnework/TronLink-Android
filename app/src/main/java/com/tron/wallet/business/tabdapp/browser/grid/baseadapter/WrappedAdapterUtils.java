package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import androidx.recyclerview.widget.RecyclerView;
public class WrappedAdapterUtils {
    private WrappedAdapterUtils() {
    }

    public static void invokeOnViewRecycled(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int i) {
        if (adapter instanceof WrapperAdapter) {
            ((WrapperAdapter) adapter).onViewRecycled(viewHolder, i);
        } else {
            adapter.onViewRecycled(viewHolder);
        }
    }

    public static boolean invokeOnFailedToRecycleView(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int i) {
        if (adapter instanceof WrappedAdapter) {
            return ((WrappedAdapter) adapter).onFailedToRecycleView(viewHolder, i);
        }
        return adapter.onFailedToRecycleView(viewHolder);
    }

    public static void invokeOnViewAttachedToWindow(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int i) {
        if (adapter instanceof WrappedAdapter) {
            ((WrappedAdapter) adapter).onViewAttachedToWindow(viewHolder, i);
        } else {
            adapter.onViewAttachedToWindow(viewHolder);
        }
    }

    public static void invokeOnViewDetachedFromWindow(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int i) {
        if (adapter instanceof WrappedAdapter) {
            ((WrappedAdapter) adapter).onViewDetachedFromWindow(viewHolder, i);
        } else {
            adapter.onViewDetachedFromWindow(viewHolder);
        }
    }
}
