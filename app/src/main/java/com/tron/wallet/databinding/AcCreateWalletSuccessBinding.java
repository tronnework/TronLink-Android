package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcCreateWalletSuccessBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final TextView btnJump;
    public final TextView btnProject;
    public final SimpleDraweeViewGif gifImage;
    public final ConstraintLayout llAction;
    public final CoordinatorLayout root;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;
    public final TextView tvWarningTips;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcCreateWalletSuccessBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, TextView textView, TextView textView2, SimpleDraweeViewGif simpleDraweeViewGif, ConstraintLayout constraintLayout, CoordinatorLayout coordinatorLayout, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout, TextView textView3) {
        this.rootView = relativeLayout;
        this.appBar = appBarLayout;
        this.btnJump = textView;
        this.btnProject = textView2;
        this.gifImage = simpleDraweeViewGif;
        this.llAction = constraintLayout;
        this.root = coordinatorLayout;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
        this.tvWarningTips = textView3;
    }

    public static AcCreateWalletSuccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcCreateWalletSuccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_create_wallet_success, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcCreateWalletSuccessBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.btn_jump;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_jump);
            if (textView != null) {
                i = R.id.btn_project;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_project);
                if (textView2 != null) {
                    i = R.id.gif_image;
                    SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                    if (simpleDraweeViewGif != null) {
                        i = R.id.ll_action;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                        if (constraintLayout != null) {
                            i = R.id.root;
                            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.root);
                            if (coordinatorLayout != null) {
                                i = R.id.toolbar;
                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                                if (toolbar != null) {
                                    i = R.id.toolbar_layout;
                                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                    if (collapsingToolbarLayout != null) {
                                        i = R.id.tv_warning_tips;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_warning_tips);
                                        if (textView3 != null) {
                                            return new AcCreateWalletSuccessBinding((RelativeLayout) view, appBarLayout, textView, textView2, simpleDraweeViewGif, constraintLayout, coordinatorLayout, toolbar, collapsingToolbarLayout, textView3);
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
