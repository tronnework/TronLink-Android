package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tronlinkpro.wallet.R;
public final class ActivityScrollingTestBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final SimpleDraweeView gifImage;
    private final CoordinatorLayout rootView;
    public final View stateView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;

    @Override
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    private ActivityScrollingTestBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, SimpleDraweeView simpleDraweeView, View view, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout) {
        this.rootView = coordinatorLayout;
        this.appBar = appBarLayout;
        this.gifImage = simpleDraweeView;
        this.stateView = view;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
    }

    public static ActivityScrollingTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityScrollingTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_scrolling_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityScrollingTestBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.gif_image;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.gif_image);
            if (simpleDraweeView != null) {
                i = R.id.state_view;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.state_view);
                if (findChildViewById != null) {
                    i = R.id.toolbar;
                    Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                    if (toolbar != null) {
                        i = R.id.toolbar_layout;
                        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                        if (collapsingToolbarLayout != null) {
                            return new ActivityScrollingTestBinding((CoordinatorLayout) view, appBarLayout, simpleDraweeView, findChildViewById, toolbar, collapsingToolbarLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
