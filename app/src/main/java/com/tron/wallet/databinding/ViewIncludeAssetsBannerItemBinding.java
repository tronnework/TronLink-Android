package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ViewIncludeAssetsBannerItemBinding implements ViewBinding {
    public final ImageView ivIcon;
    public final ImageView ivNewAdded;
    public final RelativeLayout rlContent;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ViewIncludeAssetsBannerItemBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, TextView textView) {
        this.rootView = constraintLayout;
        this.ivIcon = imageView;
        this.ivNewAdded = imageView2;
        this.rlContent = relativeLayout;
        this.tvTitle = textView;
    }

    public static ViewIncludeAssetsBannerItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewIncludeAssetsBannerItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.view_include_assets_banner_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewIncludeAssetsBannerItemBinding bind(View view) {
        int i = R.id.iv_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
        if (imageView != null) {
            i = R.id.iv_new_added;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_new_added);
            if (imageView2 != null) {
                i = R.id.rl_content;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                if (relativeLayout != null) {
                    i = R.id.tv_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView != null) {
                        return new ViewIncludeAssetsBannerItemBinding((ConstraintLayout) view, imageView, imageView2, relativeLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
