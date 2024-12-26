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
public final class PopupViewShieldMainlandNoticeBinding implements ViewBinding {
    public final Button btnConfirm;
    public final ImageView ivIcon;
    public final ImageView ivSelect;
    public final LinearLayout llAction;
    public final LinearLayout llIKnow;
    private final RelativeLayout rootView;
    public final TextView tvIKnow;
    public final TextView tvInfo;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupViewShieldMainlandNoticeBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnConfirm = button;
        this.ivIcon = imageView;
        this.ivSelect = imageView2;
        this.llAction = linearLayout;
        this.llIKnow = linearLayout2;
        this.tvIKnow = textView;
        this.tvInfo = textView2;
        this.tvTitle = textView3;
    }

    public static PopupViewShieldMainlandNoticeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupViewShieldMainlandNoticeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_view_shield_mainland_notice, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupViewShieldMainlandNoticeBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.iv_select;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select);
                if (imageView2 != null) {
                    i = R.id.ll_action;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                    if (linearLayout != null) {
                        i = R.id.ll_i_know;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_i_know);
                        if (linearLayout2 != null) {
                            i = R.id.tv_i_know;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_i_know);
                            if (textView != null) {
                                i = R.id.tv_info;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
                                if (textView2 != null) {
                                    i = R.id.tv_title;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (textView3 != null) {
                                        return new PopupViewShieldMainlandNoticeBinding((RelativeLayout) view, button, imageView, imageView2, linearLayout, linearLayout2, textView, textView2, textView3);
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
