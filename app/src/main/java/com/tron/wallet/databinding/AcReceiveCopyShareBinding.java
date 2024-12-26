package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcReceiveCopyShareBinding implements ViewBinding {
    public final ImageView ivReceiveLogoQr;
    public final LinearLayout liQrContent;
    public final ImageView qr2;
    private final RelativeLayout rootView;
    public final TextView tvAddress2;
    public final TextView tvCopyTop;
    public final TextView tvWalletName2;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcReceiveCopyShareBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.ivReceiveLogoQr = imageView;
        this.liQrContent = linearLayout;
        this.qr2 = imageView2;
        this.tvAddress2 = textView;
        this.tvCopyTop = textView2;
        this.tvWalletName2 = textView3;
    }

    public static AcReceiveCopyShareBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcReceiveCopyShareBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_receive_copy_share, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcReceiveCopyShareBinding bind(View view) {
        int i = R.id.iv_receive_logo_qr;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_receive_logo_qr);
        if (imageView != null) {
            i = R.id.li_qr_content;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_qr_content);
            if (linearLayout != null) {
                i = R.id.qr2;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.qr2);
                if (imageView2 != null) {
                    i = R.id.tv_address2;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address2);
                    if (textView != null) {
                        i = R.id.tv_copy_top;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_copy_top);
                        if (textView2 != null) {
                            i = R.id.tv_wallet_name2;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name2);
                            if (textView3 != null) {
                                return new AcReceiveCopyShareBinding((RelativeLayout) view, imageView, linearLayout, imageView2, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
