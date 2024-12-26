package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopAssetSortBinding implements ViewBinding {
    public final View ivDivider;
    public final ImageView ivScamTokenTips;
    public final ImageView ivSelect;
    public final ImageView ivSelectScam;
    public final ImageView ivSmallValueTips;
    public final LinearLayout liHideSmallValues;
    public final LinearLayout liScamToken;
    public final ConstraintLayout llHide;
    public final RelativeLayout rlContent;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;
    public final RecyclerView rv;
    public final TextView tvCancle;
    public final TextView tvConfirm;
    public final TextView tvFilter;
    public final TextView tvHideAssets;
    public final TextView tvHideScamAssets;
    public final TextView tvSortBy;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopAssetSortBinding(RelativeLayout relativeLayout, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.ivDivider = view;
        this.ivScamTokenTips = imageView;
        this.ivSelect = imageView2;
        this.ivSelectScam = imageView3;
        this.ivSmallValueTips = imageView4;
        this.liHideSmallValues = linearLayout;
        this.liScamToken = linearLayout2;
        this.llHide = constraintLayout;
        this.rlContent = relativeLayout2;
        this.rootview = relativeLayout3;
        this.rv = recyclerView;
        this.tvCancle = textView;
        this.tvConfirm = textView2;
        this.tvFilter = textView3;
        this.tvHideAssets = textView4;
        this.tvHideScamAssets = textView5;
        this.tvSortBy = textView6;
    }

    public static PopAssetSortBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopAssetSortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_asset_sort, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopAssetSortBinding bind(View view) {
        int i = R.id.iv_divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_divider);
        if (findChildViewById != null) {
            i = R.id.iv_scam_token_tips;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam_token_tips);
            if (imageView != null) {
                i = R.id.iv_select;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select);
                if (imageView2 != null) {
                    i = R.id.iv_select_scam;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select_scam);
                    if (imageView3 != null) {
                        i = R.id.iv_small_value_tips;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_small_value_tips);
                        if (imageView4 != null) {
                            i = R.id.li_hide_small_values;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_hide_small_values);
                            if (linearLayout != null) {
                                i = R.id.li_scam_token;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_scam_token);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_hide;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_hide);
                                    if (constraintLayout != null) {
                                        i = R.id.rl_content;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                                        if (relativeLayout != null) {
                                            RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                            i = R.id.rv;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv);
                                            if (recyclerView != null) {
                                                i = R.id.tv_cancle;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                                                if (textView != null) {
                                                    i = R.id.tv_confirm;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_confirm);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_filter;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_filter);
                                                        if (textView3 != null) {
                                                            i = R.id.tv_hide_assets;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hide_assets);
                                                            if (textView4 != null) {
                                                                i = R.id.tv_hide_scam_assets;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hide_scam_assets);
                                                                if (textView5 != null) {
                                                                    i = R.id.tv_sort_by;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sort_by);
                                                                    if (textView6 != null) {
                                                                        return new PopAssetSortBinding(relativeLayout2, findChildViewById, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, constraintLayout, relativeLayout, relativeLayout2, recyclerView, textView, textView2, textView3, textView4, textView5, textView6);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
