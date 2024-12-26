package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcMyAssetsBinding implements ViewBinding {
    public final TextView etSearch;
    public final ImageView ivClear;
    public final ImageView ivCommonLeft;
    public final ImageView ivMyAssetSort;
    public final LinearLayout llCommonLeft;
    public final RelativeLayout llHeader;
    public final LinearLayout llSearch;
    public final XTabLayoutV2 llTab;
    public final RelativeLayout llTabMain;
    public final RelativeLayout rlSearch;
    private final ConstraintLayout rootView;
    public final TextView tvCommonRight;
    public final TextView tvCommonTitle;
    public final ViewPager2 viewpager;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private AcMyAssetsBinding(ConstraintLayout constraintLayout, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, RelativeLayout relativeLayout, LinearLayout linearLayout2, XTabLayoutV2 xTabLayoutV2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView2, TextView textView3, ViewPager2 viewPager2) {
        this.rootView = constraintLayout;
        this.etSearch = textView;
        this.ivClear = imageView;
        this.ivCommonLeft = imageView2;
        this.ivMyAssetSort = imageView3;
        this.llCommonLeft = linearLayout;
        this.llHeader = relativeLayout;
        this.llSearch = linearLayout2;
        this.llTab = xTabLayoutV2;
        this.llTabMain = relativeLayout2;
        this.rlSearch = relativeLayout3;
        this.tvCommonRight = textView2;
        this.tvCommonTitle = textView3;
        this.viewpager = viewPager2;
    }

    public static AcMyAssetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMyAssetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_my_assets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMyAssetsBinding bind(View view) {
        int i = R.id.et_search;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.et_search);
        if (textView != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.iv_common_left;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                if (imageView2 != null) {
                    i = R.id.iv_my_asset_sort;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_my_asset_sort);
                    if (imageView3 != null) {
                        i = R.id.ll_common_left;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                        if (linearLayout != null) {
                            i = R.id.ll_header;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header);
                            if (relativeLayout != null) {
                                i = R.id.ll_search;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_tab;
                                    XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.ll_tab);
                                    if (xTabLayoutV2 != null) {
                                        i = R.id.ll_tab_main;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_tab_main);
                                        if (relativeLayout2 != null) {
                                            i = R.id.rl_search;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                            if (relativeLayout3 != null) {
                                                i = R.id.tv_common_right;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_right);
                                                if (textView2 != null) {
                                                    i = R.id.tv_common_title;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                                                    if (textView3 != null) {
                                                        i = R.id.viewpager;
                                                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                                        if (viewPager2 != null) {
                                                            return new AcMyAssetsBinding((ConstraintLayout) view, textView, imageView, imageView2, imageView3, linearLayout, relativeLayout, linearLayout2, xTabLayoutV2, relativeLayout2, relativeLayout3, textView2, textView3, viewPager2);
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
