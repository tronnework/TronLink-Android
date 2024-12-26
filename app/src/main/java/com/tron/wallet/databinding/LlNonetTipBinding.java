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
public final class LlNonetTipBinding implements ViewBinding {
    public final ImageView ivNonetArrow;
    public final ImageView ivNonetTipClose;
    public final RelativeLayout rlNonetTip;
    private final RelativeLayout rootView;
    public final TextView tvNonetDesc;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LlNonetTipBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivNonetArrow = imageView;
        this.ivNonetTipClose = imageView2;
        this.rlNonetTip = relativeLayout2;
        this.tvNonetDesc = textView;
    }

    public static LlNonetTipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LlNonetTipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ll_nonet_tip, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LlNonetTipBinding bind(View view) {
        int i = R.id.iv_nonet_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_nonet_arrow);
        if (imageView != null) {
            i = R.id.iv_nonet_tip_close;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_nonet_tip_close);
            if (imageView2 != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_nonet_desc;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_nonet_desc);
                if (textView != null) {
                    return new LlNonetTipBinding(relativeLayout, imageView, imageView2, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
