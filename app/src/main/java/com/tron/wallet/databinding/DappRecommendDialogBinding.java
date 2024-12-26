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
public final class DappRecommendDialogBinding implements ViewBinding {
    public final TextView dappRecomCentent;
    public final TextView dappRecomTitle;
    public final SimpleDraweeView ivLogo;
    public final ImageView recomClose;
    private final RelativeLayout rootView;
    public final TextView tvEnter;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DappRecommendDialogBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, SimpleDraweeView simpleDraweeView, ImageView imageView, TextView textView3) {
        this.rootView = relativeLayout;
        this.dappRecomCentent = textView;
        this.dappRecomTitle = textView2;
        this.ivLogo = simpleDraweeView;
        this.recomClose = imageView;
        this.tvEnter = textView3;
    }

    public static DappRecommendDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DappRecommendDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dapp_recommend_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DappRecommendDialogBinding bind(View view) {
        int i = R.id.dapp_recom_centent;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.dapp_recom_centent);
        if (textView != null) {
            i = R.id.dapp_recom_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.dapp_recom_title);
            if (textView2 != null) {
                i = R.id.iv_logo;
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
                if (simpleDraweeView != null) {
                    i = R.id.recom_close;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.recom_close);
                    if (imageView != null) {
                        i = R.id.tv_enter;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_enter);
                        if (textView3 != null) {
                            return new DappRecommendDialogBinding((RelativeLayout) view, textView, textView2, simpleDraweeView, imageView, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
