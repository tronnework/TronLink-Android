package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class LayoutTransferTokenSelectBinding implements ViewBinding {
    public final View divider;
    public final EditText etSearch;
    public final ImageView ivClear;
    public final FrameLayout ivCommonLeft;
    public final ImageView ivSearch;
    public final NoNetView noNetView;
    public final RelativeLayout rlSearch;
    private final RelativeLayout rootView;
    public final RecyclerView rvTokenList;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutTransferTokenSelectBinding(RelativeLayout relativeLayout, View view, EditText editText, ImageView imageView, FrameLayout frameLayout, ImageView imageView2, NoNetView noNetView, RelativeLayout relativeLayout2, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.divider = view;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.ivCommonLeft = frameLayout;
        this.ivSearch = imageView2;
        this.noNetView = noNetView;
        this.rlSearch = relativeLayout2;
        this.rvTokenList = recyclerView;
        this.tvTitle = textView;
    }

    public static LayoutTransferTokenSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutTransferTokenSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_transfer_token_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutTransferTokenSelectBinding bind(View view) {
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.et_search;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
            if (editText != null) {
                i = R.id.iv_clear;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
                if (imageView != null) {
                    i = R.id.iv_common_left;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                    if (frameLayout != null) {
                        i = R.id.iv_search;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search);
                        if (imageView2 != null) {
                            i = R.id.no_net_view;
                            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                            if (noNetView != null) {
                                i = R.id.rl_search;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                if (relativeLayout != null) {
                                    i = R.id.rv_token_list;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_token_list);
                                    if (recyclerView != null) {
                                        i = R.id.tv_title;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                        if (textView != null) {
                                            return new LayoutTransferTokenSelectBinding((RelativeLayout) view, findChildViewById, editText, imageView, frameLayout, imageView2, noNetView, relativeLayout, recyclerView, textView);
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
