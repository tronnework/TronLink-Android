package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class PopupSelectWalletFinanceBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivClear;
    public final ImageView ivCommonRight;
    public final ImageView ivPlaceHolder;
    public final ImageView ivSearch;
    public final NoNetView noDataView;
    public final NoNetView noMatchedView;
    public final RelativeLayout rlSearch;
    public final RelativeLayout rlTitle;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupSelectWalletFinanceBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, NoNetView noNetView, NoNetView noNetView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.ivCommonRight = imageView2;
        this.ivPlaceHolder = imageView3;
        this.ivSearch = imageView4;
        this.noDataView = noNetView;
        this.noMatchedView = noNetView2;
        this.rlSearch = relativeLayout2;
        this.rlTitle = relativeLayout3;
        this.root = relativeLayout4;
        this.rvList = recyclerView;
        this.tvTitle = textView;
    }

    public static PopupSelectWalletFinanceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupSelectWalletFinanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_select_wallet_finance, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupSelectWalletFinanceBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.iv_common_right;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
                if (imageView2 != null) {
                    i = R.id.iv_place_holder;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
                    if (imageView3 != null) {
                        i = R.id.iv_search;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search);
                        if (imageView4 != null) {
                            i = R.id.no_data_view;
                            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                            if (noNetView != null) {
                                i = R.id.no_matched_view;
                                NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_matched_view);
                                if (noNetView2 != null) {
                                    i = R.id.rl_search;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                    if (relativeLayout != null) {
                                        i = R.id.rl_title;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                        if (relativeLayout2 != null) {
                                            RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                            i = R.id.rv_list;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                                            if (recyclerView != null) {
                                                i = R.id.tv_title;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                if (textView != null) {
                                                    return new PopupSelectWalletFinanceBinding(relativeLayout3, editText, imageView, imageView2, imageView3, imageView4, noNetView, noNetView2, relativeLayout, relativeLayout2, relativeLayout3, recyclerView, textView);
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
