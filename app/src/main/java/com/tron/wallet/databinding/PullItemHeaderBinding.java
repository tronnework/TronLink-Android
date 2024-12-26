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
public final class PullItemHeaderBinding implements ViewBinding {
    public final ImageView ivPullTitleError;
    public final ImageView ivPullTitleRightError;
    public final LinearLayout liPullTitleError;
    private final RelativeLayout rootView;
    public final TextView tvPullTitle;
    public final TextView tvPullTitleError;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PullItemHeaderBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivPullTitleError = imageView;
        this.ivPullTitleRightError = imageView2;
        this.liPullTitleError = linearLayout;
        this.tvPullTitle = textView;
        this.tvPullTitleError = textView2;
    }

    public static PullItemHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PullItemHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pull_item_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PullItemHeaderBinding bind(View view) {
        int i = R.id.iv_pull_title_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_pull_title_error);
        if (imageView != null) {
            i = R.id.iv_pull_title_right_error;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_pull_title_right_error);
            if (imageView2 != null) {
                i = R.id.li_pull_title_error;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_pull_title_error);
                if (linearLayout != null) {
                    i = R.id.tv_pull_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_title);
                    if (textView != null) {
                        i = R.id.tv_pull_title_error;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_title_error);
                        if (textView2 != null) {
                            return new PullItemHeaderBinding((RelativeLayout) view, imageView, imageView2, linearLayout, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
