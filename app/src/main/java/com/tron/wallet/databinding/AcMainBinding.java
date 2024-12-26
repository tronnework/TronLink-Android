package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.MainTabView;
import com.tronlinkpro.wallet.R;
public final class AcMainBinding implements ViewBinding {
    public final MainTabView llMainTab;
    public final FrameLayout main;
    public final RelativeLayout root;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcMainBinding(RelativeLayout relativeLayout, MainTabView mainTabView, FrameLayout frameLayout, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.llMainTab = mainTabView;
        this.main = frameLayout;
        this.root = relativeLayout2;
    }

    public static AcMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMainBinding bind(View view) {
        int i = R.id.ll_main_tab;
        MainTabView mainTabView = (MainTabView) ViewBindings.findChildViewById(view, R.id.ll_main_tab);
        if (mainTabView != null) {
            i = R.id.main;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.main);
            if (frameLayout != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                return new AcMainBinding(relativeLayout, mainTabView, frameLayout, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
