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
import com.tron.wallet.common.components.tablayout.MsgView;
import com.tronlinkpro.wallet.R;
public final class LayoutTabLeftBinding implements ViewBinding {
    public final ImageView ivTabIcon;
    public final LinearLayout llTap;
    private final RelativeLayout rootView;
    public final MsgView rtvMsgTip;
    public final TextView tvTabTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutTabLeftBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, MsgView msgView, TextView textView) {
        this.rootView = relativeLayout;
        this.ivTabIcon = imageView;
        this.llTap = linearLayout;
        this.rtvMsgTip = msgView;
        this.tvTabTitle = textView;
    }

    public static LayoutTabLeftBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutTabLeftBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_tab_left, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutTabLeftBinding bind(View view) {
        int i = R.id.iv_tab_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tab_icon);
        if (imageView != null) {
            i = R.id.ll_tap;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_tap);
            if (linearLayout != null) {
                i = R.id.rtv_msg_tip;
                MsgView msgView = (MsgView) ViewBindings.findChildViewById(view, R.id.rtv_msg_tip);
                if (msgView != null) {
                    i = R.id.tv_tab_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tab_title);
                    if (textView != null) {
                        return new LayoutTabLeftBinding((RelativeLayout) view, imageView, linearLayout, msgView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
