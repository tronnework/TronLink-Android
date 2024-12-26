package com.tron.wallet.business.tabdapp.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tronlinkpro.wallet.R;
public class DappListAdapter extends BaseQuickAdapter<DappBean, DappListAdapterHolder> {
    public DappListAdapter() {
        super(R.layout.layout_holder_item_dapp_list, null);
    }

    @Override
    public void convert(DappListAdapterHolder dappListAdapterHolder, DappBean dappBean) {
        dappListAdapterHolder.onBind(dappBean);
    }
}
