package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class ActivityPairColdWalletBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final Button btnNext;
    public final LinearLayout gifContainer;
    public final SimpleDraweeViewGif gifImage;
    public final CommonTitleDescriptionEditView inputAddress;
    public final NestedScrollView inputContainer;
    public final CommonTitleDescriptionEditView inputName;
    public final LinearLayout llAddressError;
    public final LinearLayout llNameError;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;
    public final TextView tvAddressError;
    public final TextView tvNameError;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityPairColdWalletBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, Button button, LinearLayout linearLayout, SimpleDraweeViewGif simpleDraweeViewGif, CommonTitleDescriptionEditView commonTitleDescriptionEditView, NestedScrollView nestedScrollView, CommonTitleDescriptionEditView commonTitleDescriptionEditView2, LinearLayout linearLayout2, LinearLayout linearLayout3, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.appBar = appBarLayout;
        this.btnNext = button;
        this.gifContainer = linearLayout;
        this.gifImage = simpleDraweeViewGif;
        this.inputAddress = commonTitleDescriptionEditView;
        this.inputContainer = nestedScrollView;
        this.inputName = commonTitleDescriptionEditView2;
        this.llAddressError = linearLayout2;
        this.llNameError = linearLayout3;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
        this.tvAddressError = textView;
        this.tvNameError = textView2;
    }

    public static ActivityPairColdWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityPairColdWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_pair_cold_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityPairColdWalletBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.btn_next;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
            if (button != null) {
                i = R.id.gif_container;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.gif_container);
                if (linearLayout != null) {
                    i = R.id.gif_image;
                    SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_image);
                    if (simpleDraweeViewGif != null) {
                        i = R.id.input_address;
                        CommonTitleDescriptionEditView commonTitleDescriptionEditView = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.input_address);
                        if (commonTitleDescriptionEditView != null) {
                            i = R.id.input_container;
                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.input_container);
                            if (nestedScrollView != null) {
                                i = R.id.input_name;
                                CommonTitleDescriptionEditView commonTitleDescriptionEditView2 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.input_name);
                                if (commonTitleDescriptionEditView2 != null) {
                                    i = R.id.ll_address_error;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_address_error);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_name_error;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name_error);
                                        if (linearLayout3 != null) {
                                            i = R.id.toolbar;
                                            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                                            if (toolbar != null) {
                                                i = R.id.toolbar_layout;
                                                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                                if (collapsingToolbarLayout != null) {
                                                    i = R.id.tv_address_error;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_error);
                                                    if (textView != null) {
                                                        i = R.id.tv_name_error;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                                                        if (textView2 != null) {
                                                            return new ActivityPairColdWalletBinding((RelativeLayout) view, appBarLayout, button, linearLayout, simpleDraweeViewGif, commonTitleDescriptionEditView, nestedScrollView, commonTitleDescriptionEditView2, linearLayout2, linearLayout3, toolbar, collapsingToolbarLayout, textView, textView2);
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
