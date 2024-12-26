package com.tron.wallet.business.tabdapp.home.adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tronlinkpro.wallet.R;
public class DappListAdapterHolder extends BaseViewHolder {
    private final SimpleDraweeView image;
    private final TextView tvSubtitle;
    private final TextView tvTitle;

    public DappListAdapterHolder(View view) {
        super(view);
        this.image = (SimpleDraweeView) view.findViewById(R.id.image);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.tvSubtitle = (TextView) view.findViewById(R.id.tv_subtitle);
    }

    public void onBind(DappBean dappBean) {
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setText(dappBean.getName());
        }
        if (this.tvSubtitle != null) {
            if (TextUtils.isEmpty(dappBean.getIntro())) {
                this.tvSubtitle.setVisibility(View.GONE);
            } else {
                this.tvSubtitle.setText(dappBean.getIntro());
                this.tvSubtitle.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.isEmpty(dappBean.getImageUrl())) {
            return;
        }
        this.image.setImageURI(Uri.parse(dappBean.getImageUrl()));
    }
}
