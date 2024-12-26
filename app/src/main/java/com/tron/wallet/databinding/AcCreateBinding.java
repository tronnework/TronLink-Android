package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcCreateBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final Button btCreate;
    public final TextView btnJump;
    public final TextView btnSecurity;
    public final ContentCreateWalletBinding contentCreateWallet;
    public final RelativeLayout createLayout;
    public final SimpleDraweeViewGif gifImage;
    public final ImageView ivLearn;
    public final RelativeLayout learnLayout;
    public final RelativeLayout llAction;
    public final RelativeLayout rlBottom;
    public final CoordinatorLayout root;
    private final RelativeLayout rootView;
    public final RelativeLayout securityLayout;
    public final TextView securityTips;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;
    public final TextView tvCreateWalletTips;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcCreateBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, Button button, TextView textView, TextView textView2, ContentCreateWalletBinding contentCreateWalletBinding, RelativeLayout relativeLayout2, SimpleDraweeViewGif simpleDraweeViewGif, ImageView imageView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, CoordinatorLayout coordinatorLayout, RelativeLayout relativeLayout6, TextView textView3, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout, TextView textView4) {
        this.rootView = relativeLayout;
        this.appBar = appBarLayout;
        this.btCreate = button;
        this.btnJump = textView;
        this.btnSecurity = textView2;
        this.contentCreateWallet = contentCreateWalletBinding;
        this.createLayout = relativeLayout2;
        this.gifImage = simpleDraweeViewGif;
        this.ivLearn = imageView;
        this.learnLayout = relativeLayout3;
        this.llAction = relativeLayout4;
        this.rlBottom = relativeLayout5;
        this.root = coordinatorLayout;
        this.securityLayout = relativeLayout6;
        this.securityTips = textView3;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
        this.tvCreateWalletTips = textView4;
    }

    public static AcCreateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcCreateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_create, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcCreateBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.bt_create;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_create);
            if (button != null) {
                i = R.id.btn_jump;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_jump);
                if (textView != null) {
                    i = R.id.btn_security;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_security);
                    if (textView2 != null) {
                        i = R.id.content_create_wallet;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.content_create_wallet);
                        if (findChildViewById != null) {
                            ContentCreateWalletBinding bind = ContentCreateWalletBinding.bind(findChildViewById);
                            i = R.id.create_layout;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.create_layout);
                            if (relativeLayout != null) {
                                i = R.id.gif_image;
                                SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                                if (simpleDraweeViewGif != null) {
                                    i = R.id.iv_learn;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_learn);
                                    if (imageView != null) {
                                        i = R.id.learn_layout;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.learn_layout);
                                        if (relativeLayout2 != null) {
                                            i = R.id.ll_action;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                                            if (relativeLayout3 != null) {
                                                i = R.id.rl_bottom;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                                                if (relativeLayout4 != null) {
                                                    i = R.id.root;
                                                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.root);
                                                    if (coordinatorLayout != null) {
                                                        i = R.id.security_layout;
                                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.security_layout);
                                                        if (relativeLayout5 != null) {
                                                            i = R.id.security_tips;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.security_tips);
                                                            if (textView3 != null) {
                                                                i = R.id.toolbar;
                                                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                                                                if (toolbar != null) {
                                                                    i = R.id.toolbar_layout;
                                                                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                                                    if (collapsingToolbarLayout != null) {
                                                                        i = R.id.tv_create_wallet_tips;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_create_wallet_tips);
                                                                        if (textView4 != null) {
                                                                            return new AcCreateBinding((RelativeLayout) view, appBarLayout, button, textView, textView2, bind, relativeLayout, simpleDraweeViewGif, imageView, relativeLayout2, relativeLayout3, relativeLayout4, coordinatorLayout, relativeLayout5, textView3, toolbar, collapsingToolbarLayout, textView4);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
