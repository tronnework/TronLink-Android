package com.tron.wallet.business.tabdapp.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tronlinkpro.wallet.R;
public class DappQuickAdapter extends BaseQuickAdapter<DappBean, DappQuickAdapterHolder> {
    private boolean hasMore;

    public boolean hasMore() {
        return this.hasMore;
    }

    public void setHasMore(boolean z) {
        this.hasMore = z;
    }

    public DappQuickAdapter() {
        super(R.layout.item_holder_dapp_quick, null);
    }

    @Override
    public void convert(DappQuickAdapterHolder dappQuickAdapterHolder, DappBean dappBean) {
        dappQuickAdapterHolder.onBind(dappBean, this.hasMore);
    }
}
