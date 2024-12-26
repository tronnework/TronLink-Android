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
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class ItemTokenDetailBottomBinding implements ViewBinding {
    public final RelativeLayout contentRoot;
    public final View divider;
    public final ImageView ivFilterSmallValueTip;
    public final ImageView ivToggleSmallValue;
    public final LinearLayout liOption;
    public final RelativeLayout liTablayout;
    public final LinearLayout llShasta;
    private final RelativeLayout rootView;
    public final TextView tvToggleSmallValue;
    public final ViewPager2 vpContent;
    public final XTabLayoutV2 xTablayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemTokenDetailBottomBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, View view, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout3, LinearLayout linearLayout2, TextView textView, ViewPager2 viewPager2, XTabLayoutV2 xTabLayoutV2) {
        this.rootView = relativeLayout;
        this.contentRoot = relativeLayout2;
        this.divider = view;
        this.ivFilterSmallValueTip = imageView;
        this.ivToggleSmallValue = imageView2;
        this.liOption = linearLayout;
        this.liTablayout = relativeLayout3;
        this.llShasta = linearLayout2;
        this.tvToggleSmallValue = textView;
        this.vpContent = viewPager2;
        this.xTablayout = xTabLayoutV2;
    }

    public static ItemTokenDetailBottomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTokenDetailBottomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_token_detail_bottom, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTokenDetailBottomBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.iv_filter_small_value_tip;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_filter_small_value_tip);
            if (imageView != null) {
                i = R.id.iv_toggle_small_value;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_toggle_small_value);
                if (imageView2 != null) {
                    i = R.id.li_option;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_option);
                    if (linearLayout != null) {
                        i = R.id.li_tablayout;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_tablayout);
                        if (relativeLayout2 != null) {
                            i = R.id.ll_shasta;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_shasta);
                            if (linearLayout2 != null) {
                                i = R.id.tv_toggle_small_value;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_toggle_small_value);
                                if (textView != null) {
                                    i = R.id.vp_content;
                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
                                    if (viewPager2 != null) {
                                        i = R.id.xTablayout;
                                        XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.xTablayout);
                                        if (xTabLayoutV2 != null) {
                                            return new ItemTokenDetailBottomBinding(relativeLayout, relativeLayout, findChildViewById, imageView, imageView2, linearLayout, relativeLayout2, linearLayout2, textView, viewPager2, xTabLayoutV2);
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
