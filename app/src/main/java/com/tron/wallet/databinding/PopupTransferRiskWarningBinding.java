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
public final class PopupTransferRiskWarningBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnGotIt;
    public final ImageView ivIcon;
    private final LinearLayout rootView;
    public final TextView tvContent;
    public final TextView tvEnterWeb;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopupTransferRiskWarningBinding(LinearLayout linearLayout, Button button, Button button2, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.btnCancel = button;
        this.btnGotIt = button2;
        this.ivIcon = imageView;
        this.tvContent = textView;
        this.tvEnterWeb = textView2;
        this.tvTitle = textView3;
    }

    public static PopupTransferRiskWarningBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupTransferRiskWarningBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_transfer_risk_warning, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupTransferRiskWarningBinding bind(View view) {
        int i = R.id.btn_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel);
        if (button != null) {
            i = R.id.btn_got_it;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_got_it);
            if (button2 != null) {
                i = R.id.iv_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                if (imageView != null) {
                    i = R.id.tv_content;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                    if (textView != null) {
                        i = R.id.tv_enter_web;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_enter_web);
                        if (textView2 != null) {
                            i = R.id.tv_title;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView3 != null) {
                                return new PopupTransferRiskWarningBinding((LinearLayout) view, button, button2, imageView, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
