package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabdapp.browser.search.HistoryFlowLayout;
import com.tronlinkpro.wallet.R;
public final class SearchDappHistoryBinding implements ViewBinding {
    public final ImageView ivSearchHistoryClear;
    public final RelativeLayout rlSearchRoot;
    private final RelativeLayout rootView;
    public final HistoryFlowLayout searchHistoryFlowLayout;
    public final TextView searchHistoryTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SearchDappHistoryBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, HistoryFlowLayout historyFlowLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.ivSearchHistoryClear = imageView;
        this.rlSearchRoot = relativeLayout2;
        this.searchHistoryFlowLayout = historyFlowLayout;
        this.searchHistoryTitle = textView;
    }

    public static SearchDappHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchDappHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.search_dapp_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SearchDappHistoryBinding bind(View view) {
        int i = R.id.iv_search_history_clear;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search_history_clear);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.search_history_flow_layout;
            HistoryFlowLayout historyFlowLayout = (HistoryFlowLayout) ViewBindings.findChildViewById(view, R.id.search_history_flow_layout);
            if (historyFlowLayout != null) {
                i = R.id.search_history_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.search_history_title);
                if (textView != null) {
                    return new SearchDappHistoryBinding(relativeLayout, imageView, relativeLayout, historyFlowLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
