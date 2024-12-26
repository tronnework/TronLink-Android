package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcImportWalletBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final Button btnNextStep;
    public final ContentImportWalletBinding contentImportWallet;
    public final SimpleDraweeViewGif gifImage;
    public final RelativeLayout rlBottomNextstep;
    private final RelativeLayout rootView;
    public final View stateView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;
    public final RelativeLayout viewContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcImportWalletBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, Button button, ContentImportWalletBinding contentImportWalletBinding, SimpleDraweeViewGif simpleDraweeViewGif, RelativeLayout relativeLayout2, View view, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout, RelativeLayout relativeLayout3) {
        this.rootView = relativeLayout;
        this.appBar = appBarLayout;
        this.btnNextStep = button;
        this.contentImportWallet = contentImportWalletBinding;
        this.gifImage = simpleDraweeViewGif;
        this.rlBottomNextstep = relativeLayout2;
        this.stateView = view;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
        this.viewContent = relativeLayout3;
    }

    public static AcImportWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcImportWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_import_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcImportWalletBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.btn_next_step;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next_step);
            if (button != null) {
                i = R.id.content_import_wallet;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.content_import_wallet);
                if (findChildViewById != null) {
                    ContentImportWalletBinding bind = ContentImportWalletBinding.bind(findChildViewById);
                    i = R.id.gif_image;
                    SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                    if (simpleDraweeViewGif != null) {
                        i = R.id.rl_bottom_nextstep;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom_nextstep);
                        if (relativeLayout != null) {
                            i = R.id.state_view;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.state_view);
                            if (findChildViewById2 != null) {
                                i = R.id.toolbar;
                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                                if (toolbar != null) {
                                    i = R.id.toolbar_layout;
                                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                    if (collapsingToolbarLayout != null) {
                                        RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                        return new AcImportWalletBinding(relativeLayout2, appBarLayout, button, bind, simpleDraweeViewGif, relativeLayout, findChildViewById2, toolbar, collapsingToolbarLayout, relativeLayout2);
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
