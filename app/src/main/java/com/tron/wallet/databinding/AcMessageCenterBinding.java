package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.MessageHeaderView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.placeholder.TokenDetailPlaceHolderRecyclerView;
import com.tronlinkpro.wallet.R;
public final class AcMessageCenterBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final MessageHeaderView messageHeader;
    public final NoNetView noNetView;
    public final TokenDetailPlaceHolderRecyclerView rcHolderList;
    private final RelativeLayout rootView;
    public final LoadMoreRecyclerView tokenList;
    public final TextView tvNoData;
    public final NestedScrollView tvNoNet;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcMessageCenterBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, MessageHeaderView messageHeaderView, NoNetView noNetView, TokenDetailPlaceHolderRecyclerView tokenDetailPlaceHolderRecyclerView, LoadMoreRecyclerView loadMoreRecyclerView, TextView textView, NestedScrollView nestedScrollView2) {
        this.rootView = relativeLayout;
        this.llNodata = nestedScrollView;
        this.messageHeader = messageHeaderView;
        this.noNetView = noNetView;
        this.rcHolderList = tokenDetailPlaceHolderRecyclerView;
        this.tokenList = loadMoreRecyclerView;
        this.tvNoData = textView;
        this.tvNoNet = nestedScrollView2;
    }

    public static AcMessageCenterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMessageCenterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_message_center, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMessageCenterBinding bind(View view) {
        int i = R.id.ll_nodata;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
        if (nestedScrollView != null) {
            i = R.id.message_header;
            MessageHeaderView messageHeaderView = (MessageHeaderView) ViewBindings.findChildViewById(view, R.id.message_header);
            if (messageHeaderView != null) {
                i = R.id.no_net_view;
                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                if (noNetView != null) {
                    i = R.id.rc_holder_list;
                    TokenDetailPlaceHolderRecyclerView tokenDetailPlaceHolderRecyclerView = (TokenDetailPlaceHolderRecyclerView) ViewBindings.findChildViewById(view, R.id.rc_holder_list);
                    if (tokenDetailPlaceHolderRecyclerView != null) {
                        i = R.id.token_list;
                        LoadMoreRecyclerView loadMoreRecyclerView = (LoadMoreRecyclerView) ViewBindings.findChildViewById(view, R.id.token_list);
                        if (loadMoreRecyclerView != null) {
                            i = R.id.tv_no_data;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                            if (textView != null) {
                                i = R.id.tv_no_net;
                                NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.tv_no_net);
                                if (nestedScrollView2 != null) {
                                    return new AcMessageCenterBinding((RelativeLayout) view, nestedScrollView, messageHeaderView, noNetView, tokenDetailPlaceHolderRecyclerView, loadMoreRecyclerView, textView, nestedScrollView2);
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
