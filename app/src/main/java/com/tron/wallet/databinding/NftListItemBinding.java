package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CircularImageDraweeView;
import com.tronlinkpro.wallet.R;
public final class NftListItemBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final CircularImageDraweeView ivLogo;
    private final RelativeLayout rootView;
    public final TextView tvName;
    public final TextView tvTokenId;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NftListItemBinding(RelativeLayout relativeLayout, ImageView imageView, CircularImageDraweeView circularImageDraweeView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivLogo = circularImageDraweeView;
        this.tvName = textView;
        this.tvTokenId = textView2;
    }

    public static NftListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NftListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nft_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NftListItemBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_logo;
            CircularImageDraweeView circularImageDraweeView = (CircularImageDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
            if (circularImageDraweeView != null) {
                i = R.id.tv_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                if (textView != null) {
                    i = R.id.tv_token_id;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                    if (textView2 != null) {
                        return new NftListItemBinding((RelativeLayout) view, imageView, circularImageDraweeView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
