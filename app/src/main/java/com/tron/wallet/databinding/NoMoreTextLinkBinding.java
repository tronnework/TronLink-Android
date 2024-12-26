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
public final class NoMoreTextLinkBinding implements ViewBinding {
    public final ImageView ivFailed;
    public final ImageView ivLoading;
    public final RelativeLayout rlLoadingFailed;
    public final RelativeLayout rlNoMoreText;
    private final RelativeLayout rootView;
    public final TextView tvExtra;
    public final TextView tvNoMore;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NoMoreTextLinkBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivFailed = imageView;
        this.ivLoading = imageView2;
        this.rlLoadingFailed = relativeLayout2;
        this.rlNoMoreText = relativeLayout3;
        this.tvExtra = textView;
        this.tvNoMore = textView2;
    }

    public static NoMoreTextLinkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NoMoreTextLinkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.no_more_text_link, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NoMoreTextLinkBinding bind(View view) {
        int i = R.id.iv_failed;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_failed);
        if (imageView != null) {
            i = R.id.iv_loading;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
            if (imageView2 != null) {
                i = R.id.rl_loading_failed;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_loading_failed);
                if (relativeLayout != null) {
                    i = R.id.rl_no_more_text;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_no_more_text);
                    if (relativeLayout2 != null) {
                        i = R.id.tv_extra;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_extra);
                        if (textView != null) {
                            i = R.id.tv_no_more;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_more);
                            if (textView2 != null) {
                                return new NoMoreTextLinkBinding((RelativeLayout) view, imageView, imageView2, relativeLayout, relativeLayout2, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
