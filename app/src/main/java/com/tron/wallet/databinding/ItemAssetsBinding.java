package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class ItemAssetsBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final NoNetView noNetView;
    public final PtrHTFrameLayout rcFrame;
    public final ImageView rcHolderList;
    public final RecyclerView rcList;
    private final FrameLayout rootView;
    public final TextView tvNoData;
    public final TextView tvNoDataDetail;
    public final NestedScrollView tvNoNet;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsBinding(FrameLayout frameLayout, NestedScrollView nestedScrollView, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, ImageView imageView, RecyclerView recyclerView, TextView textView, TextView textView2, NestedScrollView nestedScrollView2) {
        this.rootView = frameLayout;
        this.llNodata = nestedScrollView;
        this.noNetView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rcHolderList = imageView;
        this.rcList = recyclerView;
        this.tvNoData = textView;
        this.tvNoDataDetail = textView2;
        this.tvNoNet = nestedScrollView2;
    }

    public static ItemAssetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsBinding bind(View view) {
        int i = R.id.ll_nodata;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
        if (nestedScrollView != null) {
            i = R.id.no_net_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
            if (noNetView != null) {
                i = R.id.rc_frame;
                PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                if (ptrHTFrameLayout != null) {
                    i = R.id.rc_holder_list;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rc_holder_list);
                    if (imageView != null) {
                        i = R.id.rc_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                        if (recyclerView != null) {
                            i = R.id.tv_no_data;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                            if (textView != null) {
                                i = R.id.tv_no_data_detail;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data_detail);
                                if (textView2 != null) {
                                    i = R.id.tv_no_net;
                                    NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.tv_no_net);
                                    if (nestedScrollView2 != null) {
                                        return new ItemAssetsBinding((FrameLayout) view, nestedScrollView, noNetView, ptrHTFrameLayout, imageView, recyclerView, textView, textView2, nestedScrollView2);
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
