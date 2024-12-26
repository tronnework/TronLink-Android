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
import com.tronlinkpro.wallet.R;
public final class FgConfirmPendingBinding implements ViewBinding {
    public final FrameLayout flResult;
    public final RelativeLayout headerLayout;
    public final ImageView ivBack;
    public final LayoutPendingLoadingBinding layoutPendingLoading;
    private final RelativeLayout rootView;
    public final TextView tvMainTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmPendingBinding(RelativeLayout relativeLayout, FrameLayout frameLayout, RelativeLayout relativeLayout2, ImageView imageView, LayoutPendingLoadingBinding layoutPendingLoadingBinding, TextView textView) {
        this.rootView = relativeLayout;
        this.flResult = frameLayout;
        this.headerLayout = relativeLayout2;
        this.ivBack = imageView;
        this.layoutPendingLoading = layoutPendingLoadingBinding;
        this.tvMainTitle = textView;
    }

    public static FgConfirmPendingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmPendingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_pending, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmPendingBinding bind(View view) {
        int i = R.id.fl_result;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_result);
        if (frameLayout != null) {
            i = R.id.header_layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
            if (relativeLayout != null) {
                i = R.id.iv_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                if (imageView != null) {
                    i = R.id.layout_pending_loading;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.layout_pending_loading);
                    if (findChildViewById != null) {
                        LayoutPendingLoadingBinding bind = LayoutPendingLoadingBinding.bind(findChildViewById);
                        i = R.id.tv_main_title;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                        if (textView != null) {
                            return new FgConfirmPendingBinding((RelativeLayout) view, frameLayout, relativeLayout, imageView, bind, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
