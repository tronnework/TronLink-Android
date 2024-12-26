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
public final class PopupRiskWarningBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnConfirm;
    public final TextView info;
    public final TextView info2;
    public final ImageView ivAlert;
    public final LinearLayout liTips1;
    public final LinearLayout liTips2;
    public final LinearLayout llAction;
    private final RelativeLayout rootView;
    public final TextView title;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupRiskWarningBinding(RelativeLayout relativeLayout, Button button, Button button2, TextView textView, TextView textView2, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnCancel = button;
        this.btnConfirm = button2;
        this.info = textView;
        this.info2 = textView2;
        this.ivAlert = imageView;
        this.liTips1 = linearLayout;
        this.liTips2 = linearLayout2;
        this.llAction = linearLayout3;
        this.title = textView3;
    }

    public static PopupRiskWarningBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupRiskWarningBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_risk_warning, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupRiskWarningBinding bind(View view) {
        int i = R.id.btn_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel);
        if (button != null) {
            i = R.id.btn_confirm;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
            if (button2 != null) {
                i = R.id.info;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.info);
                if (textView != null) {
                    i = R.id.info2;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.info2);
                    if (textView2 != null) {
                        i = R.id.iv_alert;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_alert);
                        if (imageView != null) {
                            i = R.id.li_tips1;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_tips1);
                            if (linearLayout != null) {
                                i = R.id.li_tips2;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_tips2);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_action;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                                    if (linearLayout3 != null) {
                                        i = R.id.title;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                        if (textView3 != null) {
                                            return new PopupRiskWarningBinding((RelativeLayout) view, button, button2, textView, textView2, imageView, linearLayout, linearLayout2, linearLayout3, textView3);
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
