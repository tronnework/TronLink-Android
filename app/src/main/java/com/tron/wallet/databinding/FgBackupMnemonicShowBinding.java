package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgBackupMnemonicShowBinding implements ViewBinding {
    public final Button btCancel;
    public final Button btKnow;
    public final Button btSaved;
    public final ImageView ivQrCode;
    public final LinearLayout llHideQrcode;
    public final LinearLayout llQrCode;
    public final RecyclerView rlMnemonic;
    public final LinearLayout rlQrcode;
    private final RelativeLayout rootView;
    public final TextView tvTip;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgBackupMnemonicShowBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, LinearLayout linearLayout3, TextView textView) {
        this.rootView = relativeLayout;
        this.btCancel = button;
        this.btKnow = button2;
        this.btSaved = button3;
        this.ivQrCode = imageView;
        this.llHideQrcode = linearLayout;
        this.llQrCode = linearLayout2;
        this.rlMnemonic = recyclerView;
        this.rlQrcode = linearLayout3;
        this.tvTip = textView;
    }

    public static FgBackupMnemonicShowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgBackupMnemonicShowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_backup_mnemonic_show, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgBackupMnemonicShowBinding bind(View view) {
        int i = R.id.bt_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_cancel);
        if (button != null) {
            i = R.id.bt_know;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_know);
            if (button2 != null) {
                i = R.id.bt_saved;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.bt_saved);
                if (button3 != null) {
                    i = R.id.iv_qr_code;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr_code);
                    if (imageView != null) {
                        i = R.id.ll_hide_qrcode;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_hide_qrcode);
                        if (linearLayout != null) {
                            i = R.id.ll_qr_code;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_qr_code);
                            if (linearLayout2 != null) {
                                i = R.id.rl_mnemonic;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rl_mnemonic);
                                if (recyclerView != null) {
                                    i = R.id.rl_qrcode;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_qrcode);
                                    if (linearLayout3 != null) {
                                        i = R.id.tv_tip;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip);
                                        if (textView != null) {
                                            return new FgBackupMnemonicShowBinding((RelativeLayout) view, button, button2, button3, imageView, linearLayout, linearLayout2, recyclerView, linearLayout3, textView);
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
