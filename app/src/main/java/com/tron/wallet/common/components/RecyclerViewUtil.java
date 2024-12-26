package com.tron.wallet.common.components;

import androidx.recyclerview.widget.RecyclerView;
public class RecyclerViewUtil {
    private boolean enable = true;
    private RecyclerView mRecyclerView;
    private RecyclerViewLoadMoreListener mRecyclerViewLoadMoreListener;

    public interface RecyclerViewLoadMoreListener {
        void onLoadMore();

        void onScrolled(RecyclerView recyclerView, int i, int i2);
    }

    public void setLoadMoreEnable(boolean z) {
        this.enable = z;
    }

    public RecyclerViewUtil(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public void setRecyclerViewLoadMoreListener(RecyclerViewLoadMoreListener recyclerViewLoadMoreListener) {
        this.mRecyclerViewLoadMoreListener = recyclerViewLoadMoreListener;
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    RecyclerViewUtil recyclerViewUtil = RecyclerViewUtil.this;
                    if (recyclerViewUtil.isBottom(recyclerViewUtil.mRecyclerView) && enable && mRecyclerViewLoadMoreListener != null) {
                        mRecyclerViewLoadMoreListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (mRecyclerViewLoadMoreListener != null) {
                    mRecyclerViewLoadMoreListener.onScrolled(recyclerView, i, i2);
                }
            }
        });
    }

    public boolean isBottom(RecyclerView recyclerView) {
        return recyclerView != null && recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange();
    }
}
