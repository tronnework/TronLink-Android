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
public final class LlSigndealTipBinding implements ViewBinding {
    public final ImageView ivSignClose;
    public final LinearLayout llSignArrow;
    public final RelativeLayout rlDealSignTip;
    private final RelativeLayout rootView;
    public final TextView tvGoNow;
    public final TextView tvSignDesc;
    public final TextView tvSignTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LlSigndealTipBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.ivSignClose = imageView;
        this.llSignArrow = linearLayout;
        this.rlDealSignTip = relativeLayout2;
        this.tvGoNow = textView;
        this.tvSignDesc = textView2;
        this.tvSignTitle = textView3;
    }

    public static LlSigndealTipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LlSigndealTipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ll_signdeal_tip, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LlSigndealTipBinding bind(View view) {
        int i = R.id.iv_sign_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sign_close);
        if (imageView != null) {
            i = R.id.ll_sign_arrow;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_sign_arrow);
            if (linearLayout != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_go_now;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_go_now);
                if (textView != null) {
                    i = R.id.tv_sign_desc;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_desc);
                    if (textView2 != null) {
                        i = R.id.tv_sign_title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_title);
                        if (textView3 != null) {
                            return new LlSigndealTipBinding(relativeLayout, imageView, linearLayout, relativeLayout, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
