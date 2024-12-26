package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PullItemHeaderLocalBinding implements ViewBinding {
    public final ImageView ivPullTitleError;
    public final LinearLayout liPullTitleError;
    private final RelativeLayout rootView;
    public final TextView tvPullTitleError;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PullItemHeaderLocalBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.ivPullTitleError = imageView;
        this.liPullTitleError = linearLayout;
        this.tvPullTitleError = textView;
    }

    public static PullItemHeaderLocalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PullItemHeaderLocalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pull_item_header_local, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PullItemHeaderLocalBinding bind(View view) {
        int i = R.id.iv_pull_title_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_pull_title_error);
        if (imageView != null) {
            i = R.id.li_pull_title_error;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_pull_title_error);
            if (linearLayout != null) {
                i = R.id.tv_pull_title_error;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_title_error);
                if (textView != null) {
                    return new PullItemHeaderLocalBinding((RelativeLayout) view, imageView, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
