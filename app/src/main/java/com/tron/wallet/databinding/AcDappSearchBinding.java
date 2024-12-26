package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcDappSearchBinding implements ViewBinding {
    public final EditText etSearch;
    public final ImageView icSearch;
    public final ImageView ivDelete;
    public final LinearLayout llCommonBack;
    public final LinearLayout llLoad;
    public final RecyclerView rlContentSearch;
    private final LinearLayout rootView;
    public final TextView tvSearch;
    public final TextView tvSearchTitle;
    public final DappSearchUrlBinding urlEntranceView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcDappSearchBinding(LinearLayout linearLayout, EditText editText, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, LinearLayout linearLayout3, RecyclerView recyclerView, TextView textView, TextView textView2, DappSearchUrlBinding dappSearchUrlBinding) {
        this.rootView = linearLayout;
        this.etSearch = editText;
        this.icSearch = imageView;
        this.ivDelete = imageView2;
        this.llCommonBack = linearLayout2;
        this.llLoad = linearLayout3;
        this.rlContentSearch = recyclerView;
        this.tvSearch = textView;
        this.tvSearchTitle = textView2;
        this.urlEntranceView = dappSearchUrlBinding;
    }

    public static AcDappSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDappSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_dapp_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDappSearchBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.ic_search;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_search);
            if (imageView != null) {
                i = R.id.iv_delete;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                if (imageView2 != null) {
                    i = R.id.ll_common_back;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_back);
                    if (linearLayout != null) {
                        i = R.id.ll_load;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_load);
                        if (linearLayout2 != null) {
                            i = R.id.rl_content_search;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rl_content_search);
                            if (recyclerView != null) {
                                i = R.id.tv_search;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search);
                                if (textView != null) {
                                    i = R.id.tv_search_title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search_title);
                                    if (textView2 != null) {
                                        i = R.id.url_entrance_view;
                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.url_entrance_view);
                                        if (findChildViewById != null) {
                                            return new AcDappSearchBinding((LinearLayout) view, editText, imageView, imageView2, linearLayout, linearLayout2, recyclerView, textView, textView2, DappSearchUrlBinding.bind(findChildViewById));
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
