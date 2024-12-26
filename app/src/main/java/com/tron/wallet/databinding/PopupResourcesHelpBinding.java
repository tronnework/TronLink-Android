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
public final class PopupResourcesHelpBinding implements ViewBinding {
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

    private PopupResourcesHelpBinding(RelativeLayout relativeLayout, Button button, TextView textView, ImageView imageView, LinearLayout linearLayout, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnConfirm = button;
        this.info = textView;
        this.ivAlert = imageView;
        this.llAction = linearLayout;
        this.title = textView2;
    }

    public static PopupResourcesHelpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupResourcesHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_resources_help, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupResourcesHelpBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
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
                            return new PopupResourcesHelpBinding((RelativeLayout) view, button, textView, imageView, linearLayout, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
