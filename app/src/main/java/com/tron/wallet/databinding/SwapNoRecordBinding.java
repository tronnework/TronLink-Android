package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class SwapNoRecordBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final NoNetView noNetView;
    private final RelativeLayout rootView;
    public final TextView tvNoData;
    public final NestedScrollView tvNoNet;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapNoRecordBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, NoNetView noNetView, TextView textView, NestedScrollView nestedScrollView2) {
        this.rootView = relativeLayout;
        this.llNodata = nestedScrollView;
        this.noNetView = noNetView;
        this.tvNoData = textView;
        this.tvNoNet = nestedScrollView2;
    }

    public static SwapNoRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapNoRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_no_record, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapNoRecordBinding bind(View view) {
        int i = R.id.ll_nodata;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
        if (nestedScrollView != null) {
            i = R.id.no_net_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
            if (noNetView != null) {
                i = R.id.tv_no_data;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                if (textView != null) {
                    i = R.id.tv_no_net;
                    NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.tv_no_net);
                    if (nestedScrollView2 != null) {
                        return new SwapNoRecordBinding((RelativeLayout) view, nestedScrollView, noNetView, textView, nestedScrollView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
