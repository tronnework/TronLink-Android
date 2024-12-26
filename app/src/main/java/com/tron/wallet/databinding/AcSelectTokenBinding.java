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
import com.tronlinkpro.wallet.R;
public final class AcSelectTokenBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final NoNetView noNetView;
    public final TokenDetailPlaceHolderRecyclerView rcHolderList;
    private final RelativeLayout rootView;
    public final RecyclerView tokenList;
    public final TextView tokenNameTitle;
    public final TextView tvNoData;
    public final NestedScrollView tvNoNet;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectTokenBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, NoNetView noNetView, TokenDetailPlaceHolderRecyclerView tokenDetailPlaceHolderRecyclerView, RecyclerView recyclerView, TextView textView, TextView textView2, NestedScrollView nestedScrollView2) {
        this.rootView = relativeLayout;
        this.llNodata = nestedScrollView;
        this.noNetView = noNetView;
        this.rcHolderList = tokenDetailPlaceHolderRecyclerView;
        this.tokenList = recyclerView;
        this.tokenNameTitle = textView;
        this.tvNoData = textView2;
        this.tvNoNet = nestedScrollView2;
    }

    public static AcSelectTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectTokenBinding bind(View view) {
        int i = R.id.ll_nodata;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
        if (nestedScrollView != null) {
            i = R.id.no_net_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
            if (noNetView != null) {
                i = R.id.rc_holder_list;
                TokenDetailPlaceHolderRecyclerView tokenDetailPlaceHolderRecyclerView = (TokenDetailPlaceHolderRecyclerView) ViewBindings.findChildViewById(view, R.id.rc_holder_list);
                if (tokenDetailPlaceHolderRecyclerView != null) {
                    i = R.id.token_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.token_list);
                    if (recyclerView != null) {
                        i = R.id.token_name_title;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.token_name_title);
                        if (textView != null) {
                            i = R.id.tv_no_data;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                            if (textView2 != null) {
                                i = R.id.tv_no_net;
                                NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.tv_no_net);
                                if (nestedScrollView2 != null) {
                                    return new AcSelectTokenBinding((RelativeLayout) view, nestedScrollView, noNetView, tokenDetailPlaceHolderRecyclerView, recyclerView, textView, textView2, nestedScrollView2);
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
