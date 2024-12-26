package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LoadingTextLoadMoreBinding implements ViewBinding {
    public final ImageView ivFailed;
    public final TextView noMore;
    public final RelativeLayout rlLoadingFailed;
    private final RelativeLayout rootView;
    public final TextView tvLoading;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LoadingTextLoadMoreBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, RelativeLayout relativeLayout2, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivFailed = imageView;
        this.noMore = textView;
        this.rlLoadingFailed = relativeLayout2;
        this.tvLoading = textView2;
    }

    public static LoadingTextLoadMoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoadingTextLoadMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.loading_text_load_more, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoadingTextLoadMoreBinding bind(View view) {
        int i = R.id.iv_failed;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_failed);
        if (imageView != null) {
            i = R.id.no_more;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.no_more);
            if (textView != null) {
                i = R.id.rl_loading_failed;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_loading_failed);
                if (relativeLayout != null) {
                    i = R.id.tv_loading;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_loading);
                    if (textView2 != null) {
                        return new LoadingTextLoadMoreBinding((RelativeLayout) view, imageView, textView, relativeLayout, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
