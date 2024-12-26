package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ListGridItemBinding implements ViewBinding {
    public final RelativeLayout container;
    public final FrameLayout frRoot;
    public final ImageView ivTabClose;
    public final SimpleDraweeView ivTabIcon;
    public final SimpleDraweeView ivTabSnapshot;
    public final ImageView ivTabsMark;
    public final RelativeLayout rlTitlebar;
    private final FrameLayout rootView;
    public final TextView tvTitle;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private ListGridItemBinding(FrameLayout frameLayout, RelativeLayout relativeLayout, FrameLayout frameLayout2, ImageView imageView, SimpleDraweeView simpleDraweeView, SimpleDraweeView simpleDraweeView2, ImageView imageView2, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = frameLayout;
        this.container = relativeLayout;
        this.frRoot = frameLayout2;
        this.ivTabClose = imageView;
        this.ivTabIcon = simpleDraweeView;
        this.ivTabSnapshot = simpleDraweeView2;
        this.ivTabsMark = imageView2;
        this.rlTitlebar = relativeLayout2;
        this.tvTitle = textView;
    }

    public static ListGridItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListGridItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.list_grid_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListGridItemBinding bind(View view) {
        int i = R.id.container;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.container);
        if (relativeLayout != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            i = R.id.iv_tab_close;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tab_close);
            if (imageView != null) {
                i = R.id.iv_tab_icon;
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_tab_icon);
                if (simpleDraweeView != null) {
                    i = R.id.iv_tab_snapshot;
                    SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_tab_snapshot);
                    if (simpleDraweeView2 != null) {
                        i = R.id.iv_tabs_mark;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tabs_mark);
                        if (imageView2 != null) {
                            i = R.id.rl_titlebar;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_titlebar);
                            if (relativeLayout2 != null) {
                                i = R.id.tv_title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                if (textView != null) {
                                    return new ListGridItemBinding(frameLayout, relativeLayout, frameLayout, imageView, simpleDraweeView, simpleDraweeView2, imageView2, relativeLayout2, textView);
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
