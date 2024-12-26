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
public final class PopupViewWithTitleIconBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnConfirm;
    public final ImageView ivIcon;
    public final LinearLayout llAction;
    private final RelativeLayout rootView;
    public final TextView tvInfo;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupViewWithTitleIconBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnCancel = button;
        this.btnConfirm = button2;
        this.ivIcon = imageView;
        this.llAction = linearLayout;
        this.tvInfo = textView;
        this.tvTitle = textView2;
    }

    public static PopupViewWithTitleIconBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupViewWithTitleIconBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_view_with_title_icon, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupViewWithTitleIconBinding bind(View view) {
        int i = R.id.btn_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel);
        if (button != null) {
            i = R.id.btn_confirm;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
            if (button2 != null) {
                i = R.id.iv_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                if (imageView != null) {
                    i = R.id.ll_action;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                    if (linearLayout != null) {
                        i = R.id.tv_info;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
                        if (textView != null) {
                            i = R.id.tv_title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView2 != null) {
                                return new PopupViewWithTitleIconBinding((RelativeLayout) view, button, button2, imageView, linearLayout, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
