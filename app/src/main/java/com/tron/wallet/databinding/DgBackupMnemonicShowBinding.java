package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgBackupMnemonicShowBinding implements ViewBinding {
    public final Button btCancel;
    public final Button btKnow;
    public final ImageView ivQrCode;
    public final LinearLayout llQrCode;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgBackupMnemonicShowBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.btCancel = button;
        this.btKnow = button2;
        this.ivQrCode = imageView;
        this.llQrCode = linearLayout;
    }

    public static DgBackupMnemonicShowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgBackupMnemonicShowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_backup_mnemonic_show, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgBackupMnemonicShowBinding bind(View view) {
        int i = R.id.bt_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_cancel);
        if (button != null) {
            i = R.id.bt_know;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_know);
            if (button2 != null) {
                i = R.id.iv_qr_code;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr_code);
                if (imageView != null) {
                    i = R.id.ll_qr_code;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_qr_code);
                    if (linearLayout != null) {
                        return new DgBackupMnemonicShowBinding((RelativeLayout) view, button, button2, imageView, linearLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
