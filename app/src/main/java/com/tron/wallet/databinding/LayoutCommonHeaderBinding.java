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
public final class LayoutCommonHeaderBinding implements ViewBinding {
    public final TextView btnVoteToolBar;
    public final RelativeLayout headerLayout;
    public final ImageView ivBack;
    public final ImageView ivTips;
    private final RelativeLayout rootView;
    public final TextView tvMainTitle;
    public final TextView tvMultiSign;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutCommonHeaderBinding(RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, ImageView imageView, ImageView imageView2, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnVoteToolBar = textView;
        this.headerLayout = relativeLayout2;
        this.ivBack = imageView;
        this.ivTips = imageView2;
        this.tvMainTitle = textView2;
        this.tvMultiSign = textView3;
    }

    public static LayoutCommonHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutCommonHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_common_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutCommonHeaderBinding bind(View view) {
        int i = R.id.btn_vote_tool_bar;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_vote_tool_bar);
        if (textView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.iv_back;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
            if (imageView != null) {
                i = R.id.iv_tips;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
                if (imageView2 != null) {
                    i = R.id.tv_main_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                    if (textView2 != null) {
                        i = R.id.tv_multi_sign;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_sign);
                        if (textView3 != null) {
                            return new LayoutCommonHeaderBinding(relativeLayout, textView, relativeLayout, imageView, imageView2, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
