package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcAddWatchWalletBinding implements ViewBinding {
    public final RelativeLayout addBottomLayout;
    public final Button addWatchWallet;
    public final AppBarLayout appBar;
    public final ContentAddWatchWalletBinding contentAddWatchWallet;
    public final SimpleDraweeViewGif gifImage;
    public final CoordinatorLayout root;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcAddWatchWalletBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, Button button, AppBarLayout appBarLayout, ContentAddWatchWalletBinding contentAddWatchWalletBinding, SimpleDraweeViewGif simpleDraweeViewGif, CoordinatorLayout coordinatorLayout, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout) {
        this.rootView = relativeLayout;
        this.addBottomLayout = relativeLayout2;
        this.addWatchWallet = button;
        this.appBar = appBarLayout;
        this.contentAddWatchWallet = contentAddWatchWalletBinding;
        this.gifImage = simpleDraweeViewGif;
        this.root = coordinatorLayout;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
    }

    public static AcAddWatchWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAddWatchWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_add_watch_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAddWatchWalletBinding bind(View view) {
        int i = R.id.add_bottom_layout;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.add_bottom_layout);
        if (relativeLayout != null) {
            i = R.id.add_watch_wallet;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.add_watch_wallet);
            if (button != null) {
                i = R.id.app_bar;
                AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
                if (appBarLayout != null) {
                    i = R.id.content_add_watch_wallet;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.content_add_watch_wallet);
                    if (findChildViewById != null) {
                        ContentAddWatchWalletBinding bind = ContentAddWatchWalletBinding.bind(findChildViewById);
                        i = R.id.gif_image;
                        SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                        if (simpleDraweeViewGif != null) {
                            i = R.id.root;
                            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.root);
                            if (coordinatorLayout != null) {
                                i = R.id.toolbar;
                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                                if (toolbar != null) {
                                    i = R.id.toolbar_layout;
                                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                    if (collapsingToolbarLayout != null) {
                                        return new AcAddWatchWalletBinding((RelativeLayout) view, relativeLayout, button, appBarLayout, bind, simpleDraweeViewGif, coordinatorLayout, toolbar, collapsingToolbarLayout);
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
