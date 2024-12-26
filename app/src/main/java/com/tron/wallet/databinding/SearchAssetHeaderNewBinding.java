package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class SearchAssetHeaderNewBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView ivClear;
    public final LinearLayout llSearch;
    public final RelativeLayout rlSearch;
    private final RelativeLayout rootView;
    public final TextView tvCancel;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SearchAssetHeaderNewBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = editText;
        this.ivClear = imageView;
        this.llSearch = linearLayout;
        this.rlSearch = relativeLayout2;
        this.tvCancel = textView;
    }

    public static SearchAssetHeaderNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchAssetHeaderNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.search_asset_header_new, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SearchAssetHeaderNewBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.ll_search;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                if (linearLayout != null) {
                    i = R.id.rl_search;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                    if (relativeLayout != null) {
                        i = R.id.tv_cancel;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                        if (textView != null) {
                            return new SearchAssetHeaderNewBinding((RelativeLayout) view, editText, imageView, linearLayout, relativeLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
