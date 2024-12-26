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
import com.tron.wallet.common.components.EllipsizedTextView;
import com.tronlinkpro.wallet.R;
public final class ScamAddressWarningPopwindowBinding implements ViewBinding {
    public final TextView addressRiskPopContent;
    public final LinearLayout addressView;
    public final Button btnCancel;
    public final Button btnContinue;
    public final ImageView ivIcon;
    public final ImageView ivRaCopy;
    public final LinearLayout llAction2;
    private final RelativeLayout rootView;
    public final TextView title;
    public final EllipsizedTextView tvAddress;
    public final TextView tvTronscan;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ScamAddressWarningPopwindowBinding(RelativeLayout relativeLayout, TextView textView, LinearLayout linearLayout, Button button, Button button2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, TextView textView2, EllipsizedTextView ellipsizedTextView, TextView textView3) {
        this.rootView = relativeLayout;
        this.addressRiskPopContent = textView;
        this.addressView = linearLayout;
        this.btnCancel = button;
        this.btnContinue = button2;
        this.ivIcon = imageView;
        this.ivRaCopy = imageView2;
        this.llAction2 = linearLayout2;
        this.title = textView2;
        this.tvAddress = ellipsizedTextView;
        this.tvTronscan = textView3;
    }

    public static ScamAddressWarningPopwindowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ScamAddressWarningPopwindowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.scam_address_warning_popwindow, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ScamAddressWarningPopwindowBinding bind(View view) {
        int i = R.id.address_risk_pop_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.address_risk_pop_content);
        if (textView != null) {
            i = R.id.address_view;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.address_view);
            if (linearLayout != null) {
                i = R.id.btn_cancel;
                Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel);
                if (button != null) {
                    i = R.id.btn_continue;
                    Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_continue);
                    if (button2 != null) {
                        i = R.id.iv_icon;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                        if (imageView != null) {
                            i = R.id.iv_ra_copy;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra_copy);
                            if (imageView2 != null) {
                                i = R.id.ll_action_2;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action_2);
                                if (linearLayout2 != null) {
                                    i = R.id.title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                    if (textView2 != null) {
                                        i = R.id.tv_address;
                                        EllipsizedTextView ellipsizedTextView = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                        if (ellipsizedTextView != null) {
                                            i = R.id.tv_tronscan;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tronscan);
                                            if (textView3 != null) {
                                                return new ScamAddressWarningPopwindowBinding((RelativeLayout) view, textView, linearLayout, button, button2, imageView, imageView2, linearLayout2, textView2, ellipsizedTextView, textView3);
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
