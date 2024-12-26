package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class NftHistoryDetailTitleContentBinding implements ViewBinding {
    public final TextView assetId;
    public final SimpleDraweeView ivLogo1;
    public final SimpleDraweeView ivLogo2;
    public final ImageView ivScam;
    public final TextView name;
    public final TextView nftName;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private NftHistoryDetailTitleContentBinding(LinearLayout linearLayout, TextView textView, SimpleDraweeView simpleDraweeView, SimpleDraweeView simpleDraweeView2, ImageView imageView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.assetId = textView;
        this.ivLogo1 = simpleDraweeView;
        this.ivLogo2 = simpleDraweeView2;
        this.ivScam = imageView;
        this.name = textView2;
        this.nftName = textView3;
    }

    public static NftHistoryDetailTitleContentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NftHistoryDetailTitleContentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nft_history_detail_title_content, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NftHistoryDetailTitleContentBinding bind(View view) {
        int i = R.id.asset_id;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.asset_id);
        if (textView != null) {
            i = R.id.iv_logo1;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo1);
            if (simpleDraweeView != null) {
                i = R.id.iv_logo2;
                SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo2);
                if (simpleDraweeView2 != null) {
                    i = R.id.iv_scam;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
                    if (imageView != null) {
                        i = R.id.name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.name);
                        if (textView2 != null) {
                            i = R.id.nft_name;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.nft_name);
                            if (textView3 != null) {
                                return new NftHistoryDetailTitleContentBinding((LinearLayout) view, textView, simpleDraweeView, simpleDraweeView2, imageView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
