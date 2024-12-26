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
public final class AcReceiveBinding implements ViewBinding {
    public final ImageView copy;
    public final LinearLayout liSave;
    public final LinearLayout liShare;
    public final ImageView qr;
    public final LinearLayout rlCenter;
    public final RelativeLayout rlTop;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvReceiveWatchonly;
    public final TextView tvScanQrAndPay;
    public final TextView tvWalletName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcReceiveBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, ImageView imageView2, LinearLayout linearLayout3, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.copy = imageView;
        this.liSave = linearLayout;
        this.liShare = linearLayout2;
        this.qr = imageView2;
        this.rlCenter = linearLayout3;
        this.rlTop = relativeLayout2;
        this.tvAddress = textView;
        this.tvReceiveWatchonly = textView2;
        this.tvScanQrAndPay = textView3;
        this.tvWalletName = textView4;
    }

    public static AcReceiveBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcReceiveBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_receive, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcReceiveBinding bind(View view) {
        int i = R.id.copy;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.copy);
        if (imageView != null) {
            i = R.id.li_save;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_save);
            if (linearLayout != null) {
                i = R.id.li_share;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_share);
                if (linearLayout2 != null) {
                    i = R.id.qr;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.qr);
                    if (imageView2 != null) {
                        i = R.id.rl_center;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_center);
                        if (linearLayout3 != null) {
                            i = R.id.rl_top;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                            if (relativeLayout != null) {
                                i = R.id.tv_address;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                if (textView != null) {
                                    i = R.id.tv_receive_watchonly;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_watchonly);
                                    if (textView2 != null) {
                                        i = R.id.tv_scan_qr_and_pay;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_scan_qr_and_pay);
                                        if (textView3 != null) {
                                            i = R.id.tv_wallet_name;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                                            if (textView4 != null) {
                                                return new AcReceiveBinding((RelativeLayout) view, imageView, linearLayout, linearLayout2, imageView2, linearLayout3, relativeLayout, textView, textView2, textView3, textView4);
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
