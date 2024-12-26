package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class ActivityBrowserSearchResultBinding implements ViewBinding {
    public final TextView interviewHistoryTitle;
    public final ImageView ivEmpty;
    public final ImageView ivInterviewHistoryClear;
    public final LinearLayout llInterviewHistory;
    public final LinearLayout llNoresult;
    public final LinearLayout llSearchResult;
    public final NoNetView netErrorView;
    public final NoNetView noDataView;
    public final ImageView placeHolder;
    public final RelativeLayout rlEmpty;
    private final RelativeLayout rootView;
    public final RecyclerView rvDappResult;
    public final RecyclerView rvInterviewHistory;
    public final SearchAssetHeaderDappBinding searchHeader;
    public final SearchDappHistoryBinding searchHistory;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityBrowserSearchResultBinding(RelativeLayout relativeLayout, TextView textView, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, NoNetView noNetView, NoNetView noNetView2, ImageView imageView3, RelativeLayout relativeLayout2, RecyclerView recyclerView, RecyclerView recyclerView2, SearchAssetHeaderDappBinding searchAssetHeaderDappBinding, SearchDappHistoryBinding searchDappHistoryBinding) {
        this.rootView = relativeLayout;
        this.interviewHistoryTitle = textView;
        this.ivEmpty = imageView;
        this.ivInterviewHistoryClear = imageView2;
        this.llInterviewHistory = linearLayout;
        this.llNoresult = linearLayout2;
        this.llSearchResult = linearLayout3;
        this.netErrorView = noNetView;
        this.noDataView = noNetView2;
        this.placeHolder = imageView3;
        this.rlEmpty = relativeLayout2;
        this.rvDappResult = recyclerView;
        this.rvInterviewHistory = recyclerView2;
        this.searchHeader = searchAssetHeaderDappBinding;
        this.searchHistory = searchDappHistoryBinding;
    }

    public static ActivityBrowserSearchResultBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityBrowserSearchResultBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_browser_search_result, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityBrowserSearchResultBinding bind(View view) {
        int i = R.id.interview_history_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.interview_history_title);
        if (textView != null) {
            i = R.id.iv_empty;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_empty);
            if (imageView != null) {
                i = R.id.iv_interview_history_clear;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_interview_history_clear);
                if (imageView2 != null) {
                    i = R.id.ll_interview_history;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_interview_history);
                    if (linearLayout != null) {
                        i = R.id.ll_noresult;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_noresult);
                        if (linearLayout2 != null) {
                            i = R.id.ll_search_result;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search_result);
                            if (linearLayout3 != null) {
                                i = R.id.net_error_view;
                                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
                                if (noNetView != null) {
                                    i = R.id.no_data_view;
                                    NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                                    if (noNetView2 != null) {
                                        i = R.id.place_holder;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.place_holder);
                                        if (imageView3 != null) {
                                            i = R.id.rl_empty;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_empty);
                                            if (relativeLayout != null) {
                                                i = R.id.rv_dapp_result;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_dapp_result);
                                                if (recyclerView != null) {
                                                    i = R.id.rv_interview_history;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_interview_history);
                                                    if (recyclerView2 != null) {
                                                        i = R.id.search_header;
                                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.search_header);
                                                        if (findChildViewById != null) {
                                                            SearchAssetHeaderDappBinding bind = SearchAssetHeaderDappBinding.bind(findChildViewById);
                                                            i = R.id.search_history;
                                                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.search_history);
                                                            if (findChildViewById2 != null) {
                                                                return new ActivityBrowserSearchResultBinding((RelativeLayout) view, textView, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, noNetView, noNetView2, imageView3, relativeLayout, recyclerView, recyclerView2, bind, SearchDappHistoryBinding.bind(findChildViewById2));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
