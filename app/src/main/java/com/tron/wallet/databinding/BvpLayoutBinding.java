package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tronlinkpro.wallet.R;
public final class BvpLayoutBinding implements ViewBinding {
    private final View rootView;
    public final ViewPager2 vpMain;

    @Override
    public View getRoot() {
        return this.rootView;
    }

    private BvpLayoutBinding(View view, ViewPager2 viewPager2) {
        this.rootView = view;
        this.vpMain = viewPager2;
    }

    public static BvpLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.bvp_layout, viewGroup);
        return bind(viewGroup);
    }

    public static BvpLayoutBinding bind(View view) {
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_main);
        if (viewPager2 != null) {
            return new BvpLayoutBinding(view, viewPager2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.vp_main)));
    }
}
