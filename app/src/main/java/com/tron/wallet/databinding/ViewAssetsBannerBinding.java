package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tronlinkpro.wallet.R;
public final class ViewAssetsBannerBinding implements ViewBinding {
    public final ViewPager2 bannerViewPager;
    public final LinearLayout llIndicator;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ViewAssetsBannerBinding(RelativeLayout relativeLayout, ViewPager2 viewPager2, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.bannerViewPager = viewPager2;
        this.llIndicator = linearLayout;
    }

    public static ViewAssetsBannerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewAssetsBannerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.view_assets_banner, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewAssetsBannerBinding bind(View view) {
        int i = R.id.banner_view_pager;
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.banner_view_pager);
        if (viewPager2 != null) {
            i = R.id.ll_indicator;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_indicator);
            if (linearLayout != null) {
                return new ViewAssetsBannerBinding((RelativeLayout) view, viewPager2, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
