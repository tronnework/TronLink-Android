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
public final class DgMnemonicQrBinding implements ViewBinding {
    public final LinearLayout content;
    public final Button ivClose;
    public final ImageView ivQrcode;
    private final RelativeLayout rootView;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgMnemonicQrBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, Button button, ImageView imageView, TextView textView) {
        this.rootView = relativeLayout;
        this.content = linearLayout;
        this.ivClose = button;
        this.ivQrcode = imageView;
        this.tvTitle = textView;
    }

    public static DgMnemonicQrBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgMnemonicQrBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_mnemonic_qr, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgMnemonicQrBinding bind(View view) {
        int i = R.id.content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.content);
        if (linearLayout != null) {
            i = R.id.iv_close;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.iv_close);
            if (button != null) {
                i = R.id.iv_qrcode;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qrcode);
                if (imageView != null) {
                    i = R.id.tv_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView != null) {
                        return new DgMnemonicQrBinding((RelativeLayout) view, linearLayout, button, imageView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
