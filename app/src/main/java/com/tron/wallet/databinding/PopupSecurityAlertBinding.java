package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupSecurityAlertBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnConfirm;
    public final TextView info;
    public final ImageView ivAlert;
    public final LinearLayout llAction;
    private final RelativeLayout rootView;
    public final TextView title;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupSecurityAlertBinding(RelativeLayout relativeLayout, Button button, Button button2, TextView textView, ImageView imageView, LinearLayout linearLayout, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnCancel = button;
        this.btnConfirm = button2;
        this.info = textView;
        this.ivAlert = imageView;
        this.llAction = linearLayout;
        this.title = textView2;
    }

    public static PopupSecurityAlertBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupSecurityAlertBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_security_alert, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupSecurityAlertBinding bind(View view) {
        int i = R.id.btn_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel);
        if (button != null) {
            i = R.id.btn_confirm;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
            if (button2 != null) {
                i = R.id.info;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.info);
                if (textView != null) {
                    i = R.id.iv_alert;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_alert);
                    if (imageView != null) {
                        i = R.id.ll_action;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                        if (linearLayout != null) {
                            i = R.id.title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView2 != null) {
                                return new PopupSecurityAlertBinding((RelativeLayout) view, button, button2, textView, imageView, linearLayout, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
