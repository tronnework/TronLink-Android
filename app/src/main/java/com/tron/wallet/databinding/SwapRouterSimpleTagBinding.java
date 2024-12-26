package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class SwapRouterSimpleTagBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final SimpleDraweeView ivLogo;
    private final RelativeLayout rootView;
    public final TextView tvSymbol;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapRouterSimpleTagBinding(RelativeLayout relativeLayout, ImageView imageView, SimpleDraweeView simpleDraweeView, TextView textView) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivLogo = simpleDraweeView;
        this.tvSymbol = textView;
    }

    public static SwapRouterSimpleTagBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapRouterSimpleTagBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_router_simple_tag, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapRouterSimpleTagBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_logo;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
            if (simpleDraweeView != null) {
                i = R.id.tv_symbol;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_symbol);
                if (textView != null) {
                    return new SwapRouterSimpleTagBinding((RelativeLayout) view, imageView, simpleDraweeView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
