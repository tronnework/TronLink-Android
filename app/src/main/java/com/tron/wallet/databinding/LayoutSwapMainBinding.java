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
import com.tron.wallet.common.components.NoScrollViewPager;
import com.tronlinkpro.wallet.R;
public final class LayoutSwapMainBinding implements ViewBinding {
    public final ImageView ivError;
    public final ImageView ivServerNotice;
    public final LinearLayout llBg;
    public final RelativeLayout rlBottom;
    public final RelativeLayout rlNetNotice;
    public final RelativeLayout rlServerNotice;
    private final RelativeLayout rootView;
    public final TextView tabFinance;
    public final TextView tabSwap;
    public final TextView tvSwitchServer;
    public final View viewBackground;
    public final NoScrollViewPager viewpagerMain;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutSwapMainBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, View view, NoScrollViewPager noScrollViewPager) {
        this.rootView = relativeLayout;
        this.ivError = imageView;
        this.ivServerNotice = imageView2;
        this.llBg = linearLayout;
        this.rlBottom = relativeLayout2;
        this.rlNetNotice = relativeLayout3;
        this.rlServerNotice = relativeLayout4;
        this.tabFinance = textView;
        this.tabSwap = textView2;
        this.tvSwitchServer = textView3;
        this.viewBackground = view;
        this.viewpagerMain = noScrollViewPager;
    }

    public static LayoutSwapMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSwapMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_swap_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSwapMainBinding bind(View view) {
        int i = R.id.iv_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_error);
        if (imageView != null) {
            i = R.id.iv_server_notice;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_server_notice);
            if (imageView2 != null) {
                i = R.id.ll_bg;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bg);
                if (linearLayout != null) {
                    i = R.id.rl_bottom;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                    if (relativeLayout != null) {
                        i = R.id.rl_net_notice;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_net_notice);
                        if (relativeLayout2 != null) {
                            i = R.id.rl_server_notice;
                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_server_notice);
                            if (relativeLayout3 != null) {
                                i = R.id.tab_finance;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tab_finance);
                                if (textView != null) {
                                    i = R.id.tab_swap;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tab_swap);
                                    if (textView2 != null) {
                                        i = R.id.tv_switch_server;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_server);
                                        if (textView3 != null) {
                                            i = R.id.view_background;
                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_background);
                                            if (findChildViewById != null) {
                                                i = R.id.viewpager_main;
                                                NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(view, R.id.viewpager_main);
                                                if (noScrollViewPager != null) {
                                                    return new LayoutSwapMainBinding((RelativeLayout) view, imageView, imageView2, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, findChildViewById, noScrollViewPager);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
