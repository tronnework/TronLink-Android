package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LedgerNoDeviceBinding implements ViewBinding {
    public final Button btnRetry;
    public final ImageView ivIcon;
    private final RelativeLayout rootView;
    public final TextView tvSubtip;
    public final TextView tvTip;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LedgerNoDeviceBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnRetry = button;
        this.ivIcon = imageView;
        this.tvSubtip = textView;
        this.tvTip = textView2;
    }

    public static LedgerNoDeviceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LedgerNoDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ledger_no_device, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LedgerNoDeviceBinding bind(View view) {
        int i = R.id.btn_retry;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_retry);
        if (button != null) {
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.tv_subtip;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtip);
                if (textView != null) {
                    i = R.id.tv_tip;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip);
                    if (textView2 != null) {
                        return new LedgerNoDeviceBinding((RelativeLayout) view, button, imageView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
