package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcCreateAccountBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final Button btCreate;
    public final ContentCreateAccountBinding contentCreateAccount;
    public final SimpleDraweeViewGif gifImage;
    public final LinearLayout liCreate;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;
    public final TextView tvRight;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcCreateAccountBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, Button button, ContentCreateAccountBinding contentCreateAccountBinding, SimpleDraweeViewGif simpleDraweeViewGif, LinearLayout linearLayout, RelativeLayout relativeLayout2, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.appBar = appBarLayout;
        this.btCreate = button;
        this.contentCreateAccount = contentCreateAccountBinding;
        this.gifImage = simpleDraweeViewGif;
        this.liCreate = linearLayout;
        this.rlRoot = relativeLayout2;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
        this.tvRight = textView;
    }

    public static AcCreateAccountBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcCreateAccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_create_account, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcCreateAccountBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.bt_create;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_create);
            if (button != null) {
                i = R.id.content_create_account;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.content_create_account);
                if (findChildViewById != null) {
                    ContentCreateAccountBinding bind = ContentCreateAccountBinding.bind(findChildViewById);
                    i = R.id.gif_image;
                    SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                    if (simpleDraweeViewGif != null) {
                        i = R.id.li_create;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_create);
                        if (linearLayout != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            i = R.id.toolbar;
                            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                            if (toolbar != null) {
                                i = R.id.toolbar_layout;
                                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                if (collapsingToolbarLayout != null) {
                                    i = R.id.tv_right;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right);
                                    if (textView != null) {
                                        return new AcCreateAccountBinding(relativeLayout, appBarLayout, button, bind, simpleDraweeViewGif, linearLayout, relativeLayout, toolbar, collapsingToolbarLayout, textView);
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
