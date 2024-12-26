package com.tron.wallet.business.tabdapp.home.adapter;

import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tronlinkpro.wallet.R;
public class DappQuickAdapterHolder extends BaseViewHolder {
    private static int MORE_ICON_INDEX = 10;
    private final SimpleDraweeView image;
    private final TextView tvName;

    public DappQuickAdapterHolder(View view) {
        super(view);
        this.tvName = (TextView) view.findViewById(R.id.tv_name);
        this.image = (SimpleDraweeView) view.findViewById(R.id.sdv_image);
    }

    public void onBind(DappBean dappBean, boolean z) {
        if (dappBean == null) {
            return;
        }
        if (z && getLayoutPosition() == MORE_ICON_INDEX) {
            this.tvName.setText(R.string.dapp_more);
            this.image.setImageResource(R.mipmap.dapp_main_book_more);
            return;
        }
        TextView textView = this.tvName;
        if (textView != null) {
            String name = dappBean.getName();
            if (name == null) {
                name = DAppUrlUtils.getHost(dappBean.getHomeUrl());
            }
            textView.setText(name);
        }
        if (this.image != null) {
            if (StringTronUtil.getHost(dappBean.getHomeUrl()).startsWith("www.google.com")) {
                this.image.setImageResource(R.mipmap.ic_search_dapp_google);
                return;
            }
            this.image.setController(Fresco.newDraweeControllerBuilder().setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFailure(String str, Throwable th) {
                    super.onFailure(str, th);
                    image.setImageResource(R.mipmap.ic_dapp_item_place_holder);
                }
            }).setUri(dappBean.getImageUrl()).build());
        }
    }
}
