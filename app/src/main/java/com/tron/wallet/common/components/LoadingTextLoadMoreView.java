package com.tron.wallet.common.components;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.tronlinkpro.wallet.R;
public class LoadingTextLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.loading_text_load_more;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.no_more;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.rl_loading_failed;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.tv_loading;
    }
}
