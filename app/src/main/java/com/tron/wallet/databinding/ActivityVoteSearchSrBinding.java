package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class ActivityVoteSearchSrBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivClear;
    public final LinearLayout llNoresult;
    public final LinearLayout llSearch;
    public final LinearLayout llSrResult;
    public final LinearLayout llTopResult;
    public final NoNetView netErrorView;
    public final NoNetView noDataView;
    public final ImageView placeHolder;
    public final RelativeLayout rlSearch;
    private final RelativeLayout rootView;
    public final RecyclerView rvSrResult;
    public final RecyclerView rvTopSr;
    public final RelativeLayout searchHeader;
    public final TextView tvCancel;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityVoteSearchSrBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, NoNetView noNetView, NoNetView noNetView2, ImageView imageView2, RelativeLayout relativeLayout2, RecyclerView recyclerView, RecyclerView recyclerView2, RelativeLayout relativeLayout3, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.llNoresult = linearLayout;
        this.llSearch = linearLayout2;
        this.llSrResult = linearLayout3;
        this.llTopResult = linearLayout4;
        this.netErrorView = noNetView;
        this.noDataView = noNetView2;
        this.placeHolder = imageView2;
        this.rlSearch = relativeLayout2;
        this.rvSrResult = recyclerView;
        this.rvTopSr = recyclerView2;
        this.searchHeader = relativeLayout3;
        this.tvCancel = textView;
    }

    public static ActivityVoteSearchSrBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityVoteSearchSrBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_vote_search_sr, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityVoteSearchSrBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.ll_noresult;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_noresult);
                if (linearLayout != null) {
                    i = R.id.ll_search;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                    if (linearLayout2 != null) {
                        i = R.id.ll_sr_result;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_sr_result);
                        if (linearLayout3 != null) {
                            i = R.id.ll_top_result;
                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_result);
                            if (linearLayout4 != null) {
                                i = R.id.net_error_view;
                                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
                                if (noNetView != null) {
                                    i = R.id.no_data_view;
                                    NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                                    if (noNetView2 != null) {
                                        i = R.id.place_holder;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.place_holder);
                                        if (imageView2 != null) {
                                            i = R.id.rl_search;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                            if (relativeLayout != null) {
                                                i = R.id.rv_sr_result;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_sr_result);
                                                if (recyclerView != null) {
                                                    i = R.id.rv_top_sr;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_top_sr);
                                                    if (recyclerView2 != null) {
                                                        i = R.id.search_header;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.search_header);
                                                        if (relativeLayout2 != null) {
                                                            i = R.id.tv_cancel;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                                                            if (textView != null) {
                                                                return new ActivityVoteSearchSrBinding((RelativeLayout) view, editText, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, noNetView, noNetView2, imageView2, relativeLayout, recyclerView, recyclerView2, relativeLayout2, textView);
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
