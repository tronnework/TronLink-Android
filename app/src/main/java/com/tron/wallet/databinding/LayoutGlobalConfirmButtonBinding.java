package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutGlobalConfirmButtonBinding implements ViewBinding {
    public final Button btnConfirm;
    public final ImageView ivError;
    public final ImageView ivFeeLoading;
    public final LinearLayout llError;
    private final LinearLayout rootView;
    public final TextView tvBtnText;
    public final TextView tvErrorBottom;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutGlobalConfirmButtonBinding(LinearLayout linearLayout, Button button, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.btnConfirm = button;
        this.ivError = imageView;
        this.ivFeeLoading = imageView2;
        this.llError = linearLayout2;
        this.tvBtnText = textView;
        this.tvErrorBottom = textView2;
    }

    public static LayoutGlobalConfirmButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutGlobalConfirmButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_global_confirm_button, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGlobalConfirmButtonBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.iv_error;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_error);
            if (imageView != null) {
                i = R.id.iv_fee_loading;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_fee_loading);
                if (imageView2 != null) {
                    i = R.id.ll_error;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_error);
                    if (linearLayout != null) {
                        i = R.id.tv_btn_text;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_btn_text);
                        if (textView != null) {
                            i = R.id.tv_error_bottom;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_bottom);
                            if (textView2 != null) {
                                return new LayoutGlobalConfirmButtonBinding((LinearLayout) view, button, imageView, imageView2, linearLayout, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
