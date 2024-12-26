package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class ActivityImportSamsungBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final Button btnImport;
    public final CommonTitleDescriptionEditView etNameInput;
    public final SimpleDraweeViewGif imageTitle;
    private final CoordinatorLayout rootView;
    public final View stateView;
    public final Toolbar toolbar;
    public final CollapsingToolbarLayout toolbarLayout;

    @Override
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    private ActivityImportSamsungBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Button button, CommonTitleDescriptionEditView commonTitleDescriptionEditView, SimpleDraweeViewGif simpleDraweeViewGif, View view, Toolbar toolbar, CollapsingToolbarLayout collapsingToolbarLayout) {
        this.rootView = coordinatorLayout;
        this.appBar = appBarLayout;
        this.btnImport = button;
        this.etNameInput = commonTitleDescriptionEditView;
        this.imageTitle = simpleDraweeViewGif;
        this.stateView = view;
        this.toolbar = toolbar;
        this.toolbarLayout = collapsingToolbarLayout;
    }

    public static ActivityImportSamsungBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityImportSamsungBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_import_samsung, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityImportSamsungBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.btn_import;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_import);
            if (button != null) {
                i = R.id.et_name_input;
                CommonTitleDescriptionEditView commonTitleDescriptionEditView = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.et_name_input);
                if (commonTitleDescriptionEditView != null) {
                    i = R.id.image_title;
                    SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.image_title);
                    if (simpleDraweeViewGif != null) {
                        i = R.id.state_view;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.state_view);
                        if (findChildViewById != null) {
                            i = R.id.toolbar;
                            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                            if (toolbar != null) {
                                i = R.id.toolbar_layout;
                                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) ViewBindings.findChildViewById(view, R.id.toolbar_layout);
                                if (collapsingToolbarLayout != null) {
                                    return new ActivityImportSamsungBinding((CoordinatorLayout) view, appBarLayout, button, commonTitleDescriptionEditView, simpleDraweeViewGif, findChildViewById, toolbar, collapsingToolbarLayout);
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
