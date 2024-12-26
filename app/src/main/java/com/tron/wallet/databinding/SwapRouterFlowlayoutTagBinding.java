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
public final class SwapRouterFlowlayoutTagBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final SimpleDraweeView ivLogo;
    private final RelativeLayout rootView;
    public final TextView tvPoolVersion;
    public final TextView tvSymbol;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapRouterFlowlayoutTagBinding(RelativeLayout relativeLayout, ImageView imageView, SimpleDraweeView simpleDraweeView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivLogo = simpleDraweeView;
        this.tvPoolVersion = textView;
        this.tvSymbol = textView2;
    }

    public static SwapRouterFlowlayoutTagBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapRouterFlowlayoutTagBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_router_flowlayout_tag, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapRouterFlowlayoutTagBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_logo;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
            if (simpleDraweeView != null) {
                i = R.id.tv_pool_version;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pool_version);
                if (textView != null) {
                    i = R.id.tv_symbol;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_symbol);
                    if (textView2 != null) {
                        return new SwapRouterFlowlayoutTagBinding((RelativeLayout) view, imageView, simpleDraweeView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
