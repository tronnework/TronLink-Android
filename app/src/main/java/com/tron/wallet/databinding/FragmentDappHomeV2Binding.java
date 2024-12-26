package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tronlinkpro.wallet.R;
public final class FragmentDappHomeV2Binding implements ViewBinding {
    public final ViewPager2 frameContent;
    public final ImageView ivIndicator;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentDappHomeV2Binding(RelativeLayout relativeLayout, ViewPager2 viewPager2, ImageView imageView) {
        this.rootView = relativeLayout;
        this.frameContent = viewPager2;
        this.ivIndicator = imageView;
    }

    public static FragmentDappHomeV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDappHomeV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dapp_home_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDappHomeV2Binding bind(View view) {
        int i = R.id.frame_content;
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.frame_content);
        if (viewPager2 != null) {
            i = R.id.iv_indicator;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_indicator);
            if (imageView != null) {
                return new FragmentDappHomeV2Binding((RelativeLayout) view, viewPager2, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
