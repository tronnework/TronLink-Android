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
public final class PopupSelectWalletBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivClear;
    public final ImageView ivCommonRight;
    public final ImageView ivSearch;
    public final NoNetView noDataView;
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

    private PopupSelectWalletBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, ImageView imageView2, ImageView imageView3, NoNetView noNetView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.ivCommonRight = imageView2;
        this.ivSearch = imageView3;
        this.noDataView = noNetView;
        this.rlSearch = relativeLayout2;
        this.rlTitle = relativeLayout3;
        this.root = relativeLayout4;
        this.rvList = recyclerView;
        this.tvTitle = textView;
    }

    public static PopupSelectWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupSelectWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_select_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupSelectWalletBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.iv_common_right;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
                if (imageView2 != null) {
                    i = R.id.iv_search;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search);
                    if (imageView3 != null) {
                        i = R.id.no_data_view;
                        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                        if (noNetView != null) {
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
                                            return new PopupSelectWalletBinding(relativeLayout3, editText, imageView, imageView2, imageView3, noNetView, relativeLayout, relativeLayout2, relativeLayout3, recyclerView, textView);
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
