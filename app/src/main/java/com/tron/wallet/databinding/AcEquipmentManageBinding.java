package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcEquipmentManageBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final FrameLayout content;
    public final SimpleDraweeViewGif gifImage;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;
    public final TextView tvTutorial;

    @Override
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    private AcEquipmentManageBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FrameLayout frameLayout, SimpleDraweeViewGif simpleDraweeViewGif, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout, TextView textView) {
        this.rootView = coordinatorLayout;
        this.appBar = appBarLayout;
        this.content = frameLayout;
        this.gifImage = simpleDraweeViewGif;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
        this.tvTutorial = textView;
    }

    public static AcEquipmentManageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcEquipmentManageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_equipment_manage, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcEquipmentManageBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.content;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.content);
            if (frameLayout != null) {
                i = R.id.gif_image;
                SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                if (simpleDraweeViewGif != null) {
                    i = R.id.toolbar;
                    Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                    if (toolbar != null) {
                        i = R.id.toolbar_layout;
                        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                        if (collapsingToolbarLayout != null) {
                            i = R.id.tv_tutorial;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tutorial);
                            if (textView != null) {
                                return new AcEquipmentManageBinding((CoordinatorLayout) view, appBarLayout, frameLayout, simpleDraweeViewGif, toolbar, collapsingToolbarLayout, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
