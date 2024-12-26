package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class RiskTokenListFragmentBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivClear;
    public final LinearLayout llSearch;
    private final RelativeLayout rootView;
    public final ImageView searchIcon;
    public final RecyclerView tokenList;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private RiskTokenListFragmentBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, LinearLayout linearLayout, ImageView imageView2, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.llSearch = linearLayout;
        this.searchIcon = imageView2;
        this.tokenList = recyclerView;
    }

    public static RiskTokenListFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static RiskTokenListFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.risk_token_list_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static RiskTokenListFragmentBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.ll_search;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                if (linearLayout != null) {
                    i = R.id.search_icon;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.search_icon);
                    if (imageView2 != null) {
                        i = R.id.token_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.token_list);
                        if (recyclerView != null) {
                            return new RiskTokenListFragmentBinding((RelativeLayout) view, editText, imageView, linearLayout, imageView2, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
