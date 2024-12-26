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
public final class NoMoreQueryAllApprovedBinding implements ViewBinding {
    public final ImageView ivFailed;
    public final ImageView ivLoading;
    public final RelativeLayout rlLoadingFailed;
    public final RelativeLayout rlNoMoreText;
    private final RelativeLayout rootView;
    public final TextView tvNoMore;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NoMoreQueryAllApprovedBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView) {
        this.rootView = relativeLayout;
        this.ivFailed = imageView;
        this.ivLoading = imageView2;
        this.rlLoadingFailed = relativeLayout2;
        this.rlNoMoreText = relativeLayout3;
        this.tvNoMore = textView;
    }

    public static NoMoreQueryAllApprovedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NoMoreQueryAllApprovedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.no_more_query_all_approved, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NoMoreQueryAllApprovedBinding bind(View view) {
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
                        i = R.id.tv_no_more;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_more);
                        if (textView != null) {
                            return new NoMoreQueryAllApprovedBinding((RelativeLayout) view, imageView, imageView2, relativeLayout, relativeLayout2, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
