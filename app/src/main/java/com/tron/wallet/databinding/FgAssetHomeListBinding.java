package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class FgAssetHomeListBinding implements ViewBinding {
    public final ImageView ivPlaceHolder;
    public final NestedScrollView llHolder;
    public final NoNetView netErrorView;
    public final NoNetView noDataView;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgAssetHomeListBinding(RelativeLayout relativeLayout, ImageView imageView, NestedScrollView nestedScrollView, NoNetView noNetView, NoNetView noNetView2, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.ivPlaceHolder = imageView;
        this.llHolder = nestedScrollView;
        this.netErrorView = noNetView;
        this.noDataView = noNetView2;
        this.rvList = recyclerView;
    }

    public static FgAssetHomeListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgAssetHomeListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_asset_home_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgAssetHomeListBinding bind(View view) {
        int i = R.id.iv_place_holder;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
        if (imageView != null) {
            i = R.id.ll_holder;
            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_holder);
            if (nestedScrollView != null) {
                i = R.id.net_error_view;
                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
                if (noNetView != null) {
                    i = R.id.no_data_view;
                    NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                    if (noNetView2 != null) {
                        i = R.id.rv_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                        if (recyclerView != null) {
                            return new FgAssetHomeListBinding((RelativeLayout) view, imageView, nestedScrollView, noNetView, noNetView2, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
