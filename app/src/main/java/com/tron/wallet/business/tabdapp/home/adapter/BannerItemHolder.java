package com.tron.wallet.business.tabdapp.home.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.business.tabdapp.home.bean.DappBannerBean;
import com.tronlinkpro.wallet.R;
public class BannerItemHolder extends BaseViewHolder {
    SimpleDraweeView ivImage;

    public BannerItemHolder(View view) {
        super(view);
        this.ivImage = (SimpleDraweeView) view.findViewById(R.id.dapp_banner_item_image);
    }

    public void onBind(DappBannerBean dappBannerBean) {
        if (dappBannerBean == null) {
            return;
        }
        checkAndSetImage(this.ivImage, dappBannerBean.getImageUrl());
    }

    private void checkAndSetText(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        textView.setText(str);
    }

    private void checkAndSetImage(SimpleDraweeView simpleDraweeView, String str) {
        if (TextUtils.isEmpty(str) || simpleDraweeView == null) {
            return;
        }
        simpleDraweeView.setImageURI(str);
    }
}
