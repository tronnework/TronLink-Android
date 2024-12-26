package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class LoadMoreRecyclerView extends RecyclerView {
    private int lastItem;
    private List<OnLoadMoreListener> listeners;

    public interface OnLoadMoreListener {
        void onLoadMore(RecyclerView recyclerView);
    }

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastItem = -1;
        this.listeners = new ArrayList();
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (listeners == null || i2 != 0 || recyclerView.getAdapter() == null || lastItem != recyclerView.getAdapter().getItemCount() - 1) {
                    return;
                }
                for (OnLoadMoreListener onLoadMoreListener : listeners) {
                    onLoadMoreListener.onLoadMore(recyclerView);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.listeners.add(onLoadMoreListener);
    }
}
