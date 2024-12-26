package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class FragmentUnstakeResourcesBinding implements ViewBinding {
    public final ImageView ivLoading;
    public final NoNetView noDataView;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentUnstakeResourcesBinding(RelativeLayout relativeLayout, ImageView imageView, NoNetView noNetView, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.ivLoading = imageView;
        this.noDataView = noNetView;
        this.rvList = recyclerView;
    }

    public static FragmentUnstakeResourcesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentUnstakeResourcesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_unstake_resources, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentUnstakeResourcesBinding bind(View view) {
        int i = R.id.iv_loading;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
        if (imageView != null) {
            i = R.id.no_data_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
            if (noNetView != null) {
                i = R.id.rv_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                if (recyclerView != null) {
                    return new FragmentUnstakeResourcesBinding((RelativeLayout) view, imageView, noNetView, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
