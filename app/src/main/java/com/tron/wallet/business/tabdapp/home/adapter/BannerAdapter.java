package com.tron.wallet.business.tabdapp.home.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.tabdapp.home.bean.DappBannerBean;
import com.tronlinkpro.wallet.R;
public class BannerAdapter extends BaseQuickAdapter<DappBannerBean, BannerItemHolder> {
    public BannerAdapter() {
        super((int) R.layout.item_holder_browser_banner);
    }

    @Override
    public void convert(BannerItemHolder bannerItemHolder, DappBannerBean dappBannerBean) {
        bannerItemHolder.onBind(dappBannerBean);
    }

    @Override
    public BannerItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = View.inflate(viewGroup.getContext(), this.mLayoutResId, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        final BannerItemHolder bannerItemHolder = new BannerItemHolder(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$onCreateViewHolder$0(bannerItemHolder, view);
            }
        });
        return bannerItemHolder;
    }

    public void lambda$onCreateViewHolder$0(BannerItemHolder bannerItemHolder, View view) {
        getOnItemClickListener().onItemClick(this, view, bannerItemHolder.getAbsoluteAdapterPosition() % getData().size());
    }

    @Override
    public void onBindViewHolder(BannerItemHolder bannerItemHolder, int i) {
        convert(bannerItemHolder, getItem(i));
    }

    @Override
    public DappBannerBean getItem(int i) {
        return getData().get(i % getData().size());
    }

    @Override
    public int getItemCount() {
        if (getData().isEmpty()) {
            return 0;
        }
        return getData().size() == 1 ? 1 : Integer.MAX_VALUE;
    }
}
