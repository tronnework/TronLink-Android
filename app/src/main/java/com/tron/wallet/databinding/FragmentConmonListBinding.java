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
public final class FragmentConmonListBinding implements ViewBinding {
    public final RelativeLayout contentRoot;
    public final ImageView ivPlaceHolder;
    public final NoNetView noDataView;
    public final NoNetView noNetView;
    public final RecyclerView recyclerView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentConmonListBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, NoNetView noNetView, NoNetView noNetView2, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.contentRoot = relativeLayout2;
        this.ivPlaceHolder = imageView;
        this.noDataView = noNetView;
        this.noNetView = noNetView2;
        this.recyclerView = recyclerView;
    }

    public static FragmentConmonListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentConmonListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_conmon_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentConmonListBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.iv_place_holder;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
        if (imageView != null) {
            i = R.id.no_data_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
            if (noNetView != null) {
                i = R.id.no_net_view;
                NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                if (noNetView2 != null) {
                    i = R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
                    if (recyclerView != null) {
                        return new FragmentConmonListBinding(relativeLayout, relativeLayout, imageView, noNetView, noNetView2, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
