package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemNftListBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final SimpleDraweeView ivLogo;
    public final RelativeLayout llNftItem;
    private final RelativeLayout rootView;
    public final TextView tvId;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemNftListBinding(RelativeLayout relativeLayout, ImageView imageView, SimpleDraweeView simpleDraweeView, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivLogo = simpleDraweeView;
        this.llNftItem = relativeLayout2;
        this.tvId = textView;
        this.tvName = textView2;
    }

    public static ItemNftListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNftListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_nft_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNftListBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_logo;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
            if (simpleDraweeView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_id;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_id);
                if (textView != null) {
                    i = R.id.tv_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView2 != null) {
                        return new ItemNftListBinding(relativeLayout, imageView, simpleDraweeView, relativeLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
