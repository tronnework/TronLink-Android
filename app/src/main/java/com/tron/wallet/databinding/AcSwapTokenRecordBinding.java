package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.placeholder.TokenDetailPlaceHolderRecyclerView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class AcSwapTokenRecordBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final NoNetView noNetView;
    public final PtrHTFrameLayout rcFrame;
    public final TokenDetailPlaceHolderRecyclerView rcHolderList;
    public final RecyclerView recyclerView;
    private final RelativeLayout rootView;
    public final TextView tvNoData;
    public final NestedScrollView tvNoNet;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSwapTokenRecordBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, TokenDetailPlaceHolderRecyclerView tokenDetailPlaceHolderRecyclerView, RecyclerView recyclerView, TextView textView, NestedScrollView nestedScrollView2) {
        this.rootView = relativeLayout;
        this.llNodata = nestedScrollView;
        this.noNetView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rcHolderList = tokenDetailPlaceHolderRecyclerView;
        this.recyclerView = recyclerView;
        this.tvNoData = textView;
        this.tvNoNet = nestedScrollView2;
    }

    public static AcSwapTokenRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSwapTokenRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_swap_token_record, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSwapTokenRecordBinding bind(View view) {
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
                    TokenDetailPlaceHolderRecyclerView tokenDetailPlaceHolderRecyclerView = (TokenDetailPlaceHolderRecyclerView) ViewBindings.findChildViewById(view, R.id.rc_holder_list);
                    if (tokenDetailPlaceHolderRecyclerView != null) {
                        i = R.id.recycler_view;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                        if (recyclerView != null) {
                            i = R.id.tv_no_data;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                            if (textView != null) {
                                i = R.id.tv_no_net;
                                NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.tv_no_net);
                                if (nestedScrollView2 != null) {
                                    return new AcSwapTokenRecordBinding((RelativeLayout) view, nestedScrollView, noNetView, ptrHTFrameLayout, tokenDetailPlaceHolderRecyclerView, recyclerView, textView, nestedScrollView2);
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
