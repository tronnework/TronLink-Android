package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.BadgeButton;
import com.tronlinkpro.wallet.R;
public final class AssetTabBinding implements ViewBinding {
    public final TextView divider;
    public final ImageView ivAddAssets;
    public final ImageView ivSort;
    public final LinearLayout llNewAssetsCount;
    public final RelativeLayout rlBtnContainer;
    public final RelativeLayout rlSort;
    private final RelativeLayout rootView;
    public final BadgeButton textNewAssetsCount;
    public final TextView tvAsset;
    public final TextView tvCollection;
    public final TextView tvSortBy;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AssetTabBinding(RelativeLayout relativeLayout, TextView textView, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, BadgeButton badgeButton, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.divider = textView;
        this.ivAddAssets = imageView;
        this.ivSort = imageView2;
        this.llNewAssetsCount = linearLayout;
        this.rlBtnContainer = relativeLayout2;
        this.rlSort = relativeLayout3;
        this.textNewAssetsCount = badgeButton;
        this.tvAsset = textView2;
        this.tvCollection = textView3;
        this.tvSortBy = textView4;
    }

    public static AssetTabBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AssetTabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.asset_tab, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AssetTabBinding bind(View view) {
        int i = R.id.divider;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.divider);
        if (textView != null) {
            i = R.id.iv_add_assets;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_add_assets);
            if (imageView != null) {
                i = R.id.iv_sort;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sort);
                if (imageView2 != null) {
                    i = R.id.ll_new_assets_count;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_new_assets_count);
                    if (linearLayout != null) {
                        i = R.id.rl_btn_container;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_btn_container);
                        if (relativeLayout != null) {
                            i = R.id.rl_sort;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_sort);
                            if (relativeLayout2 != null) {
                                i = R.id.text_new_assets_count;
                                BadgeButton badgeButton = (BadgeButton) ViewBindings.findChildViewById(view, R.id.text_new_assets_count);
                                if (badgeButton != null) {
                                    i = R.id.tv_asset;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_asset);
                                    if (textView2 != null) {
                                        i = R.id.tv_collection;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_collection);
                                        if (textView3 != null) {
                                            i = R.id.tv_sort_by;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sort_by);
                                            if (textView4 != null) {
                                                return new AssetTabBinding((RelativeLayout) view, textView, imageView, imageView2, linearLayout, relativeLayout, relativeLayout2, badgeButton, textView2, textView3, textView4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
