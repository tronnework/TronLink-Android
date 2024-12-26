package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tronlinkpro.wallet.R;
public final class PopupDelegateForOtherBinding implements ViewBinding {
    public final ImageView ivCommonRight;
    public final RelativeLayout rlTitle;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvTitle;
    public final TextView tvTitleTag;
    public final ViewPager vpContent;
    public final XTabLayout xTabLayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupDelegateForOtherBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, ViewPager viewPager, XTabLayout xTabLayout) {
        this.rootView = relativeLayout;
        this.ivCommonRight = imageView;
        this.rlTitle = relativeLayout2;
        this.root = relativeLayout3;
        this.tvTitle = textView;
        this.tvTitleTag = textView2;
        this.vpContent = viewPager;
        this.xTabLayout = xTabLayout;
    }

    public static PopupDelegateForOtherBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupDelegateForOtherBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_delegate_for_other, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupDelegateForOtherBinding bind(View view) {
        int i = R.id.iv_common_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
        if (imageView != null) {
            i = R.id.rl_title;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
            if (relativeLayout != null) {
                RelativeLayout relativeLayout2 = (RelativeLayout) view;
                i = R.id.tv_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                if (textView != null) {
                    i = R.id.tv_title_tag;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_tag);
                    if (textView2 != null) {
                        i = R.id.vp_content;
                        ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.vp_content);
                        if (viewPager != null) {
                            i = R.id.x_tab_layout;
                            XTabLayout xTabLayout = (XTabLayout) ViewBindings.findChildViewById(view, R.id.x_tab_layout);
                            if (xTabLayout != null) {
                                return new PopupDelegateForOtherBinding(relativeLayout2, imageView, relativeLayout, relativeLayout2, textView, textView2, viewPager, xTabLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
