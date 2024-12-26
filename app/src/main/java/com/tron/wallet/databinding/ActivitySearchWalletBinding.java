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
public final class ActivitySearchWalletBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivClear;
    public final LinearLayout llRecent;
    public final LinearLayout llResult;
    public final LinearLayout llSearch;
    public final NoNetView noResultView;
    private final RelativeLayout rootView;
    public final RecyclerView rvRecent;
    public final RecyclerView rvSearchResult;
    public final TextView tvCancel;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivitySearchWalletBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, NoNetView noNetView, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.llRecent = linearLayout;
        this.llResult = linearLayout2;
        this.llSearch = linearLayout3;
        this.noResultView = noNetView;
        this.rvRecent = recyclerView;
        this.rvSearchResult = recyclerView2;
        this.tvCancel = textView;
    }

    public static ActivitySearchWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivitySearchWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_search_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivitySearchWalletBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.ll_recent;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_recent);
                if (linearLayout != null) {
                    i = R.id.ll_result;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_result);
                    if (linearLayout2 != null) {
                        i = R.id.ll_search;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                        if (linearLayout3 != null) {
                            i = R.id.no_result_view;
                            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_result_view);
                            if (noNetView != null) {
                                i = R.id.rv_recent;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_recent);
                                if (recyclerView != null) {
                                    i = R.id.rv_search_result;
                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_search_result);
                                    if (recyclerView2 != null) {
                                        i = R.id.tv_cancel;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                                        if (textView != null) {
                                            return new ActivitySearchWalletBinding((RelativeLayout) view, editText, imageView, linearLayout, linearLayout2, linearLayout3, noNetView, recyclerView, recyclerView2, textView);
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
