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
public final class ItemWarningTagBinding implements ViewBinding {
    public final ImageView ivScamClose;
    public final ImageView ivScamNotice;
    public final RelativeLayout rlScam;
    private final RelativeLayout rootView;
    public final TextView tvScamNotice;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemWarningTagBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivScamClose = imageView;
        this.ivScamNotice = imageView2;
        this.rlScam = relativeLayout2;
        this.tvScamNotice = textView;
    }

    public static ItemWarningTagBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemWarningTagBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_warning_tag, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemWarningTagBinding bind(View view) {
        int i = R.id.iv_scam_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam_close);
        if (imageView != null) {
            i = R.id.iv_scam_notice;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam_notice);
            if (imageView2 != null) {
                i = R.id.rl_scam;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_scam);
                if (relativeLayout != null) {
                    i = R.id.tv_scam_notice;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_scam_notice);
                    if (textView != null) {
                        return new ItemWarningTagBinding((RelativeLayout) view, imageView, imageView2, relativeLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
