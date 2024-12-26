package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class FgTokendetailContentBinding implements ViewBinding {
    public final AppCompatEditText etSearch;
    public final NestedScrollView llNodata;
    public final NestedScrollView llShasta;
    public final NoNetView noNetView;
    public final LoadMoreRecyclerView rlContent;
    public final ImageView rlPlaceHolder;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvNoData;
    public final NestedScrollView tvNoNet;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgTokendetailContentBinding(RelativeLayout relativeLayout, AppCompatEditText appCompatEditText, NestedScrollView nestedScrollView, NestedScrollView nestedScrollView2, NoNetView noNetView, LoadMoreRecyclerView loadMoreRecyclerView, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, NestedScrollView nestedScrollView3) {
        this.rootView = relativeLayout;
        this.etSearch = appCompatEditText;
        this.llNodata = nestedScrollView;
        this.llShasta = nestedScrollView2;
        this.noNetView = noNetView;
        this.rlContent = loadMoreRecyclerView;
        this.rlPlaceHolder = imageView;
        this.root = relativeLayout2;
        this.tvNoData = textView;
        this.tvNoNet = nestedScrollView3;
    }

    public static FgTokendetailContentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgTokendetailContentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_tokendetail_content, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgTokendetailContentBinding bind(View view) {
        int i = R.id.et_search;
        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (appCompatEditText != null) {
            i = R.id.ll_nodata;
            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
            if (nestedScrollView != null) {
                i = R.id.ll_shasta;
                NestedScrollView nestedScrollView2 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_shasta);
                if (nestedScrollView2 != null) {
                    i = R.id.no_net_view;
                    NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                    if (noNetView != null) {
                        i = R.id.rl_content;
                        LoadMoreRecyclerView loadMoreRecyclerView = (LoadMoreRecyclerView) ViewBindings.findChildViewById(view, R.id.rl_content);
                        if (loadMoreRecyclerView != null) {
                            i = R.id.rl_place_holder;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rl_place_holder);
                            if (imageView != null) {
                                RelativeLayout relativeLayout = (RelativeLayout) view;
                                i = R.id.tv_no_data;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                                if (textView != null) {
                                    i = R.id.tv_no_net;
                                    NestedScrollView nestedScrollView3 = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.tv_no_net);
                                    if (nestedScrollView3 != null) {
                                        return new FgTokendetailContentBinding(relativeLayout, appCompatEditText, nestedScrollView, nestedScrollView2, noNetView, loadMoreRecyclerView, imageView, relativeLayout, textView, nestedScrollView3);
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
